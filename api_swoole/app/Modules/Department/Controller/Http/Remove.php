<?php

namespace App\Modules\Department\Controller\Http;

use App\Base\BaseController;
use Library\Facades\DB;
use Library\Facades\Request;
use Library\Facades\Response;
use Library\Facades\Validate;

/**
 * 删除部门
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
            'id' => ['text' => '部门ID', 'rules' => ['required', 'integer', 'min:1']],
        ]);
        
        $this->id = (int)Request::post('id');
        $this->updatedAt = Request::post('updatedAt');
        
        // 检查部门是否存在
        $dept = DB::table('tb_basic_sys_department')
            ->where('id', $this->id)
            ->where('is_delete', 0)
            ->first();
        if (!$dept) {
            throw new \Exception("部门不存在");
        }
        
        // 乐观锁检查
        if ($this->updatedAt && $dept['updated_at'] !== $this->updatedAt) {
            throw new \Exception("数据已被修改，请刷新后重试");
        }
        
        // 检查是否有子部门
        $childCount = DB::table('tb_basic_sys_department')
            ->where('parent_id', $this->id)
            ->where('is_delete', 0)
            ->count();
        if ($childCount > 0) {
            throw new \Exception("请先删除子部门");
        }
        
        // 检查是否有用户属于该部门
        $userCount = DB::table('tb_basic_sys_user')
            ->where('department_id', $this->id)
            ->count();
        if ($userCount > 0) {
            throw new \Exception("该部门下还有用户，无法删除");
        }
    }

    /**
     * 业务处理
     */
    protected function service()
    {
        $now = date('Y-m-d H:i:s');
        
        // 软删除部门
        DB::table('tb_basic_sys_department')
            ->where('id', $this->id)
            ->update([
                'is_delete' => 1,
                'updated_user_id' => $this->userId,
                'updated_at' => $now
            ]);
        
        return Response::success(null, '部门删除成功');
    }
}
