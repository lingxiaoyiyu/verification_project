package com.api.common.base;

import com.api.common.utils.FunctionUtil;
import org.springframework.beans.factory.annotation.Value;

public abstract class AbstractService {

    @Value("${result.code.success:200}")
    private int successCode;
    @Value("${spring.profiles.active}")
    private String active;
    protected String commonFailMessage;

    /**
     * 构造函数
     * @return
     */
    public AbstractService() {
        this.commonFailMessage = FunctionUtil.getI18nString("result.fail");
    }

    protected String getActiveProfile() {
        return active;
    }

    protected boolean isDev() {
        return "dev".equals(active);
    }

    protected boolean isProd() {
        return "prod".equals(active);
    }
}
