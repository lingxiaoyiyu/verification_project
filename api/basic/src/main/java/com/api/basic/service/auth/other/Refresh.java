package com.api.basic.service.auth.other;

import cn.dev33.satoken.SaManager;
import cn.dev33.satoken.stp.StpUtil;
import com.api.common.base.AbstractService;
import com.api.common.base.Result;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.HashMap;
import java.util.Map;

/**
 * 更新token
 */
@Service("BasicAuthOtherRefreshServiceImpl")
@RequiredArgsConstructor
public class Refresh extends AbstractService {

    /**
     * 业务主体]
     *
     * @return 处理结果
     */
    public Result<?> service(HttpServletRequest request) {
        StpUtil.renewTimeout(SaManager.getConfig().getTimeout());
        Map<String, Object> data = new HashMap<>();
        data.put("data", StpUtil.getTokenValue());
        return Result.ok("更新成功", data);
    }

    /**
     * 从请求头中提取 JWT 令牌
     *
     * @param servletRequest HTTP 请求对象
     * @return 提取到的 JWT 令牌，可能为 null
     */
    private String getToken(HttpServletRequest servletRequest) {
        // 如果请求或请求头属性为空，返回 null
        if (servletRequest == null) {
            return null;
        }

        // 从请求头中获取 JWT 令牌
        String headerToken = StpUtil.getTokenValue();

        // 如果请求头包含值
        if (StringUtils.hasText(headerToken)) {
            // 否则直接返回令牌值
            return headerToken;
        }

        // 未找到有效令牌，返回 null
        return null;
    }
}
