<?php
namespace App\Modules\Index\Controller\Http;

use App\Base\BaseController;
use Library\Facades\Response;

/**
 * 默认入口
 * GET/POST /
 */
class Index extends BaseController
{
    protected bool $requireAuth = false;

    protected function check(): void
    {
        // 无需参数校验
    }

    protected function service(): string
    {
        return Response::success([
            'message' => 'API Server is running',
            'version' => '1.0.0',
            'time' => date('Y-m-d H:i:s'),
        ]);
    }
}
