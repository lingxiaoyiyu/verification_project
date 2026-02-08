<?php
namespace App\Modules\Config\Controller\Http;

use App\Base\BaseController;
use Library\Facades\DB;
use Library\Facades\Request;
use Library\Facades\Response;
use Library\Facades\Validate;

/**
 * 查询配置项列表
 * POST /basic/config/query
 */
class Query extends BaseController
{
    protected function check(): void
    {
        $validator = Validate::make(Request::all(), [
            'groupId' => 'nullable|integer',
            'configKey' => 'nullable|string|max:100',
            'configName' => 'nullable|string|max:100',
            'status' => 'nullable|integer|in:0,1',
        ]);

        if ($validator->fails()) {
            throw new \Exception($validator->errors()->first());
        }
    }

    protected function service(): string
    {
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

        $list = $query->orderBy('sort', 'asc')
            ->orderBy('id', 'asc')
            ->get();

        // 格式化返回数据
        $result = [];
        foreach ($list as $item) {
            $result[] = [
                'id' => $item['id'],
                'groupId' => $item['group_id'],
                'configKey' => $item['config_key'],
                'configValue' => $item['config_value'],
                'configName' => $item['config_name'],
                'remark' => $item['remark'],
                'sort' => $item['sort'],
                'status' => $item['status'],
                'createdAt' => $item['created_at'],
                'updatedAt' => $item['updated_at'],
            ];
        }

        return Response::success($result);
    }
}
