<?php
namespace App\Modules\Dashboard\Controller\Http\Workbench;

use App\Base\BaseController;
use Library\Facades\DB;
use Library\Facades\Response;

/**
 * 最新动态
 * POST /basic/dashboard/workbench/trend
 */
class Trend extends BaseController
{
    protected function check(): void
    {
        // 无需参数校验
    }

    protected function service(): void
    {
        // 获取最近的操作日志
        $logs = DB::table('tb_basic_log_operation as o')
            ->leftJoin('tb_basic_sys_user as u', 'o.user_id', '=', 'u.id')
            ->select([
                'o.id',
                'o.module',
                'o.action',
                'o.description',
                'o.created_at',
                'u.username',
                'u.nickname',
                'u.avatar',
            ])
            ->orderBy('o.created_at', 'desc')
            ->limit(10)
            ->get();

        $result = [];
        foreach ($logs as $log) {
            $result[] = [
                'id' => $log->id,
                'module' => $log->module,
                'action' => $log->action,
                'description' => $log->description,
                'createdAt' => $log->created_at,
                'user' => [
                    'username' => $log->username,
                    'nickname' => $log->nickname,
                    'avatar' => $log->avatar,
                ],
            ];
        }

        Response::success($result)->send();
    }
}
