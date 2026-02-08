<?php
namespace App\Modules\S3\Controller\Http\Folder;

use App\Base\BaseController;
use Library\Facades\Request;
use Library\Facades\Response;
use Library\Facades\Validate;
use Aws\S3\S3Client;
use Aws\Exception\AwsException;

/**
 * S3删除文件夹
 * POST /basic/s3/file/folder/delete
 */
class Delete extends BaseController
{
    protected function check(): void
    {
        $validator = Validate::make(Request::all(), [
            'key' => 'required|string|max:500',
        ], [
            'key.required' => '文件夹路径不能为空',
            'key.max' => '文件夹路径长度不能超过500个字符',
        ]);

        if ($validator->fails()) {
            Response::fail($validator->errors()->first())->send();
        }
    }

    protected function service(): void
    {
        $key = Request::input('key');
        
        // 确保文件夹路径以/结尾
        if (!str_ends_with($key, '/')) {
            $key .= '/';
        }

        try {
            // 获取S3配置
            $config = $this->getS3Config();
            
            // 创建S3客户端
            $s3Client = new S3Client([
                'version' => 'latest',
                'region' => $config['region'],
                'endpoint' => $config['endpoint'],
                'use_path_style_endpoint' => true,
                'credentials' => [
                    'key' => $config['access_key'],
                    'secret' => $config['secret_key'],
                ],
            ]);

            // 先列出文件夹下所有对象
            $objects = [];
            $params = [
                'Bucket' => $config['bucket'],
                'Prefix' => $key,
            ];

            do {
                $result = $s3Client->listObjectsV2($params);
                $contents = $result->get('Contents') ?: [];
                
                foreach ($contents as $object) {
                    $objects[] = ['Key' => $object['Key']];
                }

                $params['ContinuationToken'] = $result->get('NextContinuationToken');
            } while ($result->get('IsTruncated'));

            // 如果文件夹下有对象，批量删除
            if (!empty($objects)) {
                $s3Client->deleteObjects([
                    'Bucket' => $config['bucket'],
                    'Delete' => [
                        'Objects' => $objects,
                        'Quiet' => true,
                    ],
                ]);
            }

            Response::success([
                'key' => $key,
                'deletedCount' => count($objects),
                'message' => '文件夹删除成功',
            ])->send();
        } catch (AwsException $e) {
            Response::fail('删除文件夹失败: ' . $e->getAwsErrorMessage())->send();
        } catch (\Exception $e) {
            Response::fail('删除文件夹失败: ' . $e->getMessage())->send();
        }
    }

    /**
     * 获取S3配置
     */
    private function getS3Config(): array
    {
        return [
            'endpoint' => env('S3_ENDPOINT', 'http://localhost:9000'),
            'region' => env('S3_REGION', 'us-east-1'),
            'bucket' => env('S3_BUCKET', 'default'),
            'access_key' => env('S3_ACCESS_KEY', ''),
            'secret_key' => env('S3_SECRET_KEY', ''),
        ];
    }
}
