package com.api.basic.data.po;

import com.api.basic.data.entity.Column;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.SuperBuilder;

import java.util.List;

/**
 * 数据库表自动信息条件实体
 */
@EqualsAndHashCode(doNotUseGetters = true, callSuper = true)
@Data
@SuperBuilder
public class ColumnPo extends Column {

    public ColumnPo() {
        super();
    }

    // 条件：所属数据库名
    private String whereTableSchema;
    // 条件：表名
    private String whereTableName;
    // 条件：表名
    private List<String> whereInTableNames;
    // 条件：表注释
    private String whereTableComment;
    // 条件：字段名称，模糊查询
    private String whereLikeColumnName;
    // 条件：字段注释，模糊查询
    private String whereLikeColumnComment;
    // 条件：数据类型
    private String whereDataType;
    // 条件：字段类型
    private String whereColumnType;
    // 条件：是否可为空
    private String whereIsNullable;
}
