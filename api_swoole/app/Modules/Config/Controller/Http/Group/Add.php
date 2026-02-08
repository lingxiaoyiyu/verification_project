<?php
namespace App\Modules\Config\Controller\Http\Group;

use App\Base\BaseController;
use Library\Facades\DB;
use Library\Facades\Request;
use Library\Facades\Response;
use Library\Facades\Validate;

/**
 * 添加配置组
 * POST /basic/config/group/add
 */
class Add extends BaseController
{
    protected function check(): void
    {
        $validator = Validate::make(Request::all(), [
            'groupKey' => 'required|string|max:100',
            'groupName' => 'required|string|max:100',
            'remark' => 'nullable|string|max:500',
            'sort' => 'nullable|integer|min:0',
        ], [
            'groupKey.required' => '配置组键不能为空',
            'groupKey.max' => '配置组键长度不能超过100个字符',
            'groupName.required' => '配置组名称不能为空',
            'groupName.max' => '配置组名称长度不能超过100个字符',
        ]);

        if ($validator->fails()) {
            Response::fail($validator->errors()->first())->send();
        }

        // 检查配置组键是否已存在
        $groupKey = Request::input('groupKey');
        $exists = DB::table('tb_basic_config_group')
            ->where('group_key', $groupKey)
            ->where('is_delete', 0)
            ->exists();
        if ($exists) {
            Response::fail('配置组键已存在')->send();
        }
    }

    protected function service(): void
    {
        $userId = $this->getUserId();
        $now = date('Y-m-d H:i:s');

        $data = [
            'group_key' => Request::input('groupKey'),
            'group_name' => Request::input('groupName'),
            'remark' => Request::input('remark', ''),
            'sort' => Request::input('sort', 0),
            'is_delete' => 0,
            'created_at' => $now,
            'updated_at' => $now,
            'created_user_id' => $userId,
            'updated_user_id' => $userId,
        ];

        $id = DB::table('tb_basic_config_group')->insertGetId($data);

        Response::success(['id' => $id])->send();
    }
}
