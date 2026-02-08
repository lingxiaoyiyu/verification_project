package com.api.apps.data.vo.apps;

import lombok.Data;

/**
 * 小程序接口响应用VO
 *
 * @author 裴金伟
 */
@Data
public class PageItemVo {

  // 
  private String id;
  // 应用秘钥
  private String appSecret;
  // 排序ID
  private Integer sort;
  // uniapp_id
  private String uniAppid;
  // 允许访问类型。1：公开，2：受限（指定角色或指定用户可访问）。仅零号小程序和普通小程序的访问类型值有意义。
  private Integer accessType;
  // 允许访问的角色
  private String accessRoleIds;
  // 允许访问的用户
  private String accessUserIds;
  // 是否是入口小程序。1：是，2：否
  private Integer isEntranceMp;
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
  // 分类
  private String categoryName;
  // 测试版本信息
  private String testVersion;
  // 线上版本信息
  private String onlineVersion;
}
