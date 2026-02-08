<?php

namespace App\Modules\Upload\Controller\Http;

use App\Base\BaseController;
use Library\Facades\Request;
use Library\Facades\Response;

/**
 * 上传图片
 */
class Img extends BaseController
{
    private $file;
    
    // 允许的图片扩展名
    private const ALLOWED_EXTENSIONS = ['jpg', 'jpeg', 'png', 'gif', 'bmp', 'svg'];
    
    // 最大文件大小 20MB
    private const MAX_FILE_SIZE = 20971520;

    /**
     * 参数检查
     */
    protected function check()
    {
        $this->file = Request::file('file');
        
        if (empty($this->file) || $this->file['error'] !== UPLOAD_ERR_OK) {
            throw new \Exception("文件为空");
        }
        
        // 获取文件扩展名
        $extension = $this->getExtension($this->file['name']);
        
        // 检查扩展名
        if (!in_array(strtolower($extension), self::ALLOWED_EXTENSIONS)) {
            throw new \Exception("文件类型不正确，只允许上传图片");
        }
        
        // 检查MIME类型
        $finfo = finfo_open(FILEINFO_MIME_TYPE);
        $mimeType = finfo_file($finfo, $this->file['tmp_name']);
        finfo_close($finfo);
        
        if (strpos($mimeType, 'image/') !== 0) {
            throw new \Exception("文件类型不正确，只允许上传图片");
        }
        
        // 检查文件大小
        if ($this->file['size'] > self::MAX_FILE_SIZE) {
            throw new \Exception("文件大小超过20MB限制");
        }
    }

    /**
     * 业务处理
     */
    protected function service()
    {
        return $this->uploadFile($this->file);
    }

    /**
     * 上传文件到本地
     */
    private function uploadFile($file)
    {
        // 生成唯一文件名
        $extension = $this->getExtension($file['name']);
        $uniqueFileName = $this->generateUUID() . '.' . $extension;
        
        // 按日期创建目录
        $currentDate = date('Ymd');
        $uploadDir = ROOT_PATH . '/storage/uploads/' . $currentDate;
        
        if (!is_dir($uploadDir)) {
            mkdir($uploadDir, 0755, true);
        }
        
        $filePath = $uploadDir . '/' . $uniqueFileName;
        
        // 移动上传文件
        if (!move_uploaded_file($file['tmp_name'], $filePath)) {
            throw new \Exception("上传失败");
        }
        
        // 构建访问URL
        $url = '/storage/uploads/' . $currentDate . '/' . $uniqueFileName;
        
        return Response::success([
            'url' => $url,
            'fileName' => $uniqueFileName,
            'fileInfoId' => $url,
            'fullUrl' => $url
        ], '上传成功');
    }

    /**
     * 获取文件扩展名
     */
    private function getExtension($fileName)
    {
        $dotIndex = strrpos($fileName, '.');
        if ($dotIndex !== false) {
            return substr($fileName, $dotIndex + 1);
        }
        return '';
    }

    /**
     * 生成UUID
     */
    private function generateUUID()
    {
        return sprintf('%04x%04x%04x%04x%04x%04x%04x%04x',
            mt_rand(0, 0xffff), mt_rand(0, 0xffff),
            mt_rand(0, 0xffff),
            mt_rand(0, 0x0fff) | 0x4000,
            mt_rand(0, 0x3fff) | 0x8000,
            mt_rand(0, 0xffff), mt_rand(0, 0xffff), mt_rand(0, 0xffff)
        );
    }
}
