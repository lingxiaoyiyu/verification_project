# Response 门面使用说明

## 简介

Response 门面类提供了便捷的响应输出方法，基于 Swoole 协程上下文封装，支持多种响应格式（JSON、HTML、文件、重定向等），统一了 API 响应规范。

## 基本用法

```php
use Library\Facades\Response;

// JSON 响应
Response::json(['message' => 'Hello World']);

// 成功响应（统一格式）
Response::success($data, '操作成功');

// 失败响应（统一格式）
Response::fail('操作失败', 500);
```

## 方法列表

### 一、统一格式响应

#### success()
输出成功的 JSON 响应（推荐使用）

```php
// 基础用法
Response::success($data, '操作成功');

// 只返回数据
Response::success(['user_id' => 123]);

// 自定义状态码
Response::success($data, '创建成功', 201);

// 响应格式：
// {
//     "code": 0,
//     "message": "操作成功",
//     "data": { ... }
// }
```

**参数说明**：
- `$data`：响应数据（可选，默认 null）
- `$message`：响应消息（可选，默认 'success'）
- `$code`：业务状态码（可选，默认从配置读取）

#### fail()
输出失败的 JSON 响应（推荐使用）

```php
// 基础用法
Response::fail('操作失败');

// 自定义错误码
Response::fail('用户不存在', 404);

// 带额外数据
Response::fail('验证失败', 400, ['errors' => ['field' => 'error']]);

// 自定义 HTTP 状态码
Response::fail('服务器错误', 500, null, 500);

// 响应格式：
// {
//     "code": 500,
//     "message": "操作失败",
//     "data": null
// }
```

**参数说明**：
- `$message`：错误消息（必填）
- `$code`：业务状态码（可选，默认从配置读取）
- `$data`：额外数据（可选）
- `$httpStatus`：HTTP 状态码（可选，默认 200）

#### paginate()
输出分页 JSON 响应

```php
Response::paginate($list, $total, '查询成功');

// 响应格式：
// {
//     "code": 0,
//     "message": "查询成功",
//     "data": {
//         "list": [ ... ],
//         "total": 100
//     }
// }
```

---

### 二、基础响应

#### json()
输出 JSON 响应

```php
// 基础用法
Response::json(['key' => 'value']);

// 自定义状态码
Response::json(['error' => 'Not Found'], 404);

// 自定义 JSON 选项
Response::json($data, 200, JSON_PRETTY_PRINT);
```

#### text()
输出纯文本响应

```php
Response::text('Hello World');
Response::text('Not Found', 404);
```

#### html()
输出 HTML 响应

```php
Response::html('<h1>Welcome</h1>');
Response::html($htmlContent, 200);
```

#### xml()
输出 XML 响应

```php
$xml = '<?xml version="1.0" encoding="UTF-8"?><root><item>value</item></root>';
Response::xml($xml);
```

#### jsonp()
输出 JSONP 响应

```php
Response::jsonp($data, 'callback');
Response::jsonp(['result' => 'success'], 'myCallback');
```

---

### 三、HTTP 状态响应

#### noContent()
输出空响应（204 No Content）

```php
Response::noContent();
```

**适用场景**：删除操作成功、无需返回数据

#### unauthorized()
输出未授权响应（401 Unauthorized）

```php
Response::unauthorized('请先登录');
Response::unauthorized(); // 默认消息：'Unauthorized'
```

#### forbidden()
输出禁止访问响应（403 Forbidden）

```php
Response::forbidden('无权限访问');
Response::forbidden(); // 默认消息：'Forbidden'
```

#### notFound()
输出未找到响应（404 Not Found）

```php
Response::notFound('资源不存在');
Response::notFound(); // 默认消息：'Not Found'
```

#### serverError()
输出服务器错误响应（500 Internal Server Error）

```php
Response::serverError('服务器内部错误');
Response::serverError(); // 默认消息：'Internal Server Error'
```

---

### 四、文件响应

