package com.api.basic.dao;

import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import com.api.basic.data.entity.TbBasicSysDepartment;
import com.api.basic.data.po.TbBasicSysDepartmentPo;
import com.api.basic.mapper.TbBasicSysDepartmentMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.*;
import java.util.function.Function;

/**
 * 单位信息表Dao
 *
 * @author 裴金伟
 */
@Component
@RequiredArgsConstructor
public class TbBasicSysDepartmentDao {

    private final TbBasicSysDepartmentMapper mapper;

    //***************************************************添加 START******************************************************
    /**
     * 新增
     *
     * @param entity 新增信息
     * @return 新增ID
     */
    public Integer add(TbBasicSysDepartment entity) {
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
    public Integer delete(TbBasicSysDepartmentPo po) {
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
    public Integer update(TbBasicSysDepartmentPo po) {
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
    public List<TbBasicSysDepartment> query(TbBasicSysDepartmentPo po) {
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
    public Integer cnt(TbBasicSysDepartmentPo po) {
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
    public TbBasicSysDepartment get(TbBasicSysDepartmentPo po) {
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
    public TbBasicSysDepartment getById(Integer id) {
        if (ObjectUtil.isNull(id)) {
            return null;
        }

        TbBasicSysDepartmentPo po = new TbBasicSysDepartmentPo();
        po.setWhereId(id);
        return get(po);
    }

    /**
     * 根据ID查询多条数据
     *
     * @param id ID
     * @return 查询结果
     */
    public List<TbBasicSysDepartment> queryById(Integer id) {
        if (ObjectUtil.isNull(id)) {
            return null;
        }

        TbBasicSysDepartmentPo po = new TbBasicSysDepartmentPo();
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
    public <T> Optional<T> getFieldById(Integer id, Function<TbBasicSysDepartment, T> fieldMapper) {
        if (ObjectUtil.isNull(id)) {
            return Optional.empty();
        }

        return Optional.ofNullable(getById(id)).map(fieldMapper);
   }

    /**
     * 根据部门名称查询单条数据
     *
     * @param name 部门名称
     * @return 查询结果
     */
    public TbBasicSysDepartment getByName(String name) {
        if (StrUtil.isBlank(name)) {
            return null;
        }

        TbBasicSysDepartmentPo po = new TbBasicSysDepartmentPo();
        po.setWhereName(name);
        return get(po);
    }

    /**
     * 根据部门名称查询多条数据
     *
     * @param name 部门名称
     * @return 查询结果
     */
    public List<TbBasicSysDepartment> queryByName(String name) {
        if (StrUtil.isBlank(name)) {
            return null;
        }

        TbBasicSysDepartmentPo po = new TbBasicSysDepartmentPo();
        po.setWhereName(name);
        return query(po);
    }

    /**
     * 根据部门名称获取指定字段的值
     *
     * @param name 部门名称
     * @param fieldMapper 字段映射函数
     * @param <T> 字段类型
     * @return 字段值的 Optional
     */
    public <T> Optional<T> getFieldByName(String name, Function<TbBasicSysDepartment, T> fieldMapper) {
        if (StrUtil.isBlank(name)) {
            return Optional.empty();
        }

        return Optional.ofNullable(getByName(name)).map(fieldMapper);
   }

    /**
     * 根据上级ID查询单条数据
     *
     * @param parentId 上级ID
     * @return 查询结果
     */
    public TbBasicSysDepartment getByParentId(Integer parentId) {
        if (ObjectUtil.isNull(parentId)) {
            return null;
        }

        TbBasicSysDepartmentPo po = new TbBasicSysDepartmentPo();
        po.setWhereParentId(parentId);
        return get(po);
    }

    /**
     * 根据上级ID查询多条数据
     *
     * @param parentId 上级ID
     * @return 查询结果
     */
    public List<TbBasicSysDepartment> queryByParentId(Integer parentId) {
        if (ObjectUtil.isNull(parentId)) {
            return null;
        }

        TbBasicSysDepartmentPo po = new TbBasicSysDepartmentPo();
        po.setWhereParentId(parentId);
        return query(po);
    }

    /**
     * 根据上级ID获取指定字段的值
     *
     * @param parentId 上级ID
     * @param fieldMapper 字段映射函数
     * @param <T> 字段类型
     * @return 字段值的 Optional
     */
    public <T> Optional<T> getFieldByParentId(Integer parentId, Function<TbBasicSysDepartment, T> fieldMapper) {
        if (ObjectUtil.isNull(parentId)) {
            return Optional.empty();
        }

        return Optional.ofNullable(getByParentId(parentId)).map(fieldMapper);
   }

    /**
     * 根据是否删除。1：未删除，2：已删除。查询单条数据
     *
     * @param isDelete 是否删除。1：未删除，2：已删除。
     * @return 查询结果
     */
    public TbBasicSysDepartment getByIsDelete(Integer isDelete) {
        if (ObjectUtil.isNull(isDelete)) {
            return null;
        }

        TbBasicSysDepartmentPo po = new TbBasicSysDepartmentPo();
        po.setWhereIsDelete(isDelete);
        return get(po);
    }

    /**
     * 根据是否删除。1：未删除，2：已删除。查询多条数据
     *
     * @param isDelete 是否删除。1：未删除，2：已删除。
     * @return 查询结果
     */
    public List<TbBasicSysDepartment> queryByIsDelete(Integer isDelete) {
        if (ObjectUtil.isNull(isDelete)) {
            return null;
        }

        TbBasicSysDepartmentPo po = new TbBasicSysDepartmentPo();
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
    public <T> Optional<T> getFieldByIsDelete(Integer isDelete, Function<TbBasicSysDepartment, T> fieldMapper) {
        if (ObjectUtil.isNull(isDelete)) {
            return Optional.empty();
        }

        return Optional.ofNullable(getByIsDelete(isDelete)).map(fieldMapper);
   }

    /**
     * 根据排序查询单条数据
     *
     * @param sort 排序
     * @return 查询结果
     */
    public TbBasicSysDepartment getBySort(Integer sort) {
        if (ObjectUtil.isNull(sort)) {
            return null;
        }

        TbBasicSysDepartmentPo po = new TbBasicSysDepartmentPo();
        po.setWhereSort(sort);
        return get(po);
    }

    /**
     * 根据排序查询多条数据
     *
     * @param sort 排序
     * @return 查询结果
     */
    public List<TbBasicSysDepartment> queryBySort(Integer sort) {
        if (ObjectUtil.isNull(sort)) {
            return null;
        }

        TbBasicSysDepartmentPo po = new TbBasicSysDepartmentPo();
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
    public <T> Optional<T> getFieldBySort(Integer sort, Function<TbBasicSysDepartment, T> fieldMapper) {
        if (ObjectUtil.isNull(sort)) {
            return Optional.empty();
        }

        return Optional.ofNullable(getBySort(sort)).map(fieldMapper);
   }

    /**
     * 根据创建时间查询单条数据
     *
     * @param createdAt 创建时间
     * @return 查询结果
     */
    public TbBasicSysDepartment getByCreatedAt(String createdAt) {
        if (StrUtil.isBlank(createdAt)) {
            return null;
        }

        TbBasicSysDepartmentPo po = new TbBasicSysDepartmentPo();
        po.setWhereCreatedAt(createdAt);
        return get(po);
    }

    /**
     * 根据创建时间查询多条数据
     *
     * @param createdAt 创建时间
     * @return 查询结果
     */
    public List<TbBasicSysDepartment> queryByCreatedAt(String createdAt) {
        if (StrUtil.isBlank(createdAt)) {
            return null;
        }

        TbBasicSysDepartmentPo po = new TbBasicSysDepartmentPo();
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
    public <T> Optional<T> getFieldByCreatedAt(String createdAt, Function<TbBasicSysDepartment, T> fieldMapper) {
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
    public TbBasicSysDepartment getByCreatedUserId(Integer createdUserId) {
        if (ObjectUtil.isNull(createdUserId)) {
            return null;
        }

        TbBasicSysDepartmentPo po = new TbBasicSysDepartmentPo();
        po.setWhereCreatedUserId(createdUserId);
        return get(po);
    }

    /**
     * 根据创建者查询多条数据
     *
     * @param createdUserId 创建者
     * @return 查询结果
     */
    public List<TbBasicSysDepartment> queryByCreatedUserId(Integer createdUserId) {
        if (ObjectUtil.isNull(createdUserId)) {
            return null;
        }

        TbBasicSysDepartmentPo po = new TbBasicSysDepartmentPo();
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
    public <T> Optional<T> getFieldByCreatedUserId(Integer createdUserId, Function<TbBasicSysDepartment, T> fieldMapper) {
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
    public TbBasicSysDepartment getByUpdatedAt(String updatedAt) {
        if (StrUtil.isBlank(updatedAt)) {
            return null;
        }

        TbBasicSysDepartmentPo po = new TbBasicSysDepartmentPo();
        po.setWhereUpdatedAt(updatedAt);
        return get(po);
    }

    /**
     * 根据更新时间查询多条数据
     *
     * @param updatedAt 更新时间
     * @return 查询结果
     */
    public List<TbBasicSysDepartment> queryByUpdatedAt(String updatedAt) {
        if (StrUtil.isBlank(updatedAt)) {
            return null;
        }

        TbBasicSysDepartmentPo po = new TbBasicSysDepartmentPo();
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
    public <T> Optional<T> getFieldByUpdatedAt(String updatedAt, Function<TbBasicSysDepartment, T> fieldMapper) {
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
    public TbBasicSysDepartment getByUpdatedUserId(Integer updatedUserId) {
        if (ObjectUtil.isNull(updatedUserId)) {
            return null;
        }

        TbBasicSysDepartmentPo po = new TbBasicSysDepartmentPo();
        po.setWhereUpdatedUserId(updatedUserId);
        return get(po);
    }

    /**
     * 根据更新者查询多条数据
     *
     * @param updatedUserId 更新者
     * @return 查询结果
     */
    public List<TbBasicSysDepartment> queryByUpdatedUserId(Integer updatedUserId) {
        if (ObjectUtil.isNull(updatedUserId)) {
            return null;
        }

        TbBasicSysDepartmentPo po = new TbBasicSysDepartmentPo();
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
    public <T> Optional<T> getFieldByUpdatedUserId(Integer updatedUserId, Function<TbBasicSysDepartment, T> fieldMapper) {
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
    public BigDecimal sumId(TbBasicSysDepartmentPo po) {
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
    public BigDecimal maxId(TbBasicSysDepartmentPo po) {
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
    public BigDecimal minId(TbBasicSysDepartmentPo po) {
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
    public BigDecimal avgId(TbBasicSysDepartmentPo po) {
        if (po == null) {
            return BigDecimal.ZERO;
        }
        return mapper.avgId(po);
    }
    /**
     * 统计上级ID的总和
     *
     * @param po 查询条件
     * @return 总和结果
     */
    public BigDecimal sumParentId(TbBasicSysDepartmentPo po) {
        if (po == null) {
            return BigDecimal.ZERO;
        }
        return mapper.sumParentId(po);
    }

    /**
     * 统计上级ID的最大值
     *
     * @param po 查询条件
     * @return 最大值结果
     */
    public BigDecimal maxParentId(TbBasicSysDepartmentPo po) {
        if (po == null) {
            return BigDecimal.ZERO;
        }
        return mapper.maxParentId(po);
    }

    /**
     * 统计上级ID的最小值
     *
     * @param po 查询条件
     * @return 最小值结果
     */
    public BigDecimal minParentId(TbBasicSysDepartmentPo po) {
        if (po == null) {
            return BigDecimal.ZERO;
        }
        return mapper.minParentId(po);
    }

    /**
     * 统计上级ID的平均值
     *
     * @param po 查询条件
     * @return 平均值结果
     */
    public BigDecimal avgParentId(TbBasicSysDepartmentPo po) {
        if (po == null) {
            return BigDecimal.ZERO;
        }
        return mapper.avgParentId(po);
    }
    /**
     * 统计是否删除。1：未删除，2：已删除。的总和
     *
     * @param po 查询条件
     * @return 总和结果
     */
    public BigDecimal sumIsDelete(TbBasicSysDepartmentPo po) {
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
    public BigDecimal maxIsDelete(TbBasicSysDepartmentPo po) {
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
    public BigDecimal minIsDelete(TbBasicSysDepartmentPo po) {
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
    public BigDecimal avgIsDelete(TbBasicSysDepartmentPo po) {
        if (po == null) {
            return BigDecimal.ZERO;
        }
        return mapper.avgIsDelete(po);
    }
    /**
     * 统计排序的总和
     *
     * @param po 查询条件
     * @return 总和结果
     */
    public BigDecimal sumSort(TbBasicSysDepartmentPo po) {
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
    public BigDecimal maxSort(TbBasicSysDepartmentPo po) {
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
    public BigDecimal minSort(TbBasicSysDepartmentPo po) {
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
    public BigDecimal avgSort(TbBasicSysDepartmentPo po) {
        if (po == null) {
            return BigDecimal.ZERO;
        }
        return mapper.avgSort(po);
    }
    /**
     * 统计创建者的总和
     *
     * @param po 查询条件
     * @return 总和结果
     */
    public BigDecimal sumCreatedUserId(TbBasicSysDepartmentPo po) {
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
    public BigDecimal maxCreatedUserId(TbBasicSysDepartmentPo po) {
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
    public BigDecimal minCreatedUserId(TbBasicSysDepartmentPo po) {
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
    public BigDecimal avgCreatedUserId(TbBasicSysDepartmentPo po) {
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
    public BigDecimal sumUpdatedUserId(TbBasicSysDepartmentPo po) {
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
    public BigDecimal maxUpdatedUserId(TbBasicSysDepartmentPo po) {
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
    public BigDecimal minUpdatedUserId(TbBasicSysDepartmentPo po) {
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
    public BigDecimal avgUpdatedUserId(TbBasicSysDepartmentPo po) {
        if (po == null) {
            return BigDecimal.ZERO;
        }
        return mapper.avgUpdatedUserId(po);
    }
    //***************************************************聚合操作 END********************************************************
}