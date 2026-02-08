package com.api.common.base.data.po;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

import java.util.List;

/**
 * 分页查询基类PO
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@SuperBuilder
public class BasePagePo {

    // 分页查询：起始索引
    private Integer offset;
    // 分页查询：每页数量
    private Integer limit;
    // 排序条件列表
    private List<BasePageSort> sortList;
    // 合计字段
    private String sumField;

    /**
     * 设置分页
     * @param page 页码值，从1开始、
     */
    public void setPage(Integer page) {
        this.setPage(page, 20);
    }

    /**
     * 设置分页
     * @param page 页码值，从1开始
     * @param limit 每页数量
     */
    public void setPage(Integer page, Integer limit) {
        this.limit = limit;
        this.offset = (page - 1) * limit;
    }
}
