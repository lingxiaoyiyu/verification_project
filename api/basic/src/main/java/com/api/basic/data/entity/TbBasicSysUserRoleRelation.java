package com.api.basic.data.entity;

import com.api.common.base.data.po.BasePagePo;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.SuperBuilder;

/**
 * 用户-角色关联信息表实体
 */
@EqualsAndHashCode(doNotUseGetters = true, callSuper = true)
@Data
@SuperBuilder
public class TbBasicSysUserRoleRelation extends BasePagePo {

    public TbBasicSysUserRoleRelation(){
        super();
    }

    // ID
    private Integer id;
    // 用户id
    private Integer userId;
    // 角色ID
    private Integer roleId;
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
