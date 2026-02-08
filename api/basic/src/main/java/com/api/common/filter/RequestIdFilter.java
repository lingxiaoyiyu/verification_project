package com.api.common.filter;

import cn.hutool.core.lang.UUID;
import cn.hutool.core.util.StrUtil;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.util.ContentCachingResponseWrapper;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

@Component
@Order(Ordered.HIGHEST_PRECEDENCE)
public class RequestIdFilter extends OncePerRequestFilter {

    private static final Logger LOGGER = LoggerFactory.getLogger(RequestIdFilter.class);
    private final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        // 彻底拦截favicon.ico请求，不传递给后续处理链
        String requestURI = request.getRequestURI();
        if (requestURI.contains("favicon.ico")) {
            // 设置响应状态为204 No Content
            response.setStatus(HttpServletResponse.SC_NO_CONTENT);
            // 添加缓存控制头，让浏览器长时间缓存，减少请求次数
            response.setHeader("Cache-Control", "max-age=31536000, public");
            response.setHeader("Expires", "" + (System.currentTimeMillis() + 31536000000L));
            // 直接返回，不调用filterChain.doFilter
            return;
        }

        long startTime = System.currentTimeMillis();
        // 捕获请求参数
        StringBuilder requestParams = new StringBuilder();
        Enumeration<String> paramNames = request.getParameterNames();
        while (paramNames.hasMoreElements()) {
            String paramName = paramNames.nextElement();
            String[] paramValues = request.getParameterValues(paramName);
            for (String paramValue : paramValues) {
                if (!requestParams.isEmpty()) {
                    requestParams.append("&");
                }
                requestParams.append(paramName).append("=").append(paramValue);
            }
        }

        // 捕获请求体
        CustomHttpServletRequestWrapper requestWrapper = new CustomHttpServletRequestWrapper(request);
        String requestBody = requestWrapper.getBody();
        if (requestBody != null && !requestBody.isEmpty()) {
            if (!requestParams.isEmpty()) {
                requestParams.append("&");
            }
            requestParams.append(requestBody);
        }

        // 包装响应
        ContentCachingResponseWrapper responseWrapper = new ContentCachingResponseWrapper(response);

        String requestId = UUID.randomUUID().toString();
        MDC.put("requestId", requestId);
//        MDC.put("type", "process");
        MDC.put("method", request.getMethod());
        MDC.put("schema", request.getScheme());
        MDC.put("domain", request.getServerName());
        MDC.put("uri", request.getRequestURI());
        MDC.put("ip", getIpAddress(request));
        MDC.put("port", String.valueOf(request.getServerPort()));

        LOGGER.info("start");

        String responseContent = null;
        try {
            filterChain.doFilter(requestWrapper, responseWrapper);
            // 捕获响应结果
            byte[] contentAsByteArray = responseWrapper.getContentAsByteArray();
            responseContent = new String(contentAsByteArray, StandardCharsets.UTF_8); // 使用 UTF-8 编码
            // 复制响应内容到原始响应
            responseWrapper.copyBodyToResponse();
        } finally {
            // 遍历请求头并将它们添加到 headersMap 中
            Enumeration<String> headerNames = request.getHeaderNames();
            Map<String, String> headers = new HashMap<>();
            while (headerNames.hasMoreElements()) {
                String headerName = headerNames.nextElement();
                String headerValue = request.getHeader(headerName);
                headers.put(headerName, headerValue);
            }
            
            // 创建请求日志对象
            Map<String, Object> requestLogMap = new HashMap<>();
            requestLogMap.put("requestId", requestId);
            requestLogMap.put("method", request.getMethod());
            requestLogMap.put("uri", request.getRequestURI());
            requestLogMap.put("ip", getIpAddress(request));
            requestLogMap.put("headers", headers);
            try {
                requestLogMap.put("requestBody", objectMapper.readValue(requestWrapper.getBody(), Object.class));
            } catch (Exception e) {
                requestLogMap.put("requestBody", StrUtil.blankToDefault(requestWrapper.getBody(), ""));
            }
            requestLogMap.put("uid", MDC.get("uid"));
            
            // 创建响应日志对象
            Map<String, Object> responseLogMap = new HashMap<>();
            responseLogMap.put("requestId", requestId);
            responseLogMap.put("responseStatus", response.getStatus());
            try {
                responseLogMap.put("responseBody", objectMapper.readValue(responseContent, Object.class));
            } catch (Exception e) {
                responseLogMap.put("responseBody", StrUtil.blankToDefault(responseContent, ""));
            }
            responseLogMap.put("Time", System.currentTimeMillis() - startTime);
            
            // 构建请求日志，整合到一条日志中
            StringBuilder logStr = new StringBuilder();
            logStr.append("\n开始请求");
            logStr.append("\n请求ID: ").append(requestId);
            logStr.append("\n请求时间: ").append(new Date());
            logStr.append("\n请求IP: ").append(getIpAddress(request));
            logStr.append("\n请求方法: ").append(request.getMethod());
            logStr.append("\nURL: ").append(request.getRequestURL().toString());
            logStr.append("\n请求头: ");
            logStr.append("\n").append(objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(headers));
            logStr.append("\n参数: \n");
            try {
                // 格式化请求体，使用缩进和换行
                Object requestBodyObj = objectMapper.readValue(requestWrapper.getBody(), Object.class);
                logStr.append(objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(requestBodyObj));
            } catch (Exception e) {
                logStr.append(StrUtil.blankToDefault(requestWrapper.getBody(), ""));
            }

            logStr.append("\n\n结束请求");
            logStr.append("\n响应状态: ").append(response.getStatus());
            logStr.append("\n响应结果: \n");
            try {
                // 格式化响应体，使用缩进和换行
                Object responseBodyObj = objectMapper.readValue(responseContent, Object.class);
                logStr.append(objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(responseBodyObj));
            } catch (Exception e) {
                logStr.append(StrUtil.blankToDefault(responseContent, ""));
            }
            logStr.append("\n处理时间: ").append(System.currentTimeMillis() - startTime).append(" ms");
            LOGGER.info(logStr.toString());
            LOGGER.info("end");

            MDC.remove("requestId");
            MDC.remove("method");
            MDC.remove("schema");
            MDC.remove("domain");
            MDC.remove("uri");
            MDC.remove("ip");
            MDC.remove("port");
            MDC.remove("uid");
        }
    }

    /**
     * 获取客户端IP地址
     *
     * @return IP地址
     */
    public static String getIpAddress(HttpServletRequest request) {
        String ip = request.getHeader("x-forwarded-for");
        if (StrUtil.isBlank(ip) || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("X-real-ip");
        }
        if (StrUtil.isBlank(ip) || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }
        if (StrUtil.isNotBlank(ip)) {
            String[] ips = ip.split(",");
            for (String tmpIp : ips) {
                if (StrUtil.isNotBlank(tmpIp) && !"unknown".equalsIgnoreCase(tmpIp.trim()) && !"127.0.0.1".equals(tmpIp.trim())) {
                    return tmpIp.trim();
                }
            }
        }
        return ip;
    }
}
