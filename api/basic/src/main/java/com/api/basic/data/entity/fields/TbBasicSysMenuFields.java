package com.api.basic.data.entity.fields;

/**
 * 菜单信息表字段常量类
 */
public class TbBasicSysMenuFields {
    /**
     * ID
     */
    public static final String ID = "id";
    /**
     * 父级id
     */
    public static final String PARENTID = "parentId";
    /**
     * 类型。 1：目录，2：页面，3：权限。
     */
    public static final String TYPE = "type";
    /**
     * 菜单名
     */
    public static final String NAME = "name";
    /**
     * 路由地址
     */
    public static final String PATH = "path";
    /**
     * 组件地址
     */
    public static final String COMPONENT = "component";
    /**
     * 权限标识
     */
    public static final String IDENTIFYING = "identifying";
    /**
     * 状态。1：正常，2：禁用。
     */
    public static final String STATUS = "status";
    /**
     * 删除状态。1：未删除，2：已删除。
     */
    public static final String ISDELETE = "isDelete";
    /**
     * 菜单标题
     */
    public static final String TITLE = "title";
    /**
     * 图标
     */
    public static final String ICON = "icon";
    /**
     * 用于配置页面的激活图标，会在菜单中显示。一般会配合图标库使用，如果是http链接，会自动加载图片。
     */
    public static final String ACTIVEICON = "activeIcon";
    /**
     * 用于配置页面的排序，用于路由在菜单中的排序。排序仅针对一级菜单有效，二级菜单的排序需要在对应的一级菜单中按代码顺序设置。
     */
    public static final String ORDER = "order";
    /**
     * 用于配置页面是否开启缓存，开启后页面会缓存，不会重新加载，仅在标签页启用时有效。1：是，2：否。
     */
    public static final String KEEPALIVE = "keepalive";
    /**
     * 用于配置页面是否在菜单中隐藏，隐藏后页面不会在菜单中显示。1：隐藏，2：显示。
     */
    public static final String HIDEINMENU = "hideInMenu";
    /**
     * 用于配置页面是否在标签页中隐藏，隐藏后页面不会在标签页中显示。1：隐藏，2：显示。
     */
    public static final String HIDEINTAB = "hideInTab";
    /**
     * 用于配置页面的子页面是否在菜单中隐藏，隐藏后子页面不会在菜单中显示。。1：是，2：否。
     */
    public static final String HIDECHILDRENINMENU = "hideChildrenInMenu";
    /**
     * 用于配置页面的权限，只有拥有对应权限的用户才能访问页面，不配置则不需要权限。
     */
    public static final String AUTHORITY = "authority";
    /**
     * 用于配置当前激活的菜单，有时候页面没有显示在菜单内，需要激活父级菜单时使用。
     */
    public static final String ACTIVEID = "activeId";
    /**
     * 用于配置页面是否固定标签页，固定后页面不可关闭。1：是，2：否
     */
    public static final String AFFIXTAB = "affixTab";
    /**
     * 用于配置页面固定标签页的排序, 采用升序排序。
     */
    public static final String AFFIXTABORDER = "affixTabOrder";
    /**
     * 用于配置页面是否忽略权限，直接可以访问。1：是，2：否
     */
    public static final String IGNOREACCESS = "ignoreAccess";
    /**
     * 用于配置是否是内嵌页面的iframe地址
     */
    public static final String ISIFRAME = "isIframe";
    /**
     * 用于配置内嵌页面的 iframe 地址，设置后会在当前页面内嵌对应的页面。
     */
    public static final String IFRAMESRC = "iframeSrc";
    /**
     * 用于配置是否是外链跳转路径。1：是，2：否
     */
    public static final String ISLINK = "isLink";
    /**
     * 用于配置外链跳转路径，会在新窗口打开。
     */
    public static final String LINK = "link";
    /**
     * 用于配置标签页最大打开数量，设置后会在打开新标签页时自动关闭最早打开的标签页(仅在打开同名标签页时生效)。
     */
    public static final String MAXNUMOFOPENTAB = "maxNumOfOpenTab";
    /**
     * 用于配置页面是否在新窗口中打开。1：是，2：否
     */
    public static final String OPENINNEWWINDOW = "openInNewWindow";
    /**
     * 用于配置页面的菜单参数，会在菜单中传递给页面。
     */
    public static final String QUERY = "query";
    /**
     * 是否在管理后台首页导航中显示。1：是，2：否
     */
    public static final String SHOWINADMINHOME = "showInAdminHome";
    /**
     * 创建时间
     */
    public static final String CREATEDAT = "createdAt";
    /**
     * 创建者
     */
    public static final String CREATEDUSERID = "createdUserId";
    /**
     * 更新时间
     */
    public static final String UPDATEDAT = "updatedAt";
    /**
     * 最后更新者
     */
    public static final String UPDATEDUSERID = "updatedUserId";
}