<?php

namespace App\Modules\User\Controller\Http\Online;

use App\Base\BaseController;
use Library\Facades\Redis;
use Library\Facades\Request;
use Library\Facades\Response;

/**
 * 踢人下线
 */
class Kickout extends BaseController
{
    private $kickoutToken;
    private $kickoutClientFrom;
    private $kickoutUserId;

    /**
     * 参数检查
     */
    protected function check()
    {
        $this->kickoutToken = trim(Request::post('kickoutToken', ''));
        $this->kickoutClientFrom = trim(Request::post('kickoutClientFrom', ''));
        $this->kickoutUserId = Request::post('kickoutUserId');
        
        // 至少提供一个参数
        if (empty($this->kickoutToken) && empty($this->kickoutClientFrom) && empty($this->kickoutUserId)) {
            throw new \Exception("请提供踢出条件");
        }
    }

    /**
     * 业务处理
     */
    protected function service()
    {
        // 按Token踢出
        if (!empty($this->kickoutToken)) {
            Redis::del("token:{$this->kickoutToken}");
            return Response::success(null, '已踢出指定会话');
        }
        
        // 按用户ID和终端类型踢出
        if (!empty($this->kickoutUserId)) {
            $onlineTokens = Redis::keys('token:*');
            $kicked = 0;
            
            foreach ($onlineTokens as $tokenKey) {
                $tokenData = Redis::get($tokenKey);
                if ($tokenData) {
                    $data = json_decode($tokenData, true);
                    if (isset($data['uid']) && $data['uid'] == $this->kickoutUserId) {
                        // 如果指定了终端类型，只踢出该终端
                        if (!empty($this->kickoutClientFrom)) {
                            if (isset($data['clientFrom']) && $data['clientFrom'] == $this->kickoutClientFrom) {
                                Redis::del($tokenKey);
                                $kicked++;
                            }
                        } else {
                            // 踢出该用户所有会话
                            Redis::del($tokenKey);
                            $kicked++;
                        }
                    }
                }
            }
            
            return Response::success(null, "已踢出 {$kicked} 个会话");
        }
        
        return Response::success(null);
    }
}
