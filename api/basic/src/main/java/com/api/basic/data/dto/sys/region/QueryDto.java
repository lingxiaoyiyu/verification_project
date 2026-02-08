package com.api.basic.data.dto.sys.region;

import com.api.common.base.data.dto.BaseDto;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 查询行政区域列表相关接口请求用DTO
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class QueryDto extends BaseDto {

    // 行政区域名称
    public String name;
    // 行政区域编码
    public Long code;
    // 父级编码
    public Long parentCode;
}
