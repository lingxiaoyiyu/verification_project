<?php

namespace App\Modules\User\Controller\Http\Update\Password;

use App\Base\BaseController;
use Library\Facades\DB;
use Library\Facades\Request;
use Library\Facades\Response;
use Library\Facades\Validate;

/**
 * 管理员修改指定用户密码
 */
class ById extends BaseController
{
    private $id;
    private $password;
    private $updatedAt;

    /**
     * 参数检查
     */
    protected function check()
    {
        Validate::validate([
            'id' => ['text' => '用户ID', 'rules' => ['required', 'integer', 'min:1']],
            'password' => ['text' => '密码', 'rules' => ['required', 'min:6', 'max:20']],
        ]);
        
        $this->id = (int)Request::post('id');
        $this->password = Request::post('password');
        $this->updatedAt = Request::post('updatedAt');
        
        // 检查用户是否存在
        $user = DB::table('tb_basic_sys_user')->where('id', $this->id)->first();
        if (!$user) {
            throw new \Exception("用户不存在");
        }
        
        // 乐观锁检查
        if ($this->updatedAt && $user['updated_at'] !== $this->updatedAt) {
            throw new \Exception("数据已被修改，请刷新后重试");
        }
    }

    /**
     * 业务处理
     */
    protected function service()
    {
        $now = date('Y-m-d H:i:s');
        
        DB::table('tb_basic_sys_user')
            ->where('id', $this->id)
            ->update([
                'password' => md5($this->password),
                'updated_user_id' => $this->userId,
                'updated_at' => $now
            ]);
        
        return Response::success([
            'updatedAt' => $now
        ]);
    }
}
