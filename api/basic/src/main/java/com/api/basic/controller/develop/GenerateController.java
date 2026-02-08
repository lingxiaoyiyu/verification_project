package com.api.basic.controller.develop;

import com.api.basic.data.dto.develop.generate.ApiCodeDto;
import com.api.basic.data.dto.develop.generate.BaseCodeDto;
import com.api.basic.data.dto.develop.generate.QueryModuleDto;
import com.api.basic.data.dto.develop.generate.QuerySubModuleDto;
import com.api.basic.service.develop.generate.ApiCode;
import com.api.basic.service.develop.generate.BaseCode;
import com.api.basic.service.develop.generate.QueryModule;
import com.api.basic.service.develop.generate.QuerySubModule;
import com.api.common.base.Result;
import jakarta.annotation.Resource;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 生成器管理相关接口
 */
@RestController
@RequestMapping("/basic/develop/generate")
public class GenerateController {

    @Resource(name = "BasicGenerateApiCodeServiceImpl")
    private ApiCode apiCode;
    @Resource(name = "BasicGenerateBaseCodeServiceImpl")
    private BaseCode baseCode;
    @Resource(name = "BasicDevelopGenerateQueryModuleServiceImpl")
    private QueryModule queryModule;
    @Resource(name = "BasicDevelopGenerateQuerySubModuleServiceImpl")
    private QuerySubModule querySubModule;

    /**
     * Api接口代码生成器
     */
    @PostMapping("/apiCode")
    public Result<?> apiCode(@RequestBody @Valid ApiCodeDto dto) {
        apiCode.check(dto);
        return apiCode.service(dto);
    }

    /**
     * 基础代码生成器
     */
    @PostMapping("/baseCode")
    public Result<?> baseCode(@RequestBody @Valid BaseCodeDto dto) {
        baseCode.check(dto);
        return baseCode.service(dto);
    }

    /**
     * 获取模块列表
     */
    @PostMapping("/queryModule")
    public Result<?> queryModule(@RequestBody @Valid QueryModuleDto dto) {
        queryModule.check(dto);
        return queryModule.service(dto);
    }

    /**
     * 获取子模块列表
     */
    @PostMapping("/querySubModule")
    public Result<?> querySubModule(@RequestBody @Valid QuerySubModuleDto dto) {
        querySubModule.check(dto);
        return querySubModule.service(dto);
    }
}
