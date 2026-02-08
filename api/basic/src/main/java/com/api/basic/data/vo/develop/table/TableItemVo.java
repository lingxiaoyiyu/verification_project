package com.api.basic.data.vo.develop.table;

import lombok.Data;

/**
 * 获取数据库表列表接口响应用VO
 */
@Data
public class TableItemVo {

    // 所属数据库名
    private String tableSchema;
    // 表名
    private String tableName;
    // 表注释
    private String tableComment;
}
