<?php

namespace App\Modules\Auth\Controller\Http\ResetPwd;

use App\Base\BaseController;
use Library\Facades\DB;
use Library\Facades\Redis;
use Library\Facades\Request;
use Library\Facades\Response;
use Library\Facades\Validate;

/**
 * 邮箱验证码重置密码
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
            'password' => ['text' => '新密码', 'rules' => ['required']],
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
            ->where('email', $this->email)
            ->first();
        
        if (!$user) {
            throw new \Exception("邮箱未注册");
        }
        
        // 更新密码
        DB::table('tb_basic_sys_user')
            ->where('id', $user['id'])
            ->update([
                'password' => md5($this->password),
                'updated_at' => date('Y-m-d H:i:s')
            ]);
        
        return Response::success(null, '密码重置成功');
    }
}
