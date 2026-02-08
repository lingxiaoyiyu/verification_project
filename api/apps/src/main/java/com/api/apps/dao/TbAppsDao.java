package com.api.apps.dao;

import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import com.api.apps.data.entity.TbApps;
import com.api.apps.data.po.TbAppsPo;
import com.api.apps.mapper.TbAppsMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;

/**
 * 应用信息表Dao
 *
 * @author 裴金伟
 */
@Component
@RequiredArgsConstructor
public class TbAppsDao {

    private final TbAppsMapper mapper;

    //***************************************************添加 START******************************************************
    /**
     * 新增
     *
     * @param entity 新增信息
     * @return 新增ID
     */
    public Integer add(TbApps entity) {
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
    public Integer delete(TbAppsPo po) {
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
    public Integer update(TbAppsPo po) {
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
    public List<TbApps> query(TbAppsPo po) {
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
    public Integer cnt(TbAppsPo po) {
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
    public TbApps get(TbAppsPo po) {
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
    public TbApps getById(String id) {
        if (StrUtil.isBlank(id)) {
            return null;
        }

        TbAppsPo po = new TbAppsPo();
        po.setWhereId(id);
        return get(po);
    }

    /**
     * 根据查询多条数据
     *
     * @param id 
     * @return 查询结果
     */
    public List<TbApps> queryById(String id) {
        if (StrUtil.isBlank(id)) {
            return null;
        }

        TbAppsPo po = new TbAppsPo();
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
    public <T> Optional<T> getFieldById(String id, Function<TbApps, T> fieldMapper) {
        if (StrUtil.isBlank(id)) {
            return Optional.empty();
        }

        return Optional.ofNullable(getById(id)).map(fieldMapper);
   }

    /**
     * 根据应用秘钥查询单条数据
     *
     * @param appSecret 应用秘钥
     * @return 查询结果
     */
    public TbApps getByAppSecret(String appSecret) {
        if (StrUtil.isBlank(appSecret)) {
            return null;
        }

        TbAppsPo po = new TbAppsPo();
        po.setWhereAppSecret(appSecret);
        return get(po);
    }

    /**
     * 根据应用秘钥查询多条数据
     *
     * @param appSecret 应用秘钥
     * @return 查询结果
     */
    public List<TbApps> queryByAppSecret(String appSecret) {
        if (StrUtil.isBlank(appSecret)) {
            return null;
        }

        TbAppsPo po = new TbAppsPo();
        po.setWhereAppSecret(appSecret);
        return query(po);
    }

    /**
     * 根据应用秘钥获取指定字段的值
     *
     * @param appSecret 应用秘钥
     * @param fieldMapper 字段映射函数
     * @param <T> 字段类型
     * @return 字段值的 Optional
     */
    public <T> Optional<T> getFieldByAppSecret(String appSecret, Function<TbApps, T> fieldMapper) {
        if (StrUtil.isBlank(appSecret)) {
            return Optional.empty();
        }

        return Optional.ofNullable(getByAppSecret(appSecret)).map(fieldMapper);
   }

    /**
     * 根据应用类型。1：宿主APP，2：小程序，3：网站。4：第三方应用。查询单条数据
     *
     * @param type 应用类型。1：宿主APP，2：小程序，3：网站。4：第三方应用。
     * @return 查询结果
     */
    public TbApps getByType(Integer type) {
        if (ObjectUtil.isNull(type)) {
            return null;
        }

        TbAppsPo po = new TbAppsPo();
        po.setWhereType(type);
        return get(po);
    }

    /**
     * 根据应用类型。1：宿主APP，2：小程序，3：网站。4：第三方应用。查询多条数据
     *
     * @param type 应用类型。1：宿主APP，2：小程序，3：网站。4：第三方应用。
     * @return 查询结果
     */
    public List<TbApps> queryByType(Integer type) {
        if (ObjectUtil.isNull(type)) {
            return null;
        }

        TbAppsPo po = new TbAppsPo();
        po.setWhereType(type);
        return query(po);
    }

    /**
     * 根据应用类型。1：宿主APP，2：小程序，3：网站。4：第三方应用。获取指定字段的值
     *
     * @param type 应用类型。1：宿主APP，2：小程序，3：网站。4：第三方应用。
     * @param fieldMapper 字段映射函数
     * @param <T> 字段类型
     * @return 字段值的 Optional
     */
    public <T> Optional<T> getFieldByType(Integer type, Function<TbApps, T> fieldMapper) {
        if (ObjectUtil.isNull(type)) {
            return Optional.empty();
        }

        return Optional.ofNullable(getByType(type)).map(fieldMapper);
   }

    /**
     * 根据排序ID查询单条数据
     *
     * @param sort 排序ID
     * @return 查询结果
     */
    public TbApps getBySort(Integer sort) {
        if (ObjectUtil.isNull(sort)) {
            return null;
        }

        TbAppsPo po = new TbAppsPo();
        po.setWhereSort(sort);
        return get(po);
    }

    /**
     * 根据排序ID查询多条数据
     *
     * @param sort 排序ID
     * @return 查询结果
     */
    public List<TbApps> queryBySort(Integer sort) {
        if (ObjectUtil.isNull(sort)) {
            return null;
        }

        TbAppsPo po = new TbAppsPo();
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
    public <T> Optional<T> getFieldBySort(Integer sort, Function<TbApps, T> fieldMapper) {
        if (ObjectUtil.isNull(sort)) {
            return Optional.empty();
        }

        return Optional.ofNullable(getBySort(sort)).map(fieldMapper);
   }

    /**
     * 根据uniapp_id查询单条数据
     *
     * @param uniAppid uniapp_id
     * @return 查询结果
     */
    public TbApps getByUniAppid(String uniAppid) {
        if (StrUtil.isBlank(uniAppid)) {
            return null;
        }

        TbAppsPo po = new TbAppsPo();
        po.setWhereUniAppid(uniAppid);
        return get(po);
    }

    /**
     * 根据uniapp_id查询多条数据
     *
     * @param uniAppid uniapp_id
     * @return 查询结果
     */
    public List<TbApps> queryByUniAppid(String uniAppid) {
        if (StrUtil.isBlank(uniAppid)) {
            return null;
        }

        TbAppsPo po = new TbAppsPo();
        po.setWhereUniAppid(uniAppid);
        return query(po);
    }

    /**
     * 根据uniapp_id获取指定字段的值
     *
     * @param uniAppid uniapp_id
     * @param fieldMapper 字段映射函数
     * @param <T> 字段类型
     * @return 字段值的 Optional
     */
    public <T> Optional<T> getFieldByUniAppid(String uniAppid, Function<TbApps, T> fieldMapper) {
        if (StrUtil.isBlank(uniAppid)) {
            return Optional.empty();
        }

        return Optional.ofNullable(getByUniAppid(uniAppid)).map(fieldMapper);
   }

    /**
     * 根据允许访问类型。1：公开，2：受限（指定角色或指定用户可访问）。仅零号小程序和普通小程序的访问类型值有意义。查询单条数据
     *
     * @param accessType 允许访问类型。1：公开，2：受限（指定角色或指定用户可访问）。仅零号小程序和普通小程序的访问类型值有意义。
     * @return 查询结果
     */
    public TbApps getByAccessType(Integer accessType) {
        if (ObjectUtil.isNull(accessType)) {
            return null;
        }

        TbAppsPo po = new TbAppsPo();
        po.setWhereAccessType(accessType);
        return get(po);
    }

    /**
     * 根据允许访问类型。1：公开，2：受限（指定角色或指定用户可访问）。仅零号小程序和普通小程序的访问类型值有意义。查询多条数据
     *
     * @param accessType 允许访问类型。1：公开，2：受限（指定角色或指定用户可访问）。仅零号小程序和普通小程序的访问类型值有意义。
     * @return 查询结果
     */
    public List<TbApps> queryByAccessType(Integer accessType) {
        if (ObjectUtil.isNull(accessType)) {
            return null;
        }

        TbAppsPo po = new TbAppsPo();
        po.setWhereAccessType(accessType);
        return query(po);
    }

    /**
     * 根据允许访问类型。1：公开，2：受限（指定角色或指定用户可访问）。仅零号小程序和普通小程序的访问类型值有意义。获取指定字段的值
     *
     * @param accessType 允许访问类型。1：公开，2：受限（指定角色或指定用户可访问）。仅零号小程序和普通小程序的访问类型值有意义。
     * @param fieldMapper 字段映射函数
     * @param <T> 字段类型
     * @return 字段值的 Optional
     */
    public <T> Optional<T> getFieldByAccessType(Integer accessType, Function<TbApps, T> fieldMapper) {
        if (ObjectUtil.isNull(accessType)) {
            return Optional.empty();
        }

        return Optional.ofNullable(getByAccessType(accessType)).map(fieldMapper);
   }

    /**
     * 根据允许访问的角色查询单条数据
     *
     * @param accessRoleIds 允许访问的角色
     * @return 查询结果
     */
    public TbApps getByAccessRoleIds(String accessRoleIds) {
        if (StrUtil.isBlank(accessRoleIds)) {
            return null;
        }

        TbAppsPo po = new TbAppsPo();
        po.setWhereAccessRoleIds(accessRoleIds);
        return get(po);
    }

    /**
     * 根据允许访问的角色查询多条数据
     *
     * @param accessRoleIds 允许访问的角色
     * @return 查询结果
     */
    public List<TbApps> queryByAccessRoleIds(String accessRoleIds) {
        if (StrUtil.isBlank(accessRoleIds)) {
            return null;
        }

        TbAppsPo po = new TbAppsPo();
        po.setWhereAccessRoleIds(accessRoleIds);
        return query(po);
    }

    /**
     * 根据允许访问的角色获取指定字段的值
     *
     * @param accessRoleIds 允许访问的角色
     * @param fieldMapper 字段映射函数
     * @param <T> 字段类型
     * @return 字段值的 Optional
     */
    public <T> Optional<T> getFieldByAccessRoleIds(String accessRoleIds, Function<TbApps, T> fieldMapper) {
        if (StrUtil.isBlank(accessRoleIds)) {
            return Optional.empty();
        }

        return Optional.ofNullable(getByAccessRoleIds(accessRoleIds)).map(fieldMapper);
   }

    /**
     * 根据允许访问的用户ID查询单条数据
     *
     * @param accessUserIds 允许访问的用户ID
     * @return 查询结果
     */
    public TbApps getByAccessUserIds(String accessUserIds) {
        if (StrUtil.isBlank(accessUserIds)) {
            return null;
        }

        TbAppsPo po = new TbAppsPo();
        po.setWhereAccessUserIds(accessUserIds);
        return get(po);
    }

    /**
     * 根据允许访问的用户ID查询多条数据
     *
     * @param accessUserIds 允许访问的用户ID
     * @return 查询结果
     */
    public List<TbApps> queryByAccessUserIds(String accessUserIds) {
        if (StrUtil.isBlank(accessUserIds)) {
            return null;
        }

        TbAppsPo po = new TbAppsPo();
        po.setWhereAccessUserIds(accessUserIds);
        return query(po);
    }

    /**
     * 根据允许访问的用户ID获取指定字段的值
     *
     * @param accessUserIds 允许访问的用户ID
     * @param fieldMapper 字段映射函数
     * @param <T> 字段类型
     * @return 字段值的 Optional
     */
    public <T> Optional<T> getFieldByAccessUserIds(String accessUserIds, Function<TbApps, T> fieldMapper) {
        if (StrUtil.isBlank(accessUserIds)) {
            return Optional.empty();
        }

        return Optional.ofNullable(getByAccessUserIds(accessUserIds)).map(fieldMapper);
   }

    /**
     * 根据应用名称查询单条数据
     *
     * @param name 应用名称
     * @return 查询结果
     */
    public TbApps getByName(String name) {
        if (StrUtil.isBlank(name)) {
            return null;
        }

        TbAppsPo po = new TbAppsPo();
        po.setWhereName(name);
        return get(po);
    }

    /**
     * 根据应用名称查询多条数据
     *
     * @param name 应用名称
     * @return 查询结果
     */
    public List<TbApps> queryByName(String name) {
        if (StrUtil.isBlank(name)) {
            return null;
        }

        TbAppsPo po = new TbAppsPo();
        po.setWhereName(name);
        return query(po);
    }

    /**
     * 根据应用名称获取指定字段的值
     *
     * @param name 应用名称
     * @param fieldMapper 字段映射函数
     * @param <T> 字段类型
     * @return 字段值的 Optional
     */
    public <T> Optional<T> getFieldByName(String name, Function<TbApps, T> fieldMapper) {
        if (StrUtil.isBlank(name)) {
            return Optional.empty();
        }

        return Optional.ofNullable(getByName(name)).map(fieldMapper);
   }

    /**
     * 根据应用简介查询单条数据
     *
     * @param desc 应用简介
     * @return 查询结果
     */
    public TbApps getByDesc(String desc) {
        if (StrUtil.isBlank(desc)) {
            return null;
        }

        TbAppsPo po = new TbAppsPo();
        po.setWhereDesc(desc);
        return get(po);
    }

    /**
     * 根据应用简介查询多条数据
     *
     * @param desc 应用简介
     * @return 查询结果
     */
    public List<TbApps> queryByDesc(String desc) {
        if (StrUtil.isBlank(desc)) {
            return null;
        }

        TbAppsPo po = new TbAppsPo();
        po.setWhereDesc(desc);
        return query(po);
    }

    /**
     * 根据应用简介获取指定字段的值
     *
     * @param desc 应用简介
     * @param fieldMapper 字段映射函数
     * @param <T> 字段类型
     * @return 字段值的 Optional
     */
    public <T> Optional<T> getFieldByDesc(String desc, Function<TbApps, T> fieldMapper) {
        if (StrUtil.isBlank(desc)) {
            return Optional.empty();
        }

        return Optional.ofNullable(getByDesc(desc)).map(fieldMapper);
   }

    /**
     * 根据应用图标查询单条数据
     *
     * @param img 应用图标
     * @return 查询结果
     */
    public TbApps getByImg(String img) {
        if (StrUtil.isBlank(img)) {
            return null;
        }

        TbAppsPo po = new TbAppsPo();
        po.setWhereImg(img);
        return get(po);
    }

    /**
     * 根据应用图标查询多条数据
     *
     * @param img 应用图标
     * @return 查询结果
     */
    public List<TbApps> queryByImg(String img) {
        if (StrUtil.isBlank(img)) {
            return null;
        }

        TbAppsPo po = new TbAppsPo();
        po.setWhereImg(img);
        return query(po);
    }

    /**
     * 根据应用图标获取指定字段的值
     *
     * @param img 应用图标
     * @param fieldMapper 字段映射函数
     * @param <T> 字段类型
     * @return 字段值的 Optional
     */
    public <T> Optional<T> getFieldByImg(String img, Function<TbApps, T> fieldMapper) {
        if (StrUtil.isBlank(img)) {
            return Optional.empty();
        }

        return Optional.ofNullable(getByImg(img)).map(fieldMapper);
   }

    /**
     * 根据状态。1：启用，2：禁用查询单条数据
     *
     * @param status 状态。1：启用，2：禁用
     * @return 查询结果
     */
    public TbApps getByStatus(Integer status) {
        if (ObjectUtil.isNull(status)) {
            return null;
        }

        TbAppsPo po = new TbAppsPo();
        po.setWhereStatus(status);
        return get(po);
    }

    /**
     * 根据状态。1：启用，2：禁用查询多条数据
     *
     * @param status 状态。1：启用，2：禁用
     * @return 查询结果
     */
    public List<TbApps> queryByStatus(Integer status) {
        if (ObjectUtil.isNull(status)) {
            return null;
        }

        TbAppsPo po = new TbAppsPo();
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
    public <T> Optional<T> getFieldByStatus(Integer status, Function<TbApps, T> fieldMapper) {
        if (ObjectUtil.isNull(status)) {
            return Optional.empty();
        }

        return Optional.ofNullable(getByStatus(status)).map(fieldMapper);
   }

    /**
     * 根据是否是入口小程序。1：是，2：否查询单条数据
     *
     * @param isEntranceMp 是否是入口小程序。1：是，2：否
     * @return 查询结果
     */
    public TbApps getByIsEntranceMp(Integer isEntranceMp) {
        if (ObjectUtil.isNull(isEntranceMp)) {
            return null;
        }

        TbAppsPo po = new TbAppsPo();
        po.setWhereIsEntranceMp(isEntranceMp);
        return get(po);
    }

    /**
     * 根据是否是入口小程序。1：是，2：否查询多条数据
     *
     * @param isEntranceMp 是否是入口小程序。1：是，2：否
     * @return 查询结果
     */
    public List<TbApps> queryByIsEntranceMp(Integer isEntranceMp) {
        if (ObjectUtil.isNull(isEntranceMp)) {
            return null;
        }

        TbAppsPo po = new TbAppsPo();
        po.setWhereIsEntranceMp(isEntranceMp);
        return query(po);
    }

    /**
     * 根据是否是入口小程序。1：是，2：否获取指定字段的值
     *
     * @param isEntranceMp 是否是入口小程序。1：是，2：否
     * @param fieldMapper 字段映射函数
     * @param <T> 字段类型
     * @return 字段值的 Optional
     */
    public <T> Optional<T> getFieldByIsEntranceMp(Integer isEntranceMp, Function<TbApps, T> fieldMapper) {
        if (ObjectUtil.isNull(isEntranceMp)) {
            return Optional.empty();
        }

        return Optional.ofNullable(getByIsEntranceMp(isEntranceMp)).map(fieldMapper);
   }

    /**
     * 根据分类ID查询单条数据
     *
     * @param categoryId 分类ID
     * @return 查询结果
     */
    public TbApps getByCategoryId(Integer categoryId) {
        if (ObjectUtil.isNull(categoryId)) {
            return null;
        }

        TbAppsPo po = new TbAppsPo();
        po.setWhereCategoryId(categoryId);
        return get(po);
    }

    /**
     * 根据分类ID查询多条数据
     *
     * @param categoryId 分类ID
     * @return 查询结果
     */
    public List<TbApps> queryByCategoryId(Integer categoryId) {
        if (ObjectUtil.isNull(categoryId)) {
            return null;
        }

        TbAppsPo po = new TbAppsPo();
        po.setWhereCategoryId(categoryId);
        return query(po);
    }

    /**
     * 根据分类ID获取指定字段的值
     *
     * @param categoryId 分类ID
     * @param fieldMapper 字段映射函数
     * @param <T> 字段类型
     * @return 字段值的 Optional
     */
    public <T> Optional<T> getFieldByCategoryId(Integer categoryId, Function<TbApps, T> fieldMapper) {
        if (ObjectUtil.isNull(categoryId)) {
            return Optional.empty();
        }

        return Optional.ofNullable(getByCategoryId(categoryId)).map(fieldMapper);
   }

    /**
     * 根据创建时间查询单条数据
     *
     * @param createdAt 创建时间
     * @return 查询结果
     */
    public TbApps getByCreatedAt(String createdAt) {
        if (StrUtil.isBlank(createdAt)) {
            return null;
        }

        TbAppsPo po = new TbAppsPo();
        po.setWhereCreatedAt(createdAt);
        return get(po);
    }

    /**
     * 根据创建时间查询多条数据
     *
     * @param createdAt 创建时间
     * @return 查询结果
     */
    public List<TbApps> queryByCreatedAt(String createdAt) {
        if (StrUtil.isBlank(createdAt)) {
            return null;
        }

        TbAppsPo po = new TbAppsPo();
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
    public <T> Optional<T> getFieldByCreatedAt(String createdAt, Function<TbApps, T> fieldMapper) {
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
    public TbApps getByCreatedUserId(Integer createdUserId) {
        if (ObjectUtil.isNull(createdUserId)) {
            return null;
        }

        TbAppsPo po = new TbAppsPo();
        po.setWhereCreatedUserId(createdUserId);
        return get(po);
    }

    /**
     * 根据创建者查询多条数据
     *
     * @param createdUserId 创建者
     * @return 查询结果
     */
    public List<TbApps> queryByCreatedUserId(Integer createdUserId) {
        if (ObjectUtil.isNull(createdUserId)) {
            return null;
        }

        TbAppsPo po = new TbAppsPo();
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
    public <T> Optional<T> getFieldByCreatedUserId(Integer createdUserId, Function<TbApps, T> fieldMapper) {
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
    public TbApps getByUpdatedAt(String updatedAt) {
        if (StrUtil.isBlank(updatedAt)) {
            return null;
        }

        TbAppsPo po = new TbAppsPo();
        po.setWhereUpdatedAt(updatedAt);
        return get(po);
    }

    /**
     * 根据更新时间查询多条数据
     *
     * @param updatedAt 更新时间
     * @return 查询结果
     */
    public List<TbApps> queryByUpdatedAt(String updatedAt) {
        if (StrUtil.isBlank(updatedAt)) {
            return null;
        }

        TbAppsPo po = new TbAppsPo();
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
    public <T> Optional<T> getFieldByUpdatedAt(String updatedAt, Function<TbApps, T> fieldMapper) {
        if (StrUtil.isBlank(updatedAt)) {
            return Optional.empty();
        }

        return Optional.ofNullable(getByUpdatedAt(updatedAt)).map(fieldMapper);
   }

    /**
     * 根据最后更新者查询单条数据
     *
     * @param updatedUserId 最后更新者
     * @return 查询结果
     */
    public TbApps getByUpdatedUserId(Integer updatedUserId) {
        if (ObjectUtil.isNull(updatedUserId)) {
            return null;
        }

        TbAppsPo po = new TbAppsPo();
        po.setWhereUpdatedUserId(updatedUserId);
        return get(po);
    }

    /**
     * 根据最后更新者查询多条数据
     *
     * @param updatedUserId 最后更新者
     * @return 查询结果
     */
    public List<TbApps> queryByUpdatedUserId(Integer updatedUserId) {
        if (ObjectUtil.isNull(updatedUserId)) {
            return null;
        }

        TbAppsPo po = new TbAppsPo();
        po.setWhereUpdatedUserId(updatedUserId);
        return query(po);
    }

    /**
     * 根据最后更新者获取指定字段的值
     *
     * @param updatedUserId 最后更新者
     * @param fieldMapper 字段映射函数
     * @param <T> 字段类型
     * @return 字段值的 Optional
     */
    public <T> Optional<T> getFieldByUpdatedUserId(Integer updatedUserId, Function<TbApps, T> fieldMapper) {
        if (ObjectUtil.isNull(updatedUserId)) {
            return Optional.empty();
        }

        return Optional.ofNullable(getByUpdatedUserId(updatedUserId)).map(fieldMapper);
   }

    //***************************************************获取 END********************************************************
}