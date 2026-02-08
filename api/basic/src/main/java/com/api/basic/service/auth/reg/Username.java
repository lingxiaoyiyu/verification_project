package com.api.basic.service.auth.reg;

import cn.hutool.core.util.StrUtil;
import cn.hutool.crypto.digest.DigestUtil;
import com.api.basic.data.dto.auth.reg.UsernameDto;
import com.api.basic.data.entity.TbBasicSysUser;
import com.api.basic.data.enums.UserSourceEnum;
import com.api.basic.data.po.TbBasicSysUserPo;
import com.api.basic.event.UserRegisterEvent;
import com.api.basic.event.entity.UserRegisterEntity;
import com.api.common.base.Result;
import com.api.common.exception.ServerException;
import com.api.common.utils.FunctionUtil;
import jakarta.annotation.Resource;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Map;

/**
 * 用户名密码登录
 */
@Service("BasicAuthRegUsernameRegServiceImpl")
public class Username extends RegAbstract {

    @Resource
    private ApplicationEventPublisher eventPublisher;

    /**
     * 参数检查
     */
    public void check(UsernameDto dto) {
        dto.setUsername(StrUtil.trim(dto.getUsername()));
        dto.setPassword(StrUtil.trim(StrUtil.trim(dto.getPassword())));

        if (tbBasicSysUserDao.cnt(TbBasicSysUserPo.builder().whereUsername(dto.getUsername()).build()) > 0) {
            throw new ServerException("账号已存在");
        }

        if (StrUtil.isNotBlank(dto.getEmail()) && tbBasicSysUserDao.cnt(TbBasicSysUserPo.builder().whereEmail(dto.getEmail()).build()) > 0) {
            throw new ServerException("邮箱已存在");
        }

        if (StrUtil.isNotBlank(dto.getNickName()) && tbBasicSysUserDao.cnt(TbBasicSysUserPo.builder().whereNickName(dto.getNickName()).build()) > 0) {
            throw new ServerException("昵称已存在");
        }

        if (StrUtil.isNotBlank(dto.getPhoneNumber()) && tbBasicSysUserDao.cnt(TbBasicSysUserPo.builder().wherePhoneNumber(dto.getPhoneNumber()).build()) > 0) {
            throw new ServerException("手机号已存在");
        }
    }

    /**
     * 业务主体
     *
     * @return 处理结果
     */

    public Result<?> service(UsernameDto dto) {

        Result<Map<String, Object>> result = (Result<Map<String, Object>>) handleService(dto);

        Integer userId = tbBasicSysUserDao.get(TbBasicSysUserPo.builder().whereUsername(dto.getUsername()).build()).getId();
        eventPublisher.publishEvent(new UserRegisterEvent(this, UserRegisterEntity.builder()
                                                                        .userId(userId)
                                                                        .request(FunctionUtil.getRequest())
                                                                        .build())); // 发布事件
        return result;
    }

    @Transactional(rollbackFor = Throwable.class)
    public Result<?> handleService(UsernameDto dto) {
        String password = dto.getPassword();
        Integer userId = registerUser(UserSourceEnum.EMAIL_REGISTRATION.getCode());
        if (userId == null) {
            throw new ServerException("注册失败");
        }

        TbBasicSysUserPo updatePo = new TbBasicSysUserPo();
        updatePo.setWhereId(userId);
        updatePo.setUsername(dto.getUsername());
        updatePo.setPassword(DigestUtil.md5Hex(password));
        updatePo.setUpdatedUserId(userId);
        if (StrUtil.isNotBlank(dto.getEmail())) {
            updatePo.setEmail(dto.getEmail());
        }
        if (StrUtil.isNotBlank(dto.getPhoneNumber())) {
            updatePo.setPhoneNumber(dto.getPhoneNumber());
        }
        if (StrUtil.isNotBlank(dto.getNickName())) {
            updatePo.setNickName(dto.getNickName());
        }

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
