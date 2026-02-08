package com.api.basic.event.entity;

import jakarta.servlet.http.HttpServletRequest;
import lombok.Data;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
public class UserRegisterEntity {

    // 用户ID
    private int userId;
    // 请求对象
    private HttpServletRequest request;
}
