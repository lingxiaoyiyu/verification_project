package com.api.apps.data.vo.apps.query;

import lombok.Data;

/**
 * 小程序接口响应用VO
 *
 * @author 裴金伟
 */
@Data
public class MpListItemVo {

  // 应用名称
  private String name;
  // 版本号
  private Integer versionCode;
  // 小程序uniAppid
  private String uniAppid;
  // 图片
  private String img;
  // 下载地址
  private String url;
}
