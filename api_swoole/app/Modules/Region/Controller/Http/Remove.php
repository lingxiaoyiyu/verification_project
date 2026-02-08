<?php

namespace App\Modules\Region\Controller\Http;

use App\Base\BaseController;
use Library\Facades\DB;
use Library\Facades\Request;
use Library\Facades\Response;
use Library\Facades\Validate;

/**
 * 删除行政区域
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
            'id' => ['text' => '区域ID', 'rules' => ['required', 'integer', 'min:1']],
        ]);
        
        $this->id = (int)Request::post('id');
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
        if ($this->updatedAt && $region['updated_at'] !== $this->updatedAt) {
            throw new \Exception("数据已被修改，请刷新后重试");
        }
        
        // 检查是否有子区域
        $childCount = DB::table('tb_basic_sys_region')
            ->where('parent_id', $this->id)
            ->where('is_delete', 0)
            ->count();
        if ($childCount > 0) {
            throw new \Exception("请先删除子区域");
        }
    }

    /**
     * 业务处理
     */
    protected function service()
    {
        $now = date('Y-m-d H:i:s');
        
        // 软删除区域
        DB::table('tb_basic_sys_region')
            ->where('id', $this->id)
            ->update([
                'is_delete' => 1,
                'updated_user_id' => $this->userId,
                'updated_at' => $now
            ]);
        
        return Response::success(null, '区域删除成功');
    }
}
