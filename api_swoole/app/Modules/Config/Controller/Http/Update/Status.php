<?php
namespace App\Modules\Config\Controller\Http\Update;

use App\Base\BaseController;
use Library\Facades\DB;
use Library\Facades\Request;
use Library\Facades\Response;
use Library\Facades\Validate;

/**
 * 更新配置项状态
 * POST /basic/config/update/status
 */
class Status extends BaseController
{
    protected function check(): void
    {
        $validator = Validate::make(Request::all(), [
            'id' => 'required|integer',
            'status' => 'required|integer|in:0,1',
            'updatedAt' => 'required|date',
        ], [
            'id.required' => '配置ID不能为空',
            'status.required' => '状态不能为空',
            'status.in' => '状态值无效',
            'updatedAt.required' => '更新时间不能为空',
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

        // 乐观锁检查
        $updatedAt = Request::input('updatedAt');
        if ($config->updated_at != $updatedAt) {
            Response::fail('数据已被修改，请刷新后重试')->send();
        }
    }

    protected function service(): void
    {
        $id = Request::input('id');
        $status = Request::input('status');
        $userId = $this->getUserId();
        $now = date('Y-m-d H:i:s');

        DB::table('tb_basic_config')
            ->where('id', $id)
            ->update([
                'status' => $status,
                'updated_at' => $now,
                'updated_user_id' => $userId,
            ]);

        Response::success()->send();
    }
}
