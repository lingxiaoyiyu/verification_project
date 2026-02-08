<?php

namespace Library\Facades;

use Swoole\Coroutine;
use Library\Core\Config;

/**
 * Response 门面类
 * 提供便捷的响应输出方法
 */
class Response
{
    /**
     * 获取 Swoole Response 对象
     *
     * @return \Swoole\Http\Response|null
     */
    private static function getSwooleResponse()
    {
        return Coroutine::getContext()[\CONTEXT_RESPONSE] ?? null;
    }

    /**
     * 设置响应头
     *
     * @param string $key 响应头键名
     * @param string $value 响应头值
     * @param bool $replace 是否替换已存在的同名响应头
     * @return void
     */
    public static function header(string $key, string $value, bool $replace = true): void
    {
        $response = self::getSwooleResponse();
        if ($response) {
            $response->header($key, $value, $replace);
        }
    }

    /**
     * 批量设置响应头
     *
     * @param array $headers 响应头数组
     * @return void
     */
    public static function withHeaders(array $headers): void
    {
        foreach ($headers as $key => $value) {
            self::header($key, $value);
        }
    }

    /**
     * 设置 Cookie
     *
     * @param string $name Cookie 名称
     * @param string $value Cookie 值
     * @param int $expire 过期时间（秒）
     * @param string $path 路径
     * @param string $domain 域名
     * @param bool $secure 是否仅 HTTPS
     * @param bool $httponly 是否仅 HTTP
     * @param string $samesite SameSite 属性
     * @return void
     */
    public static function cookie(
        string $name,
        string $value = '',
        int $expire = 0,
        string $path = '/',
        string $domain = '',
        bool $secure = false,
        bool $httponly = true,
        string $samesite = ''
    ): void {
        $response = self::getSwooleResponse();
        if ($response) {
            $response->cookie($name, $value, $expire, $path, $domain, $secure, $httponly, $samesite);
        }
    }

    /**
     * 删除 Cookie
     *
     * @param string $name Cookie 名称
     * @param string $path 路径
     * @param string $domain 域名
     * @return void
     */
    public static function deleteCookie(string $name, string $path = '/', string $domain = ''): void
    {
        self::cookie($name, '', time() - 3600, $path, $domain);
    }

    /**
     * 设置 HTTP 状态码
     *
     * @param int $code HTTP 状态码
     * @param string $reason 状态原因（可选）
     * @return void
     */
    public static function status(int $code, string $reason = ''): void
    {
        $response = self::getSwooleResponse();
        if ($response) {
            if ($reason) {
                $response->status($code, $reason);
            } else {
                $response->status($code);
            }
        }
    }

    /**
     * 输出纯文本响应
     *
     * @param string $content 响应内容
     * @param int $status HTTP 状态码
     * @return string 响应内容
     */
    public static function text(string $content, int $status = 200): string
    {
        self::status($status);
        self::header('Content-Type', 'text/plain; charset=utf-8');
        return $content;
    }

    /**
     * 输出 HTML 响应
     *
     * @param string $content HTML 内容
     * @param int $status HTTP 状态码
     * @return string 响应内容
     */
    public static function html(string $content, int $status = 200): string
    {
        self::status($status);
        self::header('Content-Type', 'text/html; charset=utf-8');
        return $content;
    }

    /**
     * 输出 JSON 响应
     *
     * @param mixed $data 要转换为 JSON 的数据
     * @param int $status HTTP 状态码
     * @param int $options JSON 编码选项
     * @return string JSON 响应内容
     */
    public static function json($data, int $status = 200, int $options = JSON_UNESCAPED_UNICODE | JSON_UNESCAPED_SLASHES): string
    {
        self::status($status);
        self::header('Content-Type', 'application/json; charset=utf-8');
        return json_encode($data, $options);
    }

    /**
     * 输出成功的 JSON 响应（统一格式）
     *
     * @param mixed $data 响应数据
     * @param string $message 响应消息
     * @param int|null $code 业务状态码（null 则使用配置）
     * @return string JSON 响应内容
     */
    public static function success($data = null, string $message = 'success', ?int $code = null): string
    {
        if ($code === null) {
            $code = Config::get('result.code.success', 0);
        }
        
        return self::json([
            'code' => $code,
            'message' => $message,
            'data' => $data,
        ]);
    }

    /**
     * 输出失败的 JSON 响应（统一格式）
     *
     * @param string $message 错误消息
     * @param int|null $code 业务状态码（null 则使用配置）
     * @param mixed $data 额外数据
     * @param int $httpStatus HTTP 状态码
     * @return string JSON 响应内容
     */
    public static function fail(string $message = 'error', ?int $code = null, $data = null, int $httpStatus = 200): string
    {
        if ($code === null) {
            $code = Config::get('result.code.error', 500);
        }
        
        return self::json([
            'code' => $code,
            'message' => $message,
            'data' => $data,
        ], $httpStatus);
    }

