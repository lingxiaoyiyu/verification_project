package com.api.basic.dao;

import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import com.api.basic.data.entity.TbBasicConfig;
import com.api.basic.data.po.TbBasicConfigPo;
import com.api.basic.mapper.TbBasicConfigMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.*;
import java.util.function.Function;

/**
 * 配置管理Dao
 *
 * @author 裴金伟
 */
@Component
@RequiredArgsConstructor
public class TbBasicConfigDao {

    private final TbBasicConfigMapper mapper;

    //***************************************************添加 START******************************************************
    /**
     * 新增
     *
     * @param entity 新增信息
     * @return 新增ID
     */
    public Integer add(TbBasicConfig entity) {
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
    public Integer delete(TbBasicConfigPo po) {
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
    public Integer update(TbBasicConfigPo po) {
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
    public List<TbBasicConfig> query(TbBasicConfigPo po) {
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
    public Integer cnt(TbBasicConfigPo po) {
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
    public TbBasicConfig get(TbBasicConfigPo po) {
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
    public TbBasicConfig getById(Integer id) {
        if (ObjectUtil.isNull(id)) {
            return null;
        }

        TbBasicConfigPo po = new TbBasicConfigPo();
        po.setWhereId(id);
        return get(po);
    }

    /**
     * 根据查询多条数据
     *
     * @param id 
     * @return 查询结果
     */
    public List<TbBasicConfig> queryById(Integer id) {
        if (ObjectUtil.isNull(id)) {
            return null;
        }

        TbBasicConfigPo po = new TbBasicConfigPo();
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
    public <T> Optional<T> getFieldById(Integer id, Function<TbBasicConfig, T> fieldMapper) {
        if (ObjectUtil.isNull(id)) {
            return Optional.empty();
        }

        return Optional.ofNullable(getById(id)).map(fieldMapper);
   }

    /**
     * 根据配置分组ID查询单条数据
     *
     * @param groupId 配置分组ID
     * @return 查询结果
     */
    public TbBasicConfig getByGroupId(Integer groupId) {
        if (ObjectUtil.isNull(groupId)) {
            return null;
        }

        TbBasicConfigPo po = new TbBasicConfigPo();
        po.setWhereGroupId(groupId);
        return get(po);
    }

    /**
     * 根据配置分组ID查询多条数据
     *
     * @param groupId 配置分组ID
     * @return 查询结果
     */
    public List<TbBasicConfig> queryByGroupId(Integer groupId) {
        if (ObjectUtil.isNull(groupId)) {
            return null;
        }

        TbBasicConfigPo po = new TbBasicConfigPo();
        po.setWhereGroupId(groupId);
        return query(po);
    }

    /**
     * 根据配置分组ID获取指定字段的值
     *
     * @param groupId 配置分组ID
     * @param fieldMapper 字段映射函数
     * @param <T> 字段类型
     * @return 字段值的 Optional
     */
    public <T> Optional<T> getFieldByGroupId(Integer groupId, Function<TbBasicConfig, T> fieldMapper) {
        if (ObjectUtil.isNull(groupId)) {
            return Optional.empty();
        }

        return Optional.ofNullable(getByGroupId(groupId)).map(fieldMapper);
   }

    /**
     * 根据配置项标题查询单条数据
     *
     * @param title 配置项标题
     * @return 查询结果
     */
    public TbBasicConfig getByTitle(String title) {
        if (StrUtil.isBlank(title)) {
            return null;
        }

        TbBasicConfigPo po = new TbBasicConfigPo();
        po.setWhereTitle(title);
        return get(po);
    }

    /**
     * 根据配置项标题查询多条数据
     *
     * @param title 配置项标题
     * @return 查询结果
     */
    public List<TbBasicConfig> queryByTitle(String title) {
        if (StrUtil.isBlank(title)) {
            return null;
        }

        TbBasicConfigPo po = new TbBasicConfigPo();
        po.setWhereTitle(title);
        return query(po);
    }

    /**
     * 根据配置项标题获取指定字段的值
     *
     * @param title 配置项标题
     * @param fieldMapper 字段映射函数
     * @param <T> 字段类型
     * @return 字段值的 Optional
     */
    public <T> Optional<T> getFieldByTitle(String title, Function<TbBasicConfig, T> fieldMapper) {
        if (StrUtil.isBlank(title)) {
            return Optional.empty();
        }

        return Optional.ofNullable(getByTitle(title)).map(fieldMapper);
   }

    /**
     * 根据配置项名称，即key查询单条数据
     *
     * @param name 配置项名称，即key
     * @return 查询结果
     */
    public TbBasicConfig getByName(String name) {
        if (StrUtil.isBlank(name)) {
            return null;
        }

        TbBasicConfigPo po = new TbBasicConfigPo();
        po.setWhereName(name);
        return get(po);
    }

    /**
     * 根据配置项名称，即key查询多条数据
     *
     * @param name 配置项名称，即key
     * @return 查询结果
     */
    public List<TbBasicConfig> queryByName(String name) {
        if (StrUtil.isBlank(name)) {
            return null;
        }

        TbBasicConfigPo po = new TbBasicConfigPo();
        po.setWhereName(name);
        return query(po);
    }

    /**
     * 根据配置项名称，即key获取指定字段的值
     *
     * @param name 配置项名称，即key
     * @param fieldMapper 字段映射函数
     * @param <T> 字段类型
     * @return 字段值的 Optional
     */
    public <T> Optional<T> getFieldByName(String name, Function<TbBasicConfig, T> fieldMapper) {
        if (StrUtil.isBlank(name)) {
            return Optional.empty();
        }

        return Optional.ofNullable(getByName(name)).map(fieldMapper);
   }

    /**
     * 根据配置项value查询单条数据
     *
     * @param value 配置项value
     * @return 查询结果
     */
    public TbBasicConfig getByValue(String value) {
        if (StrUtil.isBlank(value)) {
            return null;
        }

        TbBasicConfigPo po = new TbBasicConfigPo();
        po.setWhereValue(value);
        return get(po);
    }

    /**
     * 根据配置项value查询多条数据
     *
     * @param value 配置项value
     * @return 查询结果
     */
    public List<TbBasicConfig> queryByValue(String value) {
        if (StrUtil.isBlank(value)) {
            return null;
        }

        TbBasicConfigPo po = new TbBasicConfigPo();
        po.setWhereValue(value);
        return query(po);
    }

    /**
     * 根据配置项value获取指定字段的值
     *
     * @param value 配置项value
     * @param fieldMapper 字段映射函数
     * @param <T> 字段类型
     * @return 字段值的 Optional
     */
    public <T> Optional<T> getFieldByValue(String value, Function<TbBasicConfig, T> fieldMapper) {
        if (StrUtil.isBlank(value)) {
            return Optional.empty();
        }

        return Optional.ofNullable(getByValue(value)).map(fieldMapper);
   }

    /**
     * 根据配置项描述查询单条数据
     *
     * @param description 配置项描述
     * @return 查询结果
     */
    public TbBasicConfig getByDescription(String description) {
        if (StrUtil.isBlank(description)) {
            return null;
        }

        TbBasicConfigPo po = new TbBasicConfigPo();
        po.setWhereDescription(description);
        return get(po);
    }

    /**
     * 根据配置项描述查询多条数据
     *
     * @param description 配置项描述
     * @return 查询结果
     */
    public List<TbBasicConfig> queryByDescription(String description) {
        if (StrUtil.isBlank(description)) {
            return null;
        }

        TbBasicConfigPo po = new TbBasicConfigPo();
        po.setWhereDescription(description);
        return query(po);
    }

    /**
     * 根据配置项描述获取指定字段的值
     *
     * @param description 配置项描述
     * @param fieldMapper 字段映射函数
     * @param <T> 字段类型
     * @return 字段值的 Optional
     */
    public <T> Optional<T> getFieldByDescription(String description, Function<TbBasicConfig, T> fieldMapper) {
        if (StrUtil.isBlank(description)) {
            return Optional.empty();
        }

        return Optional.ofNullable(getByDescription(description)).map(fieldMapper);
   }

    /**
     * 根据数据类型。1：字符串，2：数字，3：json，4：图片，5：富文本。查询单条数据
     *
     * @param dataType 数据类型。1：字符串，2：数字，3：json，4：图片，5：富文本。
     * @return 查询结果
     */
    public TbBasicConfig getByDataType(Integer dataType) {
        if (ObjectUtil.isNull(dataType)) {
            return null;
        }

        TbBasicConfigPo po = new TbBasicConfigPo();
        po.setWhereDataType(dataType);
        return get(po);
    }

    /**
     * 根据数据类型。1：字符串，2：数字，3：json，4：图片，5：富文本。查询多条数据
     *
     * @param dataType 数据类型。1：字符串，2：数字，3：json，4：图片，5：富文本。
     * @return 查询结果
     */
    public List<TbBasicConfig> queryByDataType(Integer dataType) {
        if (ObjectUtil.isNull(dataType)) {
            return null;
        }

        TbBasicConfigPo po = new TbBasicConfigPo();
        po.setWhereDataType(dataType);
        return query(po);
    }

    /**
     * 根据数据类型。1：字符串，2：数字，3：json，4：图片，5：富文本。获取指定字段的值
     *
     * @param dataType 数据类型。1：字符串，2：数字，3：json，4：图片，5：富文本。
     * @param fieldMapper 字段映射函数
     * @param <T> 字段类型
     * @return 字段值的 Optional
     */
    public <T> Optional<T> getFieldByDataType(Integer dataType, Function<TbBasicConfig, T> fieldMapper) {
        if (ObjectUtil.isNull(dataType)) {
            return Optional.empty();
        }

        return Optional.ofNullable(getByDataType(dataType)).map(fieldMapper);
   }

    /**
     * 根据状态。1：启用，2：禁用查询单条数据
     *
     * @param status 状态。1：启用，2：禁用
     * @return 查询结果
     */
    public TbBasicConfig getByStatus(Integer status) {
        if (ObjectUtil.isNull(status)) {
            return null;
        }

        TbBasicConfigPo po = new TbBasicConfigPo();
        po.setWhereStatus(status);
        return get(po);
    }

    /**
     * 根据状态。1：启用，2：禁用查询多条数据
     *
     * @param status 状态。1：启用，2：禁用
     * @return 查询结果
     */
    public List<TbBasicConfig> queryByStatus(Integer status) {
        if (ObjectUtil.isNull(status)) {
            return null;
        }

        TbBasicConfigPo po = new TbBasicConfigPo();
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
    public <T> Optional<T> getFieldByStatus(Integer status, Function<TbBasicConfig, T> fieldMapper) {
        if (ObjectUtil.isNull(status)) {
            return Optional.empty();
        }

        return Optional.ofNullable(getByStatus(status)).map(fieldMapper);
   }

    /**
     * 根据排序值查询单条数据
     *
     * @param sort 排序值
     * @return 查询结果
     */
    public TbBasicConfig getBySort(Integer sort) {
        if (ObjectUtil.isNull(sort)) {
            return null;
        }

        TbBasicConfigPo po = new TbBasicConfigPo();
        po.setWhereSort(sort);
        return get(po);
    }

    /**
     * 根据排序值查询多条数据
     *
     * @param sort 排序值
     * @return 查询结果
     */
    public List<TbBasicConfig> queryBySort(Integer sort) {
        if (ObjectUtil.isNull(sort)) {
            return null;
        }

        TbBasicConfigPo po = new TbBasicConfigPo();
        po.setWhereSort(sort);
        return query(po);
    }

    /**
     * 根据排序值获取指定字段的值
     *
     * @param sort 排序值
     * @param fieldMapper 字段映射函数
     * @param <T> 字段类型
     * @return 字段值的 Optional
     */
    public <T> Optional<T> getFieldBySort(Integer sort, Function<TbBasicConfig, T> fieldMapper) {
        if (ObjectUtil.isNull(sort)) {
            return Optional.empty();
        }

        return Optional.ofNullable(getBySort(sort)).map(fieldMapper);
   }

    /**
     * 根据日期时间选择器类型查询单条数据
     *
     * @param picker 日期时间选择器类型
     * @return 查询结果
     */
    public TbBasicConfig getByPicker(String picker) {
        if (StrUtil.isBlank(picker)) {
            return null;
        }

        TbBasicConfigPo po = new TbBasicConfigPo();
        po.setWherePicker(picker);
        return get(po);
    }

    /**
     * 根据日期时间选择器类型查询多条数据
     *
     * @param picker 日期时间选择器类型
     * @return 查询结果
     */
    public List<TbBasicConfig> queryByPicker(String picker) {
        if (StrUtil.isBlank(picker)) {
            return null;
        }

        TbBasicConfigPo po = new TbBasicConfigPo();
        po.setWherePicker(picker);
        return query(po);
    }

    /**
     * 根据日期时间选择器类型获取指定字段的值
     *
     * @param picker 日期时间选择器类型
     * @param fieldMapper 字段映射函数
     * @param <T> 字段类型
     * @return 字段值的 Optional
     */
    public <T> Optional<T> getFieldByPicker(String picker, Function<TbBasicConfig, T> fieldMapper) {
        if (StrUtil.isBlank(picker)) {
            return Optional.empty();
        }

        return Optional.ofNullable(getByPicker(picker)).map(fieldMapper);
   }

    /**
     * 根据组件数据查询单条数据
     *
     * @param componentData 组件数据
     * @return 查询结果
     */
    public TbBasicConfig getByComponentData(String componentData) {
        if (StrUtil.isBlank(componentData)) {
            return null;
        }

        TbBasicConfigPo po = new TbBasicConfigPo();
        po.setWhereComponentData(componentData);
        return get(po);
    }

    /**
     * 根据组件数据查询多条数据
     *
     * @param componentData 组件数据
     * @return 查询结果
     */
    public List<TbBasicConfig> queryByComponentData(String componentData) {
        if (StrUtil.isBlank(componentData)) {
            return null;
        }

        TbBasicConfigPo po = new TbBasicConfigPo();
        po.setWhereComponentData(componentData);
        return query(po);
    }

    /**
     * 根据组件数据获取指定字段的值
     *
     * @param componentData 组件数据
     * @param fieldMapper 字段映射函数
     * @param <T> 字段类型
     * @return 字段值的 Optional
     */
    public <T> Optional<T> getFieldByComponentData(String componentData, Function<TbBasicConfig, T> fieldMapper) {
        if (StrUtil.isBlank(componentData)) {
            return Optional.empty();
        }

        return Optional.ofNullable(getByComponentData(componentData)).map(fieldMapper);
   }

    /**
     * 根据创建时间查询单条数据
     *
     * @param createdAt 创建时间
     * @return 查询结果
     */
    public TbBasicConfig getByCreatedAt(String createdAt) {
        if (StrUtil.isBlank(createdAt)) {
            return null;
        }

        TbBasicConfigPo po = new TbBasicConfigPo();
        po.setWhereCreatedAt(createdAt);
        return get(po);
    }

    /**
     * 根据创建时间查询多条数据
     *
     * @param createdAt 创建时间
     * @return 查询结果
     */
    public List<TbBasicConfig> queryByCreatedAt(String createdAt) {
        if (StrUtil.isBlank(createdAt)) {
            return null;
        }

        TbBasicConfigPo po = new TbBasicConfigPo();
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
    public <T> Optional<T> getFieldByCreatedAt(String createdAt, Function<TbBasicConfig, T> fieldMapper) {
        if (StrUtil.isBlank(createdAt)) {
            return Optional.empty();
        }

        return Optional.ofNullable(getByCreatedAt(createdAt)).map(fieldMapper);
   }

    /**
     * 根据创建用户查询单条数据
     *
     * @param createdUserId 创建用户
     * @return 查询结果
     */
    public TbBasicConfig getByCreatedUserId(Integer createdUserId) {
        if (ObjectUtil.isNull(createdUserId)) {
            return null;
        }

        TbBasicConfigPo po = new TbBasicConfigPo();
        po.setWhereCreatedUserId(createdUserId);
        return get(po);
    }

    /**
     * 根据创建用户查询多条数据
     *
     * @param createdUserId 创建用户
     * @return 查询结果
     */
    public List<TbBasicConfig> queryByCreatedUserId(Integer createdUserId) {
        if (ObjectUtil.isNull(createdUserId)) {
            return null;
        }

        TbBasicConfigPo po = new TbBasicConfigPo();
        po.setWhereCreatedUserId(createdUserId);
        return query(po);
    }

    /**
     * 根据创建用户获取指定字段的值
     *
     * @param createdUserId 创建用户
     * @param fieldMapper 字段映射函数
     * @param <T> 字段类型
     * @return 字段值的 Optional
     */
    public <T> Optional<T> getFieldByCreatedUserId(Integer createdUserId, Function<TbBasicConfig, T> fieldMapper) {
        if (ObjectUtil.isNull(createdUserId)) {
            return Optional.empty();
        }

        return Optional.ofNullable(getByCreatedUserId(createdUserId)).map(fieldMapper);
   }

    /**
     * 根据最后更新时间查询单条数据
     *
     * @param updatedAt 最后更新时间
     * @return 查询结果
     */
    public TbBasicConfig getByUpdatedAt(String updatedAt) {
        if (StrUtil.isBlank(updatedAt)) {
            return null;
        }

        TbBasicConfigPo po = new TbBasicConfigPo();
        po.setWhereUpdatedAt(updatedAt);
        return get(po);
    }

    /**
     * 根据最后更新时间查询多条数据
     *
     * @param updatedAt 最后更新时间
     * @return 查询结果
     */
    public List<TbBasicConfig> queryByUpdatedAt(String updatedAt) {
        if (StrUtil.isBlank(updatedAt)) {
            return null;
        }

        TbBasicConfigPo po = new TbBasicConfigPo();
        po.setWhereUpdatedAt(updatedAt);
        return query(po);
    }

    /**
     * 根据最后更新时间获取指定字段的值
     *
     * @param updatedAt 最后更新时间
     * @param fieldMapper 字段映射函数
     * @param <T> 字段类型
     * @return 字段值的 Optional
     */
    public <T> Optional<T> getFieldByUpdatedAt(String updatedAt, Function<TbBasicConfig, T> fieldMapper) {
        if (StrUtil.isBlank(updatedAt)) {
            return Optional.empty();
        }

        return Optional.ofNullable(getByUpdatedAt(updatedAt)).map(fieldMapper);
   }

    /**
     * 根据更新用户查询单条数据
     *
     * @param updatedUserId 更新用户
     * @return 查询结果
     */
    public TbBasicConfig getByUpdatedUserId(Integer updatedUserId) {
        if (ObjectUtil.isNull(updatedUserId)) {
            return null;
        }

        TbBasicConfigPo po = new TbBasicConfigPo();
        po.setWhereUpdatedUserId(updatedUserId);
        return get(po);
    }

    /**
     * 根据更新用户查询多条数据
     *
     * @param updatedUserId 更新用户
     * @return 查询结果
     */
    public List<TbBasicConfig> queryByUpdatedUserId(Integer updatedUserId) {
        if (ObjectUtil.isNull(updatedUserId)) {
            return null;
        }

        TbBasicConfigPo po = new TbBasicConfigPo();
        po.setWhereUpdatedUserId(updatedUserId);
        return query(po);
    }

    /**
     * 根据更新用户获取指定字段的值
     *
     * @param updatedUserId 更新用户
     * @param fieldMapper 字段映射函数
     * @param <T> 字段类型
     * @return 字段值的 Optional
     */
    public <T> Optional<T> getFieldByUpdatedUserId(Integer updatedUserId, Function<TbBasicConfig, T> fieldMapper) {
        if (ObjectUtil.isNull(updatedUserId)) {
            return Optional.empty();
        }

        return Optional.ofNullable(getByUpdatedUserId(updatedUserId)).map(fieldMapper);
   }

    //***************************************************获取 END********************************************************

    //***************************************************聚合操作 START******************************************************
    /**
     * 统计的总和
     *
     * @param po 查询条件
     * @return 总和结果
     */
    public BigDecimal sumId(TbBasicConfigPo po) {
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
    public BigDecimal maxId(TbBasicConfigPo po) {
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
    public BigDecimal minId(TbBasicConfigPo po) {
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
    public BigDecimal avgId(TbBasicConfigPo po) {
        if (po == null) {
            return BigDecimal.ZERO;
        }
        return mapper.avgId(po);
    }
    /**
     * 统计配置分组ID的总和
     *
     * @param po 查询条件
     * @return 总和结果
     */
    public BigDecimal sumGroupId(TbBasicConfigPo po) {
        if (po == null) {
            return BigDecimal.ZERO;
        }
        return mapper.sumGroupId(po);
    }

    /**
     * 统计配置分组ID的最大值
     *
     * @param po 查询条件
     * @return 最大值结果
     */
    public BigDecimal maxGroupId(TbBasicConfigPo po) {
        if (po == null) {
            return BigDecimal.ZERO;
        }
        return mapper.maxGroupId(po);
    }

    /**
     * 统计配置分组ID的最小值
     *
     * @param po 查询条件
     * @return 最小值结果
     */
    public BigDecimal minGroupId(TbBasicConfigPo po) {
        if (po == null) {
            return BigDecimal.ZERO;
        }
        return mapper.minGroupId(po);
    }

    /**
     * 统计配置分组ID的平均值
     *
     * @param po 查询条件
     * @return 平均值结果
     */
    public BigDecimal avgGroupId(TbBasicConfigPo po) {
        if (po == null) {
            return BigDecimal.ZERO;
        }
        return mapper.avgGroupId(po);
    }
    /**
     * 统计数据类型。1：字符串，2：数字，3：json，4：图片，5：富文本。的总和
     *
     * @param po 查询条件
     * @return 总和结果
     */
    public BigDecimal sumDataType(TbBasicConfigPo po) {
        if (po == null) {
            return BigDecimal.ZERO;
        }
        return mapper.sumDataType(po);
    }

    /**
     * 统计数据类型。1：字符串，2：数字，3：json，4：图片，5：富文本。的最大值
     *
     * @param po 查询条件
     * @return 最大值结果
     */
    public BigDecimal maxDataType(TbBasicConfigPo po) {
        if (po == null) {
            return BigDecimal.ZERO;
        }
        return mapper.maxDataType(po);
    }

    /**
     * 统计数据类型。1：字符串，2：数字，3：json，4：图片，5：富文本。的最小值
     *
     * @param po 查询条件
     * @return 最小值结果
     */
    public BigDecimal minDataType(TbBasicConfigPo po) {
        if (po == null) {
            return BigDecimal.ZERO;
        }
        return mapper.minDataType(po);
    }

    /**
     * 统计数据类型。1：字符串，2：数字，3：json，4：图片，5：富文本。的平均值
     *
     * @param po 查询条件
     * @return 平均值结果
     */
    public BigDecimal avgDataType(TbBasicConfigPo po) {
        if (po == null) {
            return BigDecimal.ZERO;
        }
        return mapper.avgDataType(po);
    }
    /**
     * 统计状态。1：启用，2：禁用的总和
     *
     * @param po 查询条件
     * @return 总和结果
     */
    public BigDecimal sumStatus(TbBasicConfigPo po) {
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
    public BigDecimal maxStatus(TbBasicConfigPo po) {
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
    public BigDecimal minStatus(TbBasicConfigPo po) {
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
    public BigDecimal avgStatus(TbBasicConfigPo po) {
        if (po == null) {
            return BigDecimal.ZERO;
        }
        return mapper.avgStatus(po);
    }
    /**
     * 统计排序值的总和
     *
     * @param po 查询条件
     * @return 总和结果
     */
    public BigDecimal sumSort(TbBasicConfigPo po) {
        if (po == null) {
            return BigDecimal.ZERO;
        }
        return mapper.sumSort(po);
    }

    /**
     * 统计排序值的最大值
     *
     * @param po 查询条件
     * @return 最大值结果
     */
    public BigDecimal maxSort(TbBasicConfigPo po) {
        if (po == null) {
            return BigDecimal.ZERO;
        }
        return mapper.maxSort(po);
    }

    /**
     * 统计排序值的最小值
     *
     * @param po 查询条件
     * @return 最小值结果
     */
    public BigDecimal minSort(TbBasicConfigPo po) {
        if (po == null) {
            return BigDecimal.ZERO;
        }
        return mapper.minSort(po);
    }

    /**
     * 统计排序值的平均值
     *
     * @param po 查询条件
     * @return 平均值结果
     */
    public BigDecimal avgSort(TbBasicConfigPo po) {
        if (po == null) {
            return BigDecimal.ZERO;
        }
        return mapper.avgSort(po);
    }
    /**
     * 统计创建用户的总和
     *
     * @param po 查询条件
     * @return 总和结果
     */
    public BigDecimal sumCreatedUserId(TbBasicConfigPo po) {
        if (po == null) {
            return BigDecimal.ZERO;
        }
        return mapper.sumCreatedUserId(po);
    }

    /**
     * 统计创建用户的最大值
     *
     * @param po 查询条件
     * @return 最大值结果
     */
    public BigDecimal maxCreatedUserId(TbBasicConfigPo po) {
        if (po == null) {
            return BigDecimal.ZERO;
        }
        return mapper.maxCreatedUserId(po);
    }

    /**
     * 统计创建用户的最小值
     *
     * @param po 查询条件
     * @return 最小值结果
     */
    public BigDecimal minCreatedUserId(TbBasicConfigPo po) {
        if (po == null) {
            return BigDecimal.ZERO;
        }
        return mapper.minCreatedUserId(po);
    }

    /**
     * 统计创建用户的平均值
     *
     * @param po 查询条件
     * @return 平均值结果
     */
    public BigDecimal avgCreatedUserId(TbBasicConfigPo po) {
        if (po == null) {
            return BigDecimal.ZERO;
        }
        return mapper.avgCreatedUserId(po);
    }
    /**
     * 统计更新用户的总和
     *
     * @param po 查询条件
     * @return 总和结果
     */
    public BigDecimal sumUpdatedUserId(TbBasicConfigPo po) {
        if (po == null) {
            return BigDecimal.ZERO;
        }
        return mapper.sumUpdatedUserId(po);
    }

    /**
     * 统计更新用户的最大值
     *
     * @param po 查询条件
     * @return 最大值结果
     */
    public BigDecimal maxUpdatedUserId(TbBasicConfigPo po) {
        if (po == null) {
            return BigDecimal.ZERO;
        }
        return mapper.maxUpdatedUserId(po);
    }

    /**
     * 统计更新用户的最小值
     *
     * @param po 查询条件
     * @return 最小值结果
     */
    public BigDecimal minUpdatedUserId(TbBasicConfigPo po) {
        if (po == null) {
            return BigDecimal.ZERO;
        }
        return mapper.minUpdatedUserId(po);
    }

    /**
     * 统计更新用户的平均值
     *
     * @param po 查询条件
     * @return 平均值结果
     */
    public BigDecimal avgUpdatedUserId(TbBasicConfigPo po) {
        if (po == null) {
            return BigDecimal.ZERO;
        }
        return mapper.avgUpdatedUserId(po);
    }
    //***************************************************聚合操作 END********************************************************
}