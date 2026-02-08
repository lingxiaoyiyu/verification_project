<?php

namespace App\Modules\User\Controller\Http\Update\Password;

use App\Base\BaseController;
use Library\Facades\DB;
use Library\Facades\Request;
use Library\Facades\Response;
use Library\Facades\Validate;

/**
 * 用户修改自己的密码
 */
class Current extends BaseController
{
    private $oldPassword;
    private $newPassword;

    /**
     * 参数检查
     */
    protected function check()
    {
        if (!$this->userId) {
            throw new \Exception("未登录");
        }
        
        Validate::validate([
            'oldPassword' => ['text' => '旧密码', 'rules' => ['required']],
            'newPassword' => ['text' => '新密码', 'rules' => ['required', 'min:6', 'max:20']],
        ]);
        
        $this->oldPassword = Request::post('oldPassword');
        $this->newPassword = Request::post('newPassword');
        
        // 获取当前用户
        $user = DB::table('tb_basic_sys_user')->where('id', $this->userId)->first();
        if (!$user) {
            throw new \Exception("用户不存在");
        }
        
        // 验证旧密码
        if (md5($this->oldPassword) !== $user['password']) {
            throw new \Exception("旧密码错误");
        }
    }

    /**
     * 业务处理
     */
    protected function service()
    {
        $now = date('Y-m-d H:i:s');
        
        DB::table('tb_basic_sys_user')
            ->where('id', $this->userId)
            ->update([
                'password' => md5($this->newPassword),
                'updated_user_id' => $this->userId,
                'updated_at' => $now
            ]);
        
        return Response::success([
            'updatedAt' => $now
        ], '密码修改成功');
    }
}
