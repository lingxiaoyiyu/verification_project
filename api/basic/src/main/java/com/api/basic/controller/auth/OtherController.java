package com.api.basic.controller.auth;

import com.api.basic.data.dto.auth.other.LogoutDto;
import com.api.basic.service.auth.other.Codes;
import com.api.basic.service.auth.other.Logout;
import com.api.basic.service.auth.other.Menus;
import com.api.basic.service.auth.other.Refresh;
import com.api.common.base.Result;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 其它权限相关接口
 */
@RestController
@RequestMapping("/basic/auth")
public class OtherController {

    @Resource(name = "BasicAuthOtherLogoutServiceImpl")
    private Logout logout;
    @Resource(name = "BasicAuthOtherRefreshServiceImpl")
    private Refresh refresh;
    @Resource(name = "BasicAuthOtherCodesServiceImpl")
    private Codes codes;
    @Resource(name = "BasicAuthOtherMenuServiceImpl")
    private Menus menus;

    /**
     * 退出登录
     */
    @PostMapping("/logout")
    public Result<?> username(@RequestBody @Valid LogoutDto dto) {
        return logout.service(dto);
    }

    /**
     * 刷新token
     */
    @PostMapping("/refresh")
    public Result<?> refresh(HttpServletRequest request) {
        return refresh.service(request);
    }

    /**
     * 获取用户菜单
     */
    @PostMapping("/menus")
    public Result<?> menus() {
        return menus.service();
    }

    /**
     * 获取用户权限码
     */
    @PostMapping("/codes")
    public Result<?> codes() {
        return codes.service();
    }
}
