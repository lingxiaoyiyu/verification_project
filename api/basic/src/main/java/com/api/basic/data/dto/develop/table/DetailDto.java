package com.api.basic.data.dto.develop.table;

import com.api.common.base.data.dto.BaseDto;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 获取数据库表相关接口请求用DTO
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class DetailDto extends BaseDto {

    // 表名
    private String tableName;
}
