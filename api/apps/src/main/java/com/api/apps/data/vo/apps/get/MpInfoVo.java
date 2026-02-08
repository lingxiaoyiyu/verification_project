package com.api.apps.data.vo.apps.get;

import lombok.Data;

/**
 * 小程序接口响应用VO
 *
 * @author 裴金伟
 */
@Data
public class MpInfoVo {

  // 版本号
  private Integer versionCode;
  // 版本名称
  private String versionName;
  // wgt下载地址
  private String url;
  // logo地址
  private String logo;
  // 版本描述
  private String desc;
}
