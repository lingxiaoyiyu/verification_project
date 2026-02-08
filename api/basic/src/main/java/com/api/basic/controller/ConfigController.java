package com.api.basic.controller;

import com.api.basic.data.dto.config.*;
import com.api.basic.data.dto.config.update.MultipleDto;
import com.api.basic.data.dto.config.update.StatusDto;
import com.api.basic.data.dto.sys.menu.update.SortDto;
import com.api.basic.service.config.*;
import com.api.basic.service.config.update.Multiple;
import com.api.basic.service.config.update.Sort;
import com.api.basic.service.config.update.Status;
import com.api.common.base.Result;
import jakarta.annotation.Resource;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 配置管理相关接口
 */
@RestController
@RequestMapping("/basic/config")
public class ConfigController {

    @Resource(name = "BasicConfigAddServiceImpl")
    private Add add;
    @Resource(name = "BasicConfigDeleteServiceImpl")
    private Delete delete;
    @Resource(name = "BasicConfigQueryServiceImpl")
    private Query query;
    @Resource(name = "BasicConfigPageServiceImpl")
    private Page page;
    @Resource(name = "BasicConfigGetServiceImpl")
    private Get get;
    @Resource(name = "BasicConfigUpdateService")
    private Update update;
    @Resource(name = "BasicConfigUpdateMultipleService")
    private Multiple multiple;
    @Resource(name = "BasicConfigUpdateStatusServiceImpl")
    private Status status;
    @Resource(name = "BasicConfigUpdateSortServiceImpl")
    private Sort sort;
    @Resource(name = "BasicConfigGroupAddServiceImpl")
    private com.api.basic.service.config.group.Add addGroup;
    @Resource(name = "BasicConfigGroupDeleteServiceImpl")
    private com.api.basic.service.config.group.Delete deleteGroup;
    @Resource(name = "BasicConfigGroupUpdateServiceImpl")
    private com.api.basic.service.config.group.Update updateGroup;
    @Resource(name = "BasicConfigGroupQueryServiceImpl")
    private com.api.basic.service.config.group.Query queryGroup;
    @Resource(name = "BasicConfigGroupGetServiceImpl")
    private com.api.basic.service.config.group.Get getGroup;

    /**
     * 添加配置
     */
    @PostMapping("/add")
    public Result<?> add(@RequestBody @Valid AddDto dto) {
        add.check(dto);
        return add.service(dto);
    }

    /**
     * 删除配置
     */
    @PostMapping("/delete")
    public Result<?> delete(@RequestBody @Valid DeleteDto dto) {
        delete.check(dto);
        return delete.service(dto);
    }

    /**
     * 查询配置项列表
     */
    @PostMapping("/query")
    public Result<?> query(@RequestBody @Valid QueryDto dto) {
        return query.service(dto);
    }

    /**
     * 获取配置详情
     */
    @PostMapping("/get")
    public Result<?> get(@RequestBody @Valid GetDto dto) {
        return get.service(dto);
    }

    /**
     * 查询配置项分页列表
     */
    @PostMapping("/page")
    public Result<?> page(@RequestBody @Valid PageDto dto) {
        return page.service(dto);
    }

    /**
     * 更新配置项
     */
    @PostMapping("/update")
    public Result<?> update(@RequestBody @Valid UpdateDto dto) {
        update.check(dto);
        return update.service(dto);
    }

    /**
     * 更新配置项排序
     */
    @PostMapping("/update/sort")
    public Result<?> sort(@RequestBody @Valid SortDto dto) {
        sort.check(dto);
        return sort.service(dto);
    }

    /**
     * 更新多个配置项
     */
    @PostMapping("/update/multiple")
    public Result<?> multiple(@RequestBody @Valid MultipleDto dto) {
        return multiple.service(dto);
    }

    /**
     * 更新配置项状态
     */
    @PostMapping("/update/status")
    public Result<?> status(@RequestBody @Valid StatusDto dto) {
        status.check(dto);
        return status.service(dto);
    }

    /**
     * 添加配置组
     */
    @PostMapping("/group/add")
    public Result<?> groupAdd(@RequestBody @Valid com.api.basic.data.dto.config.group.AddDto dto) {
        addGroup.check(dto);
        return addGroup.service(dto);
    }

    /**
     * 删除配置组
     */
    @PostMapping("/group/delete")
    public Result<?> groupDelete(@RequestBody @Valid com.api.basic.data.dto.config.group.DeleteDto dto) {
        deleteGroup.check(dto);
        return deleteGroup.service(dto);
    }

    /**
     * 更新配置组
     */
    @PostMapping("/group/update")
    public Result<?> updateGroup(@RequestBody @Valid com.api.basic.data.dto.config.group.UpdateDto dto) {
        updateGroup.check(dto);
        return updateGroup.service(dto);
    }

    /**
     * 查询配置组列表
     */
    @PostMapping("/group/query")
    public Result<?> groupQuery() {
        return queryGroup.service();
    }

    /**
     * 查询配置组详情
     */
    @PostMapping("/group/get")
    public Result<?> groupGet(@RequestBody @Valid com.api.basic.data.dto.config.group.GetDto dto) {
        return getGroup.service(dto);
    }
}
