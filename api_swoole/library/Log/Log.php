<?php

namespace Library\Log;

use Library\Core\Config;

/**
 * 简化的日志处理类
 * 单例模式，使用Swoole协程通道实现异步日志处理
 */
class Log
{
    /**
     * 单例实例
     * @var Log
     */
    private static $instance;

    /**
     * 日志级别常量
     */
    const EMERGENCY = 0;
    const ALERT = 1;
    const CRITICAL = 2;
    const ERROR = 3;
    const WARNING = 4;
    const NOTICE = 5;
    const INFO = 6;
    const DEBUG = 7;

    /**
     * 输出通道类型
     */
    const CHANNEL_FILE = 'file';
    const CHANNEL_CONSOLE = 'console';

    /**
     * 是否允许日志写入
     * @var bool
     */
    protected $allowWrite = true;

    /**
     * 日志配置
     * @var array
     */
    protected $config = [
        'path' => '',
        'level' => 'info',
        'max_files' => 0,
        'file_permission' => 0666,
        'channel' => 'default',
        'channels' => [
            [
                'type' => self::CHANNEL_FILE,
                'enabled' => true
            ],
            [
                'type' => self::CHANNEL_CONSOLE,
                'enabled' => true
            ]
        ]
    ];

    /**
     * 日志级别映射
     * @var array
     */
    protected $levelMap = [
        'emergency' => self::EMERGENCY,
        'alert' => self::ALERT,
        'critical' => self::CRITICAL,
        'error' => self::ERROR,
        'warning' => self::WARNING,
        'notice' => self::NOTICE,
        'info' => self::INFO,
        'debug' => self::DEBUG
    ];

    /**
     * 日志级别名称映射
     * @var array
     */
    protected $levelNameMap = [
        self::EMERGENCY => 'EMERGENCY',
        self::ALERT => 'ALERT',
        self::CRITICAL => 'CRITICAL',
        self::ERROR => 'ERROR',
        self::WARNING => 'WARNING',
        self::NOTICE => 'NOTICE',
        self::INFO => 'INFO',
        self::DEBUG => 'DEBUG'
    ];

    /**
     * 日志队列（协程通道）
     * @var \Swoole\Coroutine\Channel|null
     */
    protected $logQueue = null;

    /**
     * 日志处理器是否运行
     * @var bool
     */
    protected $processorRunning = false;

    /**
     * 控制台输出锁
     * @var \Swoole\Lock|null
     */
    protected $consoleLock = null;

    /**
     * 私有构造函数，防止外部实例化
     * @param array $config 配置参数
     */
    private function __construct($config = [])
    {
        // 合并配置
        if (is_array($config)) {
            $this->config = array_merge($this->config, $config);
        }
        
        // 从全局配置获取日志设置
        $globalConfig = Config::get('log', []);
        
        // 特殊处理channels配置，确保兼容两种格式
        if (isset($globalConfig['channels'])) {
            // 保存通道开关状态
            $channelEnabled = $globalConfig['channels'];
            // 移除channels，避免完全覆盖默认结构
            unset($globalConfig['channels']);
        }
        
        // 合并其他配置
        $this->config = array_merge($this->config, $globalConfig);
        
        // 修正通道配置
        $this->fixChannelsConfig($channelEnabled ?? []);
        
        // 处理关闭日志
        if (!empty($this->config['close'])) {
            $this->allowWrite = false;
            return;
        }

        // 设置默认日志路径
        if (empty($this->config['path'])) {
            $this->config['path'] = \ROOT_PATH . \DS . 'storage' . \DS . 'logs' . \DS;
        } elseif (substr($this->config['path'], -1) != DIRECTORY_SEPARATOR) {
            $this->config['path'] .= DIRECTORY_SEPARATOR;
        }
        
        // 初始化日志队列和处理器
        $this->init();
    }
    
