package com.api.basic.service.sys.user.update.password;

import cn.hutool.core.util.StrUtil;
import com.api.basic.dao.TbBasicSysUserDao;
import com.api.basic.data.dto.sys.user.update.password.ByIdDto;
import com.api.basic.data.entity.TbBasicSysUser;
import com.api.basic.data.po.TbBasicSysUserPo;
import com.api.common.base.Result;
import com.api.common.exception.ServerException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.api.common.utils.FunctionUtil;

import java.util.HashMap;
import java.util.Map;

/**
 * 管理员修改指定用户密码
 */
@Service("BasicSysUserUpdatePasswordByIdServiceImpl")
public class ById extends AbstractPassword {

    public ById(TbBasicSysUserDao tbBasicSysUserDao) {
        super(tbBasicSysUserDao);
    }

    /**
     * 参数检查
     *
     * @param dto DTO对象
     */
    public void check(ByIdDto dto) {
        TbBasicSysUser entity = tbBasicSysUserDao.get(TbBasicSysUserPo.builder().whereId(dto.getId()).build());

        if (entity == null) {
            throw new ServerException(FunctionUtil.getI18nString("service.user.notExist"));
        }

        if (StrUtil.isNotBlank(dto.getUpdatedAt()) && !dto.getUpdatedAt().equals(entity.getUpdatedAt())) {
            throw new ServerException(FunctionUtil.getI18nString("service.user.infoModified"));
        }
    }

    /**
     * 业务主体
     *
     * @param dto DTO对象
     * @return 处理结果
     */
    @Transactional(rollbackFor = Throwable.class)
    public Result<?> service(ByIdDto dto) {
        updatePassword(dto.getId(), dto.getPassword());

        Map<String, String> data = new HashMap<>();
        data.put("updatedAt", tbBasicSysUserDao.getFieldById(dto.getId(), TbBasicSysUser::getUpdatedAt).orElse(""));
        return Result.ok(data);
    }
}
