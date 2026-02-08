<?php
namespace App\Modules\S3\Controller\Http\File;

use App\Base\BaseController;
use Library\Facades\Request;
use Library\Facades\Response;
use Library\Facades\Validate;
use Aws\S3\S3Client;
use Aws\Exception\AwsException;

/**
 * S3文件上传
 * POST /basic/s3/file/add
 */
class Add extends BaseController
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

        // 检查是否有上传的文件
        $file = Request::file('file');
        if (!$file) {
            Response::fail('请选择要上传的文件')->send();
        }
    }

    protected function service(): void
    {
        $key = Request::input('key');
        $file = Request::file('file');

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

            // 读取文件内容
            $filePath = $file->getPathname();
            $fileContent = file_get_contents($filePath);
            $contentType = $file->getMimeType() ?: 'application/octet-stream';

            // 上传文件
            $result = $s3Client->putObject([
                'Bucket' => $config['bucket'],
                'Key' => $key,
                'Body' => $fileContent,
                'ContentType' => $contentType,
            ]);

            // 构建文件URL
            $fileUrl = $config['endpoint'] . '/' . $config['bucket'] . '/' . $key;

            Response::success([
                'key' => $key,
                'url' => $fileUrl,
                'size' => $file->getSize(),
                'contentType' => $contentType,
            ])->send();
        } catch (AwsException $e) {
            Response::fail('文件上传失败: ' . $e->getAwsErrorMessage())->send();
        } catch (\Exception $e) {
            Response::fail('文件上传失败: ' . $e->getMessage())->send();
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
