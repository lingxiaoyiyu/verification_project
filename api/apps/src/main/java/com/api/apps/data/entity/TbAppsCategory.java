package com.api.apps.data.entity;

import com.api.common.base.data.po.BasePagePo;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.SuperBuilder;

/**
 * 实体
 */
@EqualsAndHashCode(doNotUseGetters = true, callSuper = true)
@Data
@SuperBuilder
public class TbAppsCategory extends BasePagePo {

    public TbAppsCategory(){
        super();
    }

    // 
    private Integer id;
    // 父级ID
    private Integer pid;
    // 分类名称
    private String name;
    // 分类说明
    private String desc;
    // 排序
    private Integer sort;
    // 是否显示。1：显示，2：不显示。
    private Integer isShow;
    // 创建时间
    private String createdAt;
    // 创建用户
    private Integer createdUserId;
    // 更新时间
    private String updatedAt;
    // 更新用户
    private Integer updatedUserId;
}
