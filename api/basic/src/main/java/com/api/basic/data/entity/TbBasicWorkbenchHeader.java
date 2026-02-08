package com.api.basic.data.entity;

import com.api.common.base.data.po.BasePagePo;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.SuperBuilder;

/**
 * 工作台头部项目信息表实体
 */
@EqualsAndHashCode(doNotUseGetters = true, callSuper = true)
@Data
@SuperBuilder
public class TbBasicWorkbenchHeader extends BasePagePo {

    public TbBasicWorkbenchHeader(){
        super();
    }

    // ID
    private Integer id;
    // 工作台头部项目key
    private String key;
    // 标题
    private String title;
    // 描述
    private String description;
}
