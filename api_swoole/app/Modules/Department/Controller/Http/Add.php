<?php

namespace App\Modules\Department\Controller\Http;

use App\Base\BaseController;
use Library\Facades\DB;
use Library\Facades\Request;
use Library\Facades\Response;
use Library\Facades\Validate;

/**
 * 添加部门
 */
class Add extends BaseController
{
    private $parentId;
    private $name;
    private $status;
    private $remark;

    /**
     * 参数检查
     */
    protected function check()
    {
        Validate::validate([
            'parentId' => ['text' => '父部门ID', 'rules' => ['required', 'integer', 'min:0']],
            'name' => ['text' => '部门名称', 'rules' => ['required', 'max:64']],
            'status' => ['text' => '状态', 'rules' => ['required', 'integer']],
        ]);
        
        $this->parentId = (int)Request::post('parentId');
        $this->name = trim(Request::post('name'));
        $this->status = (int)Request::post('status');
        $this->remark = trim(Request::post('remark', ''));
        
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
        
        // 验证部门名称唯一性（同父部门下）
        $existName = DB::table('tb_basic_sys_department')
            ->where('parent_id', $this->parentId)
            ->where('name', $this->name)
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
        // 计算排序值
        $maxOrder = DB::table('tb_basic_sys_department')
            ->where('parent_id', $this->parentId)
            ->where('is_delete', 0)
            ->max('order') ?? 0;
        
        $now = date('Y-m-d H:i:s');
        
        $deptId = DB::table('tb_basic_sys_department')->insertGetId([
            'parent_id' => $this->parentId,
            'name' => $this->name,
            'status' => $this->status,
            'remark' => $this->remark ?: null,
            'order' => $maxOrder + 1,
            'is_delete' => 0,
            'created_user_id' => $this->userId,
            'updated_user_id' => $this->userId,
            'created_at' => $now,
            'updated_at' => $now
        ]);
        
        return Response::success([
            'id' => $deptId
        ], '部门添加成功');
    }
}
