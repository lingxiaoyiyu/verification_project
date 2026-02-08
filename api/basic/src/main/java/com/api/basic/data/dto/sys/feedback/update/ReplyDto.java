package com.api.basic.data.dto.sys.feedback.update;

import lombok.Data;

/**
 * 回复问题反馈接口请求用DTO
 *
 * @author 裴金伟
 */
@Data
public class ReplyDto {

    // ID
    private Integer id;
    // 回复内容
    private String replyContent;
}
