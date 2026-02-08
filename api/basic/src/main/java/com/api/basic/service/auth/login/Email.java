package com.api.basic.service.auth.login;

import cn.hutool.core.util.StrUtil;
import cn.hutool.crypto.digest.DigestUtil;
import com.api.basic.data.dto.auth.login.EmailDto;
import com.api.basic.data.entity.TbBasicSysUser;
import com.api.basic.data.enums.UserStatusEnum;
import com.api.basic.data.po.TbBasicSysUserPo;
import com.api.common.base.Result;
import com.api.common.exception.ServerException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 邮箱密码登录
 */
@Service("BasicAuthEmailLoginServiceImpl")
public class Email extends LoginAbstract {

    /**
     * 参数检查
     *
     * @param dto DTO对象
     */
    public void check(EmailDto dto) {
        dto.setEmail(StrUtil.trim(dto.getEmail()));
        dto.setPassword(StrUtil.trim(dto.getPassword()));
    }

    /**
     * 业务主体
     *
     * @param dto DTO对象
     * @return 处理结果
     */
    @Transactional(rollbackFor = Throwable.class)
    public Result<?> service(EmailDto dto, HttpServletRequest request) {
        TbBasicSysUserPo queryPo = new TbBasicSysUserPo();
        queryPo.setWhereEmail(dto.getEmail());
        TbBasicSysUser user = tbBasicSysUserDao.get(queryPo);
        if (user == null) {
            throw new ServerException("账号不存在");
        } else if (user.getStatus() == UserStatusEnum.DISABLED.getCode()) {
            throw new ServerException("账号已禁用");
        } else if (user.getStatus() == UserStatusEnum.DELETED.getCode()) {
            throw new ServerException("账号已注销");
        } else if (!DigestUtil.md5Hex(dto.getPassword()).equals(user.getPassword())) {
            throw new ServerException("密码错误");
        }
        return loginSuccess(user, request, dto.getClientFrom());
    }
}
