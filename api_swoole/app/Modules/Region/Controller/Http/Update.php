<?php

namespace App\Modules\Region\Controller\Http;

use App\Base\BaseController;
use Library\Facades\DB;
use Library\Facades\Request;
use Library\Facades\Response;
use Library\Facades\Validate;

/**
 * 更新行政区域
 */
class Update extends BaseController
{
    private $id;
    private $parentId;
    private $name;
    private $code;
    private $status;
    private $updatedAt;

    /**
     * 参数检查
     */
    protected function check()
    {
        Validate::validate([
            'id' => ['text' => '区域ID', 'rules' => ['required', 'integer', 'min:1']],
            'parentId' => ['text' => '父区域ID', 'rules' => ['required', 'integer', 'min:0']],
            'name' => ['text' => '区域名称', 'rules' => ['required', 'max:64']],
            'code' => ['text' => '区域编码', 'rules' => ['required', 'max:32']],
            'status' => ['text' => '状态', 'rules' => ['required', 'integer']],
            'updatedAt' => ['text' => '更新时间', 'rules' => ['required']],
        ]);
        
        $this->id = (int)Request::post('id');
        $this->parentId = (int)Request::post('parentId');
        $this->name = trim(Request::post('name'));
        $this->code = trim(Request::post('code'));
        $this->status = (int)Request::post('status');
        $this->updatedAt = Request::post('updatedAt');
        
        // 检查区域是否存在
        $region = DB::table('tb_basic_sys_region')
            ->where('id', $this->id)
            ->where('is_delete', 0)
            ->first();
        if (!$region) {
            throw new \Exception("区域不存在");
        }
        
        // 乐观锁检查
        if ($region['updated_at'] !== $this->updatedAt) {
            throw new \Exception("数据已被修改，请刷新后重试");
        }
        
        // 不能将区域设为自己的子区域
        if ($this->parentId == $this->id) {
            throw new \Exception("不能将区域设为自己的子区域");
        }
        
        // 验证父区域
        if ($this->parentId > 0) {
            $parent = DB::table('tb_basic_sys_region')
                ->where('id', $this->parentId)
                ->where('is_delete', 0)
                ->first();
            if (!$parent) {
                throw new \Exception("父区域不存在");
            }
        }
        
        // 验证编码唯一性（排除自身）
        $existCode = DB::table('tb_basic_sys_region')
            ->where('code', $this->code)
            ->where('id', '!=', $this->id)
            ->where('is_delete', 0)
            ->first();
        if ($existCode) {
            throw new \Exception("区域编码已存在");
        }
    }

    /**
     * 业务处理
     */
    protected function service()
    {
        $now = date('Y-m-d H:i:s');
        
        // 计算层级
        $level = 1;
        if ($this->parentId > 0) {
            $parent = DB::table('tb_basic_sys_region')
                ->where('id', $this->parentId)
                ->first();
            $level = $parent['level'] + 1;
        }
        
        DB::table('tb_basic_sys_region')
            ->where('id', $this->id)
            ->update([
                'parent_id' => $this->parentId,
                'name' => $this->name,
                'code' => $this->code,
                'level' => $level,
                'status' => $this->status,
                'updated_user_id' => $this->userId,
                'updated_at' => $now
            ]);
        
        return Response::success([
            'updatedAt' => $now
        ], '区域更新成功');
    }
}
