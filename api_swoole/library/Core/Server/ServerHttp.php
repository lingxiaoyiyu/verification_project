<?php

namespace Library\Core\Server;

use Library\Core\ServerException;
use Library\Core\Config;
use Library\Facades\Log;
use Swoole\Http\Request;
use Swoole\Http\Response;
use Swoole\WebSocket\Server;
use Error;
use Throwable;
use Library\Facades\Route;

/**
 * http服务
 */
class ServerHttp extends BaseServer {

    public function run(Server $server, Request $request, Response $response) {
        // 允许跨域
        $response->header('Access-Control-Allow-Origin', '*');
        $response->header('Access-Control-Allow-Credentials', 'true');
        $response->header('Access-Control-Allow-Methods', 'GET,POST,DELETE,PUT,PATCH,OPTIONS');
        $response->header('Access-Control-Allow-Headers', 'Authorization, User-Agent, Keep-Alive, Content-Type, X-Requested-With, ignorecanceltoken');
        
        // 记录请求开始时间
        $startTime = microtime(true);
        
        $data = '';
        $method = $request->server['request_method'];
        $uri = $request->server['request_uri'];
        
        if ($method == 'OPTIONS' || $uri == '/favicon.ico') {
            $response->end();
        } else if (!$uri || $uri == '/') {
            $response->write('success');
            $response->end();
        } else {
            // 提前生成并设置requestId，确保start日志包含requestId
            $this->setRequestId();
            
            // 开始请求日志
            $clientIp = isset($request->header['x-real-ip']) ? $request->header['x-real-ip'] : (isset($request->server['remote_addr']) ? $request->server['remote_addr'] : 'unknown');
            Log::info("[START] {$method} {$uri} from {$clientIp}");
            
            ob_start();
            try {
                $this->init($server, $request, $response);
                $data = Route::dispatch($uri, $method, \CONTEXT_REQUESY_TYPE_HTTP); // 处理路由，中间件会在这里执行，包括RequestIdMiddleware
            } catch (\Throwable $e) { // 捕获所有异常，使用 Throwable 更简洁
                // 记录完整异常信息到日志
                Log::exception($e);
                
                // 获取默认错误码
                $defaultErrorCode = Config::get("result.code.error");
                
                // 统一返回标准 JSON 响应
                $errorCode = $e instanceof ServerException ? $e->getCode() : $defaultErrorCode;
                $errorMessage = $e->getMessage();
                $data = \json_encode([
                    'code' => $errorCode,
                    'message' => $errorMessage,
                    'request_id' => $GLOBALS['requestId'] ?? ''
                ], \JSON_UNESCAPED_SLASHES | \JSON_UNESCAPED_UNICODE);
            }
            
            // 确保响应正确结束，无论是否发生异常
            if ($response->isWritable()) {
                // 处理缓冲区输出
                $buffStr = ob_get_contents();
                if ($buffStr) {
                    ob_end_clean();
                    // 如果$data是数组（文件响应），则不合并缓冲区
                    if (!is_array($data)) {
                        $data = $buffStr . $data;
                    }
                }
                
                // 添加requestId到响应头，此时中间件已经执行，requestId已经被设置
                $response->header('X-Request-Id', $GLOBALS['requestId'] ?? '');
                
                // 处理不同类型的响应
                if (is_array($data) && isset($data['type'])) {
                    // 文件响应
                    if ($data['type'] === 'file') {
                        $filePath = $data['file'];
                        $offset = $data['offset'] ?? 0;
                        $length = $data['length'] ?? 0;
                        $response->sendfile($filePath, $offset, $length);
                    } 
                    // 内容响应
                    else if ($data['type'] === 'content') {
                        $response->write($data['content']);
                        $response->end();
                    }
                } 
                // 普通内容响应
                else {
                    // 确保有响应数据
                    if (empty($data)) {
                        $defaultErrorCode = Config::get("result.code.error");
                        $data = \json_encode([
                            'code' => $defaultErrorCode,
                            'message' => '服务器内部错误',
                            'request_id' => $GLOBALS['requestId'] ?? ''
                        ], \JSON_UNESCAPED_SLASHES | \JSON_UNESCAPED_UNICODE);
                    }
                    $response->write($data);
                    $response->end();
                }
            } else {
                // 连接不可写时，尝试关闭连接
                $response->close();
            }
        }

        if ($method != 'OPTIONS' && $uri != '/favicon.ico') {
            // 计算请求耗时，保留3位小数
            $duration = number_format(microtime(true) - $startTime, 3);
            
            $clientIp = isset($request->header['x-real-ip']) ? $request->header['x-real-ip'] : (isset($request->server['remote_addr']) ? $request->server['remote_addr'] : 'unknown');
            $responseInfo = !empty($data) ? substr($data, 0, 100) : 'no response data';
            
            // 构建请求参数信息
            $requestParams = '';
            if ($request->get) {
                $requestParams .= ' | GET: ' . http_build_query($request->get);
            }
            if ($request->post) {
                $requestParams .= ' | POST: ' . json_encode($request->post, JSON_UNESCAPED_SLASHES|JSON_UNESCAPED_UNICODE);
            }
            
            // 只保留结束请求日志，包含所有必要信息，避免重复
            Log::info("[END] {$method} {$uri} from {$clientIp} | Duration: {$duration}s | Protocol: {$request->server['server_protocol']} | User-Agent: " . (isset($request->header['user-agent']) ? $request->header['user-agent'] : '') . $requestParams . " | Response: {$responseInfo}");
        }
        
        // 清理MDC上下文，避免协程上下文泄漏
        // 放在最后，确保所有日志都已记录
        \Library\Core\Mdc::clear();
    }

