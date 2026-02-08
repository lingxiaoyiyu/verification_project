<?php

namespace App\Modules\Menu\Controller\Http\Update;

use App\Base\BaseController;
use Library\Facades\DB;
use Library\Facades\Request;
use Library\Facades\Response;
use Library\Facades\Validate;

/**
 * 更新菜单排序
 */
class Sort extends BaseController
{
    private $id;
    private $order;
    private $updatedAt;

    /**
     * 参数检查
     */
    protected function check()
    {
        Validate::validate([
            'id' => ['text' => '菜单ID', 'rules' => ['required', 'integer', 'min:1']],
            'order' => ['text' => '排序值', 'rules' => ['required', 'integer', 'min:0']],
        ]);
        
        $this->id = (int)Request::post('id');
        $this->order = (int)Request::post('order');
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
        if ($this->updatedAt && $menu['updated_at'] !== $this->updatedAt) {
            throw new \Exception("菜单信息已被其他用户修改");
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
                'order' => $this->order,
                'updated_user_id' => $this->userId,
                'updated_at' => $now
            ]);
        
        return Response::success([
            'updatedAt' => $now
        ], '排序更新成功');
    }
}
