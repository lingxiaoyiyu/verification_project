<?php

namespace App\Modules\Dic\Controller\Http\Item;

use App\Base\BaseController;
use Library\Facades\DB;
use Library\Facades\Request;
use Library\Facades\Response;
use Library\Facades\Validate;

/**
 * 获取字典项详情
 */
class Get extends BaseController
{
    private $id;

    /**
     * 参数检查
     */
    protected function check()
    {
        Validate::validate([
            'id' => ['text' => '字典项ID', 'rules' => ['required', 'integer', 'min:1']],
        ]);
        
        $this->id = (int)Request::post('id');
    }

    /**
     * 业务处理
     */
    protected function service()
    {
        $item = DB::table('tb_basic_sys_dic_item')
            ->where('id', $this->id)
            ->where('is_delete', 0)
            ->first();
        
        if (!$item) {
            throw new \Exception("字典项不存在");
        }
        
        // 获取创建人信息
        $createdUserName = '';
        if ($item['created_user_id']) {
            $creator = DB::table('tb_basic_sys_user')
                ->where('id', $item['created_user_id'])
                ->first(['username']);
            $createdUserName = $creator ? $creator['username'] : '';
        }
        
        // 获取更新人信息
        $updatedUserName = '';
        if ($item['updated_user_id']) {
            $updater = DB::table('tb_basic_sys_user')
                ->where('id', $item['updated_user_id'])
                ->first(['username']);
            $updatedUserName = $updater ? $updater['username'] : '';
        }
        
        return Response::success([
            'id' => $item['id'],
            'dicId' => $item['dic_id'],
            'label' => $item['label'],
            'value' => $item['value'],
            'status' => $item['status'],
            'order' => $item['order'],
            'remark' => $item['remark'],
            'createdAt' => $item['created_at'],
            'createdUserName' => $createdUserName,
            'updatedAt' => $item['updated_at'],
            'updatedUserName' => $updatedUserName
        ]);
    }
}
