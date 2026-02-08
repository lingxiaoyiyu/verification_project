<?php

namespace App\Modules\Auth\Controller\Http\Login;

use App\Base\BaseController;
use Library\Facades\DB;
use Library\Facades\Redis;
use Library\Facades\Request;
use Library\Facades\Response;
use Library\Facades\Validate;

/**
 * 微信小程序登录
 */
class WechatMp extends BaseController
{
    private $code;
    private $clientFrom;

    /**
     * 参数检查
     */
    protected function check()
    {
        Validate::validate([
            'code' => ['text' => 'code', 'rules' => ['required']],
        ]);
        
        $this->code = trim(Request::get('code'));
        $this->clientFrom = Request::get('clientFrom', 'miniapp');
    }

    /**
     * 业务处理
     */
    protected function service()
    {
        // 获取微信小程序配置
        $appId = env('WECHAT_MP_APPID', '');
        $appSecret = env('WECHAT_MP_SECRET', '');
        
        if (empty($appId) || empty($appSecret)) {
            throw new \Exception("微信小程序配置不完整");
        }
        
        // 通过code获取session信息
        $sessionUrl = "https://api.weixin.qq.com/sns/jscode2session?appid={$appId}&secret={$appSecret}&js_code={$this->code}&grant_type=authorization_code";
        $sessionResult = $this->httpGet($sessionUrl);
        
        if (!$sessionResult || isset($sessionResult['errcode'])) {
            throw new \Exception("获取微信session失败: " . ($sessionResult['errmsg'] ?? '未知错误'));
        }
        
        $openid = $sessionResult['openid'];
        $unionid = $sessionResult['unionid'] ?? null;
        
        $user = null;
        
        // 先通过unionid查询用户
        if (!empty($unionid)) {
            $user = DB::table('tb_basic_sys_user')
                ->where('wechat_unionid', $unionid)
                ->first();
            
            // 如果找到用户但小程序openid不一致，更新openid
            if ($user && $user['wechat_mp_account_openid'] !== $openid) {
                DB::table('tb_basic_sys_user')
                    ->where('id', $user['id'])
                    ->update([
                        'wechat_mp_account_openid' => $openid,
                        'updated_at' => date('Y-m-d H:i:s')
                    ]);
            }
        }
        
        // 如果unionid未找到用户，通过openid查询
        if (!$user) {
            $user = DB::table('tb_basic_sys_user')
                ->where('wechat_mp_account_openid', $openid)
                ->first();
        }
        
        // 用户不存在则自动注册
        if (!$user) {
            $userId = $this->registerUser($openid, $unionid);
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
    private function registerUser($openid, $unionid = null)
    {
        $username = $this->generateRandomString(8);
        $password = md5('123456');
        $inviteCode = $this->generateRandomString(6);
        
        $data = [
            'username' => $username,
            'password' => $password,
            'wechat_mp_account_openid' => $openid,
            'invite_code' => $inviteCode,
            'source' => 6, // 微信小程序注册
            'status' => 0,
            'created_at' => date('Y-m-d H:i:s'),
            'updated_at' => date('Y-m-d H:i:s')
        ];
        
        if (!empty($unionid)) {
            $data['wechat_unionid'] = $unionid;
        }
        
        $userId = DB::table('tb_basic_sys_user')->insertGetId($data);
        
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
