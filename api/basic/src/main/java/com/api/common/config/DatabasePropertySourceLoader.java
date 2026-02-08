package com.api.common.config;

import com.api.basic.dao.TbBasicConfigDao;
import com.api.basic.data.po.TbBasicConfigPo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.env.MutablePropertySources;
import org.springframework.stereotype.Component;

import jakarta.annotation.PostConstruct;
import java.util.List;

/**
 * 数据库配置属性源加载器
 */
@Component
public class DatabasePropertySourceLoader {

    @Autowired
    private TbBasicConfigDao configDao;

    @Autowired
    private ConfigurableApplicationContext applicationContext;

    private DatabasePropertySource databasePropertySource;

    @PostConstruct
    public void loadProperties() {
        try {
            // 创建数据库属性源
            databasePropertySource = new DatabasePropertySource("databaseProperties");
            
            // 从数据库加载配置
            loadFromDatabase();
            
            // 注册到Spring环境中
            registerPropertySource();
        } catch (Exception e) {
            // 记录警告日志，但不中断应用启动
            System.err.println("警告：无法从数据库加载配置属性: " + e.getMessage());
            e.printStackTrace();
        }
    }

    /**
     * 从数据库加载配置属性
     */
    private void loadFromDatabase() {
        try {
            // 查询所有启用的配置项
            List<com.api.basic.data.entity.TbBasicConfig> configs = configDao.query(
                TbBasicConfigPo.builder()
                    .status(1) // 只加载启用的配置
                    .build()
            );

            // 将配置项添加到属性源中
            for (com.api.basic.data.entity.TbBasicConfig config : configs) {
                databasePropertySource.updateProperty(config.getName(), config.getValue());
            }
        } catch (Exception e) {
            // 记录警告日志，但不中断操作
            System.err.println("警告：从数据库加载配置时出错: " + e.getMessage());
            e.printStackTrace();
        }
    }

    /**
     * 注册属性源到Spring环境
     */
    private void registerPropertySource() {
        ConfigurableEnvironment environment = applicationContext.getEnvironment();
        MutablePropertySources propertySources = environment.getPropertySources();
        
        // 将数据库属性源添加到环境中的第一位，优先级最高
        propertySources.addFirst(databasePropertySource);
    }

    /**
     * 刷新配置属性
     */
    public void refreshProperties() {
        try {
            // 重新从数据库加载配置
            loadFromDatabase();
            
            // 通知环境配置已更改
            applicationContext.publishEvent(new org.springframework.context.event.ContextRefreshedEvent(applicationContext));
        } catch (Exception e) {
            // 记录错误日志
            System.err.println("错误：刷新数据库配置时出错: " + e.getMessage());
            e.printStackTrace();
        }
    }
}