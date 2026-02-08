package com.api.basic.data.entity;

import com.api.common.base.data.po.BasePagePo;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.SuperBuilder;

/**
 * 配置管理实体
 */
@EqualsAndHashCode(doNotUseGetters = true, callSuper = true)
@Data
@SuperBuilder
public class TbBasicConfig extends BasePagePo {

    public TbBasicConfig(){
        super();
    }

    // 
    private Integer id;
    // 配置分组ID
    private Integer groupId;
    // 配置项标题
    private String title;
    // 配置项名称，即key
    private String name;
    // 配置项value
    private String value;
    // 配置项描述
    private String description;
    // 数据类型。1：字符串，2：数字，3：json，4：图片，5：富文本。
    private Integer dataType;
    // 状态。1：启用，2：禁用
    private Integer status;
    // 排序值
    private Integer sort;
    // 日期时间选择器类型
    private String picker;
    // 组件数据
    private String componentData;
    // 创建时间
    private String createdAt;
    // 创建用户
    private Integer createdUserId;
    // 最后更新时间
    private String updatedAt;
    // 更新用户
    private Integer updatedUserId;
}
