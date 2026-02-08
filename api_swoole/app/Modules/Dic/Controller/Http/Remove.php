<?php

namespace App\Modules\Dic\Controller\Http;

use App\Base\BaseController;
use Library\Facades\DB;
use Library\Facades\Request;
use Library\Facades\Response;
use Library\Facades\Validate;

/**
 * 移除字典组（软删除）
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
            'id' => ['text' => '字典ID', 'rules' => ['required', 'integer', 'min:1']],
        ]);
        
        $this->id = (int)Request::post('id');
        $this->updatedAt = Request::post('updatedAt');
        
        // 检查字典是否存在
        $dic = DB::table('tb_basic_sys_dic')
            ->where('id', $this->id)
            ->where('is_delete', 0)
            ->first();
        if (!$dic) {
            throw new \Exception("字典不存在");
        }
        
        // 乐观锁检查
        if ($this->updatedAt && $dic['updated_at'] !== $this->updatedAt) {
            throw new \Exception("数据已被修改，请刷新后重试");
        }
    }

    /**
     * 业务处理
     */
    protected function service()
    {
        $now = date('Y-m-d H:i:s');
        
        DB::beginTransaction();
        
        try {
            // 软删除字典
            DB::table('tb_basic_sys_dic')
                ->where('id', $this->id)
                ->update([
                    'is_delete' => 1,
                    'updated_user_id' => $this->userId,
                    'updated_at' => $now
                ]);
            
            // 软删除字典项
            DB::table('tb_basic_sys_dic_item')
                ->where('dic_id', $this->id)
                ->update([
                    'is_delete' => 1,
                    'updated_user_id' => $this->userId,
                    'updated_at' => $now
                ]);
            
            DB::commit();
            
            return Response::success(null, '字典移除成功');
        } catch (\Exception $e) {
            DB::rollBack();
            throw $e;
        }
    }
}
