<?php

namespace App\Modules\Auth\Controller\Http;

use App\Base\BaseController;
use Library\Facades\DB;
use Library\Facades\Redis;
use Library\Facades\Request;
use Library\Facades\Response;

/**
 * 刷新Token
 */
class Refresh extends BaseController
{
    private $currentToken;

    /**
     * 参数检查
     */
    protected function check()
    {
        $this->currentToken = Request::header('Authorization');
        if ($this->currentToken) {
            $this->currentToken = str_replace('Bearer ', '', $this->currentToken);
        }
        
        if (!$this->currentToken) {
            throw new \Exception("Token不能为空");
        }
    }

    /**
     * 业务处理
     */
    protected function service()
    {
        // 获取当前Token信息
        $tokenInfo = Redis::get("token:{$this->currentToken}");
        if ($tokenInfo === false || $tokenInfo === null) {
            throw new \Exception("Token已过期，请重新登录");
        }
        
        $tokenData = json_decode($tokenInfo, true);
        $userId = $tokenData['uid'];
        
        // 删除旧Token
        Redis::del("token:{$this->currentToken}");
        
        // 生成新Token
        $newToken = $this->generateToken($userId);
        
        // 存储新Token
        Redis::setex("token:{$newToken}", 7200, json_encode($tokenData));
        
        return Response::success([
            'accessToken' => $newToken
        ], '刷新成功');
    }

    /**
     * 生成Token
     */
    private function generateToken($userId)
    {
        $payload = [
            'uid' => $userId,
            'iat' => time(),
            'exp' => time() + 7200
        ];
        return base64_encode(json_encode($payload)) . '.' . md5(json_encode($payload) . 'secret_key');
    }
}
