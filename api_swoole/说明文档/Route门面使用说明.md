# Route 门面使用说明

## 一、概述

Route 门面提供了路由定义和管理的简化接口，支持 HTTP、WebSocket、TCP、UDP 等多种协议的路由注册。

## 二、基础路由

### 2.1 HTTP 路由

```php
use Library\Facades\Route;

// GET 请求
Route::get('/users', 'UserController@index');

// POST 请求
Route::post('/users', 'UserController@store');

// PUT 请求
Route::put('/users/{id}', 'UserController@update');

// DELETE 请求
Route::delete('/users/{id}', 'UserController@destroy');

// 匹配多种请求方法
Route::match(['get', 'post'], '/users', 'UserController@handle');

// 匹配所有请求方法
Route::any('/users', 'UserController@handle');
```

### 2.2 路由参数

```php
// 必选参数
Route::get('/users/{id}', 'UserController@show');

// 可选参数
Route::get('/users/{id?}', 'UserController@show');

// 正则约束
Route::get('/users/{id}', 'UserController@show')
    ->where('id', '\d+');  // 仅匹配数字
```

在控制器中获取参数：
```php
public function show($id) {
    // $id 自动注入
}
```

## 三、路由组

### 3.1 基础路由组

```php
Route::group(['prefix' => 'api'], function() {
    Route::get('/users', 'UserController@index');
    Route::post('/users', 'UserController@store');
});
// 生成路由：/api/users
```

### 3.2 中间件路由组

```php
Route::group(['middleware' => ['auth']], function() {
    Route::get('/profile', 'UserController@profile');
    Route::post('/logout', 'AuthController@logout');
});
```

### 3.3 命名空间路由组

```php
Route::group(['namespace' => 'Admin'], function() {
    Route::get('/dashboard', 'DashboardController@index');
    // 对应：App\Controller\Admin\DashboardController
});
```

### 3.4 组合属性

```php
Route::group([
    'prefix' => 'api/v1',
    'middleware' => ['auth', 'throttle'],
    'namespace' => 'Api'
], function() {
    Route::get('/users', 'UserController@index');
    Route::get('/orders', 'OrderController@index');
});
```

## 四、路由类型设置

### 4.1 setRequestType 方法

**方法签名：**
```php
public static function setRequestType(string $type): void
```

**参数说明：**
| 参数 | 类型 | 说明 |
|------|------|------|
| $type | string | 请求类型：http、websocket、tcp、udp |

**功能说明：**
显式设置当前路由的请求类型，用于区分不同协议的路由注册。

### 4.2 使用示例

#### WebSocket 路由
```php
use Library\Facades\Route;

// 设置请求类型为 WebSocket
Route::setRequestType('websocket');

// 注册 WebSocket 路由
Route::get('/ws/chat', 'ChatController@handle');
Route::get('/ws/notify', 'NotifyController@handle');
```

#### TCP 路由
```php
// 设置请求类型为 TCP
Route::setRequestType('tcp');

// 注册 TCP 路由
Route::get('/tcp/data', 'TcpController@handle');
```

#### UDP 路由
```php
// 设置请求类型为 UDP
Route::setRequestType('udp');

// 注册 UDP 路由
Route::get('/udp/packet', 'UdpController@handle');
```

### 4.3 混合路由定义

```php
use Library\Facades\Route;

// HTTP 路由（默认）
Route::get('/api/users', 'UserController@index');
Route::post('/api/users', 'UserController@store');

// WebSocket 路由
Route::setRequestType('websocket');
Route::get('/ws/chat', 'ChatController@handle');

// 切换回 HTTP 路由
Route::setRequestType('http');
Route::get('/api/orders', 'OrderController@index');
```

## 五、路由回调

### 5.1 控制器方式

```php
// 完整类名
Route::get('/users', 'App\Controller\UserController@index');

// 简写（自动补全命名空间）
Route::get('/users', 'UserController@index');

// 使用数组
Route::get('/users', ['UserController', 'index']);
```

### 5.2 闭包方式

```php
use Library\Facades\Request;
use Library\Facades\Response;

Route::get('/hello', function() {
    return Response::success(['message' => 'Hello World']);
});

Route::get('/users/{id}', function($id) {
    return Response::success(['user_id' => $id]);
});
```

## 六、路由中间件

### 6.1 定义中间件

中间件在 `config/middleware.php` 中配置：

```php
return [
    'auth' => \App\Middleware\AuthMiddleware::class,
    'throttle' => \App\Middleware\ThrottleMiddleware::class,
];
```

### 6.2 使用中间件

```php
// 单个中间件
Route::get('/profile', 'UserController@profile')->middleware('auth');

// 多个中间件
Route::get('/admin', 'AdminController@index')->middleware(['auth', 'admin']);

// 路由组中间件
Route::middleware(['auth'])->group(function() {
    Route::get('/dashboard', 'DashboardController@index');
    Route::get('/settings', 'SettingsController@index');
});
```

### 6.3 中间件实现示例

```php
namespace App\Middleware;

use Library\Facades\Request;
use Library\Facades\Response;
use Library\Core\Jwt;

class AuthMiddleware {
    public function handle($request, $next) {
        $token = Request::header('Authorization');
        
        try {
            Jwt::verifyToken($token);
            return $next($request);
        } catch (\Exception $e) {
            return Response::fail('未授权', 401);
        }
    }
}
```

## 七、路由命名

```php
// 命名路由
Route::get('/users', 'UserController@index')->name('users.index');

// 生成 URL（如需要）
// $url = route('users.index');
```

## 八、路由前缀

```php
// 单个路由前缀
Route::prefix('admin')->group(function() {
    Route::get('/dashboard', 'AdminController@dashboard');
    Route::get('/users', 'AdminController@users');
});
// 生成路由：/admin/dashboard, /admin/users
```

## 九、完整示例

```php
<?php
// route/api.php

use Library\Facades\Route;

// 公共路由
Route::group(['prefix' => 'api'], function() {
    
    // 用户认证（无需登录）
    Route::post('/login', 'AuthController@login');
    Route::post('/register', 'AuthController@register');
    
    // 需要登录的路由
    Route::group(['middleware' => ['auth']], function() {
        
        // 用户管理
        Route::get('/users', 'UserController@index');
        Route::get('/users/{id}', 'UserController@show');
        Route::post('/users', 'UserController@store');
        Route::put('/users/{id}', 'UserController@update');
        Route::delete('/users/{id}', 'UserController@destroy');
        
        // 个人中心
        Route::get('/profile', 'UserController@profile');
        Route::put('/profile', 'UserController@updateProfile');
    });
});

// WebSocket 路由
Route::setRequestType('websocket');
Route::group(['middleware' => ['auth']], function() {
    Route::get('/ws/chat', 'ChatController@handle');
    Route::get('/ws/notification', 'NotificationController@handle');
});

// 切换回 HTTP 路由
Route::setRequestType('http');

// 其他 HTTP 路由
Route::get('/health', function() {
    return \Library\Facades\Response::success(['status' => 'ok']);
});
```

## 十、注意事项

1. **路由顺序**：路由按注册顺序匹配，具体路由应放在通用路由之前
2. **类型切换**：使用 `setRequestType()` 后，后续路由都使用该类型，直到再次切换
3. **性能优化**：显式设置请求类型比自动检测性能更好
4. **参数约束**：使用正则约束可以提高路由匹配效率
5. **中间件顺序**：中间件按数组顺序执行
