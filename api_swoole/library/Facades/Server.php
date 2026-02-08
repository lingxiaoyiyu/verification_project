<?php

namespace Library\Facades;

use Swoole\Coroutine;
use Library\Facades\Request;
use Library\Core\Config;

/**
 * Server 门面类
 * 提供便捷的 Swoole Server 操作方法
 */
class Server
{
    /**
     * 获取 Swoole Server 对象
     *
     * @return \Swoole\Server|\Swoole\Http\Server|\Swoole\WebSocket\Server|null
     */
    private static function getSwooleServer()
    {
        return Coroutine::getContext()[\CONTEXT_SERVER] ?? null;
    }

    /**
     * 判断连接是否存在
     *
     * @param int $fd 连接的文件描述符
     * @return bool
     */
    public static function exists(int $fd): bool
    {
        $server = self::getSwooleServer();
        return $server ? $server->exists($fd) : false;
    }

    /**
     * 判断 WebSocket 连接是否已建立
     *
     * @param int $fd 连接的文件描述符
     * @return bool
     */
    public static function isEstablished(int $fd): bool
    {
        $server = self::getSwooleServer();
        return $server ? $server->isEstablished($fd) : false;
    }

    /**
     * 向 WebSocket 客户端推送消息
     *
     * @param int $fd 连接的文件描述符
     * @param string $data 要发送的数据
     * @param int $opcode 数据类型（WEBSOCKET_OPCODE_TEXT 或 WEBSOCKET_OPCODE_BINARY）
     * @param bool $finish 是否完成发送
     * @return bool
     */
    public static function push(int $fd, string $data, int $opcode = 1, bool $finish = true): bool
    {
        $server = self::getSwooleServer();
        if (!$server) {
            return false;
        }
        
        return $server->push($fd, $data, $opcode, $finish);
    }

    /**
     * 向当前 WebSocket 客户端推送消息
     *
     * @param string $data 要发送的数据
     * @param int $opcode 数据类型（WEBSOCKET_OPCODE_TEXT 或 WEBSOCKET_OPCODE_BINARY）
     * @param bool $finish 是否完成发送
     * @return bool
     */
    public static function pushCurrent(string $data, int $opcode = 1, bool $finish = true): bool
    {
        $fd = Request::fd();
        if ($fd <= 0) {
            return false;
        }
        
        return self::push($fd, $data, $opcode, $finish);
    }

    /**
     * 向指定 WebSocket 客户端推送成功消息（统一格式）
     *
     * @param int $fd 连接的文件描述符
     * @param mixed $data 响应数据
     * @param string $message 响应消息
     * @param int|null $code 业务状态码（null 则使用配置）
     * @return bool
     */
    public static function pushSuccess(int $fd, $data = null, string $message = 'success', ?int $code = null): bool
    {
        if ($code === null) {
            $code = Config::get('result.code.success', 0);
        }
        
        $json = json_encode([
            'code' => $code,
            'message' => $message,
            'data' => $data,
        ], JSON_UNESCAPED_UNICODE | JSON_UNESCAPED_SLASHES);
        
        return self::push($fd, $json);
    }

    /**
     * 向当前 WebSocket 客户端推送成功消息（统一格式）
     *
     * @param mixed $data 响应数据
     * @param string $message 响应消息
     * @param int|null $code 业务状态码（null 则使用配置）
     * @return bool
     */
    public static function pushSuccessCurrent($data = null, string $message = 'success', ?int $code = null): bool
    {
        $fd = Request::fd();
        if ($fd <= 0) {
            return false;
        }
        
        return self::pushSuccess($fd, $data, $message, $code);
    }

    /**
     * 向指定 WebSocket 客户端推送失败消息（统一格式）
     *
     * @param int $fd 连接的文件描述符
     * @param string $message 错误消息
     * @param int|null $code 业务状态码（null 则使用配置）
     * @param mixed $data 额外数据
     * @return bool
     */
    public static function pushFail(int $fd, string $message = 'error', ?int $code = null, $data = null): bool
    {
        if ($code === null) {
            $code = Config::get('result.code.error', 500);
        }
        
        $json = json_encode([
            'code' => $code,
            'message' => $message,
            'data' => $data,
        ], JSON_UNESCAPED_UNICODE | JSON_UNESCAPED_SLASHES);
        
        return self::push($fd, $json);
    }

