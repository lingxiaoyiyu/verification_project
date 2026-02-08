<?php

namespace Library\Facades;

use Swoole\Coroutine;

/**
 * Request 门面类
 * 提供便捷的请求数据访问方法
 */
class Request
{
    /**
     * 获取 Swoole Request 对象
     *
     * @return \Swoole\Http\Request|null
     */
    private static function getSwooleRequest()
    {
        return Coroutine::getContext()[\CONTEXT_REQUEST] ?? null;
    }

    /**
     * 获取所有请求参数（GET + POST + JSON）
     *
     * @param string|null $key 参数键名
     * @param mixed $default 默认值
     * @return mixed
     */
    public static function all(?string $key = null, $default = null)
    {
        $data = array_merge(
            self::get(),
            self::post(),
            self::json()
        );
        
        if ($key === null) {
            return $data;
        }
        
        return $data[$key] ?? $default;
    }

    /**
     * 获取 GET 参数
     *
     * @param string|null $key 参数键名
     * @param mixed $default 默认值
     * @return mixed
     */
    public static function get(?string $key = null, $default = null)
    {
        $request = self::getSwooleRequest();
        if (!$request) {
            return $key === null ? [] : $default;
        }

        $getData = $request->get ?? [];
        
        if ($key === null) {
            return $getData;
        }
        
        return $getData[$key] ?? $default;
    }

    /**
     * 获取 POST 参数
     *
     * @param string|null $key 参数键名
     * @param mixed $default 默认值
     * @return mixed
     */
    public static function post(?string $key = null, $default = null)
    {
        $request = self::getSwooleRequest();
        if (!$request) {
            return $key === null ? [] : $default;
        }

        $postData = $request->post ?? [];
        
        if ($key === null) {
            return $postData;
        }
        
        return $postData[$key] ?? $default;
    }

    /**
     * 获取 JSON 请求体数据
     *
     * @param string|null $key 参数键名
     * @param mixed $default 默认值
     * @return mixed
     */
    public static function json(?string $key = null, $default = null)
    {
        $request = self::getSwooleRequest();
        if (!$request) {
            return $key === null ? [] : $default;
        }

        $rawContent = $request->rawContent();
        $jsonData = [];
        
        if ($rawContent) {
            $decoded = json_decode($rawContent, true);
            if (json_last_error() === JSON_ERROR_NONE) {
                $jsonData = $decoded;
            }
        }
        
        if ($key === null) {
            return $jsonData;
        }
        
        return $jsonData[$key] ?? $default;
    }

    /**
     * 获取请求头
     *
     * @param string|null $key 请求头键名
     * @param mixed $default 默认值
     * @return mixed
     */
    public static function header(?string $key = null, $default = null)
    {
        $request = self::getSwooleRequest();
        if (!$request) {
            return $key === null ? [] : $default;
        }

        $headers = $request->header ?? [];
        
        if ($key === null) {
            return $headers;
        }
        
        $key = strtolower($key);
        return $headers[$key] ?? $default;
    }

    /**
     * 获取 Cookie
     *
     * @param string|null $key Cookie 键名
     * @param mixed $default 默认值
     * @return mixed
     */
    public static function cookie(?string $key = null, $default = null)
    {
        $request = self::getSwooleRequest();
        if (!$request) {
            return $key === null ? [] : $default;
        }

        $cookies = $request->cookie ?? [];
        
        if ($key === null) {
            return $cookies;
        }
        
        return $cookies[$key] ?? $default;
    }

    /**
     * 获取上传文件
     *
     * @param string|null $key 文件字段名
     * @return mixed
     */
    public static function file(?string $key = null)
    {
        $request = self::getSwooleRequest();
        if (!$request) {
            return $key === null ? [] : null;
        }

        $files = $request->files ?? [];
        
        if ($key === null) {
            return $files;
        }
        
        return $files[$key] ?? null;
    }

    /**
     * 获取请求方法
     *
     * @return string
     */
    public static function method(): string
    {
        $request = self::getSwooleRequest();
        if (!$request) {
            return '';
        }
        
        return strtoupper($request->server['request_method'] ?? '');
    }

    /**
     * 判断是否是指定的请求方法
     *
     * @param string $method 请求方法
     * @return bool
     */
    public static function isMethod(string $method): bool
    {
        return strtoupper($method) === self::method();
    }

    /**
     * 判断是否是 GET 请求
     *
     * @return bool
     */
    public static function isGet(): bool
    {
        return self::isMethod('GET');
    }

    /**
     * 判断是否是 POST 请求
     *
     * @return bool
     */
    public static function isPost(): bool
    {
        return self::isMethod('POST');
    }

    /**
     * 判断是否是 AJAX 请求
     *
     * @return bool
     */
    public static function isAjax(): bool
    {
        return self::header('x-requested-with') === 'XMLHttpRequest';
    }

    /**
     * 获取请求 URI
     *
     * @return string
     */
    public static function uri(): string
    {
        $request = self::getSwooleRequest();
        if (!$request) {
            return '';
        }
        
        return $request->server['request_uri'] ?? '';
    }

    /**
     * 获取请求路径（不包含查询参数）
     *
     * @return string
     */
    public static function path(): string
    {
        $uri = self::uri();
        return parse_url($uri, PHP_URL_PATH) ?? '';
    }

    /**
     * 获取完整 URL
     *
     * @return string
     */
    public static function url(): string
    {
        $request = self::getSwooleRequest();
        if (!$request) {
            return '';
        }
        
        $scheme = self::isSecure() ? 'https' : 'http';
        $host = $request->header['host'] ?? '';
        $uri = $request->server['request_uri'] ?? '';
        
        return "{$scheme}://{$host}{$uri}";
    }

