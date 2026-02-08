package com.api.basic.dao;

import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import com.api.basic.data.entity.TbBasicSysRole;
import com.api.basic.data.po.TbBasicSysRolePo;
import com.api.basic.mapper.TbBasicSysRoleMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.*;
import java.util.function.Function;

/**
 * 角色信息表Dao
 *
 * @author 裴金伟
 */
@Component
@RequiredArgsConstructor
public class TbBasicSysRoleDao {

    private final TbBasicSysRoleMapper mapper;

    //***************************************************添加 START******************************************************
    /**
     * 新增
     *
     * @param entity 新增信息
     * @return 新增ID
     */
    public Integer add(TbBasicSysRole entity) {
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
    public Integer delete(TbBasicSysRolePo po) {
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
    public Integer update(TbBasicSysRolePo po) {
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
    public List<TbBasicSysRole> query(TbBasicSysRolePo po) {
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
    public Integer cnt(TbBasicSysRolePo po) {
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
    public TbBasicSysRole get(TbBasicSysRolePo po) {
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
    public TbBasicSysRole getById(Integer id) {
        if (ObjectUtil.isNull(id)) {
            return null;
        }

        TbBasicSysRolePo po = new TbBasicSysRolePo();
        po.setWhereId(id);
        return get(po);
    }

    /**
     * 根据ID查询多条数据
     *
     * @param id ID
     * @return 查询结果
     */
    public List<TbBasicSysRole> queryById(Integer id) {
        if (ObjectUtil.isNull(id)) {
            return null;
        }

        TbBasicSysRolePo po = new TbBasicSysRolePo();
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
    public <T> Optional<T> getFieldById(Integer id, Function<TbBasicSysRole, T> fieldMapper) {
        if (ObjectUtil.isNull(id)) {
            return Optional.empty();
        }

        return Optional.ofNullable(getById(id)).map(fieldMapper);
   }

    /**
     * 根据名称查询单条数据
     *
     * @param name 名称
     * @return 查询结果
     */
    public TbBasicSysRole getByName(String name) {
        if (StrUtil.isBlank(name)) {
            return null;
        }

        TbBasicSysRolePo po = new TbBasicSysRolePo();
        po.setWhereName(name);
        return get(po);
    }

    /**
     * 根据名称查询多条数据
     *
     * @param name 名称
     * @return 查询结果
     */
    public List<TbBasicSysRole> queryByName(String name) {
        if (StrUtil.isBlank(name)) {
            return null;
        }

        TbBasicSysRolePo po = new TbBasicSysRolePo();
        po.setWhereName(name);
        return query(po);
    }

    /**
     * 根据名称获取指定字段的值
     *
     * @param name 名称
     * @param fieldMapper 字段映射函数
     * @param <T> 字段类型
     * @return 字段值的 Optional
     */
    public <T> Optional<T> getFieldByName(String name, Function<TbBasicSysRole, T> fieldMapper) {
        if (StrUtil.isBlank(name)) {
            return Optional.empty();
        }

        return Optional.ofNullable(getByName(name)).map(fieldMapper);
   }

    /**
     * 根据角色标识查询单条数据
     *
     * @param identifying 角色标识
     * @return 查询结果
     */
    public TbBasicSysRole getByIdentifying(String identifying) {
        if (StrUtil.isBlank(identifying)) {
            return null;
        }

        TbBasicSysRolePo po = new TbBasicSysRolePo();
        po.setWhereIdentifying(identifying);
        return get(po);
    }

    /**
     * 根据角色标识查询多条数据
     *
     * @param identifying 角色标识
     * @return 查询结果
     */
    public List<TbBasicSysRole> queryByIdentifying(String identifying) {
        if (StrUtil.isBlank(identifying)) {
            return null;
        }

        TbBasicSysRolePo po = new TbBasicSysRolePo();
        po.setWhereIdentifying(identifying);
        return query(po);
    }

    /**
     * 根据角色标识获取指定字段的值
     *
     * @param identifying 角色标识
     * @param fieldMapper 字段映射函数
     * @param <T> 字段类型
     * @return 字段值的 Optional
     */
    public <T> Optional<T> getFieldByIdentifying(String identifying, Function<TbBasicSysRole, T> fieldMapper) {
        if (StrUtil.isBlank(identifying)) {
            return Optional.empty();
        }

        return Optional.ofNullable(getByIdentifying(identifying)).map(fieldMapper);
   }

    /**
     * 根据备注查询单条数据
     *
     * @param remark 备注
     * @return 查询结果
     */
    public TbBasicSysRole getByRemark(String remark) {
        if (StrUtil.isBlank(remark)) {
            return null;
        }

        TbBasicSysRolePo po = new TbBasicSysRolePo();
        po.setWhereRemark(remark);
        return get(po);
    }

    /**
     * 根据备注查询多条数据
     *
     * @param remark 备注
     * @return 查询结果
     */
    public List<TbBasicSysRole> queryByRemark(String remark) {
        if (StrUtil.isBlank(remark)) {
            return null;
        }

        TbBasicSysRolePo po = new TbBasicSysRolePo();
        po.setWhereRemark(remark);
        return query(po);
    }

    /**
     * 根据备注获取指定字段的值
     *
     * @param remark 备注
     * @param fieldMapper 字段映射函数
     * @param <T> 字段类型
     * @return 字段值的 Optional
     */
    public <T> Optional<T> getFieldByRemark(String remark, Function<TbBasicSysRole, T> fieldMapper) {
        if (StrUtil.isBlank(remark)) {
            return Optional.empty();
        }

        return Optional.ofNullable(getByRemark(remark)).map(fieldMapper);
   }

    /**
     * 根据状态。1：正常，2：禁用查询单条数据
     *
     * @param status 状态。1：正常，2：禁用
     * @return 查询结果
     */
    public TbBasicSysRole getByStatus(Integer status) {
        if (ObjectUtil.isNull(status)) {
            return null;
        }

        TbBasicSysRolePo po = new TbBasicSysRolePo();
        po.setWhereStatus(status);
        return get(po);
    }

    /**
     * 根据状态。1：正常，2：禁用查询多条数据
     *
     * @param status 状态。1：正常，2：禁用
     * @return 查询结果
     */
    public List<TbBasicSysRole> queryByStatus(Integer status) {
        if (ObjectUtil.isNull(status)) {
            return null;
        }

        TbBasicSysRolePo po = new TbBasicSysRolePo();
        po.setWhereStatus(status);
        return query(po);
    }

    /**
     * 根据状态。1：正常，2：禁用获取指定字段的值
     *
     * @param status 状态。1：正常，2：禁用
     * @param fieldMapper 字段映射函数
     * @param <T> 字段类型
     * @return 字段值的 Optional
     */
    public <T> Optional<T> getFieldByStatus(Integer status, Function<TbBasicSysRole, T> fieldMapper) {
        if (ObjectUtil.isNull(status)) {
            return Optional.empty();
        }

        return Optional.ofNullable(getByStatus(status)).map(fieldMapper);
   }

    /**
     * 根据是否已删除。1：未删除，2：已删除查询单条数据
     *
     * @param isDelete 是否已删除。1：未删除，2：已删除
     * @return 查询结果
     */
    public TbBasicSysRole getByIsDelete(Integer isDelete) {
        if (ObjectUtil.isNull(isDelete)) {
            return null;
        }

        TbBasicSysRolePo po = new TbBasicSysRolePo();
        po.setWhereIsDelete(isDelete);
        return get(po);
    }

    /**
     * 根据是否已删除。1：未删除，2：已删除查询多条数据
     *
     * @param isDelete 是否已删除。1：未删除，2：已删除
     * @return 查询结果
     */
    public List<TbBasicSysRole> queryByIsDelete(Integer isDelete) {
        if (ObjectUtil.isNull(isDelete)) {
            return null;
        }

        TbBasicSysRolePo po = new TbBasicSysRolePo();
        po.setWhereIsDelete(isDelete);
        return query(po);
    }

    /**
     * 根据是否已删除。1：未删除，2：已删除获取指定字段的值
     *
     * @param isDelete 是否已删除。1：未删除，2：已删除
     * @param fieldMapper 字段映射函数
     * @param <T> 字段类型
     * @return 字段值的 Optional
     */
    public <T> Optional<T> getFieldByIsDelete(Integer isDelete, Function<TbBasicSysRole, T> fieldMapper) {
        if (ObjectUtil.isNull(isDelete)) {
            return Optional.empty();
        }

        return Optional.ofNullable(getByIsDelete(isDelete)).map(fieldMapper);
   }

    /**
     * 根据创建时间查询单条数据
     *
     * @param createdAt 创建时间
     * @return 查询结果
     */
    public TbBasicSysRole getByCreatedAt(String createdAt) {
        if (StrUtil.isBlank(createdAt)) {
            return null;
        }

        TbBasicSysRolePo po = new TbBasicSysRolePo();
        po.setWhereCreatedAt(createdAt);
        return get(po);
    }

    /**
     * 根据创建时间查询多条数据
     *
     * @param createdAt 创建时间
     * @return 查询结果
     */
    public List<TbBasicSysRole> queryByCreatedAt(String createdAt) {
        if (StrUtil.isBlank(createdAt)) {
            return null;
        }

        TbBasicSysRolePo po = new TbBasicSysRolePo();
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
    public <T> Optional<T> getFieldByCreatedAt(String createdAt, Function<TbBasicSysRole, T> fieldMapper) {
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
    public TbBasicSysRole getByCreatedUserId(Integer createdUserId) {
        if (ObjectUtil.isNull(createdUserId)) {
            return null;
        }

        TbBasicSysRolePo po = new TbBasicSysRolePo();
        po.setWhereCreatedUserId(createdUserId);
        return get(po);
    }

    /**
     * 根据创建者查询多条数据
     *
     * @param createdUserId 创建者
     * @return 查询结果
     */
    public List<TbBasicSysRole> queryByCreatedUserId(Integer createdUserId) {
        if (ObjectUtil.isNull(createdUserId)) {
            return null;
        }

        TbBasicSysRolePo po = new TbBasicSysRolePo();
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
    public <T> Optional<T> getFieldByCreatedUserId(Integer createdUserId, Function<TbBasicSysRole, T> fieldMapper) {
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
    public TbBasicSysRole getByUpdatedAt(String updatedAt) {
        if (StrUtil.isBlank(updatedAt)) {
            return null;
        }

        TbBasicSysRolePo po = new TbBasicSysRolePo();
        po.setWhereUpdatedAt(updatedAt);
        return get(po);
    }

    /**
     * 根据更新时间查询多条数据
     *
     * @param updatedAt 更新时间
     * @return 查询结果
     */
    public List<TbBasicSysRole> queryByUpdatedAt(String updatedAt) {
        if (StrUtil.isBlank(updatedAt)) {
            return null;
        }

        TbBasicSysRolePo po = new TbBasicSysRolePo();
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
    public <T> Optional<T> getFieldByUpdatedAt(String updatedAt, Function<TbBasicSysRole, T> fieldMapper) {
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
    public TbBasicSysRole getByUpdatedUserId(Integer updatedUserId) {
        if (ObjectUtil.isNull(updatedUserId)) {
            return null;
        }

        TbBasicSysRolePo po = new TbBasicSysRolePo();
        po.setWhereUpdatedUserId(updatedUserId);
        return get(po);
    }

    /**
     * 根据更新者查询多条数据
     *
     * @param updatedUserId 更新者
     * @return 查询结果
     */
    public List<TbBasicSysRole> queryByUpdatedUserId(Integer updatedUserId) {
        if (ObjectUtil.isNull(updatedUserId)) {
            return null;
        }

        TbBasicSysRolePo po = new TbBasicSysRolePo();
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
    public <T> Optional<T> getFieldByUpdatedUserId(Integer updatedUserId, Function<TbBasicSysRole, T> fieldMapper) {
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
    public BigDecimal sumId(TbBasicSysRolePo po) {
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
    public BigDecimal maxId(TbBasicSysRolePo po) {
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
    public BigDecimal minId(TbBasicSysRolePo po) {
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
    public BigDecimal avgId(TbBasicSysRolePo po) {
        if (po == null) {
            return BigDecimal.ZERO;
        }
        return mapper.avgId(po);
    }
    /**
     * 统计状态。1：正常，2：禁用的总和
     *
     * @param po 查询条件
     * @return 总和结果
     */
    public BigDecimal sumStatus(TbBasicSysRolePo po) {
        if (po == null) {
            return BigDecimal.ZERO;
        }
        return mapper.sumStatus(po);
    }

    /**
     * 统计状态。1：正常，2：禁用的最大值
     *
     * @param po 查询条件
     * @return 最大值结果
     */
    public BigDecimal maxStatus(TbBasicSysRolePo po) {
        if (po == null) {
            return BigDecimal.ZERO;
        }
        return mapper.maxStatus(po);
    }

    /**
     * 统计状态。1：正常，2：禁用的最小值
     *
     * @param po 查询条件
     * @return 最小值结果
     */
    public BigDecimal minStatus(TbBasicSysRolePo po) {
        if (po == null) {
            return BigDecimal.ZERO;
        }
        return mapper.minStatus(po);
    }

    /**
     * 统计状态。1：正常，2：禁用的平均值
     *
     * @param po 查询条件
     * @return 平均值结果
     */
    public BigDecimal avgStatus(TbBasicSysRolePo po) {
        if (po == null) {
            return BigDecimal.ZERO;
        }
        return mapper.avgStatus(po);
    }
    /**
     * 统计是否已删除。1：未删除，2：已删除的总和
     *
     * @param po 查询条件
     * @return 总和结果
     */
    public BigDecimal sumIsDelete(TbBasicSysRolePo po) {
        if (po == null) {
            return BigDecimal.ZERO;
        }
        return mapper.sumIsDelete(po);
    }

    /**
     * 统计是否已删除。1：未删除，2：已删除的最大值
     *
     * @param po 查询条件
     * @return 最大值结果
     */
    public BigDecimal maxIsDelete(TbBasicSysRolePo po) {
        if (po == null) {
            return BigDecimal.ZERO;
        }
        return mapper.maxIsDelete(po);
    }

    /**
     * 统计是否已删除。1：未删除，2：已删除的最小值
     *
     * @param po 查询条件
     * @return 最小值结果
     */
    public BigDecimal minIsDelete(TbBasicSysRolePo po) {
        if (po == null) {
            return BigDecimal.ZERO;
        }
        return mapper.minIsDelete(po);
    }

    /**
     * 统计是否已删除。1：未删除，2：已删除的平均值
     *
     * @param po 查询条件
     * @return 平均值结果
     */
    public BigDecimal avgIsDelete(TbBasicSysRolePo po) {
        if (po == null) {
            return BigDecimal.ZERO;
        }
        return mapper.avgIsDelete(po);
    }
    /**
     * 统计创建者的总和
     *
     * @param po 查询条件
     * @return 总和结果
     */
    public BigDecimal sumCreatedUserId(TbBasicSysRolePo po) {
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
    public BigDecimal maxCreatedUserId(TbBasicSysRolePo po) {
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
    public BigDecimal minCreatedUserId(TbBasicSysRolePo po) {
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
    public BigDecimal avgCreatedUserId(TbBasicSysRolePo po) {
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
    public BigDecimal sumUpdatedUserId(TbBasicSysRolePo po) {
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
    public BigDecimal maxUpdatedUserId(TbBasicSysRolePo po) {
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
    public BigDecimal minUpdatedUserId(TbBasicSysRolePo po) {
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
    public BigDecimal avgUpdatedUserId(TbBasicSysRolePo po) {
        if (po == null) {
            return BigDecimal.ZERO;
        }
        return mapper.avgUpdatedUserId(po);
    }
    //***************************************************聚合操作 END********************************************************
}