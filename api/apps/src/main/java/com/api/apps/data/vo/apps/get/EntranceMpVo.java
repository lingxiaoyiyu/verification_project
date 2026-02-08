package com.api.apps.data.vo.apps.get;

import lombok.Data;

/**
 * 获取入口小程序信息接口响应用VO
 *
 * @author 裴金伟
 */
@Data
public class EntranceMpVo {

  // 入口小程序的uniappid
  private String entranceUniAppid;
  // 版本号
  private Integer versionCode;
  // 版本名称
  private String versionName;
  // wgt下载地址
  private String url;
  // logo图片地址
  private String logo;
  // 版本描述
  private String desc;
}