#### download()
下载文件

```php
// 使用原文件名
Response::download('/path/to/file.pdf');

// 自定义下载文件名
Response::download('/path/to/file.pdf', 'report.pdf');
```

#### file()
在浏览器中显示文件

```php
// 自动检测 MIME 类型
Response::file('/path/to/image.jpg');

// 指定 MIME 类型
Response::file('/path/to/file.pdf', 'application/pdf');
```

#### sendfile()
发送文件到浏览器（底层方法）

```php
// 发送整个文件
Response::sendfile('/path/to/file.txt');

// 发送文件的一部分
Response::sendfile('/path/to/file.txt', 100, 1024); // 从第100字节开始，发送1024字节
```

---

### 五、重定向

#### redirect()
重定向到指定 URL

```php
// 临时重定向（302）
Response::redirect('https://example.com');

// 永久重定向（301）
Response::redirect('https://example.com', 301);
```

---

### 六、响应头和 Cookie

#### header()
设置响应头

```php
Response::header('X-Custom-Header', 'value');
Response::header('Cache-Control', 'no-cache');
```

#### withHeaders()
批量设置响应头

```php
Response::withHeaders([
    'X-RateLimit-Limit' => '60',
    'X-RateLimit-Remaining' => '59',
    'X-Custom-Header' => 'value'
]);
```

#### cookie()
设置 Cookie

```php
// 基础用法
Response::cookie('user_id', '123');

// 完整参数
Response::cookie(
    'session_id',           // Cookie 名称
    'abc123',               // Cookie 值
    time() + 3600,          // 过期时间（1小时）
    '/',                    // 路径
    'example.com',          // 域名
    true,                   // 仅 HTTPS
    true,                   // 仅 HTTP（不可通过 JS 访问）
    'Strict'                // SameSite 属性
);
```

#### deleteCookie()
删除 Cookie

```php
Response::deleteCookie('session_id');
Response::deleteCookie('user_id', '/', 'example.com');
```

#### status()
设置 HTTP 状态码

```php
Response::status(404);
Response::status(200, 'OK');
```

---

### 七、跨域（CORS）

#### cors()
设置跨域响应头

```php
// 使用默认配置
Response::cors();

// 自定义配置
Response::cors(
    '*',                                    // 允许的源
    'GET,POST,PUT,DELETE',                  // 允许的方法
    'Authorization, Content-Type',           // 允许的请求头
    true,                                   // 允许携带凭证
    86400                                   // 预检请求缓存时间（秒）
);
```

**OPTIONS 请求处理**：

```php
public function handleRequest() {
    Response::cors();
    
    if (Request::isMethod('OPTIONS')) {
        Response::noContent();
        return;
    }
    
    // 处理实际请求
}
```

---

### 八、缓存控制

#### cache()
设置缓存响应头

```php
// 不缓存
Response::cache(0);

// 缓存 1 小时
Response::cache(3600);

// 缓存 1 天
Response::cache(86400);
```

**效果**：

```
// cache(0)
Cache-Control: no-cache, no-store, must-revalidate
Pragma: no-cache
Expires: 0

// cache(3600)
Cache-Control: public, max-age=3600
Expires: Mon, 01 Jan 2026 12:00:00 GMT
```

---

### 九、压缩

#### compress()
设置响应压缩级别

```php
// 使用默认压缩（级别1）
Response::compress();

// 自定义压缩级别（1-9）
Response::compress(6);

// 不压缩
Response::compress(0);
```

**注意**：需要 Swoole 4.4.12+ 版本支持

---

### 十、Server-Sent Events (SSE)

#### sse()
输出 SSE 事件

```php
// 基础用法
Response::sse('{"message": "Hello"}');

// 带事件类型
Response::sse('{"data": "value"}', 'update');

// 完整参数
Response::sse(
    '{"progress": 50}',     // 数据
    'progress',             // 事件类型
    '12345',                // 事件 ID
    3000                    // 重试时间（毫秒）
);
```

