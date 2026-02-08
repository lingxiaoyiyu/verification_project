package com.api.basic.data.dto.config;

import com.api.common.base.data.dto.BaseDto;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 查询配置项列表相关接口请求用DTO
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class QueryDto extends BaseDto {

    // 配置key
    private String name;
    // 分组ID
    private Integer groupId;
    // 状态
    private Integer status;
}
