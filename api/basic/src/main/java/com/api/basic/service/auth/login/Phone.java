package com.api.basic.service.auth.login;

import cn.hutool.core.util.StrUtil;
import com.api.basic.data.dto.auth.login.PhoneDto;
import com.api.basic.data.entity.TbBasicSysUser;
import com.api.basic.data.enums.UserStatusEnum;
import com.api.basic.data.po.TbBasicSysUserPo;
import com.api.common.base.Result;
import com.api.common.exception.ServerException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 手机号验证码登录
 */
@Service("BasicAuthPhoneLoginServiceImpl")
public class Phone extends LoginAbstract {

    /**
     * 参数检查
     *
     * @param dto DTO对象
     */
    public void check(PhoneDto dto) {
        dto.setPhoneNumber(StrUtil.trim(dto.getPhoneNumber()));

        String verCode = redisCache.getCacheObject("phone_code_" + dto.getPhoneNumber());
        if (StrUtil.isBlank(verCode)) {
            throw new ServerException("验证码未发送或已超时");
        } else if (StrUtil.isBlank(verCode) || !verCode.equals(dto.getVerCode())) {
            throw new ServerException("验证码错误");
        }
    }

    /**
     * 业务主体
     *
     * @param dto DTO对象
     * @return 处理结果
     */
    @Transactional(rollbackFor = Throwable.class)
    public Result<?> service(PhoneDto dto, HttpServletRequest request) {
        TbBasicSysUserPo queryPo = new TbBasicSysUserPo();
        queryPo.setWhereLikePhoneNumber(dto.getPhoneNumber());
        TbBasicSysUser user = tbBasicSysUserDao.get(queryPo);
        if (user == null) {
            throw new ServerException("该手机号未注册");
        } else if (user.getStatus() == UserStatusEnum.DISABLED.getCode()) {
            throw new ServerException("账号已禁用");
        } else if (user.getStatus() == UserStatusEnum.DELETED.getCode()) {
            throw new ServerException("账号已注销");
        }
        return loginSuccess(user, request, dto.getClientFrom());
    }
}
