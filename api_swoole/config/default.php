<?php
use Library\Core\Env;

/**
 * 项目配置文件
 * 所有配置项均支持从环境变量读取，优先级：环境变量 > 默认值
 */
return [
    /**
     * 调试模式
     * true: 开启调试模式，显示详细错误信息
     * false: 关闭调试模式，显示友好错误信息
     */
    'debug' => Env::get('APP_DEBUG', false),
    
    /**
     * 国际化配置
     */
    'i18n' => [
        'default' => Env::get('I18N_DEFAULT', 'zh-CN'), // 默认语言
        'supported' => ['zh-CN', 'en-US'], // 支持的语言列表
        'directory' => dirname(__DIR__) . '/resources/lang', // 语言文件目录
        'detect' => [
            'header' => true, // 是否从请求头检测语言
            'query' => true, // 是否从URL参数检测语言
            'param' => 'lang' // URL参数名
        ]
    ],
    
    /**
     * 数据库配置
     */
    'database' => [
        'type' => Env::get('DB_CONNECTION', 'mysql'), // 数据库类型：mysql, sqlite
        'dbhost' => Env::get('DB_HOST', '127.0.0.1'), // 数据库服务器地址
        'dbname' => Env::get('DB_DATABASE', 'default'), // 数据库名称
        'dbuser' => Env::get('DB_USERNAME', 'root'), // 数据库用户名
        'dbpasswd' => Env::get('DB_PASSWORD', ''), // 数据库密码
        'dbport' => Env::get('DB_PORT', '3306'), // 数据库端口
        'charset' => 'utf8mb4', // 数据库编码，支持emoji表情
        'pool_conn_num' => Env::get('DB_POOL_CONN_NUM', swoole_cpu_num() * 2), // 数据库连接池连接数量
        'option' => [
            PDO::ATTR_PERSISTENT => true, // 是否使用持久连接
        ]
    ],
    
    /**
     * Redis配置
     */
    'redis' => [
        'host' => Env::get('REDIS_HOST', '127.0.0.1'), // Redis服务器地址
        'port' => Env::get('REDIS_PORT', '6379'), // Redis端口
        // 'auth' => Env::get('REDIS_AUTH'), // Redis密码（可选）
        'dbIndex' => Env::get('REDIS_DBINDEX', '0'), // Redis数据库索引
        'timeout' => Env::get('REDIS_TIMEOUT', '30'), // Redis连接超时时间（秒）
        'pool_conn_num' => '30' // Redis连接池连接数量
    ],
    
    /**
     * Cookie配置
     */
    'cookie' => [
        'domain' => Env::get('COOKIE_DOMAIN', ''), // Cookie域名
        'expire' => Env::get('COOKIE_EXPIRE', 3600), // Cookie过期时间（秒）
    ],
    
    /**
     * JWT配置
     */
    'jwt_key' => Env::get('JWT_KEY', 'default_jwt_key_for_development_only'), // JWT签名密钥
    'token_expire' => '86400', // Token过期时间（秒），默认24小时
    
    /**
     * 日志配置
     */
    'log' => [
        'level' => 'debug', // 日志级别：debug, info, notice, warning, error, critical, alert, emergency
        'path' =>  dirname(__DIR__) . '/storage/logs/server', // 日志文件保存路径
        
        // 异步日志配置
        'async' => true, // 是否启用异步日志
        'queue_size' => 10000, // 异步日志队列大小
        'process_num' => 1, // 异步日志处理进程数
        
        // 日志输出目标配置，可以同时输出到多个目标
        'channels' => [
            'file' => true, // 是否输出到文件
            'console' => true, // 是否输出到控制台
            'database' => false, // 是否输出到数据库
        ],
        
        // 数据库日志配置（当channels.database为true时生效）
        'database' => [
            'table' => 'operation_logs', // 日志表名
        ],
        
        // 日志轮转配置
        'rotation' => [
            'max_days' => 7, // 保留日志的最大天数，默认7天
            'max_size' => 100 * 1024 * 1024, // 单个日志文件的最大大小，默认100MB
            'compress' => false, // 是否压缩旧日志文件
            'compress_age' => 1, // 日志文件超过多少天开始压缩
        ]
    ],
    
    /**
     * WebSocket路由配置
     */
    'websocketRoute' => [
        'open' => '/open', // WebSocket连接打开事件路由
        'close' => '/close' // WebSocket连接关闭事件路由
    ],
    
    /**
     * WebSocket消息字段配置
     */
    'websocketField' => [
        'uri' => 'event', // WebSocket message消息中路由字段名称
        "data" => 'data', // WebSocket message消息中数据字段名称
        'token' => 'token' // WebSocket message消息中token字段名称
    ],
    
    /**
     * API返回结果码配置
     */
    'result'=> [
        'code' => [
            'success'=>0, // 表示业务处理成功
            'badRequest'=>400, // 表示客户端请求有误，例如参数验证失败
            'unauthorized'=>401, // 表示未授权，用户未登录或权限不足
            'forbidden'=>403, // 表示服务器理解请求，但拒绝处理。未登录，或登录已过期
            'notFound'=>404, // 表示请求的资源不存在
            'methodNotAllowed'=>405, // 请求方式错误
            'error'=>500 // 表示服务器内部错误
        ]
    ],
    
    // 路由中间件配置
    'routeMiddleware' => [
        'requestId' => App\Middlewares\RequestIdMiddleware::class, // 请求ID中间件
    ],
    
    /**
     * 微信配置
     */
    'wx' => [
        /**
         * 微信公众号配置
         */
        'officialAccount' => [
            'app_id'             =>  Env::get('WX_OFFICIALACCOUNT_APP_ID', ''), // 公众号AppID
            'secret'             =>  Env::get('WX_OFFICIALACCOUNT_SECRET', ''), // 公众号AppSecret
            'oauth2_redirect_url'=>  Env::get('WX_OFFICIALACCOUNT_OAUHT2_REDIRECT_URL', ''), // 公众号oauth2授权跳转地址
            'oauth2_login_redirect_url'=>  Env::get('WX_OFFICIALACCOUNT_OAUHT2_LOGIN_REDIRECT_URL', ''), // 公众号oauth2授权登录跳转地址
        ],
        
        /**
         * 微信支付配置
         */
        'pay' => [
            'mch_id'             => Env::get('WX_PAY_MCH_ID', ''), // 商户号
            'mch_secret'         => Env::get('WX_PAY_MCH_SECRET', ''), // 商户密钥
            // 'private_key'        => '/data/private/certs/apiclient_key.pem', // API证书私钥路径
            // 'certificate'        => '/data/private/certs/apiclient_cert.pem', // API证书公钥路径
            // 'notify_url'         => 'http://example.com/payments/wechat-notify', // 默认支付结果通知地址
            
            /**
             * 证书序列号，可通过命令从证书获取：
             * `openssl x509 -in application_cert.pem -noout -serial`
             */
            // 'certificate_serial_no' => '6F2BADBE1738B07EE45C6A85C5F86EE343CAABC3',
            
            // HTTP请求配置
            'http' => [
                'base_uri' => 'https://api.mch.weixin.qq.com/', // 微信支付API基础URL
            ],
            
            // v2 API 秘钥
            'v2_secret_key' => 'VUYROlNk2VM7r37vFTezWB1ZzDZq468c',
            
            // v3 API 秘钥
            //'secret_key' => '43A03299A3C3FED3D8CE7B820Fxxxxx',
            
            // 注意 此处为微信支付平台证书 https://pay.weixin.qq.com/wiki/doc/apiv3/apis/wechatpay5_1.shtml
            //  'platform_certs' => [
            //      '/data/private/certs/platform_key.pem',
            //  ],
        ],
        
        /**
         * 微信小程序配置
         */
        'mp'=>[
            'app_id' => '', // 小程序AppID
            'secret' => '', // 小程序AppSecret
            'aes_key' => '', // 消息加密密钥
            'token' => '', // 消息校验Token
            
            /**
             * 接口请求相关配置，超时时间等，具体可用参数请参考：
             * https://github.com/symfony/symfony/blob/6.0/src/Symfony/Contracts/HttpClient/HttpClientInterface.php#L26
             */
            'http' => [
                'timeout' => 5.0, // 请求超时时间（秒）
                // 如果你在国外想要覆盖默认的 url 的时候才使用，根据不同的模块配置不同的 uri
                'base_uri' => 'https://api.weixin.qq.com/', // 微信API基础URL
            ],
        ]
    ],
];
