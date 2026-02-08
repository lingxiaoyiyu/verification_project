package com.api.common.config;

import cn.dev33.satoken.spring.SaBeanInject;
import com.api.common.resolver.CustomLocaleResolver;
import org.mybatis.spring.mapper.MapperScannerConfigurer;
import org.springframework.boot.LazyInitializationExcludeFilter;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Configuration
//@EnableWebMvc
@EnableTransactionManagement
public class WebConfig implements WebMvcConfigurer {

    @Bean
    public MapperScannerConfigurer mapperScannerConfigurer() {
        MapperScannerConfigurer scannerConfigurer = new MapperScannerConfigurer();
        scannerConfigurer.setBasePackage("com.api.*");
        return scannerConfigurer;
    }

    @Bean
    public LocaleResolver localeResolver() {
        return new CustomLocaleResolver();
    }

    /**
     * 配置 Validator 使用 MessageSource 进行国际化
     */
    @Bean
    public LocalValidatorFactoryBean validator(MessageSource messageSource) {
        LocalValidatorFactoryBean validator = new LocalValidatorFactoryBean();
        validator.setValidationMessageSource(messageSource);
        return validator;
    }

    @Bean(name = "messageSource")
    public MessageSource messageSource() {
        ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();
        
        // 自动扫描 classpath 下所有模块的 i18n 目录
        List<String> basenames = scanI18nBasenames();
        System.out.println("basenames : " + basenames);
        messageSource.setBasenames(basenames.toArray(new String[0]));
        messageSource.setDefaultEncoding("UTF-8");
        
        // 打印加载的资源文件信息（调试用）
        System.out.println("================ MessageSource 配置信息 ================");
        System.out.println("自动扫描到的 Basenames: " + String.join(", ", basenames));
        System.out.println("========================================================");
        
        return messageSource;
    }
    
    /**
     * 扫描 classpath 下所有 i18n 目录，自动发现所有模块的语言文件
     * 支持的目录结构：
     *   - classpath:i18n/messages*.properties           (主模块)
     *   - classpath:i18n/os/messages*.properties        (子模块)
     *   - classpath:i18n/apps/messages*.properties      (子模块)
     *   - classpath:i18n/traffic/messages*.properties   (子模块)
     */
    private List<String> scanI18nBasenames() {
        Set<String> basenameSet = new HashSet<>();
        PathMatchingResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
        
        try {
            // 扫描所有 i18n 目录下的 messages*.properties 文件
            Resource[] resources = resolver.getResources("classpath*:i18n/**/messages*.properties");
            
            for (Resource resource : resources) {
                String path = resource.getURL().getPath();
                // 提取 basename：从 i18n 开始到 messages 之前
                // 例：/i18n/os/messages_zh_HANS.properties -> i18n/os/messages
                int i18nIndex = path.indexOf("/i18n/");
                if (i18nIndex >= 0) {
                    String relativePath = path.substring(i18nIndex + 1); // i18n/os/messages_zh_HANS.properties
                    int messagesIndex = relativePath.indexOf("/messages");
                    if (messagesIndex < 0) {
                        messagesIndex = relativePath.indexOf("messages");
                    }
                    if (messagesIndex >= 0) {
                        String basename = "classpath:" + relativePath.substring(0, messagesIndex) + "/messages";
                        // 去掉多余的斜杠
                        basename = basename.replace("//", "/");
                        basenameSet.add(basename);
                    }
                }
            }
        } catch (IOException e) {
            System.err.println("扫描 i18n 目录失败: " + e.getMessage());
            // 如果扫描失败，使用默认配置
            basenameSet.add("classpath:i18n/messages");
        }
        
        // 按字典顺序排序，确保主模块（i18n/messages）在最前面
        List<String> sortedBasenames = new ArrayList<>(basenameSet);
        sortedBasenames.sort((a, b) -> {
            // i18n/messages 排在最前
            if (a.endsWith("i18n/messages")) return -1;
            if (b.endsWith("i18n/messages")) return 1;
            return a.compareTo(b);
        });
        
        return sortedBasenames;
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        // 将 /uploads/** 的请求映射到 classpath:/uploads/ 目录下的资源
        registry.addResourceHandler("/uploads/**")
                .addResourceLocations("file:./uploads/");
    }

    @Bean
    LazyInitializationExcludeFilter integrationLazyInitExcludeFilter() {
        return LazyInitializationExcludeFilter.forBeanTypes(SaBeanInject.class);
    }
}

