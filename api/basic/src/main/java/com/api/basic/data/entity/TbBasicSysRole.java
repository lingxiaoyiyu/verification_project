package com.api.basic.data.entity;

import com.api.common.base.data.po.BasePagePo;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.SuperBuilder;

/**
 * 角色信息表实体
 */
@EqualsAndHashCode(doNotUseGetters = true, callSuper = true)
@Data
@SuperBuilder
public class TbBasicSysRole extends BasePagePo {

    public TbBasicSysRole(){
        super();
    }

    // ID
    private Integer id;
    // 名称
    private String name;
    // 角色标识
    private String identifying;
    // 备注
    private String remark;
    // 状态。1：正常，2：禁用
    private Integer status;
    // 是否已删除。1：未删除，2：已删除
    private Integer isDelete;
    // 创建时间
    private String createdAt;
    // 创建者
    private Integer createdUserId;
    // 更新时间
    private String updatedAt;
    // 更新者
    private Integer updatedUserId;
}
