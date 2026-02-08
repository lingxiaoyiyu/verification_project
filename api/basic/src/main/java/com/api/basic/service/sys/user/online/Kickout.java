package com.api.basic.service.sys.user.online;

import cn.dev33.satoken.stp.StpUtil;
import cn.hutool.core.util.ObjUtil;
import cn.hutool.core.util.StrUtil;
import com.api.basic.data.dto.sys.user.online.KickoutDto;
import com.api.common.base.AbstractService;
import com.api.common.base.Result;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 踢人下线
 */
@Service("BasicSysUserOnlineKickoutServiceImpl")
@RequiredArgsConstructor
public class Kickout extends AbstractService {

    /**
     * 业务逻辑处理
     *
     * @param dto DTO对象
     * @return 处理结果
     */
    @Transactional(rollbackFor = Throwable.class)
    public Result<?> service(KickoutDto dto) {

        if (StrUtil.isNotBlank(dto.getKickoutToken())) {
            StpUtil.kickoutByTokenValue(dto.getKickoutToken());
        } else if (StrUtil.isNotBlank(dto.getClientFrom())) {
            StpUtil.kickout(dto.getKickoutUserId(), dto.getClientFrom());
        } else if (ObjUtil.isNotEmpty(dto.getKickoutUserId())) {
            StpUtil.kickout(dto.getKickoutUserId());
        }
        return Result.ok("");
    }
}
