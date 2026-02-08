package com.api.basic.data.vo.maintenance.statistics;

import lombok.Data;

/**
 * 每日用户注册统计接口响应用VO
 *
 * @author 裴金伟
 */
@Data
public class UserRegeisterDayItemVo {

  // 日期
  private String day;
  // 新增用户数
  private Integer cnt;
}
