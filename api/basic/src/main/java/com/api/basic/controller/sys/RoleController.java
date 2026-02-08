package com.api.basic.controller.sys;

import com.api.basic.data.dto.sys.role.*;
import com.api.basic.data.dto.sys.role.update.StatusDto;
import com.api.basic.service.sys.role.*;
import com.api.basic.service.sys.role.update.Status;
import com.api.common.base.Result;
import jakarta.annotation.Resource;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 角色管理相关接口
 */
@RestController
@RequestMapping("/basic/sys/role")
public class RoleController {

    @Resource(name = "BasicSysRoleAddServiceImpl")
    private Add add;
    @Resource(name = "BasicSysRoleRemoveServiceImpl")
    private Remove remove;
    @Resource(name = "BasicSysRoleUpdateServiceImpl")
    private Update updateUpdate;
    @Resource(name = "BasicSysRoleUpdateStatusServiceImpl")
    private Status updateStatus;
    @Resource(name = "BasicSysRolePageServiceImpl")
    private Page page;
    @Resource(name = "BasicSysRoleQueryServiceImpl")
    private Query query;
    @Resource(name = "BasicSysRoleGetServiceImpl")
    private Get get;

    /**
     * 添加角色
     */
    @PostMapping("/add")
    public Result<?> add(@RequestBody @Valid AddDto dto) {
        add.check(dto);
        return add.service(dto);
    }

    /**
     * 删除角色
     */
    @PostMapping("/remove")
    public Result<?> delete(@RequestBody @Valid RemoveDto dto) {
        remove.check(dto);
        return remove.service(dto);
    }

    /**
     * 修改角色信息
     */
    @PostMapping("/update")
    public Result<?> updateInfo(@RequestBody @Valid UpdateDto dto) {
        updateUpdate.check(dto);
        return updateUpdate.service(dto);
    }

    /**
     * 修改指定角色的状态
     */
    @PostMapping("/update/status")
    public Result<?> updateStatus(@RequestBody @Valid StatusDto dto) {
        updateStatus.check(dto);
        return updateStatus.service(dto);
    }

    /**
     * 查询角色分页列表
     */
    @PostMapping("/page")
    public Result<?> page(@RequestBody @Valid PageDto dto) {
        return page.service(dto);
    }

    /**
     * 查询角色列表
     */
    @PostMapping("/query")
    public Result<?> query() {
        return query.service();
    }

    /**
     * 查询角色分页列表
     */
    @PostMapping("/get")
    public Result<?> get(@RequestBody @Valid GetDto dto) {
        get.check(dto);
        return get.service(dto);
    }
}
