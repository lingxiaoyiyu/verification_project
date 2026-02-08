package com.api.basic.controller;

import com.api.basic.data.dto.sys.feedback.GetDto;
import com.api.basic.data.dto.sys.feedback.PageDto;
import com.api.basic.service.feedback.Add;
import com.api.basic.service.feedback.Get;
import com.api.basic.service.feedback.Page;
import com.api.basic.service.feedback.Remove;
import com.api.basic.service.feedback.update.Reply;
import com.api.basic.service.feedback.update.Status;
import com.api.common.base.Result;
import jakarta.annotation.Resource;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 问题反馈管理相关接口
 */
@RestController
@RequestMapping("/basic/feedback")
public class FeedbackController {

    @Resource(name = "BasicFeedbackAddServiceImpl")
    private Add add;
    @Resource(name = "BasicFeedbackRemoveServiceImpl")
    private Remove remove;
    @Resource(name = "BasicFeedbackUpdateStatusServiceImpl")
    private Status updateStatus;
    @Resource(name = "BasicFeedbackUpdateReplyServiceImpl")
    private Reply updateReply;
    @Resource(name = "BasicFeedbackGetServiceImpl")
    private Get get;
    @Resource(name = "BasicFeedbackPageServiceImpl")
    private Page page;

    /**
     * 添加问题反馈信息
     */
    @PostMapping("/add")
    public Result<?> add(@RequestBody @Valid com.api.basic.data.dto.sys.feedback.AddDto dto) {
        add.check(dto);
        return add.service(dto);
    }

    /**
     * 删除问题反馈信息
     */
    @PostMapping("/remove")
    public Result<?> remove(@RequestBody @Valid com.api.basic.data.dto.sys.feedback.RemoveDto dto) {
        remove.check(dto);
        return remove.service(dto);
    }

    /**
     * 修改问题反馈状态信息
     */
    @PostMapping("/update/status")
    public Result<?> status(@RequestBody @Valid com.api.basic.data.dto.sys.feedback.update.StatusDto dto) {
        updateStatus.check(dto);
        return updateStatus.service(dto);
    }

    /**
     * 回复问题反馈
     */
    @PostMapping("/update/reply")
    public Result<?> reply(@RequestBody @Valid com.api.basic.data.dto.sys.feedback.update.ReplyDto dto) {
        updateReply.check(dto);
        return updateReply.service(dto);
    }

    /**
     * 获取指定问题反馈信息
     */
    @PostMapping("/get")
    public Result<?> get(@RequestBody @Valid GetDto dto) {
        get.check(dto);
        return get.service(dto);
    }

    /**
     * 获取问题反馈列表-分页
     */
    @PostMapping("/page")
    public Result<?> page(@RequestBody @Valid PageDto dto) {
        page.check(dto);
        return page.service(dto);
    }
}
