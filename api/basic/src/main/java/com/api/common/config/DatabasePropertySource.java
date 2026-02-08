package com.api.common.config;

import org.springframework.core.env.PropertySource;

import java.util.Properties;

/**
 * 数据库配置属性源
 */
public class DatabasePropertySource extends PropertySource<Properties> {
    
    public DatabasePropertySource(String name, Properties source) {
        super(name, source);
    }
    
    public DatabasePropertySource(String name) {
        super(name, new Properties());
    }
    
    @Override
    public Object getProperty(String name) {
        return this.source.getProperty(name);
    }
    
    /**
     * 更新属性值
     * @param key 属性键
     * @param value 属性值
     */
    public void updateProperty(String key, String value) {
        this.source.setProperty(key, value);
    }
}