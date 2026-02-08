<?php

// 移除命名空间，因为这是全局常量定义文件

// 定义DS常量，防止未定义错误
if (!defined('DS')) {
    define('DS', DIRECTORY_SEPARATOR);
}

// 定义ROOT_PATH，确保路径正确
define('APP_PATH', ROOT_PATH . DS . 'app');
define('LIBRARY_PATH', ROOT_PATH . DS . 'library');
define('RUNTIME_PATH', ROOT_PATH . DS . 'runtime');

define('CONTEXT_IS_LOGIN', 'loginVerify');

// 请求类型 相关
define('CONTEXT_REQUEST_TYPE', 'requestType');
define('CONTEXT_REQUESY_TYPE_HTTP', 'http');
define('CONTEXT_REQUESY_TYPE_WEBSOCKET', 'websocket');
define('CONTEXT_REQUESY_TYPE_TCP', 'tcp');
define('CONTEXT_REQUESY_TYPE_UDP', 'udp');
define('CONTEXT_REQUESY_TYPE_WEBSOCKET_OPEN', 'websocket_open');
define('CONTEXT_REQUESY_TYPE_WEBSOCKET_MESSAGE', 'websocket_message');
define('CONTEXT_REQUESY_TYPE_WEBSOCKET_CLOSE', 'websocket_close');
define('CONTEXT_REQUESY_TYPE_TCP_CONNECT', 'tcp_connect');
define('CONTEXT_REQUESY_TYPE_TCP_RECEIVE', 'tcp_receive');
define('CONTEXT_REQUESY_TYPE_TCP_CLOSE', 'tcp_close');
define('CONTEXT_REQUESY_TYPE_UDP_PACKET', 'udp_packet');

define('CONTEXT_TOKEN', 'token');
define('CONTEXT_TOKEN_PAYLOAD', 'tokenPayload');
define('CONTEXT_SERVER', 'server');
define('CONTEXT_REQUEST', 'request');
define('CONTEXT_RESPONSE', 'response');
define('CONTEXT_FRAME', 'frame');
define('CONTEXT_REACTIRID', 'reactorId');
define('CONTEXT_FD', 'fd');

// 数据库相关
define('CONTEXT_DB_CONN', 'dbConn');
define('CONTEXT_DB_TRANSCATION', 'transactionFlg');
define('CONTEXT_DB_COMMIT', 'commit');
define('CONTEXT_DB_ROLLBACK', 'rollback');

// Redis相关
define('CONTEXT_REDIS_CONN', 'redisConn');
