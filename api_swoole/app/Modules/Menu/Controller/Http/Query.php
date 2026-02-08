<?php

namespace App\Modules\Menu\Controller\Http;

use App\Base\BaseController;
use Library\Facades\DB;
use Library\Facades\Request;
use Library\Facades\Response;

/**
 * 查询菜单列表
 */
class Query extends BaseController
{
    private $name;
    private $title;
    private $type;
    private $status;
    private $isShow;
    private $keepalive;
    private $isLink;
    private $hideChildren;

    /**
     * 参数检查
     */
    protected function check()
    {
        $this->name = trim(Request::post('name', ''));
        $this->title = trim(Request::post('title', ''));
        $this->type = Request::post('type');
        $this->status = Request::post('status');
        $this->isShow = Request::post('isShow');
        $this->keepalive = Request::post('keepalive');
        $this->isLink = Request::post('isLink');
        $this->hideChildren = Request::post('hideChildren');
    }

    /**
     * 业务处理
     */
    protected function service()
    {
        $query = DB::table('tb_basic_sys_menu')
            ->where('is_delete', 0);
        
        // 条件过滤
        if (!empty($this->name)) {
            $query->where('name', 'like', "%{$this->name}%");
        }
        if (!empty($this->title)) {
            $query->where('title', 'like', "%{$this->title}%");
        }
        if ($this->type !== null && $this->type !== '') {
            $query->where('type', $this->type);
        }
        if ($this->status !== null && $this->status !== '') {
            $query->where('status', $this->status);
        }
        if ($this->isShow !== null && $this->isShow !== '') {
            // isShow=1表示显示, 对应hideInMenu=2
            // isShow=2表示隐藏, 对应hideInMenu=1
            $hideInMenu = $this->isShow == 1 ? 2 : 1;
            $query->where('hide_in_menu', $hideInMenu);
        }
        if ($this->keepalive !== null && $this->keepalive !== '') {
            $query->where('keepalive', $this->keepalive);
        }
        if ($this->isLink !== null && $this->isLink !== '') {
            $query->where('is_link', $this->isLink);
        }
        if ($this->hideChildren !== null && $this->hideChildren !== '') {
            $query->where('hide_children_in_menu', $this->hideChildren);
        }
        
        // 查询列表
        $list = $query->orderBy('order', 'asc')->get();
        
        // 获取创建人和更新人信息
        $userIds = array_unique(array_merge(
            array_filter(array_column($list, 'created_user_id')),
            array_filter(array_column($list, 'updated_user_id'))
        ));
        
        $users = [];
        if (!empty($userIds)) {
            $userList = DB::table('tb_basic_sys_user')
                ->whereIn('id', $userIds)
                ->get(['id', 'username']);
            foreach ($userList as $user) {
                $users[$user['id']] = $user['username'];
            }
        }
        
        // 格式化返回数据
        $result = [];
        foreach ($list as $item) {
            $result[] = [
                'id' => $item['id'],
                'parentId' => $item['parent_id'],
                'type' => $item['type'],
                'title' => $item['title'],
                'name' => $item['name'],
                'path' => $item['path'],
                'component' => $item['component'],
                'icon' => $item['icon'],
                'activeIcon' => $item['active_icon'],
                'identifying' => $item['identifying'],
                'order' => $item['order'],
                'status' => $item['status'],
                'keepalive' => $item['keepalive'],
                'hideInMenu' => $item['hide_in_menu'],
                'hideInTab' => $item['hide_in_tab'],
                'hideChildrenInMenu' => $item['hide_children_in_menu'],
                'affixTab' => $item['affix_tab'],
                'affixTabOrder' => $item['affix_tab_order'],
                'ignoreAccess' => $item['ignore_access'],
                'isIframe' => $item['is_iframe'],
                'iframeSrc' => $item['iframe_src'],
                'isLink' => $item['is_link'],
                'link' => $item['link'],
                'maxNumOfOpenTab' => $item['max_num_of_open_tab'],
                'openInNewWindow' => $item['open_in_new_window'],
                'query' => $item['query'],
                'authority' => $item['authority'],
                'activeId' => $item['active_id'],
                'showInAdminHome' => $item['show_in_admin_home'],
                'createdAt' => $item['created_at'],
                'createdUserName' => $users[$item['created_user_id']] ?? '',
                'updatedAt' => $item['updated_at'],
                'updatedUserName' => $users[$item['updated_user_id']] ?? ''
            ];
        }
        
        return Response::success([
            'list' => $result
        ]);
    }
}
