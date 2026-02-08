package com.api.common.utils;

import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

@Component
public class StartupListener implements ApplicationListener<ContextRefreshedEvent> {

    private final ApplicationContext context;

    public StartupListener(ApplicationContext context) {
        this.context = context;
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        FunctionUtil.uploadFileDomain = context.getEnvironment().getProperty("config.upload_file_domain");
        FunctionUtil.defaultAvatar = context.getEnvironment().getProperty("config.default_avatar");
    }
}

