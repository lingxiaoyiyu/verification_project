package com.api.basic.data.vo.sys.department;

import lombok.Data;

/**
 * 分页查询部门相关接口响应用VO
 */
@Data
public class ItemVo {

    // 部门ID
    private Integer id;
    // 部门名称
    private String name;
    // 上级部门ID
    private Integer parentId;
    // 创建时间
    private String createdAt;
    // 创建者
    private String createdUserName;
    // 最后更新时间
    private String updatedAt;
    // 最后更新者
    private String updatedUserName;
}
