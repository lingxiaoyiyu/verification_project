package com.api.common.base.data.po;

import cn.hutool.core.util.StrUtil;
import com.api.common.exception.ServerException;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

/**
 * 分页查询排序对象
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class BasePageSortList {

    // 排序条件列表
    private List<BasePageSort> sortList;

    /**
     * 添加排序条件
     *
     * @param sortField 排序字段
     * @return 添加结果 true:添加成功 false:添加失败
     */
    public BasePageSortList addSort(String sortField) {
        return addSort(sortField, "asc", "");
    }

    /**
     * 添加排序条件
     *
     * @param sortField 排序字段
     * @param sortType  排序方式
     * @return 添加结果 true:添加成功 false:添加失败
     */
    public BasePageSortList addSort(String sortField, String sortType) {
        return addSort(sortField, sortType, "");
    }

    /**
     * 添加排序条件
     *
     * @param sortField 排序字段
     * @param sortType  排序方式
     * @param alias     别名
     * @return 添加结果 true:添加成功 false:添加失败
     */
    public BasePageSortList addSort(String sortField, String sortType, String alias) {
        if (sortList == null) {
            sortList = new ArrayList<>();
        }
        BasePageSort pageSort = new BasePageSort();
        if (StrUtil.isBlank(StrUtil.trim(sortField))) {
            throw new ServerException("排序字段不能为空");
        }
        pageSort.setSortField(StrUtil.trim(sortField));
        pageSort.setSortType(StrUtil.blankToDefault(StrUtil.trim(sortType), "desc"));
        pageSort.setAlias(StrUtil.trim(alias));
        sortList.add(pageSort);
        return this;
    }
}
