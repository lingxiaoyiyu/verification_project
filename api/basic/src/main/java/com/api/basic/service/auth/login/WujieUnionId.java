package com.api.basic.service.auth.login;

import com.api.basic.data.dto.auth.login.WujieUnionidDto;
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
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 无界unionid登录
 */
@Service("BasicAuthWujieUnionIdServiceImpl")
public class WujieUnionId extends LoginAbstract {

    /**
     * 业务主体
     *
     * @param dto DTO对象
     * @return 处理结果
     */
    @Transactional(rollbackFor = Throwable.class)
    public Result<?> service(WujieUnionidDto dto, HttpServletRequest request) {
        TbBasicSysUserPo queryPo = new TbBasicSysUserPo();
        queryPo.setWhereWujieUnionid(dto.getUnionId());
        TbBasicSysUser user = tbBasicSysUserDao.get(queryPo);

        if (user == null) { // 用户不存在，注册
            Integer userId = registerUser(UserSourceEnum.WUJIE_UNIOINID_REGISTRATION.getCode());
            if (userId == null) {
                throw new ServerException("注册失败");
            }

            TbBasicSysUserPo updatePo = new TbBasicSysUserPo();
            updatePo.setWhereId(userId);
            updatePo.setWujieUnionid(dto.getUnionId());
            updatePo.setUpdatedUserId(userId);
            if (tbBasicSysUserDao.update(updatePo) != 1) {
                throw new ServerException("注册失败");
            }

            // 获取用户信息
            queryPo.setWhereWujieUnionid(null);
            queryPo.setWhereId(userId);
            user = tbBasicSysUserDao.get(queryPo);

            eventPublisher.publishEvent(new UserRegisterEvent(this, UserRegisterEntity.builder()
                    .userId(userId)
                    .request(FunctionUtil.getRequest())
                    .build())); // 发布事件
        } else {
            if (user.getStatus() == UserStatusEnum.DISABLED.getCode()) {
                throw new ServerException("账号已禁用");
            } else if (user.getStatus() == UserStatusEnum.DELETED.getCode()) {
                throw new ServerException("账号已注销");
            }
        }
        return loginSuccess(user, request, dto.getClientFrom());
    }
}
