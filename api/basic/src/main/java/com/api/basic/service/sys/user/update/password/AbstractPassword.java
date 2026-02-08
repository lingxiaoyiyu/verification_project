package com.api.basic.service.sys.user.update.password;

import cn.dev33.satoken.stp.StpUtil;
import cn.hutool.crypto.digest.DigestUtil;
import com.api.basic.dao.TbBasicSysUserDao;
import com.api.basic.data.po.TbBasicSysUserPo;
import com.api.common.base.AbstractService;
import com.api.common.exception.ServerException;
import com.api.common.utils.FunctionUtil;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class AbstractPassword extends AbstractService {

    protected final TbBasicSysUserDao tbBasicSysUserDao;

    /**
     * 修改密码
     *
     * @param userId   用户id
     * @param password 修改后的密码
     */
    protected void updatePassword(Integer userId, String password) {
        TbBasicSysUserPo updatePo = new TbBasicSysUserPo();
        updatePo.setWhereId(userId);
        password = DigestUtil.md5Hex(password);
        updatePo.setPassword(password);
        updatePo.setUpdatedUserId(StpUtil.getLoginIdAsInt());
        if (tbBasicSysUserDao.update(updatePo) != 1) {
            throw new ServerException(FunctionUtil.getI18nString("service.user.password.updateFailed"));
        }
    }
}
