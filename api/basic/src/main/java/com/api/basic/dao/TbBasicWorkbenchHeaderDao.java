package com.api.basic.dao;

import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import com.api.basic.data.entity.TbBasicWorkbenchHeader;
import com.api.basic.data.po.TbBasicWorkbenchHeaderPo;
import com.api.basic.mapper.TbBasicWorkbenchHeaderMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.*;
import java.util.function.Function;

/**
 * 工作台头部项目信息表Dao
 *
 * @author 裴金伟
 */
@Component
@RequiredArgsConstructor
public class TbBasicWorkbenchHeaderDao {

    private final TbBasicWorkbenchHeaderMapper mapper;

    //***************************************************添加 START******************************************************
    /**
     * 新增
     *
     * @param entity 新增信息
     * @return 新增ID
     */
    public Integer add(TbBasicWorkbenchHeader entity) {
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
    public Integer delete(TbBasicWorkbenchHeaderPo po) {
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
    public Integer update(TbBasicWorkbenchHeaderPo po) {
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
    public List<TbBasicWorkbenchHeader> query(TbBasicWorkbenchHeaderPo po) {
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
    public Integer cnt(TbBasicWorkbenchHeaderPo po) {
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
    public TbBasicWorkbenchHeader get(TbBasicWorkbenchHeaderPo po) {
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
    public TbBasicWorkbenchHeader getById(Integer id) {
        if (ObjectUtil.isNull(id)) {
            return null;
        }

        TbBasicWorkbenchHeaderPo po = new TbBasicWorkbenchHeaderPo();
        po.setWhereId(id);
        return get(po);
    }

    /**
     * 根据ID查询多条数据
     *
     * @param id ID
     * @return 查询结果
     */
    public List<TbBasicWorkbenchHeader> queryById(Integer id) {
        if (ObjectUtil.isNull(id)) {
            return null;
        }

        TbBasicWorkbenchHeaderPo po = new TbBasicWorkbenchHeaderPo();
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
    public <T> Optional<T> getFieldById(Integer id, Function<TbBasicWorkbenchHeader, T> fieldMapper) {
        if (ObjectUtil.isNull(id)) {
            return Optional.empty();
        }

        return Optional.ofNullable(getById(id)).map(fieldMapper);
   }

    /**
     * 根据工作台头部项目key查询单条数据
     *
     * @param key 工作台头部项目key
     * @return 查询结果
     */
    public TbBasicWorkbenchHeader getByKey(String key) {
        if (StrUtil.isBlank(key)) {
            return null;
        }

        TbBasicWorkbenchHeaderPo po = new TbBasicWorkbenchHeaderPo();
        po.setWhereKey(key);
        return get(po);
    }

    /**
     * 根据工作台头部项目key查询多条数据
     *
     * @param key 工作台头部项目key
     * @return 查询结果
     */
    public List<TbBasicWorkbenchHeader> queryByKey(String key) {
        if (StrUtil.isBlank(key)) {
            return null;
        }

        TbBasicWorkbenchHeaderPo po = new TbBasicWorkbenchHeaderPo();
        po.setWhereKey(key);
        return query(po);
    }

    /**
     * 根据工作台头部项目key获取指定字段的值
     *
     * @param key 工作台头部项目key
     * @param fieldMapper 字段映射函数
     * @param <T> 字段类型
     * @return 字段值的 Optional
     */
    public <T> Optional<T> getFieldByKey(String key, Function<TbBasicWorkbenchHeader, T> fieldMapper) {
        if (StrUtil.isBlank(key)) {
            return Optional.empty();
        }

        return Optional.ofNullable(getByKey(key)).map(fieldMapper);
   }

    /**
     * 根据标题查询单条数据
     *
     * @param title 标题
     * @return 查询结果
     */
    public TbBasicWorkbenchHeader getByTitle(String title) {
        if (StrUtil.isBlank(title)) {
            return null;
        }

        TbBasicWorkbenchHeaderPo po = new TbBasicWorkbenchHeaderPo();
        po.setWhereTitle(title);
        return get(po);
    }

    /**
     * 根据标题查询多条数据
     *
     * @param title 标题
     * @return 查询结果
     */
    public List<TbBasicWorkbenchHeader> queryByTitle(String title) {
        if (StrUtil.isBlank(title)) {
            return null;
        }

        TbBasicWorkbenchHeaderPo po = new TbBasicWorkbenchHeaderPo();
        po.setWhereTitle(title);
        return query(po);
    }

    /**
     * 根据标题获取指定字段的值
     *
     * @param title 标题
     * @param fieldMapper 字段映射函数
     * @param <T> 字段类型
     * @return 字段值的 Optional
     */
    public <T> Optional<T> getFieldByTitle(String title, Function<TbBasicWorkbenchHeader, T> fieldMapper) {
        if (StrUtil.isBlank(title)) {
            return Optional.empty();
        }

        return Optional.ofNullable(getByTitle(title)).map(fieldMapper);
   }

    /**
     * 根据描述查询单条数据
     *
     * @param description 描述
     * @return 查询结果
     */
    public TbBasicWorkbenchHeader getByDescription(String description) {
        if (StrUtil.isBlank(description)) {
            return null;
        }

        TbBasicWorkbenchHeaderPo po = new TbBasicWorkbenchHeaderPo();
        po.setWhereDescription(description);
        return get(po);
    }

    /**
     * 根据描述查询多条数据
     *
     * @param description 描述
     * @return 查询结果
     */
    public List<TbBasicWorkbenchHeader> queryByDescription(String description) {
        if (StrUtil.isBlank(description)) {
            return null;
        }

        TbBasicWorkbenchHeaderPo po = new TbBasicWorkbenchHeaderPo();
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
    public <T> Optional<T> getFieldByDescription(String description, Function<TbBasicWorkbenchHeader, T> fieldMapper) {
        if (StrUtil.isBlank(description)) {
            return Optional.empty();
        }

        return Optional.ofNullable(getByDescription(description)).map(fieldMapper);
   }

    //***************************************************获取 END********************************************************

    //***************************************************聚合操作 START******************************************************
    /**
     * 统计ID的总和
     *
     * @param po 查询条件
     * @return 总和结果
     */
    public BigDecimal sumId(TbBasicWorkbenchHeaderPo po) {
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
    public BigDecimal maxId(TbBasicWorkbenchHeaderPo po) {
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
    public BigDecimal minId(TbBasicWorkbenchHeaderPo po) {
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
    public BigDecimal avgId(TbBasicWorkbenchHeaderPo po) {
        if (po == null) {
            return BigDecimal.ZERO;
        }
        return mapper.avgId(po);
    }
    //***************************************************聚合操作 END********************************************************
}