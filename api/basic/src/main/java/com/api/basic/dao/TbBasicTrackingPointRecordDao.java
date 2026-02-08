package com.api.basic.dao;

import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import com.api.basic.data.entity.TbBasicTrackingPointRecord;
import com.api.basic.data.po.TbBasicTrackingPointRecordPo;
import com.api.basic.mapper.TbBasicTrackingPointRecordMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.*;
import java.util.function.Function;

/**
 * 埋点记录Dao
 *
 * @author 裴金伟
 */
@Component
@RequiredArgsConstructor
public class TbBasicTrackingPointRecordDao {

    private final TbBasicTrackingPointRecordMapper mapper;

    //***************************************************添加 START******************************************************
    /**
     * 新增
     *
     * @param entity 新增信息
     * @return 新增ID
     */
    public Integer add(TbBasicTrackingPointRecord entity) {
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
    public Integer delete(TbBasicTrackingPointRecordPo po) {
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
    public Integer update(TbBasicTrackingPointRecordPo po) {
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
    public List<TbBasicTrackingPointRecord> query(TbBasicTrackingPointRecordPo po) {
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
    public Integer cnt(TbBasicTrackingPointRecordPo po) {
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
    public TbBasicTrackingPointRecord get(TbBasicTrackingPointRecordPo po) {
        if (po == null) {
            return null;
        }
        return mapper.query(po).stream().findFirst().orElse(null);
    }

    /**
     * 根据ID查询单条数据
     *
     * @param id ID
     * @return 查询结果
     */
    public TbBasicTrackingPointRecord getById(Integer id) {
        if (ObjectUtil.isNull(id)) {
            return null;
        }

        TbBasicTrackingPointRecordPo po = new TbBasicTrackingPointRecordPo();
        po.setWhereId(id);
        return get(po);
    }

    /**
     * 根据ID查询多条数据
     *
     * @param id ID
     * @return 查询结果
     */
    public List<TbBasicTrackingPointRecord> queryById(Integer id) {
        if (ObjectUtil.isNull(id)) {
            return null;
        }

        TbBasicTrackingPointRecordPo po = new TbBasicTrackingPointRecordPo();
        po.setWhereId(id);
        return query(po);
    }

    /**
     * 根据ID获取指定字段的值
     *
     * @param id ID
     * @param fieldMapper 字段映射函数
     * @param <T> 字段类型
     * @return 字段值的 Optional
     */
    public <T> Optional<T> getFieldById(Integer id, Function<TbBasicTrackingPointRecord, T> fieldMapper) {
        if (ObjectUtil.isNull(id)) {
            return Optional.empty();
        }

        return Optional.ofNullable(getById(id)).map(fieldMapper);
   }

    /**
     * 根据页面查询单条数据
     *
     * @param page 页面
     * @return 查询结果
     */
    public TbBasicTrackingPointRecord getByPage(String page) {
        if (StrUtil.isBlank(page)) {
            return null;
        }

        TbBasicTrackingPointRecordPo po = new TbBasicTrackingPointRecordPo();
        po.setWherePage(page);
        return get(po);
    }

    /**
     * 根据页面查询多条数据
     *
     * @param page 页面
     * @return 查询结果
     */
    public List<TbBasicTrackingPointRecord> queryByPage(String page) {
        if (StrUtil.isBlank(page)) {
            return null;
        }

        TbBasicTrackingPointRecordPo po = new TbBasicTrackingPointRecordPo();
        po.setWherePage(page);
        return query(po);
    }

    /**
     * 根据页面获取指定字段的值
     *
     * @param page 页面
     * @param fieldMapper 字段映射函数
     * @param <T> 字段类型
     * @return 字段值的 Optional
     */
    public <T> Optional<T> getFieldByPage(String page, Function<TbBasicTrackingPointRecord, T> fieldMapper) {
        if (StrUtil.isBlank(page)) {
            return Optional.empty();
        }

        return Optional.ofNullable(getByPage(page)).map(fieldMapper);
   }

    /**
     * 根据标识查询单条数据
     *
     * @param tag 标识
     * @return 查询结果
     */
    public TbBasicTrackingPointRecord getByTag(String tag) {
        if (StrUtil.isBlank(tag)) {
            return null;
        }

        TbBasicTrackingPointRecordPo po = new TbBasicTrackingPointRecordPo();
        po.setWhereTag(tag);
        return get(po);
    }

    /**
     * 根据标识查询多条数据
     *
     * @param tag 标识
     * @return 查询结果
     */
    public List<TbBasicTrackingPointRecord> queryByTag(String tag) {
        if (StrUtil.isBlank(tag)) {
            return null;
        }

        TbBasicTrackingPointRecordPo po = new TbBasicTrackingPointRecordPo();
        po.setWhereTag(tag);
        return query(po);
    }

    /**
     * 根据标识获取指定字段的值
     *
     * @param tag 标识
     * @param fieldMapper 字段映射函数
     * @param <T> 字段类型
     * @return 字段值的 Optional
     */
    public <T> Optional<T> getFieldByTag(String tag, Function<TbBasicTrackingPointRecord, T> fieldMapper) {
        if (StrUtil.isBlank(tag)) {
            return Optional.empty();
        }

        return Optional.ofNullable(getByTag(tag)).map(fieldMapper);
   }

    /**
     * 根据创建时间查询单条数据
     *
     * @param createdAt 创建时间
     * @return 查询结果
     */
    public TbBasicTrackingPointRecord getByCreatedAt(String createdAt) {
        if (StrUtil.isBlank(createdAt)) {
            return null;
        }

        TbBasicTrackingPointRecordPo po = new TbBasicTrackingPointRecordPo();
        po.setWhereCreatedAt(createdAt);
        return get(po);
    }

    /**
     * 根据创建时间查询多条数据
     *
     * @param createdAt 创建时间
     * @return 查询结果
     */
    public List<TbBasicTrackingPointRecord> queryByCreatedAt(String createdAt) {
        if (StrUtil.isBlank(createdAt)) {
            return null;
        }

        TbBasicTrackingPointRecordPo po = new TbBasicTrackingPointRecordPo();
        po.setWhereCreatedAt(createdAt);
        return query(po);
    }

    /**
     * 根据创建时间获取指定字段的值
     *
     * @param createdAt 创建时间
     * @param fieldMapper 字段映射函数
     * @param <T> 字段类型
     * @return 字段值的 Optional
     */
    public <T> Optional<T> getFieldByCreatedAt(String createdAt, Function<TbBasicTrackingPointRecord, T> fieldMapper) {
        if (StrUtil.isBlank(createdAt)) {
            return Optional.empty();
        }

        return Optional.ofNullable(getByCreatedAt(createdAt)).map(fieldMapper);
   }

    /**
     * 根据访问UA查询单条数据
     *
     * @param UA 访问UA
     * @return 查询结果
     */
    public TbBasicTrackingPointRecord getByUA(String UA) {
        if (StrUtil.isBlank(UA)) {
            return null;
        }

        TbBasicTrackingPointRecordPo po = new TbBasicTrackingPointRecordPo();
        po.setWhereUA(UA);
        return get(po);
    }

    /**
     * 根据访问UA查询多条数据
     *
     * @param UA 访问UA
     * @return 查询结果
     */
    public List<TbBasicTrackingPointRecord> queryByUA(String UA) {
        if (StrUtil.isBlank(UA)) {
            return null;
        }

        TbBasicTrackingPointRecordPo po = new TbBasicTrackingPointRecordPo();
        po.setWhereUA(UA);
        return query(po);
    }

    /**
     * 根据访问UA获取指定字段的值
     *
     * @param UA 访问UA
     * @param fieldMapper 字段映射函数
     * @param <T> 字段类型
     * @return 字段值的 Optional
     */
    public <T> Optional<T> getFieldByUA(String UA, Function<TbBasicTrackingPointRecord, T> fieldMapper) {
        if (StrUtil.isBlank(UA)) {
            return Optional.empty();
        }

        return Optional.ofNullable(getByUA(UA)).map(fieldMapper);
   }

    /**
     * 根据访问IP查询单条数据
     *
     * @param ip 访问IP
     * @return 查询结果
     */
    public TbBasicTrackingPointRecord getByIp(String ip) {
        if (StrUtil.isBlank(ip)) {
            return null;
        }

        TbBasicTrackingPointRecordPo po = new TbBasicTrackingPointRecordPo();
        po.setWhereIp(ip);
        return get(po);
    }

    /**
     * 根据访问IP查询多条数据
     *
     * @param ip 访问IP
     * @return 查询结果
     */
    public List<TbBasicTrackingPointRecord> queryByIp(String ip) {
        if (StrUtil.isBlank(ip)) {
            return null;
        }

        TbBasicTrackingPointRecordPo po = new TbBasicTrackingPointRecordPo();
        po.setWhereIp(ip);
        return query(po);
    }

    /**
     * 根据访问IP获取指定字段的值
     *
     * @param ip 访问IP
     * @param fieldMapper 字段映射函数
     * @param <T> 字段类型
     * @return 字段值的 Optional
     */
    public <T> Optional<T> getFieldByIp(String ip, Function<TbBasicTrackingPointRecord, T> fieldMapper) {
        if (StrUtil.isBlank(ip)) {
            return Optional.empty();
        }

        return Optional.ofNullable(getByIp(ip)).map(fieldMapper);
   }

    //***************************************************获取 END********************************************************
}