package com.api.basic.service.auth.other;

import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONUtil;
import com.api.basic.dao.TbBasicSysMenuDao;
import com.api.basic.dao.TbBasicSysRoleDao;
import com.api.basic.dao.TbBasicSysRoleMenuRelationDao;
import com.api.basic.dao.TbBasicSysUserRoleRelationDao;
import com.api.basic.data.entity.TbBasicSysMenu;
import com.api.basic.data.enums.MenuTypeEnum;
import com.api.basic.data.vo.auth.other.MenuItemVo;
import com.api.basic.data.vo.auth.other.MenuMetaVo;
import com.api.basic.service.sys.menu.Get;
import com.api.common.base.Result;
import com.api.common.enums.YesOrNoEnum;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * 获取用户菜单
 */
@Service("BasicAuthOtherMenuServiceImpl")
public class Menus extends AbstractMenu {

    public Menus(TbBasicSysUserRoleRelationDao tbBasicSysUserRoleRelationDao, TbBasicSysRoleMenuRelationDao tbBasicSysRoleMenuRelationDao, TbBasicSysMenuDao tbBasicSysMenuDao, TbBasicSysRoleDao tbBasicSysRoleDao, Get get) {
        super(tbBasicSysUserRoleRelationDao, tbBasicSysRoleMenuRelationDao, tbBasicSysMenuDao, tbBasicSysRoleDao);
    }

    /**
     * 业务主体
     *
     * @return 处理结果
     */
    public Result<?> service() {
        List<MenuItemVo> treeList = new ArrayList<>();
        List<TbBasicSysMenu> menuList = getUserMenus(List.of(MenuTypeEnum.DIR.getCode(), MenuTypeEnum.PAGE.getCode()));
        if (!menuList.isEmpty()) {
            treeList = buildTree(menuList);
        }
        return Result.ok(treeList);
    }


    // 将菜单列表构建成属性结构
    protected List<MenuItemVo> buildTree(List<TbBasicSysMenu> menuList) {
        List<MenuItemVo> treeList = new ArrayList<>();
        for (TbBasicSysMenu menu : menuList) {
            // 将菜单添加到节点树
            treeList = addTree(treeList, menu);
            if (!isExist(treeList, menu)) {
                treeList.add(transfer(menu));
            }
        }
        handleTree(treeList);
        for (MenuItemVo item : treeList) {
            if (StrUtil.isNotBlank(item.getPath()) && !StrUtil.startWith(item.getPath(), "/"))
                item.setPath("/" + item.getPath());
        }
        return sort(treeList);
    }


    /**
     * 对treeList每一层根据order进行升序排列
     *
     * @param menuList 菜单列表
     * @return 排序后的菜单列表
     */
    protected List<MenuItemVo> sort(List<MenuItemVo> menuList) {
        if (menuList != null && !menuList.isEmpty()) {
            menuList.sort(Comparator.comparing(MenuItemVo::getOrder));
            // menuList可能是无限层，对每一层都根据order进行升序排列
            for (MenuItemVo menuNode : menuList) {
                // 对Children中的列表，根据每一项的meta中的order值升序排列
                menuNode.getChildren().sort(Comparator.comparing(MenuItemVo::getOrder));
                // 递归处理子节点
                sort(menuNode.getChildren());
            }
        }
        return menuList;
    }

