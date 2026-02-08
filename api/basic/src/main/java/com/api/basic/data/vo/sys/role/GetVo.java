package com.api.basic.data.vo.sys.role;

import lombok.Data;

import java.util.List;

/**
 * 角色相关接口响应用VO
 */
@Data
public class GetVo {
    // 角色ID
    private int id;
    // 角色名称
    private String name;
    // 角色标识
    private String identifying;
    // 备注
    private String remark;
    // 状态
    private int status;
    // 创建时间
    private String createdAt;
    // 创建者
    private String createdUserName;
    // 最后更新时间
    private String updatedAt;
    // 最后更新者
    private String updatedUserName;
    // 角色的菜单ID列表
    private List<Integer> menuIdList;
    // 角色的菜单名称列表
    private List<String> menuNames;
}
