package com.api.basic.data.vo.config.group;

import lombok.Data;

/**
 * 获取配置组列表接口响应用VO
 *
 * @author 裴金伟
 */
@Data
public class QueryItemVo {

  // ID
  private Integer id;
  // 名称
  private String name;
  // 值
  private String value;
}
