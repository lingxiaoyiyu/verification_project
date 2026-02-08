package com.api.basic.dao;

import com.api.basic.data.entity.Column;
import com.api.basic.data.po.ColumnPo;
import com.api.basic.mapper.ColumnMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * 数据库表结构Dao
 */
@Component
@RequiredArgsConstructor
public class ColumnDao {

    private final ColumnMapper mapper;

    //***************************************************获取 START******************************************************

    /**
     * 查询列表
     *
     * @param po 查询条件
     * @return 列表
     */
    public List<Column> query(ColumnPo po) {
        return mapper.query(po);
    }

    /**
     * 统计数量
     *
     * @param po 统计条件
     * @return 数量
     */
    public Integer cnt(ColumnPo po) {
        return mapper.cnt(po);
    }

    /**
     * 查询单条数据
     *
     * @param po 查询条件
     * @return 查询结果
     */
    public Column get(ColumnPo po) {
        List<Column> list = mapper.query(po);
        return list.isEmpty() ? null : list.getFirst();
    }
    //***************************************************获取 END********************************************************
}
