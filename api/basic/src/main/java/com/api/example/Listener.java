package com.api.example;

import com.api.basic.event.UserRegisterEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

// 示例监听器-用户注册
@Component
public class Listener implements ApplicationListener<UserRegisterEvent> {

    @Override
    @Async
    public void onApplicationEvent(UserRegisterEvent event) {
        int userId = event.getEntity().getUserId();
        // 处理事件的逻辑
        System.out.println("用户注册: " + userId);
    }
}