    /**
     * 判断是否是 HTTPS 请求
     *
     * @return bool
     */
    public static function isSecure(): bool
    {
        $request = self::getSwooleRequest();
        if (!$request) {
            return false;
        }
        
        // 检查 x-forwarded-proto 头（nginx 代理）
        $forwardedProto = self::header('x-forwarded-proto');
        if ($forwardedProto === 'https') {
            return true;
        }
        
        // 检查服务器协议
        $protocol = $request->server['server_protocol'] ?? '';
        return stripos($protocol, 'https') !== false;
    }

    /**
     * 获取客户端 IP 地址
     *
     * @return string
     */
    public static function ip(): string
    {
        // 优先从 nginx 代理头获取真实 IP
        $ip = self::header('x-real-ip');
        if ($ip) {
            return $ip;
        }
        
        $ip = self::header('x-forwarded-for');
        if ($ip) {
            // x-forwarded-for 可能包含多个 IP，取第一个
            $ips = explode(',', $ip);
            return trim($ips[0]);
        }
        
        $request = self::getSwooleRequest();
        if (!$request) {
            return '';
        }
        
        return $request->server['remote_addr'] ?? '';
    }

    /**
     * 获取 User-Agent
     *
     * @return string
     */
    public static function userAgent(): string
    {
        return self::header('user-agent', '');
    }

    /**
     * 获取 Host
     *
     * @return string
     */
    public static function host(): string
    {
        return self::header('host', '');
    }

    /**
     * 获取 Server 信息
     *
     * @param string|null $key Server 键名
     * @param mixed $default 默认值
     * @return mixed
     */
    public static function server(?string $key = null, $default = null)
    {
        $request = self::getSwooleRequest();
        if (!$request) {
            return $key === null ? [] : $default;
        }

        $serverInfo = $request->server ?? [];
        
        if ($key === null) {
            return $serverInfo;
        }
        
        return $serverInfo[$key] ?? $default;
    }

    /**
     * 获取原始请求体
     *
     * @return string
     */
    public static function rawContent(): string
    {
        $request = self::getSwooleRequest();
        if (!$request) {
            return '';
        }
        
        return $request->rawContent() ?? '';
    }

    /**
     * 获取 Token（从 Authorization 头）
     *
     * @return string
     */
    public static function bearerToken(): string
    {
        $authorization = self::header('authorization', '');
        if (preg_match('/Bearer\s+(\S+)/i', $authorization, $matches)) {
            return $matches[1];
        }
        
        return '';
    }

    /**
     * 判断请求中是否存在指定参数
     *
     * @param string|array $keys 参数键名或键名数组
     * @return bool
     */
    public static function has($keys): bool
    {
        $keys = is_array($keys) ? $keys : [$keys];
        $allData = self::all();
        
        foreach ($keys as $key) {
            if (!array_key_exists($key, $allData)) {
                return false;
            }
        }
        
        return true;
    }

    /**
     * 判断请求参数是否存在且不为空
     *
     * @param string|array $keys 参数键名或键名数组
     * @return bool
     */
    public static function filled($keys): bool
    {
        $keys = is_array($keys) ? $keys : [$keys];
        $allData = self::all();
        
        foreach ($keys as $key) {
            if (!isset($allData[$key]) || $allData[$key] === '') {
                return false;
            }
        }
        
        return true;
    }

    /**
     * 获取指定的参数
     *
     * @param array $keys 参数键名数组
     * @return array
     */
    public static function only(array $keys): array
    {
        $allData = self::all();
        $result = [];
        
        foreach ($keys as $key) {
            if (array_key_exists($key, $allData)) {
                $result[$key] = $allData[$key];
            }
        }
        
        return $result;
    }

    /**
     * 获取除指定参数外的所有参数
     *
     * @param array $keys 要排除的参数键名数组
     * @return array
     */
    public static function except(array $keys): array
    {
        $allData = self::all();
        
        foreach ($keys as $key) {
            unset($allData[$key]);
        }
        
        return $allData;
    }

    /**
     * 获取 FD（文件描述符）
     *
     * @return int
     */
    public static function fd(): int
    {
        return Coroutine::getContext()[\CONTEXT_FD] ?? 0;
    }

    /**
     * 获取完整的原始 HTTP 请求报文（包括 Header 和 Body）
     *
     * @return string
     */
    public static function getData(): string
    {
        $request = self::getSwooleRequest();
        if (!$request || !method_exists($request, 'getData')) {
            return '';
        }
        
        return $request->getData();
    }

    /**
     * 获取 Token Payload（从协程上下文）
     *
     * @param string|null $key 键名
     * @return mixed
     */
    public static function getTokenPayload(?string $key = null)
    {
        $payload = Coroutine::getContext()[\CONTEXT_TOKEN_PAYLOAD] ?? [];
        
        if ($key === null) {
            return $payload;
        }
        
        return $payload[$key] ?? null;
    }

    /**
     * 判断请求是否已完成
     *
     * @return bool
     */
    public static function isCompleted(): bool
    {
        $request = self::getSwooleRequest();
        if (!$request) {
            return false;
        }
        
        return $request->isCompleted ?? false;
    }

    /**
     * 参数验证
     *
     * @param array $rules 校验规则
     * @return void
     * @throws \Library\Core\ServerException
     */
    public static function validate(array $rules): void
    {
        (new \Library\Core\Validate())->validate($rules);
    }

    /**
     * 获取输入参数（兼容Laravel风格）
     *
     * @param string|null $key 参数键名
     * @param mixed $default 默认值
     * @return mixed
     */
    public static function input(?string $key = null, $default = null)
    {
        return self::all($key, $default);
    }
}
