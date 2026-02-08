package com.api.basic.dao;

import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import com.api.basic.data.entity.TbBasicSysDictionary;
import com.api.basic.data.po.TbBasicSysDictionaryPo;
import com.api.basic.mapper.TbBasicSysDictionaryMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.*;
import java.util.function.Function;

/**
 * 字典信息表Dao
 *
 * @author 裴金伟
 */
@Component
@RequiredArgsConstructor
public class TbBasicSysDictionaryDao {

    private final TbBasicSysDictionaryMapper mapper;

    //***************************************************添加 START******************************************************
    /**
     * 新增
     *
     * @param entity 新增信息
     * @return 新增ID
     */
    public Integer add(TbBasicSysDictionary entity) {
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
    public Integer delete(TbBasicSysDictionaryPo po) {
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
    public Integer update(TbBasicSysDictionaryPo po) {
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
    public List<TbBasicSysDictionary> query(TbBasicSysDictionaryPo po) {
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
    public Integer cnt(TbBasicSysDictionaryPo po) {
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
    public TbBasicSysDictionary get(TbBasicSysDictionaryPo po) {
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
    public TbBasicSysDictionary getById(Integer id) {
        if (ObjectUtil.isNull(id)) {
            return null;
        }

        TbBasicSysDictionaryPo po = new TbBasicSysDictionaryPo();
        po.setWhereId(id);
        return get(po);
    }

    /**
     * 根据ID查询多条数据
     *
     * @param id ID
     * @return 查询结果
     */
    public List<TbBasicSysDictionary> queryById(Integer id) {
        if (ObjectUtil.isNull(id)) {
            return null;
        }

        TbBasicSysDictionaryPo po = new TbBasicSysDictionaryPo();
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
    public <T> Optional<T> getFieldById(Integer id, Function<TbBasicSysDictionary, T> fieldMapper) {
        if (ObjectUtil.isNull(id)) {
            return Optional.empty();
        }

        return Optional.ofNullable(getById(id)).map(fieldMapper);
   }

    /**
     * 根据父节点ID查询单条数据
     *
     * @param parentId 父节点ID
     * @return 查询结果
     */
    public TbBasicSysDictionary getByParentId(Integer parentId) {
        if (ObjectUtil.isNull(parentId)) {
            return null;
        }

        TbBasicSysDictionaryPo po = new TbBasicSysDictionaryPo();
        po.setWhereParentId(parentId);
        return get(po);
    }

    /**
     * 根据父节点ID查询多条数据
     *
     * @param parentId 父节点ID
     * @return 查询结果
     */
    public List<TbBasicSysDictionary> queryByParentId(Integer parentId) {
        if (ObjectUtil.isNull(parentId)) {
            return null;
        }

        TbBasicSysDictionaryPo po = new TbBasicSysDictionaryPo();
        po.setWhereParentId(parentId);
        return query(po);
    }

    /**
     * 根据父节点ID获取指定字段的值
     *
     * @param parentId 父节点ID
     * @param fieldMapper 字段映射函数
     * @param <T> 字段类型
     * @return 字段值的 Optional
     */
    public <T> Optional<T> getFieldByParentId(Integer parentId, Function<TbBasicSysDictionary, T> fieldMapper) {
        if (ObjectUtil.isNull(parentId)) {
            return Optional.empty();
        }

        return Optional.ofNullable(getByParentId(parentId)).map(fieldMapper);
   }

    /**
     * 根据字典名称查询单条数据
     *
     * @param name 字典名称
     * @return 查询结果
     */
    public TbBasicSysDictionary getByName(String name) {
        if (StrUtil.isBlank(name)) {
            return null;
        }

        TbBasicSysDictionaryPo po = new TbBasicSysDictionaryPo();
        po.setWhereName(name);
        return get(po);
    }

    /**
     * 根据字典名称查询多条数据
     *
     * @param name 字典名称
     * @return 查询结果
     */
    public List<TbBasicSysDictionary> queryByName(String name) {
        if (StrUtil.isBlank(name)) {
            return null;
        }

        TbBasicSysDictionaryPo po = new TbBasicSysDictionaryPo();
        po.setWhereName(name);
        return query(po);
    }

    /**
     * 根据字典名称获取指定字段的值
     *
     * @param name 字典名称
     * @param fieldMapper 字段映射函数
     * @param <T> 字段类型
     * @return 字段值的 Optional
     */
    public <T> Optional<T> getFieldByName(String name, Function<TbBasicSysDictionary, T> fieldMapper) {
        if (StrUtil.isBlank(name)) {
            return Optional.empty();
        }

        return Optional.ofNullable(getByName(name)).map(fieldMapper);
   }

    /**
     * 根据字典标识查询单条数据
     *
     * @param identifying 字典标识
     * @return 查询结果
     */
    public TbBasicSysDictionary getByIdentifying(String identifying) {
        if (StrUtil.isBlank(identifying)) {
            return null;
        }

        TbBasicSysDictionaryPo po = new TbBasicSysDictionaryPo();
        po.setWhereIdentifying(identifying);
        return get(po);
    }

    /**
     * 根据字典标识查询多条数据
     *
     * @param identifying 字典标识
     * @return 查询结果
     */
    public List<TbBasicSysDictionary> queryByIdentifying(String identifying) {
        if (StrUtil.isBlank(identifying)) {
            return null;
        }

        TbBasicSysDictionaryPo po = new TbBasicSysDictionaryPo();
        po.setWhereIdentifying(identifying);
        return query(po);
    }

    /**
     * 根据字典标识获取指定字段的值
     *
     * @param identifying 字典标识
     * @param fieldMapper 字段映射函数
     * @param <T> 字段类型
     * @return 字段值的 Optional
     */
    public <T> Optional<T> getFieldByIdentifying(String identifying, Function<TbBasicSysDictionary, T> fieldMapper) {
        if (StrUtil.isBlank(identifying)) {
            return Optional.empty();
        }

        return Optional.ofNullable(getByIdentifying(identifying)).map(fieldMapper);
   }

    /**
     * 根据排序ID查询单条数据
     *
     * @param sort 排序ID
     * @return 查询结果
     */
    public TbBasicSysDictionary getBySort(Integer sort) {
        if (ObjectUtil.isNull(sort)) {
            return null;
        }

        TbBasicSysDictionaryPo po = new TbBasicSysDictionaryPo();
        po.setWhereSort(sort);
        return get(po);
    }

    /**
     * 根据排序ID查询多条数据
     *
     * @param sort 排序ID
     * @return 查询结果
     */
    public List<TbBasicSysDictionary> queryBySort(Integer sort) {
        if (ObjectUtil.isNull(sort)) {
            return null;
        }

        TbBasicSysDictionaryPo po = new TbBasicSysDictionaryPo();
        po.setWhereSort(sort);
        return query(po);
    }

    /**
     * 根据排序ID获取指定字段的值
     *
     * @param sort 排序ID
     * @param fieldMapper 字段映射函数
     * @param <T> 字段类型
     * @return 字段值的 Optional
     */
    public <T> Optional<T> getFieldBySort(Integer sort, Function<TbBasicSysDictionary, T> fieldMapper) {
        if (ObjectUtil.isNull(sort)) {
            return Optional.empty();
        }

        return Optional.ofNullable(getBySort(sort)).map(fieldMapper);
   }

    /**
     * 根据描述查询单条数据
     *
     * @param description 描述
     * @return 查询结果
     */
    public TbBasicSysDictionary getByDescription(String description) {
        if (StrUtil.isBlank(description)) {
            return null;
        }

        TbBasicSysDictionaryPo po = new TbBasicSysDictionaryPo();
        po.setWhereDescription(description);
        return get(po);
    }

    /**
     * 根据描述查询多条数据
     *
     * @param description 描述
     * @return 查询结果
     */
    public List<TbBasicSysDictionary> queryByDescription(String description) {
        if (StrUtil.isBlank(description)) {
            return null;
        }

        TbBasicSysDictionaryPo po = new TbBasicSysDictionaryPo();
        po.setWhereDescription(description);
        return query(po);
    }

    /**
     * 根据描述获取指定字段的值
     *
     * @param description 描述
     * @param fieldMapper 字段映射函数
     * @param <T> 字段类型
     * @return 字段值的 Optional
     */
    public <T> Optional<T> getFieldByDescription(String description, Function<TbBasicSysDictionary, T> fieldMapper) {
        if (StrUtil.isBlank(description)) {
            return Optional.empty();
        }

        return Optional.ofNullable(getByDescription(description)).map(fieldMapper);
   }

    /**
     * 根据状态。1：启用，2：禁用查询单条数据
     *
     * @param status 状态。1：启用，2：禁用
     * @return 查询结果
     */
    public TbBasicSysDictionary getByStatus(Integer status) {
        if (ObjectUtil.isNull(status)) {
            return null;
        }

        TbBasicSysDictionaryPo po = new TbBasicSysDictionaryPo();
        po.setWhereStatus(status);
        return get(po);
    }

    /**
     * 根据状态。1：启用，2：禁用查询多条数据
     *
     * @param status 状态。1：启用，2：禁用
     * @return 查询结果
     */
    public List<TbBasicSysDictionary> queryByStatus(Integer status) {
        if (ObjectUtil.isNull(status)) {
            return null;
        }

        TbBasicSysDictionaryPo po = new TbBasicSysDictionaryPo();
        po.setWhereStatus(status);
        return query(po);
    }

    /**
     * 根据状态。1：启用，2：禁用获取指定字段的值
     *
     * @param status 状态。1：启用，2：禁用
     * @param fieldMapper 字段映射函数
     * @param <T> 字段类型
     * @return 字段值的 Optional
     */
    public <T> Optional<T> getFieldByStatus(Integer status, Function<TbBasicSysDictionary, T> fieldMapper) {
        if (ObjectUtil.isNull(status)) {
            return Optional.empty();
        }

        return Optional.ofNullable(getByStatus(status)).map(fieldMapper);
   }

    /**
     * 根据创建时间查询单条数据
     *
     * @param createdAt 创建时间
     * @return 查询结果
     */
    public TbBasicSysDictionary getByCreatedAt(String createdAt) {
        if (StrUtil.isBlank(createdAt)) {
            return null;
        }

        TbBasicSysDictionaryPo po = new TbBasicSysDictionaryPo();
        po.setWhereCreatedAt(createdAt);
        return get(po);
    }

    /**
     * 根据创建时间查询多条数据
     *
     * @param createdAt 创建时间
     * @return 查询结果
     */
    public List<TbBasicSysDictionary> queryByCreatedAt(String createdAt) {
        if (StrUtil.isBlank(createdAt)) {
            return null;
        }

        TbBasicSysDictionaryPo po = new TbBasicSysDictionaryPo();
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
    public <T> Optional<T> getFieldByCreatedAt(String createdAt, Function<TbBasicSysDictionary, T> fieldMapper) {
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
    public TbBasicSysDictionary getByCreatedUserId(Integer createdUserId) {
        if (ObjectUtil.isNull(createdUserId)) {
            return null;
        }

        TbBasicSysDictionaryPo po = new TbBasicSysDictionaryPo();
        po.setWhereCreatedUserId(createdUserId);
        return get(po);
    }

    /**
     * 根据创建者查询多条数据
     *
     * @param createdUserId 创建者
     * @return 查询结果
     */
    public List<TbBasicSysDictionary> queryByCreatedUserId(Integer createdUserId) {
        if (ObjectUtil.isNull(createdUserId)) {
            return null;
        }

        TbBasicSysDictionaryPo po = new TbBasicSysDictionaryPo();
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
    public <T> Optional<T> getFieldByCreatedUserId(Integer createdUserId, Function<TbBasicSysDictionary, T> fieldMapper) {
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
    public TbBasicSysDictionary getByUpdatedAt(String updatedAt) {
        if (StrUtil.isBlank(updatedAt)) {
            return null;
        }

        TbBasicSysDictionaryPo po = new TbBasicSysDictionaryPo();
        po.setWhereUpdatedAt(updatedAt);
        return get(po);
    }

    /**
     * 根据更新时间查询多条数据
     *
     * @param updatedAt 更新时间
     * @return 查询结果
     */
    public List<TbBasicSysDictionary> queryByUpdatedAt(String updatedAt) {
        if (StrUtil.isBlank(updatedAt)) {
            return null;
        }

        TbBasicSysDictionaryPo po = new TbBasicSysDictionaryPo();
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
    public <T> Optional<T> getFieldByUpdatedAt(String updatedAt, Function<TbBasicSysDictionary, T> fieldMapper) {
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
    public TbBasicSysDictionary getByUpdatedUserId(Integer updatedUserId) {
        if (ObjectUtil.isNull(updatedUserId)) {
            return null;
        }

        TbBasicSysDictionaryPo po = new TbBasicSysDictionaryPo();
        po.setWhereUpdatedUserId(updatedUserId);
        return get(po);
    }

    /**
     * 根据更新者查询多条数据
     *
     * @param updatedUserId 更新者
     * @return 查询结果
     */
    public List<TbBasicSysDictionary> queryByUpdatedUserId(Integer updatedUserId) {
        if (ObjectUtil.isNull(updatedUserId)) {
            return null;
        }

        TbBasicSysDictionaryPo po = new TbBasicSysDictionaryPo();
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
    public <T> Optional<T> getFieldByUpdatedUserId(Integer updatedUserId, Function<TbBasicSysDictionary, T> fieldMapper) {
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
    public BigDecimal sumId(TbBasicSysDictionaryPo po) {
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
    public BigDecimal maxId(TbBasicSysDictionaryPo po) {
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
    public BigDecimal minId(TbBasicSysDictionaryPo po) {
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
    public BigDecimal avgId(TbBasicSysDictionaryPo po) {
        if (po == null) {
            return BigDecimal.ZERO;
        }
        return mapper.avgId(po);
    }
    /**
     * 统计父节点ID的总和
     *
     * @param po 查询条件
     * @return 总和结果
     */
    public BigDecimal sumParentId(TbBasicSysDictionaryPo po) {
        if (po == null) {
            return BigDecimal.ZERO;
        }
        return mapper.sumParentId(po);
    }

    /**
     * 统计父节点ID的最大值
     *
     * @param po 查询条件
     * @return 最大值结果
     */
    public BigDecimal maxParentId(TbBasicSysDictionaryPo po) {
        if (po == null) {
            return BigDecimal.ZERO;
        }
        return mapper.maxParentId(po);
    }

    /**
     * 统计父节点ID的最小值
     *
     * @param po 查询条件
     * @return 最小值结果
     */
    public BigDecimal minParentId(TbBasicSysDictionaryPo po) {
        if (po == null) {
            return BigDecimal.ZERO;
        }
        return mapper.minParentId(po);
    }

    /**
     * 统计父节点ID的平均值
     *
     * @param po 查询条件
     * @return 平均值结果
     */
    public BigDecimal avgParentId(TbBasicSysDictionaryPo po) {
        if (po == null) {
            return BigDecimal.ZERO;
        }
        return mapper.avgParentId(po);
    }
    /**
     * 统计排序ID的总和
     *
     * @param po 查询条件
     * @return 总和结果
     */
    public BigDecimal sumSort(TbBasicSysDictionaryPo po) {
        if (po == null) {
            return BigDecimal.ZERO;
        }
        return mapper.sumSort(po);
    }

    /**
     * 统计排序ID的最大值
     *
     * @param po 查询条件
     * @return 最大值结果
     */
    public BigDecimal maxSort(TbBasicSysDictionaryPo po) {
        if (po == null) {
            return BigDecimal.ZERO;
        }
        return mapper.maxSort(po);
    }

    /**
     * 统计排序ID的最小值
     *
     * @param po 查询条件
     * @return 最小值结果
     */
    public BigDecimal minSort(TbBasicSysDictionaryPo po) {
        if (po == null) {
            return BigDecimal.ZERO;
        }
        return mapper.minSort(po);
    }

    /**
     * 统计排序ID的平均值
     *
     * @param po 查询条件
     * @return 平均值结果
     */
    public BigDecimal avgSort(TbBasicSysDictionaryPo po) {
        if (po == null) {
            return BigDecimal.ZERO;
        }
        return mapper.avgSort(po);
    }
    /**
     * 统计状态。1：启用，2：禁用的总和
     *
     * @param po 查询条件
     * @return 总和结果
     */
    public BigDecimal sumStatus(TbBasicSysDictionaryPo po) {
        if (po == null) {
            return BigDecimal.ZERO;
        }
        return mapper.sumStatus(po);
    }

    /**
     * 统计状态。1：启用，2：禁用的最大值
     *
     * @param po 查询条件
     * @return 最大值结果
     */
    public BigDecimal maxStatus(TbBasicSysDictionaryPo po) {
        if (po == null) {
            return BigDecimal.ZERO;
        }
        return mapper.maxStatus(po);
    }

    /**
     * 统计状态。1：启用，2：禁用的最小值
     *
     * @param po 查询条件
     * @return 最小值结果
     */
    public BigDecimal minStatus(TbBasicSysDictionaryPo po) {
        if (po == null) {
            return BigDecimal.ZERO;
        }
        return mapper.minStatus(po);
    }

    /**
     * 统计状态。1：启用，2：禁用的平均值
     *
     * @param po 查询条件
     * @return 平均值结果
     */
    public BigDecimal avgStatus(TbBasicSysDictionaryPo po) {
        if (po == null) {
            return BigDecimal.ZERO;
        }
        return mapper.avgStatus(po);
    }
    /**
     * 统计创建者的总和
     *
     * @param po 查询条件
     * @return 总和结果
     */
    public BigDecimal sumCreatedUserId(TbBasicSysDictionaryPo po) {
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
    public BigDecimal maxCreatedUserId(TbBasicSysDictionaryPo po) {
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
    public BigDecimal minCreatedUserId(TbBasicSysDictionaryPo po) {
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
    public BigDecimal avgCreatedUserId(TbBasicSysDictionaryPo po) {
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
    public BigDecimal sumUpdatedUserId(TbBasicSysDictionaryPo po) {
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
    public BigDecimal maxUpdatedUserId(TbBasicSysDictionaryPo po) {
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
    public BigDecimal minUpdatedUserId(TbBasicSysDictionaryPo po) {
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
    public BigDecimal avgUpdatedUserId(TbBasicSysDictionaryPo po) {
        if (po == null) {
            return BigDecimal.ZERO;
        }
        return mapper.avgUpdatedUserId(po);
    }
    //***************************************************聚合操作 END********************************************************
}