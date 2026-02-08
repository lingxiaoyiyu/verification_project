<?php

namespace Library\Log;

use Psr\Log\LoggerInterface;
use Psr\Log\LogLevel;

/**
 * PSR-3 日志接口实现类
 * 适配现有 Log 类，实现 PSR-3 标准
 */
class PsrLogger implements LoggerInterface
{
    /**
     * 日志级别映射（PSR-3 -> 内部级别）
     * @var array
     */
    protected $levelMap = [
        LogLevel::EMERGENCY => 'emergency',
        LogLevel::ALERT => 'alert',
        LogLevel::CRITICAL => 'critical',
        LogLevel::ERROR => 'error',
        LogLevel::WARNING => 'warning',
        LogLevel::NOTICE => 'notice',
        LogLevel::INFO => 'info',
        LogLevel::DEBUG => 'debug',
    ];

    /**
     * 系统不可用
     *
     * @param string $message
     * @param array $context
     * @return void
     */
    public function emergency($message, array $context = []): void
    {
        $this->log(LogLevel::EMERGENCY, $message, $context);
    }

    /**
     * 必须立即采取行动
     *
     * 例如：整个网站宕机，数据库不可用等
     *
     * @param string $message
     * @param array $context
     * @return void
     */
    public function alert($message, array $context = []): void
    {
        $this->log(LogLevel::ALERT, $message, $context);
    }

    /**
     * 严重情况
     *
     * @param string $message
     * @param array $context
     * @return void
     */
    public function critical($message, array $context = []): void
    {
        $this->log(LogLevel::CRITICAL, $message, $context);
    }

    /**
     * 运行时错误
     *
     * @param string $message
     * @param array $context
     * @return void
     */
    public function error($message, array $context = []): void
    {
        $this->log(LogLevel::ERROR, $message, $context);
    }

    /**
     * 警告但不是错误
     *
     * @param string $message
     * @param array $context
     * @return void
     */
    public function warning($message, array $context = []): void
    {
        $this->log(LogLevel::WARNING, $message, $context);
    }

    /**
     * 普通但重要的事件
     *
     * @param string $message
     * @param array $context
     * @return void
     */
    public function notice($message, array $context = []): void
    {
        $this->log(LogLevel::NOTICE, $message, $context);
    }

    /**
     * 有趣的事件
     *
     * 例如：用户登录，SQL日志等
     *
     * @param string $message
     * @param array $context
     * @return void
     */
    public function info($message, array $context = []): void
    {
        $this->log(LogLevel::INFO, $message, $context);
    }

    /**
     * 详细的调试信息
     *
     * @param string $message
     * @param array $context
     * @return void
     */
    public function debug($message, array $context = []): void
    {
        $this->log(LogLevel::DEBUG, $message, $context);
    }

    /**
     * 记录带有任意级别的日志
     *
     * @param mixed $level
     * @param string $message
     * @param array $context
     * @return void
     */
    public function log($level, $message, array $context = []): void
    {
        // 转换 PSR-3 级别为内部级别
        $internalLevel = $this->levelMap[$level] ?? 'info';

        // 处理上下文中的占位符
        $message = $this->interpolate($message, $context);

        // 调用现有的 Log 类记录日志
        Log::log($internalLevel, $message, $context);
    }

    /**
     * 处理消息中的占位符
     *
     * PSR-3 规范要求支持 {placeholder} 格式的占位符
     *
     * @param string $message
     * @param array $context
     * @return string
     */
    protected function interpolate($message, array $context = []): string
    {
        // 构建替换数组
        $replace = [];
        foreach ($context as $key => $val) {
            // 检查值是否可以转换为字符串
            if (!is_array($val) && (!is_object($val) || method_exists($val, '__toString'))) {
                $replace['{' . $key . '}'] = $val;
            }
        }

        // 替换消息中的占位符
        return strtr($message, $replace);
    }
}