    /**
     * 输出 JSONP 响应
     *
     * @param mixed $data 要转换为 JSON 的数据
     * @param string $callback 回调函数名
     * @param int $status HTTP 状态码
     * @return string JSONP 响应内容
     */
    public static function jsonp($data, string $callback = 'callback', int $status = 200): string
    {
        self::status($status);
        self::header('Content-Type', 'application/javascript; charset=utf-8');
        $jsonContent = json_encode($data, JSON_UNESCAPED_UNICODE | JSON_UNESCAPED_SLASHES);
        return "{$callback}({$jsonContent})";
    }

    /**
     * 输出 XML 响应
     *
     * @param string $content XML 内容
     * @param int $status HTTP 状态码
     * @return string XML 响应内容
     */
    public static function xml(string $content, int $status = 200): string
    {
        self::status($status);
        self::header('Content-Type', 'application/xml; charset=utf-8');
        return $content;
    }

    /**
     * 重定向
     *
     * @param string $url 目标 URL
     * @param int $status HTTP 状态码（301 永久重定向，302 临时重定向）
     * @return string 空字符串
     */
    public static function redirect(string $url, int $status = 302): string
    {
        self::status($status);
        self::header('Location', $url);
        return '';
    }

    /**
     * 下载文件
     *
     * @param string $filePath 文件路径
     * @param string|null $filename 下载文件名（为空则使用原文件名）
     * @return array 包含文件路径和类型的数组
     */
    public static function download(string $filePath, ?string $filename = null): array
    {
        if (!file_exists($filePath)) {
            self::status(404);
            return ['type' => 'content', 'content' => 'File not found'];
        }

        if ($filename === null) {
            $filename = basename($filePath);
        }

        self::header('Content-Type', 'application/octet-stream');
        self::header('Content-Disposition', 'attachment; filename="' . $filename . '"');
        self::header('Content-Length', (string)filesize($filePath));
        
        return ['type' => 'file', 'file' => $filePath];
    }

    /**
     * 输出文件内容（在浏览器中显示）
     *
     * @param string $filePath 文件路径
     * @param string|null $mimeType MIME 类型
     * @return array 包含文件路径和类型的数组
     */
    public static function file(string $filePath, ?string $mimeType = null): array
    {
        if (!file_exists($filePath)) {
            self::status(404);
            return ['type' => 'content', 'content' => 'File not found'];
        }

        if ($mimeType === null) {
            $mimeType = mime_content_type($filePath) ?: 'application/octet-stream';
        }

        self::header('Content-Type', $mimeType);
        
        return ['type' => 'file', 'file' => $filePath];
    }

    /**
     * 写入响应内容（不结束响应）
     *
     * @param string $content 响应内容
     * @return void
     */
    public static function write(string $content): void
    {
        $response = self::getSwooleResponse();
        if ($response && $response->isWritable()) {
            $response->write($content);
        }
    }

    /**
     * 结束响应
     *
     * @param string|null $content 可选的响应内容
     * @return void
     */
    public static function end(?string $content = null): void
    {
        $response = self::getSwooleResponse();
        if ($response && $response->isWritable()) {
            if ($content !== null) {
                $response->end($content);
            } else {
                $response->end();
            }
        }
    }

    /**
     * 判断响应是否可写
     *
     * @return bool
     */
    public static function isWritable(): bool
    {
        $response = self::getSwooleResponse();
        return $response ? $response->isWritable() : false;
    }

    /**
     * 分离响应（用于异步响应）
     *
     * @return bool
     */
    public static function detach(): bool
    {
        $response = self::getSwooleResponse();
        return $response ? $response->detach() : false;
    }

    /**
     * 设置响应压缩级别
     *
     * @param int $level 压缩级别（1-9，0 表示不压缩）
     * @param int $type 压缩类型
     * @return void
     */
    public static function compress(int $level = 1, int $type = 1): void
    {
        $response = self::getSwooleResponse();
        if ($response && defined('SWOOLE_HTTP_COMPRESSION_GZIP')) {
            // Swoole 4.4.12+ 支持
            if (method_exists($response, 'compression')) {
                $response->compression($level, $type);
            }
        }
    }

    /**
     * 设置跨域响应头（CORS）
     *
     * @param string $origin 允许的源（* 表示所有源）
     * @param string $methods 允许的方法
     * @param string $headers 允许的请求头
     * @param bool $credentials 是否允许携带凭证
     * @param int $maxAge 预检请求缓存时间（秒）
     * @return void
     */
    public static function cors(
        string $origin = '*',
        string $methods = 'GET,POST,PUT,DELETE,PATCH,OPTIONS',
        string $headers = 'Authorization, User-Agent, Keep-Alive, Content-Type, X-Requested-With, ignorecanceltoken',
        bool $credentials = true,
        int $maxAge = 86400
    ): void
    {
        self::header('Access-Control-Allow-Origin', $origin);
        self::header('Access-Control-Allow-Methods', $methods);
        self::header('Access-Control-Allow-Headers', $headers);
        self::header('Access-Control-Allow-Credentials', $credentials ? 'true' : 'false');
        self::header('Access-Control-Max-Age', (string)$maxAge);
    }

