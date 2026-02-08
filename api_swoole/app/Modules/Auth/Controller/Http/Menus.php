<?php

namespace App\Modules\Auth\Controller\Http;

use App\Base\BaseController;
use Library\Facades\DB;
use Library\Facades\Response;

/**
 * 获取用户菜单
 */
class Menus extends BaseController
{
    /**
     * 参数检查
     */
    protected function check()
    {
        if (!$this->userId) {
            throw new \Exception("请先登录");
        }
    }

    /**
     * 业务处理
     */
    protected function service()
    {
        // 获取用户角色
        $roleIds = DB::table('tb_basic_sys_user_role_relation')
            ->where('user_id', $this->userId)
            ->pluck('role_id');
        
        if (empty($roleIds)) {
            return Response::success([], '获取成功');
        }
        
        // 获取角色对应的菜单ID
        $menuIds = DB::table('tb_basic_sys_role_menu_relation')
            ->whereIn('role_id', $roleIds)
            ->pluck('menu_id');
        
        if (empty($menuIds)) {
            return Response::success([], '获取成功');
        }
        
        // 获取菜单列表
        $menus = DB::table('tb_basic_sys_menu')
            ->whereIn('id', array_unique($menuIds))
            ->where('status', 0)
            ->where('type', '<>', 3) // 排除按钮类型
            ->orderBy('sort', 'asc')
            ->get();
        
        // 构建树形结构
        $menuTree = $this->buildTree($menus);
        
        return Response::success($menuTree, '获取成功');
    }

    /**
     * 构建树形结构
     */
    private function buildTree($items, $parentId = 0)
    {
        $tree = [];
        foreach ($items as $item) {
            if ($item['parent_id'] == $parentId) {
                $children = $this->buildTree($items, $item['id']);
                if (!empty($children)) {
                    $item['children'] = $children;
                }
                $tree[] = $item;
            }
        }
        return $tree;
    }
}