    /**
     * http请求初始化
     */
    private function init(Server $server, Request $request, Response $response) {
        $this->context[\CONTEXT_REQUEST_TYPE] = \CONTEXT_REQUESY_TYPE_HTTP; // 请求类型标识
        $this->context[\CONTEXT_SERVER] = $server;
        $this->context[\CONTEXT_REQUEST] = $request;
        $this->context[\CONTEXT_RESPONSE] = $response;
        $this->context[\CONTEXT_FD] = $request->fd;

        $this->context[\CONTEXT_IS_LOGIN] = true; // 初始登陆状态
        $this->context[\CONTEXT_TOKEN] = $this->getToken($request); // token
        $this->context[\CONTEXT_TOKEN_PAYLOAD] = $this->parsePayLoad();
    }

    /**
     * 获取token
     */
    private function getToken(Request $request) {
        $token = '';
        if (isset($request->header['authorization'])) {
            $headerAuth = $request->header['authorization'];
            $headerAuths = \explode(' ', $headerAuth);
            if (\count($headerAuths) == 2 && $headerAuths[0] == 'Bearer') {
                $token = $headerAuths[1];
            }
        }
        return $token;
    }
    
    /**
     * 设置requestId到MDC
     * 生成唯一的requestId并设置到MDC中，确保start日志包含requestId
     */
    private function setRequestId() {
        // 清理旧的MDC上下文，确保每个请求有干净的上下文
        \Library\Core\Mdc::clear();
        
        // 生成唯一的requestId
        $requestId = $this->generateRequestId();
        
        // 将requestId存入MDC
        \Library\Core\Mdc::put('requestId', $requestId);
        
        // 在后续处理中，response会包含这个requestId
        // 这里使用全局变量临时存储，后续会在响应中添加
        $GLOBALS['requestId'] = $requestId;
    }
    
    /**
     * 生成唯一的requestId
     *
     * @return string
     */
    private function generateRequestId() {
        // 结合时间戳、随机数和进程ID生成唯一ID
        return sprintf(
            '%s-%s-%s',
            date('YmdHis'),
            substr(md5(uniqid(mt_rand(), true)), 0, 8),
            getmypid()
        );
    }
}
