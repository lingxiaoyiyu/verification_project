package com.api.common.base.data.po;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * 分页查询排序对象
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class BasePageSort {

    // 分页查询：排序字段
    private String sortField;
    // 分页查询：排序类型
    private String sortType;
    // 表别名
    private String alias;
}
