package com.api.basic.data.vo.sys.feedback;

import lombok.Data;

/**
 * 获取指定问题反馈信息接口响应用VO
 *
 * @author 裴金伟
 */
@Data
public class GetVo {

  // ID
  private Integer id;
  // 反馈类型。bug：Bug反馈，suggestion：功能建议，question：使用咨询，other：其他
  private String type;
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
