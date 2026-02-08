package com.api.basic.service.auth.oauth;

import cn.dev33.satoken.stp.StpUtil;
import cn.dev33.satoken.stp.parameter.SaLoginParameter;
import cn.hutool.json.JSONUtil;
import com.api.basic.common.AuthWebSocketHandler;
import com.api.basic.data.dto.auth.oauth.NotifyDto;
import com.api.common.base.AbstractService;
import com.api.common.base.Result;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * OAuth授权登录通知
 */
@Service("BasicAuthOAuthNotifyServiceImpl")
public class Notify extends AbstractService {

    /**
     * 参数检查
     *
     * @param dto DTO对象
     */
    public void check(NotifyDto dto) {
    }

    /**
     * 业务主体
     *
     * @param dto DTO对象
     * @return 处理结果
     */
    @Transactional(rollbackFor = Throwable.class)
    public Result<?> service(NotifyDto dto) {
        WebSocketSession session = AuthWebSocketHandler.getSession(dto.getFd());
        if (session != null) {
            Map<String, String> data = new HashMap<>();
            try {
                if("scanSuccess".equals(dto.getType()) ){
                    data.put("type", "scanSuccess");
                    session.sendMessage(new TextMessage(JSONUtil.toJsonStr(Result.ok("扫码成功，等待授权", data))));
                } else if("authOk".equals(dto.getType())){
                    data.put("type", "authOk");
                    StpUtil.login(StpUtil.getLoginIdAsInt(), new SaLoginParameter().setDeviceType("OAuth"));
                    data.put("token", StpUtil.getTokenValueByLoginId(StpUtil.getLoginIdAsInt(), "OAuth"));
                    session.sendMessage(new TextMessage(JSONUtil.toJsonStr(Result.ok("同意授权登录", data))));
                } else if("authConfuse".equals(dto.getType())){
                    data.put("type", "authConfuse");
                    session.sendMessage(new TextMessage(JSONUtil.toJsonStr(Result.ok("拒绝授权登录", data))));
                }
            } catch (IOException e) {
                e.printStackTrace();
                return Result.fail("推送失败1");
            }

            return Result.ok("推送成功", data);
        }
        return Result.fail("推送失败2");
    }
}
