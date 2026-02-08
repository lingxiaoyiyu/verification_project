<?php

namespace App\Utils;

//微信小程序支付类
class WeixinPay {
    protected $appid; //AppID(小程序ID)
    protected $mch_id; //商户号
    protected $key; //商户秘钥
    protected $openid; //通过小程序秘钥获取
    protected $out_trade_no; //卖家自定义的订单号
    protected $body;
    protected $detail;
    protected $attach;
    protected $total_fee;
    protected $notify_url;
    protected $spbill_create_ip;

    /**
     * WeixinPay constructor.
     */
    function __construct() {
    }

    /**
     * 微信支付
     * @param $appid                小程序appid
     * @param $mch_id               商户号
     * @param $key                  微信商户平台key
     * @param $openid               小程序用户唯一标识
     * @param $out_trade_no         订单编号
     * @param $body                 订单内容
     * @param $total_fee            订单金额
     * @param $notify_url           回调地址
     * @param $spbill_create_ip     用户ip
     * @param $detail               商品详情
     * @param $attach               附加信息
     //appid/商户号/商户平台key/openid/订单号/内容/金额/回调/ip地址/详情/附加内容
     */
    public function pay($appid, $mch_id, $key, $openid, $out_trade_no, $body, $total_fee, $notify_url, $spbill_create_ip, $detail, $attach) {
        $this->appid = $appid;
        $this->mch_id = $mch_id;
        $this->key = $key;
        $this->openid = $openid;
        $this->out_trade_no = $out_trade_no;
        $this->body = $body;
        $this->detail = $detail;
        $this->attach = $attach;
        $this->total_fee = $total_fee;
        $this->notify_url = $notify_url;
        $this->spbill_create_ip = $spbill_create_ip;

        // 统一下单接口
        $unifiedorder = $this->unifiedorder();
        if ($unifiedorder['return_code'] == 'FAIL') {
            return $unifiedorder;
        } else {
            $parameters = array(
                'appId' => $this->appid, //小程序 ID
                'timeStamp' => '' . time() . '', //时间戳
                'nonceStr' => $this->createNoncestr(), //随机串
                'package' => 'prepay_id=' . $unifiedorder['prepay_id'], //数据包
                'signType' => 'MD5' //签名方式
            );
            //签名
            $parameters['paySign'] = $this->getSign($parameters);
            return $parameters;
        }
    }



    // 统一下单接口
    private function unifiedorder() {

        $url = 'https://api.mch.weixin.qq.com/pay/unifiedorder';
        // 这里的参数顺序一定要按下面的，不然可能就一直报商户号此功能未授权等错误
        $parameters = array(
            'appid' => $this->appid,                            // 小程序ID
            'body' => $this->body,                              // 商品描述
            'detail' => $this->detail,
            'attach' => $this->attach,                          //附加数据
            'mch_id' => $this->mch_id,                          // 商户号
            'nonce_str' => $this->createNoncestr(),             // 随机字符串
            'notify_url' => $this->notify_url,                  // 通知地址 确保外网能正常访问
            'openid' => $this->openid,                          // 用户id
            'out_trade_no' => $this->out_trade_no,
            'spbill_create_ip' => $this->spbill_create_ip,      // 终端IP
            'total_fee' => floatval(($this->total_fee) * 100),  // 单位 分
            'trade_type' => 'JSAPI'                             // 交易类型
        );


        //统一下单签名
        $parameters['sign'] = $this->getSign($parameters);

        $xmlData =  $this->arrayToXml($parameters);

        $return  =  $this->xmlToArray($this->postXmlCurl($xmlData, $url, 60));
        return $return;
    }


    private static function postXmlCurl($xml, $url, $second = 30) {
        $ch = curl_init();
        // 设置超时
        curl_setopt($ch, CURLOPT_TIMEOUT, $second);
        curl_setopt($ch, CURLOPT_URL, $url);
        curl_setopt($ch, CURLOPT_SSL_VERIFYPEER, FALSE);
        curl_setopt($ch, CURLOPT_SSL_VERIFYHOST, FALSE); //严格校验
        // 设置 header
        curl_setopt($ch, CURLOPT_HEADER, FALSE);
        // 要求结果为字符串且输出到屏幕上
        curl_setopt($ch, CURLOPT_RETURNTRANSFER, TRUE);
        // post 提交方式
        curl_setopt($ch, CURLOPT_POST, TRUE);
        curl_setopt($ch, CURLOPT_POSTFIELDS, $xml);
        curl_setopt($ch, CURLOPT_CONNECTTIMEOUT, 20);
        curl_setopt($ch, CURLOPT_TIMEOUT, 40);
        set_time_limit(0);
        // 运行 curl
        $data = curl_exec($ch);
        // 返回结果
        if ($data) {
            curl_close($ch);
            return $data;
        } else {
            $error = curl_errno($ch);
            curl_close($ch);
            return "curl 出错，错误码:$error";
        }
    }

