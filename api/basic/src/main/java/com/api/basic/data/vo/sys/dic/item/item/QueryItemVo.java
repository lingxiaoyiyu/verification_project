package com.api.basic.data.vo.sys.dic.item.item;

import lombok.Data;

/**
 * 获取字典项列表接口响应用VO
 *
 * @author 裴金伟
 */
@Data
public class QueryItemVo {

  // ID
  private Integer id;
  // 字典标识
  private String dicIdentifying;
  // 字典项名称
  private String name;
  // 字典项值
  private Integer value;
  // 字典项标识
  private String identifying;
  // 排序ID
  private Integer sort;
  // 描述
  private String description;
  // 背景色
  private String backgroundColor;
  // 状态。1：启用，2：禁用
  private Integer status;
  // 创建时间
  private String createdAt;
  // 创建者
  private Integer createdUserId;
  // 更新时间
  private String updatedAt;
  // 更新者
  private Integer updatedUserId;
}
