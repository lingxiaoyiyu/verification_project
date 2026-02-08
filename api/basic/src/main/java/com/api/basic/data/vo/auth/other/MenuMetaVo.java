package com.api.basic.data.vo.auth.other;

import lombok.Data;

/**
 * 菜单相关接口响应用VO
 */
@Data
public class MenuMetaVo {

    // 菜单标题。用于配置页面的标题，会在菜单和标签页中显示。一般会配合国际化使用。
    private String title;
    // 图标。用于配置页面的图标，会在菜单和标签页中显示。一般会配合图标库使用，如果是http链接，会自动加载图片。
    private String icon;
    // 用于配置页面的激活图标，会在菜单中显示。一般会配合图标库使用，如果是http链接，会自动加载图片。
    private String activeIcon;
    // 是否缓存。用于配置页面是否开启缓存，开启后页面会缓存，不会重新加载，仅在标签页启用时有效。
    private boolean keepalive;
    // 用于配置页面是否在菜单中隐藏，隐藏后页面不会在菜单中显示。
    private boolean hideInMenu;
    // 用于配置页面是否在标签页中隐藏，隐藏后页面不会在标签页中显示。
    private boolean hideInTab;
    // 用于配置页面是否在面包屑中隐藏，隐藏后页面不会在面包屑中显示。
    private boolean hideInBreadcrumb;
    // 用于配置页面的子页面是否在菜单中隐藏，隐藏后子页面不会在菜单中显示。
    private boolean hideChildrenInMenu;
    // 用于配置页面的权限，只有拥有对应权限的用户才能访问页面，不配置则不需要权限。
    private String[] authority;
    // 用于配置当前激活的菜单，有时候页面没有显示在菜单内，需要激活父级菜单时使用。
    private String activePath;
    // 用于配置页面是否固定标签页，固定后页面不可关闭。
    private boolean affixTab;
    // 用于配置页面固定标签页的排序, 采用升序排序。
    private Integer affixTabOrder;
    // 用于配置页面是否忽略权限，直接可以访问。
    private boolean ignoreAccess;
    // 用于配置内嵌页面的 iframe 地址，设置后会在当前页面内嵌对应的页面。
    private String iframeSrc;
    // 用于配置外链跳转路径，会在新窗口打开。
    private String link;
    // 用于配置标签页最大打开数量，设置后会在打开新标签页时自动关闭最早打开的标签页(仅在打开同名标签页时生效)。
    private Integer maxNumOfOpenTab;
    // 设置为 true 时，会在新窗口打开页面。
    private boolean openInNewWindow;
    // 排序。用于配置页面的排序，用于路由到菜单排序。
    // 注意: 排序仅针对一级菜单有效，二级菜单的排序需要在对应的一级菜单中按代码顺序设置。
    private Integer order;
    // 用于配置页面的菜单参数，会在菜单中传递给页面。
    private Object query;

    // 用于配置页面的徽标，会在菜单显示。
    private String badge;
    // 用于配置页面的徽标类型，dot 为小红点，normal 为文本。
    private String badgeType;
    // 用于配置页面的徽标颜色。
    private String badgeVariants;
    // 用于配置页面在菜单可以看到，但是访问会被重定向到403。
    private boolean menuVisibleWithForbidden;
}
