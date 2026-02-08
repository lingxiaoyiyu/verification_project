package com.api.basic.controller.common;

import cn.dev33.satoken.annotation.SaIgnore;
import com.api.basic.data.dto.common.verifyCode.EmailCodeDto;
import com.api.basic.data.dto.common.verifyCode.PhoneCodeDto;
import com.api.basic.service.common.verifyCode.Email;
import com.api.basic.service.common.verifyCode.Img;
import com.api.basic.service.common.verifyCode.PhoneCode;
import com.api.common.base.Result;
import jakarta.annotation.Resource;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 验证码管理相关接口
 */
@RestController
@RequestMapping("/basic/common/verifyCode")
public class VerifyCodeController {

    @Resource(name = "VerifyCodeImgServiceImpl")
    Img img;
    @Resource(name = "VerifyCodePhoneCodeServiceImpl")
    PhoneCode phoneCode;
    @Resource(name = "VerifyCodeEmailServiceImpl")
    Email email;

    /**
     * 获取图片验证码
     */
    @PostMapping("/img")
    @SaIgnore
    public Result<?> img() {
        return img.service();
    }

    /**
     * 获取手机验证码
     */
    @PostMapping("/phone")
    @SaIgnore
    public Result<?> phone(@RequestBody @Valid PhoneCodeDto dto) {
        phoneCode.check(dto);
        return phoneCode.service(dto);
    }

    /**
     * 获取邮件验证码
     */
    @PostMapping("/email")
    @SaIgnore
    public Result<?> email(@RequestBody @Valid EmailCodeDto dto) {
        return email.service(dto);
    }
}
