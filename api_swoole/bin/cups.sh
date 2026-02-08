#!/bin/bash
### BEGIN INIT INFO
# Provides:          family application server
# Required-Start:    $remote_fs $network
# Required-Stop:     $remote_fs $network
# Default-Start:     2 3 4 5
# Default-Stop:      0 1 6
# Short-Description: starts family server
# Description:       starts the Family Application daemon
### END INIT INFO
#php路径，如不知道在哪，可以用whereis php尝试
# 优先使用环境变量中的PHP_BIN，否则使用默认值
export PHP_BIN=${PHP_BIN:-`which swoole-cli 2>/dev/null`}

#检查PHP_BIN是否存在
if [ -z "$PHP_BIN" ]; then
    # 如果swoole-cli不存在，尝试使用php
    PHP_BIN=`which php 2>/dev/null`
    if [ -z "$PHP_BIN" ]; then
        echo "Error: swoole-cli or php not found in PATH"
        exit 1
    fi
fi

#bin目录
export BIN_PATH=${BIN_PATH:-`pwd`}
#入口文件
export SERVER_PATH=${SERVER_PATH:-$BIN_PATH/..}
#脚本执行地址, 可修改为你的php运行脚本#########
export APPLICATION_FILE=${APPLICATION_FILE:-$SERVER_PATH/index.php}

#日志文件路径
export LOG_PATH=${LOG_PATH:-$SERVER_PATH/storage/logs}
export LOG_FILE=${LOG_FILE:-$LOG_PATH/server.log}

#确保日志目录存在
mkdir -p $LOG_PATH

#版本信息
VERSION="1.0.0"

#获取主进程id
getMasterPid()
{
    if [ ! -f "$BIN_PATH/master.pid" ];then
        echo ''
    else
        PID=`cat $BIN_PATH/master.pid`
        echo $PID
    fi
}

#获取管理进程id
getManagerPid()
{
    if [ ! -f "$BIN_PATH/manager.pid" ];then
        echo ''
    else
        MID=`cat $BIN_PATH/manager.pid`
        echo $MID
    fi
}

kill_pid_from_file() {  
    local pid_file="$1"  # 传入的文件名参数  
    if [ ! -f "$pid_file" ]; then  # 检查文件是否存在且为普通文件  
        return 0  # 文件不存在，返回成功  
    fi  
  
    pids=$(cat "$pid_file")  # 读取文件中的PID  
    
    if [ -z "$pids" ]; then  # 检查PID是否为空  
        return 0  # PID为空，返回成功  
    fi  
    # 杀死每个 PID 对应的进程  
    for pid in $pids; do  
        if [ -n "$pid" ] && [ "$pid" -gt 0 ]; then  # 确保PID是有效的数字  
            if ps -p $pid > /dev/null; then  
                # 先尝试优雅停止进程  
                echo "正在优雅停止进程: $pid"  
                kill -15 $pid  
                
                # 等待1秒，检查进程是否已停止  
                sleep 1  
                if ps -p $pid > /dev/null; then  
                    # 优雅停止失败，强制杀死进程  
                    echo "优雅停止失败，强制杀死进程: $pid"  
                    kill -9 $pid  
                else  
                    echo "进程已优雅停止: $pid"  
                fi  
            fi  
        fi  
    done 
    
    return 0  
}

case "$1" in
        #启动服务
        start)
                # 检查是否使用后台模式
                DAEMON_MODE=false
                if [ "$2" = "--daemon" ] || [ "$2" = "-d" ]; then
                    DAEMON_MODE=true
                fi
                
                PID=`getMasterPid`
                if ps aux | awk '{ print $2 }' | grep -e "^${PID}$"
                then
                    echo "server is running"
                else
                    echo "Starting server "
                    if [ "$DAEMON_MODE" = true ]; then
                        # 在后台运行，并将stdout和stderr重定向到日志文件
                        nohup $PHP_BIN $APPLICATION_FILE > $LOG_FILE 2>&1 &
                        echo " done"
                    else
                        # 在前台运行
                        $PHP_BIN $APPLICATION_FILE
                    fi
                fi
        ;;
        #停止服务
        stop)
                echo "Gracefully shutting down server "

                # 调用方法并传入完整路径的文件名参数  
                kill_pid_from_file "$BIN_PATH/manager.pid"
                kill_pid_from_file "$BIN_PATH/master.pid"
                kill_pid_from_file "$BIN_PATH/worker.pid"
                kill_pid_from_file "$BIN_PATH/listen.pid"

                # 等待1秒，确保进程已停止
                sleep 1

                # 删除PID文件
                rm -rf $BIN_PATH/master.pid 2>/dev/null
                rm -rf $BIN_PATH/manager.pid 2>/dev/null
                rm -rf $BIN_PATH/worker.pid 2>/dev/null
                rm -rf $BIN_PATH/listen.pid 2>/dev/null
                rm -rf $BIN_PATH/process.log 2>/dev/null

                echo " done"
        ;;
        #查看状态
        status)
                PID=`getMasterPid`
                if [ -n "$PID" ]; then
                    if ps aux | awk '{ print $2 }' | grep -e "^${PID}$"
                    then
                        echo "server is running"
                        echo ""
                        pstree -pa ${PID}
                    else
                        echo "server is not running"
                    fi
                else
                    echo "server is not running"
                fi
        ;;
        #退出
        force-quit)
                $0 stop
        ;;
        #重启
        restart)
                $0 stop
                $0 start
        ;;
        #reload
        reload)
                MID=`getManagerPid`
                if [ -z "$MID" ]; then
                    echo "server is not running"
                    exit 1
                fi
                echo "Reload server ing..."
                kill -USR1 $MID
                echo " done"
                exit 0
        ;;
        #reload task进程
        reloadtask)
                MID=`getManagerPid`

                if [ -z "$MID" ]; then
                    echo "server is not running"
                    exit 1
                fi
                echo "Reload task ing..."
                kill -USR2 $MID
                echo " done"
        ;;
        #显示版本信息
        version)
                echo "Family Application Server Version: $VERSION"
                exit 0
        ;;
        #提示
        *)
                echo "Usage: $0 {start|stop|force-quit|restart|reload|status|version}"
                exit 1
        ;;
esac