package com.api.basic.data.dto.sys.feedback;

import lombok.Data;

/**
 * 删除问题反馈信息接口请求用DTO
 *
 * @author 裴金伟
 */
@Data
public class RemoveDto {

    // ID
    private Integer id;
    // 更新时间
    private String updatedAt;
}
