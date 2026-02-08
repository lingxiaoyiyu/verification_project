package com.api.basic.data.vo.config;

import lombok.Data;

/**
 * 获取配置项详情接口响应用VO
 *
 * @author 裴金伟
 */
@Data
public class GetVo {

  // ID
  private Integer id;
  // 标题
  private String title;
  // 名称
  private String name;
  // 描述
  private String description;
  // 数据类型
  private Integer dataType;
  // 状态
  private Integer status;
  // 分组ID
  private Integer groupId;
  // 分组名称
  private String groupName;
  // 创建时间
  private String createdAt;
  // 创建者
  private String createdUserName;
  // 最后更新时间
  private String updatedAt;
  // 最后更新者
  private String updatedUserName;
  // 组件数据
  private String componentData;
}
