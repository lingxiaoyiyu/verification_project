<?php

namespace App\Modules\Auth\Controller\Http;

use App\Base\BaseController;
use Library\Facades\Redis;
use Library\Facades\Request;
use Library\Facades\Response;

/**
 * 退出登录
 */
class Logout extends BaseController
{
    /**
     * 参数检查
     */
    protected function check()
    {
        // 无需参数验证
    }

    /**
     * 业务处理
     */
    protected function service()
    {
        // 获取当前Token
        $token = Request::header('Authorization');
        if ($token) {
            // 移除Bearer前缀
            $token = str_replace('Bearer ', '', $token);
            // 删除Redis中的Token
            Redis::del("token:{$token}");
        }
        
        return Response::success(null, '退出成功');
    }
}
