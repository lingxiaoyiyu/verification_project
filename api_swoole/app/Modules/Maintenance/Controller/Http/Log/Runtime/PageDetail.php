<?php
namespace App\Modules\Maintenance\Controller\Http\Log\Runtime;

use App\Base\BaseController;
use Library\Facades\DB;
use Library\Facades\Request;
use Library\Facades\Response;
use Library\Facades\Validate;

/**
 * 访问日志详情列表-分页
 * POST /basic/maintenance/log/runtime/pageDetail
 */
class PageDetail extends BaseController
{
    protected function check(): void
    {
        $validator = Validate::make(Request::all(), [
            'pageNo' => 'nullable|integer|min:1',
            'pageSize' => 'nullable|integer|min:1|max:100',
            'startTime' => 'nullable|date',
            'endTime' => 'nullable|date',
            'userId' => 'nullable|integer',
            'level' => 'nullable|string|in:INFO,WARN,ERROR,DEBUG',
        ]);

        if ($validator->fails()) {
            Response::fail($validator->errors()->first())->send();
        }
    }

    protected function service(): void
    {
        $pageNo = Request::input('pageNo', 1);
        $pageSize = Request::input('pageSize', 20);
        $startTime = Request::input('startTime');
        $endTime = Request::input('endTime');
        $userId = Request::input('userId');
        $level = Request::input('level');

        $query = DB::table('tb_basic_log_runtime as r')
            ->leftJoin('tb_basic_sys_user as u', 'r.user_id', '=', 'u.id')
            ->select([
                'r.id',
                'r.level',
                'r.message',
                'r.context',
                'r.url',
                'r.ip',
                'r.user_id',
                'r.created_at',
                'u.username',
                'u.nickname',
            ]);

        if ($startTime) {
            $query->where('r.created_at', '>=', $startTime);
        }
        if ($endTime) {
            $query->where('r.created_at', '<=', $endTime);
        }
        if ($userId !== null) {
            $query->where('r.user_id', $userId);
        }
        if ($level) {
            $query->where('r.level', $level);
        }

        // 获取总数
        $total = $query->count();

        // 获取分页数据
        $list = $query->orderBy('r.created_at', 'desc')
            ->offset(($pageNo - 1) * $pageSize)
            ->limit($pageSize)
            ->get();

        // 格式化返回数据
        $records = [];
        foreach ($list as $item) {
            $records[] = [
                'id' => $item->id,
                'level' => $item->level,
                'message' => $item->message,
                'context' => $item->context ? json_decode($item->context, true) : null,
                'url' => $item->url,
                'ip' => $item->ip,
                'userId' => $item->user_id,
                'createdAt' => $item->created_at,
                'user' => $item->user_id ? [
                    'username' => $item->username,
                    'nickname' => $item->nickname,
                ] : null,
            ];
        }

        Response::success([
            'records' => $records,
            'total' => $total,
            'pageNo' => $pageNo,
            'pageSize' => $pageSize,
        ])->send();
    }
}
