package com.api.basic.data.dto.sys.menu;

import com.api.basic.data.enums.MenuTypeEnum;
import com.api.common.base.data.dto.BaseDto;
import com.api.common.validate.enumValue.ValidEnumValue;
import jakarta.annotation.Nullable;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

/**
 * 添加目录菜单相关接口请求用DTO
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class AddDto  extends BaseDto {

    // 父节点ID
    @NotNull(message = "{validation.menu.parent.notNull}")
    @Min(value = 0, message = "{validation.menu.parent.min}")
    private Integer parentId;

    // 类型
    @NotNull(message = "{validation.menu.type.notNull}")
    @ValidEnumValue(enumClass = MenuTypeEnum.class, expectedType = Integer.class, message = "{validation.menu.type.enumValue}")
    private Integer type;

    // 名称
    @Nullable
    @Size(max = 32, message = "{validation.menu.name.length}")
    private String name;

    // 路由地址
    @Nullable
    @Size(max = 128, message = "{validation.menu.path.length}")
    private String path;

    // 组件地址
    @Nullable
    @Size(max = 128, message = "{validation.menu.component.length}")
    private String component;

    // 菜单标识
    @Nullable
    @Size(max = 64, message = "{validation.menu.identifying.length}")
    private String identifying;

    // 状态
    @NotNull(message = "{validation.menu.status.notNull}")
    private Integer status;

    // 菜单标题
    @NotBlank(message = "{validation.menu.title.notBlank}")
    @Size(max = 32, message = "{validation.menu.title.length}")
    private String title;

    // 图标
    @Nullable
    @Size(max = 128, message = "{validation.menu.icon.length}")
    private String icon;

    // 激活图标
    @Nullable
    @Size(max = 128, message = "{validation.menu.activeIcon.length}")
    private String activeIcon;

    // 是否缓存
    @Nullable
    private Integer keepalive;

    // 在管理后台首页中是否显示
    @Nullable
    private Integer showInAdminHome;

    // 是否在菜单中隐藏
    @Nullable
    private Integer hideInMenu;

    // 子菜单是否隐藏
    @Nullable
    private Integer hideChildrenInMenu;

    // 是否在标签页中隐藏
    @Nullable
    private Integer hideInTab;

    // 页面权限
    @Nullable
    private List<String> authority;

    // 当前激活的菜单
    @Min(value = 1, message = "{validation.menu.activeId.min}")
    private Integer activeId;

    // 否固定标签页
    @Nullable
    private Integer affixTab;

    // 固定标签页的排序
    @Min(value = 0, message = "{validation.menu.affixTabOrder.min}")
    private Integer affixTabOrder;

    // 是否忽略权限
    @Nullable
    private Integer ignoreAccess;

    // 是否外链
    @Nullable
    private Integer isIframe;

    // 内嵌页面的 iframe 地址
    @Nullable
    @Size(max = 128, message = "{validation.menu.iframeSrc.length}")
    private String iframeSrc;

    // 是否外链
    @Nullable
    private Integer isLink;

    // 外链地址，会在新窗口打开
    @Nullable
    @Size(max = 128, message = "{validation.menu.link.length}")
    private String link;

    // 标签页最大打开数量
    @Nullable
    @Min(value = 0, message = "{validation.menu.maxNumOfOpenTab.min}")
    private Integer maxNumOfOpenTab;

    // 是否在新窗口中打开
    @Nullable
    private Integer openInNewWindow;

    // 页面的菜单参数
    private String query;
}
