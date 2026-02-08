package com.api.basic.dao;

import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import com.api.basic.data.entity.TbBasicSysUser;
import com.api.basic.data.po.TbBasicSysUserPo;
import com.api.basic.mapper.TbBasicSysUserMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.*;
import java.util.function.Function;

/**
 * 用户登录信息表Dao
 *
 * @author 裴金伟
 */
@Component
@RequiredArgsConstructor
public class TbBasicSysUserDao {

    private final TbBasicSysUserMapper mapper;

    //***************************************************添加 START******************************************************
    /**
     * 新增
     *
     * @param entity 新增信息
     * @return 新增ID
     */
    public Integer add(TbBasicSysUser entity) {
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
    public Integer delete(TbBasicSysUserPo po) {
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
    public Integer update(TbBasicSysUserPo po) {
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
    public List<TbBasicSysUser> query(TbBasicSysUserPo po) {
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
    public Integer cnt(TbBasicSysUserPo po) {
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
    public TbBasicSysUser get(TbBasicSysUserPo po) {
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
    public TbBasicSysUser getById(Integer id) {
        if (ObjectUtil.isNull(id)) {
            return null;
        }

        TbBasicSysUserPo po = new TbBasicSysUserPo();
        po.setWhereId(id);
        return get(po);
    }

    /**
     * 根据ID查询多条数据
     *
     * @param id ID
     * @return 查询结果
     */
    public List<TbBasicSysUser> queryById(Integer id) {
        if (ObjectUtil.isNull(id)) {
            return null;
        }

        TbBasicSysUserPo po = new TbBasicSysUserPo();
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
    public <T> Optional<T> getFieldById(Integer id, Function<TbBasicSysUser, T> fieldMapper) {
        if (ObjectUtil.isNull(id)) {
            return Optional.empty();
        }

        return Optional.ofNullable(getById(id)).map(fieldMapper);
   }

    /**
     * 根据用户名查询单条数据
     *
     * @param username 用户名
     * @return 查询结果
     */
    public TbBasicSysUser getByUsername(String username) {
        if (StrUtil.isBlank(username)) {
            return null;
        }

        TbBasicSysUserPo po = new TbBasicSysUserPo();
        po.setWhereUsername(username);
        return get(po);
    }

    /**
     * 根据用户名查询多条数据
     *
     * @param username 用户名
     * @return 查询结果
     */
    public List<TbBasicSysUser> queryByUsername(String username) {
        if (StrUtil.isBlank(username)) {
            return null;
        }

        TbBasicSysUserPo po = new TbBasicSysUserPo();
        po.setWhereUsername(username);
        return query(po);
    }

    /**
     * 根据用户名获取指定字段的值
     *
     * @param username 用户名
     * @param fieldMapper 字段映射函数
     * @param <T> 字段类型
     * @return 字段值的 Optional
     */
    public <T> Optional<T> getFieldByUsername(String username, Function<TbBasicSysUser, T> fieldMapper) {
        if (StrUtil.isBlank(username)) {
            return Optional.empty();
        }

        return Optional.ofNullable(getByUsername(username)).map(fieldMapper);
   }

    /**
     * 根据密码查询单条数据
     *
     * @param password 密码
     * @return 查询结果
     */
    public TbBasicSysUser getByPassword(String password) {
        if (StrUtil.isBlank(password)) {
            return null;
        }

        TbBasicSysUserPo po = new TbBasicSysUserPo();
        po.setWherePassword(password);
        return get(po);
    }

    /**
     * 根据密码查询多条数据
     *
     * @param password 密码
     * @return 查询结果
     */
    public List<TbBasicSysUser> queryByPassword(String password) {
        if (StrUtil.isBlank(password)) {
            return null;
        }

        TbBasicSysUserPo po = new TbBasicSysUserPo();
        po.setWherePassword(password);
        return query(po);
    }

    /**
     * 根据密码获取指定字段的值
     *
     * @param password 密码
     * @param fieldMapper 字段映射函数
     * @param <T> 字段类型
     * @return 字段值的 Optional
     */
    public <T> Optional<T> getFieldByPassword(String password, Function<TbBasicSysUser, T> fieldMapper) {
        if (StrUtil.isBlank(password)) {
            return Optional.empty();
        }

        return Optional.ofNullable(getByPassword(password)).map(fieldMapper);
   }

    /**
     * 根据手机号查询单条数据
     *
     * @param phoneNumber 手机号
     * @return 查询结果
     */
    public TbBasicSysUser getByPhoneNumber(String phoneNumber) {
        if (StrUtil.isBlank(phoneNumber)) {
            return null;
        }

        TbBasicSysUserPo po = new TbBasicSysUserPo();
        po.setWherePhoneNumber(phoneNumber);
        return get(po);
    }

    /**
     * 根据手机号查询多条数据
     *
     * @param phoneNumber 手机号
     * @return 查询结果
     */
    public List<TbBasicSysUser> queryByPhoneNumber(String phoneNumber) {
        if (StrUtil.isBlank(phoneNumber)) {
            return null;
        }

        TbBasicSysUserPo po = new TbBasicSysUserPo();
        po.setWherePhoneNumber(phoneNumber);
        return query(po);
    }

    /**
     * 根据手机号获取指定字段的值
     *
     * @param phoneNumber 手机号
     * @param fieldMapper 字段映射函数
     * @param <T> 字段类型
     * @return 字段值的 Optional
     */
    public <T> Optional<T> getFieldByPhoneNumber(String phoneNumber, Function<TbBasicSysUser, T> fieldMapper) {
        if (StrUtil.isBlank(phoneNumber)) {
            return Optional.empty();
        }

        return Optional.ofNullable(getByPhoneNumber(phoneNumber)).map(fieldMapper);
   }

    /**
     * 根据微信unionid查询单条数据
     *
     * @param wechatUnionid 微信unionid
     * @return 查询结果
     */
    public TbBasicSysUser getByWechatUnionid(String wechatUnionid) {
        if (StrUtil.isBlank(wechatUnionid)) {
            return null;
        }

        TbBasicSysUserPo po = new TbBasicSysUserPo();
        po.setWhereWechatUnionid(wechatUnionid);
        return get(po);
    }

    /**
     * 根据微信unionid查询多条数据
     *
     * @param wechatUnionid 微信unionid
     * @return 查询结果
     */
    public List<TbBasicSysUser> queryByWechatUnionid(String wechatUnionid) {
        if (StrUtil.isBlank(wechatUnionid)) {
            return null;
        }

        TbBasicSysUserPo po = new TbBasicSysUserPo();
        po.setWhereWechatUnionid(wechatUnionid);
        return query(po);
    }

    /**
     * 根据微信unionid获取指定字段的值
     *
     * @param wechatUnionid 微信unionid
     * @param fieldMapper 字段映射函数
     * @param <T> 字段类型
     * @return 字段值的 Optional
     */
    public <T> Optional<T> getFieldByWechatUnionid(String wechatUnionid, Function<TbBasicSysUser, T> fieldMapper) {
        if (StrUtil.isBlank(wechatUnionid)) {
            return Optional.empty();
        }

        return Optional.ofNullable(getByWechatUnionid(wechatUnionid)).map(fieldMapper);
   }

    /**
     * 根据微信小程序openid查询单条数据
     *
     * @param wechatMpAccountOpenid 微信小程序openid
     * @return 查询结果
     */
    public TbBasicSysUser getByWechatMpAccountOpenid(String wechatMpAccountOpenid) {
        if (StrUtil.isBlank(wechatMpAccountOpenid)) {
            return null;
        }

        TbBasicSysUserPo po = new TbBasicSysUserPo();
        po.setWhereWechatMpAccountOpenid(wechatMpAccountOpenid);
        return get(po);
    }

    /**
     * 根据微信小程序openid查询多条数据
     *
     * @param wechatMpAccountOpenid 微信小程序openid
     * @return 查询结果
     */
    public List<TbBasicSysUser> queryByWechatMpAccountOpenid(String wechatMpAccountOpenid) {
        if (StrUtil.isBlank(wechatMpAccountOpenid)) {
            return null;
        }

        TbBasicSysUserPo po = new TbBasicSysUserPo();
        po.setWhereWechatMpAccountOpenid(wechatMpAccountOpenid);
        return query(po);
    }

    /**
     * 根据微信小程序openid获取指定字段的值
     *
     * @param wechatMpAccountOpenid 微信小程序openid
     * @param fieldMapper 字段映射函数
     * @param <T> 字段类型
     * @return 字段值的 Optional
     */
    public <T> Optional<T> getFieldByWechatMpAccountOpenid(String wechatMpAccountOpenid, Function<TbBasicSysUser, T> fieldMapper) {
        if (StrUtil.isBlank(wechatMpAccountOpenid)) {
            return Optional.empty();
        }

        return Optional.ofNullable(getByWechatMpAccountOpenid(wechatMpAccountOpenid)).map(fieldMapper);
   }

    /**
     * 根据微信公众号openid查询单条数据
     *
     * @param wechatOfficialAccountOpenid 微信公众号openid
     * @return 查询结果
     */
    public TbBasicSysUser getByWechatOfficialAccountOpenid(String wechatOfficialAccountOpenid) {
        if (StrUtil.isBlank(wechatOfficialAccountOpenid)) {
            return null;
        }

        TbBasicSysUserPo po = new TbBasicSysUserPo();
        po.setWhereWechatOfficialAccountOpenid(wechatOfficialAccountOpenid);
        return get(po);
    }

    /**
     * 根据微信公众号openid查询多条数据
     *
     * @param wechatOfficialAccountOpenid 微信公众号openid
     * @return 查询结果
     */
    public List<TbBasicSysUser> queryByWechatOfficialAccountOpenid(String wechatOfficialAccountOpenid) {
        if (StrUtil.isBlank(wechatOfficialAccountOpenid)) {
            return null;
        }

        TbBasicSysUserPo po = new TbBasicSysUserPo();
        po.setWhereWechatOfficialAccountOpenid(wechatOfficialAccountOpenid);
        return query(po);
    }

    /**
     * 根据微信公众号openid获取指定字段的值
     *
     * @param wechatOfficialAccountOpenid 微信公众号openid
     * @param fieldMapper 字段映射函数
     * @param <T> 字段类型
     * @return 字段值的 Optional
     */
    public <T> Optional<T> getFieldByWechatOfficialAccountOpenid(String wechatOfficialAccountOpenid, Function<TbBasicSysUser, T> fieldMapper) {
        if (StrUtil.isBlank(wechatOfficialAccountOpenid)) {
            return Optional.empty();
        }

        return Optional.ofNullable(getByWechatOfficialAccountOpenid(wechatOfficialAccountOpenid)).map(fieldMapper);
   }

    /**
     * 根据无界unionid查询单条数据
     *
     * @param wujieUnionid 无界unionid
     * @return 查询结果
     */
    public TbBasicSysUser getByWujieUnionid(String wujieUnionid) {
        if (StrUtil.isBlank(wujieUnionid)) {
            return null;
        }

        TbBasicSysUserPo po = new TbBasicSysUserPo();
        po.setWhereWujieUnionid(wujieUnionid);
        return get(po);
    }

    /**
     * 根据无界unionid查询多条数据
     *
     * @param wujieUnionid 无界unionid
     * @return 查询结果
     */
    public List<TbBasicSysUser> queryByWujieUnionid(String wujieUnionid) {
        if (StrUtil.isBlank(wujieUnionid)) {
            return null;
        }

        TbBasicSysUserPo po = new TbBasicSysUserPo();
        po.setWhereWujieUnionid(wujieUnionid);
        return query(po);
    }

    /**
     * 根据无界unionid获取指定字段的值
     *
     * @param wujieUnionid 无界unionid
     * @param fieldMapper 字段映射函数
     * @param <T> 字段类型
     * @return 字段值的 Optional
     */
    public <T> Optional<T> getFieldByWujieUnionid(String wujieUnionid, Function<TbBasicSysUser, T> fieldMapper) {
        if (StrUtil.isBlank(wujieUnionid)) {
            return Optional.empty();
        }

        return Optional.ofNullable(getByWujieUnionid(wujieUnionid)).map(fieldMapper);
   }

    /**
     * 根据邮箱查询单条数据
     *
     * @param email 邮箱
     * @return 查询结果
     */
    public TbBasicSysUser getByEmail(String email) {
        if (StrUtil.isBlank(email)) {
            return null;
        }

        TbBasicSysUserPo po = new TbBasicSysUserPo();
        po.setWhereEmail(email);
        return get(po);
    }

    /**
     * 根据邮箱查询多条数据
     *
     * @param email 邮箱
     * @return 查询结果
     */
    public List<TbBasicSysUser> queryByEmail(String email) {
        if (StrUtil.isBlank(email)) {
            return null;
        }

        TbBasicSysUserPo po = new TbBasicSysUserPo();
        po.setWhereEmail(email);
        return query(po);
    }

    /**
     * 根据邮箱获取指定字段的值
     *
     * @param email 邮箱
     * @param fieldMapper 字段映射函数
     * @param <T> 字段类型
     * @return 字段值的 Optional
     */
    public <T> Optional<T> getFieldByEmail(String email, Function<TbBasicSysUser, T> fieldMapper) {
        if (StrUtil.isBlank(email)) {
            return Optional.empty();
        }

        return Optional.ofNullable(getByEmail(email)).map(fieldMapper);
   }

    /**
     * 根据邀请码查询单条数据
     *
     * @param inviteCode 邀请码
     * @return 查询结果
     */
    public TbBasicSysUser getByInviteCode(String inviteCode) {
        if (StrUtil.isBlank(inviteCode)) {
            return null;
        }

        TbBasicSysUserPo po = new TbBasicSysUserPo();
        po.setWhereInviteCode(inviteCode);
        return get(po);
    }

    /**
     * 根据邀请码查询多条数据
     *
     * @param inviteCode 邀请码
     * @return 查询结果
     */
    public List<TbBasicSysUser> queryByInviteCode(String inviteCode) {
        if (StrUtil.isBlank(inviteCode)) {
            return null;
        }

        TbBasicSysUserPo po = new TbBasicSysUserPo();
        po.setWhereInviteCode(inviteCode);
        return query(po);
    }

    /**
     * 根据邀请码获取指定字段的值
     *
     * @param inviteCode 邀请码
     * @param fieldMapper 字段映射函数
     * @param <T> 字段类型
     * @return 字段值的 Optional
     */
    public <T> Optional<T> getFieldByInviteCode(String inviteCode, Function<TbBasicSysUser, T> fieldMapper) {
        if (StrUtil.isBlank(inviteCode)) {
            return Optional.empty();
        }

        return Optional.ofNullable(getByInviteCode(inviteCode)).map(fieldMapper);
   }

    /**
     * 根据邀请者查询单条数据
     *
     * @param inviter 邀请者
     * @return 查询结果
     */
    public TbBasicSysUser getByInviter(Integer inviter) {
        if (ObjectUtil.isNull(inviter)) {
            return null;
        }

        TbBasicSysUserPo po = new TbBasicSysUserPo();
        po.setWhereInviter(inviter);
        return get(po);
    }

    /**
     * 根据邀请者查询多条数据
     *
     * @param inviter 邀请者
     * @return 查询结果
     */
    public List<TbBasicSysUser> queryByInviter(Integer inviter) {
        if (ObjectUtil.isNull(inviter)) {
            return null;
        }

        TbBasicSysUserPo po = new TbBasicSysUserPo();
        po.setWhereInviter(inviter);
        return query(po);
    }

    /**
     * 根据邀请者获取指定字段的值
     *
     * @param inviter 邀请者
     * @param fieldMapper 字段映射函数
     * @param <T> 字段类型
     * @return 字段值的 Optional
     */
    public <T> Optional<T> getFieldByInviter(Integer inviter, Function<TbBasicSysUser, T> fieldMapper) {
        if (ObjectUtil.isNull(inviter)) {
            return Optional.empty();
        }

        return Optional.ofNullable(getByInviter(inviter)).map(fieldMapper);
   }

    /**
     * 根据注册来源查询单条数据
     *
     * @param source 注册来源
     * @return 查询结果
     */
    public TbBasicSysUser getBySource(Integer source) {
        if (ObjectUtil.isNull(source)) {
            return null;
        }

        TbBasicSysUserPo po = new TbBasicSysUserPo();
        po.setWhereSource(source);
        return get(po);
    }

    /**
     * 根据注册来源查询多条数据
     *
     * @param source 注册来源
     * @return 查询结果
     */
    public List<TbBasicSysUser> queryBySource(Integer source) {
        if (ObjectUtil.isNull(source)) {
            return null;
        }

        TbBasicSysUserPo po = new TbBasicSysUserPo();
        po.setWhereSource(source);
        return query(po);
    }

    /**
     * 根据注册来源获取指定字段的值
     *
     * @param source 注册来源
     * @param fieldMapper 字段映射函数
     * @param <T> 字段类型
     * @return 字段值的 Optional
     */
    public <T> Optional<T> getFieldBySource(Integer source, Function<TbBasicSysUser, T> fieldMapper) {
        if (ObjectUtil.isNull(source)) {
            return Optional.empty();
        }

        return Optional.ofNullable(getBySource(source)).map(fieldMapper);
   }

    /**
     * 根据头像查询单条数据
     *
     * @param avatar 头像
     * @return 查询结果
     */
    public TbBasicSysUser getByAvatar(String avatar) {
        if (StrUtil.isBlank(avatar)) {
            return null;
        }

        TbBasicSysUserPo po = new TbBasicSysUserPo();
        po.setWhereAvatar(avatar);
        return get(po);
    }

    /**
     * 根据头像查询多条数据
     *
     * @param avatar 头像
     * @return 查询结果
     */
    public List<TbBasicSysUser> queryByAvatar(String avatar) {
        if (StrUtil.isBlank(avatar)) {
            return null;
        }

        TbBasicSysUserPo po = new TbBasicSysUserPo();
        po.setWhereAvatar(avatar);
        return query(po);
    }

    /**
     * 根据头像获取指定字段的值
     *
     * @param avatar 头像
     * @param fieldMapper 字段映射函数
     * @param <T> 字段类型
     * @return 字段值的 Optional
     */
    public <T> Optional<T> getFieldByAvatar(String avatar, Function<TbBasicSysUser, T> fieldMapper) {
        if (StrUtil.isBlank(avatar)) {
            return Optional.empty();
        }

        return Optional.ofNullable(getByAvatar(avatar)).map(fieldMapper);
   }

    /**
     * 根据真实姓名查询单条数据
     *
     * @param realName 真实姓名
     * @return 查询结果
     */
    public TbBasicSysUser getByRealName(String realName) {
        if (StrUtil.isBlank(realName)) {
            return null;
        }

        TbBasicSysUserPo po = new TbBasicSysUserPo();
        po.setWhereRealName(realName);
        return get(po);
    }

    /**
     * 根据真实姓名查询多条数据
     *
     * @param realName 真实姓名
     * @return 查询结果
     */
    public List<TbBasicSysUser> queryByRealName(String realName) {
        if (StrUtil.isBlank(realName)) {
            return null;
        }

        TbBasicSysUserPo po = new TbBasicSysUserPo();
        po.setWhereRealName(realName);
        return query(po);
    }

    /**
     * 根据真实姓名获取指定字段的值
     *
     * @param realName 真实姓名
     * @param fieldMapper 字段映射函数
     * @param <T> 字段类型
     * @return 字段值的 Optional
     */
    public <T> Optional<T> getFieldByRealName(String realName, Function<TbBasicSysUser, T> fieldMapper) {
        if (StrUtil.isBlank(realName)) {
            return Optional.empty();
        }

        return Optional.ofNullable(getByRealName(realName)).map(fieldMapper);
   }

    /**
     * 根据昵称查询单条数据
     *
     * @param nickName 昵称
     * @return 查询结果
     */
    public TbBasicSysUser getByNickName(String nickName) {
        if (StrUtil.isBlank(nickName)) {
            return null;
        }

        TbBasicSysUserPo po = new TbBasicSysUserPo();
        po.setWhereNickName(nickName);
        return get(po);
    }

    /**
     * 根据昵称查询多条数据
     *
     * @param nickName 昵称
     * @return 查询结果
     */
    public List<TbBasicSysUser> queryByNickName(String nickName) {
        if (StrUtil.isBlank(nickName)) {
            return null;
        }

        TbBasicSysUserPo po = new TbBasicSysUserPo();
        po.setWhereNickName(nickName);
        return query(po);
    }

    /**
     * 根据昵称获取指定字段的值
     *
     * @param nickName 昵称
     * @param fieldMapper 字段映射函数
     * @param <T> 字段类型
     * @return 字段值的 Optional
     */
    public <T> Optional<T> getFieldByNickName(String nickName, Function<TbBasicSysUser, T> fieldMapper) {
        if (StrUtil.isBlank(nickName)) {
            return Optional.empty();
        }

        return Optional.ofNullable(getByNickName(nickName)).map(fieldMapper);
   }

    /**
     * 根据状态。1：正常，2:禁用，3：注销查询单条数据
     *
     * @param status 状态。1：正常，2:禁用，3：注销
     * @return 查询结果
     */
    public TbBasicSysUser getByStatus(Integer status) {
        if (ObjectUtil.isNull(status)) {
            return null;
        }

        TbBasicSysUserPo po = new TbBasicSysUserPo();
        po.setWhereStatus(status);
        return get(po);
    }

    /**
     * 根据状态。1：正常，2:禁用，3：注销查询多条数据
     *
     * @param status 状态。1：正常，2:禁用，3：注销
     * @return 查询结果
     */
    public List<TbBasicSysUser> queryByStatus(Integer status) {
        if (ObjectUtil.isNull(status)) {
            return null;
        }

        TbBasicSysUserPo po = new TbBasicSysUserPo();
        po.setWhereStatus(status);
        return query(po);
    }

    /**
     * 根据状态。1：正常，2:禁用，3：注销获取指定字段的值
     *
     * @param status 状态。1：正常，2:禁用，3：注销
     * @param fieldMapper 字段映射函数
     * @param <T> 字段类型
     * @return 字段值的 Optional
     */
    public <T> Optional<T> getFieldByStatus(Integer status, Function<TbBasicSysUser, T> fieldMapper) {
        if (ObjectUtil.isNull(status)) {
            return Optional.empty();
        }

        return Optional.ofNullable(getByStatus(status)).map(fieldMapper);
   }

    /**
     * 根据性别。1：男，2：女，3：保密查询单条数据
     *
     * @param gender 性别。1：男，2：女，3：保密
     * @return 查询结果
     */
    public TbBasicSysUser getByGender(Integer gender) {
        if (ObjectUtil.isNull(gender)) {
            return null;
        }

        TbBasicSysUserPo po = new TbBasicSysUserPo();
        po.setWhereGender(gender);
        return get(po);
    }

    /**
     * 根据性别。1：男，2：女，3：保密查询多条数据
     *
     * @param gender 性别。1：男，2：女，3：保密
     * @return 查询结果
     */
    public List<TbBasicSysUser> queryByGender(Integer gender) {
        if (ObjectUtil.isNull(gender)) {
            return null;
        }

        TbBasicSysUserPo po = new TbBasicSysUserPo();
        po.setWhereGender(gender);
        return query(po);
    }

    /**
     * 根据性别。1：男，2：女，3：保密获取指定字段的值
     *
     * @param gender 性别。1：男，2：女，3：保密
     * @param fieldMapper 字段映射函数
     * @param <T> 字段类型
     * @return 字段值的 Optional
     */
    public <T> Optional<T> getFieldByGender(Integer gender, Function<TbBasicSysUser, T> fieldMapper) {
        if (ObjectUtil.isNull(gender)) {
            return Optional.empty();
        }

        return Optional.ofNullable(getByGender(gender)).map(fieldMapper);
   }

    /**
     * 根据简介查询单条数据
     *
     * @param introduction 简介
     * @return 查询结果
     */
    public TbBasicSysUser getByIntroduction(String introduction) {
        if (StrUtil.isBlank(introduction)) {
            return null;
        }

        TbBasicSysUserPo po = new TbBasicSysUserPo();
        po.setWhereIntroduction(introduction);
        return get(po);
    }

    /**
     * 根据简介查询多条数据
     *
     * @param introduction 简介
     * @return 查询结果
     */
    public List<TbBasicSysUser> queryByIntroduction(String introduction) {
        if (StrUtil.isBlank(introduction)) {
            return null;
        }

        TbBasicSysUserPo po = new TbBasicSysUserPo();
        po.setWhereIntroduction(introduction);
        return query(po);
    }

    /**
     * 根据简介获取指定字段的值
     *
     * @param introduction 简介
     * @param fieldMapper 字段映射函数
     * @param <T> 字段类型
     * @return 字段值的 Optional
     */
    public <T> Optional<T> getFieldByIntroduction(String introduction, Function<TbBasicSysUser, T> fieldMapper) {
        if (StrUtil.isBlank(introduction)) {
            return Optional.empty();
        }

        return Optional.ofNullable(getByIntroduction(introduction)).map(fieldMapper);
   }

    /**
     * 根据所属部门ID查询单条数据
     *
     * @param departmentId 所属部门ID
     * @return 查询结果
     */
    public TbBasicSysUser getByDepartmentId(Integer departmentId) {
        if (ObjectUtil.isNull(departmentId)) {
            return null;
        }

        TbBasicSysUserPo po = new TbBasicSysUserPo();
        po.setWhereDepartmentId(departmentId);
        return get(po);
    }

    /**
     * 根据所属部门ID查询多条数据
     *
     * @param departmentId 所属部门ID
     * @return 查询结果
     */
    public List<TbBasicSysUser> queryByDepartmentId(Integer departmentId) {
        if (ObjectUtil.isNull(departmentId)) {
            return null;
        }

        TbBasicSysUserPo po = new TbBasicSysUserPo();
        po.setWhereDepartmentId(departmentId);
        return query(po);
    }

    /**
     * 根据所属部门ID获取指定字段的值
     *
     * @param departmentId 所属部门ID
     * @param fieldMapper 字段映射函数
     * @param <T> 字段类型
     * @return 字段值的 Optional
     */
    public <T> Optional<T> getFieldByDepartmentId(Integer departmentId, Function<TbBasicSysUser, T> fieldMapper) {
        if (ObjectUtil.isNull(departmentId)) {
            return Optional.empty();
        }

        return Optional.ofNullable(getByDepartmentId(departmentId)).map(fieldMapper);
   }

    /**
     * 根据身份证号查询单条数据
     *
     * @param cardId 身份证号
     * @return 查询结果
     */
    public TbBasicSysUser getByCardId(String cardId) {
        if (StrUtil.isBlank(cardId)) {
            return null;
        }

        TbBasicSysUserPo po = new TbBasicSysUserPo();
        po.setWhereCardId(cardId);
        return get(po);
    }

    /**
     * 根据身份证号查询多条数据
     *
     * @param cardId 身份证号
     * @return 查询结果
     */
    public List<TbBasicSysUser> queryByCardId(String cardId) {
        if (StrUtil.isBlank(cardId)) {
            return null;
        }

        TbBasicSysUserPo po = new TbBasicSysUserPo();
        po.setWhereCardId(cardId);
        return query(po);
    }

    /**
     * 根据身份证号获取指定字段的值
     *
     * @param cardId 身份证号
     * @param fieldMapper 字段映射函数
     * @param <T> 字段类型
     * @return 字段值的 Optional
     */
    public <T> Optional<T> getFieldByCardId(String cardId, Function<TbBasicSysUser, T> fieldMapper) {
        if (StrUtil.isBlank(cardId)) {
            return Optional.empty();
        }

        return Optional.ofNullable(getByCardId(cardId)).map(fieldMapper);
   }

    /**
     * 根据生日查询单条数据
     *
     * @param birthDate 生日
     * @return 查询结果
     */
    public TbBasicSysUser getByBirthDate(String birthDate) {
        if (StrUtil.isBlank(birthDate)) {
            return null;
        }

        TbBasicSysUserPo po = new TbBasicSysUserPo();
        po.setWhereBirthDate(birthDate);
        return get(po);
    }

    /**
     * 根据生日查询多条数据
     *
     * @param birthDate 生日
     * @return 查询结果
     */
    public List<TbBasicSysUser> queryByBirthDate(String birthDate) {
        if (StrUtil.isBlank(birthDate)) {
            return null;
        }

        TbBasicSysUserPo po = new TbBasicSysUserPo();
        po.setWhereBirthDate(birthDate);
        return query(po);
    }

    /**
     * 根据生日获取指定字段的值
     *
     * @param birthDate 生日
     * @param fieldMapper 字段映射函数
     * @param <T> 字段类型
     * @return 字段值的 Optional
     */
    public <T> Optional<T> getFieldByBirthDate(String birthDate, Function<TbBasicSysUser, T> fieldMapper) {
        if (StrUtil.isBlank(birthDate)) {
            return Optional.empty();
        }

        return Optional.ofNullable(getByBirthDate(birthDate)).map(fieldMapper);
   }

    /**
     * 根据身高查询单条数据
     *
     * @param height 身高
     * @return 查询结果
     */
    public TbBasicSysUser getByHeight(Integer height) {
        if (ObjectUtil.isNull(height)) {
            return null;
        }

        TbBasicSysUserPo po = new TbBasicSysUserPo();
        po.setWhereHeight(height);
        return get(po);
    }

    /**
     * 根据身高查询多条数据
     *
     * @param height 身高
     * @return 查询结果
     */
    public List<TbBasicSysUser> queryByHeight(Integer height) {
        if (ObjectUtil.isNull(height)) {
            return null;
        }

        TbBasicSysUserPo po = new TbBasicSysUserPo();
        po.setWhereHeight(height);
        return query(po);
    }

    /**
     * 根据身高获取指定字段的值
     *
     * @param height 身高
     * @param fieldMapper 字段映射函数
     * @param <T> 字段类型
     * @return 字段值的 Optional
     */
    public <T> Optional<T> getFieldByHeight(Integer height, Function<TbBasicSysUser, T> fieldMapper) {
        if (ObjectUtil.isNull(height)) {
            return Optional.empty();
        }

        return Optional.ofNullable(getByHeight(height)).map(fieldMapper);
   }

    /**
     * 根据省查询单条数据
     *
     * @param province 省
     * @return 查询结果
     */
    public TbBasicSysUser getByProvince(Long province) {
        if (ObjectUtil.isNull(province)) {
            return null;
        }

        TbBasicSysUserPo po = new TbBasicSysUserPo();
        po.setWhereProvince(province);
        return get(po);
    }

    /**
     * 根据省查询多条数据
     *
     * @param province 省
     * @return 查询结果
     */
    public List<TbBasicSysUser> queryByProvince(Long province) {
        if (ObjectUtil.isNull(province)) {
            return null;
        }

        TbBasicSysUserPo po = new TbBasicSysUserPo();
        po.setWhereProvince(province);
        return query(po);
    }

    /**
     * 根据省获取指定字段的值
     *
     * @param province 省
     * @param fieldMapper 字段映射函数
     * @param <T> 字段类型
     * @return 字段值的 Optional
     */
    public <T> Optional<T> getFieldByProvince(Long province, Function<TbBasicSysUser, T> fieldMapper) {
        if (ObjectUtil.isNull(province)) {
            return Optional.empty();
        }

        return Optional.ofNullable(getByProvince(province)).map(fieldMapper);
   }

    /**
     * 根据市查询单条数据
     *
     * @param city 市
     * @return 查询结果
     */
    public TbBasicSysUser getByCity(Long city) {
        if (ObjectUtil.isNull(city)) {
            return null;
        }

        TbBasicSysUserPo po = new TbBasicSysUserPo();
        po.setWhereCity(city);
        return get(po);
    }

    /**
     * 根据市查询多条数据
     *
     * @param city 市
     * @return 查询结果
     */
    public List<TbBasicSysUser> queryByCity(Long city) {
        if (ObjectUtil.isNull(city)) {
            return null;
        }

        TbBasicSysUserPo po = new TbBasicSysUserPo();
        po.setWhereCity(city);
        return query(po);
    }

    /**
     * 根据市获取指定字段的值
     *
     * @param city 市
     * @param fieldMapper 字段映射函数
     * @param <T> 字段类型
     * @return 字段值的 Optional
     */
    public <T> Optional<T> getFieldByCity(Long city, Function<TbBasicSysUser, T> fieldMapper) {
        if (ObjectUtil.isNull(city)) {
            return Optional.empty();
        }

        return Optional.ofNullable(getByCity(city)).map(fieldMapper);
   }

    /**
     * 根据区县查询单条数据
     *
     * @param district 区县
     * @return 查询结果
     */
    public TbBasicSysUser getByDistrict(Long district) {
        if (ObjectUtil.isNull(district)) {
            return null;
        }

        TbBasicSysUserPo po = new TbBasicSysUserPo();
        po.setWhereDistrict(district);
        return get(po);
    }

    /**
     * 根据区县查询多条数据
     *
     * @param district 区县
     * @return 查询结果
     */
    public List<TbBasicSysUser> queryByDistrict(Long district) {
        if (ObjectUtil.isNull(district)) {
            return null;
        }

        TbBasicSysUserPo po = new TbBasicSysUserPo();
        po.setWhereDistrict(district);
        return query(po);
    }

    /**
     * 根据区县获取指定字段的值
     *
     * @param district 区县
     * @param fieldMapper 字段映射函数
     * @param <T> 字段类型
     * @return 字段值的 Optional
     */
    public <T> Optional<T> getFieldByDistrict(Long district, Function<TbBasicSysUser, T> fieldMapper) {
        if (ObjectUtil.isNull(district)) {
            return Optional.empty();
        }

        return Optional.ofNullable(getByDistrict(district)).map(fieldMapper);
   }

    /**
     * 根据详细地址查询单条数据
     *
     * @param address 详细地址
     * @return 查询结果
     */
    public TbBasicSysUser getByAddress(String address) {
        if (StrUtil.isBlank(address)) {
            return null;
        }

        TbBasicSysUserPo po = new TbBasicSysUserPo();
        po.setWhereAddress(address);
        return get(po);
    }

    /**
     * 根据详细地址查询多条数据
     *
     * @param address 详细地址
     * @return 查询结果
     */
    public List<TbBasicSysUser> queryByAddress(String address) {
        if (StrUtil.isBlank(address)) {
            return null;
        }

        TbBasicSysUserPo po = new TbBasicSysUserPo();
        po.setWhereAddress(address);
        return query(po);
    }

    /**
     * 根据详细地址获取指定字段的值
     *
     * @param address 详细地址
     * @param fieldMapper 字段映射函数
     * @param <T> 字段类型
     * @return 字段值的 Optional
     */
    public <T> Optional<T> getFieldByAddress(String address, Function<TbBasicSysUser, T> fieldMapper) {
        if (StrUtil.isBlank(address)) {
            return Optional.empty();
        }

        return Optional.ofNullable(getByAddress(address)).map(fieldMapper);
   }

    /**
     * 根据余额查询单条数据
     *
     * @param balance 余额
     * @return 查询结果
     */
    public TbBasicSysUser getByBalance(Integer balance) {
        if (ObjectUtil.isNull(balance)) {
            return null;
        }

        TbBasicSysUserPo po = new TbBasicSysUserPo();
        po.setWhereBalance(balance);
        return get(po);
    }

    /**
     * 根据余额查询多条数据
     *
     * @param balance 余额
     * @return 查询结果
     */
    public List<TbBasicSysUser> queryByBalance(Integer balance) {
        if (ObjectUtil.isNull(balance)) {
            return null;
        }

        TbBasicSysUserPo po = new TbBasicSysUserPo();
        po.setWhereBalance(balance);
        return query(po);
    }

    /**
     * 根据余额获取指定字段的值
     *
     * @param balance 余额
     * @param fieldMapper 字段映射函数
     * @param <T> 字段类型
     * @return 字段值的 Optional
     */
    public <T> Optional<T> getFieldByBalance(Integer balance, Function<TbBasicSysUser, T> fieldMapper) {
        if (ObjectUtil.isNull(balance)) {
            return Optional.empty();
        }

        return Optional.ofNullable(getByBalance(balance)).map(fieldMapper);
   }

    /**
     * 根据备注查询单条数据
     *
     * @param remark 备注
     * @return 查询结果
     */
    public TbBasicSysUser getByRemark(String remark) {
        if (StrUtil.isBlank(remark)) {
            return null;
        }

        TbBasicSysUserPo po = new TbBasicSysUserPo();
        po.setWhereRemark(remark);
        return get(po);
    }

    /**
     * 根据备注查询多条数据
     *
     * @param remark 备注
     * @return 查询结果
     */
    public List<TbBasicSysUser> queryByRemark(String remark) {
        if (StrUtil.isBlank(remark)) {
            return null;
        }

        TbBasicSysUserPo po = new TbBasicSysUserPo();
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
    public <T> Optional<T> getFieldByRemark(String remark, Function<TbBasicSysUser, T> fieldMapper) {
        if (StrUtil.isBlank(remark)) {
            return Optional.empty();
        }

        return Optional.ofNullable(getByRemark(remark)).map(fieldMapper);
   }

    /**
     * 根据是否是VIP。1：是，2：否。查询单条数据
     *
     * @param isVip 是否是VIP。1：是，2：否。
     * @return 查询结果
     */
    public TbBasicSysUser getByIsVip(Integer isVip) {
        if (ObjectUtil.isNull(isVip)) {
            return null;
        }

        TbBasicSysUserPo po = new TbBasicSysUserPo();
        po.setWhereIsVip(isVip);
        return get(po);
    }

    /**
     * 根据是否是VIP。1：是，2：否。查询多条数据
     *
     * @param isVip 是否是VIP。1：是，2：否。
     * @return 查询结果
     */
    public List<TbBasicSysUser> queryByIsVip(Integer isVip) {
        if (ObjectUtil.isNull(isVip)) {
            return null;
        }

        TbBasicSysUserPo po = new TbBasicSysUserPo();
        po.setWhereIsVip(isVip);
        return query(po);
    }

    /**
     * 根据是否是VIP。1：是，2：否。获取指定字段的值
     *
     * @param isVip 是否是VIP。1：是，2：否。
     * @param fieldMapper 字段映射函数
     * @param <T> 字段类型
     * @return 字段值的 Optional
     */
    public <T> Optional<T> getFieldByIsVip(Integer isVip, Function<TbBasicSysUser, T> fieldMapper) {
        if (ObjectUtil.isNull(isVip)) {
            return Optional.empty();
        }

        return Optional.ofNullable(getByIsVip(isVip)).map(fieldMapper);
   }

    /**
     * 根据创建时间查询单条数据
     *
     * @param createdAt 创建时间
     * @return 查询结果
     */
    public TbBasicSysUser getByCreatedAt(String createdAt) {
        if (StrUtil.isBlank(createdAt)) {
            return null;
        }

        TbBasicSysUserPo po = new TbBasicSysUserPo();
        po.setWhereCreatedAt(createdAt);
        return get(po);
    }

    /**
     * 根据创建时间查询多条数据
     *
     * @param createdAt 创建时间
     * @return 查询结果
     */
    public List<TbBasicSysUser> queryByCreatedAt(String createdAt) {
        if (StrUtil.isBlank(createdAt)) {
            return null;
        }

        TbBasicSysUserPo po = new TbBasicSysUserPo();
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
    public <T> Optional<T> getFieldByCreatedAt(String createdAt, Function<TbBasicSysUser, T> fieldMapper) {
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
    public TbBasicSysUser getByCreatedUserId(Integer createdUserId) {
        if (ObjectUtil.isNull(createdUserId)) {
            return null;
        }

        TbBasicSysUserPo po = new TbBasicSysUserPo();
        po.setWhereCreatedUserId(createdUserId);
        return get(po);
    }

    /**
     * 根据创建者查询多条数据
     *
     * @param createdUserId 创建者
     * @return 查询结果
     */
    public List<TbBasicSysUser> queryByCreatedUserId(Integer createdUserId) {
        if (ObjectUtil.isNull(createdUserId)) {
            return null;
        }

        TbBasicSysUserPo po = new TbBasicSysUserPo();
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
    public <T> Optional<T> getFieldByCreatedUserId(Integer createdUserId, Function<TbBasicSysUser, T> fieldMapper) {
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
    public TbBasicSysUser getByUpdatedAt(String updatedAt) {
        if (StrUtil.isBlank(updatedAt)) {
            return null;
        }

        TbBasicSysUserPo po = new TbBasicSysUserPo();
        po.setWhereUpdatedAt(updatedAt);
        return get(po);
    }

    /**
     * 根据更新时间查询多条数据
     *
     * @param updatedAt 更新时间
     * @return 查询结果
     */
    public List<TbBasicSysUser> queryByUpdatedAt(String updatedAt) {
        if (StrUtil.isBlank(updatedAt)) {
            return null;
        }

        TbBasicSysUserPo po = new TbBasicSysUserPo();
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
    public <T> Optional<T> getFieldByUpdatedAt(String updatedAt, Function<TbBasicSysUser, T> fieldMapper) {
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
    public TbBasicSysUser getByUpdatedUserId(Integer updatedUserId) {
        if (ObjectUtil.isNull(updatedUserId)) {
            return null;
        }

        TbBasicSysUserPo po = new TbBasicSysUserPo();
        po.setWhereUpdatedUserId(updatedUserId);
        return get(po);
    }

    /**
     * 根据最后更新者查询多条数据
     *
     * @param updatedUserId 最后更新者
     * @return 查询结果
     */
    public List<TbBasicSysUser> queryByUpdatedUserId(Integer updatedUserId) {
        if (ObjectUtil.isNull(updatedUserId)) {
            return null;
        }

        TbBasicSysUserPo po = new TbBasicSysUserPo();
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
    public <T> Optional<T> getFieldByUpdatedUserId(Integer updatedUserId, Function<TbBasicSysUser, T> fieldMapper) {
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
    public BigDecimal sumId(TbBasicSysUserPo po) {
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
    public BigDecimal maxId(TbBasicSysUserPo po) {
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
    public BigDecimal minId(TbBasicSysUserPo po) {
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
    public BigDecimal avgId(TbBasicSysUserPo po) {
        if (po == null) {
            return BigDecimal.ZERO;
        }
        return mapper.avgId(po);
    }
    /**
     * 统计邀请者的总和
     *
     * @param po 查询条件
     * @return 总和结果
     */
    public BigDecimal sumInviter(TbBasicSysUserPo po) {
        if (po == null) {
            return BigDecimal.ZERO;
        }
        return mapper.sumInviter(po);
    }

    /**
     * 统计邀请者的最大值
     *
     * @param po 查询条件
     * @return 最大值结果
     */
    public BigDecimal maxInviter(TbBasicSysUserPo po) {
        if (po == null) {
            return BigDecimal.ZERO;
        }
        return mapper.maxInviter(po);
    }

    /**
     * 统计邀请者的最小值
     *
     * @param po 查询条件
     * @return 最小值结果
     */
    public BigDecimal minInviter(TbBasicSysUserPo po) {
        if (po == null) {
            return BigDecimal.ZERO;
        }
        return mapper.minInviter(po);
    }

    /**
     * 统计邀请者的平均值
     *
     * @param po 查询条件
     * @return 平均值结果
     */
    public BigDecimal avgInviter(TbBasicSysUserPo po) {
        if (po == null) {
            return BigDecimal.ZERO;
        }
        return mapper.avgInviter(po);
    }
    /**
     * 统计注册来源的总和
     *
     * @param po 查询条件
     * @return 总和结果
     */
    public BigDecimal sumSource(TbBasicSysUserPo po) {
        if (po == null) {
            return BigDecimal.ZERO;
        }
        return mapper.sumSource(po);
    }

    /**
     * 统计注册来源的最大值
     *
     * @param po 查询条件
     * @return 最大值结果
     */
    public BigDecimal maxSource(TbBasicSysUserPo po) {
        if (po == null) {
            return BigDecimal.ZERO;
        }
        return mapper.maxSource(po);
    }

    /**
     * 统计注册来源的最小值
     *
     * @param po 查询条件
     * @return 最小值结果
     */
    public BigDecimal minSource(TbBasicSysUserPo po) {
        if (po == null) {
            return BigDecimal.ZERO;
        }
        return mapper.minSource(po);
    }

    /**
     * 统计注册来源的平均值
     *
     * @param po 查询条件
     * @return 平均值结果
     */
    public BigDecimal avgSource(TbBasicSysUserPo po) {
        if (po == null) {
            return BigDecimal.ZERO;
        }
        return mapper.avgSource(po);
    }
    /**
     * 统计状态。1：正常，2:禁用，3：注销的总和
     *
     * @param po 查询条件
     * @return 总和结果
     */
    public BigDecimal sumStatus(TbBasicSysUserPo po) {
        if (po == null) {
            return BigDecimal.ZERO;
        }
        return mapper.sumStatus(po);
    }

    /**
     * 统计状态。1：正常，2:禁用，3：注销的最大值
     *
     * @param po 查询条件
     * @return 最大值结果
     */
    public BigDecimal maxStatus(TbBasicSysUserPo po) {
        if (po == null) {
            return BigDecimal.ZERO;
        }
        return mapper.maxStatus(po);
    }

    /**
     * 统计状态。1：正常，2:禁用，3：注销的最小值
     *
     * @param po 查询条件
     * @return 最小值结果
     */
    public BigDecimal minStatus(TbBasicSysUserPo po) {
        if (po == null) {
            return BigDecimal.ZERO;
        }
        return mapper.minStatus(po);
    }

    /**
     * 统计状态。1：正常，2:禁用，3：注销的平均值
     *
     * @param po 查询条件
     * @return 平均值结果
     */
    public BigDecimal avgStatus(TbBasicSysUserPo po) {
        if (po == null) {
            return BigDecimal.ZERO;
        }
        return mapper.avgStatus(po);
    }
    /**
     * 统计性别。1：男，2：女，3：保密的总和
     *
     * @param po 查询条件
     * @return 总和结果
     */
    public BigDecimal sumGender(TbBasicSysUserPo po) {
        if (po == null) {
            return BigDecimal.ZERO;
        }
        return mapper.sumGender(po);
    }

    /**
     * 统计性别。1：男，2：女，3：保密的最大值
     *
     * @param po 查询条件
     * @return 最大值结果
     */
    public BigDecimal maxGender(TbBasicSysUserPo po) {
        if (po == null) {
            return BigDecimal.ZERO;
        }
        return mapper.maxGender(po);
    }

    /**
     * 统计性别。1：男，2：女，3：保密的最小值
     *
     * @param po 查询条件
     * @return 最小值结果
     */
    public BigDecimal minGender(TbBasicSysUserPo po) {
        if (po == null) {
            return BigDecimal.ZERO;
        }
        return mapper.minGender(po);
    }

    /**
     * 统计性别。1：男，2：女，3：保密的平均值
     *
     * @param po 查询条件
     * @return 平均值结果
     */
    public BigDecimal avgGender(TbBasicSysUserPo po) {
        if (po == null) {
            return BigDecimal.ZERO;
        }
        return mapper.avgGender(po);
    }
    /**
     * 统计所属部门ID的总和
     *
     * @param po 查询条件
     * @return 总和结果
     */
    public BigDecimal sumDepartmentId(TbBasicSysUserPo po) {
        if (po == null) {
            return BigDecimal.ZERO;
        }
        return mapper.sumDepartmentId(po);
    }

    /**
     * 统计所属部门ID的最大值
     *
     * @param po 查询条件
     * @return 最大值结果
     */
    public BigDecimal maxDepartmentId(TbBasicSysUserPo po) {
        if (po == null) {
            return BigDecimal.ZERO;
        }
        return mapper.maxDepartmentId(po);
    }

    /**
     * 统计所属部门ID的最小值
     *
     * @param po 查询条件
     * @return 最小值结果
     */
    public BigDecimal minDepartmentId(TbBasicSysUserPo po) {
        if (po == null) {
            return BigDecimal.ZERO;
        }
        return mapper.minDepartmentId(po);
    }

    /**
     * 统计所属部门ID的平均值
     *
     * @param po 查询条件
     * @return 平均值结果
     */
    public BigDecimal avgDepartmentId(TbBasicSysUserPo po) {
        if (po == null) {
            return BigDecimal.ZERO;
        }
        return mapper.avgDepartmentId(po);
    }
    /**
     * 统计身高的总和
     *
     * @param po 查询条件
     * @return 总和结果
     */
    public BigDecimal sumHeight(TbBasicSysUserPo po) {
        if (po == null) {
            return BigDecimal.ZERO;
        }
        return mapper.sumHeight(po);
    }

    /**
     * 统计身高的最大值
     *
     * @param po 查询条件
     * @return 最大值结果
     */
    public BigDecimal maxHeight(TbBasicSysUserPo po) {
        if (po == null) {
            return BigDecimal.ZERO;
        }
        return mapper.maxHeight(po);
    }

    /**
     * 统计身高的最小值
     *
     * @param po 查询条件
     * @return 最小值结果
     */
    public BigDecimal minHeight(TbBasicSysUserPo po) {
        if (po == null) {
            return BigDecimal.ZERO;
        }
        return mapper.minHeight(po);
    }

    /**
     * 统计身高的平均值
     *
     * @param po 查询条件
     * @return 平均值结果
     */
    public BigDecimal avgHeight(TbBasicSysUserPo po) {
        if (po == null) {
            return BigDecimal.ZERO;
        }
        return mapper.avgHeight(po);
    }
    /**
     * 统计省的总和
     *
     * @param po 查询条件
     * @return 总和结果
     */
    public BigDecimal sumProvince(TbBasicSysUserPo po) {
        if (po == null) {
            return BigDecimal.ZERO;
        }
        return mapper.sumProvince(po);
    }

    /**
     * 统计省的最大值
     *
     * @param po 查询条件
     * @return 最大值结果
     */
    public BigDecimal maxProvince(TbBasicSysUserPo po) {
        if (po == null) {
            return BigDecimal.ZERO;
        }
        return mapper.maxProvince(po);
    }

    /**
     * 统计省的最小值
     *
     * @param po 查询条件
     * @return 最小值结果
     */
    public BigDecimal minProvince(TbBasicSysUserPo po) {
        if (po == null) {
            return BigDecimal.ZERO;
        }
        return mapper.minProvince(po);
    }

    /**
     * 统计省的平均值
     *
     * @param po 查询条件
     * @return 平均值结果
     */
    public BigDecimal avgProvince(TbBasicSysUserPo po) {
        if (po == null) {
            return BigDecimal.ZERO;
        }
        return mapper.avgProvince(po);
    }
    /**
     * 统计市的总和
     *
     * @param po 查询条件
     * @return 总和结果
     */
    public BigDecimal sumCity(TbBasicSysUserPo po) {
        if (po == null) {
            return BigDecimal.ZERO;
        }
        return mapper.sumCity(po);
    }

    /**
     * 统计市的最大值
     *
     * @param po 查询条件
     * @return 最大值结果
     */
    public BigDecimal maxCity(TbBasicSysUserPo po) {
        if (po == null) {
            return BigDecimal.ZERO;
        }
        return mapper.maxCity(po);
    }

    /**
     * 统计市的最小值
     *
     * @param po 查询条件
     * @return 最小值结果
     */
    public BigDecimal minCity(TbBasicSysUserPo po) {
        if (po == null) {
            return BigDecimal.ZERO;
        }
        return mapper.minCity(po);
    }

    /**
     * 统计市的平均值
     *
     * @param po 查询条件
     * @return 平均值结果
     */
    public BigDecimal avgCity(TbBasicSysUserPo po) {
        if (po == null) {
            return BigDecimal.ZERO;
        }
        return mapper.avgCity(po);
    }
    /**
     * 统计区县的总和
     *
     * @param po 查询条件
     * @return 总和结果
     */
    public BigDecimal sumDistrict(TbBasicSysUserPo po) {
        if (po == null) {
            return BigDecimal.ZERO;
        }
        return mapper.sumDistrict(po);
    }

    /**
     * 统计区县的最大值
     *
     * @param po 查询条件
     * @return 最大值结果
     */
    public BigDecimal maxDistrict(TbBasicSysUserPo po) {
        if (po == null) {
            return BigDecimal.ZERO;
        }
        return mapper.maxDistrict(po);
    }

    /**
     * 统计区县的最小值
     *
     * @param po 查询条件
     * @return 最小值结果
     */
    public BigDecimal minDistrict(TbBasicSysUserPo po) {
        if (po == null) {
            return BigDecimal.ZERO;
        }
        return mapper.minDistrict(po);
    }

    /**
     * 统计区县的平均值
     *
     * @param po 查询条件
     * @return 平均值结果
     */
    public BigDecimal avgDistrict(TbBasicSysUserPo po) {
        if (po == null) {
            return BigDecimal.ZERO;
        }
        return mapper.avgDistrict(po);
    }
    /**
     * 统计余额的总和
     *
     * @param po 查询条件
     * @return 总和结果
     */
    public BigDecimal sumBalance(TbBasicSysUserPo po) {
        if (po == null) {
            return BigDecimal.ZERO;
        }
        return mapper.sumBalance(po);
    }

    /**
     * 统计余额的最大值
     *
     * @param po 查询条件
     * @return 最大值结果
     */
    public BigDecimal maxBalance(TbBasicSysUserPo po) {
        if (po == null) {
            return BigDecimal.ZERO;
        }
        return mapper.maxBalance(po);
    }

    /**
     * 统计余额的最小值
     *
     * @param po 查询条件
     * @return 最小值结果
     */
    public BigDecimal minBalance(TbBasicSysUserPo po) {
        if (po == null) {
            return BigDecimal.ZERO;
        }
        return mapper.minBalance(po);
    }

    /**
     * 统计余额的平均值
     *
     * @param po 查询条件
     * @return 平均值结果
     */
    public BigDecimal avgBalance(TbBasicSysUserPo po) {
        if (po == null) {
            return BigDecimal.ZERO;
        }
        return mapper.avgBalance(po);
    }
    /**
     * 统计是否是VIP。1：是，2：否。的总和
     *
     * @param po 查询条件
     * @return 总和结果
     */
    public BigDecimal sumIsVip(TbBasicSysUserPo po) {
        if (po == null) {
            return BigDecimal.ZERO;
        }
        return mapper.sumIsVip(po);
    }

    /**
     * 统计是否是VIP。1：是，2：否。的最大值
     *
     * @param po 查询条件
     * @return 最大值结果
     */
    public BigDecimal maxIsVip(TbBasicSysUserPo po) {
        if (po == null) {
            return BigDecimal.ZERO;
        }
        return mapper.maxIsVip(po);
    }

    /**
     * 统计是否是VIP。1：是，2：否。的最小值
     *
     * @param po 查询条件
     * @return 最小值结果
     */
    public BigDecimal minIsVip(TbBasicSysUserPo po) {
        if (po == null) {
            return BigDecimal.ZERO;
        }
        return mapper.minIsVip(po);
    }

    /**
     * 统计是否是VIP。1：是，2：否。的平均值
     *
     * @param po 查询条件
     * @return 平均值结果
     */
    public BigDecimal avgIsVip(TbBasicSysUserPo po) {
        if (po == null) {
            return BigDecimal.ZERO;
        }
        return mapper.avgIsVip(po);
    }
    /**
     * 统计创建者的总和
     *
     * @param po 查询条件
     * @return 总和结果
     */
    public BigDecimal sumCreatedUserId(TbBasicSysUserPo po) {
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
    public BigDecimal maxCreatedUserId(TbBasicSysUserPo po) {
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
    public BigDecimal minCreatedUserId(TbBasicSysUserPo po) {
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
    public BigDecimal avgCreatedUserId(TbBasicSysUserPo po) {
        if (po == null) {
            return BigDecimal.ZERO;
        }
        return mapper.avgCreatedUserId(po);
    }
    /**
     * 统计最后更新者的总和
     *
     * @param po 查询条件
     * @return 总和结果
     */
    public BigDecimal sumUpdatedUserId(TbBasicSysUserPo po) {
        if (po == null) {
            return BigDecimal.ZERO;
        }
        return mapper.sumUpdatedUserId(po);
    }

    /**
     * 统计最后更新者的最大值
     *
     * @param po 查询条件
     * @return 最大值结果
     */
    public BigDecimal maxUpdatedUserId(TbBasicSysUserPo po) {
        if (po == null) {
            return BigDecimal.ZERO;
        }
        return mapper.maxUpdatedUserId(po);
    }

    /**
     * 统计最后更新者的最小值
     *
     * @param po 查询条件
     * @return 最小值结果
     */
    public BigDecimal minUpdatedUserId(TbBasicSysUserPo po) {
        if (po == null) {
            return BigDecimal.ZERO;
        }
        return mapper.minUpdatedUserId(po);
    }

    /**
     * 统计最后更新者的平均值
     *
     * @param po 查询条件
     * @return 平均值结果
     */
    public BigDecimal avgUpdatedUserId(TbBasicSysUserPo po) {
        if (po == null) {
            return BigDecimal.ZERO;
        }
        return mapper.avgUpdatedUserId(po);
    }
    //***************************************************聚合操作 END********************************************************
}