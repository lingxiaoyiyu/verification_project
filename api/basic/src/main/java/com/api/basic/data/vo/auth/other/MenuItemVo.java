package com.api.basic.data.vo.auth.other;

import lombok.Data;

import java.util.List;

/**
 * 菜单相关接口响应用VO
 */
@Data
public class MenuItemVo {

    // 菜单ID
    private int id;
    // 菜单名称
    private String name;
    // 菜单标题
    private String title;
    // 路由地址
    private String path;
    // 组件地址
    private String component;
    // 排序值
    private Integer order;
    // meta 属性
    private MenuMetaVo meta;
    // 子节点数据
    private List<MenuItemVo> children;
}
