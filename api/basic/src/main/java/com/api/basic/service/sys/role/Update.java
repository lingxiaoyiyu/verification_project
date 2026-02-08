package com.api.basic.service.sys.role;

import cn.dev33.satoken.stp.StpUtil;
import cn.hutool.core.util.StrUtil;
import com.api.basic.dao.TbBasicSysMenuDao;
import com.api.basic.dao.TbBasicSysRoleDao;
import com.api.basic.dao.TbBasicSysRoleMenuRelationDao;
import com.api.basic.data.dto.sys.role.UpdateDto;
import com.api.basic.data.entity.TbBasicSysRole;
import com.api.basic.data.entity.TbBasicSysRoleMenuRelation;
import com.api.basic.data.po.TbBasicSysMenuPo;
import com.api.basic.data.po.TbBasicSysRoleMenuRelationPo;
import com.api.basic.data.po.TbBasicSysRolePo;
import com.api.common.base.AbstractService;
import com.api.common.base.Result;
import com.api.common.enums.IsDeleteEnum;
import com.api.common.exception.ServerException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 更新角色
 */
@Service("BasicSysRoleUpdateServiceImpl")
@RequiredArgsConstructor
public class Update extends AbstractService {

    private final TbBasicSysRoleDao tbBasicSysRoleDao;
    private final TbBasicSysRoleMenuRelationDao tbBasicSysRoleMenuRelationDao;
    private final TbBasicSysMenuDao tbBasicSysMenuDao;

    /**
     * 参数检查
     *
     * @param dto DTO对象
     */
    public void check(UpdateDto dto) {
        TbBasicSysRole entity = tbBasicSysRoleDao.get(TbBasicSysRolePo.builder().whereId(dto.getId()).whereIsDelete(IsDeleteEnum.NO_DELETE.getCode()).build());

        if (entity == null) {
            throw new ServerException("角色信息不存在");
        }

        if (StrUtil.isNotBlank(dto.getUpdatedAt()) && !dto.getUpdatedAt().equals(entity.getUpdatedAt())) {
            throw new ServerException("角色信息已被其他用户修改");
        }

        if (tbBasicSysRoleDao.cnt(TbBasicSysRolePo.builder()
                .whereIsDelete(IsDeleteEnum.NO_DELETE.getCode())
                .whereNotId(dto.getId())
                .whereName(dto.getName())
                .build()) > 0) {
            throw new ServerException("角色名称已存在");
        }

        if (tbBasicSysRoleDao.cnt(TbBasicSysRolePo.builder()
                .whereIsDelete(IsDeleteEnum.NO_DELETE.getCode())
                .whereNotId(dto.getId())
                .whereIdentifying(dto.getIdentifying())
                .build()) > 0) {
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
    public Result<?> service(UpdateDto dto) {
        TbBasicSysRolePo updatePo = handleUpdateData(dto);
        if (tbBasicSysRoleDao.update(updatePo) != 1) {
            throw new ServerException("角色更新失败");
        }

        handleMenuData(dto);

        Map<String, String> data = new HashMap<>();
        data.put("updatedAt", tbBasicSysRoleDao.getFieldById(dto.getId(), TbBasicSysRole::getUpdatedAt).orElse(""));
        return Result.ok("角色信息修改成功", data);
    }

    /**
     * 处理要更新的数据
     *
     * @param dto DTO对象
     * @return 处理后的数据
     */
    private TbBasicSysRolePo handleUpdateData(UpdateDto dto) {
        TbBasicSysRolePo updatePo = new TbBasicSysRolePo();
        updatePo.setWhereId(dto.getId());
        updatePo.setUpdatedUserId(StpUtil.getLoginIdAsInt());
        BeanUtils.copyProperties(dto, updatePo);
        return updatePo;
    }

    /**
     * 处理菜单数据
     *
     * @param dto DTO对象
     */
    private void handleMenuData(UpdateDto dto) {
        if (dto.getMenuIdList() == null) {
            dto.setMenuIdList(new ArrayList<>());
        }
        dto.getMenuIdList().add(1);
        dto.getMenuIdList().add(2);

        List<TbBasicSysRoleMenuRelation> roleMenuList = tbBasicSysRoleMenuRelationDao.query(TbBasicSysRoleMenuRelationPo.builder()
                .whereRoleId(dto.getId())
                .whereIsDelete(IsDeleteEnum.NO_DELETE.getCode())
                .build()); // 数据库中现存当前用户的订单列表
        List<Integer> menuIdList = dto.getMenuIdList(); // 用户提交的新菜单列表

        // 对比两个列表，找出需要删除的菜单
        roleMenuList.forEach(roleMenu -> {
            if (!menuIdList.contains(roleMenu.getMenuId())) {
                tbBasicSysRoleMenuRelationDao.update(TbBasicSysRoleMenuRelationPo.builder()
                        .whereRoleId(dto.getId())
                        .whereMenuId(roleMenu.getMenuId())
                        .updatedUserId(StpUtil.getLoginIdAsInt())
                        .isDelete(IsDeleteEnum.DELETED.getCode())
                        .build());
            }
        });

        // 对比两个列表，找出需要添加的菜单
        menuIdList.forEach(menuId -> {
            if (roleMenuList.stream().noneMatch(roleMenu -> roleMenu.getMenuId().equals(menuId))) {
                if (tbBasicSysRoleMenuRelationDao.add(TbBasicSysRoleMenuRelationPo.builder()
                        .roleId(dto.getId())
                        .menuId(menuId)
                        .createdUserId(StpUtil.getLoginIdAsInt())
                        .updatedUserId(StpUtil.getLoginIdAsInt())
                        .build()) != 1) {
                    throw new ServerException("角色菜单关联信息添加失败");
                }
            }
        });
    }
}
