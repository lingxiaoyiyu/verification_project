package com.api.example.wechatpay;

import com.api.common.exception.ServerException;
import com.github.binarywang.wxpay.bean.notify.WxPayOrderNotifyResult;
import com.github.binarywang.wxpay.exception.WxPayException;
import com.github.binarywang.wxpay.service.WxPayService;
import org.springframework.transaction.annotation.Transactional;

/**
 * 示例：微信回复回调
 */
public class PayCallback {

//    @Autowired
    private WxPayService wxPayService;

    /**
     * 业务主体
     *
     * @return 处理结果
     */
    @Transactional(rollbackFor = Throwable.class)
    public String service(String xmlData) {
        try {
            System.out.println("支付回调 xmlData："+xmlData);

            // 解析微信支付回调通知
            WxPayOrderNotifyResult notifyResult = wxPayService.parseOrderNotifyResult(xmlData);

//            TbLoveOrder order = tbLoveOrderDao.getByOrderNo();
//            if (order == null) {
//                throw new ServerException("订单不存在");
//            }
//
//            // 更新订单状态
//            TbLoveOrderPo orderPo = new TbLoveOrderPo();
//            orderPo.setWhereOrderNo(notifyResult.getOutTradeNo());
//            orderPo.setTransactionId(notifyResult.getTransactionId());
//            orderPo.setWxPayInfoCallback(xmlData);
//            orderPo.setStatus("SUCCESS".equals(notifyResult.getResultCode()) ? 2 : 4);
//            tbLoveOrderDao.update(orderPo);

            // 返回成功响应
            return "<xml><return_code><![CDATA[SUCCESS]]></return_code><return_msg><![CDATA[OK]]></return_msg></xml>";
        } catch (WxPayException|ServerException e) {
            e.printStackTrace();
            return "<xml><return_code><![CDATA[FAIL]]></return_code><return_msg><![CDATA[ERROR]]></return_msg></xml>";
        }
    }
}
