package com.api.basic.controller.sys;

import com.api.basic.data.dto.sys.region.*;
import com.api.basic.data.dto.sys.region.update.SortDto;
import com.api.basic.service.sys.region.*;
import com.api.basic.service.sys.region.update.Sort;
import com.api.common.base.Result;
import jakarta.annotation.Resource;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 行政区域管理相关接口
 */
@RestController
@RequestMapping("/basic/sys/region")
public class RegionController {

    @Resource(name = "BasicSysRegionAddService")
    private Add add;
    @Resource(name = "BasicSysRegionRemoveService")
    private Remove remove;
    @Resource(name = "BasicSysRegionUpdateService")
    private Update update;
    @Resource(name = "BasicSysRegionUpdateSortService")
    private Sort updateSort;
    @Resource(name = "BasicSysRegionQueryService")
    private Query query;
    @Resource(name = "BasicSysRegionGetServiceImpl")
    private Get get;

    /**
     * 添加部门
     */
    @PostMapping("/add")
    public Result<?> add(@RequestBody @Valid AddDto dto) {
        add.check(dto);
        return add.service(dto);
    }

    /**
     * 删除部门
     */
    @PostMapping("/remove")
    public Result<?> remove(@RequestBody @Valid RemoveDto dto) {
        remove.check(dto);
        return remove.service(dto);
    }

    /**
     * 更新部门信息
     */
    @PostMapping("/update")
    public Result<?> update(@RequestBody @Valid UpdateDto dto) {
        update.check(dto);
        return update.service(dto);
    }

    /**
     * 更新部门排序
     */
    @PostMapping("/update/sort")
    public Result<?> updateSort(@RequestBody @Valid SortDto dto) {
        updateSort.check(dto);
        return updateSort.service(dto);
    }

    /**
     * 查询部门列表
     */
    @PostMapping("/query")
    public Result<?> query(@RequestBody QueryDto dto) {
        return query.service(dto);
    }

    /**
     * 获取指定部门详情
     */
    @PostMapping("/get")
    public Result<?> get(@RequestBody GetDto dto) {
        return get.service(dto);
    }
}
