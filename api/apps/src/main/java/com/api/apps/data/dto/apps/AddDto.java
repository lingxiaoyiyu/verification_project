package com.api.apps.data.dto.apps;

import com.api.common.base.data.dto.BaseDto;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

/**
 * 添加应用接口请求用DTO
 *
 * @author 裴金伟
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class AddDto extends BaseDto {

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
    private List<Integer> accessRoleIds;
    // 分类ID
    private Integer categoryId;
    // 应用名称
    private String name;
    // 应用简介
    private String desc;
    // 应用图标
    private String img;
    // 状态。1：启用，2：禁用
    private Integer status;
}
