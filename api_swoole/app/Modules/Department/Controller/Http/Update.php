<?php

namespace App\Modules\Department\Controller\Http;

use App\Base\BaseController;
use Library\Facades\DB;
use Library\Facades\Request;
use Library\Facades\Response;
use Library\Facades\Validate;

/**
 * 更新部门
 */
class Update extends BaseController
{
    private $id;
    private $parentId;
    private $name;
    private $status;
    private $remark;
    private $updatedAt;

    /**
     * 参数检查
     */
    protected function check()
    {
        Validate::validate([
            'id' => ['text' => '部门ID', 'rules' => ['required', 'integer', 'min:1']],
            'parentId' => ['text' => '父部门ID', 'rules' => ['required', 'integer', 'min:0']],
            'name' => ['text' => '部门名称', 'rules' => ['required', 'max:64']],
            'status' => ['text' => '状态', 'rules' => ['required', 'integer']],
            'updatedAt' => ['text' => '更新时间', 'rules' => ['required']],
        ]);
        
        $this->id = (int)Request::post('id');
        $this->parentId = (int)Request::post('parentId');
        $this->name = trim(Request::post('name'));
        $this->status = (int)Request::post('status');
        $this->remark = trim(Request::post('remark', ''));
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
        if ($dept['updated_at'] !== $this->updatedAt) {
            throw new \Exception("数据已被修改，请刷新后重试");
        }
        
        // 不能将部门设为自己的子部门
        if ($this->parentId == $this->id) {
            throw new \Exception("不能将部门设为自己的子部门");
        }
        
        // 验证父部门
        if ($this->parentId > 0) {
            $parent = DB::table('tb_basic_sys_department')
                ->where('id', $this->parentId)
                ->where('is_delete', 0)
                ->first();
            if (!$parent) {
                throw new \Exception("父部门不存在");
            }
        }
        
        // 验证部门名称唯一性（排除自身）
        $existName = DB::table('tb_basic_sys_department')
            ->where('parent_id', $this->parentId)
            ->where('name', $this->name)
            ->where('id', '!=', $this->id)
            ->where('is_delete', 0)
            ->first();
        if ($existName) {
            throw new \Exception("同级部门下名称已存在");
        }
    }

    /**
     * 业务处理
     */
    protected function service()
    {
        $now = date('Y-m-d H:i:s');
        
        DB::table('tb_basic_sys_department')
            ->where('id', $this->id)
            ->update([
                'parent_id' => $this->parentId,
                'name' => $this->name,
                'status' => $this->status,
                'remark' => $this->remark ?: null,
                'updated_user_id' => $this->userId,
                'updated_at' => $now
            ]);
        
        return Response::success([
            'updatedAt' => $now
        ], '部门更新成功');
    }
}
