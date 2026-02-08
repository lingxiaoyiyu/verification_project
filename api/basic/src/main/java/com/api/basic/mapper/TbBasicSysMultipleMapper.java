package com.api.basic.mapper;

import com.api.basic.data.entity.TbBasicSysMultiple;
import com.api.basic.data.po.TbBasicSysMultiplePo;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 用户Mapper
 */
@Repository
@Mapper
public interface TbBasicSysMultipleMapper {

    /**
     * 查询列表
     *
     * @param po 查询条件
     * @return 列表
     */
    List<TbBasicSysMultiple> query(TbBasicSysMultiplePo po);

    /**
     * 统计数量
     *
     * @param po 查询条件
     * @return 数量
     */
    int cnt(TbBasicSysMultiplePo po);
}