    /**
     * 修正通道配置，兼容新旧两种格式
     * @param array $channelEnabled 通道开关状态
     */
    private function fixChannelsConfig($channelEnabled = [])
    {
        // 确保channels是索引数组格式
        if (!isset($this->config['channels']) || !is_array($this->config['channels'])) {
            $this->config['channels'] = [
                [
                    'type' => self::CHANNEL_FILE,
                    'enabled' => true
                ],
                [
                    'type' => self::CHANNEL_CONSOLE,
                    'enabled' => true
                ]
            ];
        }
        
        // 处理扁平格式的通道配置（兼容旧格式）
        if (isset($channelEnabled['file']) || isset($channelEnabled['console'])) {
            foreach ($this->config['channels'] as &$channel) {
                // 根据通道类型更新enabled状态
                if (isset($channelEnabled[$channel['type']])) {
                    $channel['enabled'] = $channelEnabled[$channel['type']];
                }
            }
        }
    }

    /**
     * 初始化日志系统
     */
    private function init()
    {
        // 初始化协程通道（队列）
        if (extension_loaded('swoole') && class_exists('\Swoole\Coroutine\Channel')) {
            // 创建容量为10000的协程通道
            $this->logQueue = new \Swoole\Coroutine\Channel(10000);
            
            // 创建控制台锁
            $this->consoleLock = new \Swoole\Lock(SWOOLE_MUTEX);
            
            // 启动日志处理协程
            $this->startLogProcessor();
        }
    }

    /**
     * 获取单例实例
     * @param array $config 配置参数
     * @return Log
     */
    public static function getInstance($config = [])
    {
        if (is_null(self::$instance)) {
            self::$instance = new self($config);
        }
        return self::$instance;
    }

    /**
     * 启动日志处理协程
     */
    private function startLogProcessor()
    {
        if (!$this->logQueue instanceof \Swoole\Coroutine\Channel) {
            return;
        }
        
        $this->processorRunning = true;
        
        // 创建协程处理日志
        \Swoole\Coroutine::create(function() {
            while (true) {
                // 从队列中获取日志数据，永不超时
                $logData = $this->logQueue->pop(-1);
                
                // 处理退出信号
                if ($logData === false || $logData === null) {
                    break;
                }
                
                try {
                    // 处理单条日志
                    $this->processLogItem($logData);
                } catch (\Throwable $e) {
                    // 日志处理异常，防止协程退出
                    $this->handleLogException($e);
                }
            }
            
            $this->processorRunning = false;
        });
    }

    /**
     * 处理单条日志
     * @param array $logData 日志数据
     */
    private function processLogItem($logData)
    {
        $level = $logData['level'];
        $message = $logData['message'];
        $context = $logData['context'];
        $loggerLevel = $logData['loggerLevel'];
        
        // 检查日志级别是否符合配置
        $configLevel = $this->levelMap[strtolower($this->config['level'])] ?? self::INFO;
        if ($loggerLevel > $configLevel) {
            return;
        }
        
        // 格式化日志
        $datetime = date('Y-m-d H:i:s');
        $levelName = $this->levelNameMap[$loggerLevel] ?? strtoupper($level);
        $contextStr = !empty($context) ? json_encode($context, JSON_UNESCAPED_UNICODE) : '';
        $formatted = "{$datetime} {$levelName} {$message} {$contextStr}";
        
        // 处理所有启用的通道
        foreach ($this->config['channels'] as $channel) {
            if (empty($channel['enabled'])) {
                continue;
            }
            
            switch ($channel['type']) {
                case self::CHANNEL_FILE:
                    $this->outputToFile($formatted, $level);
                    break;
                    
                case self::CHANNEL_CONSOLE:
                    $this->outputToConsole($formatted, $level);
                    break;
            }
        }
    }

    /**
     * 输出日志到文件
     * @param string $content 格式化后的日志内容
     * @param string $level 日志级别
     */
    private function outputToFile($content, $level)
    {
        // 确保日志目录存在
        if (!is_dir($this->config['path'])) {
            mkdir($this->config['path'], 0755, true);
        }
        
        // 日志文件名：按日期命名
        $logFile = $this->config['path'] . date('Y-m-d') . '.log';
        
        // 写入日志，使用FILE_APPEND和LOCK_EX确保并发安全
        file_put_contents($logFile, $content . PHP_EOL, FILE_APPEND | LOCK_EX);
        
        // 简单的文件轮换逻辑
        if ($this->config['max_files'] > 0) {
            $this->rotateFiles($level);
        }
    }

