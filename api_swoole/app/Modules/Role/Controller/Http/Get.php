<?php

namespace App\Modules\Role\Controller\Http;

use App\Base\BaseController;
use Library\Facades\DB;
use Library\Facades\Request;
use Library\Facades\Response;
use Library\Facades\Validate;

/**
 * 获取角色详情
 */
class Get extends BaseController
{
    private $id;

    /**
     * 参数检查
     */
    protected function check()
    {
        Validate::validate([
            'id' => ['text' => '角色ID', 'rules' => ['required', 'integer', 'min:1']],
        ]);
        
        $this->id = (int)Request::post('id');
        
        // 检查角色是否存在
        $role = DB::table('tb_basic_sys_role')
            ->where('id', $this->id)
            ->where('is_delete', 0)
            ->first();
        if (!$role) {
            throw new \Exception("角色不存在");
        }
    }

    /**
     * 业务处理
     */
    protected function service()
    {
        $role = DB::table('tb_basic_sys_role')
            ->where('id', $this->id)
            ->first();
        
        // 获取创建人信息
        $createdUserName = '';
        if ($role['created_user_id']) {
            $creator = DB::table('tb_basic_sys_user')
                ->where('id', $role['created_user_id'])
                ->first(['username']);
            $createdUserName = $creator ? $creator['username'] : '';
        }
        
        // 获取更新人信息
        $updatedUserName = '';
        if ($role['updated_user_id']) {
            $updater = DB::table('tb_basic_sys_user')
                ->where('id', $role['updated_user_id'])
                ->first(['username']);
            $updatedUserName = $updater ? $updater['username'] : '';
        }
        
        // 获取菜单列表
        $menuIdList = [];
        $menuNames = [];
        
        // 特殊处理ID为1的系统管理员角色
        if ($this->id == 1) {
            // 返回所有启用的菜单
            $menus = DB::table('tb_basic_sys_menu')
                ->where('is_delete', 0)
                ->where('status', 1)
                ->get(['id', 'name']);
            
            foreach ($menus as $menu) {
                $menuIdList[] = $menu['id'];
                $menuNames[] = $menu['name'];
            }
        } else {
            // 查询角色关联的菜单
            $roleMenus = DB::table('tb_basic_sys_role_menu_relation as rm')
                ->join('tb_basic_sys_menu as m', 'rm.menu_id', '=', 'm.id')
                ->where('rm.role_id', $this->id)
                ->where('rm.is_delete', 0)
                ->where('m.is_delete', 0)
                ->get(['m.id', 'm.name']);
            
            foreach ($roleMenus as $menu) {
                $menuIdList[] = $menu['id'];
                $menuNames[] = $menu['name'];
            }
        }
        
        return Response::success([
            'id' => $role['id'],
            'name' => $role['name'],
            'identifying' => $role['code'],
            'remark' => $role['remark'],
            'status' => $role['status'],
            'createdAt' => $role['created_at'],
            'createdUserName' => $createdUserName,
            'updatedAt' => $role['updated_at'],
            'updatedUserName' => $updatedUserName,
            'menuIdList' => $menuIdList,
            'menuNames' => $menuNames
        ]);
    }
}
