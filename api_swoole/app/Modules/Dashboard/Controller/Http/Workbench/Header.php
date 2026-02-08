<?php
namespace App\Modules\Dashboard\Controller\Http\Workbench;

use App\Base\BaseController;
use Library\Facades\DB;
use Library\Facades\Response;

/**
 * 工作台头部
 * POST /basic/dashboard/workbench/header
 */
class Header extends BaseController
{
    protected function check(): void
    {
        // 无需参数校验
    }

    protected function service(): void
    {
        $userId = $this->getUserId();
        $user = $this->getUser();

        // 获取当前时间段的问候语
        $hour = (int)date('H');
        if ($hour >= 6 && $hour < 12) {
            $greeting = '早上好';
        } elseif ($hour >= 12 && $hour < 14) {
            $greeting = '中午好';
        } elseif ($hour >= 14 && $hour < 18) {
            $greeting = '下午好';
        } else {
            $greeting = '晚上好';
        }

        // 获取用户最后登录时间
        $lastLogin = DB::table('tb_basic_log_login')
            ->where('user_id', $userId)
            ->orderBy('created_at', 'desc')
            ->first();

        Response::success([
            'greeting' => $greeting,
            'user' => [
                'id' => $user->id ?? $userId,
                'username' => $user->username ?? '',
                'nickname' => $user->nickname ?? '',
                'avatar' => $user->avatar ?? '',
            ],
            'lastLoginTime' => $lastLogin ? $lastLogin->created_at : null,
            'currentTime' => date('Y-m-d H:i:s'),
        ])->send();
    }
}
