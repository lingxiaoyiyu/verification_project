<?php
namespace App\Modules\Config\Controller\Http\Group;

use App\Base\BaseController;
use Library\Facades\DB;
use Library\Facades\Request;
use Library\Facades\Response;
use Library\Facades\Validate;

/**
 * 删除配置组
 * POST /basic/config/group/delete
 */
class Delete extends BaseController
{
    protected function check(): void
    {
        $validator = Validate::make(Request::all(), [
            'id' => 'required|integer',
        ], [
            'id.required' => '配置组ID不能为空',
        ]);

        if ($validator->fails()) {
            Response::fail($validator->errors()->first())->send();
        }

        // 检查配置组是否存在
        $id = Request::input('id');
        $group = DB::table('tb_basic_config_group')
            ->where('id', $id)
            ->where('is_delete', 0)
            ->first();
        if (!$group) {
            Response::fail('配置组不存在')->send();
        }

        // 检查配置组下是否有配置项
        $hasConfig = DB::table('tb_basic_config')
            ->where('group_id', $id)
            ->where('is_delete', 0)
            ->exists();
        if ($hasConfig) {
            Response::fail('配置组下存在配置项，无法删除')->send();
        }
    }

    protected function service(): void
    {
        $id = Request::input('id');
        $userId = $this->getUserId();
        $now = date('Y-m-d H:i:s');

        // 软删除
        DB::table('tb_basic_config_group')
            ->where('id', $id)
            ->update([
                'is_delete' => 1,
                'updated_at' => $now,
                'updated_user_id' => $userId,
            ]);

        Response::success()->send();
    }
}
