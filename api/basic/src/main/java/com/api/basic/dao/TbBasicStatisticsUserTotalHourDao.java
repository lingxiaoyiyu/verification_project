package com.api.basic.dao;

import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import com.api.basic.data.entity.TbBasicStatisticsUserTotalHour;
import com.api.basic.data.po.TbBasicStatisticsUserTotalHourPo;
import com.api.basic.mapper.TbBasicStatisticsUserTotalHourMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.*;
import java.util.function.Function;

/**
 * 用户每小时注册统计表Dao
 *
 * @author 裴金伟
 */
@Component
@RequiredArgsConstructor
public class TbBasicStatisticsUserTotalHourDao {

    private final TbBasicStatisticsUserTotalHourMapper mapper;

    //***************************************************添加 START******************************************************
    /**
     * 新增
     *
     * @param entity 新增信息
     * @return 新增ID
     */
    public Integer add(TbBasicStatisticsUserTotalHour entity) {
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
    public Integer delete(TbBasicStatisticsUserTotalHourPo po) {
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
    public Integer update(TbBasicStatisticsUserTotalHourPo po) {
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
    public List<TbBasicStatisticsUserTotalHour> query(TbBasicStatisticsUserTotalHourPo po) {
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
    public Integer cnt(TbBasicStatisticsUserTotalHourPo po) {
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
    public TbBasicStatisticsUserTotalHour get(TbBasicStatisticsUserTotalHourPo po) {
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
    public TbBasicStatisticsUserTotalHour getById(Integer id) {
        if (ObjectUtil.isNull(id)) {
            return null;
        }

        TbBasicStatisticsUserTotalHourPo po = new TbBasicStatisticsUserTotalHourPo();
        po.setWhereId(id);
        return get(po);
    }

    /**
     * 根据查询多条数据
     *
     * @param id 
     * @return 查询结果
     */
    public List<TbBasicStatisticsUserTotalHour> queryById(Integer id) {
        if (ObjectUtil.isNull(id)) {
            return null;
        }

        TbBasicStatisticsUserTotalHourPo po = new TbBasicStatisticsUserTotalHourPo();
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
    public <T> Optional<T> getFieldById(Integer id, Function<TbBasicStatisticsUserTotalHour, T> fieldMapper) {
        if (ObjectUtil.isNull(id)) {
            return Optional.empty();
        }

        return Optional.ofNullable(getById(id)).map(fieldMapper);
   }

    /**
     * 根据日期小时查询单条数据
     *
     * @param dateHour 日期小时
     * @return 查询结果
     */
    public TbBasicStatisticsUserTotalHour getByDateHour(String dateHour) {
        if (StrUtil.isBlank(dateHour)) {
            return null;
        }

        TbBasicStatisticsUserTotalHourPo po = new TbBasicStatisticsUserTotalHourPo();
        po.setWhereDateHour(dateHour);
        return get(po);
    }

    /**
     * 根据日期小时查询多条数据
     *
     * @param dateHour 日期小时
     * @return 查询结果
     */
    public List<TbBasicStatisticsUserTotalHour> queryByDateHour(String dateHour) {
        if (StrUtil.isBlank(dateHour)) {
            return null;
        }

        TbBasicStatisticsUserTotalHourPo po = new TbBasicStatisticsUserTotalHourPo();
        po.setWhereDateHour(dateHour);
        return query(po);
    }

    /**
     * 根据日期小时获取指定字段的值
     *
     * @param dateHour 日期小时
     * @param fieldMapper 字段映射函数
     * @param <T> 字段类型
     * @return 字段值的 Optional
     */
    public <T> Optional<T> getFieldByDateHour(String dateHour, Function<TbBasicStatisticsUserTotalHour, T> fieldMapper) {
        if (StrUtil.isBlank(dateHour)) {
            return Optional.empty();
        }

        return Optional.ofNullable(getByDateHour(dateHour)).map(fieldMapper);
   }

    /**
     * 根据用户注册数查询单条数据
     *
     * @param cnt 用户注册数
     * @return 查询结果
     */
    public TbBasicStatisticsUserTotalHour getByCnt(Integer cnt) {
        if (ObjectUtil.isNull(cnt)) {
            return null;
        }

        TbBasicStatisticsUserTotalHourPo po = new TbBasicStatisticsUserTotalHourPo();
        po.setWhereCnt(cnt);
        return get(po);
    }

    /**
     * 根据用户注册数查询多条数据
     *
     * @param cnt 用户注册数
     * @return 查询结果
     */
    public List<TbBasicStatisticsUserTotalHour> queryByCnt(Integer cnt) {
        if (ObjectUtil.isNull(cnt)) {
            return null;
        }

        TbBasicStatisticsUserTotalHourPo po = new TbBasicStatisticsUserTotalHourPo();
        po.setWhereCnt(cnt);
        return query(po);
    }

    /**
     * 根据用户注册数获取指定字段的值
     *
     * @param cnt 用户注册数
     * @param fieldMapper 字段映射函数
     * @param <T> 字段类型
     * @return 字段值的 Optional
     */
    public <T> Optional<T> getFieldByCnt(Integer cnt, Function<TbBasicStatisticsUserTotalHour, T> fieldMapper) {
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
    public TbBasicStatisticsUserTotalHour getByUpdatedAt(String updatedAt) {
        if (StrUtil.isBlank(updatedAt)) {
            return null;
        }

        TbBasicStatisticsUserTotalHourPo po = new TbBasicStatisticsUserTotalHourPo();
        po.setWhereUpdatedAt(updatedAt);
        return get(po);
    }

    /**
     * 根据更新时间查询多条数据
     *
     * @param updatedAt 更新时间
     * @return 查询结果
     */
    public List<TbBasicStatisticsUserTotalHour> queryByUpdatedAt(String updatedAt) {
        if (StrUtil.isBlank(updatedAt)) {
            return null;
        }

        TbBasicStatisticsUserTotalHourPo po = new TbBasicStatisticsUserTotalHourPo();
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
    public <T> Optional<T> getFieldByUpdatedAt(String updatedAt, Function<TbBasicStatisticsUserTotalHour, T> fieldMapper) {
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
    public BigDecimal sumId(TbBasicStatisticsUserTotalHourPo po) {
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
    public BigDecimal maxId(TbBasicStatisticsUserTotalHourPo po) {
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
    public BigDecimal minId(TbBasicStatisticsUserTotalHourPo po) {
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
    public BigDecimal avgId(TbBasicStatisticsUserTotalHourPo po) {
        if (po == null) {
            return BigDecimal.ZERO;
        }
        return mapper.avgId(po);
    }
    /**
     * 统计用户注册数的总和
     *
     * @param po 查询条件
     * @return 总和结果
     */
    public BigDecimal sumCnt(TbBasicStatisticsUserTotalHourPo po) {
        if (po == null) {
            return BigDecimal.ZERO;
        }
        return mapper.sumCnt(po);
    }

    /**
     * 统计用户注册数的最大值
     *
     * @param po 查询条件
     * @return 最大值结果
     */
    public BigDecimal maxCnt(TbBasicStatisticsUserTotalHourPo po) {
        if (po == null) {
            return BigDecimal.ZERO;
        }
        return mapper.maxCnt(po);
    }

    /**
     * 统计用户注册数的最小值
     *
     * @param po 查询条件
     * @return 最小值结果
     */
    public BigDecimal minCnt(TbBasicStatisticsUserTotalHourPo po) {
        if (po == null) {
            return BigDecimal.ZERO;
        }
        return mapper.minCnt(po);
    }

    /**
     * 统计用户注册数的平均值
     *
     * @param po 查询条件
     * @return 平均值结果
     */
    public BigDecimal avgCnt(TbBasicStatisticsUserTotalHourPo po) {
        if (po == null) {
            return BigDecimal.ZERO;
        }
        return mapper.avgCnt(po);
    }
    //***************************************************聚合操作 END********************************************************
}