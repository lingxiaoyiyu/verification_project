<?php
// 启用错误报告
ini_set('display_errors', 1);
ini_set('error_reporting', E_ALL);
ini_set('html_errors', 0);

// 直接加载 Cups.php 文件
require_once __DIR__ . '/library/Cups.php';

// 使用命名空间
use \Library\Cups;

ini_set('date.timezone','Asia/Shanghai');

try {
    Cups::run();
} catch (\Throwable $e) {
    echo "[ERROR] 服务器启动失败：" . PHP_EOL;
    echo "错误信息：" . $e->getMessage() . PHP_EOL;
    echo "错误文件：" . $e->getFile() . PHP_EOL;
    echo "错误行号：" . $e->getLine() . PHP_EOL;
    echo "错误类型：" . get_class($e) . PHP_EOL;
    echo "错误跟踪：" . PHP_EOL . $e->getTraceAsString() . PHP_EOL;
    exit(1);
}