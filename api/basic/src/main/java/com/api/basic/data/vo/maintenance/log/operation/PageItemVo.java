package com.api.basic.data.vo.maintenance.log.operation;

import lombok.Data;

/**
 * 查询操作日志列表接口响应用VO
 *
 * @author 裴金伟
 * @date 2025-02-22
 */
@Data
public class PageItemVo {

  // ID
  private Integer id;
  // 类型
  private String type;
  // 子类型
  private String typeSub;
  // 日志内容
  private String content;
  // 操作的IP地址
  private String ip;
  // 用户名
  private String username;
  // 手机号
  private String phoneNumber;
  // 真实姓名
  private String realName;
}
