package com.api.apps.dao;

import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import com.api.apps.data.entity.TbAppsVersion;
import com.api.apps.data.po.TbAppsVersionPo;
import com.api.apps.mapper.TbAppsVersionMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;

/**
 * 应用版本信息表Dao
 *
 * @author 裴金伟
 */
@Component
@RequiredArgsConstructor
public class TbAppsVersionDao {

    private final TbAppsVersionMapper mapper;

    //***************************************************添加 START******************************************************
    /**
     * 新增
     *
     * @param entity 新增信息
     * @return 新增ID
     */
    public Integer add(TbAppsVersion entity) {
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
    public Integer delete(TbAppsVersionPo po) {
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
    public Integer update(TbAppsVersionPo po) {
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
    public List<TbAppsVersion> query(TbAppsVersionPo po) {
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
    public Integer cnt(TbAppsVersionPo po) {
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
    public TbAppsVersion get(TbAppsVersionPo po) {
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
    public TbAppsVersion getById(Integer id) {
        if (ObjectUtil.isNull(id)) {
            return null;
        }

        TbAppsVersionPo po = new TbAppsVersionPo();
        po.setWhereId(id);
        return get(po);
    }

    /**
     * 根据查询多条数据
     *
     * @param id 
     * @return 查询结果
     */
    public List<TbAppsVersion> queryById(Integer id) {
        if (ObjectUtil.isNull(id)) {
            return null;
        }

        TbAppsVersionPo po = new TbAppsVersionPo();
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
    public <T> Optional<T> getFieldById(Integer id, Function<TbAppsVersion, T> fieldMapper) {
        if (ObjectUtil.isNull(id)) {
            return Optional.empty();
        }

        return Optional.ofNullable(getById(id)).map(fieldMapper);
   }

    /**
     * 根据应用ID查询单条数据
     *
     * @param appId 应用ID
     * @return 查询结果
     */
    public TbAppsVersion getByAppId(String appId) {
        if (StrUtil.isBlank(appId)) {
            return null;
        }

        TbAppsVersionPo po = new TbAppsVersionPo();
        po.setWhereAppId(appId);
        return get(po);
    }

    /**
     * 根据应用ID查询多条数据
     *
     * @param appId 应用ID
     * @return 查询结果
     */
    public List<TbAppsVersion> queryByAppId(String appId) {
        if (StrUtil.isBlank(appId)) {
            return null;
        }

        TbAppsVersionPo po = new TbAppsVersionPo();
        po.setWhereAppId(appId);
        return query(po);
    }

    /**
     * 根据应用ID获取指定字段的值
     *
     * @param appId 应用ID
     * @param fieldMapper 字段映射函数
     * @param <T> 字段类型
     * @return 字段值的 Optional
     */
    public <T> Optional<T> getFieldByAppId(String appId, Function<TbAppsVersion, T> fieldMapper) {
        if (StrUtil.isBlank(appId)) {
            return Optional.empty();
        }

        return Optional.ofNullable(getByAppId(appId)).map(fieldMapper);
   }

    /**
     * 根据版本号查询单条数据
     *
     * @param versionCode 版本号
     * @return 查询结果
     */
    public TbAppsVersion getByVersionCode(Integer versionCode) {
        if (ObjectUtil.isNull(versionCode)) {
            return null;
        }

        TbAppsVersionPo po = new TbAppsVersionPo();
        po.setWhereVersionCode(versionCode);
        return get(po);
    }

    /**
     * 根据版本号查询多条数据
     *
     * @param versionCode 版本号
     * @return 查询结果
     */
    public List<TbAppsVersion> queryByVersionCode(Integer versionCode) {
        if (ObjectUtil.isNull(versionCode)) {
            return null;
        }

        TbAppsVersionPo po = new TbAppsVersionPo();
        po.setWhereVersionCode(versionCode);
        return query(po);
    }

    /**
     * 根据版本号获取指定字段的值
     *
     * @param versionCode 版本号
     * @param fieldMapper 字段映射函数
     * @param <T> 字段类型
     * @return 字段值的 Optional
     */
    public <T> Optional<T> getFieldByVersionCode(Integer versionCode, Function<TbAppsVersion, T> fieldMapper) {
        if (ObjectUtil.isNull(versionCode)) {
            return Optional.empty();
        }

        return Optional.ofNullable(getByVersionCode(versionCode)).map(fieldMapper);
   }

    /**
     * 根据版本名称查询单条数据
     *
     * @param versionName 版本名称
     * @return 查询结果
     */
    public TbAppsVersion getByVersionName(String versionName) {
        if (StrUtil.isBlank(versionName)) {
            return null;
        }

        TbAppsVersionPo po = new TbAppsVersionPo();
        po.setWhereVersionName(versionName);
        return get(po);
    }

    /**
     * 根据版本名称查询多条数据
     *
     * @param versionName 版本名称
     * @return 查询结果
     */
    public List<TbAppsVersion> queryByVersionName(String versionName) {
        if (StrUtil.isBlank(versionName)) {
            return null;
        }

        TbAppsVersionPo po = new TbAppsVersionPo();
        po.setWhereVersionName(versionName);
        return query(po);
    }

    /**
     * 根据版本名称获取指定字段的值
     *
     * @param versionName 版本名称
     * @param fieldMapper 字段映射函数
     * @param <T> 字段类型
     * @return 字段值的 Optional
     */
    public <T> Optional<T> getFieldByVersionName(String versionName, Function<TbAppsVersion, T> fieldMapper) {
        if (StrUtil.isBlank(versionName)) {
            return Optional.empty();
        }

        return Optional.ofNullable(getByVersionName(versionName)).map(fieldMapper);
   }

    /**
     * 根据版本描述查询单条数据
     *
     * @param desc 版本描述
     * @return 查询结果
     */
    public TbAppsVersion getByDesc(String desc) {
        if (StrUtil.isBlank(desc)) {
            return null;
        }

        TbAppsVersionPo po = new TbAppsVersionPo();
        po.setWhereDesc(desc);
        return get(po);
    }

    /**
     * 根据版本描述查询多条数据
     *
     * @param desc 版本描述
     * @return 查询结果
     */
    public List<TbAppsVersion> queryByDesc(String desc) {
        if (StrUtil.isBlank(desc)) {
            return null;
        }

        TbAppsVersionPo po = new TbAppsVersionPo();
        po.setWhereDesc(desc);
        return query(po);
    }

    /**
     * 根据版本描述获取指定字段的值
     *
     * @param desc 版本描述
     * @param fieldMapper 字段映射函数
     * @param <T> 字段类型
     * @return 字段值的 Optional
     */
    public <T> Optional<T> getFieldByDesc(String desc, Function<TbAppsVersion, T> fieldMapper) {
        if (StrUtil.isBlank(desc)) {
            return Optional.empty();
        }

        return Optional.ofNullable(getByDesc(desc)).map(fieldMapper);
   }

    /**
     * 根据状态。1：下线，2：测试，3：正式上线。查询单条数据
     *
     * @param status 状态。1：下线，2：测试，3：正式上线。
     * @return 查询结果
     */
    public TbAppsVersion getByStatus(Integer status) {
        if (ObjectUtil.isNull(status)) {
            return null;
        }

        TbAppsVersionPo po = new TbAppsVersionPo();
        po.setWhereStatus(status);
        return get(po);
    }

    /**
     * 根据状态。1：下线，2：测试，3：正式上线。查询多条数据
     *
     * @param status 状态。1：下线，2：测试，3：正式上线。
     * @return 查询结果
     */
    public List<TbAppsVersion> queryByStatus(Integer status) {
        if (ObjectUtil.isNull(status)) {
            return null;
        }

        TbAppsVersionPo po = new TbAppsVersionPo();
        po.setWhereStatus(status);
        return query(po);
    }

    /**
     * 根据状态。1：下线，2：测试，3：正式上线。获取指定字段的值
     *
     * @param status 状态。1：下线，2：测试，3：正式上线。
     * @param fieldMapper 字段映射函数
     * @param <T> 字段类型
     * @return 字段值的 Optional
     */
    public <T> Optional<T> getFieldByStatus(Integer status, Function<TbAppsVersion, T> fieldMapper) {
        if (ObjectUtil.isNull(status)) {
            return Optional.empty();
        }

        return Optional.ofNullable(getByStatus(status)).map(fieldMapper);
   }

    /**
     * 根据下载地址查询单条数据
     *
     * @param url 下载地址
     * @return 查询结果
     */
    public TbAppsVersion getByUrl(String url) {
        if (StrUtil.isBlank(url)) {
            return null;
        }

        TbAppsVersionPo po = new TbAppsVersionPo();
        po.setWhereUrl(url);
        return get(po);
    }

    /**
     * 根据下载地址查询多条数据
     *
     * @param url 下载地址
     * @return 查询结果
     */
    public List<TbAppsVersion> queryByUrl(String url) {
        if (StrUtil.isBlank(url)) {
            return null;
        }

        TbAppsVersionPo po = new TbAppsVersionPo();
        po.setWhereUrl(url);
        return query(po);
    }

    /**
     * 根据下载地址获取指定字段的值
     *
     * @param url 下载地址
     * @param fieldMapper 字段映射函数
     * @param <T> 字段类型
     * @return 字段值的 Optional
     */
    public <T> Optional<T> getFieldByUrl(String url, Function<TbAppsVersion, T> fieldMapper) {
        if (StrUtil.isBlank(url)) {
            return Optional.empty();
        }

        return Optional.ofNullable(getByUrl(url)).map(fieldMapper);
   }

    /**
     * 根据创建时间查询单条数据
     *
     * @param createdAt 创建时间
     * @return 查询结果
     */
    public TbAppsVersion getByCreatedAt(String createdAt) {
        if (StrUtil.isBlank(createdAt)) {
            return null;
        }

        TbAppsVersionPo po = new TbAppsVersionPo();
        po.setWhereCreatedAt(createdAt);
        return get(po);
    }

    /**
     * 根据创建时间查询多条数据
     *
     * @param createdAt 创建时间
     * @return 查询结果
     */
    public List<TbAppsVersion> queryByCreatedAt(String createdAt) {
        if (StrUtil.isBlank(createdAt)) {
            return null;
        }

        TbAppsVersionPo po = new TbAppsVersionPo();
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
    public <T> Optional<T> getFieldByCreatedAt(String createdAt, Function<TbAppsVersion, T> fieldMapper) {
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
    public TbAppsVersion getByCreatedUserId(Integer createdUserId) {
        if (ObjectUtil.isNull(createdUserId)) {
            return null;
        }

        TbAppsVersionPo po = new TbAppsVersionPo();
        po.setWhereCreatedUserId(createdUserId);
        return get(po);
    }

    /**
     * 根据创建者查询多条数据
     *
     * @param createdUserId 创建者
     * @return 查询结果
     */
    public List<TbAppsVersion> queryByCreatedUserId(Integer createdUserId) {
        if (ObjectUtil.isNull(createdUserId)) {
            return null;
        }

        TbAppsVersionPo po = new TbAppsVersionPo();
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
    public <T> Optional<T> getFieldByCreatedUserId(Integer createdUserId, Function<TbAppsVersion, T> fieldMapper) {
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
    public TbAppsVersion getByUpdatedAt(String updatedAt) {
        if (StrUtil.isBlank(updatedAt)) {
            return null;
        }

        TbAppsVersionPo po = new TbAppsVersionPo();
        po.setWhereUpdatedAt(updatedAt);
        return get(po);
    }

    /**
     * 根据更新时间查询多条数据
     *
     * @param updatedAt 更新时间
     * @return 查询结果
     */
    public List<TbAppsVersion> queryByUpdatedAt(String updatedAt) {
        if (StrUtil.isBlank(updatedAt)) {
            return null;
        }

        TbAppsVersionPo po = new TbAppsVersionPo();
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
    public <T> Optional<T> getFieldByUpdatedAt(String updatedAt, Function<TbAppsVersion, T> fieldMapper) {
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
    public TbAppsVersion getByUpdatedUserId(Integer updatedUserId) {
        if (ObjectUtil.isNull(updatedUserId)) {
            return null;
        }

        TbAppsVersionPo po = new TbAppsVersionPo();
        po.setWhereUpdatedUserId(updatedUserId);
        return get(po);
    }

    /**
     * 根据最后更新者查询多条数据
     *
     * @param updatedUserId 最后更新者
     * @return 查询结果
     */
    public List<TbAppsVersion> queryByUpdatedUserId(Integer updatedUserId) {
        if (ObjectUtil.isNull(updatedUserId)) {
            return null;
        }

        TbAppsVersionPo po = new TbAppsVersionPo();
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
    public <T> Optional<T> getFieldByUpdatedUserId(Integer updatedUserId, Function<TbAppsVersion, T> fieldMapper) {
        if (ObjectUtil.isNull(updatedUserId)) {
            return Optional.empty();
        }

        return Optional.ofNullable(getByUpdatedUserId(updatedUserId)).map(fieldMapper);
   }

    //***************************************************获取 END********************************************************
}