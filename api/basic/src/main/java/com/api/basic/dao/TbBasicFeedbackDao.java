package com.api.basic.dao;

import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import com.api.basic.data.entity.TbBasicFeedback;
import com.api.basic.data.po.TbBasicFeedbackPo;
import com.api.basic.mapper.TbBasicFeedbackMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.*;
import java.util.function.Function;

/**
 * 问题反馈表Dao
 *
 * @author 裴金伟
 */
@Component
@RequiredArgsConstructor
public class TbBasicFeedbackDao {

    private final TbBasicFeedbackMapper mapper;

    //***************************************************添加 START******************************************************
    /**
     * 新增
     *
     * @param entity 新增信息
     * @return 新增ID
     */
    public Integer add(TbBasicFeedback entity) {
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
    public Integer delete(TbBasicFeedbackPo po) {
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
    public Integer update(TbBasicFeedbackPo po) {
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
    public List<TbBasicFeedback> query(TbBasicFeedbackPo po) {
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
    public Integer cnt(TbBasicFeedbackPo po) {
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
    public TbBasicFeedback get(TbBasicFeedbackPo po) {
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
    public TbBasicFeedback getById(Integer id) {
        if (ObjectUtil.isNull(id)) {
            return null;
        }

        TbBasicFeedbackPo po = new TbBasicFeedbackPo();
        po.setWhereId(id);
        return get(po);
    }

    /**
     * 根据ID查询多条数据
     *
     * @param id ID
     * @return 查询结果
     */
    public List<TbBasicFeedback> queryById(Integer id) {
        if (ObjectUtil.isNull(id)) {
            return null;
        }

        TbBasicFeedbackPo po = new TbBasicFeedbackPo();
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
    public <T> Optional<T> getFieldById(Integer id, Function<TbBasicFeedback, T> fieldMapper) {
        if (ObjectUtil.isNull(id)) {
            return Optional.empty();
        }

        return Optional.ofNullable(getById(id)).map(fieldMapper);
   }

    /**
     * 根据反馈类型。1：Bug反馈，2：功能建议，3：使用咨询，4：其他查询单条数据
     *
     * @param type 反馈类型。1：Bug反馈，2：功能建议，3：使用咨询，4：其他
     * @return 查询结果
     */
    public TbBasicFeedback getByType(Integer type) {
        if (ObjectUtil.isNull(type)) {
            return null;
        }

        TbBasicFeedbackPo po = new TbBasicFeedbackPo();
        po.setWhereType(type);
        return get(po);
    }

    /**
     * 根据反馈类型。1：Bug反馈，2：功能建议，3：使用咨询，4：其他查询多条数据
     *
     * @param type 反馈类型。1：Bug反馈，2：功能建议，3：使用咨询，4：其他
     * @return 查询结果
     */
    public List<TbBasicFeedback> queryByType(Integer type) {
        if (ObjectUtil.isNull(type)) {
            return null;
        }

        TbBasicFeedbackPo po = new TbBasicFeedbackPo();
        po.setWhereType(type);
        return query(po);
    }

    /**
     * 根据反馈类型。1：Bug反馈，2：功能建议，3：使用咨询，4：其他获取指定字段的值
     *
     * @param type 反馈类型。1：Bug反馈，2：功能建议，3：使用咨询，4：其他
     * @param fieldMapper 字段映射函数
     * @param <T> 字段类型
     * @return 字段值的 Optional
     */
    public <T> Optional<T> getFieldByType(Integer type, Function<TbBasicFeedback, T> fieldMapper) {
        if (ObjectUtil.isNull(type)) {
            return Optional.empty();
        }

        return Optional.ofNullable(getByType(type)).map(fieldMapper);
   }

    /**
     * 根据反馈内容查询单条数据
     *
     * @param content 反馈内容
     * @return 查询结果
     */
    public TbBasicFeedback getByContent(String content) {
        if (StrUtil.isBlank(content)) {
            return null;
        }

        TbBasicFeedbackPo po = new TbBasicFeedbackPo();
        po.setWhereContent(content);
        return get(po);
    }

    /**
     * 根据反馈内容查询多条数据
     *
     * @param content 反馈内容
     * @return 查询结果
     */
    public List<TbBasicFeedback> queryByContent(String content) {
        if (StrUtil.isBlank(content)) {
            return null;
        }

        TbBasicFeedbackPo po = new TbBasicFeedbackPo();
        po.setWhereContent(content);
        return query(po);
    }

    /**
     * 根据反馈内容获取指定字段的值
     *
     * @param content 反馈内容
     * @param fieldMapper 字段映射函数
     * @param <T> 字段类型
     * @return 字段值的 Optional
     */
    public <T> Optional<T> getFieldByContent(String content, Function<TbBasicFeedback, T> fieldMapper) {
        if (StrUtil.isBlank(content)) {
            return Optional.empty();
        }

        return Optional.ofNullable(getByContent(content)).map(fieldMapper);
   }

    /**
     * 根据反馈图片（JSON数组格式存储图片URL）查询单条数据
     *
     * @param images 反馈图片（JSON数组格式存储图片URL）
     * @return 查询结果
     */
    public TbBasicFeedback getByImages(String images) {
        if (StrUtil.isBlank(images)) {
            return null;
        }

        TbBasicFeedbackPo po = new TbBasicFeedbackPo();
        po.setWhereImages(images);
        return get(po);
    }

    /**
     * 根据反馈图片（JSON数组格式存储图片URL）查询多条数据
     *
     * @param images 反馈图片（JSON数组格式存储图片URL）
     * @return 查询结果
     */
    public List<TbBasicFeedback> queryByImages(String images) {
        if (StrUtil.isBlank(images)) {
            return null;
        }

        TbBasicFeedbackPo po = new TbBasicFeedbackPo();
        po.setWhereImages(images);
        return query(po);
    }

    /**
     * 根据反馈图片（JSON数组格式存储图片URL）获取指定字段的值
     *
     * @param images 反馈图片（JSON数组格式存储图片URL）
     * @param fieldMapper 字段映射函数
     * @param <T> 字段类型
     * @return 字段值的 Optional
     */
    public <T> Optional<T> getFieldByImages(String images, Function<TbBasicFeedback, T> fieldMapper) {
        if (StrUtil.isBlank(images)) {
            return Optional.empty();
        }

        return Optional.ofNullable(getByImages(images)).map(fieldMapper);
   }

    /**
     * 根据联系方式查询单条数据
     *
     * @param contact 联系方式
     * @return 查询结果
     */
    public TbBasicFeedback getByContact(String contact) {
        if (StrUtil.isBlank(contact)) {
            return null;
        }

        TbBasicFeedbackPo po = new TbBasicFeedbackPo();
        po.setWhereContact(contact);
        return get(po);
    }

    /**
     * 根据联系方式查询多条数据
     *
     * @param contact 联系方式
     * @return 查询结果
     */
    public List<TbBasicFeedback> queryByContact(String contact) {
        if (StrUtil.isBlank(contact)) {
            return null;
        }

        TbBasicFeedbackPo po = new TbBasicFeedbackPo();
        po.setWhereContact(contact);
        return query(po);
    }

    /**
     * 根据联系方式获取指定字段的值
     *
     * @param contact 联系方式
     * @param fieldMapper 字段映射函数
     * @param <T> 字段类型
     * @return 字段值的 Optional
     */
    public <T> Optional<T> getFieldByContact(String contact, Function<TbBasicFeedback, T> fieldMapper) {
        if (StrUtil.isBlank(contact)) {
            return Optional.empty();
        }

        return Optional.ofNullable(getByContact(contact)).map(fieldMapper);
   }

    /**
     * 根据处理状态。1：待处理，2：处理中，3：已解决，4：已拒绝查询单条数据
     *
     * @param status 处理状态。1：待处理，2：处理中，3：已解决，4：已拒绝
     * @return 查询结果
     */
    public TbBasicFeedback getByStatus(Integer status) {
        if (ObjectUtil.isNull(status)) {
            return null;
        }

        TbBasicFeedbackPo po = new TbBasicFeedbackPo();
        po.setWhereStatus(status);
        return get(po);
    }

    /**
     * 根据处理状态。1：待处理，2：处理中，3：已解决，4：已拒绝查询多条数据
     *
     * @param status 处理状态。1：待处理，2：处理中，3：已解决，4：已拒绝
     * @return 查询结果
     */
    public List<TbBasicFeedback> queryByStatus(Integer status) {
        if (ObjectUtil.isNull(status)) {
            return null;
        }

        TbBasicFeedbackPo po = new TbBasicFeedbackPo();
        po.setWhereStatus(status);
        return query(po);
    }

    /**
     * 根据处理状态。1：待处理，2：处理中，3：已解决，4：已拒绝获取指定字段的值
     *
     * @param status 处理状态。1：待处理，2：处理中，3：已解决，4：已拒绝
     * @param fieldMapper 字段映射函数
     * @param <T> 字段类型
     * @return 字段值的 Optional
     */
    public <T> Optional<T> getFieldByStatus(Integer status, Function<TbBasicFeedback, T> fieldMapper) {
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
    public TbBasicFeedback getByIsDelete(Integer isDelete) {
        if (ObjectUtil.isNull(isDelete)) {
            return null;
        }

        TbBasicFeedbackPo po = new TbBasicFeedbackPo();
        po.setWhereIsDelete(isDelete);
        return get(po);
    }

    /**
     * 根据是否已删除。1：未删除，2：已删除查询多条数据
     *
     * @param isDelete 是否已删除。1：未删除，2：已删除
     * @return 查询结果
     */
    public List<TbBasicFeedback> queryByIsDelete(Integer isDelete) {
        if (ObjectUtil.isNull(isDelete)) {
            return null;
        }

        TbBasicFeedbackPo po = new TbBasicFeedbackPo();
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
    public <T> Optional<T> getFieldByIsDelete(Integer isDelete, Function<TbBasicFeedback, T> fieldMapper) {
        if (ObjectUtil.isNull(isDelete)) {
            return Optional.empty();
        }

        return Optional.ofNullable(getByIsDelete(isDelete)).map(fieldMapper);
   }

    /**
     * 根据设备信息（JSON格式存储设备型号、系统版本等）查询单条数据
     *
     * @param deviceInfo 设备信息（JSON格式存储设备型号、系统版本等）
     * @return 查询结果
     */
    public TbBasicFeedback getByDeviceInfo(String deviceInfo) {
        if (StrUtil.isBlank(deviceInfo)) {
            return null;
        }

        TbBasicFeedbackPo po = new TbBasicFeedbackPo();
        po.setWhereDeviceInfo(deviceInfo);
        return get(po);
    }

    /**
     * 根据设备信息（JSON格式存储设备型号、系统版本等）查询多条数据
     *
     * @param deviceInfo 设备信息（JSON格式存储设备型号、系统版本等）
     * @return 查询结果
     */
    public List<TbBasicFeedback> queryByDeviceInfo(String deviceInfo) {
        if (StrUtil.isBlank(deviceInfo)) {
            return null;
        }

        TbBasicFeedbackPo po = new TbBasicFeedbackPo();
        po.setWhereDeviceInfo(deviceInfo);
        return query(po);
    }

    /**
     * 根据设备信息（JSON格式存储设备型号、系统版本等）获取指定字段的值
     *
     * @param deviceInfo 设备信息（JSON格式存储设备型号、系统版本等）
     * @param fieldMapper 字段映射函数
     * @param <T> 字段类型
     * @return 字段值的 Optional
     */
    public <T> Optional<T> getFieldByDeviceInfo(String deviceInfo, Function<TbBasicFeedback, T> fieldMapper) {
        if (StrUtil.isBlank(deviceInfo)) {
            return Optional.empty();
        }

        return Optional.ofNullable(getByDeviceInfo(deviceInfo)).map(fieldMapper);
   }

    /**
     * 根据应用版本号查询单条数据
     *
     * @param appVersion 应用版本号
     * @return 查询结果
     */
    public TbBasicFeedback getByAppVersion(String appVersion) {
        if (StrUtil.isBlank(appVersion)) {
            return null;
        }

        TbBasicFeedbackPo po = new TbBasicFeedbackPo();
        po.setWhereAppVersion(appVersion);
        return get(po);
    }

    /**
     * 根据应用版本号查询多条数据
     *
     * @param appVersion 应用版本号
     * @return 查询结果
     */
    public List<TbBasicFeedback> queryByAppVersion(String appVersion) {
        if (StrUtil.isBlank(appVersion)) {
            return null;
        }

        TbBasicFeedbackPo po = new TbBasicFeedbackPo();
        po.setWhereAppVersion(appVersion);
        return query(po);
    }

    /**
     * 根据应用版本号获取指定字段的值
     *
     * @param appVersion 应用版本号
     * @param fieldMapper 字段映射函数
     * @param <T> 字段类型
     * @return 字段值的 Optional
     */
    public <T> Optional<T> getFieldByAppVersion(String appVersion, Function<TbBasicFeedback, T> fieldMapper) {
        if (StrUtil.isBlank(appVersion)) {
            return Optional.empty();
        }

        return Optional.ofNullable(getByAppVersion(appVersion)).map(fieldMapper);
   }

    /**
     * 根据回复内容查询单条数据
     *
     * @param replyContent 回复内容
     * @return 查询结果
     */
    public TbBasicFeedback getByReplyContent(String replyContent) {
        if (StrUtil.isBlank(replyContent)) {
            return null;
        }

        TbBasicFeedbackPo po = new TbBasicFeedbackPo();
        po.setWhereReplyContent(replyContent);
        return get(po);
    }

    /**
     * 根据回复内容查询多条数据
     *
     * @param replyContent 回复内容
     * @return 查询结果
     */
    public List<TbBasicFeedback> queryByReplyContent(String replyContent) {
        if (StrUtil.isBlank(replyContent)) {
            return null;
        }

        TbBasicFeedbackPo po = new TbBasicFeedbackPo();
        po.setWhereReplyContent(replyContent);
        return query(po);
    }

    /**
     * 根据回复内容获取指定字段的值
     *
     * @param replyContent 回复内容
     * @param fieldMapper 字段映射函数
     * @param <T> 字段类型
     * @return 字段值的 Optional
     */
    public <T> Optional<T> getFieldByReplyContent(String replyContent, Function<TbBasicFeedback, T> fieldMapper) {
        if (StrUtil.isBlank(replyContent)) {
            return Optional.empty();
        }

        return Optional.ofNullable(getByReplyContent(replyContent)).map(fieldMapper);
   }

    /**
     * 根据回复时间查询单条数据
     *
     * @param replyAt 回复时间
     * @return 查询结果
     */
    public TbBasicFeedback getByReplyAt(String replyAt) {
        if (StrUtil.isBlank(replyAt)) {
            return null;
        }

        TbBasicFeedbackPo po = new TbBasicFeedbackPo();
        po.setWhereReplyAt(replyAt);
        return get(po);
    }

    /**
     * 根据回复时间查询多条数据
     *
     * @param replyAt 回复时间
     * @return 查询结果
     */
    public List<TbBasicFeedback> queryByReplyAt(String replyAt) {
        if (StrUtil.isBlank(replyAt)) {
            return null;
        }

        TbBasicFeedbackPo po = new TbBasicFeedbackPo();
        po.setWhereReplyAt(replyAt);
        return query(po);
    }

    /**
     * 根据回复时间获取指定字段的值
     *
     * @param replyAt 回复时间
     * @param fieldMapper 字段映射函数
     * @param <T> 字段类型
     * @return 字段值的 Optional
     */
    public <T> Optional<T> getFieldByReplyAt(String replyAt, Function<TbBasicFeedback, T> fieldMapper) {
        if (StrUtil.isBlank(replyAt)) {
            return Optional.empty();
        }

        return Optional.ofNullable(getByReplyAt(replyAt)).map(fieldMapper);
   }

    /**
     * 根据创建时间查询单条数据
     *
     * @param createdAt 创建时间
     * @return 查询结果
     */
    public TbBasicFeedback getByCreatedAt(String createdAt) {
        if (StrUtil.isBlank(createdAt)) {
            return null;
        }

        TbBasicFeedbackPo po = new TbBasicFeedbackPo();
        po.setWhereCreatedAt(createdAt);
        return get(po);
    }

    /**
     * 根据创建时间查询多条数据
     *
     * @param createdAt 创建时间
     * @return 查询结果
     */
    public List<TbBasicFeedback> queryByCreatedAt(String createdAt) {
        if (StrUtil.isBlank(createdAt)) {
            return null;
        }

        TbBasicFeedbackPo po = new TbBasicFeedbackPo();
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
    public <T> Optional<T> getFieldByCreatedAt(String createdAt, Function<TbBasicFeedback, T> fieldMapper) {
        if (StrUtil.isBlank(createdAt)) {
            return Optional.empty();
        }

        return Optional.ofNullable(getByCreatedAt(createdAt)).map(fieldMapper);
   }

    /**
     * 根据创建者用户ID（可为空，允许匿名反馈）查询单条数据
     *
     * @param createdUserId 创建者用户ID（可为空，允许匿名反馈）
     * @return 查询结果
     */
    public TbBasicFeedback getByCreatedUserId(Integer createdUserId) {
        if (ObjectUtil.isNull(createdUserId)) {
            return null;
        }

        TbBasicFeedbackPo po = new TbBasicFeedbackPo();
        po.setWhereCreatedUserId(createdUserId);
        return get(po);
    }

    /**
     * 根据创建者用户ID（可为空，允许匿名反馈）查询多条数据
     *
     * @param createdUserId 创建者用户ID（可为空，允许匿名反馈）
     * @return 查询结果
     */
    public List<TbBasicFeedback> queryByCreatedUserId(Integer createdUserId) {
        if (ObjectUtil.isNull(createdUserId)) {
            return null;
        }

        TbBasicFeedbackPo po = new TbBasicFeedbackPo();
        po.setWhereCreatedUserId(createdUserId);
        return query(po);
    }

    /**
     * 根据创建者用户ID（可为空，允许匿名反馈）获取指定字段的值
     *
     * @param createdUserId 创建者用户ID（可为空，允许匿名反馈）
     * @param fieldMapper 字段映射函数
     * @param <T> 字段类型
     * @return 字段值的 Optional
     */
    public <T> Optional<T> getFieldByCreatedUserId(Integer createdUserId, Function<TbBasicFeedback, T> fieldMapper) {
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
    public TbBasicFeedback getByUpdatedAt(String updatedAt) {
        if (StrUtil.isBlank(updatedAt)) {
            return null;
        }

        TbBasicFeedbackPo po = new TbBasicFeedbackPo();
        po.setWhereUpdatedAt(updatedAt);
        return get(po);
    }

    /**
     * 根据更新时间查询多条数据
     *
     * @param updatedAt 更新时间
     * @return 查询结果
     */
    public List<TbBasicFeedback> queryByUpdatedAt(String updatedAt) {
        if (StrUtil.isBlank(updatedAt)) {
            return null;
        }

        TbBasicFeedbackPo po = new TbBasicFeedbackPo();
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
    public <T> Optional<T> getFieldByUpdatedAt(String updatedAt, Function<TbBasicFeedback, T> fieldMapper) {
        if (StrUtil.isBlank(updatedAt)) {
            return Optional.empty();
        }

        return Optional.ofNullable(getByUpdatedAt(updatedAt)).map(fieldMapper);
   }

    /**
     * 根据更新者用户ID查询单条数据
     *
     * @param updatedUserId 更新者用户ID
     * @return 查询结果
     */
    public TbBasicFeedback getByUpdatedUserId(Integer updatedUserId) {
        if (ObjectUtil.isNull(updatedUserId)) {
            return null;
        }

        TbBasicFeedbackPo po = new TbBasicFeedbackPo();
        po.setWhereUpdatedUserId(updatedUserId);
        return get(po);
    }

    /**
     * 根据更新者用户ID查询多条数据
     *
     * @param updatedUserId 更新者用户ID
     * @return 查询结果
     */
    public List<TbBasicFeedback> queryByUpdatedUserId(Integer updatedUserId) {
        if (ObjectUtil.isNull(updatedUserId)) {
            return null;
        }

        TbBasicFeedbackPo po = new TbBasicFeedbackPo();
        po.setWhereUpdatedUserId(updatedUserId);
        return query(po);
    }

    /**
     * 根据更新者用户ID获取指定字段的值
     *
     * @param updatedUserId 更新者用户ID
     * @param fieldMapper 字段映射函数
     * @param <T> 字段类型
     * @return 字段值的 Optional
     */
    public <T> Optional<T> getFieldByUpdatedUserId(Integer updatedUserId, Function<TbBasicFeedback, T> fieldMapper) {
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
    public BigDecimal sumId(TbBasicFeedbackPo po) {
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
    public BigDecimal maxId(TbBasicFeedbackPo po) {
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
    public BigDecimal minId(TbBasicFeedbackPo po) {
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
    public BigDecimal avgId(TbBasicFeedbackPo po) {
        if (po == null) {
            return BigDecimal.ZERO;
        }
        return mapper.avgId(po);
    }
    /**
     * 统计反馈类型。1：Bug反馈，2：功能建议，3：使用咨询，4：其他的总和
     *
     * @param po 查询条件
     * @return 总和结果
     */
    public BigDecimal sumType(TbBasicFeedbackPo po) {
        if (po == null) {
            return BigDecimal.ZERO;
        }
        return mapper.sumType(po);
    }

    /**
     * 统计反馈类型。1：Bug反馈，2：功能建议，3：使用咨询，4：其他的最大值
     *
     * @param po 查询条件
     * @return 最大值结果
     */
    public BigDecimal maxType(TbBasicFeedbackPo po) {
        if (po == null) {
            return BigDecimal.ZERO;
        }
        return mapper.maxType(po);
    }

    /**
     * 统计反馈类型。1：Bug反馈，2：功能建议，3：使用咨询，4：其他的最小值
     *
     * @param po 查询条件
     * @return 最小值结果
     */
    public BigDecimal minType(TbBasicFeedbackPo po) {
        if (po == null) {
            return BigDecimal.ZERO;
        }
        return mapper.minType(po);
    }

    /**
     * 统计反馈类型。1：Bug反馈，2：功能建议，3：使用咨询，4：其他的平均值
     *
     * @param po 查询条件
     * @return 平均值结果
     */
    public BigDecimal avgType(TbBasicFeedbackPo po) {
        if (po == null) {
            return BigDecimal.ZERO;
        }
        return mapper.avgType(po);
    }
    /**
     * 统计处理状态。1：待处理，2：处理中，3：已解决，4：已拒绝的总和
     *
     * @param po 查询条件
     * @return 总和结果
     */
    public BigDecimal sumStatus(TbBasicFeedbackPo po) {
        if (po == null) {
            return BigDecimal.ZERO;
        }
        return mapper.sumStatus(po);
    }

    /**
     * 统计处理状态。1：待处理，2：处理中，3：已解决，4：已拒绝的最大值
     *
     * @param po 查询条件
     * @return 最大值结果
     */
    public BigDecimal maxStatus(TbBasicFeedbackPo po) {
        if (po == null) {
            return BigDecimal.ZERO;
        }
        return mapper.maxStatus(po);
    }

    /**
     * 统计处理状态。1：待处理，2：处理中，3：已解决，4：已拒绝的最小值
     *
     * @param po 查询条件
     * @return 最小值结果
     */
    public BigDecimal minStatus(TbBasicFeedbackPo po) {
        if (po == null) {
            return BigDecimal.ZERO;
        }
        return mapper.minStatus(po);
    }

    /**
     * 统计处理状态。1：待处理，2：处理中，3：已解决，4：已拒绝的平均值
     *
     * @param po 查询条件
     * @return 平均值结果
     */
    public BigDecimal avgStatus(TbBasicFeedbackPo po) {
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
    public BigDecimal sumIsDelete(TbBasicFeedbackPo po) {
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
    public BigDecimal maxIsDelete(TbBasicFeedbackPo po) {
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
    public BigDecimal minIsDelete(TbBasicFeedbackPo po) {
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
    public BigDecimal avgIsDelete(TbBasicFeedbackPo po) {
        if (po == null) {
            return BigDecimal.ZERO;
        }
        return mapper.avgIsDelete(po);
    }
    /**
     * 统计创建者用户ID（可为空，允许匿名反馈）的总和
     *
     * @param po 查询条件
     * @return 总和结果
     */
    public BigDecimal sumCreatedUserId(TbBasicFeedbackPo po) {
        if (po == null) {
            return BigDecimal.ZERO;
        }
        return mapper.sumCreatedUserId(po);
    }

    /**
     * 统计创建者用户ID（可为空，允许匿名反馈）的最大值
     *
     * @param po 查询条件
     * @return 最大值结果
     */
    public BigDecimal maxCreatedUserId(TbBasicFeedbackPo po) {
        if (po == null) {
            return BigDecimal.ZERO;
        }
        return mapper.maxCreatedUserId(po);
    }

    /**
     * 统计创建者用户ID（可为空，允许匿名反馈）的最小值
     *
     * @param po 查询条件
     * @return 最小值结果
     */
    public BigDecimal minCreatedUserId(TbBasicFeedbackPo po) {
        if (po == null) {
            return BigDecimal.ZERO;
        }
        return mapper.minCreatedUserId(po);
    }

    /**
     * 统计创建者用户ID（可为空，允许匿名反馈）的平均值
     *
     * @param po 查询条件
     * @return 平均值结果
     */
    public BigDecimal avgCreatedUserId(TbBasicFeedbackPo po) {
        if (po == null) {
            return BigDecimal.ZERO;
        }
        return mapper.avgCreatedUserId(po);
    }
    /**
     * 统计更新者用户ID的总和
     *
     * @param po 查询条件
     * @return 总和结果
     */
    public BigDecimal sumUpdatedUserId(TbBasicFeedbackPo po) {
        if (po == null) {
            return BigDecimal.ZERO;
        }
        return mapper.sumUpdatedUserId(po);
    }

    /**
     * 统计更新者用户ID的最大值
     *
     * @param po 查询条件
     * @return 最大值结果
     */
    public BigDecimal maxUpdatedUserId(TbBasicFeedbackPo po) {
        if (po == null) {
            return BigDecimal.ZERO;
        }
        return mapper.maxUpdatedUserId(po);
    }

    /**
     * 统计更新者用户ID的最小值
     *
     * @param po 查询条件
     * @return 最小值结果
     */
    public BigDecimal minUpdatedUserId(TbBasicFeedbackPo po) {
        if (po == null) {
            return BigDecimal.ZERO;
        }
        return mapper.minUpdatedUserId(po);
    }

    /**
     * 统计更新者用户ID的平均值
     *
     * @param po 查询条件
     * @return 平均值结果
     */
    public BigDecimal avgUpdatedUserId(TbBasicFeedbackPo po) {
        if (po == null) {
            return BigDecimal.ZERO;
        }
        return mapper.avgUpdatedUserId(po);
    }
    //***************************************************聚合操作 END********************************************************
}