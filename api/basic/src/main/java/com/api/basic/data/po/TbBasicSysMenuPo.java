package com.api.basic.data.po;

import com.api.basic.data.entity.TbBasicSysMenu;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.SuperBuilder;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * 菜单信息表条件实体
 */
@EqualsAndHashCode(doNotUseGetters = true, callSuper = true)
@Data
@SuperBuilder
public class TbBasicSysMenuPo extends TbBasicSysMenu {

    public TbBasicSysMenuPo(){
        super();
    }

    // 条件：ID，等于
    private Integer whereId;
    // 条件：ID，在列表中
    private List<Integer> whereInIds;
    // 条件：ID，在列表中，or连接
    private List<Integer> whereInOrIds;
    // 排除条件：ID
    private Integer whereNotId;
    // 条件：ID，不在列表中
    private List<Integer> whereNotInIds;
    // 条件：ID，为空
    private Boolean whereIsNullId;
    // 条件：ID，不为空
    private Boolean whereIsNotNullId;
    // 条件：ID，为空字符串
    private Boolean whereIsEmptyId;
    // 条件：ID，不为空字符串
    private Boolean whereIsNotEmptyId;
    // 条件：ID，大于
    private Integer whereGtId;
    // 条件：ID，大于等于
    private Integer whereGteId;
    // 条件：ID，小于
    private Integer whereLtId;
    // 条件：ID，小于等于
    private Integer whereLteId;
    // 条件：ID，开始范围
    private Integer whereStartId;
    // 条件：ID，结束范围
    private Integer whereEndId;
    // 条件：父级id，等于
    private Integer whereParentId;
    // 条件：父级id，在列表中
    private List<Integer> whereInParentIds;
    // 条件：父级id，在列表中，or连接
    private List<Integer> whereInOrParentIds;
    // 排除条件：父级id
    private Integer whereNotParentId;
    // 条件：父级id，不在列表中
    private List<Integer> whereNotInParentIds;
    // 条件：父级id，为空
    private Boolean whereIsNullParentId;
    // 条件：父级id，不为空
    private Boolean whereIsNotNullParentId;
    // 条件：父级id，为空字符串
    private Boolean whereIsEmptyParentId;
    // 条件：父级id，不为空字符串
    private Boolean whereIsNotEmptyParentId;
    // 条件：父级id，大于
    private Integer whereGtParentId;
    // 条件：父级id，大于等于
    private Integer whereGteParentId;
    // 条件：父级id，小于
    private Integer whereLtParentId;
    // 条件：父级id，小于等于
    private Integer whereLteParentId;
    // 条件：父级id，开始范围
    private Integer whereStartParentId;
    // 条件：父级id，结束范围
    private Integer whereEndParentId;
    // 条件：类型。 1：目录，2：页面，3：权限。，等于
    private Integer whereType;
    // 条件：类型。 1：目录，2：页面，3：权限。，在列表中
    private List<Integer> whereInTypes;
    // 条件：类型。 1：目录，2：页面，3：权限。，在列表中，or连接
    private List<Integer> whereInOrTypes;
    // 排除条件：类型。 1：目录，2：页面，3：权限。
    private Integer whereNotType;
    // 条件：类型。 1：目录，2：页面，3：权限。，不在列表中
    private List<Integer> whereNotInTypes;
    // 条件：类型。 1：目录，2：页面，3：权限。，为空
    private Boolean whereIsNullType;
    // 条件：类型。 1：目录，2：页面，3：权限。，不为空
    private Boolean whereIsNotNullType;
    // 条件：类型。 1：目录，2：页面，3：权限。，为空字符串
    private Boolean whereIsEmptyType;
    // 条件：类型。 1：目录，2：页面，3：权限。，不为空字符串
    private Boolean whereIsNotEmptyType;
    // 条件：类型。 1：目录，2：页面，3：权限。，大于
    private Integer whereGtType;
    // 条件：类型。 1：目录，2：页面，3：权限。，大于等于
    private Integer whereGteType;
    // 条件：类型。 1：目录，2：页面，3：权限。，小于
    private Integer whereLtType;
    // 条件：类型。 1：目录，2：页面，3：权限。，小于等于
    private Integer whereLteType;
    // 条件：类型。 1：目录，2：页面，3：权限。，开始范围
    private Integer whereStartType;
    // 条件：类型。 1：目录，2：页面，3：权限。，结束范围
    private Integer whereEndType;
    // 条件：菜单名，等于
    private String whereName;
    // 条件：菜单名，在列表中
    private List<String> whereInNames;
    // 条件：菜单名，在列表中，or连接
    private List<String> whereInOrNames;
    // 排除条件：菜单名
    private String whereNotName;
    // 条件：菜单名，不在列表中
    private List<String> whereNotInNames;
    // 条件：菜单名，为空
    private Boolean whereIsNullName;
    // 条件：菜单名，不为空
    private Boolean whereIsNotNullName;
    // 条件：菜单名，为空字符串
    private Boolean whereIsEmptyName;
    // 条件：菜单名，不为空字符串
    private Boolean whereIsNotEmptyName;
    // 条件：菜单名，大于
    private String whereGtName;
    // 条件：菜单名，大于等于
    private String whereGteName;
    // 条件：菜单名，小于
    private String whereLtName;
    // 条件：菜单名，小于等于
    private String whereLteName;
    // 条件：菜单名，模糊查询
    private String whereLikeName;
    // 条件：菜单名，开始范围
    private String whereStartName;
    // 条件：菜单名，结束范围
    private String whereEndName;
    // 条件：路由地址，等于
    private String wherePath;
    // 条件：路由地址，在列表中
    private List<String> whereInPaths;
    // 条件：路由地址，在列表中，or连接
    private List<String> whereInOrPaths;
    // 排除条件：路由地址
    private String whereNotPath;
    // 条件：路由地址，不在列表中
    private List<String> whereNotInPaths;
    // 条件：路由地址，为空
    private Boolean whereIsNullPath;
    // 条件：路由地址，不为空
    private Boolean whereIsNotNullPath;
    // 条件：路由地址，为空字符串
    private Boolean whereIsEmptyPath;
    // 条件：路由地址，不为空字符串
    private Boolean whereIsNotEmptyPath;
    // 条件：路由地址，大于
    private String whereGtPath;
    // 条件：路由地址，大于等于
    private String whereGtePath;
    // 条件：路由地址，小于
    private String whereLtPath;
    // 条件：路由地址，小于等于
    private String whereLtePath;
    // 条件：路由地址，模糊查询
    private String whereLikePath;
    // 条件：路由地址，开始范围
    private String whereStartPath;
    // 条件：路由地址，结束范围
    private String whereEndPath;
    // 条件：组件地址，等于
    private String whereComponent;
    // 条件：组件地址，在列表中
    private List<String> whereInComponents;
    // 条件：组件地址，在列表中，or连接
    private List<String> whereInOrComponents;
    // 排除条件：组件地址
    private String whereNotComponent;
    // 条件：组件地址，不在列表中
    private List<String> whereNotInComponents;
    // 条件：组件地址，为空
    private Boolean whereIsNullComponent;
    // 条件：组件地址，不为空
    private Boolean whereIsNotNullComponent;
    // 条件：组件地址，为空字符串
    private Boolean whereIsEmptyComponent;
    // 条件：组件地址，不为空字符串
    private Boolean whereIsNotEmptyComponent;
    // 条件：组件地址，大于
    private String whereGtComponent;
    // 条件：组件地址，大于等于
    private String whereGteComponent;
    // 条件：组件地址，小于
    private String whereLtComponent;
    // 条件：组件地址，小于等于
    private String whereLteComponent;
    // 条件：组件地址，模糊查询
    private String whereLikeComponent;
    // 条件：组件地址，开始范围
    private String whereStartComponent;
    // 条件：组件地址，结束范围
    private String whereEndComponent;
    // 条件：权限标识，等于
    private String whereIdentifying;
    // 条件：权限标识，在列表中
    private List<String> whereInIdentifyings;
    // 条件：权限标识，在列表中，or连接
    private List<String> whereInOrIdentifyings;
    // 排除条件：权限标识
    private String whereNotIdentifying;
    // 条件：权限标识，不在列表中
    private List<String> whereNotInIdentifyings;
    // 条件：权限标识，为空
    private Boolean whereIsNullIdentifying;
    // 条件：权限标识，不为空
    private Boolean whereIsNotNullIdentifying;
    // 条件：权限标识，为空字符串
    private Boolean whereIsEmptyIdentifying;
    // 条件：权限标识，不为空字符串
    private Boolean whereIsNotEmptyIdentifying;
    // 条件：权限标识，大于
    private String whereGtIdentifying;
    // 条件：权限标识，大于等于
    private String whereGteIdentifying;
    // 条件：权限标识，小于
    private String whereLtIdentifying;
    // 条件：权限标识，小于等于
    private String whereLteIdentifying;
    // 条件：权限标识，模糊查询
    private String whereLikeIdentifying;
    // 条件：权限标识，开始范围
    private String whereStartIdentifying;
    // 条件：权限标识，结束范围
    private String whereEndIdentifying;
    // 条件：状态。1：正常，2：禁用。，等于
    private Integer whereStatus;
    // 条件：状态。1：正常，2：禁用。，在列表中
    private List<Integer> whereInStatuss;
    // 条件：状态。1：正常，2：禁用。，在列表中，or连接
    private List<Integer> whereInOrStatuss;
    // 排除条件：状态。1：正常，2：禁用。
    private Integer whereNotStatus;
    // 条件：状态。1：正常，2：禁用。，不在列表中
    private List<Integer> whereNotInStatuss;
    // 条件：状态。1：正常，2：禁用。，为空
    private Boolean whereIsNullStatus;
    // 条件：状态。1：正常，2：禁用。，不为空
    private Boolean whereIsNotNullStatus;
    // 条件：状态。1：正常，2：禁用。，为空字符串
    private Boolean whereIsEmptyStatus;
    // 条件：状态。1：正常，2：禁用。，不为空字符串
    private Boolean whereIsNotEmptyStatus;
    // 条件：状态。1：正常，2：禁用。，大于
    private Integer whereGtStatus;
    // 条件：状态。1：正常，2：禁用。，大于等于
    private Integer whereGteStatus;
    // 条件：状态。1：正常，2：禁用。，小于
    private Integer whereLtStatus;
    // 条件：状态。1：正常，2：禁用。，小于等于
    private Integer whereLteStatus;
    // 条件：状态。1：正常，2：禁用。，开始范围
    private Integer whereStartStatus;
    // 条件：状态。1：正常，2：禁用。，结束范围
    private Integer whereEndStatus;
    // 条件：删除状态。1：未删除，2：已删除。，等于
    private Integer whereIsDelete;
    // 条件：删除状态。1：未删除，2：已删除。，在列表中
    private List<Integer> whereInIsDeletes;
    // 条件：删除状态。1：未删除，2：已删除。，在列表中，or连接
    private List<Integer> whereInOrIsDeletes;
    // 排除条件：删除状态。1：未删除，2：已删除。
    private Integer whereNotIsDelete;
    // 条件：删除状态。1：未删除，2：已删除。，不在列表中
    private List<Integer> whereNotInIsDeletes;
    // 条件：删除状态。1：未删除，2：已删除。，为空
    private Boolean whereIsNullIsDelete;
    // 条件：删除状态。1：未删除，2：已删除。，不为空
    private Boolean whereIsNotNullIsDelete;
    // 条件：删除状态。1：未删除，2：已删除。，为空字符串
    private Boolean whereIsEmptyIsDelete;
    // 条件：删除状态。1：未删除，2：已删除。，不为空字符串
    private Boolean whereIsNotEmptyIsDelete;
    // 条件：删除状态。1：未删除，2：已删除。，大于
    private Integer whereGtIsDelete;
    // 条件：删除状态。1：未删除，2：已删除。，大于等于
    private Integer whereGteIsDelete;
    // 条件：删除状态。1：未删除，2：已删除。，小于
    private Integer whereLtIsDelete;
    // 条件：删除状态。1：未删除，2：已删除。，小于等于
    private Integer whereLteIsDelete;
    // 条件：删除状态。1：未删除，2：已删除。，开始范围
    private Integer whereStartIsDelete;
    // 条件：删除状态。1：未删除，2：已删除。，结束范围
    private Integer whereEndIsDelete;
    // 条件：菜单标题，等于
    private String whereTitle;
    // 条件：菜单标题，在列表中
    private List<String> whereInTitles;
    // 条件：菜单标题，在列表中，or连接
    private List<String> whereInOrTitles;
    // 排除条件：菜单标题
    private String whereNotTitle;
    // 条件：菜单标题，不在列表中
    private List<String> whereNotInTitles;
    // 条件：菜单标题，为空
    private Boolean whereIsNullTitle;
    // 条件：菜单标题，不为空
    private Boolean whereIsNotNullTitle;
    // 条件：菜单标题，为空字符串
    private Boolean whereIsEmptyTitle;
    // 条件：菜单标题，不为空字符串
    private Boolean whereIsNotEmptyTitle;
    // 条件：菜单标题，大于
    private String whereGtTitle;
    // 条件：菜单标题，大于等于
    private String whereGteTitle;
    // 条件：菜单标题，小于
    private String whereLtTitle;
    // 条件：菜单标题，小于等于
    private String whereLteTitle;
    // 条件：菜单标题，模糊查询
    private String whereLikeTitle;
    // 条件：菜单标题，开始范围
    private String whereStartTitle;
    // 条件：菜单标题，结束范围
    private String whereEndTitle;
    // 条件：图标，等于
    private String whereIcon;
    // 条件：图标，在列表中
    private List<String> whereInIcons;
    // 条件：图标，在列表中，or连接
    private List<String> whereInOrIcons;
    // 排除条件：图标
    private String whereNotIcon;
    // 条件：图标，不在列表中
    private List<String> whereNotInIcons;
    // 条件：图标，为空
    private Boolean whereIsNullIcon;
    // 条件：图标，不为空
    private Boolean whereIsNotNullIcon;
    // 条件：图标，为空字符串
    private Boolean whereIsEmptyIcon;
    // 条件：图标，不为空字符串
    private Boolean whereIsNotEmptyIcon;
    // 条件：图标，大于
    private String whereGtIcon;
    // 条件：图标，大于等于
    private String whereGteIcon;
    // 条件：图标，小于
    private String whereLtIcon;
    // 条件：图标，小于等于
    private String whereLteIcon;
    // 条件：图标，模糊查询
    private String whereLikeIcon;
    // 条件：图标，开始范围
    private String whereStartIcon;
    // 条件：图标，结束范围
    private String whereEndIcon;
    // 条件：用于配置页面的激活图标，会在菜单中显示。一般会配合图标库使用，如果是http链接，会自动加载图片。，等于
    private String whereActiveIcon;
    // 条件：用于配置页面的激活图标，会在菜单中显示。一般会配合图标库使用，如果是http链接，会自动加载图片。，在列表中
    private List<String> whereInActiveIcons;
    // 条件：用于配置页面的激活图标，会在菜单中显示。一般会配合图标库使用，如果是http链接，会自动加载图片。，在列表中，or连接
    private List<String> whereInOrActiveIcons;
    // 排除条件：用于配置页面的激活图标，会在菜单中显示。一般会配合图标库使用，如果是http链接，会自动加载图片。
    private String whereNotActiveIcon;
    // 条件：用于配置页面的激活图标，会在菜单中显示。一般会配合图标库使用，如果是http链接，会自动加载图片。，不在列表中
    private List<String> whereNotInActiveIcons;
    // 条件：用于配置页面的激活图标，会在菜单中显示。一般会配合图标库使用，如果是http链接，会自动加载图片。，为空
    private Boolean whereIsNullActiveIcon;
    // 条件：用于配置页面的激活图标，会在菜单中显示。一般会配合图标库使用，如果是http链接，会自动加载图片。，不为空
    private Boolean whereIsNotNullActiveIcon;
    // 条件：用于配置页面的激活图标，会在菜单中显示。一般会配合图标库使用，如果是http链接，会自动加载图片。，为空字符串
    private Boolean whereIsEmptyActiveIcon;
    // 条件：用于配置页面的激活图标，会在菜单中显示。一般会配合图标库使用，如果是http链接，会自动加载图片。，不为空字符串
    private Boolean whereIsNotEmptyActiveIcon;
    // 条件：用于配置页面的激活图标，会在菜单中显示。一般会配合图标库使用，如果是http链接，会自动加载图片。，大于
    private String whereGtActiveIcon;
    // 条件：用于配置页面的激活图标，会在菜单中显示。一般会配合图标库使用，如果是http链接，会自动加载图片。，大于等于
    private String whereGteActiveIcon;
    // 条件：用于配置页面的激活图标，会在菜单中显示。一般会配合图标库使用，如果是http链接，会自动加载图片。，小于
    private String whereLtActiveIcon;
    // 条件：用于配置页面的激活图标，会在菜单中显示。一般会配合图标库使用，如果是http链接，会自动加载图片。，小于等于
    private String whereLteActiveIcon;
    // 条件：用于配置页面的激活图标，会在菜单中显示。一般会配合图标库使用，如果是http链接，会自动加载图片。，模糊查询
    private String whereLikeActiveIcon;
    // 条件：用于配置页面的激活图标，会在菜单中显示。一般会配合图标库使用，如果是http链接，会自动加载图片。，开始范围
    private String whereStartActiveIcon;
    // 条件：用于配置页面的激活图标，会在菜单中显示。一般会配合图标库使用，如果是http链接，会自动加载图片。，结束范围
    private String whereEndActiveIcon;
    // 条件：用于配置页面的排序，用于路由在菜单中的排序。排序仅针对一级菜单有效，二级菜单的排序需要在对应的一级菜单中按代码顺序设置。，等于
    private Integer whereOrder;
    // 条件：用于配置页面的排序，用于路由在菜单中的排序。排序仅针对一级菜单有效，二级菜单的排序需要在对应的一级菜单中按代码顺序设置。，在列表中
    private List<Integer> whereInOrders;
    // 条件：用于配置页面的排序，用于路由在菜单中的排序。排序仅针对一级菜单有效，二级菜单的排序需要在对应的一级菜单中按代码顺序设置。，在列表中，or连接
    private List<Integer> whereInOrOrders;
    // 排除条件：用于配置页面的排序，用于路由在菜单中的排序。排序仅针对一级菜单有效，二级菜单的排序需要在对应的一级菜单中按代码顺序设置。
    private Integer whereNotOrder;
    // 条件：用于配置页面的排序，用于路由在菜单中的排序。排序仅针对一级菜单有效，二级菜单的排序需要在对应的一级菜单中按代码顺序设置。，不在列表中
    private List<Integer> whereNotInOrders;
    // 条件：用于配置页面的排序，用于路由在菜单中的排序。排序仅针对一级菜单有效，二级菜单的排序需要在对应的一级菜单中按代码顺序设置。，为空
    private Boolean whereIsNullOrder;
    // 条件：用于配置页面的排序，用于路由在菜单中的排序。排序仅针对一级菜单有效，二级菜单的排序需要在对应的一级菜单中按代码顺序设置。，不为空
    private Boolean whereIsNotNullOrder;
    // 条件：用于配置页面的排序，用于路由在菜单中的排序。排序仅针对一级菜单有效，二级菜单的排序需要在对应的一级菜单中按代码顺序设置。，为空字符串
    private Boolean whereIsEmptyOrder;
    // 条件：用于配置页面的排序，用于路由在菜单中的排序。排序仅针对一级菜单有效，二级菜单的排序需要在对应的一级菜单中按代码顺序设置。，不为空字符串
    private Boolean whereIsNotEmptyOrder;
    // 条件：用于配置页面的排序，用于路由在菜单中的排序。排序仅针对一级菜单有效，二级菜单的排序需要在对应的一级菜单中按代码顺序设置。，大于
    private Integer whereGtOrder;
    // 条件：用于配置页面的排序，用于路由在菜单中的排序。排序仅针对一级菜单有效，二级菜单的排序需要在对应的一级菜单中按代码顺序设置。，大于等于
    private Integer whereGteOrder;
    // 条件：用于配置页面的排序，用于路由在菜单中的排序。排序仅针对一级菜单有效，二级菜单的排序需要在对应的一级菜单中按代码顺序设置。，小于
    private Integer whereLtOrder;
    // 条件：用于配置页面的排序，用于路由在菜单中的排序。排序仅针对一级菜单有效，二级菜单的排序需要在对应的一级菜单中按代码顺序设置。，小于等于
    private Integer whereLteOrder;
    // 条件：用于配置页面的排序，用于路由在菜单中的排序。排序仅针对一级菜单有效，二级菜单的排序需要在对应的一级菜单中按代码顺序设置。，开始范围
    private Integer whereStartOrder;
    // 条件：用于配置页面的排序，用于路由在菜单中的排序。排序仅针对一级菜单有效，二级菜单的排序需要在对应的一级菜单中按代码顺序设置。，结束范围
    private Integer whereEndOrder;
    // 条件：用于配置页面是否开启缓存，开启后页面会缓存，不会重新加载，仅在标签页启用时有效。1：是，2：否。，等于
    private Integer whereKeepalive;
    // 条件：用于配置页面是否开启缓存，开启后页面会缓存，不会重新加载，仅在标签页启用时有效。1：是，2：否。，在列表中
    private List<Integer> whereInKeepalives;
    // 条件：用于配置页面是否开启缓存，开启后页面会缓存，不会重新加载，仅在标签页启用时有效。1：是，2：否。，在列表中，or连接
    private List<Integer> whereInOrKeepalives;
    // 排除条件：用于配置页面是否开启缓存，开启后页面会缓存，不会重新加载，仅在标签页启用时有效。1：是，2：否。
    private Integer whereNotKeepalive;
    // 条件：用于配置页面是否开启缓存，开启后页面会缓存，不会重新加载，仅在标签页启用时有效。1：是，2：否。，不在列表中
    private List<Integer> whereNotInKeepalives;
    // 条件：用于配置页面是否开启缓存，开启后页面会缓存，不会重新加载，仅在标签页启用时有效。1：是，2：否。，为空
    private Boolean whereIsNullKeepalive;
    // 条件：用于配置页面是否开启缓存，开启后页面会缓存，不会重新加载，仅在标签页启用时有效。1：是，2：否。，不为空
    private Boolean whereIsNotNullKeepalive;
    // 条件：用于配置页面是否开启缓存，开启后页面会缓存，不会重新加载，仅在标签页启用时有效。1：是，2：否。，为空字符串
    private Boolean whereIsEmptyKeepalive;
    // 条件：用于配置页面是否开启缓存，开启后页面会缓存，不会重新加载，仅在标签页启用时有效。1：是，2：否。，不为空字符串
    private Boolean whereIsNotEmptyKeepalive;
    // 条件：用于配置页面是否开启缓存，开启后页面会缓存，不会重新加载，仅在标签页启用时有效。1：是，2：否。，大于
    private Integer whereGtKeepalive;
    // 条件：用于配置页面是否开启缓存，开启后页面会缓存，不会重新加载，仅在标签页启用时有效。1：是，2：否。，大于等于
    private Integer whereGteKeepalive;
    // 条件：用于配置页面是否开启缓存，开启后页面会缓存，不会重新加载，仅在标签页启用时有效。1：是，2：否。，小于
    private Integer whereLtKeepalive;
    // 条件：用于配置页面是否开启缓存，开启后页面会缓存，不会重新加载，仅在标签页启用时有效。1：是，2：否。，小于等于
    private Integer whereLteKeepalive;
    // 条件：用于配置页面是否开启缓存，开启后页面会缓存，不会重新加载，仅在标签页启用时有效。1：是，2：否。，开始范围
    private Integer whereStartKeepalive;
    // 条件：用于配置页面是否开启缓存，开启后页面会缓存，不会重新加载，仅在标签页启用时有效。1：是，2：否。，结束范围
    private Integer whereEndKeepalive;
    // 条件：用于配置页面是否在菜单中隐藏，隐藏后页面不会在菜单中显示。1：隐藏，2：显示。，等于
    private Integer whereHideInMenu;
    // 条件：用于配置页面是否在菜单中隐藏，隐藏后页面不会在菜单中显示。1：隐藏，2：显示。，在列表中
    private List<Integer> whereInHideInMenus;
    // 条件：用于配置页面是否在菜单中隐藏，隐藏后页面不会在菜单中显示。1：隐藏，2：显示。，在列表中，or连接
    private List<Integer> whereInOrHideInMenus;
    // 排除条件：用于配置页面是否在菜单中隐藏，隐藏后页面不会在菜单中显示。1：隐藏，2：显示。
    private Integer whereNotHideInMenu;
    // 条件：用于配置页面是否在菜单中隐藏，隐藏后页面不会在菜单中显示。1：隐藏，2：显示。，不在列表中
    private List<Integer> whereNotInHideInMenus;
    // 条件：用于配置页面是否在菜单中隐藏，隐藏后页面不会在菜单中显示。1：隐藏，2：显示。，为空
    private Boolean whereIsNullHideInMenu;
    // 条件：用于配置页面是否在菜单中隐藏，隐藏后页面不会在菜单中显示。1：隐藏，2：显示。，不为空
    private Boolean whereIsNotNullHideInMenu;
    // 条件：用于配置页面是否在菜单中隐藏，隐藏后页面不会在菜单中显示。1：隐藏，2：显示。，为空字符串
    private Boolean whereIsEmptyHideInMenu;
    // 条件：用于配置页面是否在菜单中隐藏，隐藏后页面不会在菜单中显示。1：隐藏，2：显示。，不为空字符串
    private Boolean whereIsNotEmptyHideInMenu;
    // 条件：用于配置页面是否在菜单中隐藏，隐藏后页面不会在菜单中显示。1：隐藏，2：显示。，大于
    private Integer whereGtHideInMenu;
    // 条件：用于配置页面是否在菜单中隐藏，隐藏后页面不会在菜单中显示。1：隐藏，2：显示。，大于等于
    private Integer whereGteHideInMenu;
    // 条件：用于配置页面是否在菜单中隐藏，隐藏后页面不会在菜单中显示。1：隐藏，2：显示。，小于
    private Integer whereLtHideInMenu;
    // 条件：用于配置页面是否在菜单中隐藏，隐藏后页面不会在菜单中显示。1：隐藏，2：显示。，小于等于
    private Integer whereLteHideInMenu;
    // 条件：用于配置页面是否在菜单中隐藏，隐藏后页面不会在菜单中显示。1：隐藏，2：显示。，开始范围
    private Integer whereStartHideInMenu;
    // 条件：用于配置页面是否在菜单中隐藏，隐藏后页面不会在菜单中显示。1：隐藏，2：显示。，结束范围
    private Integer whereEndHideInMenu;
    // 条件：用于配置页面是否在标签页中隐藏，隐藏后页面不会在标签页中显示。1：隐藏，2：显示。，等于
    private Integer whereHideInTab;
    // 条件：用于配置页面是否在标签页中隐藏，隐藏后页面不会在标签页中显示。1：隐藏，2：显示。，在列表中
    private List<Integer> whereInHideInTabs;
    // 条件：用于配置页面是否在标签页中隐藏，隐藏后页面不会在标签页中显示。1：隐藏，2：显示。，在列表中，or连接
    private List<Integer> whereInOrHideInTabs;
    // 排除条件：用于配置页面是否在标签页中隐藏，隐藏后页面不会在标签页中显示。1：隐藏，2：显示。
    private Integer whereNotHideInTab;
    // 条件：用于配置页面是否在标签页中隐藏，隐藏后页面不会在标签页中显示。1：隐藏，2：显示。，不在列表中
    private List<Integer> whereNotInHideInTabs;
    // 条件：用于配置页面是否在标签页中隐藏，隐藏后页面不会在标签页中显示。1：隐藏，2：显示。，为空
    private Boolean whereIsNullHideInTab;
    // 条件：用于配置页面是否在标签页中隐藏，隐藏后页面不会在标签页中显示。1：隐藏，2：显示。，不为空
    private Boolean whereIsNotNullHideInTab;
    // 条件：用于配置页面是否在标签页中隐藏，隐藏后页面不会在标签页中显示。1：隐藏，2：显示。，为空字符串
    private Boolean whereIsEmptyHideInTab;
    // 条件：用于配置页面是否在标签页中隐藏，隐藏后页面不会在标签页中显示。1：隐藏，2：显示。，不为空字符串
    private Boolean whereIsNotEmptyHideInTab;
    // 条件：用于配置页面是否在标签页中隐藏，隐藏后页面不会在标签页中显示。1：隐藏，2：显示。，大于
    private Integer whereGtHideInTab;
    // 条件：用于配置页面是否在标签页中隐藏，隐藏后页面不会在标签页中显示。1：隐藏，2：显示。，大于等于
    private Integer whereGteHideInTab;
    // 条件：用于配置页面是否在标签页中隐藏，隐藏后页面不会在标签页中显示。1：隐藏，2：显示。，小于
    private Integer whereLtHideInTab;
    // 条件：用于配置页面是否在标签页中隐藏，隐藏后页面不会在标签页中显示。1：隐藏，2：显示。，小于等于
    private Integer whereLteHideInTab;
    // 条件：用于配置页面是否在标签页中隐藏，隐藏后页面不会在标签页中显示。1：隐藏，2：显示。，开始范围
    private Integer whereStartHideInTab;
    // 条件：用于配置页面是否在标签页中隐藏，隐藏后页面不会在标签页中显示。1：隐藏，2：显示。，结束范围
    private Integer whereEndHideInTab;
    // 条件：用于配置页面的子页面是否在菜单中隐藏，隐藏后子页面不会在菜单中显示。。1：是，2：否。，等于
    private Integer whereHideChildrenInMenu;
    // 条件：用于配置页面的子页面是否在菜单中隐藏，隐藏后子页面不会在菜单中显示。。1：是，2：否。，在列表中
    private List<Integer> whereInHideChildrenInMenus;
    // 条件：用于配置页面的子页面是否在菜单中隐藏，隐藏后子页面不会在菜单中显示。。1：是，2：否。，在列表中，or连接
    private List<Integer> whereInOrHideChildrenInMenus;
    // 排除条件：用于配置页面的子页面是否在菜单中隐藏，隐藏后子页面不会在菜单中显示。。1：是，2：否。
    private Integer whereNotHideChildrenInMenu;
    // 条件：用于配置页面的子页面是否在菜单中隐藏，隐藏后子页面不会在菜单中显示。。1：是，2：否。，不在列表中
    private List<Integer> whereNotInHideChildrenInMenus;
    // 条件：用于配置页面的子页面是否在菜单中隐藏，隐藏后子页面不会在菜单中显示。。1：是，2：否。，为空
    private Boolean whereIsNullHideChildrenInMenu;
    // 条件：用于配置页面的子页面是否在菜单中隐藏，隐藏后子页面不会在菜单中显示。。1：是，2：否。，不为空
    private Boolean whereIsNotNullHideChildrenInMenu;
    // 条件：用于配置页面的子页面是否在菜单中隐藏，隐藏后子页面不会在菜单中显示。。1：是，2：否。，为空字符串
    private Boolean whereIsEmptyHideChildrenInMenu;
    // 条件：用于配置页面的子页面是否在菜单中隐藏，隐藏后子页面不会在菜单中显示。。1：是，2：否。，不为空字符串
    private Boolean whereIsNotEmptyHideChildrenInMenu;
    // 条件：用于配置页面的子页面是否在菜单中隐藏，隐藏后子页面不会在菜单中显示。。1：是，2：否。，大于
    private Integer whereGtHideChildrenInMenu;
    // 条件：用于配置页面的子页面是否在菜单中隐藏，隐藏后子页面不会在菜单中显示。。1：是，2：否。，大于等于
    private Integer whereGteHideChildrenInMenu;
    // 条件：用于配置页面的子页面是否在菜单中隐藏，隐藏后子页面不会在菜单中显示。。1：是，2：否。，小于
    private Integer whereLtHideChildrenInMenu;
    // 条件：用于配置页面的子页面是否在菜单中隐藏，隐藏后子页面不会在菜单中显示。。1：是，2：否。，小于等于
    private Integer whereLteHideChildrenInMenu;
    // 条件：用于配置页面的子页面是否在菜单中隐藏，隐藏后子页面不会在菜单中显示。。1：是，2：否。，开始范围
    private Integer whereStartHideChildrenInMenu;
    // 条件：用于配置页面的子页面是否在菜单中隐藏，隐藏后子页面不会在菜单中显示。。1：是，2：否。，结束范围
    private Integer whereEndHideChildrenInMenu;
    // 条件：用于配置页面的权限，只有拥有对应权限的用户才能访问页面，不配置则不需要权限。，等于
    private String whereAuthority;
    // 条件：用于配置页面的权限，只有拥有对应权限的用户才能访问页面，不配置则不需要权限。，在列表中
    private List<String> whereInAuthoritys;
    // 条件：用于配置页面的权限，只有拥有对应权限的用户才能访问页面，不配置则不需要权限。，在列表中，or连接
    private List<String> whereInOrAuthoritys;
    // 排除条件：用于配置页面的权限，只有拥有对应权限的用户才能访问页面，不配置则不需要权限。
    private String whereNotAuthority;
    // 条件：用于配置页面的权限，只有拥有对应权限的用户才能访问页面，不配置则不需要权限。，不在列表中
    private List<String> whereNotInAuthoritys;
    // 条件：用于配置页面的权限，只有拥有对应权限的用户才能访问页面，不配置则不需要权限。，为空
    private Boolean whereIsNullAuthority;
    // 条件：用于配置页面的权限，只有拥有对应权限的用户才能访问页面，不配置则不需要权限。，不为空
    private Boolean whereIsNotNullAuthority;
    // 条件：用于配置页面的权限，只有拥有对应权限的用户才能访问页面，不配置则不需要权限。，为空字符串
    private Boolean whereIsEmptyAuthority;
    // 条件：用于配置页面的权限，只有拥有对应权限的用户才能访问页面，不配置则不需要权限。，不为空字符串
    private Boolean whereIsNotEmptyAuthority;
    // 条件：用于配置页面的权限，只有拥有对应权限的用户才能访问页面，不配置则不需要权限。，大于
    private String whereGtAuthority;
    // 条件：用于配置页面的权限，只有拥有对应权限的用户才能访问页面，不配置则不需要权限。，大于等于
    private String whereGteAuthority;
    // 条件：用于配置页面的权限，只有拥有对应权限的用户才能访问页面，不配置则不需要权限。，小于
    private String whereLtAuthority;
    // 条件：用于配置页面的权限，只有拥有对应权限的用户才能访问页面，不配置则不需要权限。，小于等于
    private String whereLteAuthority;
    // 条件：用于配置页面的权限，只有拥有对应权限的用户才能访问页面，不配置则不需要权限。，模糊查询
    private String whereLikeAuthority;
    // 条件：用于配置页面的权限，只有拥有对应权限的用户才能访问页面，不配置则不需要权限。，开始范围
    private String whereStartAuthority;
    // 条件：用于配置页面的权限，只有拥有对应权限的用户才能访问页面，不配置则不需要权限。，结束范围
    private String whereEndAuthority;
    // 条件：用于配置当前激活的菜单，有时候页面没有显示在菜单内，需要激活父级菜单时使用。，等于
    private Integer whereActiveId;
    // 条件：用于配置当前激活的菜单，有时候页面没有显示在菜单内，需要激活父级菜单时使用。，在列表中
    private List<Integer> whereInActiveIds;
    // 条件：用于配置当前激活的菜单，有时候页面没有显示在菜单内，需要激活父级菜单时使用。，在列表中，or连接
    private List<Integer> whereInOrActiveIds;
    // 排除条件：用于配置当前激活的菜单，有时候页面没有显示在菜单内，需要激活父级菜单时使用。
    private Integer whereNotActiveId;
    // 条件：用于配置当前激活的菜单，有时候页面没有显示在菜单内，需要激活父级菜单时使用。，不在列表中
    private List<Integer> whereNotInActiveIds;
    // 条件：用于配置当前激活的菜单，有时候页面没有显示在菜单内，需要激活父级菜单时使用。，为空
    private Boolean whereIsNullActiveId;
    // 条件：用于配置当前激活的菜单，有时候页面没有显示在菜单内，需要激活父级菜单时使用。，不为空
    private Boolean whereIsNotNullActiveId;
    // 条件：用于配置当前激活的菜单，有时候页面没有显示在菜单内，需要激活父级菜单时使用。，为空字符串
    private Boolean whereIsEmptyActiveId;
    // 条件：用于配置当前激活的菜单，有时候页面没有显示在菜单内，需要激活父级菜单时使用。，不为空字符串
    private Boolean whereIsNotEmptyActiveId;
    // 条件：用于配置当前激活的菜单，有时候页面没有显示在菜单内，需要激活父级菜单时使用。，大于
    private Integer whereGtActiveId;
    // 条件：用于配置当前激活的菜单，有时候页面没有显示在菜单内，需要激活父级菜单时使用。，大于等于
    private Integer whereGteActiveId;
    // 条件：用于配置当前激活的菜单，有时候页面没有显示在菜单内，需要激活父级菜单时使用。，小于
    private Integer whereLtActiveId;
    // 条件：用于配置当前激活的菜单，有时候页面没有显示在菜单内，需要激活父级菜单时使用。，小于等于
    private Integer whereLteActiveId;
    // 条件：用于配置当前激活的菜单，有时候页面没有显示在菜单内，需要激活父级菜单时使用。，开始范围
    private Integer whereStartActiveId;
    // 条件：用于配置当前激活的菜单，有时候页面没有显示在菜单内，需要激活父级菜单时使用。，结束范围
    private Integer whereEndActiveId;
    // 条件：用于配置页面是否固定标签页，固定后页面不可关闭。1：是，2：否，等于
    private Integer whereAffixTab;
    // 条件：用于配置页面是否固定标签页，固定后页面不可关闭。1：是，2：否，在列表中
    private List<Integer> whereInAffixTabs;
    // 条件：用于配置页面是否固定标签页，固定后页面不可关闭。1：是，2：否，在列表中，or连接
    private List<Integer> whereInOrAffixTabs;
    // 排除条件：用于配置页面是否固定标签页，固定后页面不可关闭。1：是，2：否
    private Integer whereNotAffixTab;
    // 条件：用于配置页面是否固定标签页，固定后页面不可关闭。1：是，2：否，不在列表中
    private List<Integer> whereNotInAffixTabs;
    // 条件：用于配置页面是否固定标签页，固定后页面不可关闭。1：是，2：否，为空
    private Boolean whereIsNullAffixTab;
    // 条件：用于配置页面是否固定标签页，固定后页面不可关闭。1：是，2：否，不为空
    private Boolean whereIsNotNullAffixTab;
    // 条件：用于配置页面是否固定标签页，固定后页面不可关闭。1：是，2：否，为空字符串
    private Boolean whereIsEmptyAffixTab;
    // 条件：用于配置页面是否固定标签页，固定后页面不可关闭。1：是，2：否，不为空字符串
    private Boolean whereIsNotEmptyAffixTab;
    // 条件：用于配置页面是否固定标签页，固定后页面不可关闭。1：是，2：否，大于
    private Integer whereGtAffixTab;
    // 条件：用于配置页面是否固定标签页，固定后页面不可关闭。1：是，2：否，大于等于
    private Integer whereGteAffixTab;
    // 条件：用于配置页面是否固定标签页，固定后页面不可关闭。1：是，2：否，小于
    private Integer whereLtAffixTab;
    // 条件：用于配置页面是否固定标签页，固定后页面不可关闭。1：是，2：否，小于等于
    private Integer whereLteAffixTab;
    // 条件：用于配置页面是否固定标签页，固定后页面不可关闭。1：是，2：否，开始范围
    private Integer whereStartAffixTab;
    // 条件：用于配置页面是否固定标签页，固定后页面不可关闭。1：是，2：否，结束范围
    private Integer whereEndAffixTab;
    // 条件：用于配置页面固定标签页的排序, 采用升序排序。，等于
    private Integer whereAffixTabOrder;
    // 条件：用于配置页面固定标签页的排序, 采用升序排序。，在列表中
    private List<Integer> whereInAffixTabOrders;
    // 条件：用于配置页面固定标签页的排序, 采用升序排序。，在列表中，or连接
    private List<Integer> whereInOrAffixTabOrders;
    // 排除条件：用于配置页面固定标签页的排序, 采用升序排序。
    private Integer whereNotAffixTabOrder;
    // 条件：用于配置页面固定标签页的排序, 采用升序排序。，不在列表中
    private List<Integer> whereNotInAffixTabOrders;
    // 条件：用于配置页面固定标签页的排序, 采用升序排序。，为空
    private Boolean whereIsNullAffixTabOrder;
    // 条件：用于配置页面固定标签页的排序, 采用升序排序。，不为空
    private Boolean whereIsNotNullAffixTabOrder;
    // 条件：用于配置页面固定标签页的排序, 采用升序排序。，为空字符串
    private Boolean whereIsEmptyAffixTabOrder;
    // 条件：用于配置页面固定标签页的排序, 采用升序排序。，不为空字符串
    private Boolean whereIsNotEmptyAffixTabOrder;
    // 条件：用于配置页面固定标签页的排序, 采用升序排序。，大于
    private Integer whereGtAffixTabOrder;
    // 条件：用于配置页面固定标签页的排序, 采用升序排序。，大于等于
    private Integer whereGteAffixTabOrder;
    // 条件：用于配置页面固定标签页的排序, 采用升序排序。，小于
    private Integer whereLtAffixTabOrder;
    // 条件：用于配置页面固定标签页的排序, 采用升序排序。，小于等于
    private Integer whereLteAffixTabOrder;
    // 条件：用于配置页面固定标签页的排序, 采用升序排序。，开始范围
    private Integer whereStartAffixTabOrder;
    // 条件：用于配置页面固定标签页的排序, 采用升序排序。，结束范围
    private Integer whereEndAffixTabOrder;
    // 条件：用于配置页面是否忽略权限，直接可以访问。1：是，2：否，等于
    private Integer whereIgnoreAccess;
    // 条件：用于配置页面是否忽略权限，直接可以访问。1：是，2：否，在列表中
    private List<Integer> whereInIgnoreAccesss;
    // 条件：用于配置页面是否忽略权限，直接可以访问。1：是，2：否，在列表中，or连接
    private List<Integer> whereInOrIgnoreAccesss;
    // 排除条件：用于配置页面是否忽略权限，直接可以访问。1：是，2：否
    private Integer whereNotIgnoreAccess;
    // 条件：用于配置页面是否忽略权限，直接可以访问。1：是，2：否，不在列表中
    private List<Integer> whereNotInIgnoreAccesss;
    // 条件：用于配置页面是否忽略权限，直接可以访问。1：是，2：否，为空
    private Boolean whereIsNullIgnoreAccess;
    // 条件：用于配置页面是否忽略权限，直接可以访问。1：是，2：否，不为空
    private Boolean whereIsNotNullIgnoreAccess;
    // 条件：用于配置页面是否忽略权限，直接可以访问。1：是，2：否，为空字符串
    private Boolean whereIsEmptyIgnoreAccess;
    // 条件：用于配置页面是否忽略权限，直接可以访问。1：是，2：否，不为空字符串
    private Boolean whereIsNotEmptyIgnoreAccess;
    // 条件：用于配置页面是否忽略权限，直接可以访问。1：是，2：否，大于
    private Integer whereGtIgnoreAccess;
    // 条件：用于配置页面是否忽略权限，直接可以访问。1：是，2：否，大于等于
    private Integer whereGteIgnoreAccess;
    // 条件：用于配置页面是否忽略权限，直接可以访问。1：是，2：否，小于
    private Integer whereLtIgnoreAccess;
    // 条件：用于配置页面是否忽略权限，直接可以访问。1：是，2：否，小于等于
    private Integer whereLteIgnoreAccess;
    // 条件：用于配置页面是否忽略权限，直接可以访问。1：是，2：否，开始范围
    private Integer whereStartIgnoreAccess;
    // 条件：用于配置页面是否忽略权限，直接可以访问。1：是，2：否，结束范围
    private Integer whereEndIgnoreAccess;
    // 条件：用于配置是否是内嵌页面的iframe地址，等于
    private Integer whereIsIframe;
    // 条件：用于配置是否是内嵌页面的iframe地址，在列表中
    private List<Integer> whereInIsIframes;
    // 条件：用于配置是否是内嵌页面的iframe地址，在列表中，or连接
    private List<Integer> whereInOrIsIframes;
    // 排除条件：用于配置是否是内嵌页面的iframe地址
    private Integer whereNotIsIframe;
    // 条件：用于配置是否是内嵌页面的iframe地址，不在列表中
    private List<Integer> whereNotInIsIframes;
    // 条件：用于配置是否是内嵌页面的iframe地址，为空
    private Boolean whereIsNullIsIframe;
    // 条件：用于配置是否是内嵌页面的iframe地址，不为空
    private Boolean whereIsNotNullIsIframe;
    // 条件：用于配置是否是内嵌页面的iframe地址，为空字符串
    private Boolean whereIsEmptyIsIframe;
    // 条件：用于配置是否是内嵌页面的iframe地址，不为空字符串
    private Boolean whereIsNotEmptyIsIframe;
    // 条件：用于配置是否是内嵌页面的iframe地址，大于
    private Integer whereGtIsIframe;
    // 条件：用于配置是否是内嵌页面的iframe地址，大于等于
    private Integer whereGteIsIframe;
    // 条件：用于配置是否是内嵌页面的iframe地址，小于
    private Integer whereLtIsIframe;
    // 条件：用于配置是否是内嵌页面的iframe地址，小于等于
    private Integer whereLteIsIframe;
    // 条件：用于配置是否是内嵌页面的iframe地址，开始范围
    private Integer whereStartIsIframe;
    // 条件：用于配置是否是内嵌页面的iframe地址，结束范围
    private Integer whereEndIsIframe;
    // 条件：用于配置内嵌页面的 iframe 地址，设置后会在当前页面内嵌对应的页面。，等于
    private String whereIframeSrc;
    // 条件：用于配置内嵌页面的 iframe 地址，设置后会在当前页面内嵌对应的页面。，在列表中
    private List<String> whereInIframeSrcs;
    // 条件：用于配置内嵌页面的 iframe 地址，设置后会在当前页面内嵌对应的页面。，在列表中，or连接
    private List<String> whereInOrIframeSrcs;
    // 排除条件：用于配置内嵌页面的 iframe 地址，设置后会在当前页面内嵌对应的页面。
    private String whereNotIframeSrc;
    // 条件：用于配置内嵌页面的 iframe 地址，设置后会在当前页面内嵌对应的页面。，不在列表中
    private List<String> whereNotInIframeSrcs;
    // 条件：用于配置内嵌页面的 iframe 地址，设置后会在当前页面内嵌对应的页面。，为空
    private Boolean whereIsNullIframeSrc;
    // 条件：用于配置内嵌页面的 iframe 地址，设置后会在当前页面内嵌对应的页面。，不为空
    private Boolean whereIsNotNullIframeSrc;
    // 条件：用于配置内嵌页面的 iframe 地址，设置后会在当前页面内嵌对应的页面。，为空字符串
    private Boolean whereIsEmptyIframeSrc;
    // 条件：用于配置内嵌页面的 iframe 地址，设置后会在当前页面内嵌对应的页面。，不为空字符串
    private Boolean whereIsNotEmptyIframeSrc;
    // 条件：用于配置内嵌页面的 iframe 地址，设置后会在当前页面内嵌对应的页面。，大于
    private String whereGtIframeSrc;
    // 条件：用于配置内嵌页面的 iframe 地址，设置后会在当前页面内嵌对应的页面。，大于等于
    private String whereGteIframeSrc;
    // 条件：用于配置内嵌页面的 iframe 地址，设置后会在当前页面内嵌对应的页面。，小于
    private String whereLtIframeSrc;
    // 条件：用于配置内嵌页面的 iframe 地址，设置后会在当前页面内嵌对应的页面。，小于等于
    private String whereLteIframeSrc;
    // 条件：用于配置内嵌页面的 iframe 地址，设置后会在当前页面内嵌对应的页面。，模糊查询
    private String whereLikeIframeSrc;
    // 条件：用于配置内嵌页面的 iframe 地址，设置后会在当前页面内嵌对应的页面。，开始范围
    private String whereStartIframeSrc;
    // 条件：用于配置内嵌页面的 iframe 地址，设置后会在当前页面内嵌对应的页面。，结束范围
    private String whereEndIframeSrc;
    // 条件：用于配置是否是外链跳转路径。1：是，2：否，等于
    private Integer whereIsLink;
    // 条件：用于配置是否是外链跳转路径。1：是，2：否，在列表中
    private List<Integer> whereInIsLinks;
    // 条件：用于配置是否是外链跳转路径。1：是，2：否，在列表中，or连接
    private List<Integer> whereInOrIsLinks;
    // 排除条件：用于配置是否是外链跳转路径。1：是，2：否
    private Integer whereNotIsLink;
    // 条件：用于配置是否是外链跳转路径。1：是，2：否，不在列表中
    private List<Integer> whereNotInIsLinks;
    // 条件：用于配置是否是外链跳转路径。1：是，2：否，为空
    private Boolean whereIsNullIsLink;
    // 条件：用于配置是否是外链跳转路径。1：是，2：否，不为空
    private Boolean whereIsNotNullIsLink;
    // 条件：用于配置是否是外链跳转路径。1：是，2：否，为空字符串
    private Boolean whereIsEmptyIsLink;
    // 条件：用于配置是否是外链跳转路径。1：是，2：否，不为空字符串
    private Boolean whereIsNotEmptyIsLink;
    // 条件：用于配置是否是外链跳转路径。1：是，2：否，大于
    private Integer whereGtIsLink;
    // 条件：用于配置是否是外链跳转路径。1：是，2：否，大于等于
    private Integer whereGteIsLink;
    // 条件：用于配置是否是外链跳转路径。1：是，2：否，小于
    private Integer whereLtIsLink;
    // 条件：用于配置是否是外链跳转路径。1：是，2：否，小于等于
    private Integer whereLteIsLink;
    // 条件：用于配置是否是外链跳转路径。1：是，2：否，开始范围
    private Integer whereStartIsLink;
    // 条件：用于配置是否是外链跳转路径。1：是，2：否，结束范围
    private Integer whereEndIsLink;
    // 条件：用于配置外链跳转路径，会在新窗口打开。，等于
    private String whereLink;
    // 条件：用于配置外链跳转路径，会在新窗口打开。，在列表中
    private List<String> whereInLinks;
    // 条件：用于配置外链跳转路径，会在新窗口打开。，在列表中，or连接
    private List<String> whereInOrLinks;
    // 排除条件：用于配置外链跳转路径，会在新窗口打开。
    private String whereNotLink;
    // 条件：用于配置外链跳转路径，会在新窗口打开。，不在列表中
    private List<String> whereNotInLinks;
    // 条件：用于配置外链跳转路径，会在新窗口打开。，为空
    private Boolean whereIsNullLink;
    // 条件：用于配置外链跳转路径，会在新窗口打开。，不为空
    private Boolean whereIsNotNullLink;
    // 条件：用于配置外链跳转路径，会在新窗口打开。，为空字符串
    private Boolean whereIsEmptyLink;
    // 条件：用于配置外链跳转路径，会在新窗口打开。，不为空字符串
    private Boolean whereIsNotEmptyLink;
    // 条件：用于配置外链跳转路径，会在新窗口打开。，大于
    private String whereGtLink;
    // 条件：用于配置外链跳转路径，会在新窗口打开。，大于等于
    private String whereGteLink;
    // 条件：用于配置外链跳转路径，会在新窗口打开。，小于
    private String whereLtLink;
    // 条件：用于配置外链跳转路径，会在新窗口打开。，小于等于
    private String whereLteLink;
    // 条件：用于配置外链跳转路径，会在新窗口打开。，模糊查询
    private String whereLikeLink;
    // 条件：用于配置外链跳转路径，会在新窗口打开。，开始范围
    private String whereStartLink;
    // 条件：用于配置外链跳转路径，会在新窗口打开。，结束范围
    private String whereEndLink;
    // 条件：用于配置标签页最大打开数量，设置后会在打开新标签页时自动关闭最早打开的标签页(仅在打开同名标签页时生效)。，等于
    private Integer whereMaxNumOfOpenTab;
    // 条件：用于配置标签页最大打开数量，设置后会在打开新标签页时自动关闭最早打开的标签页(仅在打开同名标签页时生效)。，在列表中
    private List<Integer> whereInMaxNumOfOpenTabs;
    // 条件：用于配置标签页最大打开数量，设置后会在打开新标签页时自动关闭最早打开的标签页(仅在打开同名标签页时生效)。，在列表中，or连接
    private List<Integer> whereInOrMaxNumOfOpenTabs;
    // 排除条件：用于配置标签页最大打开数量，设置后会在打开新标签页时自动关闭最早打开的标签页(仅在打开同名标签页时生效)。
    private Integer whereNotMaxNumOfOpenTab;
    // 条件：用于配置标签页最大打开数量，设置后会在打开新标签页时自动关闭最早打开的标签页(仅在打开同名标签页时生效)。，不在列表中
    private List<Integer> whereNotInMaxNumOfOpenTabs;
    // 条件：用于配置标签页最大打开数量，设置后会在打开新标签页时自动关闭最早打开的标签页(仅在打开同名标签页时生效)。，为空
    private Boolean whereIsNullMaxNumOfOpenTab;
    // 条件：用于配置标签页最大打开数量，设置后会在打开新标签页时自动关闭最早打开的标签页(仅在打开同名标签页时生效)。，不为空
    private Boolean whereIsNotNullMaxNumOfOpenTab;
    // 条件：用于配置标签页最大打开数量，设置后会在打开新标签页时自动关闭最早打开的标签页(仅在打开同名标签页时生效)。，为空字符串
    private Boolean whereIsEmptyMaxNumOfOpenTab;
    // 条件：用于配置标签页最大打开数量，设置后会在打开新标签页时自动关闭最早打开的标签页(仅在打开同名标签页时生效)。，不为空字符串
    private Boolean whereIsNotEmptyMaxNumOfOpenTab;
    // 条件：用于配置标签页最大打开数量，设置后会在打开新标签页时自动关闭最早打开的标签页(仅在打开同名标签页时生效)。，大于
    private Integer whereGtMaxNumOfOpenTab;
    // 条件：用于配置标签页最大打开数量，设置后会在打开新标签页时自动关闭最早打开的标签页(仅在打开同名标签页时生效)。，大于等于
    private Integer whereGteMaxNumOfOpenTab;
    // 条件：用于配置标签页最大打开数量，设置后会在打开新标签页时自动关闭最早打开的标签页(仅在打开同名标签页时生效)。，小于
    private Integer whereLtMaxNumOfOpenTab;
    // 条件：用于配置标签页最大打开数量，设置后会在打开新标签页时自动关闭最早打开的标签页(仅在打开同名标签页时生效)。，小于等于
    private Integer whereLteMaxNumOfOpenTab;
    // 条件：用于配置标签页最大打开数量，设置后会在打开新标签页时自动关闭最早打开的标签页(仅在打开同名标签页时生效)。，开始范围
    private Integer whereStartMaxNumOfOpenTab;
    // 条件：用于配置标签页最大打开数量，设置后会在打开新标签页时自动关闭最早打开的标签页(仅在打开同名标签页时生效)。，结束范围
    private Integer whereEndMaxNumOfOpenTab;
    // 条件：用于配置页面是否在新窗口中打开。1：是，2：否，等于
    private Integer whereOpenInNewWindow;
    // 条件：用于配置页面是否在新窗口中打开。1：是，2：否，在列表中
    private List<Integer> whereInOpenInNewWindows;
    // 条件：用于配置页面是否在新窗口中打开。1：是，2：否，在列表中，or连接
    private List<Integer> whereInOrOpenInNewWindows;
    // 排除条件：用于配置页面是否在新窗口中打开。1：是，2：否
    private Integer whereNotOpenInNewWindow;
    // 条件：用于配置页面是否在新窗口中打开。1：是，2：否，不在列表中
    private List<Integer> whereNotInOpenInNewWindows;
    // 条件：用于配置页面是否在新窗口中打开。1：是，2：否，为空
    private Boolean whereIsNullOpenInNewWindow;
    // 条件：用于配置页面是否在新窗口中打开。1：是，2：否，不为空
    private Boolean whereIsNotNullOpenInNewWindow;
    // 条件：用于配置页面是否在新窗口中打开。1：是，2：否，为空字符串
    private Boolean whereIsEmptyOpenInNewWindow;
    // 条件：用于配置页面是否在新窗口中打开。1：是，2：否，不为空字符串
    private Boolean whereIsNotEmptyOpenInNewWindow;
    // 条件：用于配置页面是否在新窗口中打开。1：是，2：否，大于
    private Integer whereGtOpenInNewWindow;
    // 条件：用于配置页面是否在新窗口中打开。1：是，2：否，大于等于
    private Integer whereGteOpenInNewWindow;
    // 条件：用于配置页面是否在新窗口中打开。1：是，2：否，小于
    private Integer whereLtOpenInNewWindow;
    // 条件：用于配置页面是否在新窗口中打开。1：是，2：否，小于等于
    private Integer whereLteOpenInNewWindow;
    // 条件：用于配置页面是否在新窗口中打开。1：是，2：否，开始范围
    private Integer whereStartOpenInNewWindow;
    // 条件：用于配置页面是否在新窗口中打开。1：是，2：否，结束范围
    private Integer whereEndOpenInNewWindow;
    // 条件：用于配置页面的菜单参数，会在菜单中传递给页面。，等于
    private String whereQuery;
    // 条件：用于配置页面的菜单参数，会在菜单中传递给页面。，在列表中
    private List<String> whereInQuerys;
    // 条件：用于配置页面的菜单参数，会在菜单中传递给页面。，在列表中，or连接
    private List<String> whereInOrQuerys;
    // 排除条件：用于配置页面的菜单参数，会在菜单中传递给页面。
    private String whereNotQuery;
    // 条件：用于配置页面的菜单参数，会在菜单中传递给页面。，不在列表中
    private List<String> whereNotInQuerys;
    // 条件：用于配置页面的菜单参数，会在菜单中传递给页面。，为空
    private Boolean whereIsNullQuery;
    // 条件：用于配置页面的菜单参数，会在菜单中传递给页面。，不为空
    private Boolean whereIsNotNullQuery;
    // 条件：用于配置页面的菜单参数，会在菜单中传递给页面。，为空字符串
    private Boolean whereIsEmptyQuery;
    // 条件：用于配置页面的菜单参数，会在菜单中传递给页面。，不为空字符串
    private Boolean whereIsNotEmptyQuery;
    // 条件：用于配置页面的菜单参数，会在菜单中传递给页面。，大于
    private String whereGtQuery;
    // 条件：用于配置页面的菜单参数，会在菜单中传递给页面。，大于等于
    private String whereGteQuery;
    // 条件：用于配置页面的菜单参数，会在菜单中传递给页面。，小于
    private String whereLtQuery;
    // 条件：用于配置页面的菜单参数，会在菜单中传递给页面。，小于等于
    private String whereLteQuery;
    // 条件：用于配置页面的菜单参数，会在菜单中传递给页面。，模糊查询
    private String whereLikeQuery;
    // 条件：用于配置页面的菜单参数，会在菜单中传递给页面。，开始范围
    private String whereStartQuery;
    // 条件：用于配置页面的菜单参数，会在菜单中传递给页面。，结束范围
    private String whereEndQuery;
    // 条件：是否在管理后台首页导航中显示。1：是，2：否，等于
    private Integer whereShowInAdminHome;
    // 条件：是否在管理后台首页导航中显示。1：是，2：否，在列表中
    private List<Integer> whereInShowInAdminHomes;
    // 条件：是否在管理后台首页导航中显示。1：是，2：否，在列表中，or连接
    private List<Integer> whereInOrShowInAdminHomes;
    // 排除条件：是否在管理后台首页导航中显示。1：是，2：否
    private Integer whereNotShowInAdminHome;
    // 条件：是否在管理后台首页导航中显示。1：是，2：否，不在列表中
    private List<Integer> whereNotInShowInAdminHomes;
    // 条件：是否在管理后台首页导航中显示。1：是，2：否，为空
    private Boolean whereIsNullShowInAdminHome;
    // 条件：是否在管理后台首页导航中显示。1：是，2：否，不为空
    private Boolean whereIsNotNullShowInAdminHome;
    // 条件：是否在管理后台首页导航中显示。1：是，2：否，为空字符串
    private Boolean whereIsEmptyShowInAdminHome;
    // 条件：是否在管理后台首页导航中显示。1：是，2：否，不为空字符串
    private Boolean whereIsNotEmptyShowInAdminHome;
    // 条件：是否在管理后台首页导航中显示。1：是，2：否，大于
    private Integer whereGtShowInAdminHome;
    // 条件：是否在管理后台首页导航中显示。1：是，2：否，大于等于
    private Integer whereGteShowInAdminHome;
    // 条件：是否在管理后台首页导航中显示。1：是，2：否，小于
    private Integer whereLtShowInAdminHome;
    // 条件：是否在管理后台首页导航中显示。1：是，2：否，小于等于
    private Integer whereLteShowInAdminHome;
    // 条件：是否在管理后台首页导航中显示。1：是，2：否，开始范围
    private Integer whereStartShowInAdminHome;
    // 条件：是否在管理后台首页导航中显示。1：是，2：否，结束范围
    private Integer whereEndShowInAdminHome;
    // 条件：创建时间，等于
    private String whereCreatedAt;
    // 条件：创建时间，在列表中
    private List<String> whereInCreatedAts;
    // 条件：创建时间，在列表中，or连接
    private List<String> whereInOrCreatedAts;
    // 排除条件：创建时间
    private String whereNotCreatedAt;
    // 条件：创建时间，不在列表中
    private List<String> whereNotInCreatedAts;
    // 条件：创建时间，为空
    private Boolean whereIsNullCreatedAt;
    // 条件：创建时间，不为空
    private Boolean whereIsNotNullCreatedAt;
    // 条件：创建时间，为空字符串
    private Boolean whereIsEmptyCreatedAt;
    // 条件：创建时间，不为空字符串
    private Boolean whereIsNotEmptyCreatedAt;
    // 条件：创建时间，大于
    private String whereGtCreatedAt;
    // 条件：创建时间，大于等于
    private String whereGteCreatedAt;
    // 条件：创建时间，小于
    private String whereLtCreatedAt;
    // 条件：创建时间，小于等于
    private String whereLteCreatedAt;
    // 条件：创建时间，开始范围
    private String whereStartCreatedAt;
    // 条件：创建时间，结束范围
    private String whereEndCreatedAt;
    // 条件：创建者，等于
    private Integer whereCreatedUserId;
    // 条件：创建者，在列表中
    private List<Integer> whereInCreatedUserIds;
    // 条件：创建者，在列表中，or连接
    private List<Integer> whereInOrCreatedUserIds;
    // 排除条件：创建者
    private Integer whereNotCreatedUserId;
    // 条件：创建者，不在列表中
    private List<Integer> whereNotInCreatedUserIds;
    // 条件：创建者，为空
    private Boolean whereIsNullCreatedUserId;
    // 条件：创建者，不为空
    private Boolean whereIsNotNullCreatedUserId;
    // 条件：创建者，为空字符串
    private Boolean whereIsEmptyCreatedUserId;
    // 条件：创建者，不为空字符串
    private Boolean whereIsNotEmptyCreatedUserId;
    // 条件：创建者，大于
    private Integer whereGtCreatedUserId;
    // 条件：创建者，大于等于
    private Integer whereGteCreatedUserId;
    // 条件：创建者，小于
    private Integer whereLtCreatedUserId;
    // 条件：创建者，小于等于
    private Integer whereLteCreatedUserId;
    // 条件：创建者，开始范围
    private Integer whereStartCreatedUserId;
    // 条件：创建者，结束范围
    private Integer whereEndCreatedUserId;
    // 条件：更新时间，等于
    private String whereUpdatedAt;
    // 条件：更新时间，在列表中
    private List<String> whereInUpdatedAts;
    // 条件：更新时间，在列表中，or连接
    private List<String> whereInOrUpdatedAts;
    // 排除条件：更新时间
    private String whereNotUpdatedAt;
    // 条件：更新时间，不在列表中
    private List<String> whereNotInUpdatedAts;
    // 条件：更新时间，为空
    private Boolean whereIsNullUpdatedAt;
    // 条件：更新时间，不为空
    private Boolean whereIsNotNullUpdatedAt;
    // 条件：更新时间，为空字符串
    private Boolean whereIsEmptyUpdatedAt;
    // 条件：更新时间，不为空字符串
    private Boolean whereIsNotEmptyUpdatedAt;
    // 条件：更新时间，大于
    private String whereGtUpdatedAt;
    // 条件：更新时间，大于等于
    private String whereGteUpdatedAt;
    // 条件：更新时间，小于
    private String whereLtUpdatedAt;
    // 条件：更新时间，小于等于
    private String whereLteUpdatedAt;
    // 条件：更新时间，开始范围
    private String whereStartUpdatedAt;
    // 条件：更新时间，结束范围
    private String whereEndUpdatedAt;
    // 条件：最后更新者，等于
    private Integer whereUpdatedUserId;
    // 条件：最后更新者，在列表中
    private List<Integer> whereInUpdatedUserIds;
    // 条件：最后更新者，在列表中，or连接
    private List<Integer> whereInOrUpdatedUserIds;
    // 排除条件：最后更新者
    private Integer whereNotUpdatedUserId;
    // 条件：最后更新者，不在列表中
    private List<Integer> whereNotInUpdatedUserIds;
    // 条件：最后更新者，为空
    private Boolean whereIsNullUpdatedUserId;
    // 条件：最后更新者，不为空
    private Boolean whereIsNotNullUpdatedUserId;
    // 条件：最后更新者，为空字符串
    private Boolean whereIsEmptyUpdatedUserId;
    // 条件：最后更新者，不为空字符串
    private Boolean whereIsNotEmptyUpdatedUserId;
    // 条件：最后更新者，大于
    private Integer whereGtUpdatedUserId;
    // 条件：最后更新者，大于等于
    private Integer whereGteUpdatedUserId;
    // 条件：最后更新者，小于
    private Integer whereLtUpdatedUserId;
    // 条件：最后更新者，小于等于
    private Integer whereLteUpdatedUserId;
    // 条件：最后更新者，开始范围
    private Integer whereStartUpdatedUserId;
    // 条件：最后更新者，结束范围
    private Integer whereEndUpdatedUserId;

    // 是否使用distinct
    private Boolean useDistinct;
    
    // group by字段列表
    private List<String> groupByFields;
    
    // having条件
    private String havingClause;
    
    /**
     * 添加group by字段
     * @param field 字段名称
     * @return 当前PO实例
     */
    public TbBasicSysMenuPo addGroupByField(String field) {
        if (this.groupByFields == null) {
            this.groupByFields = new ArrayList<>();
        }
        this.groupByFields.add(field);
        return this;
    }
    
    /**
     * 设置group by字段列表
     * @param groupByFields 字段名称列表
     * @return 当前PO实例
     */
    public TbBasicSysMenuPo setGroupByFields(List<String> groupByFields) {
        this.groupByFields = groupByFields;
        return this;
    }
}
