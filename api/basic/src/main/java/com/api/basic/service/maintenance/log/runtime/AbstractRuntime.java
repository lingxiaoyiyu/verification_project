package com.api.basic.service.maintenance.log.runtime;

import cn.hutool.core.io.FileUtil;
import cn.hutool.core.util.StrUtil;
import com.api.basic.data.dto.maintenance.log.runtime.PageDto;
import com.api.basic.data.vo.maintenance.log.runtime.PageItemVo;
import com.api.common.base.AbstractService;
import org.springframework.beans.BeanUtils;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class AbstractRuntime extends AbstractService {

    // 日志目录
    private static final String LOG_DIR = System.getProperty("user.dir") + "/logs";
    // 日志文件名格式
    private static final String FILE_NAME_PATTERN = "app-\\d{4}-\\d{2}-\\d{2}_\\d{2}\\.log";
    // 日志行解析正则
    private static final Pattern LOG_PATTERN = Pattern.compile(
            "^(\\d{4}-\\d{2}-\\d{2} \\d{2}:\\d{2}:\\d{2}\\.\\d{3}) " +  // 时间戳（无前缀 "-"）
                    "- (.*?) " +        // 线程名（注意：这里有空格和方括号）
                    "- (.*?) " +       // 日志级别
                    "- (.*?) " +        // method
                    "- (.*?) " +        // schema
                    "- (.*?) " +        // domain
                    "- (.*?) " +        // uri
                    "- (.*?) " +        // ip
                    "- (.*?) " +        // port
                    "- (.*?) " +        // requestId
                    "- (.*?) " +        // uid
                    "- (.*?) " +        // logger
                    "- (.*)$"           // message
    );
    // 时间格式化
    private static final DateTimeFormatter TIME_FORMATTER =
            DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS");
    private static final DateTimeFormatter FILE_TIME_FORMATTER =
            DateTimeFormatter.ofPattern("yyyy-MM-dd_HH");
    private static final DateTimeFormatter QUERY_TIME_FORMATTER =
            DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");


    /**
     * 解析日志条目
     */
    protected LogEntry parseLogEntry(String logLine) {
        if (StrUtil.isBlank(logLine)) return null;

        Matcher matcher = LOG_PATTERN.matcher(logLine);
        if (!matcher.find()) return null;
        LogEntry entry = new LogEntry();
        try {
            entry.setTimestamp(LocalDateTime.parse(matcher.group(1), TIME_FORMATTER));
            entry.setThread(matcher.group(2));
            entry.setLevel(matcher.group(3));
            entry.setMethod(matcher.group(4));
            entry.setSchema(matcher.group(5));
            entry.setDomain(matcher.group(6));
            entry.setUri(matcher.group(7));
            entry.setIp(matcher.group(8));
            entry.setPort(matcher.group(9));
            entry.setRequestId(matcher.group(10));
            entry.setUid(StrUtil.isBlank(matcher.group(11)) ? null : Integer.valueOf(matcher.group(11)));
            entry.setLogger(matcher.group(12));
            entry.setMessage(matcher.group(13));
        } catch (Exception e) {
            e.printStackTrace();
            return null; // 解析失败
        }
        return entry;
    }

    /**
     * 查找时间范围内的日志文件
     */
    protected List<Path> findLogFiles(String startTimeStr, String endTimeStr) {
        List<Path> logFiles = new ArrayList<>();

        // 解析时间范围
        LocalDateTime startTime = parseTime(startTimeStr, LocalDateTime.MIN);
        LocalDateTime endTime = parseTime(endTimeStr, LocalDateTime.MAX);

        // 获取日志目录
        File logDir = new File(LOG_DIR);
        if (!logDir.exists() || !logDir.isDirectory()) {
            return logFiles;
        }

        // 日志文件每小时一个，app.log中是当前小时的日志
        // 如果解析时间范围包含当前小时，则将app.log添加到logFiles中
        if (endTime.getHour() > LocalDateTime.now().getHour()) {
            logFiles.add(Paths.get(LOG_DIR+"/app.log"));
        }
        // 遍历日志文件
        for (File file : FileUtil.ls(LOG_DIR)) {
            if (!file.getName().matches(FILE_NAME_PATTERN)) {
                continue;
            }

            // 从文件名解析时间：app-2025-09-20_10.log
            String timeStr = file.getName()
                    .replace("app-", "")
                    .replace(".log", "");

            try {
                LocalDateTime fileTime = LocalDateTime.parse(timeStr, FILE_TIME_FORMATTER);
                // 检查文件时间是否在查询范围内
                if (!fileTime.isBefore(startTime) && !fileTime.isAfter(endTime)) {
                    logFiles.add(Paths.get(file.getAbsolutePath()));
                }
            } catch (Exception e) {
                e.printStackTrace();
                // 忽略格式错误的文件名
            }
        }
        return logFiles;
    }

    protected LocalDateTime parseTime(String timeStr, LocalDateTime defaultValue) {
        if (StrUtil.isBlank(timeStr)) return defaultValue;
        try {
            return LocalDateTime.parse(timeStr, QUERY_TIME_FORMATTER);
        } catch (Exception e) {
            return defaultValue;
        }
    }

    /**
     * 检查日志条目是否匹配查询条件
     */
    protected boolean matches(PageDto dto, LogEntry entry) {
        // 日志级别过滤
        if (StrUtil.isNotBlank(dto.getLogLevel()) &&
                !dto.getLogLevel().equalsIgnoreCase(entry.getLevel())) {
            return false;
        }

        if (StrUtil.isBlank(entry.getUri())) {
            return false;
        } else {
            if (entry.getUri().startsWith("/maintenance/log") || entry.getUri().startsWith("/favicon.ico")) {
                return false;
            } else {
                // URI过滤
                if (StrUtil.isNotBlank(dto.getUri()) &&
                        !entry.getUri().contains(dto.getUri())) {
                    return false;
                }
            }
        }

        // 请求ID过滤
        if (StrUtil.isNotBlank(dto.getRequestId()) &&
                !dto.getRequestId().equals(entry.getRequestId())) {
            return false;
        }

        // IP过滤
        if (StrUtil.isNotBlank(dto.getIp()) &&
                !dto.getIp().equals(entry.getIp())) {
            return false;
        }

        // 域名过滤
        if (StrUtil.isNotBlank(dto.getDomain()) &&
                !dto.getDomain().equals(entry.getDomain())) {
            return false;
        }

        // 用户过滤
        if (dto.getUids() != null &&!dto.getUids().isEmpty()) {
            // 判断dto.getUids()中是否包含 entry.getUid()
            if (entry.getUid() == null || !dto.getUids().contains(entry.getUid())) {
                return false;
            }
        }
        // 过滤OPTIONS
        if ("OPTIONS".equals(entry.getMethod())) {
            return false;
        }

        return true;
    }

    /**
     * 转换为前端需要的VO格式
     */
    protected PageItemVo convertToPageItemVo(LogEntry entry) {
        PageItemVo vo = new PageItemVo();
        BeanUtils.copyProperties(entry, vo);
        vo.setCreatedAt(entry.getTimestamp().format(QUERY_TIME_FORMATTER));
        vo.setContent(StrUtil.format("[{}] {} - {}",
                entry.getThread(), entry.getLogger(), entry.getMessage()));
        return vo;
    }

    /**
     * 日志条目内部类
     */
    protected static class LogEntry {
        private LocalDateTime timestamp;
        private String thread;
        private String level;
        private String method;
        private String schema;
        private String domain;
        private String uri;
        private String ip;
        private String port;
        private String requestId;
        private Integer uid;
        private String logger;
        private String message;

        // Getter和Setter
        public LocalDateTime getTimestamp() { return timestamp; }
        public void setTimestamp(LocalDateTime timestamp) { this.timestamp = timestamp; }
        public String getThread() { return thread; }
        public void setThread(String thread) { this.thread = thread; }
        public String getLevel() { return level; }
        public void setLevel(String level) { this.level = level; }
        public String getMethod() { return method; }
        public void setMethod(String method) { this.method = method; }
        public String getSchema() { return schema; }
        public void setSchema(String schema) { this.schema = schema; }
        public String getDomain() { return domain; }
        public void setDomain(String domain) { this.domain = domain; }
        public String getUri() { return uri; }
        public void setUri(String uri) { this.uri = uri; }
        public String getIp() { return ip; }
        public void setIp(String ip) { this.ip = ip; }
        public String getPort() { return port; }
        public void setPort(String port) { this.port = port; }
        public String getRequestId() { return requestId; }
        public void setRequestId(String requestId) { this.requestId = requestId; }
        public Integer getUid() { return uid; }
        public void setUid(Integer uid) { this.uid = uid; }
        public String getLogger() { return logger; }
        public void setLogger(String logger) { this.logger = logger; }
        public String getMessage() { return message; }
        public void setMessage(String message) { this.message = message; }
    }
}
