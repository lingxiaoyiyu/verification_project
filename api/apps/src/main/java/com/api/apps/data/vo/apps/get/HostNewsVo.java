package com.api.apps.data.vo.apps.get;

import lombok.Data;

/**
 * 宿主接口响应用VO
 *
 * @author 裴金伟
 */
@Data
public class HostNewsVo {

  // 版本号
  private Integer versionCode;
  // 版本名称
  private String versionName;
  // 下载地址
  private String url;
  // 版本描述
  private String desc;
}
