package com.api.basic.data.entity;

import com.api.common.base.data.po.BasePagePo;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.SuperBuilder;

/**
 * 问题反馈表实体
 */
@EqualsAndHashCode(doNotUseGetters = true, callSuper = true)
@Data
@SuperBuilder
public class TbBasicFeedback extends BasePagePo {

    public TbBasicFeedback(){
        super();
    }

    // ID
    private Integer id;
    // 反馈类型。1：Bug反馈，2：功能建议，3：使用咨询，4：其他
    private Integer type;
    // 反馈内容
    private String content;
    // 反馈图片（JSON数组格式存储图片URL）
    private String images;
    // 联系方式
    private String contact;
    // 处理状态。1：待处理，2：处理中，3：已解决，4：已拒绝
    private Integer status;
    // 是否已删除。1：未删除，2：已删除
    private Integer isDelete;
    // 设备信息（JSON格式存储设备型号、系统版本等）
    private String deviceInfo;
    // 应用版本号
    private String appVersion;
    // 回复内容
    private String replyContent;
    // 回复时间
    private String replyAt;
    // 创建时间
    private String createdAt;
    // 创建者用户ID（可为空，允许匿名反馈）
    private Integer createdUserId;
    // 更新时间
    private String updatedAt;
    // 更新者用户ID
    private Integer updatedUserId;
}
