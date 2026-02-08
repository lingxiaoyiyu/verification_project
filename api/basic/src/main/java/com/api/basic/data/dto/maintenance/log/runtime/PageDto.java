package com.api.basic.data.dto.maintenance.log.runtime;

import com.api.common.base.data.dto.BasePageDto;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

/**
 * 获取查询列表接口请求用DTO
 *
 * @author 裴金伟
 * @date 2025-02-21
*/
@EqualsAndHashCode(callSuper = true)
@Data
public class PageDto extends BasePageDto {

    private String uid;
    // 开始时间
    private String startTime;
    // 结束时间
    private String endTime;
    // 日志等级
    private String logLevel;
    // 用户名
    private String username;
    // 手机号
    private String phoneNumber;
    // 真实姓名
    private String realName;
    // URI
    private String uri;
    // 请求ID
    private String requestId;
    // 请求IP
    private String ip;
    // 请求域名
    private String domain;
    private List<Integer> uids;
}
