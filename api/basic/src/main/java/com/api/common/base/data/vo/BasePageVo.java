package com.api.common.base.data.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;

/**
 * 分页查询通用Vo
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class BasePageVo<T> {
    /**
     * 数据列表，使用泛型以支持任何类型的对象列表。
     */
    private List<T> list;

    /**
     * 数据总量。
     */
    private Integer total;
}
