package com.api.basic.data.vo.maintenance.statistics;

import lombok.Data;

/**
 * 每小时用户注册统计接口响应用VO
 *
 * @author 裴金伟
 */
@Data
public class UserRegeisterDayHourItemVo {

  // 日期小时
  private String dateHour;
  // 用户注册数
  private Integer cnt;
}
