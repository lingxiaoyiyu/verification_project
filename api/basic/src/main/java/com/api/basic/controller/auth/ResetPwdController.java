package com.api.basic.controller.auth;

import cn.dev33.satoken.annotation.SaIgnore;
import com.api.basic.data.dto.auth.reg.EmailDto;
import com.api.basic.service.auth.reset.Email;
import com.api.common.base.Result;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 用户重置密码管理相关接口
 */
@RestController
@RequestMapping("/basic/auth")
public class ResetPwdController {

    @Resource(name = "BasicAuthResetEmailPwdServiceImpl")
    private Email emilReset;

    /**
     * 邮箱验证码重置密码
     */
    @PostMapping("/resetPwd/email")
    @SaIgnore
    public Result<?> email(@RequestBody @Valid EmailDto dto, HttpServletRequest request) {
        emilReset.check(dto);
        return emilReset.service(dto, request);
    }
}
