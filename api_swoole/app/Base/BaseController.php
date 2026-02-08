<?php

namespace App\Base;

use Library\Facades\DB;
use Library\Facades\Log;
use Library\Facades\Request;
use Library\Facades\Response;

/**
 * Http请求单一行为控制器
 */
abstract class BaseController {
    
    protected $userId;
    public function __invoke() {
        $this->userId = Request::getTokenPayload('uid');
        try {
            $this->check();
            $result = $this->service();
            return $result;
        } catch (\Throwable $th) {
            DB::rollBack();
            $errorResult = Response::fail($th->getMessage());
            Log::exception($th);
            return $errorResult;
        }
    }

    /**
     * 参数检查
     */
    protected abstract function check();

    /**
     * 业务处理
     */
    protected abstract function service();
}
