<?php

namespace Library\Facades;

use Library\Route\Builder;

require_once __DIR__ . '/../../vendor/autoload.php';

class Route {
    private static bool $initialized = false;

    /**
     * 重置初始化状态
     * 用于热重启时重新加载路由
     */
    public static function reset(): void {
        self::$initialized = false;
        // 重置Builder实例，确保路由重新加载
        $reflection = new \ReflectionClass('Library\Route\Builder');
        $instanceProperty = $reflection->getProperty('instance');
        $instanceProperty->setAccessible(true);
        $instanceProperty->setValue(null, null);
    }

    public static function initialize(bool $forceReload = false): void {
        // 如果不是强制重新加载且已初始化，则直接返回
        if (!$forceReload && self::$initialized) {
            return;
        }

        $dirs = [__DIR__ . '/../../app', __DIR__ . '/../../modules'];
        $routeFiles = [];

        // 递归搜索函数，查找所有路由文件
        $findRouteFiles = function($dir) use (&$findRouteFiles, &$routeFiles) {
            if (is_dir($dir)) {
                // 检查当前目录是否包含Routes目录
                $routesDir = $dir . DIRECTORY_SEPARATOR . 'Routes';
                if (is_dir($routesDir)) {
                    // 添加所有路由文件
                    $routeFiles[] = $routesDir . DIRECTORY_SEPARATOR . 'HTTP.php';
                    $routeFiles[] = $routesDir . DIRECTORY_SEPARATOR . 'TCP.php';
                    $routeFiles[] = $routesDir . DIRECTORY_SEPARATOR . 'UDP.php';
                    $routeFiles[] = $routesDir . DIRECTORY_SEPARATOR . 'Websocket.php';
                }
                
                // 递归遍历子目录
                $subDirs = scandir($dir);
                foreach ($subDirs as $subDir) {
                    if ($subDir != '.' && $subDir != '..' && is_dir($dir . DIRECTORY_SEPARATOR . $subDir)) {
                        $findRouteFiles($dir . DIRECTORY_SEPARATOR . $subDir);
                    }
                }
            }
        };

        // 遍历所有根目录
        foreach($dirs as $rootPath) {
            if (is_dir($rootPath)) {
                $findRouteFiles($rootPath);
            }
        }
        
        // // 注册全局中间件
        // Builder::getInstance()->globalMiddleware('requestId');
        // Log::info("已注册全局中间件：requestId");
        
        foreach($routeFiles as $file) {
            if (file_exists($file)) {
                require $file;
            }    
        }
        
        // 统计类不存在的路由数量
        $routes = Builder::getInstance()->getRoutes();
        $missingCount = 0;
        foreach ($routes as $requestType => $typeRoutes) {
            foreach ($typeRoutes as $routeInfo) {
                if (isset($routeInfo['classExists']) && !$routeInfo['classExists']) {
                    $missingCount++;
                }
            }
        }
        
        if ($missingCount > 0) {
            echo "\n[WARNING] 有 {$missingCount} 个路由的类不存在！\n";
        }
        
        // 调试：输出所有加载的路由
        Builder::getInstance()->dumpRoutes();
        
        self::$initialized = true;
    }

    // GET请求
    public static function get(string $uri, array|string|callable|null $action = null){
        Builder::getInstance()->get($uri, $action);
    }

    // POST请求
    public static function post(string $uri, array|string|callable|null $action = null){
        Builder::getInstance()->post($uri, $action);
    }

    // ANY请求（支持所有HTTP方法）
    public static function any(string $uri, array|string|callable|null $action = null){
        Builder::getInstance()->any($uri, $action);
    }

    // 请求方式数组
    public static function match(array|string $methods, string $uri, array|string|callable|null $action = null){
        Builder::getInstance()->match($methods, $uri, $action);
    }

    // 前缀
    public static function prefix(string $prefix) : Builder{
        return Builder::getInstance()->prefix($prefix);
    }

    // 中间件
    public static function middleware(array|string|null $middleware) : Builder{
        return Builder::getInstance()->middleware($middleware);
    }

    // 路由组
    public static function group(callable $callback){
        Builder::getInstance()->group($callback);
    }

    /**
     * 设置请求类型
     * 优化：显式设置路由类型，替代 debug_backtrace
     *
     * @param string $type 请求类型：http, tcp, udp, websocket
     * @return Builder
     */
    public static function setRequestType(string $type) : Builder{
        return Builder::getInstance()->setRequestType($type);
    }

    // 路由分发
    public static function dispatch($uri, $method, $requestType){
        return Builder::getInstance()->dispatch($uri, $method, $requestType);
    }

    public static function getRoutes(){
        return Builder::getInstance()->getRoutes();
    }
}