package com.api.basic.service.auth.login;

import cn.binarywang.wx.miniapp.api.WxMaService;
import cn.binarywang.wx.miniapp.bean.WxMaJscode2SessionResult;
import cn.hutool.core.util.StrUtil;
import com.api.basic.data.dto.auth.login.WechatMpDto;
import com.api.basic.data.entity.TbBasicSysUser;
import com.api.basic.data.enums.UserSourceEnum;
import com.api.basic.data.enums.UserStatusEnum;
import com.api.basic.data.po.TbBasicSysUserPo;
import com.api.basic.event.UserRegisterEvent;
import com.api.basic.event.entity.UserRegisterEntity;
import com.api.common.base.Result;
import com.api.common.exception.ServerException;
import com.api.common.utils.FunctionUtil;
import jakarta.servlet.http.HttpServletRequest;
import me.chanjar.weixin.common.error.WxErrorException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 微信小程序登录
 */
@Service("BasicAuthWechatMpLoginServiceImpl")
public class WechatMp extends LoginAbstract {

    @Autowired
    private WxMaService wxMaService;

    /**
     * 参数检查
     *
     * @param dto DTO对象
     */
    public void check(WechatMpDto dto) {
        dto.setCode(StrUtil.trim(dto.getCode()));
    }

    /**
     * 业务主体
     *
     * @param dto DTO对象
     * @return 处理结果
     */
    @Transactional(rollbackFor = Throwable.class)
    public Result<?> service(WechatMpDto dto, HttpServletRequest request) {
        try {
            WxMaJscode2SessionResult sessionResult = wxMaService.getUserService().getSessionInfo(dto.getCode());
            String openid = sessionResult.getOpenid();
            String unionid = sessionResult.getUnionid();
            TbBasicSysUser user = null;
            if(StrUtil.isNotBlank(unionid)) {
                user = tbBasicSysUserDao.getByWechatUnionid(unionid);
            }
            if (user != null) { // 微信unionid已存在
                if(!openid.equals(user.getWechatMpAccountOpenid())) {
                    tbBasicSysUserDao.update(TbBasicSysUserPo.builder()
                                    .whereId(user.getId())
                                    .wechatMpAccountOpenid(openid)
                                    .build());
                }
            } else { // 微信unionid不存在
                user = tbBasicSysUserDao.getByWechatMpAccountOpenid(openid);
                if (user == null) {// 用户不存在，注册
                    Integer userId = registerUser(UserSourceEnum.WECHAT_MINI_REGISTRATION.getCode());
                    if (userId == null) {
                        throw new ServerException("注册失败");
                    }
                    tbBasicSysUserDao.update(TbBasicSysUserPo.builder()
                                    .whereId(userId)
                                    .wechatMpAccountOpenid(openid)
                                    .build());
                    user = tbBasicSysUserDao.getByWechatMpAccountOpenid(openid);

                    eventPublisher.publishEvent(new UserRegisterEvent(this, UserRegisterEntity.builder()
                            .userId(userId)
                            .request(FunctionUtil.getRequest())
                            .build())); // 发布事件
                }
            }

            if (user.getStatus() == UserStatusEnum.DISABLED.getCode()) {
                throw new ServerException("账号已禁用");
            } else if (user.getStatus() == UserStatusEnum.DELETED.getCode()) {
                throw new ServerException("账号已注销");
            }
            return loginSuccess(user, request, dto.getClientFrom());
        } catch (WxErrorException e) {
            return Result.fail("登陆失败");
        }
    }
}
