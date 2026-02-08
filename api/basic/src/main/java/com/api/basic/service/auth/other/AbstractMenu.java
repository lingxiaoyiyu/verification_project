package com.api.basic.service.auth.other;

import cn.dev33.satoken.stp.StpUtil;
import cn.hutool.core.collection.CollUtil;
import com.api.basic.dao.TbBasicSysMenuDao;
import com.api.basic.dao.TbBasicSysRoleDao;
import com.api.basic.dao.TbBasicSysRoleMenuRelationDao;
import com.api.basic.dao.TbBasicSysUserRoleRelationDao;
import com.api.basic.data.entity.TbBasicSysMenu;
import com.api.basic.data.entity.TbBasicSysRole;
import com.api.basic.data.entity.TbBasicSysRoleMenuRelation;
import com.api.basic.data.entity.TbBasicSysUserRoleRelation;
import com.api.basic.data.po.TbBasicSysMenuPo;
import com.api.basic.data.po.TbBasicSysRoleMenuRelationPo;
import com.api.basic.data.po.TbBasicSysRolePo;
import com.api.basic.data.po.TbBasicSysUserRoleRelationPo;
import com.api.common.base.AbstractService;
import com.api.common.enums.IsDeleteEnum;
import com.api.common.enums.StatusEnum;
import lombok.RequiredArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
public class AbstractMenu extends AbstractService {

    protected final TbBasicSysUserRoleRelationDao tbBasicSysUserRoleRelationDao;
    protected final TbBasicSysRoleMenuRelationDao tbBasicSysRoleMenuRelationDao;
    protected final TbBasicSysMenuDao tbBasicSysMenuDao;
    protected final TbBasicSysRoleDao tbBasicSysRoleDao;

    /**
     * 获取用户菜单列表
     *
     * @param menuTypes 菜单类型列表
     * @return 菜单列表
     */
    protected List<TbBasicSysMenu> getUserMenus(List<Integer> menuTypes) {
        // 获取用户角色菜单值
        List<TbBasicSysMenu> roleEnableMenu = new ArrayList<>();
        // 获取用户的角色
        List<Integer> userRoleIds = tbBasicSysUserRoleRelationDao.query(TbBasicSysUserRoleRelationPo.builder()
                        .whereUserId(StpUtil.getLoginIdAsInt())
                        .build())
                .stream()
                .map(TbBasicSysUserRoleRelation::getRoleId)
                .toList();
        if (!CollUtil.isEmpty(userRoleIds)) {
// 获取用户的未删除、启用状态的角色ID
            List<Integer> userEnableRoleId = tbBasicSysRoleDao.query(TbBasicSysRolePo.builder()
                            .whereIsDelete(IsDeleteEnum.NO_DELETE.getCode())
                            .whereStatus(StatusEnum.ENABLE.getCode())
                            .whereInIds(userRoleIds).build())
                    .stream()
                    .map(TbBasicSysRole::getId)
                    .toList();
            if (CollUtil.isNotEmpty(userEnableRoleId)) {
                // 判断是否存在id=1的角色
                if (userEnableRoleId.contains(1)) { // 存在系统管理员, 所有菜单值
                    roleEnableMenu = tbBasicSysMenuDao.query(TbBasicSysMenuPo.builder()
                            .whereIsDelete(IsDeleteEnum.NO_DELETE.getCode())
                            .whereStatus(StatusEnum.ENABLE.getCode())
                            .whereInTypes(menuTypes)
                            .build());
                } else {
                    List<Integer> roleMenuIds = tbBasicSysRoleMenuRelationDao.query(TbBasicSysRoleMenuRelationPo.builder()
                                    .whereInRoleIds(userEnableRoleId)
                                    .whereIsDelete(IsDeleteEnum.NO_DELETE.getCode())
                                    .build())
                            .stream()
                            .map(TbBasicSysRoleMenuRelation::getMenuId)
                            .toList();
                    // 获取显示在首页的菜单
                    if (CollUtil.isNotEmpty(roleMenuIds)) {
                        roleEnableMenu = tbBasicSysMenuDao.query(TbBasicSysMenuPo.builder()
                                .whereInIds(roleMenuIds)
                                .whereIsDelete(IsDeleteEnum.NO_DELETE.getCode())
                                .whereStatus(StatusEnum.ENABLE.getCode())
                                .whereInTypes(menuTypes)
                                .build());
                    }
                }
            }
        } else {
            roleEnableMenu = tbBasicSysMenuDao.query(TbBasicSysMenuPo.builder()
                    .whereInIds(List.of(1, 2))
                    .whereIsDelete(IsDeleteEnum.NO_DELETE.getCode())
                    .whereStatus(StatusEnum.ENABLE.getCode())
                    .whereInTypes(menuTypes)
                    .build());
        }


        return roleEnableMenu;
    }
}
