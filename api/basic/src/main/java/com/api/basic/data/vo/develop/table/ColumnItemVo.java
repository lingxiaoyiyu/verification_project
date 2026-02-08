package com.api.basic.data.vo.develop.table;

import lombok.Data;

/**
 * 获取指定表结构接口响应用VO
 */
@Data
public class ColumnItemVo {

    // 字段名称
    private String columnName;
    // 字段注释
    private String columnComment;
    // 数据类型
    private String dataType;
    // 字段类型
    private String columnType;
    //是否可为空
    private String isNullable;
    // 默认值
    private String columnDefault;
}
