<?php
namespace App\Modules\Maintenance\Controller\Http\Log\Runtime;

use App\Base\BaseController;
use Library\Facades\DB;
use Library\Facades\Request;
use Library\Facades\Response;
use Library\Facades\Validate;

/**
 * 请求日志列表-分页
 * POST /basic/maintenance/log/runtime/pageRequest
 */
class PageRequest extends BaseController
{
    protected function check(): void
    {
        $validator = Validate::make(Request::all(), [
            'pageNo' => 'nullable|integer|min:1',
            'pageSize' => 'nullable|integer|min:1|max:100',
            'startTime' => 'nullable|date',
            'endTime' => 'nullable|date',
            'ip' => 'nullable|string|max:50',
            'url' => 'nullable|string|max:500',
            'method' => 'nullable|string|in:GET,POST,PUT,DELETE',
            'statusCode' => 'nullable|integer',
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
        $ip = Request::input('ip');
        $url = Request::input('url');
        $method = Request::input('method');
        $statusCode = Request::input('statusCode');

        $query = DB::table('tb_basic_log_request');

        if ($startTime) {
            $query->where('created_at', '>=', $startTime);
        }
        if ($endTime) {
            $query->where('created_at', '<=', $endTime);
        }
        if ($ip) {
            $query->where('ip', 'like', "%{$ip}%");
        }
        if ($url) {
            $query->where('url', 'like', "%{$url}%");
        }
        if ($method) {
            $query->where('method', $method);
        }
        if ($statusCode !== null) {
            $query->where('status_code', $statusCode);
        }

        // 获取总数
        $total = $query->count();

        // 获取分页数据
        $list = $query->orderBy('created_at', 'desc')
            ->offset(($pageNo - 1) * $pageSize)
            ->limit($pageSize)
            ->get();

        // 格式化返回数据
        $records = [];
        foreach ($list as $item) {
            $records[] = [
                'id' => $item->id,
                'url' => $item->url,
                'method' => $item->method,
                'ip' => $item->ip,
                'userAgent' => $item->user_agent ?? '',
                'statusCode' => $item->status_code,
                'duration' => $item->duration ?? 0,
                'createdAt' => $item->created_at,
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
