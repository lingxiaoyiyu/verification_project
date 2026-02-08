package com.api.basic.controller;

import cn.dev33.satoken.annotation.SaIgnore;
import com.api.common.base.Result;
import jakarta.annotation.Resource;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 埋点相关接口
 */
@RestController
@RequestMapping("/basic/trackingPoint")
public class TrackingPointController {

    @Resource(name = "BasicTrackingPointAddServiceImpl")
    private com.api.basic.service.trackingPoint.Add add;

    // 添加埋点记录
    @SaIgnore
    @PostMapping("/add")
    public Result<?> add(@RequestBody @Valid com.api.basic.data.dto.trackingPoint.AddDto dto) {
        add.check(dto);
        return add.service(dto);
    }
}
