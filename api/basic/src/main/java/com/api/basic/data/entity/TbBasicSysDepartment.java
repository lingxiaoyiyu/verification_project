package com.api.basic.data.entity;

import com.api.common.base.data.po.BasePagePo;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.SuperBuilder;

/**
 * 单位信息表实体
 */
@EqualsAndHashCode(doNotUseGetters = true, callSuper = true)
@Data
@SuperBuilder
public class TbBasicSysDepartment extends BasePagePo {

    public TbBasicSysDepartment(){
        super();
    }

    // ID
    private Integer id;
    // 部门名称
    private String name;
    // 上级ID
    private Integer parentId;
    // 是否删除。1：未删除，2：已删除。
    private Integer isDelete;
    // 排序
    private Integer sort;
    // 创建时间
    private String createdAt;
    // 创建者
    private Integer createdUserId;
    // 更新时间
    private String updatedAt;
    // 更新者
    private Integer updatedUserId;
}
