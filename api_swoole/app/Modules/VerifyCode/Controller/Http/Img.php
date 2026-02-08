<?php

namespace App\Modules\VerifyCode\Controller\Http;

use App\Base\BaseController;
use Library\Facades\Redis;
use Library\Facades\Response;

/**
 * 获取图片验证码
 */
class Img extends BaseController
{
    /**
     * 参数检查
     */
    protected function check()
    {
        // 无需参数
    }

    /**
     * 业务处理
     */
    protected function service()
    {
        // 生成验证码唯一标识
        $verifyKey = $this->generateUUID();
        
        // 生成验证码（4位数字）
        $code = (string)rand(1000, 9999);
        
        // 生成验证码图片
        $imageData = $this->generateCaptchaImage($code);
        
        // 将验证码存入Redis，有效期2分钟
        Redis::setex($verifyKey, 120, $code);
        
        return Response::success([
            'img' => 'data:image/png;base64,' . $imageData,
            'key' => $verifyKey
        ]);
    }

    /**
     * 生成UUID
     */
    private function generateUUID(): string
    {
        return sprintf('%04x%04x%04x%04x%04x%04x%04x%04x',
            mt_rand(0, 0xffff), mt_rand(0, 0xffff),
            mt_rand(0, 0xffff),
            mt_rand(0, 0x0fff) | 0x4000,
            mt_rand(0, 0x3fff) | 0x8000,
            mt_rand(0, 0xffff), mt_rand(0, 0xffff), mt_rand(0, 0xffff)
        );
    }

    /**
     * 生成验证码图片
     */
    private function generateCaptchaImage(string $code): string
    {
        $width = 120;
        $height = 40;
        
        // 创建图像
        $image = imagecreatetruecolor($width, $height);
        
        // 设置背景色
        $bgColor = imagecolorallocate($image, 255, 255, 255);
        imagefill($image, 0, 0, $bgColor);
        
        // 添加干扰线
        for ($i = 0; $i < 5; $i++) {
            $lineColor = imagecolorallocate($image, rand(100, 200), rand(100, 200), rand(100, 200));
            imageline($image, rand(0, $width), rand(0, $height), rand(0, $width), rand(0, $height), $lineColor);
        }
        
        // 添加干扰点
        for ($i = 0; $i < 50; $i++) {
            $pointColor = imagecolorallocate($image, rand(100, 200), rand(100, 200), rand(100, 200));
            imagesetpixel($image, rand(0, $width), rand(0, $height), $pointColor);
        }
        
        // 绘制验证码文字
        $textColor = imagecolorallocate($image, rand(0, 100), rand(0, 100), rand(0, 100));
        $fontSize = 5;
        $x = ($width - strlen($code) * imagefontwidth($fontSize)) / 2;
        $y = ($height - imagefontheight($fontSize)) / 2;
        imagestring($image, $fontSize, $x, $y, $code, $textColor);
        
        // 输出图像到缓冲区
        ob_start();
        imagepng($image);
        $imageData = ob_get_clean();
        
        // 销毁图像资源
        imagedestroy($image);
        
        return base64_encode($imageData);
    }
}
