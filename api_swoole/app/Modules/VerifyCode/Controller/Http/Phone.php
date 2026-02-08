<?php

namespace App\Modules\VerifyCode\Controller\Http;

use App\Base\BaseController;
use Library\Facades\Redis;
use Library\Facades\Request;
use Library\Facades\Response;
use Library\Facades\Validate;

/**
 * 获取手机验证码
 */
class Phone extends BaseController
{
    private $phoneNumber;

    /**
     * 参数检查
     */
    protected function check()
    {
        // 参数验证
        Validate::validate([
            'phoneNumber' => ['text' => '手机号', 'rules' => ['required']],
        ]);
        
        $this->phoneNumber = Request::post('phoneNumber');
        
        // 频率限制检查
        $this->checkRateLimit();
    }

    /**
     * 频率限制检查
     */
    private function checkRateLimit()
    {
        $phoneNumber = $this->phoneNumber;
        
        // 检查1分钟内是否已发送过验证码
        $oneMinuteCnt = Redis::get("phone_code_one_minutes_cnt_{$phoneNumber}");
        if ($oneMinuteCnt !== false && $oneMinuteCnt !== null) {
            throw new \Exception("1分钟内只能发送1次验证码");
        }
        
        // 检查1小时内发送次数是否达到5次
        $oneHourCnt = Redis::get("phone_code_one_hour_cnt_{$phoneNumber}");
        if ($oneHourCnt !== false && (int)$oneHourCnt >= 5) {
            throw new \Exception("1小时内只能发送5次验证码");
        }
        
        // 检查1天内发送次数是否达到10次
        $oneDayCnt = Redis::get("phone_code_one_day_cnt_{$phoneNumber}");
        if ($oneDayCnt !== false && (int)$oneDayCnt >= 10) {
            throw new \Exception("1天内只能发送10次验证码");
        }
    }

    /**
     * 业务处理
     */
    protected function service()
    {
        $phoneNumber = $this->phoneNumber;
        
        // 生成6位数字验证码
        $code = (string)rand(100000, 999999);
        
        // 将验证码存入Redis，有效期2分钟
        Redis::setex("phone_code_{$phoneNumber}", 120, $code);
        
        // 更新1分钟计数器
        Redis::setex("phone_code_one_minutes_cnt_{$phoneNumber}", 60, "1");
        
        // 更新1小时计数器
        $oneHourCnt = Redis::get("phone_code_one_hour_cnt_{$phoneNumber}");
        $oneHourCnt = ($oneHourCnt === false || $oneHourCnt === null) ? 1 : (int)$oneHourCnt + 1;
        Redis::setex("phone_code_one_hour_cnt_{$phoneNumber}", 3600, (string)$oneHourCnt);
        
        // 更新1天计数器
        $oneDayCnt = Redis::get("phone_code_one_day_cnt_{$phoneNumber}");
        $oneDayCnt = ($oneDayCnt === false || $oneDayCnt === null) ? 1 : (int)$oneDayCnt + 1;
        Redis::setex("phone_code_one_day_cnt_{$phoneNumber}", 86400, (string)$oneDayCnt);
        
        // TODO: 调用短信发送服务发送验证码
        // $this->sendSms($phoneNumber, $code);
        
        return Response::success(null, '发送成功');
    }
}
