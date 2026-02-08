<?php
namespace App\Modules\Dashboard\Controller\Http\Workbench;

use App\Base\BaseController;
use Library\Facades\DB;
use Library\Facades\Response;

/**
 * 快速导航
 * POST /basic/dashboard/workbench/quickNav
 */
class QuickNav extends BaseController
{
    protected function check(): void
    {
        // 无需参数校验
    }

    protected function service(): void
    {
        $userId = $this->getUserId();

        // 获取用户的角色ID列表
        $roleIds = DB::table('tb_basic_sys_user_role_relation')
            ->where('user_id', $userId)
            ->pluck('role_id')
            ->toArray();

        // 获取用户有权限的菜单
        $menuIds = [];
        if (!empty($roleIds)) {
            $menuIds = DB::table('tb_basic_sys_role_menu_relation')
                ->whereIn('role_id', $roleIds)
                ->pluck('menu_id')
                ->unique()
                ->toArray();
        }

        // 获取快速导航菜单（type=2 页面类型）
        $navList = DB::table('tb_basic_sys_menu')
            ->whereIn('id', $menuIds)
            ->where('type', 2) // PAGE类型
            ->where('status', 0)
            ->where('is_delete', 0)
            ->orderBy('sort', 'asc')
            ->limit(8)
            ->get();

        $result = [];
        foreach ($navList as $nav) {
            $result[] = [
                'id' => $nav->id,
                'title' => $nav->title,
                'icon' => $nav->icon,
                'path' => $nav->path,
            ];
        }

        Response::success($result)->send();
    }
}
