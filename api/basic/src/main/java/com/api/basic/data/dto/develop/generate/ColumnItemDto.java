package com.api.basic.data.dto.develop.generate;

import com.api.common.base.data.dto.BaseDto;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

/**
 * 表字段类
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class ColumnItemDto extends BaseDto {

    // 原始字段名
    private String columnName;
    // 处理后的字段名
    private String valueName;
    private List<String> whereTypes;
    private String isNullable;
    private String columnComment;
    private String dataType;
    private String columnDefault;
}
