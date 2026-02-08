<?php

namespace App\Modules\Dic\Controller\Http;

use App\Base\BaseController;
use Library\Facades\DB;
use Library\Facades\Request;
use Library\Facades\Response;
use Library\Facades\Validate;

/**
 * 获取字典组详情
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
            'id' => ['text' => '字典ID', 'rules' => ['required', 'integer', 'min:1']],
        ]);
        
        $this->id = (int)Request::post('id');
    }

    /**
     * 业务处理
     */
    protected function service()
    {
        $dic = DB::table('tb_basic_sys_dic')
            ->where('id', $this->id)
            ->where('is_delete', 0)
            ->first();
        
        if (!$dic) {
            throw new \Exception("字典不存在");
        }
        
        // 获取创建人信息
        $createdUserName = '';
        if ($dic['created_user_id']) {
            $creator = DB::table('tb_basic_sys_user')
                ->where('id', $dic['created_user_id'])
                ->first(['username']);
            $createdUserName = $creator ? $creator['username'] : '';
        }
        
        // 获取更新人信息
        $updatedUserName = '';
        if ($dic['updated_user_id']) {
            $updater = DB::table('tb_basic_sys_user')
                ->where('id', $dic['updated_user_id'])
                ->first(['username']);
            $updatedUserName = $updater ? $updater['username'] : '';
        }
        
        return Response::success([
            'id' => $dic['id'],
            'code' => $dic['code'],
            'name' => $dic['name'],
            'status' => $dic['status'],
            'remark' => $dic['remark'],
            'createdAt' => $dic['created_at'],
            'createdUserName' => $createdUserName,
            'updatedAt' => $dic['updated_at'],
            'updatedUserName' => $updatedUserName
        ]);
    }
}
