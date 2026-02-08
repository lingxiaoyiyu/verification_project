<?php

namespace Library\Core\Server;

use Library\Core\ServerException;
use Swoole\Http\Request;
use Swoole\WebSocket\Server;
use Error;
use Throwable;
use Library\Core\Config;
use Library\Facades\Route;
use Swoole\WebSocket\Frame;

/**
 * websocket服务
 */
class ServerWebSocket extends BaseServer {

    private $server;
    private $request;
    private $frame;

    private $uri;
    private $fd;
    private $reactorId;

    public function open(Server $server, Request $request) {
        $this->server = $server;
        $this->request = $request;
        $this->uri = $request->server['request_uri'];
        if ($this->uri == '' || $this->uri == "/") {
            $this->uri = Config::get('websocketRoute.open');
        }
        if ($this->uri == '' || $this->uri == "/") {
            $this->server->disconnect($this->request->fd);
        } else {
            $this->context[\CONTEXT_REQUEST_TYPE]= \CONTEXT_REQUESY_TYPE_WEBSOCKET_OPEN; // 请求类型标识
            $this->context[\CONTEXT_SERVER]= $server;
            $this->context[\CONTEXT_REQUEST]= $this->request;
            $this->context[\CONTEXT_FD]= $this->request->fd;
            
            $token = isset($request->get['token']) ? $request->get['token'] : "";
            $this->context[\CONTEXT_TOKEN] =  $token; // token
            $this->context[\CONTEXT_TOKEN_PAYLOAD] = $this->parsePayLoad();
        }
        return $this;
    }

    public function message(Server $server, Frame $frame) {
        $this->server = $server;
        $this->frame = $frame;

        $requstData = \json_decode($frame->data, true);
        if(!$requstData) { // 请求数据为空
            $server->push($frame->fd, \json_encode(['code' => 1, 'message' => '请求数据不能为空'], \JSON_UNESCAPED_SLASHES | \JSON_UNESCAPED_UNICODE));
        } else {
            if (isset($requstData[Config::get('websocketField.uri')]) && $requstData[Config::get('websocketField.uri')]) {
                $this->uri = $requstData[Config::get('websocketField.uri')];

                $this->context[\CONTEXT_REQUEST_TYPE] = \CONTEXT_REQUESY_TYPE_WEBSOCKET_MESSAGE; // 请求类型标识
                $this->context[\CONTEXT_SERVER]= $this->server;
                $this->context[\CONTEXT_FRAME] = $this->frame;
                $this->context[\CONTEXT_FD] = $this->frame->fd;

                $this->context[\CONTEXT_TOKEN] = isset($requstData[Config::get('websocketField.token')]) ? $requstData[Config::get('websocketField.token')] : ''; // token
                $this->context[\CONTEXT_TOKEN_PAYLOAD]=  $this->parsePayLoad();
            } else {
                $server->push($frame->fd, \json_encode(['code' => 1, 'message' => '缺少路由字段：'.Config::get('websocketField.uri')], \JSON_UNESCAPED_SLASHES | \JSON_UNESCAPED_UNICODE));
            }
        }

        return $this;
    }

    public function close(Server $server, int $fd, int $reactorId) {
        $this->server = $server;
        $this->fd = $fd;
        $this->reactorId = $reactorId;

        $this->uri = Config::get('websocketRoute.close');

        if ($this->uri !== '' && $this->uri !== "/") {
            $this->context[\CONTEXT_REQUEST_TYPE] = \CONTEXT_REQUESY_TYPE_WEBSOCKET_CLOSE; // 请求类型标识
            $this->context[\CONTEXT_SERVER]= $this->server;
            $this->context[\CONTEXT_FD] = $fd;
            $this->context[\CONTEXT_REACTIRID] = $reactorId;
        }

        return $this;
    }

    public function run() {
        $data = '';
        if ($this->uri && $this->uri !== "/") { // 路径不为空，不是 '/'
            try {
                Route::dispatch($this->uri, 'get', \CONTEXT_REQUESY_TYPE_WEBSOCKET); // 处理路由
            } catch (\Exception | Error | Throwable $e) { // 程序异常
                if ($e instanceof ServerException) {
                    $data = \json_encode(['code' => $e->getCode(), 'message' => $e->getMessage()], \JSON_UNESCAPED_SLASHES | \JSON_UNESCAPED_UNICODE);
                } else {
                    ob_start();
                    \print_r($e);
                    $data = \json_encode(['code' => 1, 'message' => $e->getMessage(), 'data'=>ob_get_contents()], \JSON_UNESCAPED_SLASHES | \JSON_UNESCAPED_UNICODE);
                    ob_end_clean();
                }
                $fd = $this->context[\CONTEXT_FD];
                if ($this->server->exists($fd) && $this->server->isEstablished($fd)) {
                    $this->server->push($fd, $data);
                }
            }
        }
    }
}
