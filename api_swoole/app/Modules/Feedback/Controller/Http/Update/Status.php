<?php
namespace App\Modules\Feedback\Controller\Http\Update;

use App\Base\BaseController;
use Library\Facades\DB;
use Library\Facades\Request;
use Library\Facades\Response;
use Library\Facades\Validate;

/**
 * 修改问题反馈状态
 * POST /basic/feedback/update/status
 */
class Status extends BaseController
{
    protected function check(): void
    {
        $validator = Validate::make(Request::all(), [
            'id' => 'required|integer',
            'status' => 'required|integer|in:0,1,2,3',
            'updatedAt' => 'required|date',
        ], [
            'id.required' => '反馈ID不能为空',
            'status.required' => '状态不能为空',
            'status.in' => '状态值无效',
            'updatedAt.required' => '更新时间不能为空',
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

        // 乐观锁检查
        $updatedAt = Request::input('updatedAt');
        if ($feedback->updated_at != $updatedAt) {
            Response::fail('数据已被修改，请刷新后重试')->send();
        }
    }

    protected function service(): void
    {
        $id = Request::input('id');
        $status = Request::input('status');
        $now = date('Y-m-d H:i:s');

        DB::table('tb_basic_feedback')
            ->where('id', $id)
            ->update([
                'status' => $status,
                'updated_at' => $now,
            ]);

        Response::success()->send();
    }
}
