package com.api.basic.controller.sys;

import com.api.basic.data.dto.sys.dic.PageDto;
import com.api.basic.data.dto.sys.dic.RemoveDto;
import com.api.basic.data.dto.sys.dic.item.AddDto;
import com.api.basic.data.dto.sys.dic.item.DeleteDto;
import com.api.basic.data.dto.sys.dic.item.GetDto;
import com.api.basic.data.dto.sys.dic.item.QueryDto;
import com.api.basic.data.dto.sys.dic.update.InfoDto;
import com.api.basic.data.dto.sys.dic.update.StatusDto;
import com.api.basic.service.sys.dic.Page;
import com.api.basic.service.sys.dic.Remove;
import com.api.basic.service.sys.dic.item.Add;
import com.api.basic.service.sys.dic.item.Delete;
import com.api.basic.service.sys.dic.item.Get;
import com.api.basic.service.sys.dic.item.Query;
import com.api.basic.service.sys.dic.update.Info;
import com.api.basic.service.sys.dic.update.Status;
import com.api.common.base.Result;
import jakarta.annotation.Resource;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 字典管理相关接口
 */
@RestController
@RequestMapping("/basic/sys/dic")
public class DicController {

    @Resource(name = "BasicDicDeleteServiceImpl")
    private com.api.basic.service.sys.dic.Delete delete;
    @Resource(name = "BasicDicRemoveServiceImpl")
    private Remove remove;
    @Resource(name = "BasicDicAddServiceImpl")
    private com.api.basic.service.sys.dic.Add add;
    @Resource(name = "BasicDicPageServiceImpl")
    private Page page;
    @Resource(name = "BasicDicQueryServiceImpl")
    private com.api.basic.service.sys.dic.Query query;
    @Resource(name = "BasicDicGetServiceImpl")
    private com.api.basic.service.sys.dic.Get get;
    @Resource(name = "BasicDicUpdateInfoServiceImpl")
    private Info info;
    @Resource(name = "BasicDicUpdateStatusServiceImpl")
    private Status status;
    @Resource(name = "BasicDicItemQueryServiceImpl")
    private Query itemQuery;
    @Resource(name = "BasicDicItemAddServiceImpl")
    private Add itemAdd;
    @Resource(name = "BasicDicItemDeleteServiceImpl")
    private Delete itemDelete;
    @Resource(name = "BasicDicItemGetServiceImpl")
    private Get itemGet;
    @Resource(name = "BasicDicItemUpdateStatusServiceImpl")
    private com.api.basic.service.sys.dic.item.update.Status itemUpdateStatus;
    @Resource(name = "BasicDicItemUpdateInfoServiceImpl")
    private com.api.basic.service.sys.dic.item.update.Info itemUpdateInfo;

    /**
     * 删除字典组信息
     */
    @PostMapping("/delete")
    public Result<?> delete(@RequestBody @Valid com.api.basic.data.dto.sys.dic.DeleteDto dto) {
        delete.check(dto);
        return delete.service(dto);
    }

    /**
     * 删除字典组信息
     */
    @PostMapping("/remove")
    public Result<?> remove(@RequestBody @Valid RemoveDto dto) {
        remove.check(dto);
        return remove.service(dto);
    }

    /**
     * 添加字典组信息
     */
    @PostMapping("/add")
    public Result<?> add(@RequestBody @Valid com.api.basic.data.dto.sys.dic.AddDto dto) {
        add.check(dto);
        return add.service(dto);
    }

    /**
     * 获取字典组列表-分页
     */
    @PostMapping("/page")
    public Result<?> page(@RequestBody @Valid PageDto dto) {
        page.check(dto);
        return page.service(dto);
    }

    /**
     * 获取字典组列表
     */
    @PostMapping("/query")
    public Result<?> query(@RequestBody @Valid com.api.basic.data.dto.sys.dic.QueryDto dto) {
        query.check(dto);
        return query.service(dto);
    }

    /**
     * 获取指定字典组信息
     */
    @PostMapping("/get")
    public Result<?> get(@RequestBody @Valid com.api.basic.data.dto.sys.dic.GetDto dto) {
        get.check(dto);
        return get.service(dto);
    }

    /**
     * 修改字典组信息
     */
    @PostMapping("/update/info")
    public Result<?> info(@RequestBody @Valid InfoDto dto) {
        info.check(dto);
        return info.service(dto);
    }

    /**
     * 修改字典组状态
     */
    @PostMapping("/update/status")
    public Result<?> status(@RequestBody @Valid StatusDto dto) {
        status.check(dto);
        return status.service(dto);
    }

    /**
     * 获取字典项列表
     */
    @PostMapping("/item/query")
    public Result<?> query(@RequestBody @Valid QueryDto dto) {
        itemQuery.check(dto);
        return itemQuery.service(dto);
    }

    /**
     * 添加字典项信息
     */
    @PostMapping("/item/add")
    public Result<?> add(@RequestBody @Valid AddDto dto) {
        itemAdd.check(dto);
        return itemAdd.service(dto);
    }

    /**
     * 删除字典项信息
     */
    @PostMapping("/item/delete")
    public Result<?> delete(@RequestBody @Valid DeleteDto dto) {
        itemDelete.check(dto);
        return itemDelete.service(dto);
    }

    /**
     * 获取指定字典项信息
     */
    @PostMapping("/item/get")
    public Result<?> get(@RequestBody @Valid GetDto dto) {
        itemGet.check(dto);
        return itemGet.service(dto);
    }

    /**
     * 修改字典项信息
     */
    @PostMapping("/item/update/status")
    public Result<?> status(@RequestBody @Valid com.api.basic.data.dto.sys.dic.item.update.StatusDto dto) {
        itemUpdateStatus.check(dto);
        return itemUpdateStatus.service(dto);
    }

    /**
     * 修改字典项信息
     */
    @PostMapping("/item/update/info")
    public Result<?> info(@RequestBody @Valid com.api.basic.data.dto.sys.dic.item.update.InfoDto dto) {
        itemUpdateInfo.check(dto);
        return itemUpdateInfo.service(dto);
    }
}
