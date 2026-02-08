<?php

namespace App\Modules\Auth\Controller\Http\Login;

use App\Base\BaseController;
use Library\Facades\DB;
use Library\Facades\Redis;
use Library\Facades\Request;
use Library\Facades\Response;
use Library\Facades\Validate;

/**
 * 手机验证码登录
 */
class Phone extends BaseController
{
    private $phoneNumber;
    private $verifyCode;
    private $clientFrom;

    /**
     * 参数检查
     */
    protected function check()
    {
        Validate::validate([
            'phoneNumber' => ['text' => '手机号', 'rules' => ['required']],
            'verifyCode' => ['text' => '验证码', 'rules' => ['required']],
        ]);
        
        $this->phoneNumber = trim(Request::post('phoneNumber'));
        $this->verifyCode = Request::post('verifyCode');
        $this->clientFrom = Request::post('clientFrom', 'other');
        
        // 验证手机验证码
        $storedCode = Redis::get("phone_code_{$this->phoneNumber}");
        if ($storedCode === false || $storedCode === null) {
            throw new \Exception("验证码已过期");
        }
        if ($storedCode !== $this->verifyCode) {
            throw new \Exception("验证码错误");
        }
        // 验证成功后删除验证码
        Redis::del("phone_code_{$this->phoneNumber}");
    }

    /**
     * 业务处理
     */
    protected function service()
    {
        // 查询用户
        $user = DB::table('tb_basic_sys_user')
            ->where('phone', $this->phoneNumber)
            ->first();
        
        // 用户不存在则自动注册
        if (!$user) {
            $userId = $this->registerUser();
            $user = DB::table('tb_basic_sys_user')
                ->where('id', $userId)
                ->first();
        }
        
        if ($user['status'] == 1) {
            throw new \Exception("账号已禁用");
        }
        
        if ($user['status'] == 2) {
            throw new \Exception("账号已注销");
        }
        
        // 生成Token
        $token = $this->generateToken($user['id']);
        
        // 存储Token到Redis
        Redis::setex("token:{$token}", 7200, json_encode([
            'uid' => $user['id'],
            'username' => $user['username'],
            'phone' => $user['phone'],
            'clientFrom' => $this->clientFrom
        ]));
        
        return Response::success([
            'accessToken' => $token
        ], '登录成功');
    }

    /**
     * 自动注册用户
     */
    private function registerUser()
    {
        $username = $this->generateRandomString(8);
        $password = md5('123456');
        $inviteCode = $this->generateRandomString(6);
        
        $userId = DB::table('tb_basic_sys_user')->insertGetId([
            'username' => $username,
            'password' => $password,
            'phone' => $this->phoneNumber,
            'invite_code' => $inviteCode,
            'source' => 2, // 手机注册
            'status' => 0,
            'created_at' => date('Y-m-d H:i:s'),
            'updated_at' => date('Y-m-d H:i:s')
        ]);
        
        return $userId;
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

    /**
     * 生成Token
     */
    private function generateToken($userId)
    {
        $payload = [
            'uid' => $userId,
            'iat' => time(),
            'exp' => time() + 7200
        ];
        return base64_encode(json_encode($payload)) . '.' . md5(json_encode($payload) . 'secret_key');
    }
}
