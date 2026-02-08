<?php

namespace App\Modules\User\Controller\Http\Online;

use App\Base\BaseController;
use Library\Facades\Redis;
use Library\Facades\Request;
use Library\Facades\Response;
use Library\Facades\Validate;

/**
 * 获取用户终端列表
 */
class TerminalList extends BaseController
{
    private $targetUserId;

    /**
     * 参数检查
     */
    protected function check()
    {
        Validate::validate([
            'userId' => ['text' => '用户ID', 'rules' => ['required', 'integer', 'min:1']],
        ]);
        
        $this->targetUserId = (int)Request::post('userId');
    }

    /**
     * 业务处理
     */
    protected function service()
    {
        // 获取所有Token
        $onlineTokens = Redis::keys('token:*');
        $terminals = [];
        
        foreach ($onlineTokens as $tokenKey) {
            $tokenData = Redis::get($tokenKey);
            if ($tokenData) {
                $data = json_decode($tokenData, true);
                if (isset($data['uid']) && $data['uid'] == $this->targetUserId) {
                    // 提取Token
                    $token = str_replace('token:', '', $tokenKey);
                    
                    $terminals[] = [
                        'token' => $token,
                        'clientFrom' => $data['clientFrom'] ?? 'unknown',
                        'lastActiveAt' => date('Y-m-d H:i:s') // Redis中可以存储最后活跃时间
                    ];
                }
            }
        }
        
        return Response::success($terminals);
    }
}
