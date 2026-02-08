<?php

namespace App\Modules\Menu\Controller\Http\Update;

use App\Base\BaseController;
use Library\Facades\DB;
use Library\Facades\Request;
use Library\Facades\Response;
use Library\Facades\Validate;

/**
 * 更新菜单
 */
class Info extends BaseController
{
    private $id;
    private $parentId;
    private $type;
    private $title;
    private $name;
    private $status;
    private $path;
    private $component;
    private $identifying;
    private $icon;
    private $activeIcon;
    private $keepalive;
    private $hideInMenu;
    private $hideInTab;
    private $hideChildrenInMenu;
    private $affixTab;
    private $affixTabOrder;
    private $ignoreAccess;
    private $isIframe;
    private $iframeSrc;
    private $isLink;
    private $link;
    private $maxNumOfOpenTab;
    private $openInNewWindow;
    private $query;
    private $authority;
    private $activeId;
    private $showInAdminHome;
    private $updatedAt;

    /**
     * 参数检查
     */
    protected function check()
    {
        Validate::validate([
            'id' => ['text' => '菜单ID', 'rules' => ['required', 'integer', 'min:1']],
            'parentId' => ['text' => '父节点ID', 'rules' => ['required', 'integer', 'min:0']],
            'type' => ['text' => '菜单类型', 'rules' => ['required', 'integer']],
            'title' => ['text' => '菜单标题', 'rules' => ['required', 'max:32']],
            'status' => ['text' => '状态', 'rules' => ['required', 'integer']],
            'updatedAt' => ['text' => '更新时间', 'rules' => ['required']],
        ]);
        
        $this->id = (int)Request::post('id');
        $this->parentId = (int)Request::post('parentId');
        $this->type = (int)Request::post('type');
        $this->title = trim(Request::post('title'));
        $this->name = trim(Request::post('name', ''));
        $this->status = (int)Request::post('status');
        $this->path = trim(Request::post('path', ''));
        $this->component = trim(Request::post('component', ''));
        $this->identifying = trim(Request::post('identifying', ''));
        $this->icon = trim(Request::post('icon', ''));
        $this->activeIcon = trim(Request::post('activeIcon', ''));
        $this->keepalive = (int)Request::post('keepalive', 2);
        $this->hideInMenu = (int)Request::post('hideInMenu', 2);
        $this->hideInTab = (int)Request::post('hideInTab', 2);
        $this->hideChildrenInMenu = (int)Request::post('hideChildrenInMenu', 2);
        $this->affixTab = (int)Request::post('affixTab', 2);
        $this->affixTabOrder = (int)Request::post('affixTabOrder', 0);
        $this->ignoreAccess = (int)Request::post('ignoreAccess', 2);
        $this->isIframe = (int)Request::post('isIframe', 2);
        $this->iframeSrc = trim(Request::post('iframeSrc', ''));
        $this->isLink = (int)Request::post('isLink', 2);
        $this->link = trim(Request::post('link', ''));
        $this->maxNumOfOpenTab = (int)Request::post('maxNumOfOpenTab', 0);
        $this->openInNewWindow = (int)Request::post('openInNewWindow', 2);
        $this->query = trim(Request::post('query', ''));
        $this->authority = Request::post('authority');
        $this->activeId = Request::post('activeId');
        $this->showInAdminHome = (int)Request::post('showInAdminHome', 2);
        $this->updatedAt = Request::post('updatedAt');
        
        // 检查菜单是否存在
        $menu = DB::table('tb_basic_sys_menu')
            ->where('id', $this->id)
            ->where('is_delete', 0)
            ->first();
        if (!$menu) {
            throw new \Exception("菜单不存在");
        }
        
        // 乐观锁检查
        if ($menu['updated_at'] !== $this->updatedAt) {
            throw new \Exception("菜单信息已被其他用户修改");
        }
        
        // 系统菜单不允许禁用
        if (in_array($this->id, [1, 2]) && $this->status != 1) {
            throw new \Exception("系统菜单不允许禁用");
        }
        
        // 验证菜单类型
        if (!in_array($this->type, [1, 2, 3])) {
            throw new \Exception("无效的菜单类型");
        }
        
        // 验证父节点和层级关系
        if ($this->parentId > 0) {
            $parent = DB::table('tb_basic_sys_menu')
                ->where('id', $this->parentId)
                ->where('is_delete', 0)
                ->first();
            if (!$parent) {
                throw new \Exception("父节点不存在");
            }
            
            if ($this->type == 2 && $parent['type'] != 1) {
                throw new \Exception("页面类型的父节点必须是目录");
            }
            if ($this->type == 3 && $parent['type'] != 2) {
                throw new \Exception("权限类型的父节点必须是页面");
            }
        }
        
        // 验证路由地址唯一性（排除自身）
        if ($this->type == 2 && !empty($this->path)) {
            $existPath = DB::table('tb_basic_sys_menu')
                ->where('parent_id', $this->parentId)
                ->where('path', $this->path)
                ->where('id', '!=', $this->id)
                ->where('is_delete', 0)
                ->first();
            if ($existPath) {
                throw new \Exception("同父节点下路由地址已存在");
            }
        }
        
        // 验证菜单名称唯一性（排除自身）
        if (!empty($this->name)) {
            $existName = DB::table('tb_basic_sys_menu')
                ->where('name', $this->name)
                ->where('id', '!=', $this->id)
                ->where('is_delete', 0)
                ->first();
            if ($existName) {
                throw new \Exception("菜单名称已存在");
            }
        }
        
        // 验证外链
        if ($this->isLink == 1 && empty($this->link)) {
            throw new \Exception("外链地址不能为空");
        }
        
        // 验证iframe
        if ($this->isIframe == 1 && empty($this->iframeSrc)) {
            throw new \Exception("iframe地址不能为空");
        }
    }

    /**
     * 业务处理
     */
    protected function service()
    {
        $now = date('Y-m-d H:i:s');
        
        DB::table('tb_basic_sys_menu')
            ->where('id', $this->id)
            ->update([
                'parent_id' => $this->parentId,
                'type' => $this->type,
                'title' => $this->title,
                'name' => $this->name ?: null,
                'status' => $this->status,
                'path' => $this->path ?: null,
                'component' => $this->component ?: null,
                'identifying' => $this->identifying ?: null,
                'icon' => $this->icon ?: null,
                'active_icon' => $this->activeIcon ?: null,
                'keepalive' => $this->keepalive,
                'hide_in_menu' => $this->hideInMenu,
                'hide_in_tab' => $this->hideInTab,
                'hide_children_in_menu' => $this->hideChildrenInMenu,
                'affix_tab' => $this->affixTab,
                'affix_tab_order' => $this->affixTabOrder,
                'ignore_access' => $this->ignoreAccess,
                'is_iframe' => $this->isIframe,
                'iframe_src' => $this->iframeSrc ?: null,
                'is_link' => $this->isLink,
                'link' => $this->link ?: null,
                'max_num_of_open_tab' => $this->maxNumOfOpenTab,
                'open_in_new_window' => $this->openInNewWindow,
                'query' => $this->query ?: null,
                'authority' => is_array($this->authority) ? json_encode($this->authority) : ($this->authority ?: null),
                'active_id' => $this->activeId ?: null,
                'show_in_admin_home' => $this->showInAdminHome,
                'updated_user_id' => $this->userId,
                'updated_at' => $now
            ]);
        
        return Response::success([
            'updatedAt' => $now
        ], '菜单更新成功');
    }
}
