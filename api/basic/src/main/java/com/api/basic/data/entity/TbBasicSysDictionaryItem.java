package com.api.basic.data.entity;

import com.api.common.base.data.po.BasePagePo;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.SuperBuilder;

/**
 * 字典项信息表实体
 */
@EqualsAndHashCode(doNotUseGetters = true, callSuper = true)
@Data
@SuperBuilder
public class TbBasicSysDictionaryItem extends BasePagePo {

    public TbBasicSysDictionaryItem(){
        super();
    }

    // ID
    private Integer id;
    // 字典标识
    private String dicIdentifying;
    // 字典项名称
    private String name;
    // 字典项值
    private String value;
    // 字典项label
    private String label;
    // 字典项标识
    private String identifying;
    // 排序ID
    private Integer sort;
    // 描述
    private String description;
    // 背景色
    private String backgroundColor;
    // 字体颜色
    private String color;
    // Tag预设状态
    private String tagStatus;
    // 状态。1：启用，2：禁用
    private Integer status;
    // 创建时间
    private String createdAt;
    // 创建者
    private Integer createdUserId;
    // 更新时间
    private String updatedAt;
    // 更新者
    private Integer updatedUserId;
}
