<?php

namespace App\Modules\Test\Controller\Http;

use App\Base\BaseController;
use Library\Facades\Log;
use Library\Facades\Response;

/**
 * 测试接口
 */
class Test extends BaseController
{

    /**
     * 参数检查
     */
    protected function check()
    {

    }

    /**
     * 业务处理
     */
    protected function service()
    {
        echo 1;
        // $this->result['data']['routes'] = Route::getRoutes();
        Log::debug("测试日志");
        Log::info("测试日志");
        Log::error("测试错误日志");
        Log::warning("测试警告日志");
        return Response::success("测试成功6");
    }
}
