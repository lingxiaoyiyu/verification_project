<?php
namespace App\Modules\Feedback\Controller\Http;

use App\Base\BaseController;
use Library\Facades\DB;
use Library\Facades\Request;
use Library\Facades\Response;
use Library\Facades\Validate;

/**
 * 获取问题反馈详情
 * POST /basic/feedback/get
 */
class Get extends BaseController
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
    }

    protected function service(): void
    {
        $id = Request::input('id');

        $feedback = DB::table('tb_basic_feedback as f')
            ->leftJoin('tb_basic_sys_user as u', 'f.user_id', '=', 'u.id')
            ->leftJoin('tb_basic_sys_user as ru', 'f.reply_user_id', '=', 'ru.id')
            ->select([
                'f.*',
                'u.username',
                'u.nickname',
                'u.avatar',
                'ru.username as reply_username',
                'ru.nickname as reply_nickname',
            ])
            ->where('f.id', $id)
            ->where('f.is_delete', 0)
            ->first();

        if (!$feedback) {
            Response::fail('反馈不存在')->send();
        }

        // 反馈类型映射
        $typeMap = [
            1 => '功能建议',
            2 => 'Bug反馈',
            3 => '使用咨询',
            4 => '其他',
        ];

        // 状态映射
        $statusMap = [
            0 => '待处理',
            1 => '处理中',
            2 => '已回复',
            3 => '已关闭',
        ];

        Response::success([
            'id' => $feedback->id,
            'userId' => $feedback->user_id,
            'type' => $feedback->type,
            'typeName' => $typeMap[$feedback->type] ?? '未知',
            'title' => $feedback->title,
            'content' => $feedback->content,
            'contact' => $feedback->contact,
            'images' => $feedback->images ? json_decode($feedback->images, true) : [],
            'status' => $feedback->status,
            'statusName' => $statusMap[$feedback->status] ?? '未知',
            'reply' => $feedback->reply,
            'replyUserId' => $feedback->reply_user_id,
            'replyAt' => $feedback->reply_at,
            'createdAt' => $feedback->created_at,
            'updatedAt' => $feedback->updated_at,
            'user' => [
                'username' => $feedback->username,
                'nickname' => $feedback->nickname,
                'avatar' => $feedback->avatar,
            ],
            'replyUser' => $feedback->reply_user_id ? [
                'username' => $feedback->reply_username,
                'nickname' => $feedback->reply_nickname,
            ] : null,
        ])->send();
    }
}
