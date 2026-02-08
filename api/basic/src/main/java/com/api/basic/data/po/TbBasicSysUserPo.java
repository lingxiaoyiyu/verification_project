package com.api.basic.data.po;

import com.api.basic.data.entity.TbBasicSysUser;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.SuperBuilder;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * 用户登录信息表条件实体
 */
@EqualsAndHashCode(doNotUseGetters = true, callSuper = true)
@Data
@SuperBuilder
public class TbBasicSysUserPo extends TbBasicSysUser {

    public TbBasicSysUserPo(){
        super();
    }

    // 条件：ID，等于
    private Integer whereId;
    // 条件：ID，在列表中
    private List<Integer> whereInIds;
    // 条件：ID，在列表中，or连接
    private List<Integer> whereInOrIds;
    // 排除条件：ID
    private Integer whereNotId;
    // 条件：ID，不在列表中
    private List<Integer> whereNotInIds;
    // 条件：ID，为空
    private Boolean whereIsNullId;
    // 条件：ID，不为空
    private Boolean whereIsNotNullId;
    // 条件：ID，为空字符串
    private Boolean whereIsEmptyId;
    // 条件：ID，不为空字符串
    private Boolean whereIsNotEmptyId;
    // 条件：ID，大于
    private Integer whereGtId;
    // 条件：ID，大于等于
    private Integer whereGteId;
    // 条件：ID，小于
    private Integer whereLtId;
    // 条件：ID，小于等于
    private Integer whereLteId;
    // 条件：ID，开始范围
    private Integer whereStartId;
    // 条件：ID，结束范围
    private Integer whereEndId;
    // 条件：用户名，等于
    private String whereUsername;
    // 条件：用户名，在列表中
    private List<String> whereInUsernames;
    // 条件：用户名，在列表中，or连接
    private List<String> whereInOrUsernames;
    // 排除条件：用户名
    private String whereNotUsername;
    // 条件：用户名，不在列表中
    private List<String> whereNotInUsernames;
    // 条件：用户名，为空
    private Boolean whereIsNullUsername;
    // 条件：用户名，不为空
    private Boolean whereIsNotNullUsername;
    // 条件：用户名，为空字符串
    private Boolean whereIsEmptyUsername;
    // 条件：用户名，不为空字符串
    private Boolean whereIsNotEmptyUsername;
    // 条件：用户名，大于
    private String whereGtUsername;
    // 条件：用户名，大于等于
    private String whereGteUsername;
    // 条件：用户名，小于
    private String whereLtUsername;
    // 条件：用户名，小于等于
    private String whereLteUsername;
    // 条件：用户名，模糊查询
    private String whereLikeUsername;
    // 条件：用户名，开始范围
    private String whereStartUsername;
    // 条件：用户名，结束范围
    private String whereEndUsername;
    // 条件：密码，等于
    private String wherePassword;
    // 条件：密码，在列表中
    private List<String> whereInPasswords;
    // 条件：密码，在列表中，or连接
    private List<String> whereInOrPasswords;
    // 排除条件：密码
    private String whereNotPassword;
    // 条件：密码，不在列表中
    private List<String> whereNotInPasswords;
    // 条件：密码，为空
    private Boolean whereIsNullPassword;
    // 条件：密码，不为空
    private Boolean whereIsNotNullPassword;
    // 条件：密码，为空字符串
    private Boolean whereIsEmptyPassword;
    // 条件：密码，不为空字符串
    private Boolean whereIsNotEmptyPassword;
    // 条件：密码，大于
    private String whereGtPassword;
    // 条件：密码，大于等于
    private String whereGtePassword;
    // 条件：密码，小于
    private String whereLtPassword;
    // 条件：密码，小于等于
    private String whereLtePassword;
    // 条件：密码，模糊查询
    private String whereLikePassword;
    // 条件：密码，开始范围
    private String whereStartPassword;
    // 条件：密码，结束范围
    private String whereEndPassword;
    // 条件：手机号，等于
    private String wherePhoneNumber;
    // 条件：手机号，在列表中
    private List<String> whereInPhoneNumbers;
    // 条件：手机号，在列表中，or连接
    private List<String> whereInOrPhoneNumbers;
    // 排除条件：手机号
    private String whereNotPhoneNumber;
    // 条件：手机号，不在列表中
    private List<String> whereNotInPhoneNumbers;
    // 条件：手机号，为空
    private Boolean whereIsNullPhoneNumber;
    // 条件：手机号，不为空
    private Boolean whereIsNotNullPhoneNumber;
    // 条件：手机号，为空字符串
    private Boolean whereIsEmptyPhoneNumber;
    // 条件：手机号，不为空字符串
    private Boolean whereIsNotEmptyPhoneNumber;
    // 条件：手机号，大于
    private String whereGtPhoneNumber;
    // 条件：手机号，大于等于
    private String whereGtePhoneNumber;
    // 条件：手机号，小于
    private String whereLtPhoneNumber;
    // 条件：手机号，小于等于
    private String whereLtePhoneNumber;
    // 条件：手机号，模糊查询
    private String whereLikePhoneNumber;
    // 条件：手机号，开始范围
    private String whereStartPhoneNumber;
    // 条件：手机号，结束范围
    private String whereEndPhoneNumber;
    // 条件：微信unionid，等于
    private String whereWechatUnionid;
    // 条件：微信unionid，在列表中
    private List<String> whereInWechatUnionids;
    // 条件：微信unionid，在列表中，or连接
    private List<String> whereInOrWechatUnionids;
    // 排除条件：微信unionid
    private String whereNotWechatUnionid;
    // 条件：微信unionid，不在列表中
    private List<String> whereNotInWechatUnionids;
    // 条件：微信unionid，为空
    private Boolean whereIsNullWechatUnionid;
    // 条件：微信unionid，不为空
    private Boolean whereIsNotNullWechatUnionid;
    // 条件：微信unionid，为空字符串
    private Boolean whereIsEmptyWechatUnionid;
    // 条件：微信unionid，不为空字符串
    private Boolean whereIsNotEmptyWechatUnionid;
    // 条件：微信unionid，大于
    private String whereGtWechatUnionid;
    // 条件：微信unionid，大于等于
    private String whereGteWechatUnionid;
    // 条件：微信unionid，小于
    private String whereLtWechatUnionid;
    // 条件：微信unionid，小于等于
    private String whereLteWechatUnionid;
    // 条件：微信unionid，模糊查询
    private String whereLikeWechatUnionid;
    // 条件：微信unionid，开始范围
    private String whereStartWechatUnionid;
    // 条件：微信unionid，结束范围
    private String whereEndWechatUnionid;
    // 条件：微信小程序openid，等于
    private String whereWechatMpAccountOpenid;
    // 条件：微信小程序openid，在列表中
    private List<String> whereInWechatMpAccountOpenids;
    // 条件：微信小程序openid，在列表中，or连接
    private List<String> whereInOrWechatMpAccountOpenids;
    // 排除条件：微信小程序openid
    private String whereNotWechatMpAccountOpenid;
    // 条件：微信小程序openid，不在列表中
    private List<String> whereNotInWechatMpAccountOpenids;
    // 条件：微信小程序openid，为空
    private Boolean whereIsNullWechatMpAccountOpenid;
    // 条件：微信小程序openid，不为空
    private Boolean whereIsNotNullWechatMpAccountOpenid;
    // 条件：微信小程序openid，为空字符串
    private Boolean whereIsEmptyWechatMpAccountOpenid;
    // 条件：微信小程序openid，不为空字符串
    private Boolean whereIsNotEmptyWechatMpAccountOpenid;
    // 条件：微信小程序openid，大于
    private String whereGtWechatMpAccountOpenid;
    // 条件：微信小程序openid，大于等于
    private String whereGteWechatMpAccountOpenid;
    // 条件：微信小程序openid，小于
    private String whereLtWechatMpAccountOpenid;
    // 条件：微信小程序openid，小于等于
    private String whereLteWechatMpAccountOpenid;
    // 条件：微信小程序openid，模糊查询
    private String whereLikeWechatMpAccountOpenid;
    // 条件：微信小程序openid，开始范围
    private String whereStartWechatMpAccountOpenid;
    // 条件：微信小程序openid，结束范围
    private String whereEndWechatMpAccountOpenid;
    // 条件：微信公众号openid，等于
    private String whereWechatOfficialAccountOpenid;
    // 条件：微信公众号openid，在列表中
    private List<String> whereInWechatOfficialAccountOpenids;
    // 条件：微信公众号openid，在列表中，or连接
    private List<String> whereInOrWechatOfficialAccountOpenids;
    // 排除条件：微信公众号openid
    private String whereNotWechatOfficialAccountOpenid;
    // 条件：微信公众号openid，不在列表中
    private List<String> whereNotInWechatOfficialAccountOpenids;
    // 条件：微信公众号openid，为空
    private Boolean whereIsNullWechatOfficialAccountOpenid;
    // 条件：微信公众号openid，不为空
    private Boolean whereIsNotNullWechatOfficialAccountOpenid;
    // 条件：微信公众号openid，为空字符串
    private Boolean whereIsEmptyWechatOfficialAccountOpenid;
    // 条件：微信公众号openid，不为空字符串
    private Boolean whereIsNotEmptyWechatOfficialAccountOpenid;
    // 条件：微信公众号openid，大于
    private String whereGtWechatOfficialAccountOpenid;
    // 条件：微信公众号openid，大于等于
    private String whereGteWechatOfficialAccountOpenid;
    // 条件：微信公众号openid，小于
    private String whereLtWechatOfficialAccountOpenid;
    // 条件：微信公众号openid，小于等于
    private String whereLteWechatOfficialAccountOpenid;
    // 条件：微信公众号openid，模糊查询
    private String whereLikeWechatOfficialAccountOpenid;
    // 条件：微信公众号openid，开始范围
    private String whereStartWechatOfficialAccountOpenid;
    // 条件：微信公众号openid，结束范围
    private String whereEndWechatOfficialAccountOpenid;
    // 条件：无界unionid，等于
    private String whereWujieUnionid;
    // 条件：无界unionid，在列表中
    private List<String> whereInWujieUnionids;
    // 条件：无界unionid，在列表中，or连接
    private List<String> whereInOrWujieUnionids;
    // 排除条件：无界unionid
    private String whereNotWujieUnionid;
    // 条件：无界unionid，不在列表中
    private List<String> whereNotInWujieUnionids;
    // 条件：无界unionid，为空
    private Boolean whereIsNullWujieUnionid;
    // 条件：无界unionid，不为空
    private Boolean whereIsNotNullWujieUnionid;
    // 条件：无界unionid，为空字符串
    private Boolean whereIsEmptyWujieUnionid;
    // 条件：无界unionid，不为空字符串
    private Boolean whereIsNotEmptyWujieUnionid;
    // 条件：无界unionid，大于
    private String whereGtWujieUnionid;
    // 条件：无界unionid，大于等于
    private String whereGteWujieUnionid;
    // 条件：无界unionid，小于
    private String whereLtWujieUnionid;
    // 条件：无界unionid，小于等于
    private String whereLteWujieUnionid;
    // 条件：无界unionid，模糊查询
    private String whereLikeWujieUnionid;
    // 条件：无界unionid，开始范围
    private String whereStartWujieUnionid;
    // 条件：无界unionid，结束范围
    private String whereEndWujieUnionid;
    // 条件：邮箱，等于
    private String whereEmail;
    // 条件：邮箱，在列表中
    private List<String> whereInEmails;
    // 条件：邮箱，在列表中，or连接
    private List<String> whereInOrEmails;
    // 排除条件：邮箱
    private String whereNotEmail;
    // 条件：邮箱，不在列表中
    private List<String> whereNotInEmails;
    // 条件：邮箱，为空
    private Boolean whereIsNullEmail;
    // 条件：邮箱，不为空
    private Boolean whereIsNotNullEmail;
    // 条件：邮箱，为空字符串
    private Boolean whereIsEmptyEmail;
    // 条件：邮箱，不为空字符串
    private Boolean whereIsNotEmptyEmail;
    // 条件：邮箱，大于
    private String whereGtEmail;
    // 条件：邮箱，大于等于
    private String whereGteEmail;
    // 条件：邮箱，小于
    private String whereLtEmail;
    // 条件：邮箱，小于等于
    private String whereLteEmail;
    // 条件：邮箱，模糊查询
    private String whereLikeEmail;
    // 条件：邮箱，开始范围
    private String whereStartEmail;
    // 条件：邮箱，结束范围
    private String whereEndEmail;
    // 条件：邀请码，等于
    private String whereInviteCode;
    // 条件：邀请码，在列表中
    private List<String> whereInInviteCodes;
    // 条件：邀请码，在列表中，or连接
    private List<String> whereInOrInviteCodes;
    // 排除条件：邀请码
    private String whereNotInviteCode;
    // 条件：邀请码，不在列表中
    private List<String> whereNotInInviteCodes;
    // 条件：邀请码，为空
    private Boolean whereIsNullInviteCode;
    // 条件：邀请码，不为空
    private Boolean whereIsNotNullInviteCode;
    // 条件：邀请码，为空字符串
    private Boolean whereIsEmptyInviteCode;
    // 条件：邀请码，不为空字符串
    private Boolean whereIsNotEmptyInviteCode;
    // 条件：邀请码，大于
    private String whereGtInviteCode;
    // 条件：邀请码，大于等于
    private String whereGteInviteCode;
    // 条件：邀请码，小于
    private String whereLtInviteCode;
    // 条件：邀请码，小于等于
    private String whereLteInviteCode;
    // 条件：邀请码，模糊查询
    private String whereLikeInviteCode;
    // 条件：邀请码，开始范围
    private String whereStartInviteCode;
    // 条件：邀请码，结束范围
    private String whereEndInviteCode;
    // 条件：邀请者，等于
    private Integer whereInviter;
    // 条件：邀请者，在列表中
    private List<Integer> whereInInviters;
    // 条件：邀请者，在列表中，or连接
    private List<Integer> whereInOrInviters;
    // 排除条件：邀请者
    private Integer whereNotInviter;
    // 条件：邀请者，不在列表中
    private List<Integer> whereNotInInviters;
    // 条件：邀请者，为空
    private Boolean whereIsNullInviter;
    // 条件：邀请者，不为空
    private Boolean whereIsNotNullInviter;
    // 条件：邀请者，为空字符串
    private Boolean whereIsEmptyInviter;
    // 条件：邀请者，不为空字符串
    private Boolean whereIsNotEmptyInviter;
    // 条件：邀请者，大于
    private Integer whereGtInviter;
    // 条件：邀请者，大于等于
    private Integer whereGteInviter;
    // 条件：邀请者，小于
    private Integer whereLtInviter;
    // 条件：邀请者，小于等于
    private Integer whereLteInviter;
    // 条件：邀请者，开始范围
    private Integer whereStartInviter;
    // 条件：邀请者，结束范围
    private Integer whereEndInviter;
    // 条件：注册来源，等于
    private Integer whereSource;
    // 条件：注册来源，在列表中
    private List<Integer> whereInSources;
    // 条件：注册来源，在列表中，or连接
    private List<Integer> whereInOrSources;
    // 排除条件：注册来源
    private Integer whereNotSource;
    // 条件：注册来源，不在列表中
    private List<Integer> whereNotInSources;
    // 条件：注册来源，为空
    private Boolean whereIsNullSource;
    // 条件：注册来源，不为空
    private Boolean whereIsNotNullSource;
    // 条件：注册来源，为空字符串
    private Boolean whereIsEmptySource;
    // 条件：注册来源，不为空字符串
    private Boolean whereIsNotEmptySource;
    // 条件：注册来源，大于
    private Integer whereGtSource;
    // 条件：注册来源，大于等于
    private Integer whereGteSource;
    // 条件：注册来源，小于
    private Integer whereLtSource;
    // 条件：注册来源，小于等于
    private Integer whereLteSource;
    // 条件：注册来源，开始范围
    private Integer whereStartSource;
    // 条件：注册来源，结束范围
    private Integer whereEndSource;
    // 条件：头像，等于
    private String whereAvatar;
    // 条件：头像，在列表中
    private List<String> whereInAvatars;
    // 条件：头像，在列表中，or连接
    private List<String> whereInOrAvatars;
    // 排除条件：头像
    private String whereNotAvatar;
    // 条件：头像，不在列表中
    private List<String> whereNotInAvatars;
    // 条件：头像，为空
    private Boolean whereIsNullAvatar;
    // 条件：头像，不为空
    private Boolean whereIsNotNullAvatar;
    // 条件：头像，为空字符串
    private Boolean whereIsEmptyAvatar;
    // 条件：头像，不为空字符串
    private Boolean whereIsNotEmptyAvatar;
    // 条件：头像，大于
    private String whereGtAvatar;
    // 条件：头像，大于等于
    private String whereGteAvatar;
    // 条件：头像，小于
    private String whereLtAvatar;
    // 条件：头像，小于等于
    private String whereLteAvatar;
    // 条件：头像，模糊查询
    private String whereLikeAvatar;
    // 条件：头像，开始范围
    private String whereStartAvatar;
    // 条件：头像，结束范围
    private String whereEndAvatar;
    // 条件：真实姓名，等于
    private String whereRealName;
    // 条件：真实姓名，在列表中
    private List<String> whereInRealNames;
    // 条件：真实姓名，在列表中，or连接
    private List<String> whereInOrRealNames;
    // 排除条件：真实姓名
    private String whereNotRealName;
    // 条件：真实姓名，不在列表中
    private List<String> whereNotInRealNames;
    // 条件：真实姓名，为空
    private Boolean whereIsNullRealName;
    // 条件：真实姓名，不为空
    private Boolean whereIsNotNullRealName;
    // 条件：真实姓名，为空字符串
    private Boolean whereIsEmptyRealName;
    // 条件：真实姓名，不为空字符串
    private Boolean whereIsNotEmptyRealName;
    // 条件：真实姓名，大于
    private String whereGtRealName;
    // 条件：真实姓名，大于等于
    private String whereGteRealName;
    // 条件：真实姓名，小于
    private String whereLtRealName;
    // 条件：真实姓名，小于等于
    private String whereLteRealName;
    // 条件：真实姓名，模糊查询
    private String whereLikeRealName;
    // 条件：真实姓名，开始范围
    private String whereStartRealName;
    // 条件：真实姓名，结束范围
    private String whereEndRealName;
    // 条件：昵称，等于
    private String whereNickName;
    // 条件：昵称，在列表中
    private List<String> whereInNickNames;
    // 条件：昵称，在列表中，or连接
    private List<String> whereInOrNickNames;
    // 排除条件：昵称
    private String whereNotNickName;
    // 条件：昵称，不在列表中
    private List<String> whereNotInNickNames;
    // 条件：昵称，为空
    private Boolean whereIsNullNickName;
    // 条件：昵称，不为空
    private Boolean whereIsNotNullNickName;
    // 条件：昵称，为空字符串
    private Boolean whereIsEmptyNickName;
    // 条件：昵称，不为空字符串
    private Boolean whereIsNotEmptyNickName;
    // 条件：昵称，大于
    private String whereGtNickName;
    // 条件：昵称，大于等于
    private String whereGteNickName;
    // 条件：昵称，小于
    private String whereLtNickName;
    // 条件：昵称，小于等于
    private String whereLteNickName;
    // 条件：昵称，模糊查询
    private String whereLikeNickName;
    // 条件：昵称，开始范围
    private String whereStartNickName;
    // 条件：昵称，结束范围
    private String whereEndNickName;
    // 条件：状态。1：正常，2:禁用，3：注销，等于
    private Integer whereStatus;
    // 条件：状态。1：正常，2:禁用，3：注销，在列表中
    private List<Integer> whereInStatuss;
    // 条件：状态。1：正常，2:禁用，3：注销，在列表中，or连接
    private List<Integer> whereInOrStatuss;
    // 排除条件：状态。1：正常，2:禁用，3：注销
    private Integer whereNotStatus;
    // 条件：状态。1：正常，2:禁用，3：注销，不在列表中
    private List<Integer> whereNotInStatuss;
    // 条件：状态。1：正常，2:禁用，3：注销，为空
    private Boolean whereIsNullStatus;
    // 条件：状态。1：正常，2:禁用，3：注销，不为空
    private Boolean whereIsNotNullStatus;
    // 条件：状态。1：正常，2:禁用，3：注销，为空字符串
    private Boolean whereIsEmptyStatus;
    // 条件：状态。1：正常，2:禁用，3：注销，不为空字符串
    private Boolean whereIsNotEmptyStatus;
    // 条件：状态。1：正常，2:禁用，3：注销，大于
    private Integer whereGtStatus;
    // 条件：状态。1：正常，2:禁用，3：注销，大于等于
    private Integer whereGteStatus;
    // 条件：状态。1：正常，2:禁用，3：注销，小于
    private Integer whereLtStatus;
    // 条件：状态。1：正常，2:禁用，3：注销，小于等于
    private Integer whereLteStatus;
    // 条件：状态。1：正常，2:禁用，3：注销，开始范围
    private Integer whereStartStatus;
    // 条件：状态。1：正常，2:禁用，3：注销，结束范围
    private Integer whereEndStatus;
    // 条件：性别。1：男，2：女，3：保密，等于
    private Integer whereGender;
    // 条件：性别。1：男，2：女，3：保密，在列表中
    private List<Integer> whereInGenders;
    // 条件：性别。1：男，2：女，3：保密，在列表中，or连接
    private List<Integer> whereInOrGenders;
    // 排除条件：性别。1：男，2：女，3：保密
    private Integer whereNotGender;
    // 条件：性别。1：男，2：女，3：保密，不在列表中
    private List<Integer> whereNotInGenders;
    // 条件：性别。1：男，2：女，3：保密，为空
    private Boolean whereIsNullGender;
    // 条件：性别。1：男，2：女，3：保密，不为空
    private Boolean whereIsNotNullGender;
    // 条件：性别。1：男，2：女，3：保密，为空字符串
    private Boolean whereIsEmptyGender;
    // 条件：性别。1：男，2：女，3：保密，不为空字符串
    private Boolean whereIsNotEmptyGender;
    // 条件：性别。1：男，2：女，3：保密，大于
    private Integer whereGtGender;
    // 条件：性别。1：男，2：女，3：保密，大于等于
    private Integer whereGteGender;
    // 条件：性别。1：男，2：女，3：保密，小于
    private Integer whereLtGender;
    // 条件：性别。1：男，2：女，3：保密，小于等于
    private Integer whereLteGender;
    // 条件：性别。1：男，2：女，3：保密，开始范围
    private Integer whereStartGender;
    // 条件：性别。1：男，2：女，3：保密，结束范围
    private Integer whereEndGender;
    // 条件：简介，等于
    private String whereIntroduction;
    // 条件：简介，在列表中
    private List<String> whereInIntroductions;
    // 条件：简介，在列表中，or连接
    private List<String> whereInOrIntroductions;
    // 排除条件：简介
    private String whereNotIntroduction;
    // 条件：简介，不在列表中
    private List<String> whereNotInIntroductions;
    // 条件：简介，为空
    private Boolean whereIsNullIntroduction;
    // 条件：简介，不为空
    private Boolean whereIsNotNullIntroduction;
    // 条件：简介，为空字符串
    private Boolean whereIsEmptyIntroduction;
    // 条件：简介，不为空字符串
    private Boolean whereIsNotEmptyIntroduction;
    // 条件：简介，大于
    private String whereGtIntroduction;
    // 条件：简介，大于等于
    private String whereGteIntroduction;
    // 条件：简介，小于
    private String whereLtIntroduction;
    // 条件：简介，小于等于
    private String whereLteIntroduction;
    // 条件：简介，模糊查询
    private String whereLikeIntroduction;
    // 条件：简介，开始范围
    private String whereStartIntroduction;
    // 条件：简介，结束范围
    private String whereEndIntroduction;
    // 条件：所属部门ID，等于
    private Integer whereDepartmentId;
    // 条件：所属部门ID，在列表中
    private List<Integer> whereInDepartmentIds;
    // 条件：所属部门ID，在列表中，or连接
    private List<Integer> whereInOrDepartmentIds;
    // 排除条件：所属部门ID
    private Integer whereNotDepartmentId;
    // 条件：所属部门ID，不在列表中
    private List<Integer> whereNotInDepartmentIds;
    // 条件：所属部门ID，为空
    private Boolean whereIsNullDepartmentId;
    // 条件：所属部门ID，不为空
    private Boolean whereIsNotNullDepartmentId;
    // 条件：所属部门ID，为空字符串
    private Boolean whereIsEmptyDepartmentId;
    // 条件：所属部门ID，不为空字符串
    private Boolean whereIsNotEmptyDepartmentId;
    // 条件：所属部门ID，大于
    private Integer whereGtDepartmentId;
    // 条件：所属部门ID，大于等于
    private Integer whereGteDepartmentId;
    // 条件：所属部门ID，小于
    private Integer whereLtDepartmentId;
    // 条件：所属部门ID，小于等于
    private Integer whereLteDepartmentId;
    // 条件：所属部门ID，开始范围
    private Integer whereStartDepartmentId;
    // 条件：所属部门ID，结束范围
    private Integer whereEndDepartmentId;
    // 条件：身份证号，等于
    private String whereCardId;
    // 条件：身份证号，在列表中
    private List<String> whereInCardIds;
    // 条件：身份证号，在列表中，or连接
    private List<String> whereInOrCardIds;
    // 排除条件：身份证号
    private String whereNotCardId;
    // 条件：身份证号，不在列表中
    private List<String> whereNotInCardIds;
    // 条件：身份证号，为空
    private Boolean whereIsNullCardId;
    // 条件：身份证号，不为空
    private Boolean whereIsNotNullCardId;
    // 条件：身份证号，为空字符串
    private Boolean whereIsEmptyCardId;
    // 条件：身份证号，不为空字符串
    private Boolean whereIsNotEmptyCardId;
    // 条件：身份证号，大于
    private String whereGtCardId;
    // 条件：身份证号，大于等于
    private String whereGteCardId;
    // 条件：身份证号，小于
    private String whereLtCardId;
    // 条件：身份证号，小于等于
    private String whereLteCardId;
    // 条件：身份证号，模糊查询
    private String whereLikeCardId;
    // 条件：身份证号，开始范围
    private String whereStartCardId;
    // 条件：身份证号，结束范围
    private String whereEndCardId;
    // 条件：生日，等于
    private String whereBirthDate;
    // 条件：生日，在列表中
    private List<String> whereInBirthDates;
    // 条件：生日，在列表中，or连接
    private List<String> whereInOrBirthDates;
    // 排除条件：生日
    private String whereNotBirthDate;
    // 条件：生日，不在列表中
    private List<String> whereNotInBirthDates;
    // 条件：生日，为空
    private Boolean whereIsNullBirthDate;
    // 条件：生日，不为空
    private Boolean whereIsNotNullBirthDate;
    // 条件：生日，为空字符串
    private Boolean whereIsEmptyBirthDate;
    // 条件：生日，不为空字符串
    private Boolean whereIsNotEmptyBirthDate;
    // 条件：生日，大于
    private String whereGtBirthDate;
    // 条件：生日，大于等于
    private String whereGteBirthDate;
    // 条件：生日，小于
    private String whereLtBirthDate;
    // 条件：生日，小于等于
    private String whereLteBirthDate;
    // 条件：生日，开始范围
    private String whereStartBirthDate;
    // 条件：生日，结束范围
    private String whereEndBirthDate;
    // 条件：身高，等于
    private Integer whereHeight;
    // 条件：身高，在列表中
    private List<Integer> whereInHeights;
    // 条件：身高，在列表中，or连接
    private List<Integer> whereInOrHeights;
    // 排除条件：身高
    private Integer whereNotHeight;
    // 条件：身高，不在列表中
    private List<Integer> whereNotInHeights;
    // 条件：身高，为空
    private Boolean whereIsNullHeight;
    // 条件：身高，不为空
    private Boolean whereIsNotNullHeight;
    // 条件：身高，为空字符串
    private Boolean whereIsEmptyHeight;
    // 条件：身高，不为空字符串
    private Boolean whereIsNotEmptyHeight;
    // 条件：身高，大于
    private Integer whereGtHeight;
    // 条件：身高，大于等于
    private Integer whereGteHeight;
    // 条件：身高，小于
    private Integer whereLtHeight;
    // 条件：身高，小于等于
    private Integer whereLteHeight;
    // 条件：身高，开始范围
    private Integer whereStartHeight;
    // 条件：身高，结束范围
    private Integer whereEndHeight;
    // 条件：省，等于
    private Long whereProvince;
    // 条件：省，在列表中
    private List<Long> whereInProvinces;
    // 条件：省，在列表中，or连接
    private List<Long> whereInOrProvinces;
    // 排除条件：省
    private Long whereNotProvince;
    // 条件：省，不在列表中
    private List<Long> whereNotInProvinces;
    // 条件：省，为空
    private Boolean whereIsNullProvince;
    // 条件：省，不为空
    private Boolean whereIsNotNullProvince;
    // 条件：省，为空字符串
    private Boolean whereIsEmptyProvince;
    // 条件：省，不为空字符串
    private Boolean whereIsNotEmptyProvince;
    // 条件：省，大于
    private Long whereGtProvince;
    // 条件：省，大于等于
    private Long whereGteProvince;
    // 条件：省，小于
    private Long whereLtProvince;
    // 条件：省，小于等于
    private Long whereLteProvince;
    // 条件：省，开始范围
    private Long whereStartProvince;
    // 条件：省，结束范围
    private Long whereEndProvince;
    // 条件：市，等于
    private Long whereCity;
    // 条件：市，在列表中
    private List<Long> whereInCitys;
    // 条件：市，在列表中，or连接
    private List<Long> whereInOrCitys;
    // 排除条件：市
    private Long whereNotCity;
    // 条件：市，不在列表中
    private List<Long> whereNotInCitys;
    // 条件：市，为空
    private Boolean whereIsNullCity;
    // 条件：市，不为空
    private Boolean whereIsNotNullCity;
    // 条件：市，为空字符串
    private Boolean whereIsEmptyCity;
    // 条件：市，不为空字符串
    private Boolean whereIsNotEmptyCity;
    // 条件：市，大于
    private Long whereGtCity;
    // 条件：市，大于等于
    private Long whereGteCity;
    // 条件：市，小于
    private Long whereLtCity;
    // 条件：市，小于等于
    private Long whereLteCity;
    // 条件：市，开始范围
    private Long whereStartCity;
    // 条件：市，结束范围
    private Long whereEndCity;
    // 条件：区县，等于
    private Long whereDistrict;
    // 条件：区县，在列表中
    private List<Long> whereInDistricts;
    // 条件：区县，在列表中，or连接
    private List<Long> whereInOrDistricts;
    // 排除条件：区县
    private Long whereNotDistrict;
    // 条件：区县，不在列表中
    private List<Long> whereNotInDistricts;
    // 条件：区县，为空
    private Boolean whereIsNullDistrict;
    // 条件：区县，不为空
    private Boolean whereIsNotNullDistrict;
    // 条件：区县，为空字符串
    private Boolean whereIsEmptyDistrict;
    // 条件：区县，不为空字符串
    private Boolean whereIsNotEmptyDistrict;
    // 条件：区县，大于
    private Long whereGtDistrict;
    // 条件：区县，大于等于
    private Long whereGteDistrict;
    // 条件：区县，小于
    private Long whereLtDistrict;
    // 条件：区县，小于等于
    private Long whereLteDistrict;
    // 条件：区县，开始范围
    private Long whereStartDistrict;
    // 条件：区县，结束范围
    private Long whereEndDistrict;
    // 条件：详细地址，等于
    private String whereAddress;
    // 条件：详细地址，在列表中
    private List<String> whereInAddresss;
    // 条件：详细地址，在列表中，or连接
    private List<String> whereInOrAddresss;
    // 排除条件：详细地址
    private String whereNotAddress;
    // 条件：详细地址，不在列表中
    private List<String> whereNotInAddresss;
    // 条件：详细地址，为空
    private Boolean whereIsNullAddress;
    // 条件：详细地址，不为空
    private Boolean whereIsNotNullAddress;
    // 条件：详细地址，为空字符串
    private Boolean whereIsEmptyAddress;
    // 条件：详细地址，不为空字符串
    private Boolean whereIsNotEmptyAddress;
    // 条件：详细地址，大于
    private String whereGtAddress;
    // 条件：详细地址，大于等于
    private String whereGteAddress;
    // 条件：详细地址，小于
    private String whereLtAddress;
    // 条件：详细地址，小于等于
    private String whereLteAddress;
    // 条件：详细地址，模糊查询
    private String whereLikeAddress;
    // 条件：详细地址，开始范围
    private String whereStartAddress;
    // 条件：详细地址，结束范围
    private String whereEndAddress;
    // 条件：余额，等于
    private Integer whereBalance;
    // 条件：余额，在列表中
    private List<Integer> whereInBalances;
    // 条件：余额，在列表中，or连接
    private List<Integer> whereInOrBalances;
    // 排除条件：余额
    private Integer whereNotBalance;
    // 条件：余额，不在列表中
    private List<Integer> whereNotInBalances;
    // 条件：余额，为空
    private Boolean whereIsNullBalance;
    // 条件：余额，不为空
    private Boolean whereIsNotNullBalance;
    // 条件：余额，为空字符串
    private Boolean whereIsEmptyBalance;
    // 条件：余额，不为空字符串
    private Boolean whereIsNotEmptyBalance;
    // 条件：余额，大于
    private Integer whereGtBalance;
    // 条件：余额，大于等于
    private Integer whereGteBalance;
    // 条件：余额，小于
    private Integer whereLtBalance;
    // 条件：余额，小于等于
    private Integer whereLteBalance;
    // 条件：余额，开始范围
    private Integer whereStartBalance;
    // 条件：余额，结束范围
    private Integer whereEndBalance;
    // 条件：备注，等于
    private String whereRemark;
    // 条件：备注，在列表中
    private List<String> whereInRemarks;
    // 条件：备注，在列表中，or连接
    private List<String> whereInOrRemarks;
    // 排除条件：备注
    private String whereNotRemark;
    // 条件：备注，不在列表中
    private List<String> whereNotInRemarks;
    // 条件：备注，为空
    private Boolean whereIsNullRemark;
    // 条件：备注，不为空
    private Boolean whereIsNotNullRemark;
    // 条件：备注，为空字符串
    private Boolean whereIsEmptyRemark;
    // 条件：备注，不为空字符串
    private Boolean whereIsNotEmptyRemark;
    // 条件：备注，大于
    private String whereGtRemark;
    // 条件：备注，大于等于
    private String whereGteRemark;
    // 条件：备注，小于
    private String whereLtRemark;
    // 条件：备注，小于等于
    private String whereLteRemark;
    // 条件：备注，模糊查询
    private String whereLikeRemark;
    // 条件：备注，开始范围
    private String whereStartRemark;
    // 条件：备注，结束范围
    private String whereEndRemark;
    // 条件：是否是VIP。1：是，2：否。，等于
    private Integer whereIsVip;
    // 条件：是否是VIP。1：是，2：否。，在列表中
    private List<Integer> whereInIsVips;
    // 条件：是否是VIP。1：是，2：否。，在列表中，or连接
    private List<Integer> whereInOrIsVips;
    // 排除条件：是否是VIP。1：是，2：否。
    private Integer whereNotIsVip;
    // 条件：是否是VIP。1：是，2：否。，不在列表中
    private List<Integer> whereNotInIsVips;
    // 条件：是否是VIP。1：是，2：否。，为空
    private Boolean whereIsNullIsVip;
    // 条件：是否是VIP。1：是，2：否。，不为空
    private Boolean whereIsNotNullIsVip;
    // 条件：是否是VIP。1：是，2：否。，为空字符串
    private Boolean whereIsEmptyIsVip;
    // 条件：是否是VIP。1：是，2：否。，不为空字符串
    private Boolean whereIsNotEmptyIsVip;
    // 条件：是否是VIP。1：是，2：否。，大于
    private Integer whereGtIsVip;
    // 条件：是否是VIP。1：是，2：否。，大于等于
    private Integer whereGteIsVip;
    // 条件：是否是VIP。1：是，2：否。，小于
    private Integer whereLtIsVip;
    // 条件：是否是VIP。1：是，2：否。，小于等于
    private Integer whereLteIsVip;
    // 条件：是否是VIP。1：是，2：否。，开始范围
    private Integer whereStartIsVip;
    // 条件：是否是VIP。1：是，2：否。，结束范围
    private Integer whereEndIsVip;
    // 条件：创建时间，等于
    private String whereCreatedAt;
    // 条件：创建时间，在列表中
    private List<String> whereInCreatedAts;
    // 条件：创建时间，在列表中，or连接
    private List<String> whereInOrCreatedAts;
    // 排除条件：创建时间
    private String whereNotCreatedAt;
    // 条件：创建时间，不在列表中
    private List<String> whereNotInCreatedAts;
    // 条件：创建时间，为空
    private Boolean whereIsNullCreatedAt;
    // 条件：创建时间，不为空
    private Boolean whereIsNotNullCreatedAt;
    // 条件：创建时间，为空字符串
    private Boolean whereIsEmptyCreatedAt;
    // 条件：创建时间，不为空字符串
    private Boolean whereIsNotEmptyCreatedAt;
    // 条件：创建时间，大于
    private String whereGtCreatedAt;
    // 条件：创建时间，大于等于
    private String whereGteCreatedAt;
    // 条件：创建时间，小于
    private String whereLtCreatedAt;
    // 条件：创建时间，小于等于
    private String whereLteCreatedAt;
    // 条件：创建时间，开始范围
    private String whereStartCreatedAt;
    // 条件：创建时间，结束范围
    private String whereEndCreatedAt;
    // 条件：创建者，等于
    private Integer whereCreatedUserId;
    // 条件：创建者，在列表中
    private List<Integer> whereInCreatedUserIds;
    // 条件：创建者，在列表中，or连接
    private List<Integer> whereInOrCreatedUserIds;
    // 排除条件：创建者
    private Integer whereNotCreatedUserId;
    // 条件：创建者，不在列表中
    private List<Integer> whereNotInCreatedUserIds;
    // 条件：创建者，为空
    private Boolean whereIsNullCreatedUserId;
    // 条件：创建者，不为空
    private Boolean whereIsNotNullCreatedUserId;
    // 条件：创建者，为空字符串
    private Boolean whereIsEmptyCreatedUserId;
    // 条件：创建者，不为空字符串
    private Boolean whereIsNotEmptyCreatedUserId;
    // 条件：创建者，大于
    private Integer whereGtCreatedUserId;
    // 条件：创建者，大于等于
    private Integer whereGteCreatedUserId;
    // 条件：创建者，小于
    private Integer whereLtCreatedUserId;
    // 条件：创建者，小于等于
    private Integer whereLteCreatedUserId;
    // 条件：创建者，开始范围
    private Integer whereStartCreatedUserId;
    // 条件：创建者，结束范围
    private Integer whereEndCreatedUserId;
    // 条件：更新时间，等于
    private String whereUpdatedAt;
    // 条件：更新时间，在列表中
    private List<String> whereInUpdatedAts;
    // 条件：更新时间，在列表中，or连接
    private List<String> whereInOrUpdatedAts;
    // 排除条件：更新时间
    private String whereNotUpdatedAt;
    // 条件：更新时间，不在列表中
    private List<String> whereNotInUpdatedAts;
    // 条件：更新时间，为空
    private Boolean whereIsNullUpdatedAt;
    // 条件：更新时间，不为空
    private Boolean whereIsNotNullUpdatedAt;
    // 条件：更新时间，为空字符串
    private Boolean whereIsEmptyUpdatedAt;
    // 条件：更新时间，不为空字符串
    private Boolean whereIsNotEmptyUpdatedAt;
    // 条件：更新时间，大于
    private String whereGtUpdatedAt;
    // 条件：更新时间，大于等于
    private String whereGteUpdatedAt;
    // 条件：更新时间，小于
    private String whereLtUpdatedAt;
    // 条件：更新时间，小于等于
    private String whereLteUpdatedAt;
    // 条件：更新时间，开始范围
    private String whereStartUpdatedAt;
    // 条件：更新时间，结束范围
    private String whereEndUpdatedAt;
    // 条件：最后更新者，等于
    private Integer whereUpdatedUserId;
    // 条件：最后更新者，在列表中
    private List<Integer> whereInUpdatedUserIds;
    // 条件：最后更新者，在列表中，or连接
    private List<Integer> whereInOrUpdatedUserIds;
    // 排除条件：最后更新者
    private Integer whereNotUpdatedUserId;
    // 条件：最后更新者，不在列表中
    private List<Integer> whereNotInUpdatedUserIds;
    // 条件：最后更新者，为空
    private Boolean whereIsNullUpdatedUserId;
    // 条件：最后更新者，不为空
    private Boolean whereIsNotNullUpdatedUserId;
    // 条件：最后更新者，为空字符串
    private Boolean whereIsEmptyUpdatedUserId;
    // 条件：最后更新者，不为空字符串
    private Boolean whereIsNotEmptyUpdatedUserId;
    // 条件：最后更新者，大于
    private Integer whereGtUpdatedUserId;
    // 条件：最后更新者，大于等于
    private Integer whereGteUpdatedUserId;
    // 条件：最后更新者，小于
    private Integer whereLtUpdatedUserId;
    // 条件：最后更新者，小于等于
    private Integer whereLteUpdatedUserId;
    // 条件：最后更新者，开始范围
    private Integer whereStartUpdatedUserId;
    // 条件：最后更新者，结束范围
    private Integer whereEndUpdatedUserId;

    // 是否使用distinct
    private Boolean useDistinct;
    
    // group by字段列表
    private List<String> groupByFields;
    
    // having条件
    private String havingClause;
    
    /**
     * 添加group by字段
     * @param field 字段名称
     * @return 当前PO实例
     */
    public TbBasicSysUserPo addGroupByField(String field) {
        if (this.groupByFields == null) {
            this.groupByFields = new ArrayList<>();
        }
        this.groupByFields.add(field);
        return this;
    }
    
    /**
     * 设置group by字段列表
     * @param groupByFields 字段名称列表
     * @return 当前PO实例
     */
    public TbBasicSysUserPo setGroupByFields(List<String> groupByFields) {
        this.groupByFields = groupByFields;
        return this;
    }
}
