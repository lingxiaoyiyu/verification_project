package com.api.basic.data.dto.develop.table;

import com.api.common.base.data.dto.BasePageDto;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 获取数据库表相关接口请求用DTO
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class PageDto extends BasePageDto {

    // 表名
    private String tableName;
}
