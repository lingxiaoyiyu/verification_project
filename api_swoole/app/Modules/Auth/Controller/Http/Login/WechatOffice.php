<?php

namespace App\Modules\Auth\Controller\Http\Login;

use App\Base\BaseController;
use Library\Facades\DB;
use Library\Facades\Redis;
use Library\Facades\Request;
use Library\Facades\Response;
use Library\Facades\Validate;

/**
 * 微信公众号登录
 */
class WechatOffice extends BaseController
{
    private $code;
    private $clientFrom;

    /**
     * 参数检查
     */
    protected function check()
    {
        $this->code = trim(Request::get('code', ''));
        $this->clientFrom = Request::get('clientFrom', 'other');
    }

    /**
     * 业务处理
     */
    protected function service()
    {
        // 获取微信公众号配置
        $appId = env('WECHAT_OFFICE_APPID', '');
        $appSecret = env('WECHAT_OFFICE_SECRET', '');
        
        if (empty($appId) || empty($appSecret)) {
            throw new \Exception("微信公众号配置不完整");
        }
        
        // 如果没有code，返回授权URL
        if (empty($this->code)) {
            $redirectUri = urlencode(Request::url());
            $authUrl = "https://open.weixin.qq.com/connect/oauth2/authorize?appid={$appId}&redirect_uri={$redirectUri}&response_type=code&scope=snsapi_base&state=STATE#wechat_redirect";
            return Response::success([
                'authUrl' => $authUrl
            ], '请先授权');
        }
        
        // 通过code获取access_token和openid
        $tokenUrl = "https://api.weixin.qq.com/sns/oauth2/access_token?appid={$appId}&secret={$appSecret}&code={$this->code}&grant_type=authorization_code";
        $tokenResult = $this->httpGet($tokenUrl);
        
        if (!$tokenResult || isset($tokenResult['errcode'])) {
            throw new \Exception("获取微信access_token失败: " . ($tokenResult['errmsg'] ?? '未知错误'));
        }
        
        $openid = $tokenResult['openid'];
        $accessToken = $tokenResult['access_token'];
        
        // 根据openid查询用户
        $user = DB::table('tb_basic_sys_user')
            ->where('wechat_official_account_openid', $openid)
            ->first();
        
        // 用户不存在则自动注册
        if (!$user) {
            $userId = $this->registerUser($openid);
            $user = DB::table('tb_basic_sys_user')
                ->where('id', $userId)
                ->first();
        }
        
        // 检查用户状态
        if ($user['status'] == 1) {
            throw new \Exception("账号已禁用");
        }
        
        if ($user['status'] == 2) {
            throw new \Exception("账号已注销");
        }
        
        // 生成Token
        $token = $this->generateToken($user['id']);
        
        // 存储Token到Redis
        Redis::setex("token:{$token}", 7200, json_encode([
            'uid' => $user['id'],
            'username' => $user['username'],
            'wechatOpenid' => $openid,
            'clientFrom' => $this->clientFrom
        ]));
        
        return Response::success([
            'accessToken' => $token
        ], '登录成功');
    }

    /**
     * HTTP GET请求
     */
    private function httpGet($url)
    {
        $ch = curl_init();
        curl_setopt($ch, CURLOPT_URL, $url);
        curl_setopt($ch, CURLOPT_RETURNTRANSFER, true);
        curl_setopt($ch, CURLOPT_SSL_VERIFYPEER, false);
        curl_setopt($ch, CURLOPT_TIMEOUT, 30);
        $response = curl_exec($ch);
        curl_close($ch);
        
        return json_decode($response, true);
    }

    /**
     * 自动注册用户
     */
    private function registerUser($openid)
    {
        $username = $this->generateRandomString(8);
        $password = md5('123456');
        $inviteCode = $this->generateRandomString(6);
        
        $userId = DB::table('tb_basic_sys_user')->insertGetId([
            'username' => $username,
            'password' => $password,
            'wechat_official_account_openid' => $openid,
            'invite_code' => $inviteCode,
            'source' => 5, // 微信公众号注册
            'status' => 0,
            'created_at' => date('Y-m-d H:i:s'),
            'updated_at' => date('Y-m-d H:i:s')
        ]);
        
        return $userId;
    }

    /**
     * 生成随机字符串
     */
    private function generateRandomString($length)
    {
        $chars = 'abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789';
        $str = '';
        for ($i = 0; $i < $length; $i++) {
            $str .= $chars[mt_rand(0, strlen($chars) - 1)];
        }
        return $str;
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
