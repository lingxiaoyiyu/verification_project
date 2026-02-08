package com.api.start;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication(
    exclude = {
        // 排除默认数据源自动配置，使用Druid
        org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration.class,
        // 排除默认JPA自动配置
        org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration.class
    }
)
@ComponentScan({ "com.api.*", "com.github.binarywang" })
@EnableAsync
@EnableScheduling
public class StartApplication {

    public static void main(String[] args) {
        SpringApplication.run(StartApplication.class, args);
    }
}
