<?php
namespace App\Modules\Config\Controller\Http\Update;

use App\Base\BaseController;
use Library\Facades\DB;
use Library\Facades\Request;
use Library\Facades\Response;
use Library\Facades\Validate;

/**
 * 更新配置项排序
 * POST /basic/config/update/sort
 */
class Sort extends BaseController
{
    protected function check(): void
    {
        $validator = Validate::make(Request::all(), [
            'id' => 'required|integer',
            'sort' => 'required|integer|min:0',
            'updatedAt' => 'required|date',
        ], [
            'id.required' => '配置ID不能为空',
            'sort.required' => '排序值不能为空',
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
        $sort = Request::input('sort');
        $userId = $this->getUserId();
        $now = date('Y-m-d H:i:s');

        DB::table('tb_basic_config')
            ->where('id', $id)
            ->update([
                'sort' => $sort,
                'updated_at' => $now,
                'updated_user_id' => $userId,
            ]);

        Response::success()->send();
    }
}
