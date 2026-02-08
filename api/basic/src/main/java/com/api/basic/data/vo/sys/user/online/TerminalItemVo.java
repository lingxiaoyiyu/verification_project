package com.api.basic.data.vo.sys.user.online;

import lombok.Data;

/**
 * 用户信息相关接口响应用VO
 */
@Data
public class TerminalItemVo {

    // 终端类型
    private String clientForm;
    // 创建时间
    private String createdAt;
    // 最后活跃时间
    private String lastActiveAt;
    // Token
    private String token;
    // 状态
    private Integer status;
}
