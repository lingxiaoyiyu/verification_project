package com.api.basic.controller.auth;

import cn.dev33.satoken.annotation.SaIgnore;
import com.api.basic.data.dto.auth.reg.EmailDto;
import com.api.basic.data.dto.auth.reg.UsernameDto;
import com.api.basic.service.auth.reg.Email;
import com.api.basic.service.auth.reg.Username;
import com.api.common.base.Result;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 用户注册管理相关接口
 */
@RestController
@RequestMapping("/basic/auth/reg")
public class RegController {

    @Resource(name = "BasicAuthRegUsernameRegServiceImpl")
    private Username username;

    @Resource(name = "BasicAuthRegEmailRegServiceImpl")
    private Email email;

    /**
     * 用户名密码注册
     */
    @PostMapping("/username")
    @SaIgnore
    public Result<?> username(@RequestBody @Valid UsernameDto dto) {
        username.check(dto);
        return username.service(dto);
    }

    /**
     * 邮箱密码+验证码注册
     */
    @PostMapping("/email")
    @SaIgnore
    public Result<?> usernameVerCode(@RequestBody @Valid EmailDto dto, HttpServletRequest request) {
        email.check(dto);
        return email.service(dto, request);
    }
}
