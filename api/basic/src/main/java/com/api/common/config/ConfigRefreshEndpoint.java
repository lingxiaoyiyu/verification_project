package com.api.common.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.endpoint.annotation.Endpoint;
import org.springframework.boot.actuate.endpoint.annotation.WriteOperation;
import org.springframework.stereotype.Component;

/**
 * 配置刷新端点
 * 通过调用此端点可以刷新数据库配置
 */
@Component
@Endpoint(id = "refresh-config")
public class ConfigRefreshEndpoint {

    @Autowired
    private DatabasePropertySourceLoader propertySourceLoader;

    /**
     * 刷新数据库配置
     * @return 操作结果
     */
    @WriteOperation
    public String refresh() {
        try {
            propertySourceLoader.refreshProperties();
            return "配置刷新成功";
        } catch (Exception e) {
            return "配置刷新失败: " + e.getMessage();
        }
    }
}