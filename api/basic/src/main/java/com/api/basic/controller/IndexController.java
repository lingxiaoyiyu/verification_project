package com.api.basic.controller;

import cn.dev33.satoken.annotation.SaIgnore;
import com.api.basic.service.index.Init;
import com.api.common.base.Result;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;

/**
 * 入口相关接口
 */
@RestController
public class IndexController {

    @Resource(name = "IndexInitServiceImpl")
    private Init init;
    @Resource(name = "BasicIndexGetServiceImpl")
    private com.api.basic.service.index.Get get;

    /**
     * 默认入口
     */
    @SaIgnore
    @RequestMapping(value = "", method = {RequestMethod.POST, RequestMethod.GET})
    public Result<?> index() {
        return Result.ok();
    }

    /**
     * 数据初始化接口
     */
    @SaIgnore
    @RequestMapping(value = "/index/init", method = {RequestMethod.POST, RequestMethod.GET})
    public Result<?> init() {
        init.service();
        return Result.ok();
    }

    // 获取指定获取IP信息信息
    @SaIgnore
    @GetMapping("/basic/ip2region")
    public Result<?> get(@RequestParam(value = "ip", defaultValue = "") String ip) {
        com.api.basic.data.dto.index.GetDto dto = new com.api.basic.data.dto.index.GetDto();
        dto.setIp(ip);
        get.check(dto);
        return get.service(dto);
    }
}
