<?php
namespace App\Modules\Config\Controller\Http;

use App\Base\BaseController;
use Library\Facades\DB;
use Library\Facades\Request;
use Library\Facades\Response;
use Library\Facades\Validate;

/**
 * 删除配置项
 * POST /basic/config/delete
 */
class Delete extends BaseController
{
    protected function check(): void
    {
        $validator = Validate::make(Request::all(), [
            'id' => 'required|integer',
        ], [
            'id.required' => '配置ID不能为空',
        ]);

        if ($validator->fails()) {
            Response::fail($validator->errors()->first())->send();
        }

        // 检查配置是否存在
        $id = Request::input('id');
        $config = DB::table('tb_basic_config')
            ->where('id', $id)
            ->where('is_delete', 0)
            ->first();
        if (!$config) {
            Response::fail('配置不存在')->send();
        }
    }

    protected function service(): void
    {
        $id = Request::input('id');
        $userId = $this->getUserId();
        $now = date('Y-m-d H:i:s');

        // 软删除
        DB::table('tb_basic_config')
            ->where('id', $id)
            ->update([
                'is_delete' => 1,
                'updated_at' => $now,
                'updated_user_id' => $userId,
            ]);

        Response::success()->send();
    }
}