    /**
     * 输出日志到控制台
     * @param string $content 格式化后的日志内容
     * @param string $level 日志级别
     */
    private function outputToConsole($content, $level)
    {
        // 根据日志级别设置不同颜色
        $colorMap = [
            'debug' => "\033[36m",    // 青色
            'info' => "\033[32m",     // 绿色
            'notice' => "\033[33m",   // 黄色
            'warning' => "\033[33m",  // 黄色
            'error' => "\033[31m",    // 红色
            'critical' => "\033[35m", // 紫色
            'alert' => "\033[35m",    // 紫色
            'emergency' => "\033[35m" // 紫色
        ];
        
        $color = $colorMap[$level] ?? "\033[0m";
        $output = $color . $content . "\033[0m\n";
        
        // 使用锁保证输出原子性
        if ($this->consoleLock instanceof \Swoole\Lock) {
            $this->consoleLock->lock();
            try {
                fwrite(STDOUT, $output);
            } finally {
                $this->consoleLock->unlock();
            }
        } else {
            // 没有锁的情况下直接输出
            echo $output;
        }
    }

    /**
     * 文件轮换处理
     * @param string $level 日志级别
     */
    private function rotateFiles($level)
    {
        $logDir = $this->config['path'];
        $today = date('Y-m-d');
        
        // 获取所有日志文件
        $files = glob($logDir . '*.log');
        if (empty($files)) {
            return;
        }
        
        // 按修改时间排序，最新的在前
        usort($files, function($a, $b) {
            return filemtime($b) - filemtime($a);
        });
        
        // 删除超过最大保留数量的文件
        if (count($files) > $this->config['max_files']) {
            for ($i = $this->config['max_files']; $i < count($files); $i++) {
                if (is_file($files[$i])) {
                    unlink($files[$i]);
                }
            }
        }
    }

    /**
     * 处理日志异常
     * @param \Throwable $e 异常对象
     */
    private function handleLogException(\Throwable $e)
    {
        // 异常信息
        $errorMsg = sprintf(
            "日志处理异常: %s in %s on line %d\nStack trace: %s",
            $e->getMessage(),
            $e->getFile(),
            $e->getLine(),
            $e->getTraceAsString()
        );
        
        // 直接写入文件，避免递归调用
        $emergencyFile = $this->config['path'] . 'emergency.log';
        file_put_contents($emergencyFile, $errorMsg . PHP_EOL, FILE_APPEND | LOCK_EX);
    }

    /**
     * 记录日志
     * @param mixed $message 日志内容
     * @param string $level 日志级别
     * @param array $context 上下文数据
     * @return bool
     */
    public static function record($message, $level = 'info', array $context = [])
    {
        $instance = self::getInstance();
        
        // 检查是否允许写入
        if (!$instance->allowWrite) {
            return false;
        }
        
        // 转换非字符串内容
        if (!is_string($message)) {
            $message = json_encode($message, JSON_UNESCAPED_UNICODE | JSON_UNESCAPED_SLASHES);
        }
        
        // 获取调用栈信息
        $backtrace = debug_backtrace(DEBUG_BACKTRACE_IGNORE_ARGS);
        $traceInfo = '';
        if (isset($backtrace[1])) {
            $traceInfo = basename($backtrace[1]['file']) . ":" . $backtrace[1]['line'];
            if (isset($backtrace[2]['function'])) {
                $traceInfo .= '##' . $backtrace[2]['function'];
            }
        }
        
        // 添加调用栈信息
        $message = sprintf('==> LOG: %s -- %s', $message, $traceInfo);
        
        // 获取日志级别值
        $level = strtolower($level);
        $loggerLevel = $instance->levelMap[$level] ?? self::INFO;
        
        // 封装日志数据
        $logData = [
            'message' => $message,
            'level' => $level,
            'loggerLevel' => $loggerLevel,
            'context' => $context
        ];
        
        // 根据环境选择处理方式
        if ($instance->logQueue instanceof \Swoole\Coroutine\Channel) {
            // 协程环境下，异步处理
            return $instance->logQueue->push($logData, 0.01); // 10ms超时
        } else {
            // 同步处理
            try {
                $instance->processLogItem($logData);
                return true;
            } catch (\Throwable $e) {
                return false;
            }
        }
    }

