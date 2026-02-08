<?php
namespace App\Modules\S3\Controller\Http\File;

use App\Base\BaseController;
use Library\Facades\Request;
use Library\Facades\Response;
use Library\Facades\Validate;
use Aws\S3\S3Client;
use Aws\Exception\AwsException;

/**
 * S3文件批量删除
 * POST /basic/s3/file/batchDelete
 */
class BatchDelete extends BaseController
{
    protected function check(): void
    {
        $validator = Validate::make(Request::all(), [
            'keys' => 'required|array|min:1',
            'keys.*' => 'required|string|max:500',
        ], [
            'keys.required' => '文件路径列表不能为空',
            'keys.array' => '文件路径列表格式错误',
            'keys.min' => '至少需要一个文件路径',
        ]);

        if ($validator->fails()) {
            Response::fail($validator->errors()->first())->send();
        }
    }

    protected function service(): void
    {
        $keys = Request::input('keys', []);

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

            // 构建删除对象列表
            $objects = [];
            foreach ($keys as $key) {
                $objects[] = ['Key' => $key];
            }

            // 批量删除文件
            $result = $s3Client->deleteObjects([
                'Bucket' => $config['bucket'],
                'Delete' => [
                    'Objects' => $objects,
                    'Quiet' => false,
                ],
            ]);

            $deleted = $result->get('Deleted') ?: [];
            $errors = $result->get('Errors') ?: [];

            Response::success([
                'deleted' => count($deleted),
                'failed' => count($errors),
                'deletedKeys' => array_column($deleted, 'Key'),
                'errors' => $errors,
            ])->send();
        } catch (AwsException $e) {
            Response::fail('批量删除失败: ' . $e->getAwsErrorMessage())->send();
        } catch (\Exception $e) {
            Response::fail('批量删除失败: ' . $e->getMessage())->send();
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
