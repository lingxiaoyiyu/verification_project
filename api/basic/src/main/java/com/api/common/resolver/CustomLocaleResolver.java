package com.api.common.resolver;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.i18n.AcceptHeaderLocaleResolver;

import java.util.Locale;

public class CustomLocaleResolver implements LocaleResolver {

    private final AcceptHeaderLocaleResolver delegate = new AcceptHeaderLocaleResolver();

    @Override
    public Locale resolveLocale(HttpServletRequest request) {
        // 解析请求头中的Accept-Language
        Locale locale = delegate.resolveLocale(request);
        // zh-CN设置为zh_HANS（匹配文件名 messages_zh_HANS.properties）
        if ("zh_CN".equals(locale.toString())) {
            locale = new Locale("zh", "HANS");
        } else if ("zh_TW".equals(locale.toString())) {
            locale = new Locale("zh", "HANT");
        } else if ("zh_HK".equals(locale.toString())) {
            locale = new Locale("zh", "HANT");
        } else if ("zh_SG".equals(locale.toString())) {
            locale = new Locale("zh", "HANS");
        }
        // 更新当前线程的Locale
        LocaleContextHolder.setLocale(locale);
        return locale;
    }

    @Override
    public void setLocale(HttpServletRequest request, HttpServletResponse response, Locale locale) {
        delegate.setLocale(request, response, locale);
    }
}

