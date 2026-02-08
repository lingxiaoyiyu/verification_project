<?php
namespace App\Modules\Config\Controller\Http\Update;

use App\Base\BaseController;
use Library\Facades\DB;
use Library\Facades\Request;
use Library\Facades\Response;
use Library\Facades\Validate;

/**
 * 更新配置项
 * POST /basic/config/update
 */
class Info extends BaseController
{
    protected function check(): void
    {
        $validator = Validate::make(Request::all(), [
            'id' => 'required|integer',
            'configKey' => 'nullable|string|max:100',
            'configValue' => 'nullable|string|max:2000',
            'configName' => 'nullable|string|max:100',
            'remark' => 'nullable|string|max:500',
            'updatedAt' => 'required|date',
        ], [
            'id.required' => '配置ID不能为空',
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

        // 检查配置键是否重复
        $configKey = Request::input('configKey');
        if ($configKey && $configKey !== $config->config_key) {
            $exists = DB::table('tb_basic_config')
                ->where('config_key', $configKey)
                ->where('group_id', $config->group_id)
                ->where('id', '!=', $id)
                ->where('is_delete', 0)
                ->exists();
            if ($exists) {
                Response::fail('该配置组下配置键已存在')->send();
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

        $configKey = Request::input('configKey');
        if ($configKey !== null) {
            $updateData['config_key'] = $configKey;
        }

        $configValue = Request::input('configValue');
        if ($configValue !== null) {
            $updateData['config_value'] = $configValue;
        }

        $configName = Request::input('configName');
        if ($configName !== null) {
            $updateData['config_name'] = $configName;
        }

        $remark = Request::input('remark');
        if ($remark !== null) {
            $updateData['remark'] = $remark;
        }

        DB::table('tb_basic_config')
            ->where('id', $id)
            ->update($updateData);

        Response::success()->send();
    }
}
