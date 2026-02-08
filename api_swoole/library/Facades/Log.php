<?php

namespace Library\Facades;

use Library\Log\Log as Logger;

/**
 * 日志门面类
 * 简化的日志接口，直接调用底层Log类
 */
class Log {
    
    /**
     * 调试日志
     *
     * @param mixed $log 日志内容
     * @param array $context 上下文数据
     * @return void
     */
    public static function debug($log, $context = []) {
        Logger::debug($log, $context);
    }
    
    /**
     * 普通日志
     *
     * @param mixed $log 日志内容
     * @param array $context 上下文数据
     * @return void
     */
    public static function info($log, $context = []) {
        Logger::info($log, $context);
    }
    
    /**
     * 通知日志
     *
     * @param mixed $log 日志内容
     * @param array $context 上下文数据
     * @return void
     */
    public static function notice($log, $context = []) {
        Logger::notice($log, $context);
    }
    
    /**
     * 警告日志
     *
     * @param mixed $log 日志内容
     * @param array $context 上下文数据
     * @return void
     */
    public static function warning($log, $context = []) {
        Logger::warning($log, $context);
    }
    
    /**
     * 错误日志
     *
     * @param mixed $log 日志内容
     * @param array $context 上下文数据
     * @return void
     */
    public static function error($log, $context = []) {
        Logger::error($log, $context);
    }
    
    /**
     * 严重错误日志
     *
     * @param mixed $log 日志内容
     * @param array $context 上下文数据
     * @return void
     */
    public static function critical($log, $context = []) {
        Logger::critical($log, $context);
    }
    
    /**
     * 警报日志
     *
     * @param mixed $log 日志内容
     * @param array $context 上下文数据
     * @return void
     */
    public static function alert($log, $context = []) {
        Logger::alert($log, $context);
    }
    
    /**
     * 紧急日志
     *
     * @param mixed $log 日志内容
     * @param array $context 上下文数据
     * @return void
     */
    public static function emergency($log, $context = []) {
        Logger::emergency($log, $context);
    }

    /**
     * 异常日志
     *
     * @param \Throwable $th 异常对象
     * @return void
     */
    public static function exception(\Throwable $th) {
        if ($th->getCode()) {
            $content = $th->getMessage() . '_' . $th->getCode().PHP_EOL . $th->getTraceAsString();
        } else {
            $content = $th->getMessage() .PHP_EOL . $th->getTraceAsString();
        }
        Logger::error($content);
    }
    
    /**
     * 获取日志队列状态
     *
     * @return array 队列状态信息
     */
    public static function getQueueStatus() {
        return Logger::getQueueStatus();
    }
    
    /**
     * 重置日志系统（用于热重启）
     *
     * @return void
     */
    public static function reset() {
        Logger::reset();
    }
}