package com.api.basic.service.auth.login;

import cn.hutool.core.util.StrUtil;
import com.api.basic.data.dto.auth.login.WechatOfficeDto;
import com.api.common.base.Result;
import com.api.common.exception.ServerException;
import jakarta.servlet.http.HttpServletRequest;
import me.chanjar.weixin.common.api.WxConsts;
import me.chanjar.weixin.mp.api.WxMpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.Map;

/**
 * 微信公众号登录
 */
@Service("BasicAuthWechatOfficeLoginServiceImpl")
public class WechatOffice extends LoginAbstract {

    @Autowired
    private WxMpService wxMpService;

    /**
     * 参数检查
     *
     * @param dto DTO对象
     */
    public void check(WechatOfficeDto dto) {
        dto.setCode(StrUtil.trim(dto.getCode()));
    }

    /**
     * 业务主体
     *
     * @param dto DTO对象
     * @return 处理结果
     */
    @Transactional(rollbackFor = Throwable.class)
    public Result<?> service(WechatOfficeDto dto, HttpServletRequest request) {
        try {
            // 构造网页授权url
            String url = "";
            wxMpService.getOAuth2Service().buildAuthorizationUrl(url, WxConsts.OAuth2Scope.SNSAPI_BASE, "");

//            WxOAuth2AccessToken wxOAuth2AccessToken = wxMpService.getOAuth2Service().getAccessToken(dto.getCode());
            String accessToken = wxMpService.getAccessToken();
            Map<String, Object> data = new HashMap<>();
            data.put("accessToken", accessToken);
//            data.put("accessToken", wxOAuth2AccessToken.getAccessToken());
//            data.put("openId", wxOAuth2AccessToken.getOpenId());
            return Result.ok(data);
//            TbBasicSysUserPo queryPo = new TbBasicSysUserPo();
//            queryPo.setWhereWechatOfficialAccountOpenid(wxOAuth2AccessToken.getOpenId());
//            TbBasicSysUser user =  tbUserDao.get(queryPo);
//            if (user == null) {
//                Integer userId = registerUser(UserSourceEnum.WECHAT_OFFICIAL_ACCOUNT_REGISTRATION.getCode());
//                if (userId == null) {
//                    throw new ServerException("注册失败");
//                }
//                user = tbUserDao.get(queryPo);
//            }
//            return loginSuccess(user, request);
        } catch (Exception e) {
            throw new ServerException("获取微信accessToken失败", e);
        }


//        return loginSuccess(user, request);
    }
}
