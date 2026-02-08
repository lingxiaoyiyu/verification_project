package com.api.basic.data.dto.develop.generate;

import com.api.common.base.data.dto.BaseDto;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 字段类
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class DtoVoItemDto extends BaseDto {

    // 字段名
    private String fieldName;
    // 字段注释
    private String comment;
    // 数据类型
    private String dataType;
    // 是否作为唯一值处理
    private Boolean fieldUnique;
    // 删除条件
    private String removeWhere;
    // 更新条件
    private String updateWhere;
}
