<?php

namespace App\Utils;

use Library\Core\Config;
use Library\Facades\Redis;

// 微信相关
class Wechat {
    
    /**
     * 获取网页授权用户信息
     *
     * @param [type] $code
     * @return array 用户信息
     */
    public function getOauth2UserInfo($code){
        $appid = Config::get('wx.officialAccount.app_id');
        $secret = Config::get('wx.officialAccount.secret');
        if($appid && $secret) {
            $token_url = "https://api.weixin.qq.com/sns/oauth2/access_token?appid={$appid}&secret={$secret}&code={$code}&grant_type=authorization_code";
            $data = FunctionUtil::curlGet($token_url);
            $data = json_decode($data, true);
            if (empty($tokenData['errcode'])) {
                return $data;
            }     
        }
        return [];
    }

    /**
     * 获取网页授权Accesstoken
     *
     * @param [type] $code 网页授权code
     * @return 微信网页授权accesstoken
     */
    public function getOauth2AcessToken($code){
        $userInfo = $this->getOauth2UserInfo($code);
        if ($userInfo && isset($userInfo['access_token'])){
            return $userInfo['access_token'];
        }
        return "";
    }

    
    /**
     * 获取微信公众号公众号的全局唯一接口调用凭据:accessToken
     */
    public function getOfficialAccountAccessToken(){
        $accessToken = Redis::get('wechatAccessToken');
        if (!$accessToken) {
            $appid = Config::get('wx.officialAccount.app_id');
            $secret = Config::get('wx.officialAccount.secret');;
            $token_url = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid={$appid}&secret={$secret}";
            $data = FunctionUtil::curlGet($token_url);
            $data = json_decode($data, true);
            if (empty($tokenData['errcode'])) {
                $accessToken = $data['access_token'];
                Redis::setex('wechatAccessToken', 7000, $data['access_token']);
            } 
        }
        return $accessToken;
    }

    /**
     * 获取微信公众号jsapi_ticket。jsapi_ticket是公众号用于调用微信JS接口的临时票据。
     */
    public function getOfficialAccountTicket(){
        $ticket = Redis::get('wechatTicket');
        if (!$ticket) {
            $accessToken = $this->getOfficialAccountAccessToken();
            $getticketUrl = "https://api.weixin.qq.com/cgi-bin/ticket/getticket?access_token={$accessToken}&type=jsapi";
            $ticketData = FunctionUtil::curlGet($getticketUrl);
            $ticketData = json_decode($ticketData, true);
            $ticket = $ticketData['ticket']; // 7200秒
            Redis::setex('wechatTicket', 7000, $ticket);
        }

        return $ticket;
    }

    /**
     * 上传永久素材-图片
     *
     * @param [type] $filePath 要上传文件的完整路径（包含文件名）
     * @return 永久素材的url地址
     */
    public function uploadMediaImg($filePath){
        if (file_exists($filePath) == false) {
            return "";
        }
        $accessToken = $this->getOfficialAccountAccessToken();
        $url = 'https://api.weixin.qq.com/cgi-bin/media/uploadimg?access_token='.$accessToken;
        $res = FunctionUtil::curlPostFile($url, [], $filePath);
        try {
            $res = json_decode($res, true);
            return $res['url'];
        } catch (\Throwable $th) {
            throw $th;
            return "";
        }
    }
}
