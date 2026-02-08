package com.api.basic.data.entity;

import com.api.common.base.data.po.BasePagePo;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 系统模块实体
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class TbBasicSysMultiple extends BasePagePo {

    private TbBasicSysUser tbBasicSysUser;
    private TbBasicSysUserRoleRelation tbBasicSysUserRoleRelation;
}
