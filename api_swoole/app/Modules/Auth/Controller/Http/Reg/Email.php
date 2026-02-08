<?php

namespace App\Modules\Auth\Controller\Http\Reg;

use App\Base\BaseController;
use Library\Facades\DB;
use Library\Facades\Redis;
use Library\Facades\Request;
use Library\Facades\Response;
use Library\Facades\Validate;

/**
 * 邮箱密码+验证码注册
 */
class Email extends BaseController
{
    private $email;
    private $password;
    private $verifyKey;
    private $verifyCode;

    /**
     * 参数检查
     */
    protected function check()
    {
        Validate::validate([
            'email' => ['text' => '邮箱', 'rules' => ['required']],
            'password' => ['text' => '密码', 'rules' => ['required']],
            'verifyKey' => ['text' => '验证码Key', 'rules' => ['required']],
            'verifyCode' => ['text' => '验证码', 'rules' => ['required']],
        ]);
        
        $this->email = trim(Request::post('email'));
        $this->password = trim(Request::post('password'));
        $this->verifyKey = Request::post('verifyKey');
        $this->verifyCode = Request::post('verifyCode');
        
        // 验证邮箱验证码
        $storedCode = Redis::get($this->verifyKey);
        if ($storedCode === false || $storedCode === null) {
            throw new \Exception("验证码已过期");
        }
        if ($storedCode !== $this->verifyCode) {
            throw new \Exception("验证码错误");
        }
        
        // 检查邮箱是否已存在
        $exists = DB::table('tb_basic_sys_user')
            ->where('email', $this->email)
            ->first();
        
        if ($exists) {
            throw new \Exception("邮箱已被注册");
        }
        
        // 验证成功后删除验证码
        Redis::del($this->verifyKey);
    }

    /**
     * 业务处理
     */
    protected function service()
    {
        $username = $this->generateRandomString(8);
        $inviteCode = $this->generateRandomString(6);
        
        DB::table('tb_basic_sys_user')->insert([
            'username' => $username,
            'email' => $this->email,
            'password' => md5($this->password),
            'invite_code' => $inviteCode,
            'source' => 3, // 邮箱注册
            'status' => 0,
            'created_at' => date('Y-m-d H:i:s'),
            'updated_at' => date('Y-m-d H:i:s')
        ]);
        
        return Response::success(null, '注册成功');
    }

    /**
     * 生成随机字符串
     */
    private function generateRandomString($length)
    {
        $chars = 'abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789';
        $str = '';
        for ($i = 0; $i < $length; $i++) {
            $str .= $chars[mt_rand(0, strlen($chars) - 1)];
        }
        return $str;
    }
}
