package com.api.basic.data.vo.sys.user;

import lombok.Data;

import java.util.List;

/**
 * 用户菜单相关接口响应用VO
 */
@Data
public class GetVo {

    // ID
    private Integer id;
    // 头像
    private String avatar;
    // 用户名
    private String username;
    // 昵称
    private String nickName;
    // 真实姓名
    private String realName;
    // 手机号
    private String phoneNumber;
    // 状态
    private Integer status;
    // 邀请码
    private String inviteCode;
    // 邀请者用户名
    private String inviterUsername;
    // 邮箱
    private String email;
    // 性别
    private Integer gender;
    // 部门ID
    private Integer departmentId;
    // 部门名称
    private String departmentName;
    // 简介
    private String introduction;
    // 用户来源描述
    private String sourceName;
    // 微信unioidd
    private String wechatUnionid;
    // 微信公众号openid
    private String wechatOfficialAccountOpenid;
    // 微信小程序openid
    private String wujieUnionid;
    // 备注
    private String remark;
    // 创建时间
    private String createdAt;
    // 创建者
    private String createdUserName;
    // 更新时间
    private String updatedAt;
    // 最后更新者
    private String updatedUserName;
    // 角色ID列表
    private List<Integer> roleIds;
    // 角色名称列表
    private List<String> roleNames;
    // 角色标识列表
    private List<String> roles;
    // 首页路径
    private String homePath;
}
