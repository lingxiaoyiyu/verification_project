#!/bin/bash
# Cygwin环境优化的Swoole启动脚本

echo "正在检查Cygwin环境..."

# 检查是否在Cygwin环境下
if [[ "$(uname -o)" == "Cygwin" ]]; then
    echo "检测到Cygwin环境，应用兼容性设置..."
    
    # 设置Cygwin兼容性选项
    export CYGWIN=disable_overlapped_appdata
    
    # 减少进程fork时的内存压力
    export HEAP_CHECK=
    export MALLOC_CHECK_=
fi

# 检查PHP路径
PHP_BIN=${PHP_BIN:-`which swoole-cli 2>/dev/null`}
if [ -z "$PHP_BIN" ]; then
    PHP_BIN=`which php 2>/dev/null`
fi

if [ -z "$PHP_BIN" ]; then
    echo "错误: 未找到swoole-cli或php"
    exit 1
fi

echo "使用的PHP路径: $PHP_BIN"

# 停止现有进程
echo "停止现有进程..."
./cups.sh stop 2>/dev/null

# 清理临时文件
rm -f bin/*.pid 2>/dev/null
rm -f bin/*.log 2>/dev/null

# 等待进程完全停止
sleep 2

# 检查端口是否仍在使用
PORT=9501
if netstat -tuln | grep -q ":$PORT "; then
    echo "端口 $PORT 仍被占用，尝试强制释放..."
    fuser -k $PORT/tcp 2>/dev/null || true
    sleep 1
fi

echo "启动Swoole服务器..."
# 使用前台模式启动，便于查看日志
$PHP_BIN index.php
