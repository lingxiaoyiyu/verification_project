package com.api.basic.data.dto.maintenance.log.operation;

import com.api.common.base.data.dto.BasePageDto;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

/**
 * 查询操作日志列表接口请求用DTO
 *
 * @author 裴金伟
 * @date 2025-02-22
*/
@EqualsAndHashCode(callSuper = true)
@Data
public class PageDto extends BasePageDto {

    // 用户uid
    private String userId;
    // 用户名
    private String username;
    // 手机号
    private String phoneNumber;
    // 真实姓名
    private String realName;
    // 操作类型
    private String operationType;
    // 开始时间
    private String startTime;
    // 结束时间
    private String endTime;
    private List<Integer> uids;
}
