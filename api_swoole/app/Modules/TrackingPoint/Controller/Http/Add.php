<?php
namespace App\Modules\TrackingPoint\Controller\Http;

use App\Base\BaseController;
use Library\Facades\DB;
use Library\Facades\Request;
use Library\Facades\Response;
use Library\Facades\Validate;

/**
 * 添加埋点记录
 * POST /basic/trackingPoint/add
 */
class Add extends BaseController
{
    protected bool $requireAuth = false;

    protected function check(): void
    {
        $validator = Validate::make(Request::all(), [
            'eventType' => 'required|string|max:50',
            'eventName' => 'required|string|max:100',
            'eventData' => 'nullable|array',
            'page' => 'nullable|string|max:200',
            'userId' => 'nullable|integer',
            'deviceId' => 'nullable|string|max:100',
            'platform' => 'nullable|string|max:50',
            'appVersion' => 'nullable|string|max:20',
        ], [
            'eventType.required' => '事件类型不能为空',
            'eventName.required' => '事件名称不能为空',
        ]);

        if ($validator->fails()) {
            Response::fail($validator->errors()->first())->send();
        }
    }

    protected function service(): void
    {
        $now = date('Y-m-d H:i:s');
        $eventData = Request::input('eventData', []);

        $data = [
            'event_type' => Request::input('eventType'),
            'event_name' => Request::input('eventName'),
            'event_data' => !empty($eventData) ? json_encode($eventData) : null,
            'page' => Request::input('page', ''),
            'user_id' => Request::input('userId'),
            'device_id' => Request::input('deviceId', ''),
            'platform' => Request::input('platform', ''),
            'app_version' => Request::input('appVersion', ''),
            'ip' => Request::ip(),
            'user_agent' => Request::header('User-Agent', ''),
            'created_at' => $now,
        ];

        $id = DB::table('tb_basic_tracking_point_record')->insertGetId($data);

        Response::success(['id' => $id])->send();
    }
}
