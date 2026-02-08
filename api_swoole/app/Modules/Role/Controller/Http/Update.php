<?php

namespace App\Modules\Role\Controller\Http;

use App\Base\BaseController;
use Library\Facades\DB;
use Library\Facades\Request;
use Library\Facades\Response;
use Library\Facades\Validate;

/**
 * 更新角色
 */
class Update extends BaseController
{
    private $id;
    private $name;
    private $identifying;
    private $status;
    private $remark;
    private $menuIdList;
    private $updatedAt;

    /**
     * 参数检查
     */
    protected function check()
    {
        Validate::validate([
            'id' => ['text' => '角色ID', 'rules' => ['required', 'integer', 'min:1']],
            'name' => ['text' => '角色名称', 'rules' => ['required', 'max:64']],
            'identifying' => ['text' => '角色标识', 'rules' => ['required', 'max:32']],
            'status' => ['text' => '状态', 'rules' => ['required', 'integer']],
        ]);
        
        $this->id = (int)Request::post('id');
        $this->name = trim(Request::post('name'));
        $this->identifying = trim(Request::post('identifying'));
        $this->status = (int)Request::post('status');
        $this->remark = trim(Request::post('remark', ''));
        $this->menuIdList = Request::post('menuIdList', []);
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
        
        // 检查角色名称唯一性（排除自身）
        $existName = DB::table('tb_basic_sys_role')
            ->where('name', $this->name)
            ->where('id', '!=', $this->id)
            ->where('is_delete', 0)
            ->first();
        if ($existName) {
            throw new \Exception("角色名称已存在");
        }
        
        // 检查角色标识唯一性（排除自身）
        $existCode = DB::table('tb_basic_sys_role')
            ->where('code', $this->identifying)
            ->where('id', '!=', $this->id)
            ->where('is_delete', 0)
            ->first();
        if ($existCode) {
            throw new \Exception("角色标识已存在");
        }
        
        // 验证菜单ID有效性
        if (!empty($this->menuIdList)) {
            $menuCount = DB::table('tb_basic_sys_menu')
                ->whereIn('id', $this->menuIdList)
                ->where('is_delete', 0)
                ->count();
            if ($menuCount !== count($this->menuIdList)) {
                throw new \Exception("菜单ID无效");
            }
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
            
            // 更新角色信息
            DB::table('tb_basic_sys_role')
                ->where('id', $this->id)
                ->update([
                    'name' => $this->name,
                    'code' => $this->identifying,
                    'status' => $this->status,
                    'remark' => $this->remark ?: null,
                    'updated_user_id' => $this->userId,
                    'updated_at' => $now
                ]);
            
            // 自动添加基础菜单权限(ID 1和2)
            $newMenuIds = array_unique(array_merge([1, 2], $this->menuIdList ?: []));
            
            // 获取现有菜单关联
            $existingMenus = DB::table('tb_basic_sys_role_menu_relation')
                ->where('role_id', $this->id)
                ->where('is_delete', 0)
                ->pluck('menu_id');
            
            // 计算需要删除和添加的菜单
            $toDelete = array_diff($existingMenus, $newMenuIds);
            $toAdd = array_diff($newMenuIds, $existingMenus);
            
            // 软删除不在新列表中的菜单关联
            if (!empty($toDelete)) {
                DB::table('tb_basic_sys_role_menu_relation')
                    ->where('role_id', $this->id)
                    ->whereIn('menu_id', $toDelete)
                    ->update(['is_delete' => 1]);
            }
            
            // 添加新菜单关联
            foreach ($toAdd as $menuId) {
                // 检查是否存在已删除的记录，如果有则恢复
                $existing = DB::table('tb_basic_sys_role_menu_relation')
                    ->where('role_id', $this->id)
                    ->where('menu_id', $menuId)
                    ->first();
                
                if ($existing) {
                    DB::table('tb_basic_sys_role_menu_relation')
                        ->where('id', $existing['id'])
                        ->update(['is_delete' => 0]);
                } else {
                    DB::table('tb_basic_sys_role_menu_relation')->insert([
                        'role_id' => $this->id,
                        'menu_id' => $menuId,
                        'is_delete' => 0
                    ]);
                }
            }
            
            DB::commit();
            
            return Response::success([
                'updatedAt' => $now
            ], '角色信息修改成功');
        } catch (\Exception $e) {
            DB::rollBack();
            throw $e;
        }
    }
}
