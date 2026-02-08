<?php

namespace App\Modules\Region\Controller\Http\Update;

use App\Base\BaseController;
use Library\Facades\DB;
use Library\Facades\Request;
use Library\Facades\Response;
use Library\Facades\Validate;

/**
 * 更新行政区域排序
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
            'id' => ['text' => '区域ID', 'rules' => ['required', 'integer', 'min:1']],
            'order' => ['text' => '排序值', 'rules' => ['required', 'integer', 'min:0']],
        ]);
        
        $this->id = (int)Request::post('id');
        $this->order = (int)Request::post('order');
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
    }

    /**
     * 业务处理
     */
    protected function service()
    {
        $now = date('Y-m-d H:i:s');
        
        DB::table('tb_basic_sys_region')
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
