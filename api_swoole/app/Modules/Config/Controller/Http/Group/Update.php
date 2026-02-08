<?php
namespace App\Modules\Config\Controller\Http\Group;

use App\Base\BaseController;
use Library\Facades\DB;
use Library\Facades\Request;
use Library\Facades\Response;
use Library\Facades\Validate;

/**
 * 更新配置组
 * POST /basic/config/group/update
 */
class Update extends BaseController
{
    protected function check(): void
    {
        $validator = Validate::make(Request::all(), [
            'id' => 'required|integer',
            'groupKey' => 'nullable|string|max:100',
            'groupName' => 'nullable|string|max:100',
            'remark' => 'nullable|string|max:500',
            'sort' => 'nullable|integer|min:0',
            'updatedAt' => 'required|date',
        ], [
            'id.required' => '配置组ID不能为空',
            'updatedAt.required' => '更新时间不能为空',
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

        // 乐观锁检查
        $updatedAt = Request::input('updatedAt');
        if ($group->updated_at != $updatedAt) {
            Response::fail('数据已被修改，请刷新后重试')->send();
        }

        // 检查配置组键是否重复
        $groupKey = Request::input('groupKey');
        if ($groupKey && $groupKey !== $group->group_key) {
            $exists = DB::table('tb_basic_config_group')
                ->where('group_key', $groupKey)
                ->where('id', '!=', $id)
                ->where('is_delete', 0)
                ->exists();
            if ($exists) {
                Response::fail('配置组键已存在')->send();
            }
        }
    }

    protected function service(): void
    {
        $id = Request::input('id');
        $userId = $this->getUserId();
        $now = date('Y-m-d H:i:s');

        $updateData = [
            'updated_at' => $now,
            'updated_user_id' => $userId,
        ];

        $groupKey = Request::input('groupKey');
        if ($groupKey !== null) {
            $updateData['group_key'] = $groupKey;
        }

        $groupName = Request::input('groupName');
        if ($groupName !== null) {
            $updateData['group_name'] = $groupName;
        }

        $remark = Request::input('remark');
        if ($remark !== null) {
            $updateData['remark'] = $remark;
        }

        $sort = Request::input('sort');
        if ($sort !== null) {
            $updateData['sort'] = $sort;
        }

        DB::table('tb_basic_config_group')
            ->where('id', $id)
            ->update($updateData);

        Response::success()->send();
    }
}
