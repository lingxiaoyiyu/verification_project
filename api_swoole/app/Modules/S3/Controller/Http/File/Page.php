<?php
namespace App\Modules\S3\Controller\Http\File;

use App\Base\BaseController;
use Library\Facades\Request;
use Library\Facades\Response;
use Library\Facades\Validate;
use Aws\S3\S3Client;
use Aws\Exception\AwsException;

/**
 * S3文件列表-分页
 * POST /basic/s3/file/page
 */
class Page extends BaseController
{
    protected function check(): void
    {
        $validator = Validate::make(Request::all(), [
            'prefix' => 'nullable|string|max:500',
            'pageSize' => 'nullable|integer|min:1|max:100',
            'continuationToken' => 'nullable|string',
        ], [
            'prefix.max' => '路径前缀长度不能超过500个字符',
            'pageSize.integer' => '每页数量必须是整数',
            'pageSize.min' => '每页数量最小为1',
            'pageSize.max' => '每页数量最大为100',
        ]);

        if ($validator->fails()) {
            Response::fail($validator->errors()->first())->send();
        }
    }

    protected function service(): void
    {
        $prefix = Request::input('prefix', '');
        $pageSize = Request::input('pageSize', 20);
        $continuationToken = Request::input('continuationToken');

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

            // 构建请求参数
            $params = [
                'Bucket' => $config['bucket'],
                'MaxKeys' => $pageSize,
                'Delimiter' => '/',
            ];

            if (!empty($prefix)) {
                $params['Prefix'] = $prefix;
            }

            if (!empty($continuationToken)) {
                $params['ContinuationToken'] = $continuationToken;
            }

            // 列出对象
            $result = $s3Client->listObjectsV2($params);

            // 解析文件列表
            $files = [];
            $contents = $result->get('Contents') ?: [];
            foreach ($contents as $object) {
                $key = $object['Key'];
                // 跳过目录本身
                if ($key === $prefix) {
                    continue;
                }
                $files[] = [
                    'key' => $key,
                    'size' => $object['Size'],
                    'lastModified' => $object['LastModified'] ? $object['LastModified']->format('Y-m-d H:i:s') : null,
                    'etag' => trim($object['ETag'], '"'),
                    'type' => 'file',
                    'url' => $config['endpoint'] . '/' . $config['bucket'] . '/' . $key,
                ];
            }

            // 解析文件夹列表
            $folders = [];
            $commonPrefixes = $result->get('CommonPrefixes') ?: [];
            foreach ($commonPrefixes as $commonPrefix) {
                $folderKey = $commonPrefix['Prefix'];
                $folders[] = [
                    'key' => $folderKey,
                    'name' => rtrim(str_replace($prefix, '', $folderKey), '/'),
                    'type' => 'folder',
                ];
            }

            Response::success([
                'files' => $files,
                'folders' => $folders,
                'prefix' => $prefix,
                'isTruncated' => $result->get('IsTruncated', false),
                'nextContinuationToken' => $result->get('NextContinuationToken'),
                'keyCount' => $result->get('KeyCount', 0),
            ])->send();
        } catch (AwsException $e) {
            Response::fail('获取文件列表失败: ' . $e->getAwsErrorMessage())->send();
        } catch (\Exception $e) {
            Response::fail('获取文件列表失败: ' . $e->getMessage())->send();
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
