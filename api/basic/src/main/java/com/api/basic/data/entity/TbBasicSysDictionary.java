package com.api.basic.data.entity;

import com.api.common.base.data.po.BasePagePo;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.SuperBuilder;

/**
 * 字典信息表实体
 */
@EqualsAndHashCode(doNotUseGetters = true, callSuper = true)
@Data
@SuperBuilder
public class TbBasicSysDictionary extends BasePagePo {

    public TbBasicSysDictionary(){
        super();
    }

    // ID
    private Integer id;
    // 父节点ID
    private Integer parentId;
    // 字典名称
    private String name;
    // 字典标识
    private String identifying;
    // 排序ID
    private Integer sort;
    // 描述
    private String description;
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
