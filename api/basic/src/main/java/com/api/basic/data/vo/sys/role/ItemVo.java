package com.api.basic.data.vo.sys.role;

import lombok.Data;

/**
 * 角色相关接口响应用VO
 */
@Data
public class ItemVo {

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
    // 更新时间爱你
    private String updatedAt;
}
