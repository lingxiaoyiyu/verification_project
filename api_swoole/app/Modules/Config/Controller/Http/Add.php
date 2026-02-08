<?php
namespace App\Modules\Config\Controller\Http;

use App\Base\BaseController;
use Library\Facades\DB;
use Library\Facades\Request;
use Library\Facades\Response;
use Library\Facades\Validate;

/**
 * 添加配置项
 * POST /basic/config/add
 */
class Add extends BaseController
{
    protected function check(): void
    {
        $validator = Validate::make(Request::all(), [
            'groupId' => 'required|integer',
            'configKey' => 'required|string|max:100',
            'configValue' => 'nullable|string|max:2000',
            'configName' => 'required|string|max:100',
            'remark' => 'nullable|string|max:500',
            'sort' => 'nullable|integer|min:0',
        ], [
            'groupId.required' => '配置组ID不能为空',
            'configKey.required' => '配置键不能为空',
            'configKey.max' => '配置键长度不能超过100个字符',
            'configName.required' => '配置名称不能为空',
            'configName.max' => '配置名称长度不能超过100个字符',
        ]);

        if ($validator->fails()) {
            Response::fail($validator->errors()->first())->send();
        }

        // 检查配置组是否存在
        $groupId = Request::input('groupId');
        $group = DB::table('tb_basic_config_group')->where('id', $groupId)->first();
        if (!$group) {
            Response::fail('配置组不存在')->send();
        }

        // 检查配置键是否已存在
        $configKey = Request::input('configKey');
        $exists = DB::table('tb_basic_config')
            ->where('config_key', $configKey)
            ->where('group_id', $groupId)
            ->where('is_delete', 0)
            ->exists();
        if ($exists) {
            Response::fail('该配置组下配置键已存在')->send();
        }
    }

    protected function service(): void
    {
        $userId = $this->getUserId();
        $now = date('Y-m-d H:i:s');

        $data = [
            'group_id' => Request::input('groupId'),
            'config_key' => Request::input('configKey'),
            'config_value' => Request::input('configValue', ''),
            'config_name' => Request::input('configName'),
            'remark' => Request::input('remark', ''),
            'sort' => Request::input('sort', 0),
            'status' => 0,
            'is_delete' => 0,
            'created_at' => $now,
            'updated_at' => $now,
            'created_user_id' => $userId,
            'updated_user_id' => $userId,
        ];

        $id = DB::table('tb_basic_config')->insertGetId($data);

        Response::success(['id' => $id])->send();
    }
}
