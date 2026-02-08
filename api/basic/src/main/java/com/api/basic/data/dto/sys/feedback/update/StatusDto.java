package com.api.basic.data.dto.sys.feedback.update;

import lombok.Data;

/**
 * 修改问题反馈状态信息接口请求用DTO
 *
 * @author 裴金伟
 */
@Data
public class StatusDto {

    // ID
    private Integer id;
    // 处理状态。1：待处理，2：处理中，3：已解决，4：已拒绝
    private Integer status;
    // 更新时间
    private String updatedAt;
}
