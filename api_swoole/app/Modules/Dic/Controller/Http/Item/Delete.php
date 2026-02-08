<?php

namespace App\Modules\Dic\Controller\Http\Item;

use App\Base\BaseController;
use Library\Facades\DB;
use Library\Facades\Request;
use Library\Facades\Response;
use Library\Facades\Validate;

/**
 * 删除字典项
 */
class Delete extends BaseController
{
    private $id;
    private $updatedAt;

    /**
     * 参数检查
     */
    protected function check()
    {
        Validate::validate([
            'id' => ['text' => '字典项ID', 'rules' => ['required', 'integer', 'min:1']],
        ]);
        
        $this->id = (int)Request::post('id');
        $this->updatedAt = Request::post('updatedAt');
        
        // 检查字典项是否存在
        $item = DB::table('tb_basic_sys_dic_item')
            ->where('id', $this->id)
            ->where('is_delete', 0)
            ->first();
        if (!$item) {
            throw new \Exception("字典项不存在");
        }
        
        // 乐观锁检查
        if ($this->updatedAt && $item['updated_at'] !== $this->updatedAt) {
            throw new \Exception("数据已被修改，请刷新后重试");
        }
    }

    /**
     * 业务处理
     */
    protected function service()
    {
        $now = date('Y-m-d H:i:s');
        
        // 软删除字典项
        DB::table('tb_basic_sys_dic_item')
            ->where('id', $this->id)
            ->update([
                'is_delete' => 1,
                'updated_user_id' => $this->userId,
                'updated_at' => $now
            ]);
        
        return Response::success(null, '字典项删除成功');
    }
}
