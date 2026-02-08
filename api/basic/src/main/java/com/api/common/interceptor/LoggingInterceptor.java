package com.api.common.interceptor;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class LoggingInterceptor implements HandlerInterceptor {

    private static final Logger logger = LoggerFactory.getLogger(LoggingInterceptor.class);
    private final ConcurrentHashMap<Long, Long> startTimeMap = new ConcurrentHashMap<>();

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        long startTime = System.currentTimeMillis();
        startTimeMap.put(Thread.currentThread().threadId(), startTime);
        logRequest(request, startTime);
        return true; // 继续处理请求
    }

    private void logRequest(HttpServletRequest request, long startTime) {
        StringBuilder logMessage = new StringBuilder();
        logMessage.append("[REQUEST] ");
        logMessage.append(request.getMethod());
        logMessage.append(" ");
        logMessage.append(request.getRequestURI());
        logMessage.append(" - Start Time: ");
        logMessage.append(startTime);

        // 添加请求头信息
        logMessage.append("\nHeaders: ").append(request.getHeaderNames().toString());

        // 处理GET请求参数
        if (HttpMethod.GET.name().equalsIgnoreCase(request.getMethod())) {
            if (!request.getParameterMap().isEmpty()) {
                logMessage.append("\nParameters: ");
                for (Map.Entry<String, String[]> entry : request.getParameterMap().entrySet()) {
                    logMessage.append(entry.getKey()).append("=").append(String.join(",", entry.getValue())).append(", ");
                }
            }
        } else if (HttpMethod.POST.name().equalsIgnoreCase(request.getMethod())) { // TODO 处理POST请求参数，包括表单和JSON

            if (isJsonContentType(request)) {
                logMessage.append("\nJSON Body: ");
            } else {
                logMessage.append("\nForm Data: ");
            }
        }

        // 使用Logback记录日志
        logger.info(logMessage.toString());
    }

    private boolean isJsonContentType(HttpServletRequest request) {
        String contentType = request.getContentType();
        return contentType != null && contentType.toLowerCase().contains("application/json");
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) {
        // 请求处理后可以记录响应时间
        long processingTime = calculateProcessingTime(request);
        logResponseTime(request.getRequestURI(), processingTime);
    }

    private long calculateProcessingTime(HttpServletRequest request) {
        return System.currentTimeMillis() - startTimeMap.remove(Thread.currentThread().threadId());
    }

    private void logResponseTime(String requestUri, long processingTime) {
        logger.info("[RESPONSE] URI: {} - Processing time: {}ms", requestUri, processingTime);
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {
        // 请求完成后，如果有异常，可以在这里处理
        if (ex != null) {
            logger.error("[ERROR] Exception occurred: {}", ex.getMessage(), ex);
        }
    }
}

