<?php

namespace App\Modules\Menu\Controller\Http\Query;

use App\Base\BaseController;
use Library\Facades\DB;
use Library\Facades\Request;
use Library\Facades\Response;
use Library\Facades\Validate;

/**
 * 获取指定菜单的可显示页面子菜单
 */
class Children extends BaseController
{
    private $parentId;

    /**
     * 参数检查
     */
    protected function check()
    {
        Validate::validate([
            'parentId' => ['text' => '父菜单ID', 'rules' => ['required', 'integer', 'min:0']],
        ]);
        
        $this->parentId = (int)Request::post('parentId');
    }

    /**
     * 业务处理
     */
    protected function service()
    {
        // 查询子菜单（只查页面类型，且不隐藏在菜单中）
        $list = DB::table('tb_basic_sys_menu')
            ->where('parent_id', $this->parentId)
            ->where('is_delete', 0)
            ->where('status', 1)
            ->where('type', 2) // 只查页面
            ->where('hide_in_menu', 2) // 不隐藏
            ->select(['id', 'title', 'name', 'path', 'icon'])
            ->orderBy('order', 'asc')
            ->get();
        
        // 格式化返回数据
        $result = [];
        foreach ($list as $item) {
            $result[] = [
                'id' => $item['id'],
                'title' => $item['title'],
                'name' => $item['name'],
                'path' => $item['path'],
                'icon' => $item['icon']
            ];
        }
        
        return Response::success([
            'list' => $result
        ]);
    }
}
