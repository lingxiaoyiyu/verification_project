package com.api.basic.data.entity;

import com.api.common.base.data.po.BasePagePo;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.SuperBuilder;

/**
 * 行政区域信息实体
 */
@EqualsAndHashCode(doNotUseGetters = true, callSuper = true)
@Data
@SuperBuilder
public class TbBasicSysRegion extends BasePagePo {

    public TbBasicSysRegion(){
        super();
    }

    // ID
    private Integer id;
    // 上一级的id值
    private Long parentCode;
    // 行政编码
    private Long code;
    // 地区名称
    private String name;
    // 排序
    private Integer sort;
    // 是否删除。1：未删除，2：已删除。
    private Integer isDelete;
    // 层级
    private Integer level;
    // 创建时间
    private String createdAt;
    // 创建者
    private Integer createdUserId;
    // 更新时间
    private String updatedAt;
    // 更新者
    private Integer updatedUserId;
}
