package com.api.basic.mapper;

import com.api.basic.data.entity.Table;
import com.api.basic.data.po.TablePo;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 数据库表Mapper
 */
@Repository
@Mapper
public interface TableMapper {

    /**
     * 查询列表
     *
     * @param po 查询条件
     * @return 列表
     */
    List<Table> query(TablePo po);

    /**
     * 统计数量
     *
     * @param po 统计条件
     * @return 数量
     */
    Integer cnt(TablePo po);
}
