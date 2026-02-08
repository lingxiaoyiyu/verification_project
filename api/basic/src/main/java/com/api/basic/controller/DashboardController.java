package com.api.basic.controller;

import com.api.basic.service.dashboard.Workbench;
import com.api.basic.service.dashboard.workbench.Header;
import com.api.basic.service.dashboard.workbench.QuickNav;
import com.api.basic.service.dashboard.workbench.Todo;
import com.api.basic.service.dashboard.workbench.Trend;
import com.api.common.base.Result;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 工作台管理相关接口
 */
@RestController
@RequestMapping("/basic/dashboard")
public class DashboardController {

    @Resource(name = "BasicWorkbenchService")
    private Workbench workbench;
    @Resource(name = "BasicDashboardWorkbenchHeaderService")
    private Header header;
    @Resource(name = "BasicDashboardWorkbenchQuickNavService")
    private QuickNav quickNav;
    @Resource(name = "BasicDashboardWorkbenchTrendService")
    private Trend trend;
    @Resource(name = "BasicDashboardWorkbenchTodoService")
    private Todo todo;

    /**
     * 工作台
     */
    @PostMapping("/workbench")
    public Result<?> workbench(HttpServletRequest request) {
        workbench.check();
        return workbench.service(request);
    }

    /**
     * 工作台头部
     */
    @PostMapping("/workbench/header")
    public Result<?> header(HttpServletRequest request) {
        header.check();
        return header.service(request);
    }

    /**
     * 导航
     */
    @PostMapping("/workbench/quickNav")
    public Result<?> quickNav() {
        quickNav.check();
        return quickNav.service();
    }

    /**
     * 最新动态
     */
    @PostMapping("/workbench/trend")
    public Result<?> trend() {
        trend.check();
        return trend.service();
    }

    /**
     * 待办事项
     */
    @PostMapping("/workbench/todo")
    public Result<?> todo() {
        todo.check();
        return todo.service();
    }
}
