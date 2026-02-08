<?php

namespace App\Modules\VerifyCode\Controller\Http;

use App\Base\BaseController;
use Library\Facades\Redis;
use Library\Facades\Request;
use Library\Facades\Response;
use Library\Facades\Validate;

/**
 * 获取邮件验证码
 */
class Email extends BaseController
{
    private $email;

    /**
     * 参数检查
     */
    protected function check()
    {
        // 参数验证
        Validate::validate([
            'email' => ['text' => '邮箱', 'rules' => ['required']],
        ]);
        
        $this->email = trim(Request::post('email'));
    }

    /**
     * 业务处理
     */
    protected function service()
    {
        // 生成验证码唯一标识
        $verifyKey = $this->generateUUID();
        
        // 生成6位数字验证码
        $code = (string)rand(100000, 999999);
        
        // 将验证码存入Redis，有效期30分钟
        Redis::setex($verifyKey, 1800, $code);
        
        // TODO: 发送邮件
        // $this->sendEmail($this->email, '注册验证码', "<h1>您的验证码：</h1>{$code}<h3>有效期5分钟</h3>");
        
        return Response::success([
            'key' => $verifyKey
        ], '发送成功');
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
}
