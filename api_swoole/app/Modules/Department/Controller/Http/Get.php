<?php

namespace App\Modules\Department\Controller\Http;

use App\Base\BaseController;
use Library\Facades\DB;
use Library\Facades\Request;
use Library\Facades\Response;
use Library\Facades\Validate;

/**
 * 获取部门详情
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
            'id' => ['text' => '部门ID', 'rules' => ['required', 'integer', 'min:1']],
        ]);
        
        $this->id = (int)Request::post('id');
    }

    /**
     * 业务处理
     */
    protected function service()
    {
        $dept = DB::table('tb_basic_sys_department')
            ->where('id', $this->id)
            ->where('is_delete', 0)
            ->first();
        
        if (!$dept) {
            throw new \Exception("部门不存在");
        }
        
        // 获取创建人信息
        $createdUserName = '';
        if ($dept['created_user_id']) {
            $creator = DB::table('tb_basic_sys_user')
                ->where('id', $dept['created_user_id'])
                ->first(['username']);
            $createdUserName = $creator ? $creator['username'] : '';
        }
        
        // 获取更新人信息
        $updatedUserName = '';
        if ($dept['updated_user_id']) {
            $updater = DB::table('tb_basic_sys_user')
                ->where('id', $dept['updated_user_id'])
                ->first(['username']);
            $updatedUserName = $updater ? $updater['username'] : '';
        }
        
        return Response::success([
            'id' => $dept['id'],
            'parentId' => $dept['parent_id'],
            'name' => $dept['name'],
            'status' => $dept['status'],
            'remark' => $dept['remark'],
            'order' => $dept['order'],
            'createdAt' => $dept['created_at'],
            'createdUserName' => $createdUserName,
            'updatedAt' => $dept['updated_at'],
            'updatedUserName' => $updatedUserName
        ]);
    }
}
