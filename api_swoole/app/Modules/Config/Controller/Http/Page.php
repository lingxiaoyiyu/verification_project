<?php
namespace App\Modules\Config\Controller\Http;

use App\Base\BaseController;
use Library\Facades\DB;
use Library\Facades\Request;
use Library\Facades\Response;
use Library\Facades\Validate;

/**
 * 配置项分页列表
 * POST /basic/config/page
 */
class Page extends BaseController
{
    protected function check(): void
    {
        $validator = Validate::make(Request::all(), [
            'pageNo' => 'nullable|integer|min:1',
            'pageSize' => 'nullable|integer|min:1|max:100',
            'groupId' => 'nullable|integer',
            'configKey' => 'nullable|string|max:100',
            'configName' => 'nullable|string|max:100',
            'status' => 'nullable|integer|in:0,1',
        ]);

        if ($validator->fails()) {
            Response::fail($validator->errors()->first())->send();
        }
    }

    protected function service(): void
    {
        $pageNo = Request::input('pageNo', 1);
        $pageSize = Request::input('pageSize', 10);
        $groupId = Request::input('groupId');
        $configKey = Request::input('configKey');
        $configName = Request::input('configName');
        $status = Request::input('status');

        $query = DB::table('tb_basic_config')
            ->where('is_delete', 0);

        if ($groupId !== null) {
            $query->where('group_id', $groupId);
        }

        if ($configKey) {
            $query->where('config_key', 'like', "%{$configKey}%");
        }

        if ($configName) {
            $query->where('config_name', 'like', "%{$configName}%");
        }

        if ($status !== null) {
            $query->where('status', $status);
        }

        // 获取总数
        $total = $query->count();

        // 获取分页数据
        $list = $query->orderBy('sort', 'asc')
            ->orderBy('id', 'asc')
            ->offset(($pageNo - 1) * $pageSize)
            ->limit($pageSize)
            ->get();

        // 格式化返回数据
        $records = [];
        foreach ($list as $item) {
            $records[] = [
                'id' => $item->id,
                'groupId' => $item->group_id,
                'configKey' => $item->config_key,
                'configValue' => $item->config_value,
                'configName' => $item->config_name,
                'remark' => $item->remark,
                'sort' => $item->sort,
                'status' => $item->status,
                'createdAt' => $item->created_at,
                'updatedAt' => $item->updated_at,
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
