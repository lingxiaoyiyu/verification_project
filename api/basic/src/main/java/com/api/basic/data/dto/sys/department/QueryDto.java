package com.api.basic.data.dto.sys.department;

import com.api.common.base.data.dto.BaseDto;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 查询部门列表相关接口请求用DTO
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class QueryDto extends BaseDto {

    // 部门名称
    public String name;
}