**完整示例**：

```php
public function stream() {
    Response::header('Content-Type', 'text/event-stream');
    Response::header('Cache-Control', 'no-cache');
    Response::header('Connection', 'keep-alive');
    
    for ($i = 0; $i <= 100; $i += 10) {
        Response::sse(
            json_encode(['progress' => $i]),
            'progress',
            (string)$i
        );
        \Swoole\Coroutine::sleep(1);
    }
    
    Response::end();
}
```

---

### 十一、底层方法

#### write()
写入响应内容（不结束响应）

```php
Response::write('First part');
Response::write('Second part');
Response::end();
```

#### end()
结束响应

```php
// 结束响应
Response::end();

// 结束响应并发送内容
Response::end('Final content');
```

#### isWritable()
判断响应是否可写

```php
if (Response::isWritable()) {
    Response::write('content');
}
```

#### detach()
分离响应（用于异步响应）

```php
Response::detach();
```

#### contentType()
设置内容类型

```php
Response::contentType('application/json');
Response::contentType('text/html', 'utf-8');
```

#### setTrailer()
设置 HTTP/2 Trailer 头

```php
Response::setTrailer('X-Custom-Trailer', 'value');
```

---

## 完整示例

### 示例 1：用户注册 API

```php
use Library\Facades\Request;
use Library\Facades\Response;

public function register() {
    try {
        // 验证参数
        Request::validate([
            'username' => ['text' => '用户名', 'rules' => ['required', ['min_length' => 3]]],
            'email' => ['text' => '邮箱', 'rules' => ['required', 'email']],
            'password' => ['text' => '密码', 'rules' => ['required', ['min_length' => 6]]],
        ]);
        
        // 获取参数
        $username = Request::post('username');
        $email = Request::post('email');
        $password = Request::post('password');
        
        // 检查用户是否存在
        $exists = DB::table('users')->where('username', $username)->exists();
        if ($exists) {
            Response::fail('用户名已存在', 400);
            return;
        }
        
        // 创建用户
        $userId = DB::table('users')->insertGetId([
            'username' => $username,
            'email' => $email,
            'password' => password_hash($password, PASSWORD_DEFAULT),
            'created_at' => date('Y-m-d H:i:s'),
        ]);
        
        Response::success(['user_id' => $userId], '注册成功');
        
    } catch (\Library\Core\CupException $e) {
        Response::fail($e->getMessage(), $e->getCode());
    } catch (\Exception $e) {
        Log::exception($e);
        Response::serverError('服务器错误');
    }
}
```

### 示例 2：文件下载

```php
public function downloadReport() {
    $reportId = Request::get('id');
    
    // 检查权限
    $userId = Request::getTokenPayload('user_id');
    if (!$userId) {
        Response::unauthorized('请先登录');
        return;
    }
    
    // 查询报告
    $report = DB::table('reports')
        ->where('id', $reportId)
        ->where('user_id', $userId)
        ->first();
    
    if (!$report) {
        Response::notFound('报告不存在');
        return;
    }
    
    $filePath = $report['file_path'];
    if (!file_exists($filePath)) {
        Response::notFound('文件不存在');
        return;
    }
    
    // 下载文件
    Response::download($filePath, $report['filename']);
}
```

### 示例 3：分页列表

```php
public function getUserList() {
    $page = Request::get('page', 1);
    $pageSize = Request::get('page_size', 20);
    $keyword = Request::get('keyword', '');
    
    // 构建查询
    $query = DB::table('users');
    
    if ($keyword) {
        $query->where('username', 'like', "%{$keyword}%");
    }
    
    // 查询总数
    $total = $query->count();
    
    // 查询列表
    $list = $query
        ->limit($pageSize)
        ->offset(($page - 1) * $pageSize)
        ->select(['id', 'username', 'email', 'created_at'])
        ->get();
    
    Response::paginate($list, $total);
}
```

### 示例 4：跨域 API

