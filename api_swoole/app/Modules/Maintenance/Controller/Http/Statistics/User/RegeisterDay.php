<?php
namespace App\Modules\Maintenance\Controller\Http\Statistics\User;

use App\Base\BaseController;
use Library\Facades\DB;
use Library\Facades\Request;
use Library\Facades\Response;
use Library\Facades\Validate;

/**
 * 每日用户注册统计
 * POST /maintenance/statistics/user/regeister/day
 */
class RegeisterDay extends BaseController
{
    protected function check(): void
    {
        $validator = Validate::make(Request::all(), [
            'pageNo' => 'nullable|integer|min:1',
            'pageSize' => 'nullable|integer|min:1|max:100',
            'startDate' => 'nullable|date',
            'endDate' => 'nullable|date',
        ]);

        if ($validator->fails()) {
            Response::fail($validator->errors()->first())->send();
        }
    }

    protected function service(): void
    {
        $pageNo = Request::input('pageNo', 1);
        $pageSize = Request::input('pageSize', 30);
        $startDate = Request::input('startDate', date('Y-m-d', strtotime('-30 days')));
        $endDate = Request::input('endDate', date('Y-m-d'));

        // 查询每日注册统计
        $sql = "SELECT 
                    DATE(created_at) as date,
                    COUNT(*) as count
                FROM tb_basic_sys_user 
                WHERE is_delete = 0 
                AND DATE(created_at) >= ? 
                AND DATE(created_at) <= ?
                GROUP BY DATE(created_at)
                ORDER BY date DESC";

        $stats = DB::select($sql, [$startDate, $endDate]);

        // 构建完整日期列表（包含无注册的日期）
        $dateMap = [];
        foreach ($stats as $stat) {
            $dateMap[$stat->date] = $stat->count;
        }

        $result = [];
        $currentDate = new \DateTime($startDate);
        $endDateTime = new \DateTime($endDate);

        while ($currentDate <= $endDateTime) {
            $dateStr = $currentDate->format('Y-m-d');
            $result[] = [
                'date' => $dateStr,
                'count' => $dateMap[$dateStr] ?? 0,
            ];
            $currentDate->modify('+1 day');
        }

        // 按日期倒序排列
        usort($result, function($a, $b) {
            return strcmp($b['date'], $a['date']);
        });

        // 分页
        $total = count($result);
        $records = array_slice($result, ($pageNo - 1) * $pageSize, $pageSize);

        Response::success([
            'records' => $records,
            'total' => $total,
            'pageNo' => $pageNo,
            'pageSize' => $pageSize,
            'summary' => [
                'totalCount' => array_sum(array_column($result, 'count')),
                'avgCount' => $total > 0 ? round(array_sum(array_column($result, 'count')) / $total, 2) : 0,
            ],
        ])->send();
    }
}
