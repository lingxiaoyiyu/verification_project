package com.api.basic.data.entity;

import com.api.common.base.data.po.BasePagePo;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.SuperBuilder;

/**
 * 数据库表自动信息实体
 */
@EqualsAndHashCode(doNotUseGetters = true, callSuper = true)
@Data
@SuperBuilder
public class Column extends BasePagePo {

    public Column() {
        super();
    }

    // 所属数据库名
    private String tableSchema;
    // 表名
    private String tableName;
    // 表注释
    private String tableComment;
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
