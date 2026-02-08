package com.api.basic.controller.develop;

import com.api.basic.data.dto.develop.table.DetailDto;
import com.api.basic.data.dto.develop.table.PageDto;
import com.api.basic.service.develop.table.Detail;
import com.api.basic.service.develop.table.Page;
import com.api.basic.service.develop.table.Select;
import com.api.common.base.Result;
import jakarta.annotation.Resource;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.sql.SQLException;

/**
 * 数据库表管理相关接口
 */
@RestController
@RequestMapping("/basic/develop/table")
public class TableController {

    @Resource(name = "BasicDevelopTablePageServiceImpl")
    private Page page;
    @Resource(name = "BasicDevelopTableSelectServiceImpl")
    private Select select;
    @Resource(name = "BasicDevelopTableDetailServiceImpl")
    private Detail detail;

    /**
     * 数据库表列表-分页
     *
     * @param dto
     * @return
     */
    @PostMapping("/page")
    public Result<?> pageTable(@RequestBody @Valid PageDto dto) {
        return page.service(dto);
    }

    /**
     * 数据库表列表
     */
    @PostMapping("/select")
    public Result<?> selectTable() throws SQLException {
        return select.service();
    }

    /**
     * 数据库表结构列表
     */
    @PostMapping("/detail")
    public Result<?> detail(@RequestBody @Valid DetailDto dto) {
        return detail.service(dto);
    }
}
