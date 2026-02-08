<?php
namespace App\Modules\Maintenance\Controller\Http\Log\Operation;

use App\Base\BaseController;
use Library\Facades\DB;
use Library\Facades\Request;
use Library\Facades\Response;
use Library\Facades\Validate;

/**
 * 操作日志列表-分页
 * POST /basic/maintenance/log/operation/page
 */
class Page extends BaseController
{
    protected function check(): void
    {
        $validator = Validate::make(Request::all(), [
            'pageNo' => 'nullable|integer|min:1',
            'pageSize' => 'nullable|integer|min:1|max:100',
            'startTime' => 'nullable|date',
            'endTime' => 'nullable|date',
            'userId' => 'nullable|integer',
            'module' => 'nullable|string|max:100',
            'action' => 'nullable|string|max:100',
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
        $module = Request::input('module');
        $action = Request::input('action');

        $query = DB::table('tb_basic_log_operation as o')
            ->leftJoin('tb_basic_sys_user as u', 'o.user_id', '=', 'u.id')
            ->select([
                'o.id',
                'o.module',
                'o.action',
                'o.description',
                'o.request_url',
                'o.request_method',
                'o.request_params',
                'o.response_result',
                'o.ip',
                'o.user_id',
                'o.duration',
                'o.created_at',
                'u.username',
                'u.nickname',
            ]);

        if ($startTime) {
            $query->where('o.created_at', '>=', $startTime);
        }
        if ($endTime) {
            $query->where('o.created_at', '<=', $endTime);
        }
        if ($userId !== null) {
            $query->where('o.user_id', $userId);
        }
        if ($module) {
            $query->where('o.module', 'like', "%{$module}%");
        }
        if ($action) {
            $query->where('o.action', 'like', "%{$action}%");
        }

        // 获取总数
        $total = $query->count();

        // 获取分页数据
        $list = $query->orderBy('o.created_at', 'desc')
            ->offset(($pageNo - 1) * $pageSize)
            ->limit($pageSize)
            ->get();

        // 格式化返回数据
        $records = [];
        foreach ($list as $item) {
            $records[] = [
                'id' => $item->id,
                'module' => $item->module,
                'action' => $item->action,
                'description' => $item->description,
                'requestUrl' => $item->request_url,
                'requestMethod' => $item->request_method,
                'requestParams' => $item->request_params ? json_decode($item->request_params, true) : null,
                'responseResult' => $item->response_result ? json_decode($item->response_result, true) : null,
                'ip' => $item->ip,
                'userId' => $item->user_id,
                'duration' => $item->duration,
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
