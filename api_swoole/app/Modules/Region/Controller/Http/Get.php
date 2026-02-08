<?php

namespace App\Modules\Region\Controller\Http;

use App\Base\BaseController;
use Library\Facades\DB;
use Library\Facades\Request;
use Library\Facades\Response;
use Library\Facades\Validate;

/**
 * 获取行政区域详情
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
            'id' => ['text' => '区域ID', 'rules' => ['required', 'integer', 'min:1']],
        ]);
        
        $this->id = (int)Request::post('id');
    }

    /**
     * 业务处理
     */
    protected function service()
    {
        $region = DB::table('tb_basic_sys_region')
            ->where('id', $this->id)
            ->where('is_delete', 0)
            ->first();
        
        if (!$region) {
            throw new \Exception("区域不存在");
        }
        
        // 获取创建人信息
        $createdUserName = '';
        if ($region['created_user_id']) {
            $creator = DB::table('tb_basic_sys_user')
                ->where('id', $region['created_user_id'])
                ->first(['username']);
            $createdUserName = $creator ? $creator['username'] : '';
        }
        
        // 获取更新人信息
        $updatedUserName = '';
        if ($region['updated_user_id']) {
            $updater = DB::table('tb_basic_sys_user')
                ->where('id', $region['updated_user_id'])
                ->first(['username']);
            $updatedUserName = $updater ? $updater['username'] : '';
        }
        
        return Response::success([
            'id' => $region['id'],
            'parentId' => $region['parent_id'],
            'name' => $region['name'],
            'code' => $region['code'],
            'level' => $region['level'],
            'status' => $region['status'],
            'order' => $region['order'],
            'createdAt' => $region['created_at'],
            'createdUserName' => $createdUserName,
            'updatedAt' => $region['updated_at'],
            'updatedUserName' => $updatedUserName
        ]);
    }
}
