package com.api.apps.data.vo.apps.version;

import lombok.Data;

/**
 * 应用版本接口响应用VO
 *
 * @author 裴金伟
 */
@Data
public class PageItemVo {

  // ID
  private Integer id;
  // 版本号
  private Integer versionCode;
  // 版本名称
  private String versionName;
  // 版本描述
  private String desc;
  // 状态。1：下线，2：测试，3：正式上线。
  private Integer status;
  // 创建时间
  private String createdAt;
}
