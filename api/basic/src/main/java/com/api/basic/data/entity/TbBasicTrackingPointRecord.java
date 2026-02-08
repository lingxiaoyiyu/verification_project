package com.api.basic.data.entity;

import com.api.common.base.data.po.BasePagePo;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.SuperBuilder;

/**
 * 埋点记录实体
 */
@EqualsAndHashCode(doNotUseGetters = true, callSuper = true)
@Data
@SuperBuilder
public class TbBasicTrackingPointRecord extends BasePagePo {

    public TbBasicTrackingPointRecord(){
        super();
    }

    // ID
    private Integer id;
    // 页面
    private String page;
    // 标识
    private String tag;
    // 创建时间
    private String createdAt;
    // 访问UA
    private String UA;
    // 访问IP
    private String ip;
}
