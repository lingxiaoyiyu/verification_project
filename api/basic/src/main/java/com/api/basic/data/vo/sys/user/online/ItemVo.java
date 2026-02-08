package com.api.basic.data.vo.sys.user.online;

import lombok.Data;

import java.util.List;

/**
 * 用户信息相关接口响应用VO
 */
@Data
public class ItemVo {

    // 用户ID
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
    // 邮箱
    private String email;
    // 状态
    private Integer status;
    // 部门名称
    private String departmentName;
    // 更新时间
    private String updatedAt;
    // 角色名称列表
    private List<String> roleNames;
    // sessionId
    private String sessionId;
    // 最后活跃时间
    private String lastActiveAt;
    // 终端列表
    private List<String> clientForms;
}
