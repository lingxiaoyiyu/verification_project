package com.api.basic.service.auth.other;

import cn.dev33.satoken.stp.StpUtil;
import cn.hutool.core.util.StrUtil;
import com.api.basic.data.dto.auth.other.LogoutDto;
import com.api.common.base.AbstractService;
import com.api.common.base.Result;
import org.springframework.stereotype.Service;

/**
 * 退出登录
 */
@Service("BasicAuthOtherLogoutServiceImpl")
public class Logout extends AbstractService {

    /**
     * 业务主体]
     *
     * @param dto DTO对象
     * @return 处理结果
     */
    public Result<?> service(LogoutDto dto) {
        StpUtil.logout(StpUtil.getLoginIdAsInt(), StrUtil.isBlank(dto.getClientFrom()) ? "other" : dto.getClientFrom());
        return Result.ok("退出成功");
    }
}
