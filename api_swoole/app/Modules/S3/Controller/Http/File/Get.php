<?php
namespace App\Modules\S3\Controller\Http\File;

use App\Base\BaseController;
use Library\Facades\Request;
use Library\Facades\Response;
use Library\Facades\Validate;
use Aws\S3\S3Client;
use Aws\Exception\AwsException;

/**
 * 获取S3文件信息
 * POST /basic/s3/file/get
 */
class Get extends BaseController
{
    protected function check(): void
    {
        $validator = Validate::make(Request::all(), [
            'key' => 'required|string|max:500',
        ], [
            'key.required' => '文件路径不能为空',
            'key.max' => '文件路径长度不能超过500个字符',
        ]);

        if ($validator->fails()) {
            Response::fail($validator->errors()->first())->send();
        }
    }

    protected function service(): void
    {
        $key = Request::input('key');

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

            // 获取文件元数据
            $result = $s3Client->headObject([
                'Bucket' => $config['bucket'],
                'Key' => $key,
            ]);

            // 构建文件URL
            $fileUrl = $config['endpoint'] . '/' . $config['bucket'] . '/' . $key;

            Response::success([
                'key' => $key,
                'url' => $fileUrl,
                'size' => $result->get('ContentLength'),
                'contentType' => $result->get('ContentType'),
                'lastModified' => $result->get('LastModified') ? $result->get('LastModified')->format('Y-m-d H:i:s') : null,
                'etag' => trim($result->get('ETag'), '"'),
            ])->send();
        } catch (AwsException $e) {
            if ($e->getAwsErrorCode() === 'NotFound' || $e->getAwsErrorCode() === '404') {
                Response::fail('文件不存在')->send();
            }
            Response::fail('获取文件信息失败: ' . $e->getAwsErrorMessage())->send();
        } catch (\Exception $e) {
            Response::fail('获取文件信息失败: ' . $e->getMessage())->send();
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
