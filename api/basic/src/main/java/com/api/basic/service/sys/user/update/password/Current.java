package com.api.basic.service.sys.user.update.password;

import cn.dev33.satoken.stp.StpUtil;
import cn.hutool.crypto.digest.DigestUtil;
import com.api.basic.dao.TbBasicSysUserDao;
import com.api.basic.data.dto.sys.user.update.password.CurrentDto;
import com.api.common.base.Result;
import com.api.common.exception.ServerException;
import com.api.common.utils.FunctionUtil;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 用户修改自己的密码
 */
@Service("BasicSysUserUpdatePasswordCurrentServiceImpl")
public class Current extends AbstractPassword {

    public Current(TbBasicSysUserDao tbBasicSysUserDao) {
        super(tbBasicSysUserDao);
    }

    /**
     * 参数检查
     *
     * @param dto DTO对象
     */
    public void check(CurrentDto dto) {
        if (dto.getOldPassword().equals(dto.getConfirmPassword())) {
            throw new ServerException(FunctionUtil.getI18nString("service.user.password.newNotSameOld"));
        } else {
            String oldPassword = DigestUtil.md5Hex(dto.getOldPassword());
            if (!oldPassword.equals(StpUtil.getExtra("password"))) {
                throw new ServerException(FunctionUtil.getI18nString("service.user.password.oldPasswordWrong"));
            }
        }
    }

    /**
     * 业务主体
     *
     * @param dto DTO对象
     * @return 处理结果
     */
    @Transactional(rollbackFor = Throwable.class)
    public Result<?> service(CurrentDto dto) {
        updatePassword(StpUtil.getLoginIdAsInt(), dto.getNewPassword());
        return Result.ok(FunctionUtil.getI18nString("service.user.password.updateSuccess"));
    }
}
