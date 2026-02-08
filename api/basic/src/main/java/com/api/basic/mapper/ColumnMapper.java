package com.api.basic.mapper;

import com.api.basic.data.entity.Column;
import com.api.basic.data.po.ColumnPo;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 数据库表字段Mapper
 */
@Repository
@Mapper
public interface ColumnMapper {

    /**
     * 查询列表
     *
     * @param po 查询条件
     * @return 列表
     */
    List<Column> query(ColumnPo po);

    /**
     * 统计数量
     *
     * @param po 统计条件
     * @return 数量
     */
    Integer cnt(ColumnPo po);
}
