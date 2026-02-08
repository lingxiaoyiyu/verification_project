package com.api.apps.data.po;

import com.api.apps.data.entity.TbApps;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.SuperBuilder;

import java.util.List;

/**
 * 应用信息表条件实体
 */
@EqualsAndHashCode(doNotUseGetters = true, callSuper = true)
@Data
@SuperBuilder
public class TbAppsPo extends TbApps {

    public TbAppsPo(){
        super();
    }

    // 条件：，等于
    private String whereId;
    // 条件：，在列表中
    private List<String> whereInIds;
    // 条件：，在列表中，or连接
    private List<String> whereInOrIds;
    // 排除条件：
    private String whereNotId;
    // 条件：，不在列表中
    private List<String> whereNotInIds;
    // 条件：，模糊查询
    private String whereLikeId;
    // 条件：，开始范围
    private String whereStartId;
    // 条件：，结束范围
    private String whereEndId;
    // 条件：应用秘钥，等于
    private String whereAppSecret;
    // 条件：应用秘钥，在列表中
    private List<String> whereInAppSecrets;
    // 条件：应用秘钥，在列表中，or连接
    private List<String> whereInOrAppSecrets;
    // 排除条件：应用秘钥
    private String whereNotAppSecret;
    // 条件：应用秘钥，不在列表中
    private List<String> whereNotInAppSecrets;
    // 条件：应用秘钥，模糊查询
    private String whereLikeAppSecret;
    // 条件：应用秘钥，开始范围
    private String whereStartAppSecret;
    // 条件：应用秘钥，结束范围
    private String whereEndAppSecret;
    // 条件：应用类型。1：宿主APP，2：小程序，3：网站。4：第三方应用。，等于
    private Integer whereType;
    // 条件：应用类型。1：宿主APP，2：小程序，3：网站。4：第三方应用。，在列表中
    private List<Integer> whereInTypes;
    // 条件：应用类型。1：宿主APP，2：小程序，3：网站。4：第三方应用。，在列表中，or连接
    private List<Integer> whereInOrTypes;
    // 排除条件：应用类型。1：宿主APP，2：小程序，3：网站。4：第三方应用。
    private Integer whereNotType;
    // 条件：应用类型。1：宿主APP，2：小程序，3：网站。4：第三方应用。，不在列表中
    private List<Integer> whereNotInTypes;
    // 条件：应用类型。1：宿主APP，2：小程序，3：网站。4：第三方应用。，开始范围
    private Integer whereStartType;
    // 条件：应用类型。1：宿主APP，2：小程序，3：网站。4：第三方应用。，结束范围
    private Integer whereEndType;
    // 条件：排序ID，等于
    private Integer whereSort;
    // 条件：排序ID，在列表中
    private List<Integer> whereInSorts;
    // 条件：排序ID，在列表中，or连接
    private List<Integer> whereInOrSorts;
    // 排除条件：排序ID
    private Integer whereNotSort;
    // 条件：排序ID，不在列表中
    private List<Integer> whereNotInSorts;
    // 条件：排序ID，开始范围
    private Integer whereStartSort;
    // 条件：排序ID，结束范围
    private Integer whereEndSort;
    // 条件：uniapp_id，等于
    private String whereUniAppid;
    // 条件：uniapp_id，在列表中
    private List<String> whereInUniAppids;
    // 条件：uniapp_id，在列表中，or连接
    private List<String> whereInOrUniAppids;
    // 排除条件：uniapp_id
    private String whereNotUniAppid;
    // 条件：uniapp_id，不在列表中
    private List<String> whereNotInUniAppids;
    // 条件：uniapp_id，模糊查询
    private String whereLikeUniAppid;
    // 条件：uniapp_id，开始范围
    private String whereStartUniAppid;
    // 条件：uniapp_id，结束范围
    private String whereEndUniAppid;
    // 条件：允许访问类型。1：公开，2：受限（指定角色或指定用户可访问）。仅零号小程序和普通小程序的访问类型值有意义。，等于
    private Integer whereAccessType;
    // 条件：允许访问类型。1：公开，2：受限（指定角色或指定用户可访问）。仅零号小程序和普通小程序的访问类型值有意义。，在列表中
    private List<Integer> whereInAccessTypes;
    // 条件：允许访问类型。1：公开，2：受限（指定角色或指定用户可访问）。仅零号小程序和普通小程序的访问类型值有意义。，在列表中，or连接
    private List<Integer> whereInOrAccessTypes;
    // 排除条件：允许访问类型。1：公开，2：受限（指定角色或指定用户可访问）。仅零号小程序和普通小程序的访问类型值有意义。
    private Integer whereNotAccessType;
    // 条件：允许访问类型。1：公开，2：受限（指定角色或指定用户可访问）。仅零号小程序和普通小程序的访问类型值有意义。，不在列表中
    private List<Integer> whereNotInAccessTypes;
    // 条件：允许访问类型。1：公开，2：受限（指定角色或指定用户可访问）。仅零号小程序和普通小程序的访问类型值有意义。，开始范围
    private Integer whereStartAccessType;
    // 条件：允许访问类型。1：公开，2：受限（指定角色或指定用户可访问）。仅零号小程序和普通小程序的访问类型值有意义。，结束范围
    private Integer whereEndAccessType;
    // 条件：允许访问的角色，等于
    private String whereAccessRoleIds;
    // 条件：允许访问的角色，在列表中
    private List<String> whereInAccessRoleIdss;
    // 条件：允许访问的角色，在列表中，or连接
    private List<String> whereInOrAccessRoleIdss;
    // 排除条件：允许访问的角色
    private String whereNotAccessRoleIds;
    // 条件：允许访问的角色，不在列表中
    private List<String> whereNotInAccessRoleIdss;
    // 条件：允许访问的角色，模糊查询
    private String whereLikeAccessRoleIds;
    // 条件：允许访问的角色，开始范围
    private String whereStartAccessRoleIds;
    // 条件：允许访问的角色，结束范围
    private String whereEndAccessRoleIds;
    // 条件：允许访问的用户ID，等于
    private String whereAccessUserIds;
    // 条件：允许访问的用户ID，在列表中
    private List<String> whereInAccessUserIdss;
    // 条件：允许访问的用户ID，在列表中，or连接
    private List<String> whereInOrAccessUserIdss;
    // 排除条件：允许访问的用户ID
    private String whereNotAccessUserIds;
    // 条件：允许访问的用户ID，不在列表中
    private List<String> whereNotInAccessUserIdss;
    // 条件：允许访问的用户ID，模糊查询
    private String whereLikeAccessUserIds;
    // 条件：允许访问的用户ID，开始范围
    private String whereStartAccessUserIds;
    // 条件：允许访问的用户ID，结束范围
    private String whereEndAccessUserIds;
    // 条件：应用名称，等于
    private String whereName;
    // 条件：应用名称，在列表中
    private List<String> whereInNames;
    // 条件：应用名称，在列表中，or连接
    private List<String> whereInOrNames;
    // 排除条件：应用名称
    private String whereNotName;
    // 条件：应用名称，不在列表中
    private List<String> whereNotInNames;
    // 条件：应用名称，模糊查询
    private String whereLikeName;
    // 条件：应用名称，开始范围
    private String whereStartName;
    // 条件：应用名称，结束范围
    private String whereEndName;
    // 条件：应用简介，等于
    private String whereDesc;
    // 条件：应用简介，在列表中
    private List<String> whereInDescs;
    // 条件：应用简介，在列表中，or连接
    private List<String> whereInOrDescs;
    // 排除条件：应用简介
    private String whereNotDesc;
    // 条件：应用简介，不在列表中
    private List<String> whereNotInDescs;
    // 条件：应用简介，模糊查询
    private String whereLikeDesc;
    // 条件：应用简介，开始范围
    private String whereStartDesc;
    // 条件：应用简介，结束范围
    private String whereEndDesc;
    // 条件：应用图标，等于
    private String whereImg;
    // 条件：应用图标，在列表中
    private List<String> whereInImgs;
    // 条件：应用图标，在列表中，or连接
    private List<String> whereInOrImgs;
    // 排除条件：应用图标
    private String whereNotImg;
    // 条件：应用图标，不在列表中
    private List<String> whereNotInImgs;
    // 条件：应用图标，模糊查询
    private String whereLikeImg;
    // 条件：应用图标，开始范围
    private String whereStartImg;
    // 条件：应用图标，结束范围
    private String whereEndImg;
    // 条件：状态。1：启用，2：禁用，等于
    private Integer whereStatus;
    // 条件：状态。1：启用，2：禁用，在列表中
    private List<Integer> whereInStatuss;
    // 条件：状态。1：启用，2：禁用，在列表中，or连接
    private List<Integer> whereInOrStatuss;
    // 排除条件：状态。1：启用，2：禁用
    private Integer whereNotStatus;
    // 条件：状态。1：启用，2：禁用，不在列表中
    private List<Integer> whereNotInStatuss;
    // 条件：状态。1：启用，2：禁用，开始范围
    private Integer whereStartStatus;
    // 条件：状态。1：启用，2：禁用，结束范围
    private Integer whereEndStatus;
    // 条件：是否是入口小程序。1：是，2：否，等于
    private Integer whereIsEntranceMp;
    // 条件：是否是入口小程序。1：是，2：否，在列表中
    private List<Integer> whereInIsEntranceMps;
    // 条件：是否是入口小程序。1：是，2：否，在列表中，or连接
    private List<Integer> whereInOrIsEntranceMps;
    // 排除条件：是否是入口小程序。1：是，2：否
    private Integer whereNotIsEntranceMp;
    // 条件：是否是入口小程序。1：是，2：否，不在列表中
    private List<Integer> whereNotInIsEntranceMps;
    // 条件：是否是入口小程序。1：是，2：否，开始范围
    private Integer whereStartIsEntranceMp;
    // 条件：是否是入口小程序。1：是，2：否，结束范围
    private Integer whereEndIsEntranceMp;
    // 条件：分类ID，等于
    private Integer whereCategoryId;
    // 条件：分类ID，在列表中
    private List<Integer> whereInCategoryIds;
    // 条件：分类ID，在列表中，or连接
    private List<Integer> whereInOrCategoryIds;
    // 排除条件：分类ID
    private Integer whereNotCategoryId;
    // 条件：分类ID，不在列表中
    private List<Integer> whereNotInCategoryIds;
    // 条件：分类ID，开始范围
    private Integer whereStartCategoryId;
    // 条件：分类ID，结束范围
    private Integer whereEndCategoryId;
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
    // 条件：最后更新者，开始范围
    private Integer whereStartUpdatedUserId;
    // 条件：最后更新者，结束范围
    private Integer whereEndUpdatedUserId;
}
