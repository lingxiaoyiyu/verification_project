<?php

namespace App\Modules\Role\Controller\Http\Update;

use App\Base\BaseController;
use Library\Facades\DB;
use Library\Facades\Request;
use Library\Facades\Response;
use Library\Facades\Validate;

/**
 * 更新角色状态
 */
class Status extends BaseController
{
    private $id;
    private $status;
    private $updatedAt;

    /**
     * 参数检查
     */
    protected function check()
    {
        Validate::validate([
            'id' => ['text' => '角色ID', 'rules' => ['required', 'integer', 'min:1']],
            'status' => ['text' => '状态', 'rules' => ['required', 'integer']],
        ]);
        
        $this->id = (int)Request::post('id');
        $this->status = (int)Request::post('status');
        $this->updatedAt = Request::post('updatedAt');
        
        // 检查角色是否存在
        $role = DB::table('tb_basic_sys_role')
            ->where('id', $this->id)
            ->where('is_delete', 0)
            ->first();
        if (!$role) {
            throw new \Exception("角色不存在");
        }
        
        // 乐观锁检查
        if ($this->updatedAt && $role['updated_at'] !== $this->updatedAt) {
            throw new \Exception("数据已被修改，请刷新后重试");
        }
    }

    /**
     * 业务处理
     */
    protected function service()
    {
        $now = date('Y-m-d H:i:s');
        
        DB::table('tb_basic_sys_role')
            ->where('id', $this->id)
            ->update([
                'status' => $this->status,
                'updated_user_id' => $this->userId,
                'updated_at' => $now
            ]);
        
        return Response::success([
            'updatedAt' => $now
        ], '角色状态更新成功');
    }
}
