package com.api.basic.data.vo.maintenance.log.runtime;

import lombok.Data;

/**
 * 获取查询列表接口响应用VO
 *
 * @author 裴金伟
 * @date 2025-02-21
 */
@Data
public class PageItemVo {
  // 日期
  private String createdAt;
  // 请求ID
  private String requestId;
  // 日志等级
  private String level;
  // 日志内容
  private String content;

  private String thread;
  private String method;
  private String schema;
  private String domain;
  private String uri;
  private String ip;
  private String port;
  private Integer uid;
  private String logger;
  private String message;
  private String username;
}
