package com.api.apps.data.po;

import com.api.apps.data.entity.TbAppsVersion;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.SuperBuilder;

import java.util.List;

/**
 * 应用版本信息表条件实体
 */
@EqualsAndHashCode(doNotUseGetters = true, callSuper = true)
@Data
@SuperBuilder
public class TbAppsVersionPo extends TbAppsVersion {

    public TbAppsVersionPo(){
        super();
    }

    // 条件：，等于
    private Integer whereId;
    // 条件：，在列表中
    private List<Integer> whereInIds;
    // 条件：，在列表中，or连接
    private List<Integer> whereInOrIds;
    // 排除条件：
    private Integer whereNotId;
    // 条件：，不在列表中
    private List<Integer> whereNotInIds;
    // 条件：，开始范围
    private Integer whereStartId;
    // 条件：，结束范围
    private Integer whereEndId;
    // 条件：应用ID，等于
    private String whereAppId;
    // 条件：应用ID，在列表中
    private List<String> whereInAppIds;
    // 条件：应用ID，在列表中，or连接
    private List<String> whereInOrAppIds;
    // 排除条件：应用ID
    private String whereNotAppId;
    // 条件：应用ID，不在列表中
    private List<String> whereNotInAppIds;
    // 条件：应用ID，模糊查询
    private String whereLikeAppId;
    // 条件：应用ID，开始范围
    private String whereStartAppId;
    // 条件：应用ID，结束范围
    private String whereEndAppId;
    // 条件：版本号，等于
    private Integer whereVersionCode;
    // 条件：版本号，在列表中
    private List<Integer> whereInVersionCodes;
    // 条件：版本号，在列表中，or连接
    private List<Integer> whereInOrVersionCodes;
    // 排除条件：版本号
    private Integer whereNotVersionCode;
    // 条件：版本号，不在列表中
    private List<Integer> whereNotInVersionCodes;
    // 条件：版本号，开始范围
    private Integer whereStartVersionCode;
    // 条件：版本号，结束范围
    private Integer whereEndVersionCode;
    // 条件：版本名称，等于
    private String whereVersionName;
    // 条件：版本名称，在列表中
    private List<String> whereInVersionNames;
    // 条件：版本名称，在列表中，or连接
    private List<String> whereInOrVersionNames;
    // 排除条件：版本名称
    private String whereNotVersionName;
    // 条件：版本名称，不在列表中
    private List<String> whereNotInVersionNames;
    // 条件：版本名称，模糊查询
    private String whereLikeVersionName;
    // 条件：版本名称，开始范围
    private String whereStartVersionName;
    // 条件：版本名称，结束范围
    private String whereEndVersionName;
    // 条件：版本描述，等于
    private String whereDesc;
    // 条件：版本描述，在列表中
    private List<String> whereInDescs;
    // 条件：版本描述，在列表中，or连接
    private List<String> whereInOrDescs;
    // 排除条件：版本描述
    private String whereNotDesc;
    // 条件：版本描述，不在列表中
    private List<String> whereNotInDescs;
    // 条件：版本描述，模糊查询
    private String whereLikeDesc;
    // 条件：版本描述，开始范围
    private String whereStartDesc;
    // 条件：版本描述，结束范围
    private String whereEndDesc;
    // 条件：状态。1：下线，2：测试，3：正式上线。，等于
    private Integer whereStatus;
    // 条件：状态。1：下线，2：测试，3：正式上线。，在列表中
    private List<Integer> whereInStatuss;
    // 条件：状态。1：下线，2：测试，3：正式上线。，在列表中，or连接
    private List<Integer> whereInOrStatuss;
    // 排除条件：状态。1：下线，2：测试，3：正式上线。
    private Integer whereNotStatus;
    // 条件：状态。1：下线，2：测试，3：正式上线。，不在列表中
    private List<Integer> whereNotInStatuss;
    // 条件：状态。1：下线，2：测试，3：正式上线。，开始范围
    private Integer whereStartStatus;
    // 条件：状态。1：下线，2：测试，3：正式上线。，结束范围
    private Integer whereEndStatus;
    // 条件：下载地址，等于
    private String whereUrl;
    // 条件：下载地址，在列表中
    private List<String> whereInUrls;
    // 条件：下载地址，在列表中，or连接
    private List<String> whereInOrUrls;
    // 排除条件：下载地址
    private String whereNotUrl;
    // 条件：下载地址，不在列表中
    private List<String> whereNotInUrls;
    // 条件：下载地址，模糊查询
    private String whereLikeUrl;
    // 条件：下载地址，开始范围
    private String whereStartUrl;
    // 条件：下载地址，结束范围
    private String whereEndUrl;
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
