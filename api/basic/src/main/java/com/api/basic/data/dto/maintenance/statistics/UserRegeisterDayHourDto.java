package com.api.basic.data.dto.maintenance.statistics;

import com.api.common.base.data.dto.BaseDto;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 每小时用户注册统计接口请求用DTO
 *
 * @author 裴金伟
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class UserRegeisterDayHourDto extends BaseDto {

    // 开始时间
    @NotNull(message = "{validation.statistics.startDayHour.notNull}")
    private String startDayHour;
    // 结束时间
    @NotNull(message = "{validation.statistics.endDayHour.notNull}")
    private String endDayHour;
}
