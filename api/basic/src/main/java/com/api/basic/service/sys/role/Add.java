package com.api.basic.service.sys.role;

import cn.dev33.satoken.stp.StpUtil;
import cn.hutool.core.util.StrUtil;
import com.api.basic.dao.TbBasicSysMenuDao;
import com.api.basic.dao.TbBasicSysRoleDao;
import com.api.basic.dao.TbBasicSysRoleMenuRelationDao;
import com.api.basic.data.dto.sys.role.AddDto;
import com.api.basic.data.entity.TbBasicSysRole;
import com.api.basic.data.entity.TbBasicSysRoleMenuRelation;
import com.api.basic.data.po.TbBasicSysMenuPo;
import com.api.basic.data.po.TbBasicSysRolePo;
import com.api.common.base.AbstractService;
import com.api.common.base.Result;
import com.api.common.enums.IsDeleteEnum;
import com.api.common.exception.ServerException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

/**
 * 添加角色
 */
@Service("BasicSysRoleAddServiceImpl")
@RequiredArgsConstructor
public class Add extends AbstractService {

    private final TbBasicSysRoleDao tbBasicSysRoleDao;
    private final TbBasicSysRoleMenuRelationDao tbBasicSysRoleMenuRelationDao;
    private final TbBasicSysMenuDao tbBasicSysMenuDao;

    /**
     * 参数检查
     *
     * @param dto DTO对象
     */
    public void check(AddDto dto) {
        dto.setName(StrUtil.trim(dto.getName()));
        dto.setIdentifying(StrUtil.trim(dto.getIdentifying()));
        dto.setRemark(StrUtil.trim(dto.getRemark()));

        if (tbBasicSysRoleDao.cnt(TbBasicSysRolePo.builder()
                .whereIsDelete(IsDeleteEnum.NO_DELETE.getCode())
                .whereName(dto.getName())
                .build()) != 0) {
            throw new ServerException("角色名称已存在");
        }

        if (tbBasicSysRoleDao.cnt(TbBasicSysRolePo.builder()
                .whereIsDelete(IsDeleteEnum.NO_DELETE.getCode())
                .whereIdentifying(dto.getIdentifying())
                .build()) != 0) {
            throw new ServerException("角色标识已存在");
        }

        if (dto.getMenuIdList() != null) {
            dto.getMenuIdList().forEach(menuId -> {
                if (tbBasicSysMenuDao.cnt(TbBasicSysMenuPo.builder()
                        .whereIsDelete(IsDeleteEnum.NO_DELETE.getCode())
                        .whereId(menuId)
                        .build()) == 0) {
                    throw new ServerException("部分菜单不存在");
                }
            });
        }
    }

    /**
     * 业务主体
     *
     * @param dto DTO对象
     * @return 处理结果
     */
    @Transactional(rollbackFor = Throwable.class)
    public Result<?> service(AddDto dto) {
        // 构建角色添加对象
        TbBasicSysRole roleEntity = handleAddData(dto);
        if (tbBasicSysRoleDao.add(roleEntity) == 0) {
            throw new ServerException("角色添加失败");
        }
        List<Integer> menuList = dto.getMenuIdList();
        if (menuList == null) {
            menuList = List.of();
        }
        menuList.add(1);
        menuList.add(2);
        for (Integer menuId : menuList) {
            if (tbBasicSysRoleMenuRelationDao.add(TbBasicSysRoleMenuRelation.builder()
                    .roleId(roleEntity.getId())
                    .menuId(menuId)
                    .createdUserId(StpUtil.getLoginIdAsInt())
                    .updatedUserId(StpUtil.getLoginIdAsInt())
                    .build()) == 0) {
                throw new ServerException("角色菜单关联关系添加失败");
            }
        }
        return Result.ok("角色添加成功", Map.of("id", roleEntity.getId()));

    }

    /**
     * 处理要添加的数据
     *
     * @param dto DTO对象
     * @return 处理后的数据
     */
    private TbBasicSysRole handleAddData(AddDto dto) {
        TbBasicSysRole addEntity = new TbBasicSysRole();
        BeanUtils.copyProperties(dto, addEntity);
        addEntity.setCreatedUserId(StpUtil.getLoginIdAsInt());
        addEntity.setUpdatedUserId(StpUtil.getLoginIdAsInt());
        return addEntity;
    }
}
