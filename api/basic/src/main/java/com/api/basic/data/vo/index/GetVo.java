package com.api.basic.data.vo.index;

import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

/**
 * 获取指定获取IP信息信息接口响应用VO
 *
 * @author 裴金伟
 */
@Data
public class GetVo {

  // 国家
  private String country;
  // 省
  private String province;
  // 市
  private String city;
  // 运营商
  private String isp;
  // IP
  private String ip;
}
