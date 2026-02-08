package com.api.basic.data.vo.config.group;

import lombok.Data;

/**
 * 获取配置组详情接口响应用VO
 *
 * @author 裴金伟
 */
@Data
public class GetVo {

  // ID
  private Integer id;
  // 名称
  private String name;
  // 值
  private String value;
  // 创建时间
  private String createdAt;
  // 创建者
  private String createdUserName;
}
