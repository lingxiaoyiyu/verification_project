<?php

namespace App\Modules\Auth\Controller\Http\Reg;

use App\Base\BaseController;
use Library\Facades\DB;
use Library\Facades\Request;
use Library\Facades\Response;
use Library\Facades\Validate;

/**
 * 用户名密码注册
 */
class Username extends BaseController
{
    private $username;
    private $password;

    /**
     * 参数检查
     */
    protected function check()
    {
        Validate::validate([
            'username' => ['text' => '用户名', 'rules' => ['required']],
            'password' => ['text' => '密码', 'rules' => ['required']],
        ]);
        
        $this->username = trim(Request::post('username'));
        $this->password = trim(Request::post('password'));
        
        // 检查用户名是否已存在
        $exists = DB::table('tb_basic_sys_user')
            ->where('username', $this->username)
            ->first();
        
        if ($exists) {
            throw new \Exception("用户名已存在");
        }
    }

    /**
     * 业务处理
     */
    protected function service()
    {
        $inviteCode = $this->generateRandomString(6);
        
        DB::table('tb_basic_sys_user')->insert([
            'username' => $this->username,
            'password' => md5($this->password),
            'invite_code' => $inviteCode,
            'source' => 1, // 用户名注册
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
