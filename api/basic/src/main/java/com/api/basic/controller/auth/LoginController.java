package com.api.basic.controller.auth;

import cn.dev33.satoken.annotation.SaIgnore;
import com.api.basic.data.dto.auth.login.*;
import com.api.basic.service.auth.login.*;
import com.api.common.base.Result;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

/**
 * 用户登录管理相关接口
 */
@RestController
@RequestMapping("/basic/auth/login")
public class LoginController {

    @Resource(name = "BasicAuthUsernameLoginServiceImpl")
    private Username username;
    @Resource(name = "BasicAuthEmailLoginServiceImpl")
    private Email email;
    @Resource(name = "BasicAuthUsernameVerCodeServiceImpl")
    private UsernameVerCode usernameVerCode;
    @Resource(name = "BasicAuthPhoneLoginServiceImpl")
    private Phone phone;
    @Resource(name = "BasicAuthWujieUnionIdServiceImpl")
    private WujieUnionId wujieUnionId;
    @Resource(name = "BasicAuthWechatOfficeLoginServiceImpl")
    private WechatOffice wechatOffice;
    @Resource(name = "BasicAuthWechatMpLoginServiceImpl")
    private WechatMp wechatMp;

    /**
     * 用户名密码登录
     */
    @PostMapping("/username")
    @SaIgnore
    public Result<?> username(@RequestBody @Valid UsernameDto dto, HttpServletRequest request) {
        username.check(dto);
        return username.service(dto, request);
    }

    /**
     * 邮箱密码登录
     */
    @PostMapping("/email")
    @SaIgnore
    public Result<?> email(@RequestBody @Valid EmailDto dto, HttpServletRequest request) {
        email.check(dto);
        return email.service(dto, request);
    }

    /**
     * 用户名密码+验证码登录
     */
    @PostMapping("/usernameVerCode")
    @SaIgnore
    public Result<?> usernameVerCode(@RequestBody @Valid UsernameVerCodeDto dto, HttpServletRequest request) {
        usernameVerCode.check(dto);
        return usernameVerCode.service(dto, request);
    }

    /**
     * 手机验证码登录
     */
    @PostMapping("/phone")
    @SaIgnore
    public Result<?> phone(@RequestBody @Valid PhoneDto dto, HttpServletRequest request) {
        phone.check(dto);
        return phone.service(dto, request);
    }

    /**
     * 无界unionid登录
     */
    @PostMapping("/wujieUnionId")
    @SaIgnore
    public Result<?> wujieUnionId(@RequestBody @Valid WujieUnionidDto dto, HttpServletRequest request) {
        return wujieUnionId.service(dto, request);
    }

    /**
     * 微信公众号登录
     */
    @GetMapping("/wechatoffice")
    @SaIgnore
    public Result<?> wechatoffice(@Valid WechatOfficeDto dto, HttpServletRequest request) {
        return wechatOffice.service(dto, request);
    }

    /**
     * 小程序登录
     */
    @GetMapping("/wechatmp")
    @SaIgnore
    public Result<?> wechatmp(@Valid WechatMpDto dto, HttpServletRequest request) {
        return wechatMp.service(dto, request);
    }
}