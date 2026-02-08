<?php

namespace App\Modules\Menu\Controller\Http;

use App\Base\BaseController;
use Library\Facades\DB;
use Library\Facades\Request;
use Library\Facades\Response;
use Library\Facades\Validate;

/**
 * 获取菜单详情
 */
class Get extends BaseController
{
    private $id;

    /**
     * 参数检查
     */
    protected function check()
    {
        Validate::validate([
            'id' => ['text' => '菜单ID', 'rules' => ['required', 'integer', 'min:1']],
        ]);
        
        $this->id = (int)Request::post('id');
        
        // 检查菜单是否存在
        $menu = DB::table('tb_basic_sys_menu')
            ->where('id', $this->id)
            ->where('is_delete', 0)
            ->first();
        if (!$menu) {
            throw new \Exception("菜单不存在");
        }
    }

    /**
     * 业务处理
     */
    protected function service()
    {
        $menu = DB::table('tb_basic_sys_menu')
            ->where('id', $this->id)
            ->first();
        
        // 获取创建人信息
        $createdUserName = '';
        if ($menu['created_user_id']) {
            $creator = DB::table('tb_basic_sys_user')
                ->where('id', $menu['created_user_id'])
                ->first(['username']);
            $createdUserName = $creator ? $creator['username'] : '';
        }
        
        // 获取更新人信息
        $updatedUserName = '';
        if ($menu['updated_user_id']) {
            $updater = DB::table('tb_basic_sys_user')
                ->where('id', $menu['updated_user_id'])
                ->first(['username']);
            $updatedUserName = $updater ? $updater['username'] : '';
        }
        
        return Response::success([
            'id' => $menu['id'],
            'parentId' => $menu['parent_id'],
            'type' => $menu['type'],
            'title' => $menu['title'],
            'name' => $menu['name'],
            'status' => $menu['status'],
            'path' => $menu['path'],
            'component' => $menu['component'],
            'icon' => $menu['icon'],
            'activeIcon' => $menu['active_icon'],
            'identifying' => $menu['identifying'],
            'order' => $menu['order'],
            'keepalive' => $menu['keepalive'],
            'hideInMenu' => $menu['hide_in_menu'],
            'hideInTab' => $menu['hide_in_tab'],
            'hideChildrenInMenu' => $menu['hide_children_in_menu'],
            'affixTab' => $menu['affix_tab'],
            'affixTabOrder' => $menu['affix_tab_order'],
            'ignoreAccess' => $menu['ignore_access'],
            'isIframe' => $menu['is_iframe'],
            'iframeSrc' => $menu['iframe_src'],
            'isLink' => $menu['is_link'],
            'link' => $menu['link'],
            'maxNumOfOpenTab' => $menu['max_num_of_open_tab'],
            'openInNewWindow' => $menu['open_in_new_window'],
            'query' => $menu['query'],
            'authority' => $menu['authority'],
            'activeId' => $menu['active_id'],
            'showInAdminHome' => $menu['show_in_admin_home'],
            'permission' => $menu['permission'] ?? '',
            'createdAt' => $menu['created_at'],
            'createdUserName' => $createdUserName,
            'updatedAt' => $menu['updated_at'],
            'updatedUserName' => $updatedUserName,
            'isDelete' => $menu['is_delete']
        ]);
    }
}
