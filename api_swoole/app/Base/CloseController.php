<?php

namespace App\Base;

use Library\Facades\DB;
use Library\Facades\Log;
use Library\Facades\Response;

/**
 * Websocket Close 控制器
 */
abstract class CloseController {
    
    public function __invoke() {
        try {
            $this->check();
            $this->service();
            DB::commit();
        } catch (\Throwable $th) {
            DB::rollBack();
            Response::fail($th->getMessage(), $th->getCode());
            Log::exception($th);
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
