package com.api.basic.dao;

import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import com.api.basic.data.entity.TbBasicSysRegion;
import com.api.basic.data.po.TbBasicSysRegionPo;
import com.api.basic.mapper.TbBasicSysRegionMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.*;
import java.util.function.Function;

/**
 * 行政区域信息Dao
 *
 * @author 裴金伟
 */
@Component
@RequiredArgsConstructor
public class TbBasicSysRegionDao {

    private final TbBasicSysRegionMapper mapper;

    //***************************************************添加 START******************************************************
    /**
     * 新增
     *
     * @param entity 新增信息
     * @return 新增ID
     */
    public Integer add(TbBasicSysRegion entity) {
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
    public Integer delete(TbBasicSysRegionPo po) {
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
    public Integer update(TbBasicSysRegionPo po) {
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
    public List<TbBasicSysRegion> query(TbBasicSysRegionPo po) {
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
    public Integer cnt(TbBasicSysRegionPo po) {
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
    public TbBasicSysRegion get(TbBasicSysRegionPo po) {
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
    public TbBasicSysRegion getById(Integer id) {
        if (ObjectUtil.isNull(id)) {
            return null;
        }

        TbBasicSysRegionPo po = new TbBasicSysRegionPo();
        po.setWhereId(id);
        return get(po);
    }

    /**
     * 根据ID查询多条数据
     *
     * @param id ID
     * @return 查询结果
     */
    public List<TbBasicSysRegion> queryById(Integer id) {
        if (ObjectUtil.isNull(id)) {
            return null;
        }

        TbBasicSysRegionPo po = new TbBasicSysRegionPo();
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
    public <T> Optional<T> getFieldById(Integer id, Function<TbBasicSysRegion, T> fieldMapper) {
        if (ObjectUtil.isNull(id)) {
            return Optional.empty();
        }

        return Optional.ofNullable(getById(id)).map(fieldMapper);
   }

    /**
     * 根据上一级的id值查询单条数据
     *
     * @param parentCode 上一级的id值
     * @return 查询结果
     */
    public TbBasicSysRegion getByParentCode(Long parentCode) {
        if (ObjectUtil.isNull(parentCode)) {
            return null;
        }

        TbBasicSysRegionPo po = new TbBasicSysRegionPo();
        po.setWhereParentCode(parentCode);
        return get(po);
    }

    /**
     * 根据上一级的id值查询多条数据
     *
     * @param parentCode 上一级的id值
     * @return 查询结果
     */
    public List<TbBasicSysRegion> queryByParentCode(Long parentCode) {
        if (ObjectUtil.isNull(parentCode)) {
            return null;
        }

        TbBasicSysRegionPo po = new TbBasicSysRegionPo();
        po.setWhereParentCode(parentCode);
        return query(po);
    }

    /**
     * 根据上一级的id值获取指定字段的值
     *
     * @param parentCode 上一级的id值
     * @param fieldMapper 字段映射函数
     * @param <T> 字段类型
     * @return 字段值的 Optional
     */
    public <T> Optional<T> getFieldByParentCode(Long parentCode, Function<TbBasicSysRegion, T> fieldMapper) {
        if (ObjectUtil.isNull(parentCode)) {
            return Optional.empty();
        }

        return Optional.ofNullable(getByParentCode(parentCode)).map(fieldMapper);
   }

    /**
     * 根据行政编码查询单条数据
     *
     * @param code 行政编码
     * @return 查询结果
     */
    public TbBasicSysRegion getByCode(Long code) {
        if (ObjectUtil.isNull(code)) {
            return null;
        }

        TbBasicSysRegionPo po = new TbBasicSysRegionPo();
        po.setWhereCode(code);
        return get(po);
    }

    /**
     * 根据行政编码查询多条数据
     *
     * @param code 行政编码
     * @return 查询结果
     */
    public List<TbBasicSysRegion> queryByCode(Long code) {
        if (ObjectUtil.isNull(code)) {
            return null;
        }

        TbBasicSysRegionPo po = new TbBasicSysRegionPo();
        po.setWhereCode(code);
        return query(po);
    }

    /**
     * 根据行政编码获取指定字段的值
     *
     * @param code 行政编码
     * @param fieldMapper 字段映射函数
     * @param <T> 字段类型
     * @return 字段值的 Optional
     */
    public <T> Optional<T> getFieldByCode(Long code, Function<TbBasicSysRegion, T> fieldMapper) {
        if (ObjectUtil.isNull(code)) {
            return Optional.empty();
        }

        return Optional.ofNullable(getByCode(code)).map(fieldMapper);
   }

    /**
     * 根据地区名称查询单条数据
     *
     * @param name 地区名称
     * @return 查询结果
     */
    public TbBasicSysRegion getByName(String name) {
        if (StrUtil.isBlank(name)) {
            return null;
        }

        TbBasicSysRegionPo po = new TbBasicSysRegionPo();
        po.setWhereName(name);
        return get(po);
    }

    /**
     * 根据地区名称查询多条数据
     *
     * @param name 地区名称
     * @return 查询结果
     */
    public List<TbBasicSysRegion> queryByName(String name) {
        if (StrUtil.isBlank(name)) {
            return null;
        }

        TbBasicSysRegionPo po = new TbBasicSysRegionPo();
        po.setWhereName(name);
        return query(po);
    }

    /**
     * 根据地区名称获取指定字段的值
     *
     * @param name 地区名称
     * @param fieldMapper 字段映射函数
     * @param <T> 字段类型
     * @return 字段值的 Optional
     */
    public <T> Optional<T> getFieldByName(String name, Function<TbBasicSysRegion, T> fieldMapper) {
        if (StrUtil.isBlank(name)) {
            return Optional.empty();
        }

        return Optional.ofNullable(getByName(name)).map(fieldMapper);
   }

    /**
     * 根据排序查询单条数据
     *
     * @param sort 排序
     * @return 查询结果
     */
    public TbBasicSysRegion getBySort(Integer sort) {
        if (ObjectUtil.isNull(sort)) {
            return null;
        }

        TbBasicSysRegionPo po = new TbBasicSysRegionPo();
        po.setWhereSort(sort);
        return get(po);
    }

    /**
     * 根据排序查询多条数据
     *
     * @param sort 排序
     * @return 查询结果
     */
    public List<TbBasicSysRegion> queryBySort(Integer sort) {
        if (ObjectUtil.isNull(sort)) {
            return null;
        }

        TbBasicSysRegionPo po = new TbBasicSysRegionPo();
        po.setWhereSort(sort);
        return query(po);
    }

    /**
     * 根据排序获取指定字段的值
     *
     * @param sort 排序
     * @param fieldMapper 字段映射函数
     * @param <T> 字段类型
     * @return 字段值的 Optional
     */
    public <T> Optional<T> getFieldBySort(Integer sort, Function<TbBasicSysRegion, T> fieldMapper) {
        if (ObjectUtil.isNull(sort)) {
            return Optional.empty();
        }

        return Optional.ofNullable(getBySort(sort)).map(fieldMapper);
   }

    /**
     * 根据是否删除。1：未删除，2：已删除。查询单条数据
     *
     * @param isDelete 是否删除。1：未删除，2：已删除。
     * @return 查询结果
     */
    public TbBasicSysRegion getByIsDelete(Integer isDelete) {
        if (ObjectUtil.isNull(isDelete)) {
            return null;
        }

        TbBasicSysRegionPo po = new TbBasicSysRegionPo();
        po.setWhereIsDelete(isDelete);
        return get(po);
    }

    /**
     * 根据是否删除。1：未删除，2：已删除。查询多条数据
     *
     * @param isDelete 是否删除。1：未删除，2：已删除。
     * @return 查询结果
     */
    public List<TbBasicSysRegion> queryByIsDelete(Integer isDelete) {
        if (ObjectUtil.isNull(isDelete)) {
            return null;
        }

        TbBasicSysRegionPo po = new TbBasicSysRegionPo();
        po.setWhereIsDelete(isDelete);
        return query(po);
    }

    /**
     * 根据是否删除。1：未删除，2：已删除。获取指定字段的值
     *
     * @param isDelete 是否删除。1：未删除，2：已删除。
     * @param fieldMapper 字段映射函数
     * @param <T> 字段类型
     * @return 字段值的 Optional
     */
    public <T> Optional<T> getFieldByIsDelete(Integer isDelete, Function<TbBasicSysRegion, T> fieldMapper) {
        if (ObjectUtil.isNull(isDelete)) {
            return Optional.empty();
        }

        return Optional.ofNullable(getByIsDelete(isDelete)).map(fieldMapper);
   }

    /**
     * 根据层级查询单条数据
     *
     * @param level 层级
     * @return 查询结果
     */
    public TbBasicSysRegion getByLevel(Integer level) {
        if (ObjectUtil.isNull(level)) {
            return null;
        }

        TbBasicSysRegionPo po = new TbBasicSysRegionPo();
        po.setWhereLevel(level);
        return get(po);
    }

    /**
     * 根据层级查询多条数据
     *
     * @param level 层级
     * @return 查询结果
     */
    public List<TbBasicSysRegion> queryByLevel(Integer level) {
        if (ObjectUtil.isNull(level)) {
            return null;
        }

        TbBasicSysRegionPo po = new TbBasicSysRegionPo();
        po.setWhereLevel(level);
        return query(po);
    }

    /**
     * 根据层级获取指定字段的值
     *
     * @param level 层级
     * @param fieldMapper 字段映射函数
     * @param <T> 字段类型
     * @return 字段值的 Optional
     */
    public <T> Optional<T> getFieldByLevel(Integer level, Function<TbBasicSysRegion, T> fieldMapper) {
        if (ObjectUtil.isNull(level)) {
            return Optional.empty();
        }

        return Optional.ofNullable(getByLevel(level)).map(fieldMapper);
   }

    /**
     * 根据创建时间查询单条数据
     *
     * @param createdAt 创建时间
     * @return 查询结果
     */
    public TbBasicSysRegion getByCreatedAt(String createdAt) {
        if (StrUtil.isBlank(createdAt)) {
            return null;
        }

        TbBasicSysRegionPo po = new TbBasicSysRegionPo();
        po.setWhereCreatedAt(createdAt);
        return get(po);
    }

    /**
     * 根据创建时间查询多条数据
     *
     * @param createdAt 创建时间
     * @return 查询结果
     */
    public List<TbBasicSysRegion> queryByCreatedAt(String createdAt) {
        if (StrUtil.isBlank(createdAt)) {
            return null;
        }

        TbBasicSysRegionPo po = new TbBasicSysRegionPo();
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
    public <T> Optional<T> getFieldByCreatedAt(String createdAt, Function<TbBasicSysRegion, T> fieldMapper) {
        if (StrUtil.isBlank(createdAt)) {
            return Optional.empty();
        }

        return Optional.ofNullable(getByCreatedAt(createdAt)).map(fieldMapper);
   }

    /**
     * 根据创建者查询单条数据
     *
     * @param createdUserId 创建者
     * @return 查询结果
     */
    public TbBasicSysRegion getByCreatedUserId(Integer createdUserId) {
        if (ObjectUtil.isNull(createdUserId)) {
            return null;
        }

        TbBasicSysRegionPo po = new TbBasicSysRegionPo();
        po.setWhereCreatedUserId(createdUserId);
        return get(po);
    }

    /**
     * 根据创建者查询多条数据
     *
     * @param createdUserId 创建者
     * @return 查询结果
     */
    public List<TbBasicSysRegion> queryByCreatedUserId(Integer createdUserId) {
        if (ObjectUtil.isNull(createdUserId)) {
            return null;
        }

        TbBasicSysRegionPo po = new TbBasicSysRegionPo();
        po.setWhereCreatedUserId(createdUserId);
        return query(po);
    }

    /**
     * 根据创建者获取指定字段的值
     *
     * @param createdUserId 创建者
     * @param fieldMapper 字段映射函数
     * @param <T> 字段类型
     * @return 字段值的 Optional
     */
    public <T> Optional<T> getFieldByCreatedUserId(Integer createdUserId, Function<TbBasicSysRegion, T> fieldMapper) {
        if (ObjectUtil.isNull(createdUserId)) {
            return Optional.empty();
        }

        return Optional.ofNullable(getByCreatedUserId(createdUserId)).map(fieldMapper);
   }

    /**
     * 根据更新时间查询单条数据
     *
     * @param updatedAt 更新时间
     * @return 查询结果
     */
    public TbBasicSysRegion getByUpdatedAt(String updatedAt) {
        if (StrUtil.isBlank(updatedAt)) {
            return null;
        }

        TbBasicSysRegionPo po = new TbBasicSysRegionPo();
        po.setWhereUpdatedAt(updatedAt);
        return get(po);
    }

    /**
     * 根据更新时间查询多条数据
     *
     * @param updatedAt 更新时间
     * @return 查询结果
     */
    public List<TbBasicSysRegion> queryByUpdatedAt(String updatedAt) {
        if (StrUtil.isBlank(updatedAt)) {
            return null;
        }

        TbBasicSysRegionPo po = new TbBasicSysRegionPo();
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
    public <T> Optional<T> getFieldByUpdatedAt(String updatedAt, Function<TbBasicSysRegion, T> fieldMapper) {
        if (StrUtil.isBlank(updatedAt)) {
            return Optional.empty();
        }

        return Optional.ofNullable(getByUpdatedAt(updatedAt)).map(fieldMapper);
   }

    /**
     * 根据更新者查询单条数据
     *
     * @param updatedUserId 更新者
     * @return 查询结果
     */
    public TbBasicSysRegion getByUpdatedUserId(Integer updatedUserId) {
        if (ObjectUtil.isNull(updatedUserId)) {
            return null;
        }

        TbBasicSysRegionPo po = new TbBasicSysRegionPo();
        po.setWhereUpdatedUserId(updatedUserId);
        return get(po);
    }

    /**
     * 根据更新者查询多条数据
     *
     * @param updatedUserId 更新者
     * @return 查询结果
     */
    public List<TbBasicSysRegion> queryByUpdatedUserId(Integer updatedUserId) {
        if (ObjectUtil.isNull(updatedUserId)) {
            return null;
        }

        TbBasicSysRegionPo po = new TbBasicSysRegionPo();
        po.setWhereUpdatedUserId(updatedUserId);
        return query(po);
    }

    /**
     * 根据更新者获取指定字段的值
     *
     * @param updatedUserId 更新者
     * @param fieldMapper 字段映射函数
     * @param <T> 字段类型
     * @return 字段值的 Optional
     */
    public <T> Optional<T> getFieldByUpdatedUserId(Integer updatedUserId, Function<TbBasicSysRegion, T> fieldMapper) {
        if (ObjectUtil.isNull(updatedUserId)) {
            return Optional.empty();
        }

        return Optional.ofNullable(getByUpdatedUserId(updatedUserId)).map(fieldMapper);
   }

    //***************************************************获取 END********************************************************

    //***************************************************聚合操作 START******************************************************
    /**
     * 统计ID的总和
     *
     * @param po 查询条件
     * @return 总和结果
     */
    public BigDecimal sumId(TbBasicSysRegionPo po) {
        if (po == null) {
            return BigDecimal.ZERO;
        }
        return mapper.sumId(po);
    }

    /**
     * 统计ID的最大值
     *
     * @param po 查询条件
     * @return 最大值结果
     */
    public BigDecimal maxId(TbBasicSysRegionPo po) {
        if (po == null) {
            return BigDecimal.ZERO;
        }
        return mapper.maxId(po);
    }

    /**
     * 统计ID的最小值
     *
     * @param po 查询条件
     * @return 最小值结果
     */
    public BigDecimal minId(TbBasicSysRegionPo po) {
        if (po == null) {
            return BigDecimal.ZERO;
        }
        return mapper.minId(po);
    }

    /**
     * 统计ID的平均值
     *
     * @param po 查询条件
     * @return 平均值结果
     */
    public BigDecimal avgId(TbBasicSysRegionPo po) {
        if (po == null) {
            return BigDecimal.ZERO;
        }
        return mapper.avgId(po);
    }
    /**
     * 统计上一级的id值的总和
     *
     * @param po 查询条件
     * @return 总和结果
     */
    public BigDecimal sumParentCode(TbBasicSysRegionPo po) {
        if (po == null) {
            return BigDecimal.ZERO;
        }
        return mapper.sumParentCode(po);
    }

    /**
     * 统计上一级的id值的最大值
     *
     * @param po 查询条件
     * @return 最大值结果
     */
    public BigDecimal maxParentCode(TbBasicSysRegionPo po) {
        if (po == null) {
            return BigDecimal.ZERO;
        }
        return mapper.maxParentCode(po);
    }

    /**
     * 统计上一级的id值的最小值
     *
     * @param po 查询条件
     * @return 最小值结果
     */
    public BigDecimal minParentCode(TbBasicSysRegionPo po) {
        if (po == null) {
            return BigDecimal.ZERO;
        }
        return mapper.minParentCode(po);
    }

    /**
     * 统计上一级的id值的平均值
     *
     * @param po 查询条件
     * @return 平均值结果
     */
    public BigDecimal avgParentCode(TbBasicSysRegionPo po) {
        if (po == null) {
            return BigDecimal.ZERO;
        }
        return mapper.avgParentCode(po);
    }
    /**
     * 统计行政编码的总和
     *
     * @param po 查询条件
     * @return 总和结果
     */
    public BigDecimal sumCode(TbBasicSysRegionPo po) {
        if (po == null) {
            return BigDecimal.ZERO;
        }
        return mapper.sumCode(po);
    }

    /**
     * 统计行政编码的最大值
     *
     * @param po 查询条件
     * @return 最大值结果
     */
    public BigDecimal maxCode(TbBasicSysRegionPo po) {
        if (po == null) {
            return BigDecimal.ZERO;
        }
        return mapper.maxCode(po);
    }

    /**
     * 统计行政编码的最小值
     *
     * @param po 查询条件
     * @return 最小值结果
     */
    public BigDecimal minCode(TbBasicSysRegionPo po) {
        if (po == null) {
            return BigDecimal.ZERO;
        }
        return mapper.minCode(po);
    }

    /**
     * 统计行政编码的平均值
     *
     * @param po 查询条件
     * @return 平均值结果
     */
    public BigDecimal avgCode(TbBasicSysRegionPo po) {
        if (po == null) {
            return BigDecimal.ZERO;
        }
        return mapper.avgCode(po);
    }
    /**
     * 统计排序的总和
     *
     * @param po 查询条件
     * @return 总和结果
     */
    public BigDecimal sumSort(TbBasicSysRegionPo po) {
        if (po == null) {
            return BigDecimal.ZERO;
        }
        return mapper.sumSort(po);
    }

    /**
     * 统计排序的最大值
     *
     * @param po 查询条件
     * @return 最大值结果
     */
    public BigDecimal maxSort(TbBasicSysRegionPo po) {
        if (po == null) {
            return BigDecimal.ZERO;
        }
        return mapper.maxSort(po);
    }

    /**
     * 统计排序的最小值
     *
     * @param po 查询条件
     * @return 最小值结果
     */
    public BigDecimal minSort(TbBasicSysRegionPo po) {
        if (po == null) {
            return BigDecimal.ZERO;
        }
        return mapper.minSort(po);
    }

    /**
     * 统计排序的平均值
     *
     * @param po 查询条件
     * @return 平均值结果
     */
    public BigDecimal avgSort(TbBasicSysRegionPo po) {
        if (po == null) {
            return BigDecimal.ZERO;
        }
        return mapper.avgSort(po);
    }
    /**
     * 统计是否删除。1：未删除，2：已删除。的总和
     *
     * @param po 查询条件
     * @return 总和结果
     */
    public BigDecimal sumIsDelete(TbBasicSysRegionPo po) {
        if (po == null) {
            return BigDecimal.ZERO;
        }
        return mapper.sumIsDelete(po);
    }

    /**
     * 统计是否删除。1：未删除，2：已删除。的最大值
     *
     * @param po 查询条件
     * @return 最大值结果
     */
    public BigDecimal maxIsDelete(TbBasicSysRegionPo po) {
        if (po == null) {
            return BigDecimal.ZERO;
        }
        return mapper.maxIsDelete(po);
    }

    /**
     * 统计是否删除。1：未删除，2：已删除。的最小值
     *
     * @param po 查询条件
     * @return 最小值结果
     */
    public BigDecimal minIsDelete(TbBasicSysRegionPo po) {
        if (po == null) {
            return BigDecimal.ZERO;
        }
        return mapper.minIsDelete(po);
    }

    /**
     * 统计是否删除。1：未删除，2：已删除。的平均值
     *
     * @param po 查询条件
     * @return 平均值结果
     */
    public BigDecimal avgIsDelete(TbBasicSysRegionPo po) {
        if (po == null) {
            return BigDecimal.ZERO;
        }
        return mapper.avgIsDelete(po);
    }
    /**
     * 统计层级的总和
     *
     * @param po 查询条件
     * @return 总和结果
     */
    public BigDecimal sumLevel(TbBasicSysRegionPo po) {
        if (po == null) {
            return BigDecimal.ZERO;
        }
        return mapper.sumLevel(po);
    }

    /**
     * 统计层级的最大值
     *
     * @param po 查询条件
     * @return 最大值结果
     */
    public BigDecimal maxLevel(TbBasicSysRegionPo po) {
        if (po == null) {
            return BigDecimal.ZERO;
        }
        return mapper.maxLevel(po);
    }

    /**
     * 统计层级的最小值
     *
     * @param po 查询条件
     * @return 最小值结果
     */
    public BigDecimal minLevel(TbBasicSysRegionPo po) {
        if (po == null) {
            return BigDecimal.ZERO;
        }
        return mapper.minLevel(po);
    }

    /**
     * 统计层级的平均值
     *
     * @param po 查询条件
     * @return 平均值结果
     */
    public BigDecimal avgLevel(TbBasicSysRegionPo po) {
        if (po == null) {
            return BigDecimal.ZERO;
        }
        return mapper.avgLevel(po);
    }
    /**
     * 统计创建者的总和
     *
     * @param po 查询条件
     * @return 总和结果
     */
    public BigDecimal sumCreatedUserId(TbBasicSysRegionPo po) {
        if (po == null) {
            return BigDecimal.ZERO;
        }
        return mapper.sumCreatedUserId(po);
    }

    /**
     * 统计创建者的最大值
     *
     * @param po 查询条件
     * @return 最大值结果
     */
    public BigDecimal maxCreatedUserId(TbBasicSysRegionPo po) {
        if (po == null) {
            return BigDecimal.ZERO;
        }
        return mapper.maxCreatedUserId(po);
    }

    /**
     * 统计创建者的最小值
     *
     * @param po 查询条件
     * @return 最小值结果
     */
    public BigDecimal minCreatedUserId(TbBasicSysRegionPo po) {
        if (po == null) {
            return BigDecimal.ZERO;
        }
        return mapper.minCreatedUserId(po);
    }

    /**
     * 统计创建者的平均值
     *
     * @param po 查询条件
     * @return 平均值结果
     */
    public BigDecimal avgCreatedUserId(TbBasicSysRegionPo po) {
        if (po == null) {
            return BigDecimal.ZERO;
        }
        return mapper.avgCreatedUserId(po);
    }
    /**
     * 统计更新者的总和
     *
     * @param po 查询条件
     * @return 总和结果
     */
    public BigDecimal sumUpdatedUserId(TbBasicSysRegionPo po) {
        if (po == null) {
            return BigDecimal.ZERO;
        }
        return mapper.sumUpdatedUserId(po);
    }

    /**
     * 统计更新者的最大值
     *
     * @param po 查询条件
     * @return 最大值结果
     */
    public BigDecimal maxUpdatedUserId(TbBasicSysRegionPo po) {
        if (po == null) {
            return BigDecimal.ZERO;
        }
        return mapper.maxUpdatedUserId(po);
    }

    /**
     * 统计更新者的最小值
     *
     * @param po 查询条件
     * @return 最小值结果
     */
    public BigDecimal minUpdatedUserId(TbBasicSysRegionPo po) {
        if (po == null) {
            return BigDecimal.ZERO;
        }
        return mapper.minUpdatedUserId(po);
    }

    /**
     * 统计更新者的平均值
     *
     * @param po 查询条件
     * @return 平均值结果
     */
    public BigDecimal avgUpdatedUserId(TbBasicSysRegionPo po) {
        if (po == null) {
            return BigDecimal.ZERO;
        }
        return mapper.avgUpdatedUserId(po);
    }
    //***************************************************聚合操作 END********************************************************
}