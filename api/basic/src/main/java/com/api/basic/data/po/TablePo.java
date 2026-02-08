package com.api.basic.data.po;

import com.api.basic.data.entity.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.SuperBuilder;

import java.util.List;

/**
 * 数据库表信息条件实体
 */
@EqualsAndHashCode(doNotUseGetters = true, callSuper = true)
@Data
@SuperBuilder
public class TablePo extends Table {

    public TablePo() {
        super();
    }

    // 条件：所属数据库名
    private String whereTableSchema;
    // 条件：表名
    private String whereTableName;
    // 条件：表名
    private List<String> whereInTableNames;
    // 条件：表名，模糊查询
    private String whereLikeTableName;
    // 条件：表注释，模糊查询
    private String whereLikeTableComment;
}
