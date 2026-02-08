package com.api.basic.event;

import com.api.basic.event.entity.UserRegisterEntity;
import org.springframework.context.ApplicationEvent;

/**
 * 用户注册事件
 */
public class UserRegisterEvent extends ApplicationEvent {
    private final UserRegisterEntity entity;

    public UserRegisterEvent(Object source, UserRegisterEntity userRegisterEntity) {
        super(source);
        this.entity = userRegisterEntity;
    }

    public UserRegisterEntity getEntity() {
        return entity;
    }
}