    /**
     * 向当前 WebSocket 客户端推送失败消息（统一格式）
     *
     * @param string $message 错误消息
     * @param int|null $code 业务状态码（null 则使用配置）
     * @param mixed $data 额外数据
     * @return bool
     */
    public static function pushFailCurrent(string $message = 'error', ?int $code = null, $data = null): bool
    {
        $fd = Request::fd();
        if ($fd <= 0) {
            return false;
        }
        
        return self::pushFail($fd, $message, $code, $data);
    }

    /**
     * 向客户端发送数据（TCP/UDP）
     *
     * @param int $fd 连接的文件描述符
     * @param string $data 要发送的数据
     * @param int $serverSocket 服务器套接字（可选）
     * @return bool
     */
    public static function send(int $fd, string $data, int $serverSocket = -1): bool
    {
        $server = self::getSwooleServer();
        if (!$server) {
            return false;
        }
        
        return $server->send($fd, $data, $serverSocket);
    }

    /**
     * 向当前客户端发送数据（TCP/UDP）
     *
     * @param string $data 要发送的数据
     * @param int $serverSocket 服务器套接字（可选）
     * @return bool
     */
    public static function sendCurrent(string $data, int $serverSocket = -1): bool
    {
        $fd = Request::fd();
        if ($fd <= 0) {
            return false;
        }
        
        return self::send($fd, $data, $serverSocket);
    }

    /**
     * 向指定客户端发送成功消息（统一格式）
     *
     * @param int $fd 连接的文件描述符
     * @param mixed $data 响应数据
     * @param string $message 响应消息
     * @param int|null $code 业务状态码（null 则使用配置）
     * @return bool
     */
    public static function sendSuccess(int $fd, $data = null, string $message = 'success', ?int $code = null): bool
    {
        if ($code === null) {
            $code = Config::get('result.code.success', 0);
        }
        
        $json = json_encode([
            'code' => $code,
            'message' => $message,
            'data' => $data,
        ], JSON_UNESCAPED_UNICODE | JSON_UNESCAPED_SLASHES);
        
        return self::send($fd, $json);
    }

    /**
     * 向当前客户端发送成功消息（统一格式）
     *
     * @param mixed $data 响应数据
     * @param string $message 响应消息
     * @param int|null $code 业务状态码（null 则使用配置）
     * @return bool
     */
    public static function sendSuccessCurrent($data = null, string $message = 'success', ?int $code = null): bool
    {
        $fd = Request::fd();
        if ($fd <= 0) {
            return false;
        }
        
        return self::sendSuccess($fd, $data, $message, $code);
    }

    /**
     * 向指定客户端发送失败消息（统一格式）
     *
     * @param int $fd 连接的文件描述符
     * @param string $message 错误消息
     * @param int|null $code 业务状态码（null 则使用配置）
     * @param mixed $data 额外数据
     * @return bool
     */
    public static function sendFail(int $fd, string $message = 'error', ?int $code = null, $data = null): bool
    {
        if ($code === null) {
            $code = Config::get('result.code.error', 500);
        }
        
        $json = json_encode([
            'code' => $code,
            'message' => $message,
            'data' => $data,
        ], JSON_UNESCAPED_UNICODE | JSON_UNESCAPED_SLASHES);
        
        return self::send($fd, $json);
    }

    /**
     * 向当前客户端发送失败消息（统一格式）
     *
     * @param string $message 错误消息
     * @param int|null $code 业务状态码（null 则使用配置）
     * @param mixed $data 额外数据
     * @return bool
     */
    public static function sendFailCurrent(string $message = 'error', ?int $code = null, $data = null): bool
    {
        $fd = Request::fd();
        if ($fd <= 0) {
            return false;
        }
        
        return self::sendFail($fd, $message, $code, $data);
    }

    /**
     * 关闭客户端连接
     *
     * @param int $fd 连接的文件描述符
     * @param bool $reset 是否强制关闭连接
     * @return bool
     */
    public static function close(int $fd, bool $reset = false): bool
    {
        $server = self::getSwooleServer();
        if (!$server) {
            return false;
        }
        
        return $server->close($fd, $reset);
    }

