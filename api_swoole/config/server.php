<?php
// swoole服务配置，不可热重启加载

// 定义常量
if (!defined('ROOT_PATH')) {
    define('ROOT_PATH', dirname(__DIR__));
}
if (!defined('DS')) {
    define('DS', DIRECTORY_SEPARATOR);
}

// 确保Env类已加载
require_once ROOT_PATH . DS . 'library' . DS . 'Core' . DS . 'Env.php';

// 使用Env类获取环境变量
return [
    'host' => \Library\Core\Env::get('swoole.host', '0.0.0.0'),
    'port' => (int)(\Library\Core\Env::get('swoole.port', '9501')),
    'config' => [
        'worker_num' => (int)(\Library\Core\Env::get('swoole.worker_num', '1')), // 设置启动的 Worker 进程数。【默认值：1】
        'daemonize' => filter_var(\Library\Core\Env::get('swoole.daemonize', 'false'), FILTER_VALIDATE_BOOLEAN), // 守护进程化【默认值：false】
        'reload_async' => filter_var(\Library\Core\Env::get('reload_async', 'true'), FILTER_VALIDATE_BOOLEAN), // 设置异步重启开关。【默认值：true】
        'max_wait_time' => (int)(\Library\Core\Env::get('swoole.max_wait_time', '3')), // 进程重启超时时间
        'stats_file' => dirname(__DIR__) . '/storage/logs/stats.log', // 指定 stats() 内容写入的文件路径
        'log_file' => dirname(__DIR__) . '/storage/logs/swoole.log', // 指定 Swoole 错误日志文件
        // 注释掉SWOOLE_LOG_ROTATION_DAILY常量，因为当前Swoole版本不支持
        // 'log_rotation' => \SWOOLE_LOG_ROTATION_DAILY, // 设置 Server 日志分割
        'log_date_format' => \Library\Core\Env::get('swoole.log_date_format', '%Y-%m-%d %H:%M:%S'), // Server 日志时间格式
        'open_tcp_keepalive' => filter_var(\Library\Core\Env::get('open_tcp_keepalive', 'true'), FILTER_VALIDATE_BOOLEAN), // 在 TCP 中有一个 Keep-Alive 的机制可以检测死连接
        'tcp_keepidle' => (int)(\Library\Core\Env::get('tcp.keepidle', '3')), // 3s没有数据传输就进行检测
        'tcp_keepinterval' => (int)(\Library\Core\Env::get('tcp.keepinterval', '1')), // 1s探测一次
        'tcp_keepcount' => (int)(\Library\Core\Env::get('tcp.keepcount', '5')), // 探测的次数，超过5次后还没回包close此连接

        'heartbeat_check_interval' => (int)(\Library\Core\Env::get('heartbeat.check_interval', '60')),  // 表示每60秒遍历一次
        'heartbeat_idle_time' => (int)(\Library\Core\Env::get('heartbeat.idle_time', '600')), // 表示一个连接如果600秒内未向服务器发送任何数据，此连接将被强制关闭
        'enable_coroutine' => filter_var(\Library\Core\Env::get('enable_coroutine', 'true'), FILTER_VALIDATE_BOOLEAN), // 是否启用异步风格服务器的协程支持
        // 注释掉SWOOLE_HOOK_ALL常量，因为当前Swoole版本不支持
        // 'hook_flags' => \SWOOLE_HOOK_ALL, // 设置一键协程化 Hook 的函数范围。
        'enable_static_handler' => filter_var(\Library\Core\Env::get('enable_static_hanler', 'true'), FILTER_VALIDATE_BOOLEAN), // 开启静态文件请求处理功能
        'document_root' => dirname(__DIR__) . '/storage/', // 配置静态文件根目录，与 enable_static_handler 配合使用。
        'enable_deadlock_check' => filter_var(\Library\Core\Env::get('enable_deadlock_check', 'true'), FILTER_VALIDATE_BOOLEAN), // 打开协程死锁检测。
        'upload_max_filesize' => (int)(\Library\Core\Env::get('upload_max_filesize', '104857600')), // 设置上传文件的最大值（默认100M）
        'package_max_length' => (int)(\Library\Core\Env::get('package_max_length', '104857600')), // 设置最大数据包尺寸（默认100M）
    ],
    'listen' => [
        'dir' => [
            ROOT_PATH . \DS . 'app',
            ROOT_PATH . \DS . 'config',
            ROOT_PATH . \DS . 'library',
        ],
        'ext' => ['php', 'env']
    ]
];
