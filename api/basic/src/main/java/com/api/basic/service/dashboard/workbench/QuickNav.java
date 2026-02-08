package com.api.basic.service.dashboard.workbench;

import cn.dev33.satoken.stp.StpUtil;
import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.StrUtil;
import com.api.basic.dao.TbBasicSysMenuDao;
import com.api.basic.dao.TbBasicSysRoleDao;
import com.api.basic.dao.TbBasicSysRoleMenuRelationDao;
import com.api.basic.dao.TbBasicSysUserRoleRelationDao;
import com.api.basic.data.entity.TbBasicSysMenu;
import com.api.basic.data.entity.TbBasicSysRole;
import com.api.basic.data.entity.TbBasicSysRoleMenuRelation;
import com.api.basic.data.entity.TbBasicSysUserRoleRelation;
import com.api.basic.data.enums.MenuTypeEnum;
import com.api.basic.data.po.TbBasicSysMenuPo;
import com.api.basic.data.po.TbBasicSysRoleMenuRelationPo;
import com.api.basic.data.po.TbBasicSysRolePo;
import com.api.basic.data.po.TbBasicSysUserRoleRelationPo;
import com.api.common.base.AbstractService;
import com.api.common.base.Result;
import com.api.common.enums.IsDeleteEnum;
import com.api.common.enums.StatusEnum;
import com.api.common.enums.YesOrNoEnum;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 导航
 */
@Service("BasicDashboardWorkbenchQuickNavService")
@RequiredArgsConstructor
public class QuickNav extends AbstractService {

    private final TbBasicSysUserRoleRelationDao tbBasicSysUserRoleRelationDao;
    private final TbBasicSysMenuDao tbBasicSysMenuDao;
    private final TbBasicSysRoleDao tbBasicSysRoleDao;
    private final TbBasicSysRoleMenuRelationDao tbBasicSysRoleMenuRelationDao;

    /**
     * 参数检查
     */
    public void check() {

    }

    /**
     * 业务主体
     *
     * @return 处理结果
     */
    public Result<?> service() {
        return Result.ok(getQuickNavItems());
    }

    /**
     * 导航
     *
     * @return 数据
     */
    private List<Object> getQuickNavItems() {
        List<String> colorList = List.of("#1fdaca", "#bf0c2c", "#e18525", "#3fb27f", "#4daf1bc9", "#00d8ff");
        List<String> iconList = List.of("ion:eye", "ion:eye-off", "ion:lock-closed", "ion:lock-open", "ion:key", "ion:key-outline");
        List<Object> data = new ArrayList<>();

        // 获取用户的角色
        List<Integer> userRoleIds = tbBasicSysUserRoleRelationDao.query(TbBasicSysUserRoleRelationPo.builder()
                        .whereUserId(StpUtil.getLoginIdAsInt())
                        .whereIsDelete(IsDeleteEnum.NO_DELETE.getCode())
                .build())
                .stream()
                .map(TbBasicSysUserRoleRelation::getRoleId)
                .toList();

        // 获取用户的未删除、启用状态的角色ID
        List<Integer> userEnableRoleId = tbBasicSysRoleDao.query(TbBasicSysRolePo.builder()
                .whereIsDelete(IsDeleteEnum.NO_DELETE.getCode())
                .whereStatus(StatusEnum.ENABLE.getCode())
                .whereInIds(userRoleIds).build())
                .stream()
                .map(TbBasicSysRole::getId)
                .toList();
        if (CollUtil.isNotEmpty(userEnableRoleId)) {
            // 获取用户角色菜单值
            List<TbBasicSysMenu> roleEnableMenuIds = new ArrayList<>();
            // 判断是否存在id=1的角色
            if (userEnableRoleId.contains(1)) { // 存在系统管理员, 所有菜单值
                roleEnableMenuIds = tbBasicSysMenuDao.query(TbBasicSysMenuPo.builder()
                        .whereIsDelete(IsDeleteEnum.NO_DELETE.getCode())
                        .whereStatus(StatusEnum.ENABLE.getCode())
                        .whereShowInAdminHome(YesOrNoEnum.YES.getCode())
                        .whereType(MenuTypeEnum.PAGE.getCode())
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
                    roleEnableMenuIds = tbBasicSysMenuDao.query(TbBasicSysMenuPo.builder()
                            .whereInIds(roleMenuIds)
                            .whereIsDelete(IsDeleteEnum.NO_DELETE.getCode())
                            .whereStatus(StatusEnum.ENABLE.getCode())
                            .whereShowInAdminHome(YesOrNoEnum.YES.getCode())
                            .whereType(MenuTypeEnum.PAGE.getCode())
                            .build());
                }
            }
            if (CollUtil.isNotEmpty(roleEnableMenuIds)) {
                for (int i = 0; i < roleEnableMenuIds.size(); i++) {
                    TbBasicSysMenu menu = roleEnableMenuIds.get(i);
                    Map<String, Object> item = new HashMap<>();
                    item.put("color", colorList.get(i % colorList.size())); // 循环使用颜色列表
                    item.put("icon", StrUtil.blankToDefault(menu.getIcon(), iconList.get(i % iconList.size())));
                    item.put("title", menu.getTitle());
                    item.put("url", menu.getName());
                    data.add(item);
                }
            }
        }
        return data;
    }
}
