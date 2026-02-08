package com.api.basic.dao;

import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import com.api.basic.data.entity.TbBasicStatisticsUserTotal;
import com.api.basic.data.po.TbBasicStatisticsUserTotalPo;
import com.api.basic.mapper.TbBasicStatisticsUserTotalMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.*;
import java.util.function.Function;

/**
 * 用户每日注册统计表Dao
 *
 * @author 裴金伟
 */
@Component
@RequiredArgsConstructor
public class TbBasicStatisticsUserTotalDao {

    private final TbBasicStatisticsUserTotalMapper mapper;

    //***************************************************添加 START******************************************************
    /**
     * 新增
     *
     * @param entity 新增信息
     * @return 新增ID
     */
    public Integer add(TbBasicStatisticsUserTotal entity) {
        if (entity == null) {
            return 0;
        }
        return mapper.add(entity);
    }
    //***************************************************添加 END********************************************************

    //***************************************************删除 START******************************************************
    /**
     * 删除信息
     *
     * @param po 要删除的信息
     * @return 受影响的行数，即删除的数据数量
     */
    public Integer delete(TbBasicStatisticsUserTotalPo po) {
        if (po == null) {
            return 0;
        }
        return mapper.delete(po);
    }
    //***************************************************删除 END********************************************************


    //***************************************************更新 START******************************************************
    /**
     * 更新信息
     *
     * @param po 要更新的信息
     * @return 受影响的行数，即更新的数据数量
     */
    public Integer update(TbBasicStatisticsUserTotalPo po) {
        if (po == null) {
            return 0;
        }
        return mapper.update(po);
    }
    //***************************************************更新 END********************************************************

    //***************************************************获取 START******************************************************
    /**
     * 查询列表
     *
     * @param po 查询条件
     * @return 列表
     */
    public List<TbBasicStatisticsUserTotal> query(TbBasicStatisticsUserTotalPo po) {
        if (po == null) {
            return null;
        }
        return mapper.query(po);
    }

    /**
     * 统计数量
     *
     * @param po 统计条件
     * @return 数量
     */
    public Integer cnt(TbBasicStatisticsUserTotalPo po) {
        if (po == null) {
            return 0;
        }
        return mapper.cnt(po);
    }

    /**
     * 查询单条数据
     *
     * @param po 查询条件
     * @return 查询结果
     */
    public TbBasicStatisticsUserTotal get(TbBasicStatisticsUserTotalPo po) {
        if (po == null) {
            return null;
        }
        return mapper.query(po).stream().findFirst().orElse(null);
    }

    /**
     * 根据查询单条数据
     *
     * @param id 
     * @return 查询结果
     */
    public TbBasicStatisticsUserTotal getById(Integer id) {
        if (ObjectUtil.isNull(id)) {
            return null;
        }

        TbBasicStatisticsUserTotalPo po = new TbBasicStatisticsUserTotalPo();
        po.setWhereId(id);
        return get(po);
    }

    /**
     * 根据查询多条数据
     *
     * @param id 
     * @return 查询结果
     */
    public List<TbBasicStatisticsUserTotal> queryById(Integer id) {
        if (ObjectUtil.isNull(id)) {
            return null;
        }

        TbBasicStatisticsUserTotalPo po = new TbBasicStatisticsUserTotalPo();
        po.setWhereId(id);
        return query(po);
    }

    /**
     * 根据获取指定字段的值
     *
     * @param id 
     * @param fieldMapper 字段映射函数
     * @param <T> 字段类型
     * @return 字段值的 Optional
     */
    public <T> Optional<T> getFieldById(Integer id, Function<TbBasicStatisticsUserTotal, T> fieldMapper) {
        if (ObjectUtil.isNull(id)) {
            return Optional.empty();
        }

        return Optional.ofNullable(getById(id)).map(fieldMapper);
   }

    /**
     * 根据日期查询单条数据
     *
     * @param day 日期
     * @return 查询结果
     */
    public TbBasicStatisticsUserTotal getByDay(String day) {
        if (StrUtil.isBlank(day)) {
            return null;
        }

        TbBasicStatisticsUserTotalPo po = new TbBasicStatisticsUserTotalPo();
        po.setWhereDay(day);
        return get(po);
    }

    /**
     * 根据日期查询多条数据
     *
     * @param day 日期
     * @return 查询结果
     */
    public List<TbBasicStatisticsUserTotal> queryByDay(String day) {
        if (StrUtil.isBlank(day)) {
            return null;
        }

        TbBasicStatisticsUserTotalPo po = new TbBasicStatisticsUserTotalPo();
        po.setWhereDay(day);
        return query(po);
    }

    /**
     * 根据日期获取指定字段的值
     *
     * @param day 日期
     * @param fieldMapper 字段映射函数
     * @param <T> 字段类型
     * @return 字段值的 Optional
     */
    public <T> Optional<T> getFieldByDay(String day, Function<TbBasicStatisticsUserTotal, T> fieldMapper) {
        if (StrUtil.isBlank(day)) {
            return Optional.empty();
        }

        return Optional.ofNullable(getByDay(day)).map(fieldMapper);
   }

