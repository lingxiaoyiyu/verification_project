<?php
namespace Library\Route;

use Library\Core\Config;
use Library\Core\ServerException;

class Builder
{

    private static $instance;

    // 路由类型。HTTP、TCP、UDP、Websocket
    private $routes = [];
    private $requestType = ''; // 请求类型
    
    // 当前路由组栈，每个元素包含 prefix 和 middleware
    private $groupStack = [];
    
    // 临时存储链式调用的属性
    private $pendingPrefix = '';
    private $pendingMiddleware = [];

    /**
     * 单例
     *
     * @return Builder
     */
    public static function getInstance(): Builder
    {
        if (is_null(self::$instance)) {
            self::$instance = new self();
        }

        return self::$instance;
    }

    /**
     * 设置当前请求类型
     * 优化：显式设置路由类型，替代 debug_backtrace
     *
     * @param string $type 请求类型：http, tcp, udp, websocket
     * @return self
     */
    public function setRequestType(string $type): self
    {
        $this->requestType = $type;
        return $this;
    }

    /**
     * 获取当前请求类型
     *
     * @return string
     */
    public function getRequestType(): string
    {
        return $this->requestType;
    }

    // GET请求
    public function get(string $uri, array | string | callable | null $action = null)
    {
        $this->addRoute($uri, 'get', $action);
    }

    // POST请求
    public function post(string $uri, array | string | callable | null $action = null)
    {
        $this->addRoute($uri, 'post', $action);
    }

    // ANY请求（支持所有HTTP方法）
    public function any(string $uri, array | string | callable | null $action = null)
    {
        $this->addRoute($uri, ['get', 'post', 'put', 'delete', 'patch', 'head', 'options'], $action);
    }

    // 请求方式数组
    public function match(array | string $methods, string $uri, array | string | callable | null $action = null)
    {
        $this->addRoute($uri, $methods, $action);
    }

    // 路由组
    public function group(callable $callback)
    {
        // 构建新的层级，继承父级属性并添加待处理的属性
        $parentPrefix = $this->getCurrentPrefix();
        $parentMiddleware = $this->getCurrentMiddleware();
        
        // 合并待处理的前缀
        if (!empty($this->pendingPrefix)) {
            $newPrefix = $parentPrefix . '/' . $this->pendingPrefix;
            $newPrefix = trim($newPrefix, '/');
        } else {
            $newPrefix = $parentPrefix;
        }
        
        // 合并待处理的中间件
        $newMiddleware = array_merge($parentMiddleware, $this->pendingMiddleware);
        
        // 进入新的路由组层级
        $this->groupStack[] = [
            'prefix' => $newPrefix,
            'middleware' => $newMiddleware,
        ];
        
        // 清空待处理属性
        $this->pendingPrefix = '';
        $this->pendingMiddleware = [];
        
        $callback();
        
        // 退出当前路由组层级
        array_pop($this->groupStack);
    }

    // 前缀
    public function prefix(string $prefix): Builder
    {
        $this->pendingPrefix = $this->formateUri($prefix);
        return $this;
    }

    // 中间件
    public function middleware(array | string | null $middleware): Builder
    {
        $newMiddleware = is_array($middleware) ? $middleware : [$middleware];
        $this->pendingMiddleware = array_merge($this->pendingMiddleware, $newMiddleware);
        return $this;
    }

    /**
     * 获取当前前缀
     */
    private function getCurrentPrefix(): string
    {
        if (empty($this->groupStack)) {
            return '';
        }
        
        $lastGroup = end($this->groupStack);
        return $lastGroup['prefix'] ?? '';
    }

    /**
     * 获取当前中间件
     */
    private function getCurrentMiddleware(): array
    {
        if (empty($this->groupStack)) {
            return [];
        }
        
        $lastGroup = end($this->groupStack);
        return $lastGroup['middleware'] ?? [];
    }

