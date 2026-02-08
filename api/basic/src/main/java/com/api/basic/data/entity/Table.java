package com.api.basic.data.entity;

import com.api.common.base.data.po.BasePagePo;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.SuperBuilder;

/**
 * 数据库表信息实体
 */
@EqualsAndHashCode(doNotUseGetters = true, callSuper = true)
@Data
@SuperBuilder
public class Table extends BasePagePo {

    public Table() {
        super();
    }

    // 所属数据库名
    private String tableSchema;
    // 表名
    private String tableName;
    // 表注释
    private String tableComment;
}
