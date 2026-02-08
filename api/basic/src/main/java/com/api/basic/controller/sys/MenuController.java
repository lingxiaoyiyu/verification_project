package com.api.basic.controller.sys;

import com.api.basic.data.dto.sys.menu.*;
import com.api.basic.data.dto.sys.menu.update.InfoDto;
import com.api.basic.data.dto.sys.menu.update.SortDto;
import com.api.basic.service.sys.menu.*;
import com.api.basic.service.sys.menu.query.Children;
import com.api.basic.service.sys.menu.query.SelectTree;
import com.api.basic.service.sys.menu.update.Sort;
import com.api.common.base.Result;
import jakarta.annotation.Resource;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 菜单管理相关接口
 */
@RestController
@RequestMapping("/basic/sys/menu")
public class MenuController {

    @Resource(name = "BasicSysMenuAddServiceImpl")
    private Add add;
    @Resource(name = "BasicSysMenuRemoveServiceImpl")
    private Remove remove;
    @Resource(name = "BasicSysMenuUpdateServiceImpl")
    private Update info;
    @Resource(name = "BasicSysMenuQueryServiceImpl")
    private Query query;
    @Resource(name = "BasicSysMenuQuerySelectTreeServiceImpl")
    private SelectTree queryMenuSelectTree;
    @Resource(name = "BasicSysMenuQueryChildrenServiceImpl")
    private Children queryChildrenMenu;
    @Resource(name = "BasicSysMenuUpdateSortServiceImpl")
    private Sort sort;
    @Resource(name = "BasicSysMenuGetServiceImpl")
    private Get get;

    /**
     * 添加型菜单
     */
    @PostMapping("/add")
    public Result<?> add(@RequestBody @Valid AddDto dto) {
        add.check(dto);
        return add.service(dto);
    }

    /**
     * 删除菜单
     */
    @PostMapping("/remove")
    public Result<?> remove(@RequestBody @Valid RemoveMenuDto dto) {
        remove.check(dto);
        return remove.service(dto);
    }

    /**
     * 更新目录型菜单
     */
    @PostMapping("/update")
    public Result<?> update(@RequestBody @Valid InfoDto dto) {
        info.check(dto);
        return info.service(dto);
    }
    // 修改菜单排序
    @PostMapping("/update/sort")
    public Result<?> sort(@RequestBody @Valid SortDto dto) {
        sort.check(dto);
        return sort.service(dto);
    }

    // 获取菜单数据
    @PostMapping("/query")
    public Result<?> query(@RequestBody @Valid QueryTreeDto dto) {
        query.check(dto);
        return query.service(dto);
    }

    // 获取树形结构菜单下拉列表数据
    @PostMapping("/query/selectTree")
    public Result<?> querySelectTree() {
        return queryMenuSelectTree.service();
    }

    // 获取指定菜单的可显示页面子菜单
    @PostMapping("/query/children")
    public Result<?> queryChildren(@RequestBody @Valid QueryChildrenDto dto) {
        return queryChildrenMenu.service(dto);
    }

    // 获取指定菜单详情
    @PostMapping("/get")
    public Result<?> get(@RequestBody @Valid GetDto dto) {
        get.check(dto);
        return get.service(dto);
    }
}
