<?php

namespace App\Modules\Role\Controller\Http;

use App\Base\BaseController;
use Library\Facades\DB;
use Library\Facades\Request;
use Library\Facades\Response;
use Library\Facades\Validate;

/**
 * 删除角色
 */
class Remove extends BaseController
{
    private $id;
    private $updatedAt;

    /**
     * 参数检查
     */
    protected function check()
    {
        Validate::validate([
            'id' => ['text' => '角色ID', 'rules' => ['required', 'integer', 'min:1']],
        ]);
        
        $this->id = (int)Request::post('id');
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
        
        // 检查是否有用户使用该角色
        $userCount = DB::table('tb_basic_sys_user_role_relation')
            ->where('role_id', $this->id)
            ->count();
        if ($userCount > 0) {
            throw new \Exception("该角色下还有用户，无法删除");
        }
    }

    /**
     * 业务处理
     */
    protected function service()
    {
        DB::beginTransaction();
        
        try {
            $now = date('Y-m-d H:i:s');
            
            // 软删除角色
            DB::table('tb_basic_sys_role')
                ->where('id', $this->id)
                ->update([
                    'is_delete' => 1,
                    'updated_user_id' => $this->userId,
                    'updated_at' => $now
                ]);
            
            // 软删除角色菜单关联
            DB::table('tb_basic_sys_role_menu_relation')
                ->where('role_id', $this->id)
                ->update([
                    'is_delete' => 1
                ]);
            
            DB::commit();
            
            return Response::success(null, '角色删除成功');
        } catch (\Exception $e) {
            DB::rollBack();
            throw $e;
        }
    }
}