```php
public function handleCors() {
    // 设置跨域头
    Response::cors(
        'https://example.com',  // 只允许特定域名
        'GET,POST,PUT,DELETE',
        'Authorization, Content-Type',
        true,
        86400
    );
    
    // 处理 OPTIONS 预检请求
    if (Request::isMethod('OPTIONS')) {
        Response::noContent();
        return;
    }
    
    // 处理实际请求
    Response::success(['message' => 'CORS enabled']);
}
```

### 示例 5：流式响应

```php
public function exportLargeData() {
    // 设置不缓存
    Response::cache(0);
    Response::header('Content-Type', 'text/csv');
    Response::header('Content-Disposition', 'attachment; filename="export.csv"');
    
    // 写入表头
    Response::write("ID,Username,Email\n");
    
    // 分批查询并写入
    $page = 1;
    $pageSize = 1000;
    
    while (true) {
        $users = DB::table('users')
            ->limit($pageSize)
            ->offset(($page - 1) * $pageSize)
            ->get();
        
        if (empty($users)) {
            break;
        }
        
        foreach ($users as $user) {
            Response::write("{$user['id']},{$user['username']},{$user['email']}\n");
        }
        
        $page++;
    }
    
    Response::end();
}
```

---

## 最佳实践

### 1. 统一使用 success/fail

```php
// ✅ 推荐：使用统一格式
Response::success($data, '操作成功');
Response::fail('操作失败', 400);

// ❌ 不推荐：自定义格式
Response::json(['status' => 'ok', 'data' => $data]);
```

### 2. 合理使用 HTTP 状态码

```php
// ✅ 推荐：语义明确
Response::unauthorized('请先登录');     // 401
Response::forbidden('无权限访问');      // 403
Response::notFound('资源不存在');       // 404
Response::serverError('服务器错误');    // 500

// ❌ 不推荐：全部用 200
Response::fail('请先登录', 401, null, 200);
```

### 3. 处理文件前检查存在性

```php
// ✅ 推荐：先检查再下载
if (!file_exists($filePath)) {
    Response::notFound('文件不存在');
    return;
}
Response::download($filePath);

// ❌ 不推荐：直接下载（可能报错）
Response::download($filePath);
```

### 4. CORS 处理 OPTIONS

```php
// ✅ 推荐：处理 OPTIONS 预检
Response::cors();
if (Request::isMethod('OPTIONS')) {
    Response::noContent();
    return;
}

// ❌ 不推荐：忽略 OPTIONS
Response::cors();
// 继续处理...
```

### 5. 异常处理

```php
// ✅ 推荐：捕获并返回友好错误
try {
    // 业务逻辑
} catch (\Library\Core\CupException $e) {
    Response::fail($e->getMessage(), $e->getCode());
} catch (\Exception $e) {
    Log::exception($e);
    Response::serverError('服务器错误');
}

// ❌ 不推荐：不处理异常
// 业务逻辑（可能抛出异常）
```

---

## 配置说明

在 `config/default.php` 中配置默认状态码：

```php
return [
    'result' => [
        'code' => [
            'success' => 0,
            'error' => 500,
            'badRequest' => 400,
            'unauthorized' => 401,
            'forbidden' => 403,
            'notFound' => 404,
        ]
    ]
];
```

---

## 注意事项

1. **协程安全**：Response 门面基于 Swoole 协程上下文，自动隔离不同请求
2. **响应后不可修改**：调用 `end()` 后无法再修改响应
3. **大文件下载**：使用 `sendfile()` 方法，不会占用大量内存
4. **SSE 长连接**：需要注意超时设置
5. **CORS 配置**：生产环境建议限制允许的域名，不要使用 `*`

---

## 技术支持

- **文件路径**：`d:\SVN_OTHER\basic\api_swoole\library\Facades\Response.php`
- **依赖**：Swoole 扩展、Config 配置类
- **版本**：适配 Swoole 4.x+

---

© 2026 Swoole Framework Response Facade