    // 路由分发
    public function dispatch(String $uri, String $method, String $requestType="http") {
        $routeInfo = '';
        if (isset($this->routes[$requestType]) && isset($this->routes[$requestType][$uri])) {
            $routeInfo = $this->routes[$requestType][$uri];
        }
        if (!$routeInfo) { // 路由不存在
            throw new ServerException('404 NOT FOUND', Config::get("result.code.notFound"));
        } else if (!in_array(\strtolower($method), $routeInfo['methods'])) { // 当前请求的方式不允许访问
            throw new ServerException('405 METHOD NOT ALLOWED', Config::get("result.code.methodNotAllowed"));
        }

        // 检查路由类是否存在
        if (isset($routeInfo['classExists']) && !$routeInfo['classExists']) {
            throw new ServerException('404 NOT FOUND - Class Not Found', Config::get("result.code.notFound"));
        }

        // 路由中间件处理
        $middlewares = $routeInfo['middleware'];
        $middlewareConfig = Config::get('routeMiddleware');
        foreach($middlewares as $value) {
            if(isset($middlewareConfig[$value])) {
                (new $middlewareConfig[$value]())->run();
            }
        }
        // 执行action
        $action = $routeInfo['action'];
        try {
            $data = \gettype($action) == 'object' ? $action() : (is_array($action) ? $action[0]::$action[1]() : (new $action)());
        } catch (\Throwable $th) {
            throw $th;
        }
        if ($data) {
            return \gettype($data) == 'array' ? \json_encode($data, \JSON_UNESCAPED_UNICODE | \JSON_UNESCAPED_SLASHES) : $data;
        }
    }

    // 添加路由
    private function addRoute(string $uri, array | string $methods, array | string | callable | null $action = null)
    {
        // 构建完整路径
        $prefix = $this->getCurrentPrefix();
        $formattedUri = $this->formateUri($uri);
        
        if (!empty($prefix)) {
            $path = '/' . $prefix . '/' . $formattedUri;
        } else {
            $path = '/' . $formattedUri;
        }
        
        // 清理重复斜杠
        $path = preg_replace('#/+#', '/', $path);

        // 获取中间件
        $middleware = $this->getCurrentMiddleware();

        if (\is_array($methods)) {
            foreach ($methods as $key => $value) {
                $methods[$key] = \strtolower($value);
            }
        } else {
            $methods = [\strtolower($methods)];
        }

        // 检查action是否是类名，并验证类是否存在
        $classExists = true;
        if (is_string($action) && !class_exists($action)) {
            $classExists = false;
            echo "\n[WARNING] 路由类不存在: {$path} -> {$action}\n";
        }

        $this->routes[$this->requestType][$path] = [
            'middleware' => $middleware,
            'methods' => $methods,
            'action' => $action,
            'classExists' => $classExists,
        ];
    }

    // 格式化uri
    private function formateUri($uri)
    {
        $tmps = \explode('/', $uri);
        $tmpUris = [];
        foreach ($tmps as $key => $value) {
            if ($value) {
                $tmpUris[] = $value;
            }
        }
        return \implode('/', $tmpUris);
    }

    public function getRoutes()
    {
        return $this->routes;
    }

    /**
     * 输出所有加载的路由路径，用于调试
     */
    public function dumpRoutes()
    {
        echo "\n[DEBUG] 已加载的路由路径：\n";
        echo "=======================================\n";

        if (!empty($this->routes)) {
            foreach ($this->routes as $requestType => $routes) {
                echo "请求类型: {$requestType}\n";
                foreach ($routes as $path => $routeInfo) {
                    $methods = implode(', ', $routeInfo['methods']);
                    $action = is_callable($routeInfo['action']) ? 'Closure' : (is_array($routeInfo['action']) ? implode('::', $routeInfo['action']) : $routeInfo['action']);
                    // 显示路由状态
                    $status = isset($routeInfo['classExists']) && !$routeInfo['classExists'] ? '[404 - 类不存在]' : '[OK]';
                    echo "  {$path} [{$methods}] -> {$action} {$status}\n";
                }
            }
        } else {
            echo "  没有加载到任何路由\n";
        }

        echo "=======================================\n\n";
    }
}
