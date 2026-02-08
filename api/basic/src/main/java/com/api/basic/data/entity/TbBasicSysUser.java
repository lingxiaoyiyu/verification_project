package com.api.basic.data.entity;

import com.api.common.base.data.po.BasePagePo;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.SuperBuilder;

/**
 * 用户登录信息表实体
 */
@EqualsAndHashCode(doNotUseGetters = true, callSuper = true)
@Data
@SuperBuilder
public class TbBasicSysUser extends BasePagePo {

    public TbBasicSysUser(){
        super();
    }

    // ID
    private Integer id;
    // 用户名
    private String username;
    // 密码
    private String password;
    // 手机号
    private String phoneNumber;
    // 微信unionid
    private String wechatUnionid;
    // 微信小程序openid
    private String wechatMpAccountOpenid;
    // 微信公众号openid
    private String wechatOfficialAccountOpenid;
    // 无界unionid
    private String wujieUnionid;
    // 邮箱
    private String email;
    // 邀请码
    private String inviteCode;
    // 邀请者
    private Integer inviter;
    // 注册来源
    private Integer source;
    // 头像
    private String avatar;
    // 真实姓名
    private String realName;
    // 昵称
    private String nickName;
    // 状态。1：正常，2:禁用，3：注销
    private Integer status;
    // 性别。1：男，2：女，3：保密
    private Integer gender;
    // 简介
    private String introduction;
    // 所属部门ID
    private Integer departmentId;
    // 身份证号
    private String cardId;
    // 生日
    private String birthDate;
    // 身高
    private Integer height;
    // 省
    private Long province;
    // 市
    private Long city;
    // 区县
    private Long district;
    // 详细地址
    private String address;
    // 余额
    private Integer balance;
    // 备注
    private String remark;
    // 是否是VIP。1：是，2：否。
    private Integer isVip;
    // 创建时间
    private String createdAt;
    // 创建者
    private Integer createdUserId;
    // 更新时间
    private String updatedAt;
    // 最后更新者
    private Integer updatedUserId;
}