    /**
     * 根据新增用户数查询单条数据
     *
     * @param cnt 新增用户数
     * @return 查询结果
     */
    public TbBasicStatisticsUserTotal getByCnt(Integer cnt) {
        if (ObjectUtil.isNull(cnt)) {
            return null;
        }

        TbBasicStatisticsUserTotalPo po = new TbBasicStatisticsUserTotalPo();
        po.setWhereCnt(cnt);
        return get(po);
    }

    /**
     * 根据新增用户数查询多条数据
     *
     * @param cnt 新增用户数
     * @return 查询结果
     */
    public List<TbBasicStatisticsUserTotal> queryByCnt(Integer cnt) {
        if (ObjectUtil.isNull(cnt)) {
            return null;
        }

        TbBasicStatisticsUserTotalPo po = new TbBasicStatisticsUserTotalPo();
        po.setWhereCnt(cnt);
        return query(po);
    }

    /**
     * 根据新增用户数获取指定字段的值
     *
     * @param cnt 新增用户数
     * @param fieldMapper 字段映射函数
     * @param <T> 字段类型
     * @return 字段值的 Optional
     */
    public <T> Optional<T> getFieldByCnt(Integer cnt, Function<TbBasicStatisticsUserTotal, T> fieldMapper) {
        if (ObjectUtil.isNull(cnt)) {
            return Optional.empty();
        }

        return Optional.ofNullable(getByCnt(cnt)).map(fieldMapper);
   }

    /**
     * 根据更新时间查询单条数据
     *
     * @param updatedAt 更新时间
     * @return 查询结果
     */
    public TbBasicStatisticsUserTotal getByUpdatedAt(String updatedAt) {
        if (StrUtil.isBlank(updatedAt)) {
            return null;
        }

        TbBasicStatisticsUserTotalPo po = new TbBasicStatisticsUserTotalPo();
        po.setWhereUpdatedAt(updatedAt);
        return get(po);
    }

    /**
     * 根据更新时间查询多条数据
     *
     * @param updatedAt 更新时间
     * @return 查询结果
     */
    public List<TbBasicStatisticsUserTotal> queryByUpdatedAt(String updatedAt) {
        if (StrUtil.isBlank(updatedAt)) {
            return null;
        }

        TbBasicStatisticsUserTotalPo po = new TbBasicStatisticsUserTotalPo();
        po.setWhereUpdatedAt(updatedAt);
        return query(po);
    }

    /**
     * 根据更新时间获取指定字段的值
     *
     * @param updatedAt 更新时间
     * @param fieldMapper 字段映射函数
     * @param <T> 字段类型
     * @return 字段值的 Optional
     */
    public <T> Optional<T> getFieldByUpdatedAt(String updatedAt, Function<TbBasicStatisticsUserTotal, T> fieldMapper) {
        if (StrUtil.isBlank(updatedAt)) {
            return Optional.empty();
        }

        return Optional.ofNullable(getByUpdatedAt(updatedAt)).map(fieldMapper);
   }

    //***************************************************获取 END********************************************************

    //***************************************************聚合操作 START******************************************************
    /**
     * 统计的总和
     *
     * @param po 查询条件
     * @return 总和结果
     */
    public BigDecimal sumId(TbBasicStatisticsUserTotalPo po) {
        if (po == null) {
            return BigDecimal.ZERO;
        }
        return mapper.sumId(po);
    }

    /**
     * 统计的最大值
     *
     * @param po 查询条件
     * @return 最大值结果
     */
    public BigDecimal maxId(TbBasicStatisticsUserTotalPo po) {
        if (po == null) {
            return BigDecimal.ZERO;
        }
        return mapper.maxId(po);
    }

    /**
     * 统计的最小值
     *
     * @param po 查询条件
     * @return 最小值结果
     */
    public BigDecimal minId(TbBasicStatisticsUserTotalPo po) {
        if (po == null) {
            return BigDecimal.ZERO;
        }
        return mapper.minId(po);
    }

    /**
     * 统计的平均值
     *
     * @param po 查询条件
     * @return 平均值结果
     */
    public BigDecimal avgId(TbBasicStatisticsUserTotalPo po) {
        if (po == null) {
            return BigDecimal.ZERO;
        }
        return mapper.avgId(po);
    }
    /**
     * 统计新增用户数的总和
     *
     * @param po 查询条件
     * @return 总和结果
     */
    public BigDecimal sumCnt(TbBasicStatisticsUserTotalPo po) {
        if (po == null) {
            return BigDecimal.ZERO;
        }
        return mapper.sumCnt(po);
    }

    /**
     * 统计新增用户数的最大值
     *
     * @param po 查询条件
     * @return 最大值结果
     */
    public BigDecimal maxCnt(TbBasicStatisticsUserTotalPo po) {
        if (po == null) {
            return BigDecimal.ZERO;
        }
        return mapper.maxCnt(po);
    }

    /**
     * 统计新增用户数的最小值
     *
     * @param po 查询条件
     * @return 最小值结果
     */
    public BigDecimal minCnt(TbBasicStatisticsUserTotalPo po) {
        if (po == null) {
            return BigDecimal.ZERO;
        }
        return mapper.minCnt(po);
    }

    /**
     * 统计新增用户数的平均值
     *
     * @param po 查询条件
     * @return 平均值结果
     */
    public BigDecimal avgCnt(TbBasicStatisticsUserTotalPo po) {
        if (po == null) {
            return BigDecimal.ZERO;
        }
        return mapper.avgCnt(po);
    }
    //***************************************************聚合操作 END********************************************************
}