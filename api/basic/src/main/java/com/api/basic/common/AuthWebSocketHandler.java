package com.api.basic.common;

import cn.hutool.json.JSONUtil;
import com.api.common.base.Result;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.io.IOException;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class AuthWebSocketHandler extends TextWebSocketHandler {
    // fd与Session的映射关系
    private static final ConcurrentHashMap<String, WebSocketSession> sessionMap = new ConcurrentHashMap<>();

    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws IOException {
        String fd = UUID.randomUUID().toString();
        sessionMap.put(fd, session);

        session.sendMessage(new TextMessage(JSONUtil.toJsonStr(Result.ok(Map.of("type", "connect", "fd", fd, "timestamp", System.currentTimeMillis() + 300000))))); // 将fd实时返回前端[1,7](@ref)
    }

    // 公共Getter方法（返回Map或特定Session）
    public static WebSocketSession getSession(String fd) {
        return sessionMap.get(fd);
    }
}