    /**
     * 关闭当前客户端连接
     *
     * @param bool $reset 是否强制关闭连接
     * @return bool
     */
    public static function closeCurrent(bool $reset = false): bool
    {
        $fd = Request::fd();
        if ($fd <= 0) {
            return false;
        }
        
        return self::close($fd, $reset);
    }

    /**
     * 获取客户端连接信息
     *
     * @param int $fd 连接的文件描述符
     * @param int $reactorId Reactor 线程 ID
     * @param bool $ignoreError 是否忽略错误
     * @return array|false
     */
    public static function getClientInfo(int $fd, int $reactorId = -1, bool $ignoreError = false)
    {
        $server = self::getSwooleServer();
        if (!$server) {
            return false;
        }
        
        return $server->getClientInfo($fd, $reactorId, $ignoreError);
    }

    /**
     * 获取所有客户端连接列表
     *
     * @param int $startFd 起始 fd
     * @param int $pageSize 每页数量
     * @return array|false
     */
    public static function getClientList(int $startFd = 0, int $pageSize = 10)
    {
        $server = self::getSwooleServer();
        if (!$server) {
            return false;
        }
        
        return $server->getClientList($startFd, $pageSize);
    }

    /**
     * 获取连接总数
     *
     * @return int
     */
    public static function getConnectionCount(): int
    {
        $server = self::getSwooleServer();
        return $server ? count($server->connections) : 0;
    }

    /**
     * 向所有客户端连接群发消息
     *
     * @param string $data 要发送的数据
     * @param int[] $excludeFds 要排除的 fd 列表
     * @return int 成功发送的数量
     */
    public static function broadcast(string $data, array $excludeFds = []): int
    {
        $server = self::getSwooleServer();
        if (!$server) {
            return 0;
        }
        
        $count = 0;
        foreach ($server->connections as $fd) {
            if (in_array($fd, $excludeFds)) {
                continue;
            }
            
            if (self::isEstablished($fd)) {
                if (self::push($fd, $data)) {
                    $count++;
                }
            }
        }
        
        return $count;
    }

    /**
     * 断开客户端连接（WebSocket）
     *
     * @param int $fd 连接的文件描述符
     * @param int $code 关闭状态码
     * @param string $reason 关闭原因
     * @return bool
     */
    public static function disconnect(int $fd, int $code = 1000, string $reason = ''): bool
    {
        $server = self::getSwooleServer();
        if (!$server || !method_exists($server, 'disconnect')) {
            return false;
        }
        
        return $server->disconnect($fd, $code, $reason);
    }

    /**
     * 断开当前客户端连接（WebSocket）
     *
     * @param int $code 关闭状态码
     * @param string $reason 关闭原因
     * @return bool
     */
    public static function disconnectCurrent(int $code = 1000, string $reason = ''): bool
    {
        $fd = Request::fd();
        if ($fd <= 0) {
            return false;
        }
        
        return self::disconnect($fd, $code, $reason);
    }

    /**
     * 获取 Server 统计信息
     *
     * @return array
     */
    public static function stats(): array
    {
        $server = self::getSwooleServer();
        return $server ? $server->stats() : [];
    }

    /**
     * 向任意 worker 进程或者 task 进程发送消息
     *
     * @param mixed $message 发送的消息数据
     * @param int $dstWorkerId 目标进程 ID
     * @return bool
     */
    public static function sendMessage($message, int $dstWorkerId): bool
    {
        $server = self::getSwooleServer();
        if (!$server) {
            return false;
        }
        
        return $server->sendMessage($message, $dstWorkerId);
    }

    /**
     * 投递异步任务到 task_worker 池中
     *
     * @param mixed $data 任务数据
     * @param int $dstWorkerId 目标 Task 进程 ID
     * @param callable|null $finishCallback 任务完成回调
     * @return int|false 返回任务 ID
     */
    public static function task($data, int $dstWorkerId = -1, ?callable $finishCallback = null)
    {
        $server = self::getSwooleServer();
        if (!$server) {
            return false;
        }
        
        return $server->task($data, $dstWorkerId, $finishCallback);
    }

    /**
     * 获取原始 Swoole Server 对象
     *
     * @return \Swoole\Server|\Swoole\Http\Server|\Swoole\WebSocket\Server|null
     */
    public static function getServer()
    {
        return self::getSwooleServer();
    }
}