    /**
     * 日志处理核心方法
     * @param string $level 日志级别
     * @param mixed $message 日志内容
     * @param array $context 上下文数据
     * @return void
     */
    public static function log($level, $message, array $context = [])
    {
        if ($level == 'sql') {
            $level = 'debug';
        }
        self::record($message, $level, $context);
    }

    /**
     * 记录emergency级别日志
     * @param mixed $message 日志内容
     * @param array $context 上下文数据
     * @return void
     */
    public static function emergency($message, array $context = [])
    {
        self::log(__FUNCTION__, $message, $context);
    }

    /**
     * 记录alert级别日志
     * @param mixed $message 日志内容
     * @param array $context 上下文数据
     * @return void
     */
    public static function alert($message, array $context = [])
    {
        self::log(__FUNCTION__, $message, $context);
    }

    /**
     * 记录critical级别日志
     * @param mixed $message 日志内容
     * @param array $context 上下文数据
     * @return void
     */
    public static function critical($message, array $context = [])
    {
        self::log(__FUNCTION__, $message, $context);
    }

    /**
     * 记录error级别日志
     * @param mixed $message 日志内容
     * @param array $context 上下文数据
     * @return void
     */
    public static function error($message, array $context = [])
    {
        self::log(__FUNCTION__, $message, $context);
    }

    /**
     * 记录warning级别日志
     * @param mixed $message 日志内容
     * @param array $context 上下文数据
     * @return void
     */
    public static function warning($message, array $context = [])
    {
        self::log(__FUNCTION__, $message, $context);
    }

    /**
     * 记录notice级别日志
     * @param mixed $message 日志内容
     * @param array $context 上下文数据
     * @return void
     */
    public static function notice($message, array $context = [])
    {
        self::log(__FUNCTION__, $message, $context);
    }

    /**
     * 记录info级别日志
     * @param mixed $message 日志内容
     * @param array $context 上下文数据
     * @return void
     */
    public static function info($message, array $context = [])
    {
        self::log(__FUNCTION__, $message, $context);
    }

    /**
     * 记录debug级别日志
     * @param mixed $message 日志内容
     * @param array $context 上下文数据
     * @return void
     */
    public static function debug($message, array $context = [])
    {
        self::log(__FUNCTION__, $message, $context);
    }

    /**
     * 记录sql级别日志
     * @param mixed $message 日志内容
     * @param array $context 上下文数据
     * @return void
     */
    public static function sql($message, array $context = [])
    {
        self::log(__FUNCTION__, $message, $context);
    }

    /**
     * 获取日志队列状态
     * @return array
     */
    public static function getQueueStatus()
    {
        $instance = self::getInstance();
        
        if ($instance->logQueue instanceof \Swoole\Coroutine\Channel) {
            return [
                'status' => 'initialized',
                'queue_size' => $instance->logQueue->capacity,
                'queue_length' => $instance->logQueue->length(),
                'processor_running' => $instance->processorRunning
            ];
        }
        
        return [
            'status' => 'not_initialized',
            'processor_running' => false
        ];
    }

    /**
     * 析构函数
     */
    public function __destruct()
    {
        // 关闭协程通道
        if ($this->logQueue instanceof \Swoole\Coroutine\Channel) {
            $this->logQueue->close();
        }
        
        // 释放锁资源
        if ($this->consoleLock instanceof \Swoole\Lock) {
            $this->consoleLock->free();
        }
    }
    
    /**
     * 重置日志系统（用于热重启）
     * @return void
     */
    public static function reset()
    {
        self::$instance = null;
    }
}