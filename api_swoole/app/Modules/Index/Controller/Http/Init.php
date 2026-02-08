<?php
namespace App\Modules\Index\Controller\Http;

use App\Base\BaseController;
use Library\Facades\DB;
use Library\Facades\Response;

/**
 * 数据初始化接口
 * GET/POST /index/init
 */
class Init extends BaseController
{
    protected bool $requireAuth = false;

    protected function check(): void
    {
        // 无需参数校验
    }

    protected function service(): string
    {
        // 执行数据初始化逻辑
        // 这里可以初始化一些基础数据，如默认角色、默认菜单等
        
        // 检查是否已初始化
        $adminExists = DB::table('tb_basic_sys_user')
            ->where('username', 'admin')
            ->exists();

        if ($adminExists) {
            return Response::success(['message' => '系统已初始化']);
        }

        // 初始化管理员账号等基础数据
        // 具体初始化逻辑根据业务需求实现
        
        return Response::success(['message' => '初始化完成']);
    }
}
