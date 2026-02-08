package com.api.basic.controller.sys;

import com.api.basic.data.dto.sys.user.AddDto;
import com.api.basic.data.dto.sys.user.IsExistDto;
import com.api.basic.data.dto.sys.user.page.FriendDto;
import com.api.basic.data.dto.sys.user.online.KickoutDto;
import com.api.basic.data.dto.sys.user.online.PageDto;
import com.api.basic.data.dto.sys.user.online.TerminalListDto;
import com.api.basic.data.dto.sys.user.update.BindInviteCodeDto;
import com.api.basic.data.dto.sys.user.update.StatusDto;
import com.api.basic.data.dto.sys.user.update.password.ByIdDto;
import com.api.basic.data.dto.sys.user.update.password.CurrentDto;
import com.api.basic.service.sys.user.Add;
import com.api.basic.service.sys.user.IsExist;
import com.api.basic.service.sys.user.page.Friend;
import com.api.basic.service.sys.user.online.Kickout;
import com.api.basic.service.sys.user.online.Page;
import com.api.basic.service.sys.user.online.TerminalList;
import com.api.basic.service.sys.user.update.BindInviteCode;
import com.api.basic.service.sys.user.update.Status;
import com.api.basic.service.sys.user.update.password.ById;
import com.api.basic.service.sys.user.update.password.Current;
import com.api.common.base.Result;
import jakarta.annotation.Resource;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 用户管理相关接口
 */
@RestController
@RequestMapping("/basic/sys/user")
public class UserController {

    @Resource(name = "BasicSysUserAddServiceImpl")
    private Add addService;
    @Resource(name = "BasicSysUserUpdateByIdServiceImpl")
    private com.api.basic.service.sys.user.update.ById updateInfoById;
    @Resource(name = "BasicSysUserUpdateCurrentServiceImpl")
    private com.api.basic.service.sys.user.update.Current updateInfoCurrent;
    @Resource(name = "BasicSysUserUpdateStatusServiceImpl")
    private Status updateStatus;
    @Resource(name = "BasicSysUserUpdatePasswordByIdServiceImpl")
    private ById updatePasswordById;
    @Resource(name = "BasicSysUserUpdatePasswordCurrentServiceImpl")
    private Current updatePasswordCurrent;
    @Resource(name = "BasicSysUserPageServiceImpl")
    private com.api.basic.service.sys.user.Page page;
    @Resource(name = "BasicSysUserGetByIdServiceImpl")
    private com.api.basic.service.sys.user.get.ById getById;
    @Resource(name = "BasicSysUserGetCurrentServiceImpl")
    private com.api.basic.service.sys.user.get.Current getCurrent;
    @Resource(name = "BasicSysUserIsExistServiceImpl")
    private IsExist isExist;
    @Resource(name = "BasicBasicSysUserOnlinePageServiceImpl")
    private Page onlinePage;
    @Resource(name = "BasicSysUserOnlineTerminalListServiceImpl")
    private TerminalList terminalList;
    @Resource(name = "BasicSysUserOnlineKickoutServiceImpl")
    private Kickout kickout;
    @Resource(name = "BasicSysUserPageFriendServiceImpl")
    private Friend friend;
    @Resource(name = "BasicSysUserUpdateBindInviteCodeServiceImpl")
    private BindInviteCode bindInviteCode;


    /**
     * 添加用户
     */
    @PostMapping("/add")
    public Result<?> add(@RequestBody @Valid AddDto dto) {
        addService.check(dto);
        return addService.service(dto);
    }

    /**
     * 更新指定用户的信息
     */
    @PostMapping("/update/byId")
    public Result<?> updateInfoById(@RequestBody @Valid com.api.basic.data.dto.sys.user.update.info.ByIdDto dto) {
        updateInfoById.check(dto);
        return updateInfoById.service(dto);
    }

    /**
     * 更新当前登录用户的信息
     */
    @PostMapping("/update/current")
    public Result<?> updateInfoCurrent(@RequestBody @Valid com.api.basic.data.dto.sys.user.update.info.CurrentDto dto) {
        updateInfoCurrent.check(dto);
        return updateInfoCurrent.service(dto);
    }

    /**
     * 管理员修改指定用户状态
     */
    @PostMapping("/update/status")
    public Result<?> status(@RequestBody @Valid StatusDto dto) {
        updateStatus.check(dto);
        return updateStatus.service(dto);
    }

    /**
     * 管理员修改指定用户的密码
     */
    @PostMapping("/update/password/byId")
    public Result<?> updatePasswordById(@RequestBody @Valid ByIdDto dto) {
        updatePasswordById.check(dto);
        return updatePasswordById.service(dto);
    }

    /**
     * 用户修改自己的密码
     */
    @PostMapping("/update/password/current")
    public Result<?> personallyPassword(@RequestBody @Valid CurrentDto dto) {
        updatePasswordCurrent.check(dto);
        return updatePasswordCurrent.service(dto);
    }

    /**
     * 获取用户分页列表
     */
    @PostMapping("/page")
    public Result<?> page(@RequestBody @Valid com.api.basic.data.dto.sys.user.PageDto dto) {
        return page.service(dto);
    }

    /**
     * 获取指定用户详细信息
     */
    @PostMapping("/get/byId")
    public Result<?> getDetailById(@RequestBody @Valid com.api.basic.data.dto.sys.user.get.ByIdDto dto) {
        getById.check(dto);
        return getById.service(dto);
    }

    /**
     * 获取当前登录用户详细信息
     */
    @PostMapping("/get/current")
    public Result<?> getDetailById() {
        return getCurrent.service();
    }

    /**
     * 判断用户信息属性是否存在
     */
    @PostMapping("/isExist")
    public Result<?> isExist(@RequestBody @Valid IsExistDto dto) {
        isExist.check(dto);
        return isExist.service(dto);
    }

    /**
     * 获取在线用户列表-分页
     */
    @PostMapping("/online/page")
    public Result<?> onlinePage(@RequestBody @Valid PageDto dto) {
        return onlinePage.service(dto);
    }

    /**
     * 判断用户信息属性是否存在
     */
    @PostMapping("/online/terminalList")
    public Result<?> terminalList(@RequestBody @Valid TerminalListDto dto) {
        return terminalList.service(dto);
    }

    /**
     * 踢人下线
     */
    @PostMapping("/online/kickout")
    public Result<?> kickout(@RequestBody @Valid KickoutDto dto) {
        return kickout.service(dto);
    }

    /**
     * 获取好友列表
     */
    @PostMapping("/friend")
    public Result<?> friend(@RequestBody @Valid FriendDto dto) {
        friend.check(dto);
        return friend.service(dto);
    }

    /**
     * 绑定邀请码
     */
    @PostMapping("/update/bindInviteCode")
    public Result<?> bindInviteCode(@RequestBody @Valid BindInviteCodeDto dto) {
        bindInviteCode.check(dto);
        return bindInviteCode.service(dto);
    }
}
