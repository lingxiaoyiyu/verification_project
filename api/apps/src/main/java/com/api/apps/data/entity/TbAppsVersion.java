package com.api.apps.data.entity;

import com.api.common.base.data.po.BasePagePo;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.SuperBuilder;

/**
 * 应用版本信息表实体
 */
@EqualsAndHashCode(doNotUseGetters = true, callSuper = true)
@Data
@SuperBuilder
public class TbAppsVersion extends BasePagePo {

    public TbAppsVersion(){
        super();
    }

    // 
    private Integer id;
    // 应用ID
    private String appId;
    // 版本号
    private Integer versionCode;
    // 版本名称
    private String versionName;
    // 版本描述
    private String desc;
    // 状态。1：下线，2：测试，3：正式上线。
    private Integer status;
    // 下载地址
    private String url;
    // 创建时间
    private String createdAt;
    // 创建者
    private Integer createdUserId;
    // 更新时间
    private String updatedAt;
    // 最后更新者
    private Integer updatedUserId;
}
