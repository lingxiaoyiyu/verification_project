package com.api.basic.service.auth.reset;

import cn.hutool.core.util.StrUtil;
import cn.hutool.crypto.digest.DigestUtil;
import com.api.basic.dao.TbBasicSysUserDao;
import com.api.basic.data.dto.auth.reg.EmailDto;
import com.api.basic.data.entity.TbBasicSysUser;
import com.api.basic.data.po.TbBasicSysUserPo;
import com.api.common.base.Result;
import com.api.common.exception.ServerException;
import com.api.common.utils.RedisCache;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 邮箱验证码重置密码
 */
@Service("BasicAuthResetEmailPwdServiceImpl")
public class Email extends ResetAbstract {

    public Email(TbBasicSysUserDao tbBasicSysUserDao, RedisCache redisCache) {
        super(tbBasicSysUserDao, redisCache);
    }

    /**
     * 参数检查
     *
     * @param dto DTO对象
     */
    public void check(EmailDto dto) {
        dto.setEmail(StrUtil.trim(dto.getEmail()));
        dto.setPassword(StrUtil.trim(dto.getPassword()));
        dto.setVerCode(StrUtil.trim(dto.getVerCode()));

        String cacheVerifyCode = redisCache.getCacheObject(dto.getVerCodeKey());
        if (StrUtil.isBlank(cacheVerifyCode)) {
            throw new ServerException("验证码已过期");
        }
        if (!StrUtil.equals(cacheVerifyCode, dto.getVerCode())) {
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
    public Result<?> service(EmailDto dto, HttpServletRequest request) {
        TbBasicSysUserPo queryPo = new TbBasicSysUserPo();
        queryPo.setWhereEmail(dto.getEmail());
        TbBasicSysUser user = tbBasicSysUserDao.get(queryPo);

        if (user == null) {
            throw new ServerException("账号不存在");
        }

        String password = DigestUtil.md5Hex(dto.getPassword()); // 默认密码

        TbBasicSysUserPo updatePo = new TbBasicSysUserPo();
        updatePo.setWhereId(user.getId());
        updatePo.setPassword(password);
        updatePo.setUpdatedUserId(user.getId());
        if (tbBasicSysUserDao.update(updatePo) != 1) {
            throw new ServerException("修改失败");
        }

        return loginSuccess(user);
    }
}
