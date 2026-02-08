package com.api.basic.dao;

import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import com.api.basic.data.entity.TbBasicSysRoleMenuRelation;
import com.api.basic.data.po.TbBasicSysRoleMenuRelationPo;
import com.api.basic.mapper.TbBasicSysRoleMenuRelationMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.*;
import java.util.function.Function;

/**
 * 角色-菜单关联信息表Dao
 *
 * @author 裴金伟
 */
@Component
@RequiredArgsConstructor
public class TbBasicSysRoleMenuRelationDao {

    private final TbBasicSysRoleMenuRelationMapper mapper;

    //***************************************************添加 START******************************************************
    /**
     * 新增
     *
     * @param entity 新增信息
     * @return 新增ID
     */
    public Integer add(TbBasicSysRoleMenuRelation entity) {
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
    public Integer delete(TbBasicSysRoleMenuRelationPo po) {
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
    public Integer update(TbBasicSysRoleMenuRelationPo po) {
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
    public List<TbBasicSysRoleMenuRelation> query(TbBasicSysRoleMenuRelationPo po) {
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
    public Integer cnt(TbBasicSysRoleMenuRelationPo po) {
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
    public TbBasicSysRoleMenuRelation get(TbBasicSysRoleMenuRelationPo po) {
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
    public TbBasicSysRoleMenuRelation getById(Integer id) {
        if (ObjectUtil.isNull(id)) {
            return null;
        }

        TbBasicSysRoleMenuRelationPo po = new TbBasicSysRoleMenuRelationPo();
        po.setWhereId(id);
        return get(po);
    }

    /**
     * 根据ID查询多条数据
     *
     * @param id ID
     * @return 查询结果
     */
    public List<TbBasicSysRoleMenuRelation> queryById(Integer id) {
        if (ObjectUtil.isNull(id)) {
            return null;
        }

        TbBasicSysRoleMenuRelationPo po = new TbBasicSysRoleMenuRelationPo();
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
    public <T> Optional<T> getFieldById(Integer id, Function<TbBasicSysRoleMenuRelation, T> fieldMapper) {
        if (ObjectUtil.isNull(id)) {
            return Optional.empty();
        }

        return Optional.ofNullable(getById(id)).map(fieldMapper);
   }

    /**
     * 根据角色ID查询单条数据
     *
     * @param roleId 角色ID
     * @return 查询结果
     */
    public TbBasicSysRoleMenuRelation getByRoleId(Integer roleId) {
        if (ObjectUtil.isNull(roleId)) {
            return null;
        }

        TbBasicSysRoleMenuRelationPo po = new TbBasicSysRoleMenuRelationPo();
        po.setWhereRoleId(roleId);
        return get(po);
    }

    /**
     * 根据角色ID查询多条数据
     *
     * @param roleId 角色ID
     * @return 查询结果
     */
    public List<TbBasicSysRoleMenuRelation> queryByRoleId(Integer roleId) {
        if (ObjectUtil.isNull(roleId)) {
            return null;
        }

        TbBasicSysRoleMenuRelationPo po = new TbBasicSysRoleMenuRelationPo();
        po.setWhereRoleId(roleId);
        return query(po);
    }

    /**
     * 根据角色ID获取指定字段的值
     *
     * @param roleId 角色ID
     * @param fieldMapper 字段映射函数
     * @param <T> 字段类型
     * @return 字段值的 Optional
     */
    public <T> Optional<T> getFieldByRoleId(Integer roleId, Function<TbBasicSysRoleMenuRelation, T> fieldMapper) {
        if (ObjectUtil.isNull(roleId)) {
            return Optional.empty();
        }

        return Optional.ofNullable(getByRoleId(roleId)).map(fieldMapper);
   }

    /**
     * 根据菜单ID查询单条数据
     *
     * @param menuId 菜单ID
     * @return 查询结果
     */
    public TbBasicSysRoleMenuRelation getByMenuId(Integer menuId) {
        if (ObjectUtil.isNull(menuId)) {
            return null;
        }

        TbBasicSysRoleMenuRelationPo po = new TbBasicSysRoleMenuRelationPo();
        po.setWhereMenuId(menuId);
        return get(po);
    }

    /**
     * 根据菜单ID查询多条数据
     *
     * @param menuId 菜单ID
     * @return 查询结果
     */
    public List<TbBasicSysRoleMenuRelation> queryByMenuId(Integer menuId) {
        if (ObjectUtil.isNull(menuId)) {
            return null;
        }

        TbBasicSysRoleMenuRelationPo po = new TbBasicSysRoleMenuRelationPo();
        po.setWhereMenuId(menuId);
        return query(po);
    }

    /**
     * 根据菜单ID获取指定字段的值
     *
     * @param menuId 菜单ID
     * @param fieldMapper 字段映射函数
     * @param <T> 字段类型
     * @return 字段值的 Optional
     */
    public <T> Optional<T> getFieldByMenuId(Integer menuId, Function<TbBasicSysRoleMenuRelation, T> fieldMapper) {
        if (ObjectUtil.isNull(menuId)) {
            return Optional.empty();
        }

        return Optional.ofNullable(getByMenuId(menuId)).map(fieldMapper);
   }

    /**
     * 根据是否已删除。1：未删除，2：已删除查询单条数据
     *
     * @param isDelete 是否已删除。1：未删除，2：已删除
     * @return 查询结果
     */
    public TbBasicSysRoleMenuRelation getByIsDelete(Integer isDelete) {
        if (ObjectUtil.isNull(isDelete)) {
            return null;
        }

        TbBasicSysRoleMenuRelationPo po = new TbBasicSysRoleMenuRelationPo();
        po.setWhereIsDelete(isDelete);
        return get(po);
    }

    /**
     * 根据是否已删除。1：未删除，2：已删除查询多条数据
     *
     * @param isDelete 是否已删除。1：未删除，2：已删除
     * @return 查询结果
     */
    public List<TbBasicSysRoleMenuRelation> queryByIsDelete(Integer isDelete) {
        if (ObjectUtil.isNull(isDelete)) {
            return null;
        }

        TbBasicSysRoleMenuRelationPo po = new TbBasicSysRoleMenuRelationPo();
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
    public <T> Optional<T> getFieldByIsDelete(Integer isDelete, Function<TbBasicSysRoleMenuRelation, T> fieldMapper) {
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
    public TbBasicSysRoleMenuRelation getByCreatedAt(String createdAt) {
        if (StrUtil.isBlank(createdAt)) {
            return null;
        }

        TbBasicSysRoleMenuRelationPo po = new TbBasicSysRoleMenuRelationPo();
        po.setWhereCreatedAt(createdAt);
        return get(po);
    }

    /**
     * 根据创建时间查询多条数据
     *
     * @param createdAt 创建时间
     * @return 查询结果
     */
    public List<TbBasicSysRoleMenuRelation> queryByCreatedAt(String createdAt) {
        if (StrUtil.isBlank(createdAt)) {
            return null;
        }

        TbBasicSysRoleMenuRelationPo po = new TbBasicSysRoleMenuRelationPo();
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
    public <T> Optional<T> getFieldByCreatedAt(String createdAt, Function<TbBasicSysRoleMenuRelation, T> fieldMapper) {
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
    public TbBasicSysRoleMenuRelation getByCreatedUserId(Integer createdUserId) {
        if (ObjectUtil.isNull(createdUserId)) {
            return null;
        }

        TbBasicSysRoleMenuRelationPo po = new TbBasicSysRoleMenuRelationPo();
        po.setWhereCreatedUserId(createdUserId);
        return get(po);
    }

    /**
     * 根据创建者查询多条数据
     *
     * @param createdUserId 创建者
     * @return 查询结果
     */
    public List<TbBasicSysRoleMenuRelation> queryByCreatedUserId(Integer createdUserId) {
        if (ObjectUtil.isNull(createdUserId)) {
            return null;
        }

        TbBasicSysRoleMenuRelationPo po = new TbBasicSysRoleMenuRelationPo();
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
    public <T> Optional<T> getFieldByCreatedUserId(Integer createdUserId, Function<TbBasicSysRoleMenuRelation, T> fieldMapper) {
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
    public TbBasicSysRoleMenuRelation getByUpdatedAt(String updatedAt) {
        if (StrUtil.isBlank(updatedAt)) {
            return null;
        }

        TbBasicSysRoleMenuRelationPo po = new TbBasicSysRoleMenuRelationPo();
        po.setWhereUpdatedAt(updatedAt);
        return get(po);
    }

    /**
     * 根据更新时间查询多条数据
     *
     * @param updatedAt 更新时间
     * @return 查询结果
     */
    public List<TbBasicSysRoleMenuRelation> queryByUpdatedAt(String updatedAt) {
        if (StrUtil.isBlank(updatedAt)) {
            return null;
        }

        TbBasicSysRoleMenuRelationPo po = new TbBasicSysRoleMenuRelationPo();
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
    public <T> Optional<T> getFieldByUpdatedAt(String updatedAt, Function<TbBasicSysRoleMenuRelation, T> fieldMapper) {
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
    public TbBasicSysRoleMenuRelation getByUpdatedUserId(Integer updatedUserId) {
        if (ObjectUtil.isNull(updatedUserId)) {
            return null;
        }

        TbBasicSysRoleMenuRelationPo po = new TbBasicSysRoleMenuRelationPo();
        po.setWhereUpdatedUserId(updatedUserId);
        return get(po);
    }

    /**
     * 根据更新者查询多条数据
     *
     * @param updatedUserId 更新者
     * @return 查询结果
     */
    public List<TbBasicSysRoleMenuRelation> queryByUpdatedUserId(Integer updatedUserId) {
        if (ObjectUtil.isNull(updatedUserId)) {
            return null;
        }

        TbBasicSysRoleMenuRelationPo po = new TbBasicSysRoleMenuRelationPo();
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
    public <T> Optional<T> getFieldByUpdatedUserId(Integer updatedUserId, Function<TbBasicSysRoleMenuRelation, T> fieldMapper) {
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
    public BigDecimal sumId(TbBasicSysRoleMenuRelationPo po) {
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
    public BigDecimal maxId(TbBasicSysRoleMenuRelationPo po) {
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
    public BigDecimal minId(TbBasicSysRoleMenuRelationPo po) {
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
    public BigDecimal avgId(TbBasicSysRoleMenuRelationPo po) {
        if (po == null) {
            return BigDecimal.ZERO;
        }
        return mapper.avgId(po);
    }
    /**
     * 统计角色ID的总和
     *
     * @param po 查询条件
     * @return 总和结果
     */
    public BigDecimal sumRoleId(TbBasicSysRoleMenuRelationPo po) {
        if (po == null) {
            return BigDecimal.ZERO;
        }
        return mapper.sumRoleId(po);
    }

    /**
     * 统计角色ID的最大值
     *
     * @param po 查询条件
     * @return 最大值结果
     */
    public BigDecimal maxRoleId(TbBasicSysRoleMenuRelationPo po) {
        if (po == null) {
            return BigDecimal.ZERO;
        }
        return mapper.maxRoleId(po);
    }

    /**
     * 统计角色ID的最小值
     *
     * @param po 查询条件
     * @return 最小值结果
     */
    public BigDecimal minRoleId(TbBasicSysRoleMenuRelationPo po) {
        if (po == null) {
            return BigDecimal.ZERO;
        }
        return mapper.minRoleId(po);
    }

    /**
     * 统计角色ID的平均值
     *
     * @param po 查询条件
     * @return 平均值结果
     */
    public BigDecimal avgRoleId(TbBasicSysRoleMenuRelationPo po) {
        if (po == null) {
            return BigDecimal.ZERO;
        }
        return mapper.avgRoleId(po);
    }
    /**
     * 统计菜单ID的总和
     *
     * @param po 查询条件
     * @return 总和结果
     */
    public BigDecimal sumMenuId(TbBasicSysRoleMenuRelationPo po) {
        if (po == null) {
            return BigDecimal.ZERO;
        }
        return mapper.sumMenuId(po);
    }

    /**
     * 统计菜单ID的最大值
     *
     * @param po 查询条件
     * @return 最大值结果
     */
    public BigDecimal maxMenuId(TbBasicSysRoleMenuRelationPo po) {
        if (po == null) {
            return BigDecimal.ZERO;
        }
        return mapper.maxMenuId(po);
    }

    /**
     * 统计菜单ID的最小值
     *
     * @param po 查询条件
     * @return 最小值结果
     */
    public BigDecimal minMenuId(TbBasicSysRoleMenuRelationPo po) {
        if (po == null) {
            return BigDecimal.ZERO;
        }
        return mapper.minMenuId(po);
    }

    /**
     * 统计菜单ID的平均值
     *
     * @param po 查询条件
     * @return 平均值结果
     */
    public BigDecimal avgMenuId(TbBasicSysRoleMenuRelationPo po) {
        if (po == null) {
            return BigDecimal.ZERO;
        }
        return mapper.avgMenuId(po);
    }
    /**
     * 统计是否已删除。1：未删除，2：已删除的总和
     *
     * @param po 查询条件
     * @return 总和结果
     */
    public BigDecimal sumIsDelete(TbBasicSysRoleMenuRelationPo po) {
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
    public BigDecimal maxIsDelete(TbBasicSysRoleMenuRelationPo po) {
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
    public BigDecimal minIsDelete(TbBasicSysRoleMenuRelationPo po) {
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
    public BigDecimal avgIsDelete(TbBasicSysRoleMenuRelationPo po) {
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
    public BigDecimal sumCreatedUserId(TbBasicSysRoleMenuRelationPo po) {
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
    public BigDecimal maxCreatedUserId(TbBasicSysRoleMenuRelationPo po) {
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
    public BigDecimal minCreatedUserId(TbBasicSysRoleMenuRelationPo po) {
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
    public BigDecimal avgCreatedUserId(TbBasicSysRoleMenuRelationPo po) {
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
    public BigDecimal sumUpdatedUserId(TbBasicSysRoleMenuRelationPo po) {
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
    public BigDecimal maxUpdatedUserId(TbBasicSysRoleMenuRelationPo po) {
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
    public BigDecimal minUpdatedUserId(TbBasicSysRoleMenuRelationPo po) {
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
    public BigDecimal avgUpdatedUserId(TbBasicSysRoleMenuRelationPo po) {
        if (po == null) {
            return BigDecimal.ZERO;
        }
        return mapper.avgUpdatedUserId(po);
    }
    //***************************************************聚合操作 END********************************************************
}