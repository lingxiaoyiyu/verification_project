<?php

namespace App\Modules\Dic\Controller\Http;

use App\Base\BaseController;
use Library\Facades\DB;
use Library\Facades\Request;
use Library\Facades\Response;
use Library\Facades\Validate;

/**
 * 删除字典组（物理删除）
 */
class Delete extends BaseController
{
    private $id;

    /**
     * 参数检查
     */
    protected function check()
    {
        Validate::validate([
            'id' => ['text' => '字典ID', 'rules' => ['required', 'integer', 'min:1']],
        ]);
        
        $this->id = (int)Request::post('id');
        
        // 检查字典是否存在
        $dic = DB::table('tb_basic_sys_dic')
            ->where('id', $this->id)
            ->first();
        if (!$dic) {
            throw new \Exception("字典不存在");
        }
    }

    /**
     * 业务处理
     */
    protected function service()
    {
        DB::beginTransaction();
        
        try {
            // 删除字典项
            DB::table('tb_basic_sys_dic_item')
                ->where('dic_id', $this->id)
                ->delete();
            
            // 删除字典
            DB::table('tb_basic_sys_dic')
                ->where('id', $this->id)
                ->delete();
            
            DB::commit();
            
            return Response::success(null, '字典删除成功');
        } catch (\Exception $e) {
            DB::rollBack();
            throw $e;
        }
    }
}
