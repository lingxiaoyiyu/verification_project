package com.api.basic.service.sys.role;

import cn.dev33.satoken.stp.StpUtil;
import cn.hutool.core.util.StrUtil;
import com.api.basic.dao.TbBasicSysRoleDao;
import com.api.basic.dao.TbBasicSysRoleMenuRelationDao;
import com.api.basic.data.dto.sys.role.RemoveDto;
import com.api.basic.data.entity.TbBasicSysRole;
import com.api.basic.data.po.TbBasicSysRoleMenuRelationPo;
import com.api.basic.data.po.TbBasicSysRolePo;
import com.api.common.base.AbstractService;
import com.api.common.base.Result;
import com.api.common.enums.IsDeleteEnum;
import com.api.common.exception.ServerException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 删除角色
 */
@Service("BasicSysRoleRemoveServiceImpl")
@RequiredArgsConstructor
public class Remove extends AbstractService {

    private final TbBasicSysRoleDao tbBasicSysRoleDao;
    private final TbBasicSysRoleMenuRelationDao tbBasicSysRoleMenuRelationDao;

    /**
     * 参数检查
     *
     * @param dto DTO对象
     */
    public void check(RemoveDto dto) {
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
    public Result<?> service(RemoveDto dto) {
        // 删除角色
        if (tbBasicSysRoleDao.update(TbBasicSysRolePo.builder()
                .whereId(dto.getId())
                .isDelete(IsDeleteEnum.DELETED.getCode())
                .updatedUserId(StpUtil.getLoginIdAsInt())
                .build()) == 0) {
            throw new ServerException("角色删除失败");
        }
        // 删除角色菜单关联关系
        tbBasicSysRoleMenuRelationDao.update(TbBasicSysRoleMenuRelationPo.builder()
                .whereRoleId(dto.getId())
                .isDelete(IsDeleteEnum.DELETED.getCode())
                .updatedUserId(StpUtil.getLoginIdAsInt())
                .build());
        return Result.ok("角色删除成功");
    }
}
