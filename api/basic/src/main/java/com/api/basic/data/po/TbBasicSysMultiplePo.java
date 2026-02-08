package com.api.basic.data.po;

import com.api.common.base.data.po.BasePagePo;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.SuperBuilder;

/**
 * 系统模块PO
 */
@EqualsAndHashCode(callSuper = true)
@Data
@SuperBuilder
public class TbBasicSysMultiplePo extends BasePagePo {

    public TbBasicSysMultiplePo(){
        super();
    }
    private TbBasicSysUserPo tbBasicSysUserPo;
    private TbBasicSysUserRoleRelationPo tbBasicSysUserRoleRelationPo;
}
