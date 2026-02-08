<?php
namespace App\Modules\Feedback\Controller\Http;

use App\Base\BaseController;
use Library\Facades\DB;
use Library\Facades\Request;
use Library\Facades\Response;
use Library\Facades\Validate;

/**
 * 问题反馈列表-分页
 * POST /basic/feedback/page
 */
class Page extends BaseController
{
    protected function check(): void
    {
        $validator = Validate::make(Request::all(), [
            'pageNo' => 'nullable|integer|min:1',
            'pageSize' => 'nullable|integer|min:1|max:100',
            'type' => 'nullable|integer|in:1,2,3,4',
            'status' => 'nullable|integer|in:0,1,2,3',
            'title' => 'nullable|string|max:200',
            'userId' => 'nullable|integer',
        ]);

        if ($validator->fails()) {
            Response::fail($validator->errors()->first())->send();
        }
    }

    protected function service(): void
    {
        $pageNo = Request::input('pageNo', 1);
        $pageSize = Request::input('pageSize', 10);
        $type = Request::input('type');
        $status = Request::input('status');
        $title = Request::input('title');
        $userId = Request::input('userId');

        $query = DB::table('tb_basic_feedback as f')
            ->leftJoin('tb_basic_sys_user as u', 'f.user_id', '=', 'u.id')
            ->select([
                'f.id',
                'f.user_id',
                'f.type',
                'f.title',
                'f.content',
                'f.contact',
                'f.status',
                'f.reply',
                'f.reply_at',
                'f.created_at',
                'f.updated_at',
                'u.username',
                'u.nickname',
            ])
            ->where('f.is_delete', 0);

        if ($type !== null) {
            $query->where('f.type', $type);
        }

        if ($status !== null) {
            $query->where('f.status', $status);
        }

        if ($title) {
            $query->where('f.title', 'like', "%{$title}%");
        }

        if ($userId !== null) {
            $query->where('f.user_id', $userId);
        }

        // 获取总数
        $total = $query->count();

        // 获取分页数据
        $list = $query->orderBy('f.created_at', 'desc')
            ->offset(($pageNo - 1) * $pageSize)
            ->limit($pageSize)
            ->get();

        // 类型和状态映射
        $typeMap = [1 => '功能建议', 2 => 'Bug反馈', 3 => '使用咨询', 4 => '其他'];
        $statusMap = [0 => '待处理', 1 => '处理中', 2 => '已回复', 3 => '已关闭'];

        // 格式化返回数据
        $records = [];
        foreach ($list as $item) {
            $records[] = [
                'id' => $item->id,
                'userId' => $item->user_id,
                'type' => $item->type,
                'typeName' => $typeMap[$item->type] ?? '未知',
                'title' => $item->title,
                'content' => mb_substr($item->content, 0, 100) . (mb_strlen($item->content) > 100 ? '...' : ''),
                'contact' => $item->contact,
                'status' => $item->status,
                'statusName' => $statusMap[$item->status] ?? '未知',
                'hasReply' => !empty($item->reply),
                'replyAt' => $item->reply_at,
                'createdAt' => $item->created_at,
                'updatedAt' => $item->updated_at,
                'user' => [
                    'username' => $item->username,
                    'nickname' => $item->nickname,
                ],
            ];
        }

        Response::success([
            'records' => $records,
            'total' => $total,
            'pageNo' => $pageNo,
            'pageSize' => $pageSize,
        ])->send();
    }
}
