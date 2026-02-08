<?php
namespace App\Modules\Feedback\Controller\Http;

use App\Base\BaseController;
use Library\Facades\DB;
use Library\Facades\Request;
use Library\Facades\Response;
use Library\Facades\Validate;

/**
 * 删除问题反馈
 * POST /basic/feedback/remove
 */
class Remove extends BaseController
{
    protected function check(): void
    {
        $validator = Validate::make(Request::all(), [
            'id' => 'required|integer',
        ], [
            'id.required' => '反馈ID不能为空',
        ]);

        if ($validator->fails()) {
            Response::fail($validator->errors()->first())->send();
        }

        // 检查反馈是否存在
        $id = Request::input('id');
        $feedback = DB::table('tb_basic_feedback')
            ->where('id', $id)
            ->where('is_delete', 0)
            ->first();
        if (!$feedback) {
            Response::fail('反馈不存在')->send();
        }
    }

    protected function service(): void
    {
        $id = Request::input('id');
        $now = date('Y-m-d H:i:s');

        // 软删除
        DB::table('tb_basic_feedback')
            ->where('id', $id)
            ->update([
                'is_delete' => 1,
                'updated_at' => $now,
            ]);

        Response::success()->send();
    }
}