    /**
     * 设置缓存响应头
     *
     * @param int $seconds 缓存秒数（0 表示不缓存）
     * @return void
     */
    public static function cache(int $seconds = 0): void
    {
        if ($seconds <= 0) {
            self::header('Cache-Control', 'no-cache, no-store, must-revalidate');
            self::header('Pragma', 'no-cache');
            self::header('Expires', '0');
        } else {
            self::header('Cache-Control', 'public, max-age=' . $seconds);
            self::header('Expires', gmdate('D, d M Y H:i:s', time() + $seconds) . ' GMT');
        }
    }

    /**
     * 输出分页 JSON 响应
     *
     * @param array $list 数据项
     * @param int $total 总数
     * @param string $message 消息
     * @return string JSON 响应内容
     */
    public static function paginate(
        array $list,
        int $total,
        string $message = 'success'
    ): string
    {
        return self::json([
            'code' => Config::get('result.code.success', 0),
            'message' => $message,
            'data' => [
                'list' => $list,
                'total' => $total
            ],
        ]);
    }

    /**
     * 输出 SSE（Server-Sent Events）事件
     *
     * @param string $data 事件数据
     * @param string|null $event 事件类型
     * @param string|null $id 事件 ID
     * @param int|null $retry 重试时间（毫秒）
     * @return string SSE 事件内容
     */
    public static function sse(
        string $data,
        ?string $event = null,
        ?string $id = null,
        ?int $retry = null
    ): string
    {
        self::header('Content-Type', 'text/event-stream');
        self::header('Cache-Control', 'no-cache');
        self::header('Connection', 'keep-alive');
        
        $output = '';
        
        if ($event !== null) {
            $output .= "event: {$event}\n";
        }
        
        if ($id !== null) {
            $output .= "id: {$id}\n";
        }
        
        if ($retry !== null) {
            $output .= "retry: {$retry}\n";
        }
        
        $output .= "data: {$data}\n\n";
        
        return $output;
    }

    /**
     * 设置内容类型
     *
     * @param string $contentType 内容类型
     * @param string $charset 字符集
     * @return void
     */
    public static function contentType(string $contentType, string $charset = 'utf-8'): void
    {
        self::header('Content-Type', "{$contentType}; charset={$charset}");
    }

    /**
     * 输出空响应（204 No Content）
     *
     * @return string 空字符串
     */
    public static function noContent(): string
    {
        self::status(204);
        return '';
    }

    /**
     * 输出未授权响应（401 Unauthorized）
     *
     * @param string $message 错误消息
     * @return string JSON 响应内容
     */
    public static function unauthorized(string $message = 'Unauthorized'): string
    {
        $code = Config::get('result.code.unauthorized', 401);
        return self::fail($message, $code, null, 401);
    }

    /**
     * 输出禁止访问响应（403 Forbidden）
     *
     * @param string $message 错误消息
     * @return string JSON 响应内容
     */
    public static function forbidden(string $message = 'Forbidden'): string
    {
        $code = Config::get('result.code.forbidden', 403);
        return self::fail($message, $code, null, 403);
    }

    /**
     * 输出未找到响应（404 Not Found）
     *
     * @param string $message 错误消息
     * @return string JSON 响应内容
     */
    public static function notFound(string $message = 'Not Found'): string
    {
        $code = Config::get('result.code.notFound', 404);
        return self::fail($message, $code, null, 404);
    }

    /**
     * 输出服务器错误响应（500 Internal Server Error）
     *
     * @param string $message 错误消息
     * @return string JSON 响应内容
     */
    public static function serverError(string $message = 'Internal Server Error'): string
    {
        $code = Config::get('result.code.error', 500);
        return self::fail($message, $code, null, 500);
    }

    /**
     * 将 Header 信息附加到 HTTP 响应的末尾（仅在 HTTP2 中可用）
     *
     * @param string $key HTTP 头的 Key
     * @param string $value HTTP 头的 value
     * @param bool $ucwords 是否需要对 Key 进行 HTTP 约定格式化
     * @return bool
     */
    public static function setTrailer(string $key, string $value, bool $ucwords = true): bool
    {
        $response = self::getSwooleResponse();
        if (!$response) {
            return false;
        }
        
        $result = $response->trailer($key, $value, $ucwords);
        return $result !== false;
    }

    /**
     * 发送文件到浏览器
     *
     * @param string $filename 要发送的文件名称
     * @param int $offset 上传文件的偏移量
     * @param int $length 发送数据的尺寸
     * @return array 包含文件路径和类型的数组
     */
    public static function sendfile(string $filename, int $offset = 0, int $length = 0): array
    {
        return ['type' => 'file', 'file' => $filename, 'offset' => $offset, 'length' => $length];
    }

}
