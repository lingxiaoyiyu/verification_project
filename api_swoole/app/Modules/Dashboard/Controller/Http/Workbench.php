<?php
namespace App\Modules\Dashboard\Controller\Http;

use App\Base\BaseController;
use Library\Facades\DB;
use Library\Facades\Response;

/**
 * 工作台
 * POST /basic/dashboard/workbench
 */
class Workbench extends BaseController
{
    protected function check(): void
    {
        // 无需参数校验
    }

    protected function service(): void
    {
        $userId = $this->getUserId();
        $user = $this->getUser();

        // 获取用户统计数据
        $userCount = DB::table('tb_basic_sys_user')->where('is_delete', 0)->count();
        $roleCount = DB::table('tb_basic_sys_role')->where('is_delete', 0)->count();
        $menuCount = DB::table('tb_basic_sys_menu')->where('is_delete', 0)->count();
        $departmentCount = DB::table('tb_basic_sys_department')->where('is_delete', 0)->count();

        // 获取今日登录次数
        $today = date('Y-m-d');
        $todayLoginCount = DB::table('tb_basic_log_login')
            ->whereRaw("DATE(created_at) = ?", [$today])
            ->count();

        // 获取本周新增用户
        $weekStart = date('Y-m-d', strtotime('monday this week'));
        $weekNewUsers = DB::table('tb_basic_sys_user')
            ->where('created_at', '>=', $weekStart)
            ->where('is_delete', 0)
            ->count();

        Response::success([
            'user' => [
                'id' => $user->id ?? $userId,
                'username' => $user->username ?? '',
                'nickname' => $user->nickname ?? '',
                'avatar' => $user->avatar ?? '',
            ],
            'statistics' => [
                'userCount' => $userCount,
                'roleCount' => $roleCount,
                'menuCount' => $menuCount,
                'departmentCount' => $departmentCount,
                'todayLoginCount' => $todayLoginCount,
                'weekNewUsers' => $weekNewUsers,
            ],
        ])->send();
    }
}
