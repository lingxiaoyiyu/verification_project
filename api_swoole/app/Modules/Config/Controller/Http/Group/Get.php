<?php
namespace App\Modules\Config\Controller\Http\Group;

use App\Base\BaseController;
use Library\Facades\DB;
use Library\Facades\Request;
use Library\Facades\Response;
use Library\Facades\Validate;

/**
 * 获取配置组详情
 * POST /basic/config/group/get
 */
class Get extends BaseController
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
    }

    protected function service(): void
    {
        $id = Request::input('id');

        $group = DB::table('tb_basic_config_group')
            ->where('id', $id)
            ->where('is_delete', 0)
            ->first();

        if (!$group) {
            Response::fail('配置组不存在')->send();
        }

        Response::success([
            'id' => $group->id,
            'groupKey' => $group->group_key,
            'groupName' => $group->group_name,
            'remark' => $group->remark,
            'sort' => $group->sort,
            'createdAt' => $group->created_at,
            'updatedAt' => $group->updated_at,
        ])->send();
    }
}