    // 将TbBasicSysMenu中的信息转移到ResMenuDto中
    protected MenuItemVo transfer(TbBasicSysMenu menu) {
        MenuItemVo vo = new MenuItemVo();
        vo.setId(menu.getId());
        vo.setName(menu.getName());
        vo.setTitle(menu.getTitle());
        vo.setComponent(StrUtil.isBlank(menu.getComponent()) ? "" : menu.getComponent());
        vo.setPath(StrUtil.isBlank(menu.getPath()) ? "" : menu.getPath());
        vo.setOrder(menu.getOrder());
        if (menu.getType() == MenuTypeEnum.DIR.getCode() || menu.getType() == MenuTypeEnum.PAGE.getCode()) {
            vo.setChildren(new ArrayList<>());
        }

        // 路由元信息
        MenuMetaVo metaVo = new MenuMetaVo();
        metaVo.setTitle(menu.getTitle());
        metaVo.setIcon(StrUtil.isBlank(menu.getIcon()) ? "" : menu.getIcon());
        metaVo.setOrder(menu.getOrder());
        metaVo.setActiveIcon(StrUtil.isBlank(menu.getActiveIcon()) ? "" : menu.getActiveIcon());
        metaVo.setKeepalive(menu.getKeepalive() == YesOrNoEnum.YES.getCode());
        metaVo.setHideInMenu(menu.getHideInMenu() == YesOrNoEnum.YES.getCode());
        metaVo.setHideInTab(menu.getHideInTab() == YesOrNoEnum.YES.getCode());
        metaVo.setHideChildrenInMenu(menu.getHideChildrenInMenu() == YesOrNoEnum.YES.getCode());
        metaVo.setAffixTab(menu.getAffixTab() == YesOrNoEnum.YES.getCode());
        metaVo.setAffixTabOrder(menu.getAffixTabOrder());
        metaVo.setIframeSrc(menu.getIsIframe() == YesOrNoEnum.YES.getCode() ? "" : menu.getIframeSrc());
        metaVo.setLink(menu.getIsLink() == YesOrNoEnum.YES.getCode() ? "" : menu.getLink());
        metaVo.setIgnoreAccess(menu.getIgnoreAccess() == YesOrNoEnum.YES.getCode());
        metaVo.setMaxNumOfOpenTab(menu.getMaxNumOfOpenTab());

        metaVo.setOpenInNewWindow(menu.getOpenInNewWindow() == YesOrNoEnum.YES.getCode());

        metaVo.setActivePath("");
        metaVo.setHideInBreadcrumb(false);
        metaVo.setAuthority(new String[]{});
        metaVo.setBadge("");
        metaVo.setBadgeType("normal");
        metaVo.setBadgeVariants("success");
        metaVo.setMenuVisibleWithForbidden(false);
        metaVo.setQuery(StrUtil.isNotBlank(menu.getQuery()) ? JSONUtil.parseObj(menu.getQuery()) : null);

        vo.setMeta(metaVo);
        return vo;
    }

    /**
     * 将菜单根据parentID添加到节点树中
     *
     * @param treeList 节点数
     * @param menu     菜单
     * @return 节点树
     */
    protected List<MenuItemVo> addTree(List<MenuItemVo> treeList, TbBasicSysMenu menu) {
        for (MenuItemVo value : treeList) {
            List<MenuItemVo> children = value.getChildren();
            if (value.getId() == menu.getParentId()) {
                children.add(transfer(menu));
                value.setChildren(children);
            } else if (value.getChildren() != null) { // 判断是否有子节点
                value.setChildren(addTree(value.getChildren(), menu));
            }
        }
        return treeList;
    }

    /**
     * 判断菜单是否已经在菜单树中
     *
     * @param treeList 菜单树
     * @param menu     要检查的菜单节点
     * @return boolean true:已存在，false:不存在
     */
    protected boolean isExist(List<MenuItemVo> treeList, TbBasicSysMenu menu) {
        boolean rst = false;
        for (MenuItemVo menuNode : treeList) {
            if (menuNode.getId() == menu.getId()) {
                rst = true;
            } else if (menuNode.getChildren() != null && !menuNode.getChildren().isEmpty()) {
                rst = isExist(menuNode.getChildren(), menu);
            }
            if (rst) {
                break;
            }
        }
        return rst;
    }

    protected void handleTree(List<MenuItemVo> treeList) {
        // 将节点的path路径叠加到下级节点的path前面，并以【/】连接
        for (MenuItemVo menuNode : treeList) {
            if (menuNode.getPath() != null) {
                menuNode.setPath(menuNode.getPath().startsWith("/") ? menuNode.getPath() : "/" + menuNode.getPath());
            }
            menuNode.setPath(StrUtil.replace(menuNode.getPath(), "//", "/"));
            if (menuNode.getChildren() != null && !menuNode.getChildren().isEmpty()) {
                // 对Children中的列表，根据每一项的meta中的order值升序排列
                menuNode.getChildren().sort(Comparator.comparing(MenuItemVo::getOrder));
                for (MenuItemVo child : menuNode.getChildren()) {
                    // 构建子节点的完整路径
                    String newPath = StrUtil.isNotBlank(menuNode.getPath()) ? menuNode.getPath() + "/" + child.getPath() : "/" + child.getPath();
                    // 将//替换为/
                    newPath = StrUtil.replace(newPath, "//", "/");
                    child.setPath(newPath);
                }
                // 递归处理子节点
                handleTree(menuNode.getChildren());
            }
        }
    }
}
