<?php
namespace App\Modules\Feedback\Controller\Http\Update;

use App\Base\BaseController;
use Library\Facades\DB;
use Library\Facades\Request;
use Library\Facades\Response;
use Library\Facades\Validate;

/**
 * 回复问题反馈
 * POST /basic/feedback/update/reply
 */
class Reply extends BaseController
{
    protected function check(): void
    {
        $validator = Validate::make(Request::all(), [
            'id' => 'required|integer',
            'reply' => 'required|string|max:2000',
            'updatedAt' => 'required|date',
        ], [
            'id.required' => '反馈ID不能为空',
            'reply.required' => '回复内容不能为空',
            'reply.max' => '回复内容长度不能超过2000个字符',
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
        $reply = Request::input('reply');
        $userId = $this->getUserId();
        $now = date('Y-m-d H:i:s');

        DB::table('tb_basic_feedback')
            ->where('id', $id)
            ->update([
                'reply' => $reply,
                'reply_user_id' => $userId,
                'reply_at' => $now,
                'status' => 2, // 已回复
                'updated_at' => $now,
            ]);

        Response::success()->send();
    }
}
