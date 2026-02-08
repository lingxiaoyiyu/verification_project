package com.api.basic.data.vo.config;

import lombok.Data;

/**
 * 配置信息表实体类
 */
@Data
public class ItemVo {

    // ID
    private Integer id;
    // 标题
    private String title;
    // 配置项key
    private String name;
    // 配置项value
    private String value;
    // 状态值
    private Integer status;
    // 配置项描述
    private String description;
    // 配置项数据类型
    private Integer dataType;
    // 分组名称
    private String groupName;
    // 创建时间
    private String createdAt;
    // 创建者
    private Integer createdUserId;
    // 更新时间
    private String updatedAt;
    // 更新者
    private Integer updatedUserId;
    // 组件数据
    private String componentData;
}
