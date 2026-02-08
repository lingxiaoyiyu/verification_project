package com.api.basic.service.sys.user.update;

import cn.dev33.satoken.stp.StpUtil;
import cn.hutool.core.util.StrUtil;
import com.api.basic.dao.TbBasicSysUserDao;
import com.api.basic.data.dto.sys.user.update.StatusDto;
import com.api.basic.data.entity.TbBasicSysUser;
import com.api.basic.data.po.TbBasicSysUserPo;
import com.api.common.base.AbstractService;
import com.api.common.base.Result;
import com.api.common.exception.ServerException;
import com.api.common.utils.FunctionUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.Map;

/**
 * 管理员修改指定用户状态接口
 */
@Service("BasicSysUserUpdateStatusServiceImpl")
@RequiredArgsConstructor
public class Status extends AbstractService {

    private final TbBasicSysUserDao tbBasicSysUserDao;

    /**
     * 参数检查
     *
     * @param dto DTO对象
     */
    public void check(StatusDto dto) {
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
    public Result<?> service(StatusDto dto) {
        if (tbBasicSysUserDao.update(TbBasicSysUserPo.builder()
                .whereId(dto.getId())
                .status(dto.getStatus())
                .updatedUserId(StpUtil.getLoginIdAsInt())
                .build()) != 1) {
            throw new ServerException(FunctionUtil.getI18nString("service.user.updateFailed"));
        }

        Map<String, String> data = new HashMap<>();
        data.put("updatedAt", tbBasicSysUserDao.getFieldById(dto.getId(), TbBasicSysUser::getUpdatedAt).orElse(""));
        return Result.ok(data);
    }
}
