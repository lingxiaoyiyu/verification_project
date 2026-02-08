package com.api.basic.dao;

import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import com.api.basic.data.entity.TbBasicSysDictionaryItem;
import com.api.basic.data.po.TbBasicSysDictionaryItemPo;
import com.api.basic.mapper.TbBasicSysDictionaryItemMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.*;
import java.util.function.Function;

/**
 * 字典项信息表Dao
 *
 * @author 裴金伟
 */
@Component
@RequiredArgsConstructor
public class TbBasicSysDictionaryItemDao {

    private final TbBasicSysDictionaryItemMapper mapper;

    //***************************************************添加 START******************************************************
    /**
     * 新增
     *
     * @param entity 新增信息
     * @return 新增ID
     */
    public Integer add(TbBasicSysDictionaryItem entity) {
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
    public Integer delete(TbBasicSysDictionaryItemPo po) {
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
    public Integer update(TbBasicSysDictionaryItemPo po) {
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
    public List<TbBasicSysDictionaryItem> query(TbBasicSysDictionaryItemPo po) {
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
    public Integer cnt(TbBasicSysDictionaryItemPo po) {
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
    public TbBasicSysDictionaryItem get(TbBasicSysDictionaryItemPo po) {
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
    public TbBasicSysDictionaryItem getById(Integer id) {
        if (ObjectUtil.isNull(id)) {
            return null;
        }

        TbBasicSysDictionaryItemPo po = new TbBasicSysDictionaryItemPo();
        po.setWhereId(id);
        return get(po);
    }

    /**
     * 根据ID查询多条数据
     *
     * @param id ID
     * @return 查询结果
     */
    public List<TbBasicSysDictionaryItem> queryById(Integer id) {
        if (ObjectUtil.isNull(id)) {
            return null;
        }

        TbBasicSysDictionaryItemPo po = new TbBasicSysDictionaryItemPo();
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
    public <T> Optional<T> getFieldById(Integer id, Function<TbBasicSysDictionaryItem, T> fieldMapper) {
        if (ObjectUtil.isNull(id)) {
            return Optional.empty();
        }

        return Optional.ofNullable(getById(id)).map(fieldMapper);
   }

    /**
     * 根据字典标识查询单条数据
     *
     * @param dicIdentifying 字典标识
     * @return 查询结果
     */
    public TbBasicSysDictionaryItem getByDicIdentifying(String dicIdentifying) {
        if (StrUtil.isBlank(dicIdentifying)) {
            return null;
        }

        TbBasicSysDictionaryItemPo po = new TbBasicSysDictionaryItemPo();
        po.setWhereDicIdentifying(dicIdentifying);
        return get(po);
    }

    /**
     * 根据字典标识查询多条数据
     *
     * @param dicIdentifying 字典标识
     * @return 查询结果
     */
    public List<TbBasicSysDictionaryItem> queryByDicIdentifying(String dicIdentifying) {
        if (StrUtil.isBlank(dicIdentifying)) {
            return null;
        }

        TbBasicSysDictionaryItemPo po = new TbBasicSysDictionaryItemPo();
        po.setWhereDicIdentifying(dicIdentifying);
        return query(po);
    }

    /**
     * 根据字典标识获取指定字段的值
     *
     * @param dicIdentifying 字典标识
     * @param fieldMapper 字段映射函数
     * @param <T> 字段类型
     * @return 字段值的 Optional
     */
    public <T> Optional<T> getFieldByDicIdentifying(String dicIdentifying, Function<TbBasicSysDictionaryItem, T> fieldMapper) {
        if (StrUtil.isBlank(dicIdentifying)) {
            return Optional.empty();
        }

        return Optional.ofNullable(getByDicIdentifying(dicIdentifying)).map(fieldMapper);
   }

    /**
     * 根据字典项名称查询单条数据
     *
     * @param name 字典项名称
     * @return 查询结果
     */
    public TbBasicSysDictionaryItem getByName(String name) {
        if (StrUtil.isBlank(name)) {
            return null;
        }

        TbBasicSysDictionaryItemPo po = new TbBasicSysDictionaryItemPo();
        po.setWhereName(name);
        return get(po);
    }

    /**
     * 根据字典项名称查询多条数据
     *
     * @param name 字典项名称
     * @return 查询结果
     */
    public List<TbBasicSysDictionaryItem> queryByName(String name) {
        if (StrUtil.isBlank(name)) {
            return null;
        }

        TbBasicSysDictionaryItemPo po = new TbBasicSysDictionaryItemPo();
        po.setWhereName(name);
        return query(po);
    }

    /**
     * 根据字典项名称获取指定字段的值
     *
     * @param name 字典项名称
     * @param fieldMapper 字段映射函数
     * @param <T> 字段类型
     * @return 字段值的 Optional
     */
    public <T> Optional<T> getFieldByName(String name, Function<TbBasicSysDictionaryItem, T> fieldMapper) {
        if (StrUtil.isBlank(name)) {
            return Optional.empty();
        }

        return Optional.ofNullable(getByName(name)).map(fieldMapper);
   }

    /**
     * 根据字典项值查询单条数据
     *
     * @param value 字典项值
     * @return 查询结果
     */
    public TbBasicSysDictionaryItem getByValue(String value) {
        if (StrUtil.isBlank(value)) {
            return null;
        }

        TbBasicSysDictionaryItemPo po = new TbBasicSysDictionaryItemPo();
        po.setWhereValue(value);
        return get(po);
    }

    /**
     * 根据字典项值查询多条数据
     *
     * @param value 字典项值
     * @return 查询结果
     */
    public List<TbBasicSysDictionaryItem> queryByValue(String value) {
        if (StrUtil.isBlank(value)) {
            return null;
        }

        TbBasicSysDictionaryItemPo po = new TbBasicSysDictionaryItemPo();
        po.setWhereValue(value);
        return query(po);
    }

    /**
     * 根据字典项值获取指定字段的值
     *
     * @param value 字典项值
     * @param fieldMapper 字段映射函数
     * @param <T> 字段类型
     * @return 字段值的 Optional
     */
    public <T> Optional<T> getFieldByValue(String value, Function<TbBasicSysDictionaryItem, T> fieldMapper) {
        if (StrUtil.isBlank(value)) {
            return Optional.empty();
        }

        return Optional.ofNullable(getByValue(value)).map(fieldMapper);
   }

    /**
     * 根据字典项label查询单条数据
     *
     * @param label 字典项label
     * @return 查询结果
     */
    public TbBasicSysDictionaryItem getByLabel(String label) {
        if (StrUtil.isBlank(label)) {
            return null;
        }

        TbBasicSysDictionaryItemPo po = new TbBasicSysDictionaryItemPo();
        po.setWhereLabel(label);
        return get(po);
    }

    /**
     * 根据字典项label查询多条数据
     *
     * @param label 字典项label
     * @return 查询结果
     */
    public List<TbBasicSysDictionaryItem> queryByLabel(String label) {
        if (StrUtil.isBlank(label)) {
            return null;
        }

        TbBasicSysDictionaryItemPo po = new TbBasicSysDictionaryItemPo();
        po.setWhereLabel(label);
        return query(po);
    }

    /**
     * 根据字典项label获取指定字段的值
     *
     * @param label 字典项label
     * @param fieldMapper 字段映射函数
     * @param <T> 字段类型
     * @return 字段值的 Optional
     */
    public <T> Optional<T> getFieldByLabel(String label, Function<TbBasicSysDictionaryItem, T> fieldMapper) {
        if (StrUtil.isBlank(label)) {
            return Optional.empty();
        }

        return Optional.ofNullable(getByLabel(label)).map(fieldMapper);
   }

    /**
     * 根据字典项标识查询单条数据
     *
     * @param identifying 字典项标识
     * @return 查询结果
     */
    public TbBasicSysDictionaryItem getByIdentifying(String identifying) {
        if (StrUtil.isBlank(identifying)) {
            return null;
        }

        TbBasicSysDictionaryItemPo po = new TbBasicSysDictionaryItemPo();
        po.setWhereIdentifying(identifying);
        return get(po);
    }

    /**
     * 根据字典项标识查询多条数据
     *
     * @param identifying 字典项标识
     * @return 查询结果
     */
    public List<TbBasicSysDictionaryItem> queryByIdentifying(String identifying) {
        if (StrUtil.isBlank(identifying)) {
            return null;
        }

        TbBasicSysDictionaryItemPo po = new TbBasicSysDictionaryItemPo();
        po.setWhereIdentifying(identifying);
        return query(po);
    }

    /**
     * 根据字典项标识获取指定字段的值
     *
     * @param identifying 字典项标识
     * @param fieldMapper 字段映射函数
     * @param <T> 字段类型
     * @return 字段值的 Optional
     */
    public <T> Optional<T> getFieldByIdentifying(String identifying, Function<TbBasicSysDictionaryItem, T> fieldMapper) {
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
    public TbBasicSysDictionaryItem getBySort(Integer sort) {
        if (ObjectUtil.isNull(sort)) {
            return null;
        }

        TbBasicSysDictionaryItemPo po = new TbBasicSysDictionaryItemPo();
        po.setWhereSort(sort);
        return get(po);
    }

    /**
     * 根据排序ID查询多条数据
     *
     * @param sort 排序ID
     * @return 查询结果
     */
    public List<TbBasicSysDictionaryItem> queryBySort(Integer sort) {
        if (ObjectUtil.isNull(sort)) {
            return null;
        }

        TbBasicSysDictionaryItemPo po = new TbBasicSysDictionaryItemPo();
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
    public <T> Optional<T> getFieldBySort(Integer sort, Function<TbBasicSysDictionaryItem, T> fieldMapper) {
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
    public TbBasicSysDictionaryItem getByDescription(String description) {
        if (StrUtil.isBlank(description)) {
            return null;
        }

        TbBasicSysDictionaryItemPo po = new TbBasicSysDictionaryItemPo();
        po.setWhereDescription(description);
        return get(po);
    }

    /**
     * 根据描述查询多条数据
     *
     * @param description 描述
     * @return 查询结果
     */
    public List<TbBasicSysDictionaryItem> queryByDescription(String description) {
        if (StrUtil.isBlank(description)) {
            return null;
        }

        TbBasicSysDictionaryItemPo po = new TbBasicSysDictionaryItemPo();
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
    public <T> Optional<T> getFieldByDescription(String description, Function<TbBasicSysDictionaryItem, T> fieldMapper) {
        if (StrUtil.isBlank(description)) {
            return Optional.empty();
        }

        return Optional.ofNullable(getByDescription(description)).map(fieldMapper);
   }

    /**
     * 根据背景色查询单条数据
     *
     * @param backgroundColor 背景色
     * @return 查询结果
     */
    public TbBasicSysDictionaryItem getByBackgroundColor(String backgroundColor) {
        if (StrUtil.isBlank(backgroundColor)) {
            return null;
        }

        TbBasicSysDictionaryItemPo po = new TbBasicSysDictionaryItemPo();
        po.setWhereBackgroundColor(backgroundColor);
        return get(po);
    }

    /**
     * 根据背景色查询多条数据
     *
     * @param backgroundColor 背景色
     * @return 查询结果
     */
    public List<TbBasicSysDictionaryItem> queryByBackgroundColor(String backgroundColor) {
        if (StrUtil.isBlank(backgroundColor)) {
            return null;
        }

        TbBasicSysDictionaryItemPo po = new TbBasicSysDictionaryItemPo();
        po.setWhereBackgroundColor(backgroundColor);
        return query(po);
    }

    /**
     * 根据背景色获取指定字段的值
     *
     * @param backgroundColor 背景色
     * @param fieldMapper 字段映射函数
     * @param <T> 字段类型
     * @return 字段值的 Optional
     */
    public <T> Optional<T> getFieldByBackgroundColor(String backgroundColor, Function<TbBasicSysDictionaryItem, T> fieldMapper) {
        if (StrUtil.isBlank(backgroundColor)) {
            return Optional.empty();
        }

        return Optional.ofNullable(getByBackgroundColor(backgroundColor)).map(fieldMapper);
   }

    /**
     * 根据字体颜色查询单条数据
     *
     * @param color 字体颜色
     * @return 查询结果
     */
    public TbBasicSysDictionaryItem getByColor(String color) {
        if (StrUtil.isBlank(color)) {
            return null;
        }

        TbBasicSysDictionaryItemPo po = new TbBasicSysDictionaryItemPo();
        po.setWhereColor(color);
        return get(po);
    }

    /**
     * 根据字体颜色查询多条数据
     *
     * @param color 字体颜色
     * @return 查询结果
     */
    public List<TbBasicSysDictionaryItem> queryByColor(String color) {
        if (StrUtil.isBlank(color)) {
            return null;
        }

        TbBasicSysDictionaryItemPo po = new TbBasicSysDictionaryItemPo();
        po.setWhereColor(color);
        return query(po);
    }

    /**
     * 根据字体颜色获取指定字段的值
     *
     * @param color 字体颜色
     * @param fieldMapper 字段映射函数
     * @param <T> 字段类型
     * @return 字段值的 Optional
     */
    public <T> Optional<T> getFieldByColor(String color, Function<TbBasicSysDictionaryItem, T> fieldMapper) {
        if (StrUtil.isBlank(color)) {
            return Optional.empty();
        }

        return Optional.ofNullable(getByColor(color)).map(fieldMapper);
   }

    /**
     * 根据Tag预设状态查询单条数据
     *
     * @param tagStatus Tag预设状态
     * @return 查询结果
     */
    public TbBasicSysDictionaryItem getByTagStatus(String tagStatus) {
        if (StrUtil.isBlank(tagStatus)) {
            return null;
        }

        TbBasicSysDictionaryItemPo po = new TbBasicSysDictionaryItemPo();
        po.setWhereTagStatus(tagStatus);
        return get(po);
    }

    /**
     * 根据Tag预设状态查询多条数据
     *
     * @param tagStatus Tag预设状态
     * @return 查询结果
     */
    public List<TbBasicSysDictionaryItem> queryByTagStatus(String tagStatus) {
        if (StrUtil.isBlank(tagStatus)) {
            return null;
        }

        TbBasicSysDictionaryItemPo po = new TbBasicSysDictionaryItemPo();
        po.setWhereTagStatus(tagStatus);
        return query(po);
    }

    /**
     * 根据Tag预设状态获取指定字段的值
     *
     * @param tagStatus Tag预设状态
     * @param fieldMapper 字段映射函数
     * @param <T> 字段类型
     * @return 字段值的 Optional
     */
    public <T> Optional<T> getFieldByTagStatus(String tagStatus, Function<TbBasicSysDictionaryItem, T> fieldMapper) {
        if (StrUtil.isBlank(tagStatus)) {
            return Optional.empty();
        }

        return Optional.ofNullable(getByTagStatus(tagStatus)).map(fieldMapper);
   }

    /**
     * 根据状态。1：启用，2：禁用查询单条数据
     *
     * @param status 状态。1：启用，2：禁用
     * @return 查询结果
     */
    public TbBasicSysDictionaryItem getByStatus(Integer status) {
        if (ObjectUtil.isNull(status)) {
            return null;
        }

        TbBasicSysDictionaryItemPo po = new TbBasicSysDictionaryItemPo();
        po.setWhereStatus(status);
        return get(po);
    }

    /**
     * 根据状态。1：启用，2：禁用查询多条数据
     *
     * @param status 状态。1：启用，2：禁用
     * @return 查询结果
     */
    public List<TbBasicSysDictionaryItem> queryByStatus(Integer status) {
        if (ObjectUtil.isNull(status)) {
            return null;
        }

        TbBasicSysDictionaryItemPo po = new TbBasicSysDictionaryItemPo();
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
    public <T> Optional<T> getFieldByStatus(Integer status, Function<TbBasicSysDictionaryItem, T> fieldMapper) {
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
    public TbBasicSysDictionaryItem getByCreatedAt(String createdAt) {
        if (StrUtil.isBlank(createdAt)) {
            return null;
        }

        TbBasicSysDictionaryItemPo po = new TbBasicSysDictionaryItemPo();
        po.setWhereCreatedAt(createdAt);
        return get(po);
    }

    /**
     * 根据创建时间查询多条数据
     *
     * @param createdAt 创建时间
     * @return 查询结果
     */
    public List<TbBasicSysDictionaryItem> queryByCreatedAt(String createdAt) {
        if (StrUtil.isBlank(createdAt)) {
            return null;
        }

        TbBasicSysDictionaryItemPo po = new TbBasicSysDictionaryItemPo();
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
    public <T> Optional<T> getFieldByCreatedAt(String createdAt, Function<TbBasicSysDictionaryItem, T> fieldMapper) {
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
    public TbBasicSysDictionaryItem getByCreatedUserId(Integer createdUserId) {
        if (ObjectUtil.isNull(createdUserId)) {
            return null;
        }

        TbBasicSysDictionaryItemPo po = new TbBasicSysDictionaryItemPo();
        po.setWhereCreatedUserId(createdUserId);
        return get(po);
    }

    /**
     * 根据创建者查询多条数据
     *
     * @param createdUserId 创建者
     * @return 查询结果
     */
    public List<TbBasicSysDictionaryItem> queryByCreatedUserId(Integer createdUserId) {
        if (ObjectUtil.isNull(createdUserId)) {
            return null;
        }

        TbBasicSysDictionaryItemPo po = new TbBasicSysDictionaryItemPo();
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
    public <T> Optional<T> getFieldByCreatedUserId(Integer createdUserId, Function<TbBasicSysDictionaryItem, T> fieldMapper) {
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
    public TbBasicSysDictionaryItem getByUpdatedAt(String updatedAt) {
        if (StrUtil.isBlank(updatedAt)) {
            return null;
        }

        TbBasicSysDictionaryItemPo po = new TbBasicSysDictionaryItemPo();
        po.setWhereUpdatedAt(updatedAt);
        return get(po);
    }

    /**
     * 根据更新时间查询多条数据
     *
     * @param updatedAt 更新时间
     * @return 查询结果
     */
    public List<TbBasicSysDictionaryItem> queryByUpdatedAt(String updatedAt) {
        if (StrUtil.isBlank(updatedAt)) {
            return null;
        }

        TbBasicSysDictionaryItemPo po = new TbBasicSysDictionaryItemPo();
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
    public <T> Optional<T> getFieldByUpdatedAt(String updatedAt, Function<TbBasicSysDictionaryItem, T> fieldMapper) {
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
    public TbBasicSysDictionaryItem getByUpdatedUserId(Integer updatedUserId) {
        if (ObjectUtil.isNull(updatedUserId)) {
            return null;
        }

        TbBasicSysDictionaryItemPo po = new TbBasicSysDictionaryItemPo();
        po.setWhereUpdatedUserId(updatedUserId);
        return get(po);
    }

    /**
     * 根据更新者查询多条数据
     *
     * @param updatedUserId 更新者
     * @return 查询结果
     */
    public List<TbBasicSysDictionaryItem> queryByUpdatedUserId(Integer updatedUserId) {
        if (ObjectUtil.isNull(updatedUserId)) {
            return null;
        }

        TbBasicSysDictionaryItemPo po = new TbBasicSysDictionaryItemPo();
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
    public <T> Optional<T> getFieldByUpdatedUserId(Integer updatedUserId, Function<TbBasicSysDictionaryItem, T> fieldMapper) {
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
    public BigDecimal sumId(TbBasicSysDictionaryItemPo po) {
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
    public BigDecimal maxId(TbBasicSysDictionaryItemPo po) {
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
    public BigDecimal minId(TbBasicSysDictionaryItemPo po) {
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
    public BigDecimal avgId(TbBasicSysDictionaryItemPo po) {
        if (po == null) {
            return BigDecimal.ZERO;
        }
        return mapper.avgId(po);
    }
    /**
     * 统计排序ID的总和
     *
     * @param po 查询条件
     * @return 总和结果
     */
    public BigDecimal sumSort(TbBasicSysDictionaryItemPo po) {
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
    public BigDecimal maxSort(TbBasicSysDictionaryItemPo po) {
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
    public BigDecimal minSort(TbBasicSysDictionaryItemPo po) {
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
    public BigDecimal avgSort(TbBasicSysDictionaryItemPo po) {
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
    public BigDecimal sumStatus(TbBasicSysDictionaryItemPo po) {
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
    public BigDecimal maxStatus(TbBasicSysDictionaryItemPo po) {
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
    public BigDecimal minStatus(TbBasicSysDictionaryItemPo po) {
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
    public BigDecimal avgStatus(TbBasicSysDictionaryItemPo po) {
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
    public BigDecimal sumCreatedUserId(TbBasicSysDictionaryItemPo po) {
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
    public BigDecimal maxCreatedUserId(TbBasicSysDictionaryItemPo po) {
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
    public BigDecimal minCreatedUserId(TbBasicSysDictionaryItemPo po) {
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
    public BigDecimal avgCreatedUserId(TbBasicSysDictionaryItemPo po) {
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
    public BigDecimal sumUpdatedUserId(TbBasicSysDictionaryItemPo po) {
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
    public BigDecimal maxUpdatedUserId(TbBasicSysDictionaryItemPo po) {
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
    public BigDecimal minUpdatedUserId(TbBasicSysDictionaryItemPo po) {
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
    public BigDecimal avgUpdatedUserId(TbBasicSysDictionaryItemPo po) {
        if (po == null) {
            return BigDecimal.ZERO;
        }
        return mapper.avgUpdatedUserId(po);
    }
    //***************************************************聚合操作 END********************************************************
}