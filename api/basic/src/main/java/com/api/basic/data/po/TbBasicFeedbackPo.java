package com.api.basic.data.po;

import com.api.basic.data.entity.TbBasicFeedback;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.SuperBuilder;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * 问题反馈表条件实体
 */
@EqualsAndHashCode(doNotUseGetters = true, callSuper = true)
@Data
@SuperBuilder
public class TbBasicFeedbackPo extends TbBasicFeedback {

    public TbBasicFeedbackPo(){
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
    // 条件：反馈类型。1：Bug反馈，2：功能建议，3：使用咨询，4：其他，等于
    private Integer whereType;
    // 条件：反馈类型。1：Bug反馈，2：功能建议，3：使用咨询，4：其他，在列表中
    private List<Integer> whereInTypes;
    // 条件：反馈类型。1：Bug反馈，2：功能建议，3：使用咨询，4：其他，在列表中，or连接
    private List<Integer> whereInOrTypes;
    // 排除条件：反馈类型。1：Bug反馈，2：功能建议，3：使用咨询，4：其他
    private Integer whereNotType;
    // 条件：反馈类型。1：Bug反馈，2：功能建议，3：使用咨询，4：其他，不在列表中
    private List<Integer> whereNotInTypes;
    // 条件：反馈类型。1：Bug反馈，2：功能建议，3：使用咨询，4：其他，为空
    private Boolean whereIsNullType;
    // 条件：反馈类型。1：Bug反馈，2：功能建议，3：使用咨询，4：其他，不为空
    private Boolean whereIsNotNullType;
    // 条件：反馈类型。1：Bug反馈，2：功能建议，3：使用咨询，4：其他，为空字符串
    private Boolean whereIsEmptyType;
    // 条件：反馈类型。1：Bug反馈，2：功能建议，3：使用咨询，4：其他，不为空字符串
    private Boolean whereIsNotEmptyType;
    // 条件：反馈类型。1：Bug反馈，2：功能建议，3：使用咨询，4：其他，大于
    private Integer whereGtType;
    // 条件：反馈类型。1：Bug反馈，2：功能建议，3：使用咨询，4：其他，大于等于
    private Integer whereGteType;
    // 条件：反馈类型。1：Bug反馈，2：功能建议，3：使用咨询，4：其他，小于
    private Integer whereLtType;
    // 条件：反馈类型。1：Bug反馈，2：功能建议，3：使用咨询，4：其他，小于等于
    private Integer whereLteType;
    // 条件：反馈类型。1：Bug反馈，2：功能建议，3：使用咨询，4：其他，开始范围
    private Integer whereStartType;
    // 条件：反馈类型。1：Bug反馈，2：功能建议，3：使用咨询，4：其他，结束范围
    private Integer whereEndType;
    // 条件：反馈内容，等于
    private String whereContent;
    // 条件：反馈内容，在列表中
    private List<String> whereInContents;
    // 条件：反馈内容，在列表中，or连接
    private List<String> whereInOrContents;
    // 排除条件：反馈内容
    private String whereNotContent;
    // 条件：反馈内容，不在列表中
    private List<String> whereNotInContents;
    // 条件：反馈内容，为空
    private Boolean whereIsNullContent;
    // 条件：反馈内容，不为空
    private Boolean whereIsNotNullContent;
    // 条件：反馈内容，为空字符串
    private Boolean whereIsEmptyContent;
    // 条件：反馈内容，不为空字符串
    private Boolean whereIsNotEmptyContent;
    // 条件：反馈内容，大于
    private String whereGtContent;
    // 条件：反馈内容，大于等于
    private String whereGteContent;
    // 条件：反馈内容，小于
    private String whereLtContent;
    // 条件：反馈内容，小于等于
    private String whereLteContent;
    // 条件：反馈内容，模糊查询
    private String whereLikeContent;
    // 条件：反馈内容，开始范围
    private String whereStartContent;
    // 条件：反馈内容，结束范围
    private String whereEndContent;
    // 条件：反馈图片（JSON数组格式存储图片URL），等于
    private String whereImages;
    // 条件：反馈图片（JSON数组格式存储图片URL），在列表中
    private List<String> whereInImagess;
    // 条件：反馈图片（JSON数组格式存储图片URL），在列表中，or连接
    private List<String> whereInOrImagess;
    // 排除条件：反馈图片（JSON数组格式存储图片URL）
    private String whereNotImages;
    // 条件：反馈图片（JSON数组格式存储图片URL），不在列表中
    private List<String> whereNotInImagess;
    // 条件：反馈图片（JSON数组格式存储图片URL），为空
    private Boolean whereIsNullImages;
    // 条件：反馈图片（JSON数组格式存储图片URL），不为空
    private Boolean whereIsNotNullImages;
    // 条件：反馈图片（JSON数组格式存储图片URL），为空字符串
    private Boolean whereIsEmptyImages;
    // 条件：反馈图片（JSON数组格式存储图片URL），不为空字符串
    private Boolean whereIsNotEmptyImages;
    // 条件：反馈图片（JSON数组格式存储图片URL），大于
    private String whereGtImages;
    // 条件：反馈图片（JSON数组格式存储图片URL），大于等于
    private String whereGteImages;
    // 条件：反馈图片（JSON数组格式存储图片URL），小于
    private String whereLtImages;
    // 条件：反馈图片（JSON数组格式存储图片URL），小于等于
    private String whereLteImages;
    // 条件：反馈图片（JSON数组格式存储图片URL），模糊查询
    private String whereLikeImages;
    // 条件：反馈图片（JSON数组格式存储图片URL），开始范围
    private String whereStartImages;
    // 条件：反馈图片（JSON数组格式存储图片URL），结束范围
    private String whereEndImages;
    // 条件：联系方式，等于
    private String whereContact;
    // 条件：联系方式，在列表中
    private List<String> whereInContacts;
    // 条件：联系方式，在列表中，or连接
    private List<String> whereInOrContacts;
    // 排除条件：联系方式
    private String whereNotContact;
    // 条件：联系方式，不在列表中
    private List<String> whereNotInContacts;
    // 条件：联系方式，为空
    private Boolean whereIsNullContact;
    // 条件：联系方式，不为空
    private Boolean whereIsNotNullContact;
    // 条件：联系方式，为空字符串
    private Boolean whereIsEmptyContact;
    // 条件：联系方式，不为空字符串
    private Boolean whereIsNotEmptyContact;
    // 条件：联系方式，大于
    private String whereGtContact;
    // 条件：联系方式，大于等于
    private String whereGteContact;
    // 条件：联系方式，小于
    private String whereLtContact;
    // 条件：联系方式，小于等于
    private String whereLteContact;
    // 条件：联系方式，模糊查询
    private String whereLikeContact;
    // 条件：联系方式，开始范围
    private String whereStartContact;
    // 条件：联系方式，结束范围
    private String whereEndContact;
    // 条件：处理状态。1：待处理，2：处理中，3：已解决，4：已拒绝，等于
    private Integer whereStatus;
    // 条件：处理状态。1：待处理，2：处理中，3：已解决，4：已拒绝，在列表中
    private List<Integer> whereInStatuss;
    // 条件：处理状态。1：待处理，2：处理中，3：已解决，4：已拒绝，在列表中，or连接
    private List<Integer> whereInOrStatuss;
    // 排除条件：处理状态。1：待处理，2：处理中，3：已解决，4：已拒绝
    private Integer whereNotStatus;
    // 条件：处理状态。1：待处理，2：处理中，3：已解决，4：已拒绝，不在列表中
    private List<Integer> whereNotInStatuss;
    // 条件：处理状态。1：待处理，2：处理中，3：已解决，4：已拒绝，为空
    private Boolean whereIsNullStatus;
    // 条件：处理状态。1：待处理，2：处理中，3：已解决，4：已拒绝，不为空
    private Boolean whereIsNotNullStatus;
    // 条件：处理状态。1：待处理，2：处理中，3：已解决，4：已拒绝，为空字符串
    private Boolean whereIsEmptyStatus;
    // 条件：处理状态。1：待处理，2：处理中，3：已解决，4：已拒绝，不为空字符串
    private Boolean whereIsNotEmptyStatus;
    // 条件：处理状态。1：待处理，2：处理中，3：已解决，4：已拒绝，大于
    private Integer whereGtStatus;
    // 条件：处理状态。1：待处理，2：处理中，3：已解决，4：已拒绝，大于等于
    private Integer whereGteStatus;
    // 条件：处理状态。1：待处理，2：处理中，3：已解决，4：已拒绝，小于
    private Integer whereLtStatus;
    // 条件：处理状态。1：待处理，2：处理中，3：已解决，4：已拒绝，小于等于
    private Integer whereLteStatus;
    // 条件：处理状态。1：待处理，2：处理中，3：已解决，4：已拒绝，开始范围
    private Integer whereStartStatus;
    // 条件：处理状态。1：待处理，2：处理中，3：已解决，4：已拒绝，结束范围
    private Integer whereEndStatus;
    // 条件：是否已删除。1：未删除，2：已删除，等于
    private Integer whereIsDelete;
    // 条件：是否已删除。1：未删除，2：已删除，在列表中
    private List<Integer> whereInIsDeletes;
    // 条件：是否已删除。1：未删除，2：已删除，在列表中，or连接
    private List<Integer> whereInOrIsDeletes;
    // 排除条件：是否已删除。1：未删除，2：已删除
    private Integer whereNotIsDelete;
    // 条件：是否已删除。1：未删除，2：已删除，不在列表中
    private List<Integer> whereNotInIsDeletes;
    // 条件：是否已删除。1：未删除，2：已删除，为空
    private Boolean whereIsNullIsDelete;
    // 条件：是否已删除。1：未删除，2：已删除，不为空
    private Boolean whereIsNotNullIsDelete;
    // 条件：是否已删除。1：未删除，2：已删除，为空字符串
    private Boolean whereIsEmptyIsDelete;
    // 条件：是否已删除。1：未删除，2：已删除，不为空字符串
    private Boolean whereIsNotEmptyIsDelete;
    // 条件：是否已删除。1：未删除，2：已删除，大于
    private Integer whereGtIsDelete;
    // 条件：是否已删除。1：未删除，2：已删除，大于等于
    private Integer whereGteIsDelete;
    // 条件：是否已删除。1：未删除，2：已删除，小于
    private Integer whereLtIsDelete;
    // 条件：是否已删除。1：未删除，2：已删除，小于等于
    private Integer whereLteIsDelete;
    // 条件：是否已删除。1：未删除，2：已删除，开始范围
    private Integer whereStartIsDelete;
    // 条件：是否已删除。1：未删除，2：已删除，结束范围
    private Integer whereEndIsDelete;
    // 条件：设备信息（JSON格式存储设备型号、系统版本等），等于
    private String whereDeviceInfo;
    // 条件：设备信息（JSON格式存储设备型号、系统版本等），在列表中
    private List<String> whereInDeviceInfos;
    // 条件：设备信息（JSON格式存储设备型号、系统版本等），在列表中，or连接
    private List<String> whereInOrDeviceInfos;
    // 排除条件：设备信息（JSON格式存储设备型号、系统版本等）
    private String whereNotDeviceInfo;
    // 条件：设备信息（JSON格式存储设备型号、系统版本等），不在列表中
    private List<String> whereNotInDeviceInfos;
    // 条件：设备信息（JSON格式存储设备型号、系统版本等），为空
    private Boolean whereIsNullDeviceInfo;
    // 条件：设备信息（JSON格式存储设备型号、系统版本等），不为空
    private Boolean whereIsNotNullDeviceInfo;
    // 条件：设备信息（JSON格式存储设备型号、系统版本等），为空字符串
    private Boolean whereIsEmptyDeviceInfo;
    // 条件：设备信息（JSON格式存储设备型号、系统版本等），不为空字符串
    private Boolean whereIsNotEmptyDeviceInfo;
    // 条件：设备信息（JSON格式存储设备型号、系统版本等），大于
    private String whereGtDeviceInfo;
    // 条件：设备信息（JSON格式存储设备型号、系统版本等），大于等于
    private String whereGteDeviceInfo;
    // 条件：设备信息（JSON格式存储设备型号、系统版本等），小于
    private String whereLtDeviceInfo;
    // 条件：设备信息（JSON格式存储设备型号、系统版本等），小于等于
    private String whereLteDeviceInfo;
    // 条件：设备信息（JSON格式存储设备型号、系统版本等），模糊查询
    private String whereLikeDeviceInfo;
    // 条件：设备信息（JSON格式存储设备型号、系统版本等），开始范围
    private String whereStartDeviceInfo;
    // 条件：设备信息（JSON格式存储设备型号、系统版本等），结束范围
    private String whereEndDeviceInfo;
    // 条件：应用版本号，等于
    private String whereAppVersion;
    // 条件：应用版本号，在列表中
    private List<String> whereInAppVersions;
    // 条件：应用版本号，在列表中，or连接
    private List<String> whereInOrAppVersions;
    // 排除条件：应用版本号
    private String whereNotAppVersion;
    // 条件：应用版本号，不在列表中
    private List<String> whereNotInAppVersions;
    // 条件：应用版本号，为空
    private Boolean whereIsNullAppVersion;
    // 条件：应用版本号，不为空
    private Boolean whereIsNotNullAppVersion;
    // 条件：应用版本号，为空字符串
    private Boolean whereIsEmptyAppVersion;
    // 条件：应用版本号，不为空字符串
    private Boolean whereIsNotEmptyAppVersion;
    // 条件：应用版本号，大于
    private String whereGtAppVersion;
    // 条件：应用版本号，大于等于
    private String whereGteAppVersion;
    // 条件：应用版本号，小于
    private String whereLtAppVersion;
    // 条件：应用版本号，小于等于
    private String whereLteAppVersion;
    // 条件：应用版本号，模糊查询
    private String whereLikeAppVersion;
    // 条件：应用版本号，开始范围
    private String whereStartAppVersion;
    // 条件：应用版本号，结束范围
    private String whereEndAppVersion;
    // 条件：回复内容，等于
    private String whereReplyContent;
    // 条件：回复内容，在列表中
    private List<String> whereInReplyContents;
    // 条件：回复内容，在列表中，or连接
    private List<String> whereInOrReplyContents;
    // 排除条件：回复内容
    private String whereNotReplyContent;
    // 条件：回复内容，不在列表中
    private List<String> whereNotInReplyContents;
    // 条件：回复内容，为空
    private Boolean whereIsNullReplyContent;
    // 条件：回复内容，不为空
    private Boolean whereIsNotNullReplyContent;
    // 条件：回复内容，为空字符串
    private Boolean whereIsEmptyReplyContent;
    // 条件：回复内容，不为空字符串
    private Boolean whereIsNotEmptyReplyContent;
    // 条件：回复内容，大于
    private String whereGtReplyContent;
    // 条件：回复内容，大于等于
    private String whereGteReplyContent;
    // 条件：回复内容，小于
    private String whereLtReplyContent;
    // 条件：回复内容，小于等于
    private String whereLteReplyContent;
    // 条件：回复内容，模糊查询
    private String whereLikeReplyContent;
    // 条件：回复内容，开始范围
    private String whereStartReplyContent;
    // 条件：回复内容，结束范围
    private String whereEndReplyContent;
    // 条件：回复时间，等于
    private String whereReplyAt;
    // 条件：回复时间，在列表中
    private List<String> whereInReplyAts;
    // 条件：回复时间，在列表中，or连接
    private List<String> whereInOrReplyAts;
    // 排除条件：回复时间
    private String whereNotReplyAt;
    // 条件：回复时间，不在列表中
    private List<String> whereNotInReplyAts;
    // 条件：回复时间，为空
    private Boolean whereIsNullReplyAt;
    // 条件：回复时间，不为空
    private Boolean whereIsNotNullReplyAt;
    // 条件：回复时间，为空字符串
    private Boolean whereIsEmptyReplyAt;
    // 条件：回复时间，不为空字符串
    private Boolean whereIsNotEmptyReplyAt;
    // 条件：回复时间，大于
    private String whereGtReplyAt;
    // 条件：回复时间，大于等于
    private String whereGteReplyAt;
    // 条件：回复时间，小于
    private String whereLtReplyAt;
    // 条件：回复时间，小于等于
    private String whereLteReplyAt;
    // 条件：回复时间，开始范围
    private String whereStartReplyAt;
    // 条件：回复时间，结束范围
    private String whereEndReplyAt;
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
    // 条件：创建者用户ID（可为空，允许匿名反馈），等于
    private Integer whereCreatedUserId;
    // 条件：创建者用户ID（可为空，允许匿名反馈），在列表中
    private List<Integer> whereInCreatedUserIds;
    // 条件：创建者用户ID（可为空，允许匿名反馈），在列表中，or连接
    private List<Integer> whereInOrCreatedUserIds;
    // 排除条件：创建者用户ID（可为空，允许匿名反馈）
    private Integer whereNotCreatedUserId;
    // 条件：创建者用户ID（可为空，允许匿名反馈），不在列表中
    private List<Integer> whereNotInCreatedUserIds;
    // 条件：创建者用户ID（可为空，允许匿名反馈），为空
    private Boolean whereIsNullCreatedUserId;
    // 条件：创建者用户ID（可为空，允许匿名反馈），不为空
    private Boolean whereIsNotNullCreatedUserId;
    // 条件：创建者用户ID（可为空，允许匿名反馈），为空字符串
    private Boolean whereIsEmptyCreatedUserId;
    // 条件：创建者用户ID（可为空，允许匿名反馈），不为空字符串
    private Boolean whereIsNotEmptyCreatedUserId;
    // 条件：创建者用户ID（可为空，允许匿名反馈），大于
    private Integer whereGtCreatedUserId;
    // 条件：创建者用户ID（可为空，允许匿名反馈），大于等于
    private Integer whereGteCreatedUserId;
    // 条件：创建者用户ID（可为空，允许匿名反馈），小于
    private Integer whereLtCreatedUserId;
    // 条件：创建者用户ID（可为空，允许匿名反馈），小于等于
    private Integer whereLteCreatedUserId;
    // 条件：创建者用户ID（可为空，允许匿名反馈），开始范围
    private Integer whereStartCreatedUserId;
    // 条件：创建者用户ID（可为空，允许匿名反馈），结束范围
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
    // 条件：更新者用户ID，等于
    private Integer whereUpdatedUserId;
    // 条件：更新者用户ID，在列表中
    private List<Integer> whereInUpdatedUserIds;
    // 条件：更新者用户ID，在列表中，or连接
    private List<Integer> whereInOrUpdatedUserIds;
    // 排除条件：更新者用户ID
    private Integer whereNotUpdatedUserId;
    // 条件：更新者用户ID，不在列表中
    private List<Integer> whereNotInUpdatedUserIds;
    // 条件：更新者用户ID，为空
    private Boolean whereIsNullUpdatedUserId;
    // 条件：更新者用户ID，不为空
    private Boolean whereIsNotNullUpdatedUserId;
    // 条件：更新者用户ID，为空字符串
    private Boolean whereIsEmptyUpdatedUserId;
    // 条件：更新者用户ID，不为空字符串
    private Boolean whereIsNotEmptyUpdatedUserId;
    // 条件：更新者用户ID，大于
    private Integer whereGtUpdatedUserId;
    // 条件：更新者用户ID，大于等于
    private Integer whereGteUpdatedUserId;
    // 条件：更新者用户ID，小于
    private Integer whereLtUpdatedUserId;
    // 条件：更新者用户ID，小于等于
    private Integer whereLteUpdatedUserId;
    // 条件：更新者用户ID，开始范围
    private Integer whereStartUpdatedUserId;
    // 条件：更新者用户ID，结束范围
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
    public TbBasicFeedbackPo addGroupByField(String field) {
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
    public TbBasicFeedbackPo setGroupByFields(List<String> groupByFields) {
        this.groupByFields = groupByFields;
        return this;
    }
}
