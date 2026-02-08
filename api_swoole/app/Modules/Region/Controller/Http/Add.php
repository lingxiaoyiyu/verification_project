<?php

namespace App\Modules\Region\Controller\Http;

use App\Base\BaseController;
use Library\Facades\DB;
use Library\Facades\Request;
use Library\Facades\Response;
use Library\Facades\Validate;

/**
 * 添加行政区域
 */
class Add extends BaseController
{
    private $parentId;
    private $name;
    private $code;
    private $level;
    private $status;

    /**
     * 参数检查
     */
    protected function check()
    {
        Validate::validate([
            'parentId' => ['text' => '父区域ID', 'rules' => ['required', 'integer', 'min:0']],
            'name' => ['text' => '区域名称', 'rules' => ['required', 'max:64']],
            'code' => ['text' => '区域编码', 'rules' => ['required', 'max:32']],
            'status' => ['text' => '状态', 'rules' => ['required', 'integer']],
        ]);
        
        $this->parentId = (int)Request::post('parentId');
        $this->name = trim(Request::post('name'));
        $this->code = trim(Request::post('code'));
        $this->level = (int)Request::post('level', 1);
        $this->status = (int)Request::post('status');
        
        // 验证父区域
        if ($this->parentId > 0) {
            $parent = DB::table('tb_basic_sys_region')
                ->where('id', $this->parentId)
                ->where('is_delete', 0)
                ->first();
            if (!$parent) {
                throw new \Exception("父区域不存在");
            }
            $this->level = $parent['level'] + 1;
        }
        
        // 验证编码唯一性
        $existCode = DB::table('tb_basic_sys_region')
            ->where('code', $this->code)
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
        // 计算排序值
        $maxOrder = DB::table('tb_basic_sys_region')
            ->where('parent_id', $this->parentId)
            ->where('is_delete', 0)
            ->max('order') ?? 0;
        
        $now = date('Y-m-d H:i:s');
        
        $regionId = DB::table('tb_basic_sys_region')->insertGetId([
            'parent_id' => $this->parentId,
            'name' => $this->name,
            'code' => $this->code,
            'level' => $this->level,
            'status' => $this->status,
            'order' => $maxOrder + 1,
            'is_delete' => 0,
            'created_user_id' => $this->userId,
            'updated_user_id' => $this->userId,
            'created_at' => $now,
            'updated_at' => $now
        ]);
        
        return Response::success([
            'id' => $regionId
        ], '区域添加成功');
    }
}
