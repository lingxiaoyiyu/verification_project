package com.api.example.wechatpay;

import com.api.basic.dao.TbBasicSysUserDao;
import com.api.common.base.Result;
import com.api.common.exception.ServerException;
import com.api.common.utils.FunctionUtil;
import com.github.binarywang.wxpay.bean.request.WxPayUnifiedOrderRequest;
import com.github.binarywang.wxpay.exception.WxPayException;
import com.github.binarywang.wxpay.service.WxPayService;
import jakarta.servlet.http.HttpServletRequest;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.Map;

/**
 * 示例：微信支付
 */
public class Payinfo {

//    @Autowired
    private WxPayService wxPayService;
    private TbBasicSysUserDao tbBasicSysUserDao;

    /**
     * 业务主体
     *
     * @return 处理结果
     */
    @Transactional(rollbackFor = Throwable.class)
    public Result<?> service(HttpServletRequest request) {
        try {
//            TbLoveOrder order = tbLoveOrderDao.getById(dto.getOrderId());
//            TbBasicSysUser user = tbBasicSysUserDao.getById(getUserDetails().getId());

            WxPayUnifiedOrderRequest payRequest = new WxPayUnifiedOrderRequest();
//            payRequest.setOpenid(user.getWechatOfficialAccountOpenid());
//            payRequest.setOutTradeNo(order.getOrderNo());
            payRequest.setBody("9.9元");
            payRequest.setDetail("9.9元");
            payRequest.setAttach("9.9元");
            payRequest.setSpbillCreateIp(FunctionUtil.getIpAddress());
//            payRequest.setTotalFee(order.getAmount());
            payRequest.setTradeType("JSAPI");

            // 调用微信支付统一下单接口
            com.github.binarywang.wxpay.bean.result.WxPayUnifiedOrderResult unifiedOrderResult =
                    wxPayService.unifiedOrder(payRequest);

            // 构造返回参数
            Map<String, String> data = new HashMap<>();
            data.put("appId", unifiedOrderResult.getAppid());
            data.put("timeStamp", String.valueOf(System.currentTimeMillis() / 1000));
            data.put("nonceStr", unifiedOrderResult.getNonceStr());
            data.put("package", "prepay_id=" + unifiedOrderResult.getPrepayId());
            data.put("signType", "MD5");

            // 按参数名ASCII字典序排序并拼接字符串
            String stringA = String.format("appId=%s&nonceStr=%s&package=%s&signType=%s&timeStamp=%s",
                    data.get("appId"),
                    data.get("nonceStr"),
                    data.get("package"),
                    data.get("signType"),
                    data.get("timeStamp"));

            // 拼接API密钥并生成MD5签名
            String stringSignTemp = stringA + "&key=" + wxPayService.getConfig().getMchKey();
            String paySign = DigestUtils.md5Hex(stringSignTemp).toUpperCase();

            data.put("paySign", paySign);

            return Result.ok("处理成功", data);
        } catch (WxPayException e) {
            throw new ServerException("微信支付统一下单失败", e);
        }
    }
}
