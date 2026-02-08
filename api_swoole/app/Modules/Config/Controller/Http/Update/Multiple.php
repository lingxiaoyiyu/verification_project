<?php
namespace App\Modules\Config\Controller\Http\Update;

use App\Base\BaseController;
use Library\Facades\DB;
use Library\Facades\Request;
use Library\Facades\Response;
use Library\Facades\Validate;

/**
 * 批量更新配置项
 * POST /basic/config/update/multiple
 */
class Multiple extends BaseController
{
    protected function check(): void
    {
        $validator = Validate::make(Request::all(), [
            'configs' => 'required|array|min:1',
            'configs.*.configKey' => 'required|string|max:100',
            'configs.*.configValue' => 'nullable|string|max:2000',
        ], [
            'configs.required' => '配置列表不能为空',
            'configs.array' => '配置列表格式错误',
            'configs.min' => '至少需要一个配置项',
        ]);

        if ($validator->fails()) {
            Response::fail($validator->errors()->first())->send();
        }
    }

    protected function service(): void
    {
        $configs = Request::input('configs', []);
        $userId = $this->getUserId();
        $now = date('Y-m-d H:i:s');

        $updated = 0;
        foreach ($configs as $config) {
            $configKey = $config['configKey'];
            $configValue = $config['configValue'] ?? '';

            $affected = DB::table('tb_basic_config')
                ->where('config_key', $configKey)
                ->where('is_delete', 0)
                ->update([
                    'config_value' => $configValue,
                    'updated_at' => $now,
                    'updated_user_id' => $userId,
                ]);

            $updated += $affected;
        }

        Response::success(['updated' => $updated])->send();
    }
}
