package com.api.basic.data.entity;

import com.api.common.base.data.po.BasePagePo;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.SuperBuilder;

/**
 * 角色-菜单关联信息表实体
 */
@EqualsAndHashCode(doNotUseGetters = true, callSuper = true)
@Data
@SuperBuilder
public class TbBasicSysRoleMenuRelation extends BasePagePo {

    public TbBasicSysRoleMenuRelation(){
        super();
    }

    // ID
    private Integer id;
    // 角色ID
    private Integer roleId;
    // 菜单ID
    private Integer menuId;
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
