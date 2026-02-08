<?php
namespace App\Modules\Config\Controller\Http\Group;

use App\Base\BaseController;
use Library\Facades\DB;
use Library\Facades\Response;

/**
 * 查询配置组列表
 * POST /basic/config/group/query
 */
class Query extends BaseController
{
    protected function check(): void
    {
        // 无需参数校验
    }

    protected function service(): void
    {
        $list = DB::table('tb_basic_config_group')
            ->where('is_delete', 0)
            ->orderBy('sort', 'asc')
            ->orderBy('id', 'asc')
            ->get();

        // 格式化返回数据
        $result = [];
        foreach ($list as $item) {
            $result[] = [
                'id' => $item->id,
                'groupKey' => $item->group_key,
                'groupName' => $item->group_name,
                'remark' => $item->remark,
                'sort' => $item->sort,
                'createdAt' => $item->created_at,
                'updatedAt' => $item->updated_at,
            ];
        }

        Response::success($result)->send();
    }
}
