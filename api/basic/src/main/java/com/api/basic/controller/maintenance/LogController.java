package com.api.basic.controller.maintenance;

import com.api.basic.data.dto.maintenance.log.runtime.PageDto;
import com.api.basic.service.maintenance.log.runtime.PageDetail;
import com.api.basic.service.maintenance.log.runtime.PageRequest;
import com.api.common.base.Result;
import jakarta.annotation.Resource;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 运维日志管理相关接口
 */
@RestController
@RequestMapping("/basic/maintenance")
public class LogController {

    @Resource(name = "BasicMaintenanceLogRuntimePageRequestServiceImpl")
    private PageRequest runtimePageRequest;
    @Resource(name = "BasicMaintenanceLogRuntimePageServiceImpl")
    private PageDetail runtimePageDetail;
    @Resource(name = "BasicMaintenanceLogOperationPageServiceImpl")
    private com.api.basic.service.maintenance.log.operation.Page operationPage;

    /**
     * 获取请求日志列表
     */
    @PostMapping("/log/runtime/pageRequest")
    public Result<?> pageRequest(@RequestBody @Valid PageDto dto) {
        runtimePageRequest.check(dto);
        return runtimePageRequest.service(dto);
    }

    /**
     * 访问日志列表-分页
     */
    @PostMapping("/log/runtime/pageDetail")
    public Result<?> pageRequests(@RequestBody @Valid PageDto dto) {
        runtimePageDetail.check(dto);
        return runtimePageDetail.service(dto);
    }

    /**
     * 操作日志列表-分页
     */
    @PostMapping("/log/operation/page")
    public Result<?> operationPage(@RequestBody @Valid com.api.basic.data.dto.maintenance.log.operation.PageDto dto) {
        return operationPage.service(dto);
    }
}
