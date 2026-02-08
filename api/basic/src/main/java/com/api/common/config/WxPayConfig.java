package com.api.common.config;

import com.github.binarywang.wxpay.service.WxPayService;
import com.github.binarywang.wxpay.service.impl.WxPayServiceImpl;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;

@Configuration
@Lazy
public class WxPayConfig {

    @Value("${wx.pay.appId}")
    private String appId;
    @Value("${wx.pay.mchId}")
    private String mchId;
    @Value("${wx.pay.mchKey}")
    private String mchKey;
    @Value("${wx.pay.notifyUrl}")
    private String notifyUrl;

    @Bean
    public WxPayService wxPayService() {
        WxPayService wxPayService = new WxPayServiceImpl();
        com.github.binarywang.wxpay.config.WxPayConfig payConfig = new com.github.binarywang.wxpay.config.WxPayConfig();
        // 设置微信支付的相关参数
        payConfig.setAppId(appId);
        payConfig.setMchId(mchId);
        payConfig.setMchKey(mchKey);
        payConfig.setNotifyUrl(notifyUrl);
        wxPayService.setConfig(payConfig);
        return wxPayService;
    }
}

