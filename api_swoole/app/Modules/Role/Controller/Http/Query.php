<?php

namespace App\Modules\Role\Controller\Http;

use App\Base\BaseController;
use Library\Facades\DB;
use Library\Facades\Response;

/**
 * 角色列表（不分页）
 */
class Query extends BaseController
{
    /**
     * 参数检查
     */
    protected function check()
    {
        // 无参数
    }

    /**
     * 业务处理
     */
    protected function service()
    {
        // 查询所有启用的角色
        $list = DB::table('tb_basic_sys_role')
            ->where('is_delete', 0)
            ->where('status', 1) // 启用状态
            ->select(['id', 'name', 'code', 'remark', 'status', 'updated_at'])
            ->orderBy('id', 'asc')
            ->get();
        
        // 格式化返回数据
        $result = [];
        foreach ($list as $item) {
            $result[] = [
                'id' => $item['id'],
                'name' => $item['name'],
                'identifying' => $item['code'],
                'remark' => $item['remark'],
                'status' => $item['status'],
                'updatedAt' => $item['updated_at']
            ];
        }
        
        return Response::success([
            'list' => $result
        ]);
    }
}
