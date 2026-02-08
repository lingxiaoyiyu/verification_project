package com.api.basic.service.auth.reg;

import cn.hutool.core.util.StrUtil;
import cn.hutool.crypto.digest.DigestUtil;
import com.api.basic.data.dto.auth.reg.EmailDto;
import com.api.basic.data.entity.TbBasicSysUser;
import com.api.basic.data.enums.UserSourceEnum;
import com.api.basic.data.po.TbBasicSysUserPo;
import com.api.basic.event.UserRegisterEvent;
import com.api.basic.event.entity.UserRegisterEntity;
import com.api.common.base.Result;
import com.api.common.exception.ServerException;
import com.api.common.utils.FunctionUtil;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Map;

/**
 * 邮箱验证码登录
 */
@Service("BasicAuthRegEmailRegServiceImpl")
public class Email extends RegAbstract {

    @Resource
    private ApplicationEventPublisher eventPublisher;

    /**
     * 参数检查
     *
     * @param dto DTO对象
     */
    public void check(EmailDto dto) {
        dto.setEmail(StrUtil.trim(dto.getEmail()));
        dto.setPassword(StrUtil.trim(dto.getPassword()));
        dto.setVerCode(StrUtil.trim(dto.getVerCode()));
        dto.setInviteCode(StrUtil.trim(dto.getInviteCode()));

        String cacheVerifyCode = redisCache.getCacheObject(dto.getVerCodeKey());
        if (StrUtil.isBlank(cacheVerifyCode)) {
            throw new ServerException("验证码已过期");
        }
        if (!StrUtil.equals(cacheVerifyCode, dto.getVerCode())) {
            throw new ServerException("验证码错误");
        }

        TbBasicSysUserPo queryPo = new TbBasicSysUserPo();
        queryPo.setWhereEmail(dto.getEmail());
        if (tbBasicSysUserDao.cnt(queryPo) > 0) {
            throw new ServerException("账号已存在");
        }
        queryPo.setWhereEmail(null);

        if ((StrUtil.isNotBlank(dto.getInviteCode()))) {
            queryPo.setWhereInviteCode(dto.getInviteCode());
            if (tbBasicSysUserDao.cnt(queryPo) == 0) {
                throw new ServerException("邀请码错误");
            }
        }
    }

    /**
     * 业务主体
     *
     * @param dto DTO对象
     * @return 处理结果
     */

    public Result<?> service(EmailDto dto, HttpServletRequest request) {
        Result<Map<String, Object>> result = (Result<Map<String, Object>>) handleService(dto);

        Integer userId = tbBasicSysUserDao.get(TbBasicSysUserPo.builder().whereEmail(dto.getEmail()).build()).getId();
        eventPublisher.publishEvent(new UserRegisterEvent(this, UserRegisterEntity.builder()
                .userId(userId)
                .request(FunctionUtil.getRequest())
                .build())); // 发布事件
        return result;
    }

    @Transactional(rollbackFor = Throwable.class)
    public Result<?> handleService(EmailDto dto) {
        Integer userId = registerUser(UserSourceEnum.EMAIL_REGISTRATION.getCode());
        if (userId == null) {
            throw new ServerException("注册失败");
        }

        TbBasicSysUserPo updatePo = new TbBasicSysUserPo();
        updatePo.setWhereId(userId);
        updatePo.setEmail(dto.getEmail());
        updatePo.setPassword(DigestUtil.md5Hex(dto.getPassword()));
        updatePo.setUpdatedUserId(userId);
        if (StrUtil.isNotBlank(dto.getInviteCode())) { // 邀请码不为空，设置邀请者，即师傅、上级
            TbBasicSysUserPo queryPo = new TbBasicSysUserPo();
            queryPo.setWhereInviteCode(dto.getInviteCode());
            TbBasicSysUser inviter = tbBasicSysUserDao.get(queryPo);
            if (inviter != null) {
                updatePo.setInviter(inviter.getId());
            }
        }
        if (tbBasicSysUserDao.update(updatePo) != 1) {
            throw new ServerException("注册失败");
        }

        // 获取用户信息
        TbBasicSysUserPo queryPo = new TbBasicSysUserPo();
        queryPo.setWhereId(userId);
        TbBasicSysUser user = tbBasicSysUserDao.get(queryPo);
        return registerSuccess(user);
    }
}
