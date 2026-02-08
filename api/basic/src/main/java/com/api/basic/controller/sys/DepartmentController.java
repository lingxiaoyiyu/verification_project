package com.api.basic.controller.sys;

import com.api.basic.data.dto.sys.department.*;
import com.api.basic.data.dto.sys.department.update.SortDto;
import com.api.basic.service.sys.department.*;
import com.api.basic.service.sys.department.update.Sort;
import com.api.common.base.Result;
import jakarta.annotation.Resource;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 部门管理相关接口
 */
@RestController
@RequestMapping("/basic/sys/department")
public class DepartmentController {

    @Resource(name = "BasicSysDepartmentAddService")
    private Add add;
    @Resource(name = "BasicSysDepartmentRemoveService")
    private Remove remove;
    @Resource(name = "BasicSysDepartmentUpdateService")
    private Update update;
    @Resource(name = "BasicSysDepartmentUpdateSortService")
    private Sort updateSort;
    @Resource(name = "BasicSysDepartmentQueryService")
    private Query query;
    @Resource(name = "BasicSysDepartmentGetServiceImpl")
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
