<?php

namespace App\Modules\Auth\Controller\Http\Login;

use App\Base\BaseController;
use Library\Facades\DB;
use Library\Facades\Redis;
use Library\Facades\Request;
use Library\Facades\Response;
use Library\Facades\Validate;

/**
 * 用户名密码+验证码登录
 */
class UsernameVerCode extends BaseController
{
    private $username;
    private $password;
    private $verifyKey;
    private $verifyCode;
    private $clientFrom;

    /**
     * 参数检查
     */
    protected function check()
    {
        Validate::validate([
            'username' => ['text' => '用户名', 'rules' => ['required']],
            'password' => ['text' => '密码', 'rules' => ['required']],
            'verifyKey' => ['text' => '验证码Key', 'rules' => ['required']],
            'verifyCode' => ['text' => '验证码', 'rules' => ['required']],
        ]);
        
        $this->username = trim(Request::post('username'));
        $this->password = trim(Request::post('password'));
        $this->verifyKey = Request::post('verifyKey');
        $this->verifyCode = Request::post('verifyCode');
        $this->clientFrom = Request::post('clientFrom', 'other');
        
        // 验证图片验证码
        $storedCode = Redis::get($this->verifyKey);
        if ($storedCode === false || $storedCode === null) {
            throw new \Exception("验证码已过期");
        }
        if ($storedCode !== $this->verifyCode) {
            throw new \Exception("验证码错误");
        }
        // 验证成功后删除验证码
        Redis::del($this->verifyKey);
    }

    /**
     * 业务处理
     */
    protected function service()
    {
        // 查询用户
        $user = DB::table('tb_basic_sys_user')
            ->where('username', $this->username)
            ->first();
        
        if (!$user) {
            throw new \Exception("账号不存在");
        }
        
        if ($user['status'] == 1) {
            throw new \Exception("账号已禁用");
        }
        
        if ($user['status'] == 2) {
            throw new \Exception("账号已注销");
        }
        
        // 验证密码 (MD5加密)
        if (md5($this->password) !== $user['password']) {
            throw new \Exception("密码错误");
        }
        
        // 生成Token
        $token = $this->generateToken($user['id']);
        
        // 存储Token到Redis
        Redis::setex("token:{$token}", 7200, json_encode([
            'uid' => $user['id'],
            'username' => $user['username'],
            'clientFrom' => $this->clientFrom
        ]));
        
        return Response::success([
            'accessToken' => $token
        ], '登录成功');
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
