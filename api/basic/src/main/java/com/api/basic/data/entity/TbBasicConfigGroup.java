package com.api.basic.data.entity;

import com.api.common.base.data.po.BasePagePo;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.SuperBuilder;

/**
 * 配置分组管理实体
 */
@EqualsAndHashCode(doNotUseGetters = true, callSuper = true)
@Data
@SuperBuilder
public class TbBasicConfigGroup extends BasePagePo {

    public TbBasicConfigGroup(){
        super();
    }

    // 
    private Integer id;
    // 分组名称
    private String name;
    // 分组value
    private String value;
    // 创建时间
    private String createdAt;
    // 创建用户
    private Integer createdUserId;
}
