<?php

namespace Library;

use Library\Core\Server\ServerHttp;
use Library\Core\Server\ServerWebSocket;
use Error;
use Swoole\Coroutine;
use Swoole\Http\Request;
use Swoole\Http\Response;
use Swoole\WebSocket\Frame;
use Swoole\WebSocket\Server;
use Swoole\Process;
use Library\Facades\Log;


// 定义常量
if (!defined('ROOT_PATH')) {
    // 正确计算项目根目录：当前文件是library/Cups.php，根目录应该是api_swoole目录
    define('ROOT_PATH', \dirname(__DIR__));
}
if (!defined('DS')) {
    define('DS', DIRECTORY_SEPARATOR);
}

class Cups
{
    final public static function run()
    {
        // 使用原生方法加载 .env 文件
        $envPath = ROOT_PATH . DS . '.env';
        if (!file_exists($envPath)) {
            throw new \Exception("请先创建 .env 文件");
        }
        require_once ROOT_PATH . DS . 'library' . DS . 'Core' . DS . 'Env.php';
        // 改为使用Env类加载环境变量
        \Library\Core\Env::loadFile($envPath);
        
        // 加载 server.php 配置
        $serverConfig = require_once ROOT_PATH . DS . 'config' . DS . 'server.php';

        $host = $serverConfig['host'];
        $port = $serverConfig['port'];
        $http = new Server($host, $port, \SWOOLE_PROCESS); // 通过读取配置获得ip、端口等  SWOOLE_BASE SWOOLE_PROCESS

        $http->set($serverConfig['config']);

        $http->on("start", function (Server $serv) use ($serverConfig) {
            // 输出美观的启动日志
            $startTime = date('Y-m-d H:i:s');
            $host = $serverConfig['host'];
            $port = $serverConfig['port'];
            
            echo "\n";
            echo "╔" . str_repeat("═", 60) . "╗\n";
            echo "║                   Swoole Server Started                    ║\n";
            echo "╠" . str_repeat("═", 60) . "╣\n";
            echo "║  Time:     " . str_pad($startTime, 46) . "║\n";
            echo "║  Server:   " . str_pad("http://{$host}:{$port}", 46) . "║\n";
            echo "║  Master:   " . str_pad("PID: " . $serv->master_pid, 46) . "║\n";
            echo "║  Manager:  " . str_pad("PID: " . $serv->manager_pid, 46) . "║\n";
            echo "║  Worker:   " . str_pad("Count: " . $serverConfig['config']['worker_num'], 46) . "║\n";
            echo "║  Mode:     " . str_pad("SWOOLE_BASE", 46) . "║\n";
            echo "╚" . str_repeat("═", 60) . "╝\n";
            echo "\n";
            
            // 覆盖写入PID文件，而不是追加
            \file_put_contents(ROOT_PATH . \DS . "bin" . \DS . "master.pid", $serv->master_pid . PHP_EOL);
            \file_put_contents(ROOT_PATH . \DS . "bin" . \DS . "manager.pid", $serv->manager_pid . PHP_EOL);
        });

        $http->on("shutdown", function () {
            //服务关闭，删除进程id
            \unlink(ROOT_PATH . DS . 'bin' . DS . 'master.pid');
            \unlink(ROOT_PATH . DS . 'bin' . DS . 'manager.pid');
        });

        $http->on('workerStart', function (Server $serv, int $worker_id) {
            echo "workerStart触发: workerID={$worker_id}, PID={$serv->worker_pid}\n";
            // 覆盖写入worker.pid文件，而不是追加
            file_put_contents(ROOT_PATH . DS . "bin" . DS . "worker.pid", $serv->worker_pid . PHP_EOL);
            try {
                // 在 workerStart 中重新加载 Composer 的 autoload.php 以支持 reload
                // 使用require而不是require_once，确保每次都重新加载
                require ROOT_PATH . DS . "vendor" . DS . "autoload.php";
                \Library\Facades\Log::debug("已重新加载autoload.php");

                // 加载 Const.php
                require ROOT_PATH . DS . "library" . DS . "Core" . DS . "Const.php";
                \Library\Facades\Log::debug("已加载Const.php");
                                
                $envPath = ROOT_PATH . DS . '.env';
                \Library\Core\Env::loadFile($envPath);
                \Library\Facades\Log::debug("已重新加载.env文件");
                
                // 重置Config配置，避免热重启时配置冲突
                \Library\Core\Config::reset();
                \Library\Facades\Log::debug("已重置Config配置");

                // 加载中间件配置 - 使用 Config 类加载
                \Library\Core\Config::load(ROOT_PATH . DS . 'config' . DS . 'middleware.php');
                \Library\Facades\Log::debug("已加载middleware.php配置");
                
                // 加载默认配置
                \Library\Core\Config::load(ROOT_PATH . DS . 'config' . DS . 'default.php');
                \Library\Facades\Log::debug("已加载default.php配置");

                \Library\Facades\Log::debug("开始初始化路由...");
                // 重置Route初始化状态，确保热重启时重新加载路由
                \Library\Facades\Route::reset();
                \Library\Facades\Route::initialize();
                \Library\Facades\Log::debug("路由初始化完成");

                \Library\Facades\Log::reset();
                \Library\Facades\Log::debug("日志系统已重置");
                
                \Library\Facades\Log::debug("开始初始化数据库连接池...");
                \Library\Facades\DB::initialize();
                \Library\Facades\Log::debug("数据库连接池初始化完成");
                
                \Library\Facades\Log::debug("开始初始化Redis连接池...");
                \Library\Facades\Redis::initialize();
                \Library\Facades\Log::debug("Redis连接池初始化完成");
                
                \Library\Facades\Log::debug("workerStart初始化完成");
            } catch (Exception | Throwable | Error $e) {
                //初始化异常，输出详细错误信息
                $errorMsg = "workerStart初始化失败: " . $e->getMessage() . PHP_EOL;
                $errorMsg .= "错误文件: " . $e->getFile() . " 行号: " . $e->getLine() . PHP_EOL;
                $errorMsg .= "堆栈跟踪: " . $e->getTraceAsString() . PHP_EOL;
                \Library\Facades\Log::error($errorMsg);
                file_put_contents(ROOT_PATH . DS . "bin" . DS . "error.log", $errorMsg, FILE_APPEND);
                $serv->shutdown();
            }
        });

        $http->on('open', function (Server $server, Request $request) {
            (new ServerWebSocket())->open($server, $request)->run();
        });

        $http->on('message', function (Server $server, Frame $frame) {
            (new ServerWebSocket())->message($server, $frame)->run();
        });

        $http->on('close', function (Server $server, $fd, int $reactorId) {
            (new ServerWebSocket())->close($server, $fd, $reactorId)->run();
        });

        $http->on('request', function (Request $request, Response $response) use ($http) {
            Coroutine::create(function () use ($http, $request, $response) {
                (new ServerHttp())->run($http, $request, $response);
            });
        });
        $http->on('beforeReload', function (Server $server) {
            // 清空所有PID文件
            \file_put_contents(ROOT_PATH . \DS . "bin" . \DS . "master.pid", '');
            \file_put_contents(ROOT_PATH . \DS . "bin" . \DS . "manager.pid", '');
            \file_put_contents(ROOT_PATH . \DS . "bin" . \DS . "worker.pid", '');
        });
        $process = new Process(function () use(&$http, $serverConfig) {
            // 排除的文件列表
            $excludedFiles = [
                ROOT_PATH . DS . 'library' . DS . 'Cups.php',
                ROOT_PATH . DS . 'config' . DS . 'default.php',
                ROOT_PATH . DS . 'config' . DS . 'server.php'
            ];
            
            $getMd5file = function ($path) use(&$getMd5file, $serverConfig, $excludedFiles) {
                $md5Map = [];
                
                // 使用正确的路径分隔符
                $globPattern = $path . DS . "*";
                
                if (is_dir($path)) {
                    // 改进glob函数调用，添加GLOB_MARK标志
                    $files = glob($globPattern, GLOB_MARK);
                } elseif (is_file($path)) {
                    $files = [$path];
                }
                
                foreach ($files as $f) {
                    if (basename($f) == "." || basename($f) == "..") {
                        continue;
                    } elseif (is_dir($f)) {
                        // 递归处理子目录
                        $subMd5Map = $getMd5file($f);
                        $md5Map = array_merge($md5Map, $subMd5Map);
                    } elseif (is_file($f)) {
                        // 检查是否为排除的文件
                        if (in_array($f, $excludedFiles) || strpos($f, 'Cups.php') !== false) {
                            continue;
                        }
                        
                        $ext = pathinfo($f, PATHINFO_EXTENSION);
                        if (in_array($ext, $serverConfig['listen']['ext'])) {
                            $md5 = md5_file($f);
                            $md5Map[$f] = $md5;
                        }
                    }
                }
                return $md5Map;
            };
            
            $listen = $serverConfig['listen'];
            
            // 构建初始MD5映射
            $oldMd5Map = [];
            foreach ($listen['dir'] as $dir) {
                $dirMd5Map = $getMd5file($dir);
                $oldMd5Map = array_merge($oldMd5Map, $dirMd5Map);
            }
            
            while (true) {
                sleep(2);					//每2秒检查一次
                
                // 构建当前MD5映射
                $newMd5Map = [];
                foreach ($listen['dir'] as $dir) {
                    $dirMd5Map = $getMd5file($dir);
                    $newMd5Map = array_merge($newMd5Map, $dirMd5Map);
                }
                
                // 找出变化的文件
                $changedFiles = [];
                
                // 检查新增或修改的文件
                foreach ($newMd5Map as $file => $md5) {
                    if (!isset($oldMd5Map[$file]) || $oldMd5Map[$file] != $md5) {
                        $changedFiles[] = $file;
                    }
                }
                
                // 检查删除的文件
                foreach ($oldMd5Map as $file => $md5) {
                    if (!isset($newMd5Map[$file])) {
                        $changedFiles[] = $file . " (已删除)";
                    }
                }
                
                if (!empty($changedFiles)) {
                    // 输出变化的文件到日志
                    $logMsg = date('Y-m-d H:i:s') . "  文件发生改变了, 重启。变化的文件：" . implode(", ", $changedFiles) . PHP_EOL;
                    echo $logMsg;
                    file_put_contents(ROOT_PATH . DS . "bin" . DS . "process.log", $logMsg, FILE_APPEND);
                    
                    try {
                        // 在主进程中调用reload方法进行热重启
                        $http->reload();
                        echo "热重启成功。" . PHP_EOL;
                    } catch (\Exception $e) {
                        // 忽略异常
                        echo "热重启失败：" . $e->getMessage() . PHP_EOL;
                    } catch (\Error $e) {
                        // 忽略异常
                        echo "热重启失败：" . $e->getMessage() . PHP_EOL;
                    } catch (\Throwable $e) {
                        // 忽略异常
                        echo "热重启失败：" . $e->getMessage() . PHP_EOL;
                    }

                    
                    // 更新旧的MD5映射
                    $oldMd5Map = $newMd5Map;
                }
            }
        });

        \file_put_contents(ROOT_PATH . \DS . "bin" . \DS . "listen.pid", $process->start()); // 启动监听
        
        
        $http->start();
    }
}