package com.api.basic.data.entity;

import com.api.common.base.data.po.BasePagePo;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.SuperBuilder;

/**
 * 菜单信息表实体
 */
@EqualsAndHashCode(doNotUseGetters = true, callSuper = true)
@Data
@SuperBuilder
public class TbBasicSysMenu extends BasePagePo {

    public TbBasicSysMenu(){
        super();
    }

    // ID
    private Integer id;
    // 父级id
    private Integer parentId;
    // 类型。 1：目录，2：页面，3：权限。
    private Integer type;
    // 菜单名
    private String name;
    // 路由地址
    private String path;
    // 组件地址
    private String component;
    // 权限标识
    private String identifying;
    // 状态。1：正常，2：禁用。
    private Integer status;
    // 删除状态。1：未删除，2：已删除。
    private Integer isDelete;
    // 菜单标题
    private String title;
    // 图标
    private String icon;
    // 用于配置页面的激活图标，会在菜单中显示。一般会配合图标库使用，如果是http链接，会自动加载图片。
    private String activeIcon;
    // 用于配置页面的排序，用于路由在菜单中的排序。排序仅针对一级菜单有效，二级菜单的排序需要在对应的一级菜单中按代码顺序设置。
    private Integer order;
    // 用于配置页面是否开启缓存，开启后页面会缓存，不会重新加载，仅在标签页启用时有效。1：是，2：否。
    private Integer keepalive;
    // 用于配置页面是否在菜单中隐藏，隐藏后页面不会在菜单中显示。1：隐藏，2：显示。
    private Integer hideInMenu;
    // 用于配置页面是否在标签页中隐藏，隐藏后页面不会在标签页中显示。1：隐藏，2：显示。
    private Integer hideInTab;
    // 用于配置页面的子页面是否在菜单中隐藏，隐藏后子页面不会在菜单中显示。。1：是，2：否。
    private Integer hideChildrenInMenu;
    // 用于配置页面的权限，只有拥有对应权限的用户才能访问页面，不配置则不需要权限。
    private String authority;
    // 用于配置当前激活的菜单，有时候页面没有显示在菜单内，需要激活父级菜单时使用。
    private Integer activeId;
    // 用于配置页面是否固定标签页，固定后页面不可关闭。1：是，2：否
    private Integer affixTab;
    // 用于配置页面固定标签页的排序, 采用升序排序。
    private Integer affixTabOrder;
    // 用于配置页面是否忽略权限，直接可以访问。1：是，2：否
    private Integer ignoreAccess;
    // 用于配置是否是内嵌页面的iframe地址
    private Integer isIframe;
    // 用于配置内嵌页面的 iframe 地址，设置后会在当前页面内嵌对应的页面。
    private String iframeSrc;
    // 用于配置是否是外链跳转路径。1：是，2：否
    private Integer isLink;
    // 用于配置外链跳转路径，会在新窗口打开。
    private String link;
    // 用于配置标签页最大打开数量，设置后会在打开新标签页时自动关闭最早打开的标签页(仅在打开同名标签页时生效)。
    private Integer maxNumOfOpenTab;
    // 用于配置页面是否在新窗口中打开。1：是，2：否
    private Integer openInNewWindow;
    // 用于配置页面的菜单参数，会在菜单中传递给页面。
    private String query;
    // 是否在管理后台首页导航中显示。1：是，2：否
    private Integer showInAdminHome;
    // 创建时间
    private String createdAt;
    // 创建者
    private Integer createdUserId;
    // 更新时间
    private String updatedAt;
    // 最后更新者
    private Integer updatedUserId;
}
