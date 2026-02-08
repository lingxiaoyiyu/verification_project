package com.api.common.task;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

//@Component
public class LogCleanupTask {

    private static final Logger logger = LoggerFactory.getLogger(LogCleanupTask.class);

//    private final TbLogRuntimeAccessDao logDao;
//
//    public LogCleanupTask(TbLogRuntimeAccessDao logDao) {
//        this.logDao = logDao;
//    }

//    @Scheduled(cron = "0 0 * * * ?") // 每小时执行一次
    public void cleanupLogs() {
//        logger.info("开始清理日志数据...");

//        logger.info("清理日志数据完成，共删除 {} 条记录。", deletedCount);
    }
}
