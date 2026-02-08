<?php
// 中间件相关配置, 可热重启重新加载

return [
    'routeMiddleware' => [
        'auth:api' => \App\Middlewares\Login::class, // 登录验证
    ]
];
