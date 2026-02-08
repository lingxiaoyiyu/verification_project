package com.api.basic.service.sys.role.update;

import cn.dev33.satoken.stp.StpUtil;
import cn.hutool.core.util.StrUtil;
import com.api.basic.dao.TbBasicSysRoleDao;
import com.api.basic.data.dto.sys.role.update.StatusDto;
import com.api.basic.data.entity.TbBasicSysRole;
import com.api.basic.data.po.TbBasicSysRolePo;
import com.api.common.base.AbstractService;
import com.api.common.base.Result;
import com.api.common.enums.IsDeleteEnum;
import com.api.common.exception.ServerException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.Map;

/**
 * 更新角色状态
 */
@Service("BasicSysRoleUpdateStatusServiceImpl")
@RequiredArgsConstructor
public class Status extends AbstractService {

    private final TbBasicSysRoleDao tbBasicSysRoleDao;

    /**
     * 参数检查
     *
     * @param dto DTO对象
     */
    public void check(StatusDto dto) {
        TbBasicSysRole entity = tbBasicSysRoleDao.get(TbBasicSysRolePo.builder().whereId(dto.getId()).whereIsDelete(IsDeleteEnum.NO_DELETE.getCode()).build());

        if (entity == null) {
            throw new ServerException("角色信息不存在");
        }

        if (StrUtil.isNotBlank(dto.getUpdatedAt()) && !dto.getUpdatedAt().equals(entity.getUpdatedAt())) {
            throw new ServerException("角色信息已被其他用户修改");
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
        if (tbBasicSysRoleDao.update(TbBasicSysRolePo.builder()
                .whereId(dto.getId())
                .status(dto.getStatus())
                .updatedUserId(StpUtil.getLoginIdAsInt())
                .build()) != 1) {
            throw new ServerException("角色状态更新失败");
        }

        Map<String, String> data = new HashMap<>();
        data.put("updatedAt", tbBasicSysRoleDao.getFieldById(dto.getId(), TbBasicSysRole::getUpdatedAt).orElse(""));
        return Result.ok("角色状态更新成功", data);
    }
}
