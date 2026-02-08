<?php
namespace App\Modules\Maintenance\Controller\Http\Statistics\User;

use App\Base\BaseController;
use Library\Facades\DB;
use Library\Facades\Request;
use Library\Facades\Response;
use Library\Facades\Validate;

/**
 * 每小时用户注册统计
 * POST /maintenance/statistics/user/regeister/dayHour
 */
class RegeisterDayHour extends BaseController
{
    protected function check(): void
    {
        $validator = Validate::make(Request::all(), [
            'date' => 'required|date',
        ], [
            'date.required' => '日期不能为空',
        ]);

        if ($validator->fails()) {
            Response::fail($validator->errors()->first())->send();
        }
    }

    protected function service(): void
    {
        $date = Request::input('date');

        // 查询指定日期每小时注册统计
        $sql = "SELECT 
                    HOUR(created_at) as hour,
                    COUNT(*) as count
                FROM tb_basic_sys_user 
                WHERE is_delete = 0 
                AND DATE(created_at) = ?
                GROUP BY HOUR(created_at)
                ORDER BY hour ASC";

        $stats = DB::select($sql, [$date]);

        // 构建24小时完整数据
        $hourMap = [];
        foreach ($stats as $stat) {
            $hourMap[$stat->hour] = $stat->count;
        }

        $result = [];
        for ($hour = 0; $hour < 24; $hour++) {
            $result[] = [
                'hour' => $hour,
                'hourLabel' => sprintf('%02d:00', $hour),
                'count' => $hourMap[$hour] ?? 0,
            ];
        }

        // 计算统计摘要
        $totalCount = array_sum(array_column($result, 'count'));
        $maxCount = max(array_column($result, 'count'));
        $maxHour = array_search($maxCount, array_column($result, 'count'));

        Response::success([
            'date' => $date,
            'hours' => $result,
            'summary' => [
                'totalCount' => $totalCount,
                'avgCount' => round($totalCount / 24, 2),
                'maxCount' => $maxCount,
                'maxHour' => $maxHour !== false ? sprintf('%02d:00', $maxHour) : null,
            ],
        ])->send();
    }
}
