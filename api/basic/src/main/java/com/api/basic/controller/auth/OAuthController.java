package com.api.basic.controller.auth;

import com.api.basic.data.dto.auth.oauth.NotifyDto;
import com.api.basic.service.auth.oauth.Notify;
import com.api.common.base.Result;
import jakarta.annotation.Resource;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 授权登录管理相关接口
 */
@RestController
@RequestMapping("/basic/oauth")
public class OAuthController {

    @Resource(name = "BasicAuthOAuthNotifyServiceImpl")
    private Notify notify;

    /**
     * OAuth授权登录通知
     */
    @PostMapping("/notify")
    public Result<?> notify(@RequestBody @Valid NotifyDto dto) {
        return notify.service(dto);
    }

}
