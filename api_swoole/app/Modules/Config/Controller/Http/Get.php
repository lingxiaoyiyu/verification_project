<?php
namespace App\Modules\Config\Controller\Http;

use App\Base\BaseController;
use Library\Facades\DB;
use Library\Facades\Request;
use Library\Facades\Response;
use Library\Facades\Validate;

/**
 * 获取配置详情
 * POST /basic/config/get
 */
class Get extends BaseController
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
    }

    protected function service(): void
    {
        $id = Request::input('id');

        $config = DB::table('tb_basic_config')
            ->where('id', $id)
            ->where('is_delete', 0)
            ->first();

        if (!$config) {
            Response::fail('配置不存在')->send();
        }

        Response::success([
            'id' => $config->id,
            'groupId' => $config->group_id,
            'configKey' => $config->config_key,
            'configValue' => $config->config_value,
            'configName' => $config->config_name,
            'remark' => $config->remark,
            'sort' => $config->sort,
            'status' => $config->status,
            'createdAt' => $config->created_at,
            'updatedAt' => $config->updated_at,
        ])->send();
    }
}
