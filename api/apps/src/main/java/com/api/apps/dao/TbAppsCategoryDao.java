package com.api.apps.dao;

import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import com.api.apps.data.entity.TbAppsCategory;
import com.api.apps.data.po.TbAppsCategoryPo;
import com.api.apps.mapper.TbAppsCategoryMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;

/**
 * Dao
 *
 * @author 裴金伟
 */
@Component
@RequiredArgsConstructor
public class TbAppsCategoryDao {

    private final TbAppsCategoryMapper mapper;

    //***************************************************添加 START******************************************************
    /**
     * 新增
     *
     * @param entity 新增信息
     * @return 新增ID
     */
    public Integer add(TbAppsCategory entity) {
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
    public Integer delete(TbAppsCategoryPo po) {
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
    public Integer update(TbAppsCategoryPo po) {
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
    public List<TbAppsCategory> query(TbAppsCategoryPo po) {
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
    public Integer cnt(TbAppsCategoryPo po) {
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
    public TbAppsCategory get(TbAppsCategoryPo po) {
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
    public TbAppsCategory getById(Integer id) {
        if (ObjectUtil.isNull(id)) {
            return null;
        }

        TbAppsCategoryPo po = new TbAppsCategoryPo();
        po.setWhereId(id);
        return get(po);
    }

    /**
     * 根据查询多条数据
     *
     * @param id 
     * @return 查询结果
     */
    public List<TbAppsCategory> queryById(Integer id) {
        if (ObjectUtil.isNull(id)) {
            return null;
        }

        TbAppsCategoryPo po = new TbAppsCategoryPo();
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
    public <T> Optional<T> getFieldById(Integer id, Function<TbAppsCategory, T> fieldMapper) {
        if (ObjectUtil.isNull(id)) {
            return Optional.empty();
        }

        return Optional.ofNullable(getById(id)).map(fieldMapper);
   }

    /**
     * 根据父级ID查询单条数据
     *
     * @param pid 父级ID
     * @return 查询结果
     */
    public TbAppsCategory getByPid(Integer pid) {
        if (ObjectUtil.isNull(pid)) {
            return null;
        }

        TbAppsCategoryPo po = new TbAppsCategoryPo();
        po.setWherePid(pid);
        return get(po);
    }

    /**
     * 根据父级ID查询多条数据
     *
     * @param pid 父级ID
     * @return 查询结果
     */
    public List<TbAppsCategory> queryByPid(Integer pid) {
        if (ObjectUtil.isNull(pid)) {
            return null;
        }

        TbAppsCategoryPo po = new TbAppsCategoryPo();
        po.setWherePid(pid);
        return query(po);
    }

    /**
     * 根据父级ID获取指定字段的值
     *
     * @param pid 父级ID
     * @param fieldMapper 字段映射函数
     * @param <T> 字段类型
     * @return 字段值的 Optional
     */
    public <T> Optional<T> getFieldByPid(Integer pid, Function<TbAppsCategory, T> fieldMapper) {
        if (ObjectUtil.isNull(pid)) {
            return Optional.empty();
        }

        return Optional.ofNullable(getByPid(pid)).map(fieldMapper);
   }

    /**
     * 根据分类名称查询单条数据
     *
     * @param name 分类名称
     * @return 查询结果
     */
    public TbAppsCategory getByName(String name) {
        if (StrUtil.isBlank(name)) {
            return null;
        }

        TbAppsCategoryPo po = new TbAppsCategoryPo();
        po.setWhereName(name);
        return get(po);
    }

    /**
     * 根据分类名称查询多条数据
     *
     * @param name 分类名称
     * @return 查询结果
     */
    public List<TbAppsCategory> queryByName(String name) {
        if (StrUtil.isBlank(name)) {
            return null;
        }

        TbAppsCategoryPo po = new TbAppsCategoryPo();
        po.setWhereName(name);
        return query(po);
    }

    /**
     * 根据分类名称获取指定字段的值
     *
     * @param name 分类名称
     * @param fieldMapper 字段映射函数
     * @param <T> 字段类型
     * @return 字段值的 Optional
     */
    public <T> Optional<T> getFieldByName(String name, Function<TbAppsCategory, T> fieldMapper) {
        if (StrUtil.isBlank(name)) {
            return Optional.empty();
        }

        return Optional.ofNullable(getByName(name)).map(fieldMapper);
   }

    /**
     * 根据分类说明查询单条数据
     *
     * @param desc 分类说明
     * @return 查询结果
     */
    public TbAppsCategory getByDesc(String desc) {
        if (StrUtil.isBlank(desc)) {
            return null;
        }

        TbAppsCategoryPo po = new TbAppsCategoryPo();
        po.setWhereDesc(desc);
        return get(po);
    }

    /**
     * 根据分类说明查询多条数据
     *
     * @param desc 分类说明
     * @return 查询结果
     */
    public List<TbAppsCategory> queryByDesc(String desc) {
        if (StrUtil.isBlank(desc)) {
            return null;
        }

        TbAppsCategoryPo po = new TbAppsCategoryPo();
        po.setWhereDesc(desc);
        return query(po);
    }

    /**
     * 根据分类说明获取指定字段的值
     *
     * @param desc 分类说明
     * @param fieldMapper 字段映射函数
     * @param <T> 字段类型
     * @return 字段值的 Optional
     */
    public <T> Optional<T> getFieldByDesc(String desc, Function<TbAppsCategory, T> fieldMapper) {
        if (StrUtil.isBlank(desc)) {
            return Optional.empty();
        }

        return Optional.ofNullable(getByDesc(desc)).map(fieldMapper);
   }

    /**
     * 根据排序查询单条数据
     *
     * @param sort 排序
     * @return 查询结果
     */
    public TbAppsCategory getBySort(Integer sort) {
        if (ObjectUtil.isNull(sort)) {
            return null;
        }

        TbAppsCategoryPo po = new TbAppsCategoryPo();
        po.setWhereSort(sort);
        return get(po);
    }

    /**
     * 根据排序查询多条数据
     *
     * @param sort 排序
     * @return 查询结果
     */
    public List<TbAppsCategory> queryBySort(Integer sort) {
        if (ObjectUtil.isNull(sort)) {
            return null;
        }

        TbAppsCategoryPo po = new TbAppsCategoryPo();
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
    public <T> Optional<T> getFieldBySort(Integer sort, Function<TbAppsCategory, T> fieldMapper) {
        if (ObjectUtil.isNull(sort)) {
            return Optional.empty();
        }

        return Optional.ofNullable(getBySort(sort)).map(fieldMapper);
   }

    /**
     * 根据是否显示。1：显示，2：不显示。查询单条数据
     *
     * @param isShow 是否显示。1：显示，2：不显示。
     * @return 查询结果
     */
    public TbAppsCategory getByIsShow(Integer isShow) {
        if (ObjectUtil.isNull(isShow)) {
            return null;
        }

        TbAppsCategoryPo po = new TbAppsCategoryPo();
        po.setWhereIsShow(isShow);
        return get(po);
    }

    /**
     * 根据是否显示。1：显示，2：不显示。查询多条数据
     *
     * @param isShow 是否显示。1：显示，2：不显示。
     * @return 查询结果
     */
    public List<TbAppsCategory> queryByIsShow(Integer isShow) {
        if (ObjectUtil.isNull(isShow)) {
            return null;
        }

        TbAppsCategoryPo po = new TbAppsCategoryPo();
        po.setWhereIsShow(isShow);
        return query(po);
    }

    /**
     * 根据是否显示。1：显示，2：不显示。获取指定字段的值
     *
     * @param isShow 是否显示。1：显示，2：不显示。
     * @param fieldMapper 字段映射函数
     * @param <T> 字段类型
     * @return 字段值的 Optional
     */
    public <T> Optional<T> getFieldByIsShow(Integer isShow, Function<TbAppsCategory, T> fieldMapper) {
        if (ObjectUtil.isNull(isShow)) {
            return Optional.empty();
        }

        return Optional.ofNullable(getByIsShow(isShow)).map(fieldMapper);
   }

    /**
     * 根据创建时间查询单条数据
     *
     * @param createdAt 创建时间
     * @return 查询结果
     */
    public TbAppsCategory getByCreatedAt(String createdAt) {
        if (StrUtil.isBlank(createdAt)) {
            return null;
        }

        TbAppsCategoryPo po = new TbAppsCategoryPo();
        po.setWhereCreatedAt(createdAt);
        return get(po);
    }

    /**
     * 根据创建时间查询多条数据
     *
     * @param createdAt 创建时间
     * @return 查询结果
     */
    public List<TbAppsCategory> queryByCreatedAt(String createdAt) {
        if (StrUtil.isBlank(createdAt)) {
            return null;
        }

        TbAppsCategoryPo po = new TbAppsCategoryPo();
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
    public <T> Optional<T> getFieldByCreatedAt(String createdAt, Function<TbAppsCategory, T> fieldMapper) {
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
    public TbAppsCategory getByCreatedUserId(Integer createdUserId) {
        if (ObjectUtil.isNull(createdUserId)) {
            return null;
        }

        TbAppsCategoryPo po = new TbAppsCategoryPo();
        po.setWhereCreatedUserId(createdUserId);
        return get(po);
    }

    /**
     * 根据创建用户查询多条数据
     *
     * @param createdUserId 创建用户
     * @return 查询结果
     */
    public List<TbAppsCategory> queryByCreatedUserId(Integer createdUserId) {
        if (ObjectUtil.isNull(createdUserId)) {
            return null;
        }

        TbAppsCategoryPo po = new TbAppsCategoryPo();
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
    public <T> Optional<T> getFieldByCreatedUserId(Integer createdUserId, Function<TbAppsCategory, T> fieldMapper) {
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
    public TbAppsCategory getByUpdatedAt(String updatedAt) {
        if (StrUtil.isBlank(updatedAt)) {
            return null;
        }

        TbAppsCategoryPo po = new TbAppsCategoryPo();
        po.setWhereUpdatedAt(updatedAt);
        return get(po);
    }

    /**
     * 根据更新时间查询多条数据
     *
     * @param updatedAt 更新时间
     * @return 查询结果
     */
    public List<TbAppsCategory> queryByUpdatedAt(String updatedAt) {
        if (StrUtil.isBlank(updatedAt)) {
            return null;
        }

        TbAppsCategoryPo po = new TbAppsCategoryPo();
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
    public <T> Optional<T> getFieldByUpdatedAt(String updatedAt, Function<TbAppsCategory, T> fieldMapper) {
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
    public TbAppsCategory getByUpdatedUserId(Integer updatedUserId) {
        if (ObjectUtil.isNull(updatedUserId)) {
            return null;
        }

        TbAppsCategoryPo po = new TbAppsCategoryPo();
        po.setWhereUpdatedUserId(updatedUserId);
        return get(po);
    }

    /**
     * 根据更新用户查询多条数据
     *
     * @param updatedUserId 更新用户
     * @return 查询结果
     */
    public List<TbAppsCategory> queryByUpdatedUserId(Integer updatedUserId) {
        if (ObjectUtil.isNull(updatedUserId)) {
            return null;
        }

        TbAppsCategoryPo po = new TbAppsCategoryPo();
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
    public <T> Optional<T> getFieldByUpdatedUserId(Integer updatedUserId, Function<TbAppsCategory, T> fieldMapper) {
        if (ObjectUtil.isNull(updatedUserId)) {
            return Optional.empty();
        }

        return Optional.ofNullable(getByUpdatedUserId(updatedUserId)).map(fieldMapper);
   }

    //***************************************************获取 END********************************************************
}