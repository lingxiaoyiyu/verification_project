<?php

namespace App\Modules\Wechat\Controller\Http\OfficialAccount;

use App\Base\BaseController;
use App\Utils\FunctionUtil;
use App\Modules\Utils\Weixin;
use Library\Facades\Redis;

/**
 * 获取微信公众号Ticket
 */
class Ticket extends BaseController
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
        $weixinObj = new Weixin();
        $accessToken = $weixinObj->getOfficialAccountAccessToken();
        $getticketUrl = "https://api.weixin.qq.com/cgi-bin/ticket/getticket?access_token={$accessToken}&type=jsapi";
        $ticketData = FunctionUtil::curlGet($getticketUrl);
        $ticketData = json_decode($ticketData, true);
        $ticket = $ticketData['ticket']; // 7200秒
        Redis::setex('wechatTicket', 7000, $ticket);
        $this->result['data']['ticket'] = $ticket;
        return $this->result;
    }
}
