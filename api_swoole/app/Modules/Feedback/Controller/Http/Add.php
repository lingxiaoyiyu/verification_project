<?php
namespace App\Modules\Feedback\Controller\Http;

use App\Base\BaseController;
use Library\Facades\DB;
use Library\Facades\Request;
use Library\Facades\Response;
use Library\Facades\Validate;

/**
 * 添加问题反馈
 * POST /basic/feedback/add
 */
class Add extends BaseController
{
    protected function check(): void
    {
        $validator = Validate::make(Request::all(), [
            'type' => 'required|integer|in:1,2,3,4',
            'title' => 'required|string|max:200',
            'content' => 'required|string|max:2000',
            'contact' => 'nullable|string|max:100',
            'images' => 'nullable|array',
            'images.*' => 'string|max:500',
        ], [
            'type.required' => '反馈类型不能为空',
            'type.in' => '反馈类型无效',
            'title.required' => '反馈标题不能为空',
            'title.max' => '反馈标题长度不能超过200个字符',
            'content.required' => '反馈内容不能为空',
            'content.max' => '反馈内容长度不能超过2000个字符',
        ]);

        if ($validator->fails()) {
            Response::fail($validator->errors()->first())->send();
        }
    }

    protected function service(): void
    {
        $userId = $this->getUserId();
        $now = date('Y-m-d H:i:s');

        $images = Request::input('images', []);

        $data = [
            'user_id' => $userId,
            'type' => Request::input('type'),
            'title' => Request::input('title'),
            'content' => Request::input('content'),
            'contact' => Request::input('contact', ''),
            'images' => !empty($images) ? json_encode($images) : null,
            'status' => 0, // 待处理
            'is_delete' => 0,
            'created_at' => $now,
            'updated_at' => $now,
        ];

        $id = DB::table('tb_basic_feedback')->insertGetId($data);

        Response::success(['id' => $id])->send();
    }
}
