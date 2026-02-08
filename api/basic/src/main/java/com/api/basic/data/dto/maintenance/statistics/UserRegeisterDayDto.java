package com.api.basic.data.dto.maintenance.statistics;

import com.api.common.base.data.dto.BasePageDto;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 每日用户注册统计接口请求用DTO
 *
 * @author 裴金伟
*/
@EqualsAndHashCode(callSuper = true)
@Data
public class UserRegeisterDayDto extends BasePageDto {

    // 开始日期
    @NotNull(message = "{validation.statistics.startDay.notNull}")
    private String startDay;
    // 结束日期
    @NotNull(message = "{validation.statistics.endDay.notNull}")
    private String endDay;
}
