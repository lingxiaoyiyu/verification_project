package com.api.basic.dao;

import com.api.basic.data.entity.TbBasicSysMultiple;
import com.api.basic.data.po.TbBasicSysMultiplePo;
import com.api.basic.mapper.TbBasicSysMultipleMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * 系统模块Dao
 */
@Component
@RequiredArgsConstructor
public class TbBasicSysMultipleDao {

    private final TbBasicSysMultipleMapper mapper;

    /**
     * 查询列表
     *
     * @param po 查询条件
     * @return 列表
     */
    public List<TbBasicSysMultiple> query(TbBasicSysMultiplePo po) {
        return mapper.query(po);
    }

    /**
     * 统计数量
     *
     * @param po 查询条件
     * @return 数量
     */
    public Integer cnt(TbBasicSysMultiplePo po) {
        return mapper.cnt(po);
    }

    /**
     * 查询单条数据
     *
     * @param po 查询条件
     * @return 查询结果
     */
    public TbBasicSysMultiple get(TbBasicSysMultiplePo po) {
        List<TbBasicSysMultiple> list = mapper.query(po);
        return list.isEmpty() ? null : list.getFirst();
    }
}
