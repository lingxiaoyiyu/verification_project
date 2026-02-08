<?php

namespace App\Modules\Wechat\Controller\Http;

use App\Base\BaseController;
use App\Utils\FunctionUtil;
use Library\Core\Config;
use Library\Facades\Redis;

/**
 * 获取微信accessToken
 */
class AccessToken extends BaseController
{

    /**
     * 参数检查
     */
    protected function check()
    {
    }

    /**
     * 业务主体
     */
    protected function service()
    {
        $appid = Config::get('wx.officialAccount.app_id');
        $secret = Config::get('wx.officialAccount.secret');
        $token_url = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid={$appid}&secret={$secret}";
        $data = FunctionUtil::curlGet($token_url);
        $data = json_decode($data, true);
        $openid = '';
        if (empty($tokenData['errcode'])) {
            $this->result['data']['access_token'] = $data['access_token'];
            Redis::setex('wechatAccessToken', 7000, $data['access_token']);
        } else {
            $this->fail("网页授权错误，无法获取openid");
        }    
        return $this->result;
    }
}
