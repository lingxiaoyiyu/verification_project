<?php

namespace App\Modules\Menu\Controller\Http\Query;

use App\Base\BaseController;
use Library\Facades\DB;
use Library\Facades\Response;

/**
 * 获取树形结构菜单下拉列表
 */
class SelectTree extends BaseController
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
        // 查询所有启用的目录和页面菜单
        $list = DB::table('tb_basic_sys_menu')
            ->where('is_delete', 0)
            ->where('status', 1)
            ->whereIn('type', [1, 2]) // 只查目录和页面
            ->select(['id', 'parent_id', 'title', 'type'])
            ->orderBy('order', 'asc')
            ->get();
        
        // 构建树形结构
        $tree = $this->buildTree($list, 0);
        
        return Response::success([
            'list' => $tree
        ]);
    }

    /**
     * 构建树形结构
     */
    private function buildTree($list, $parentId)
    {
        $tree = [];
        foreach ($list as $item) {
            if ($item['parent_id'] == $parentId) {
                $node = [
                    'id' => $item['id'],
                    'title' => $item['title'],
                    'type' => $item['type'],
                    'children' => $this->buildTree($list, $item['id'])
                ];
                $tree[] = $node;
            }
        }
        return $tree;
    }
}
