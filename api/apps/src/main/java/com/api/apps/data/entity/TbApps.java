package com.api.apps.data.entity;

import com.api.common.base.data.po.BasePagePo;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.SuperBuilder;

/**
 * 应用信息表实体
 */
@EqualsAndHashCode(doNotUseGetters = true, callSuper = true)
@Data
@SuperBuilder
public class TbApps extends BasePagePo {

    public TbApps(){
        super();
    }

    // 
    private String id;
    // 应用秘钥
    private String appSecret;
    // 应用类型。1：宿主APP，2：小程序，3：网站。4：第三方应用。
    private Integer type;
    // 排序ID
    private Integer sort;
    // uniapp_id
    private String uniAppid;
    // 允许访问类型。1：公开，2：受限（指定角色或指定用户可访问）。仅零号小程序和普通小程序的访问类型值有意义。
    private Integer accessType;
    // 允许访问的角色
    private String accessRoleIds;
    // 允许访问的用户ID
    private String accessUserIds;
    // 应用名称
    private String name;
    // 应用简介
    private String desc;
    // 应用图标
    private String img;
    // 状态。1：启用，2：禁用
    private Integer status;
    // 是否是入口小程序。1：是，2：否
    private Integer isEntranceMp;
    // 分类ID
    private Integer categoryId;
    // 创建时间
    private String createdAt;
    // 创建者
    private Integer createdUserId;
    // 更新时间
    private String updatedAt;
    // 最后更新者
    private Integer updatedUserId;
}
