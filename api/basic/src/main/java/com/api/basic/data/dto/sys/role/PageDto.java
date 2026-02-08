package com.api.basic.data.dto.sys.role;

import com.api.common.base.data.dto.BasePageDto;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 查询角色分页列表相关接口请求用DTO
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class PageDto extends BasePageDto {

    // 名称
    private String name;
    // 状态
    private Integer status;
    // 角色标识
    private String identifying;
}