    // 作用：格式化参数，签名过程需要使用
    private function formatBizQueryParaMap($paraMap, $urlencode) {
        $buff = "";
        ksort($paraMap);
        foreach ($paraMap as $k => $v) {
            if ($v) {
                if ($urlencode) {
                    $v = urlencode($v);
                }
                $buff .= $k . "=" . $v . "&";
            }
        }
        $reqPar = '';
        if (strlen($buff) > 0) {
            $reqPar = substr($buff, 0, strlen($buff) - 1);
        }
        return $reqPar;
    }


    // 作用：生成签名
    private function getSign($Obj) {
        foreach ($Obj as $k => $v) {
            $Parameters[$k] = $v;
        }
        // 签名步骤一：按字典序排序参数
        ksort($Parameters);
        $String = $this->formatBizQueryParaMap($Parameters, false);
        // 签名步骤二：在 string 后加入 KEY
        $String = $String . "&key=" . $this->key;
        // 签名步骤三：MD5 加密
        $String = md5($String);
        // 签名步骤四：所有字符转为大写
        $result_ = strtoupper($String);
        return $result_;
    }



    // 作用：产生随机字符串，不长于 32 位
    private function createNoncestr($length = 32) {
        $chars = "abcdefghijklmnopqrstuvwxyz0123456789";
        $str = "";
        for ($i = 0; $i < $length; $i++) {
            $str .= substr($chars, mt_rand(0, strlen($chars) - 1), 1);
        }
        return $str;
    }


    // 数组转换成 xml
    private function arrayToXml($arr) {
        $xml = "<xml>
                   <appid>" . $arr['appid'] . "</appid>
                   <body>" . $arr['body'] . "</body>
                   <detail>" . $arr['detail'] . "</detail>
                   <attach>" . $arr['attach'] . "</attach>
                   <mch_id>" . $arr['mch_id'] . "</mch_id>
                   <nonce_str>" . $arr['nonce_str'] . "</nonce_str>
                   <notify_url>" . $arr['notify_url'] . "</notify_url>
                   <openid>" . $arr['openid'] . "</openid>
                   <out_trade_no>" . $arr['out_trade_no'] . "</out_trade_no>
                   <spbill_create_ip>" . $arr['spbill_create_ip'] . "</spbill_create_ip>
                   <total_fee>" . $arr['total_fee'] . "</total_fee>
                   <trade_type>" . $arr['trade_type'] . "</trade_type>
                   <sign>" . $arr['sign'] . "</sign>
                </xml>";

        return $xml;
    }


    // xml 转换成数组
    private function xmlToArray($xml) {
        // 禁止引用外部 xml 实体
        // libxml_disable_entity_loader(true);
        $xmlstring = simplexml_load_string($xml, 'SimpleXMLElement', LIBXML_NOCDATA);
        $val = json_decode(json_encode($xmlstring), true);
        return $val;
    }

    public function checkSign($data, $key) {
        $this->key = $key;
        $sign = $this->getSign($this->paraFilter($data));
        if ($data['sign'] == $sign) {
            return true;
        } else {
            return false;
        }
    }

    private function paraFilter($para) {
        $para_filter = array();
        foreach ($para as $key => $val) {

            if ($key == "sign" || $key == "sign_type" || $val == "") {
                continue;
            } else {
                $para_filter[$key] = $para[$key];
            }
        }
        return $para_filter;
    }

    // 申请退款
    public function refund($appid, $mch_id, $out_trade_no, $total_fee, $notify_url){
        $this->appid = $appid;
        $this->mch_id = $mch_id;
        $this->out_trade_no = $out_trade_no;
        $this->total_fee = $total_fee;
        $this->notify_url = $notify_url;

        $url = 'https://api.mch.weixin.qq.com/secapi/pay/refund';

        // 这里的参数顺序一定要按下面的，不然可能就一直报商户号此功能未授权等错误
        $parameters = array(
            'appid' => $this->appid,                            // 小程序ID
            'mch_id' => $this->mch_id,                          // 商户号
            'nonce_str' => $this->createNoncestr(),             // 随机字符串
            'out_trade_no'=> $this->out_trade_no,                                // 商户系统内部订单号
            'out_refund_no'=> $this->out_trade_no,                                // 退款单号
            'total_fee' => floatval(($this->total_fee) * 100),  // 订单总金额，单位为分，只能为整数
            'refund_fee' => floatval(($this->total_fee) * 100),  // 退款总金额，订单总金额，单位为分，只能为整数
            'notify_url' => $this->notify_url,          //'https://shop.gdpress.cn/syw_jingzhun/index.php/Api/xiaochengxu/notify_url_api', // 通知地址 确保外网能正常访问
        );

        //echo '<pre>';var_dump($parameters);  die;

        // 退款签名
        $parameters['sign'] = $this->getSign($parameters);

        $xmlData =  $this->arrayToXml($parameters);

        $return  =  $this->xmlToArray($this->postXmlCurl($xmlData, $url, 60));
        return $return;
    }
}
