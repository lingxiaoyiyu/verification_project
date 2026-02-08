package com.api.basic.controller.maintenance;

import com.api.basic.data.dto.maintenance.statistics.UserRegeisterDayDto;
import com.api.basic.data.dto.maintenance.statistics.UserRegeisterDayHourDto;
import com.api.basic.service.maintenance.statistics.UserRegeisterDay;
import com.api.basic.service.maintenance.statistics.UserRegeisterDayHour;
import com.api.common.base.Result;
import jakarta.annotation.Resource;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 运维统计管理相关接口
 */
@RestController
@RequestMapping("/maintenance")
public class StatisticsUserController {

    @Resource(name = "MaintenanceStatisticsUserRegeisterDayServiceImpl")
    private UserRegeisterDay userRegeisterDay;
    @Resource(name = "MaintenanceStatisticsUserRegeisterDayHourServiceImpl")
    private UserRegeisterDayHour userRegeisterDayHour;

    /**
     * 查询每日用户注册统计列表分页
     */
    @PostMapping("/statistics/user/regeister/day")
    public Result<?> userRegeisterDay(@RequestBody @Valid UserRegeisterDayDto dto) {
        return userRegeisterDay.service(dto);
    }

    /**
     * 查询每小时用户注册统计列表
     * @param dto
     * @return
     */
    @PostMapping("/statistics/user/regeister/dayHour")
    public Result<?> userRegeisterDayHour(@RequestBody @Valid UserRegeisterDayHourDto dto) {
        return userRegeisterDayHour.service(dto);
    }
}
