<?php
namespace App\Modules\Dashboard\Controller\Http\Workbench;

use App\Base\BaseController;
use Library\Facades\DB;
use Library\Facades\Response;

/**
 * 待办事项
 * POST /basic/dashboard/workbench/todo
 */
class Todo extends BaseController
{
    protected function check(): void
    {
        // 无需参数校验
    }

    protected function service(): void
    {
        $userId = $this->getUserId();

        // 获取待处理的反馈数量
        $pendingFeedbackCount = DB::table('tb_basic_feedback')
            ->where('status', 0) // 待处理
            ->where('is_delete', 0)
            ->count();

        // 获取未读消息数量（如果有消息表）
        $unreadMessageCount = 0;
        // 可以根据实际业务扩展

        // 构建待办事项列表
        $todoList = [];

        if ($pendingFeedbackCount > 0) {
            $todoList[] = [
                'id' => 1,
                'title' => '待处理反馈',
                'count' => $pendingFeedbackCount,
                'type' => 'feedback',
                'path' => '/feedback/list',
            ];
        }

        if ($unreadMessageCount > 0) {
            $todoList[] = [
                'id' => 2,
                'title' => '未读消息',
                'count' => $unreadMessageCount,
                'type' => 'message',
                'path' => '/message/list',
            ];
        }

        Response::success([
            'list' => $todoList,
            'total' => count($todoList),
            'statistics' => [
                'pendingFeedback' => $pendingFeedbackCount,
                'unreadMessage' => $unreadMessageCount,
            ],
        ])->send();
    }
}
