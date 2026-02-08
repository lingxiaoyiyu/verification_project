<?php

namespace Library\Core\Server;

use Library\Core\Config;
use Library\Core\ServerException;
use Library\Core\Jwt;
use Error;
use Throwable;

class BaseServer {

    private $coId;
    protected $context = '';

    /**
     * 构造函数
     */
    public function __construct() {
        Config::load(\ROOT_PATH . DS . 'config' . DS . 'default.php'); // 加载应用配置
        //初始化根协程ID
        $this->context = \Swoole\Coroutine::getContext(); //初始化上下文
        $this->context[\CONTEXT_REQUEST_TYPE] = '';
        $this->context[\CONTEXT_TOKEN] = '';
        $this->context[\CONTEXT_TOKEN_PAYLOAD] = '';
        $this->context[\CONTEXT_SERVER] = '';
        $this->context[\CONTEXT_REQUEST] = '';
        $this->context[\CONTEXT_RESPONSE] = '';
        $this->context[\CONTEXT_FRAME] = '';
        $this->context[\CONTEXT_REACTIRID] = '';
        $this->context[\CONTEXT_FD] = '';
        $this->context[\CONTEXT_DB_CONN] = null;
        $this->context[\CONTEXT_DB_TRANSCATION] = false;
        $this->context[\CONTEXT_IS_LOGIN] = false;
    }

    /**
     * 获取token payload
     */
    protected function parsePayLoad() {
        try {
            $token = $this->context[\CONTEXT_TOKEN];
            if ($token) {
                return Jwt::getPayLoad($token);
            } else {
                return '';
            }
        } catch (\Exception | Error | Throwable $e) {
            throw new ServerException('token解析失败，错误信息：'.$e->getMessage(), Config::get("result.code.badRequest"));
        }
    }

    /**
     * 析构函数
     */
    public function __destruct() {
    }
}
