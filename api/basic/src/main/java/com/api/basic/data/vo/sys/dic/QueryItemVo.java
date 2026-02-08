package com.api.basic.data.vo.sys.dic;

import lombok.Data;

/**
 * 获取字典组列表接口响应用VO
 *
 * @author 裴金伟
 */
@Data
public class QueryItemVo {

  // ID
  private Integer id;
  // 父节点ID
  private Integer parentId;
  // 字典名称
  private String name;
  // 字典标识
  private String identifying;
  // 排序ID
  private Integer sort;
  // 描述
  private String description;
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
