<?php

namespace App\Modules\Menu\Controller\Http;

use App\Base\BaseController;
use Library\Facades\DB;
use Library\Facades\Request;
use Library\Facades\Response;
use Library\Facades\Validate;

/**
 * 删除菜单
 */
class Remove extends BaseController
{
    private $id;
    private $updatedAt;

    /**
     * 参数检查
     */
    protected function check()
    {
        Validate::validate([
            'id' => ['text' => '菜单ID', 'rules' => ['required', 'integer', 'min:1']],
        ]);
        
        $this->id = (int)Request::post('id');
        $this->updatedAt = Request::post('updatedAt');
        
        // 系统菜单不允许删除
        if (in_array($this->id, [1, 2])) {
            throw new \Exception("系统菜单不允许删除");
        }
        
        // 检查菜单是否存在
        $menu = DB::table('tb_basic_sys_menu')
            ->where('id', $this->id)
            ->where('is_delete', 0)
            ->first();
        if (!$menu) {
            throw new \Exception("菜单不存在");
        }
        
        // 乐观锁检查
        if ($this->updatedAt && $menu['updated_at'] !== $this->updatedAt) {
            throw new \Exception("菜单信息已被其他用户修改");
        }
        
        // 检查是否有子菜单
        $childCount = DB::table('tb_basic_sys_menu')
            ->where('parent_id', $this->id)
            ->where('is_delete', 0)
            ->count();
        if ($childCount > 0) {
            throw new \Exception("请先删除子菜单");
        }
    }

    /**
     * 业务处理
     */
    protected function service()
    {
        $now = date('Y-m-d H:i:s');
        
        // 软删除菜单
        DB::table('tb_basic_sys_menu')
            ->where('id', $this->id)
            ->update([
                'is_delete' => 1,
                'updated_user_id' => $this->userId,
                'updated_at' => $now
            ]);
        
        // 删除角色菜单关联
        DB::table('tb_basic_sys_role_menu_relation')
            ->where('menu_id', $this->id)
            ->update(['is_delete' => 1]);
        
        return Response::success(null, '菜单删除成功');
    }
}
