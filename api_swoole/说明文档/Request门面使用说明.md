# Request 门面使用说明

## 简介

Request 门面类提供了便捷的请求数据访问方法，基于 Swoole 协程上下文封装，支持 HTTP 请求的各种数据获取操作。

## 基本用法

```php
use Library\Facades\Request;

// 获取所有请求参数
$allData = Request::all();

// 获取特定参数
$username = Request::all('username', '默认值');
```

## 方法列表

### 一、参数获取

#### all()
获取所有请求参数（GET + POST + JSON 合并）

```php
// 获取所有参数
$allData = Request::all();

// 获取特定参数
$username = Request::all('username');

// 获取特定参数，带默认值
$page = Request::all('page', 1);
```

#### get()
获取 GET 参数

```php
// 获取所有 GET 参数
$getData = Request::get();

// 获取特定 GET 参数
$id = Request::get('id');

// 带默认值
$page = Request::get('page', 1);
```

#### post()
获取 POST 参数

```php
// 获取所有 POST 参数
$postData = Request::post();

// 获取特定 POST 参数
$username = Request::post('username');

// 带默认值
$status = Request::post('status', 0);
```

#### json()
获取 JSON 请求体数据

```php
// 获取所有 JSON 数据
$jsonData = Request::json();

// 获取特定字段
$name = Request::json('name');

// 带默认值
$type = Request::json('type', 'default');
```

**适用场景**：Content-Type 为 application/json 的请求

---

### 二、请求头和 Cookie

#### header()
获取请求头

```php
// 获取所有请求头
$headers = Request::header();

// 获取特定请求头
$contentType = Request::header('content-type');

// 带默认值
$auth = Request::header('authorization', '');
```

**注意**：请求头键名会自动转换为小写

#### cookie()
获取 Cookie

```php
// 获取所有 Cookie
$cookies = Request::cookie();

// 获取特定 Cookie
$sessionId = Request::cookie('session_id');

// 带默认值
$theme = Request::cookie('theme', 'light');
```

#### bearerToken()
获取 Bearer Token（从 Authorization 头）

```php
$token = Request::bearerToken();

// 示例：Authorization: Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9...
```

---

### 三、文件上传

#### file()
获取上传文件

```php
// 获取所有上传文件
$files = Request::file();

// 获取单个文件
$avatar = Request::file('avatar');

// 文件信息包含：
// [
//     'name' => '原始文件名',
//     'type' => 'MIME类型',
//     'tmp_name' => '临时文件路径',
//     'error' => '错误码',
//     'size' => '文件大小（字节）'
// ]
```

**完整示例**：

```php
$file = Request::file('avatar');
if ($file && $file['error'] === UPLOAD_ERR_OK) {
    $uploadPath = '/uploads/' . date('Ymd') . '/' . uniqid() . '_' . $file['name'];
    move_uploaded_file($file['tmp_name'], $uploadPath);
    Response::success(['url' => $uploadPath], '上传成功');
} else {
    Response::fail('上传失败');
}
```

---

### 四、请求信息

#### method()
获取请求方法

```php
$method = Request::method(); // GET, POST, PUT, DELETE, etc.
```

#### isMethod()
判断是否是指定的请求方法

```php
if (Request::isMethod('POST')) {
    // 处理 POST 请求
}
```

#### isGet() / isPost()
快捷判断方法

```php
if (Request::isGet()) {
    // GET 请求
}

if (Request::isPost()) {
    // POST 请求
}
```

#### isAjax()
判断是否是 AJAX 请求

```php
if (Request::isAjax()) {
    // 返回 JSON
    Response::json(['data' => 'result']);
} else {
    // 返回 HTML
    Response::html('<h1>Result</h1>');
}
```

#### uri()
获取请求 URI（包含查询参数）

```php
$uri = Request::uri();
// 示例：/api/user?id=123
```

#### path()
获取请求路径（不包含查询参数）

```php
$path = Request::path();
// 示例：/api/user
```

#### url()
获取完整 URL

```php
$url = Request::url();
// 示例：https://example.com/api/user?id=123
```

#### isSecure()
判断是否是 HTTPS 请求

```php
if (Request::isSecure()) {
    // HTTPS 请求
}
```

---

### 五、客户端信息

#### ip()
获取客户端真实 IP 地址

```php
$clientIp = Request::ip();
```

**支持代理**：自动识别 Nginx 反向代理（x-real-ip、x-forwarded-for）

#### userAgent()
获取 User-Agent

```php
$ua = Request::userAgent();
```

#### host()
获取 Host

```php
$host = Request::host();
```

---

### 六、高级功能

#### has()
判断请求中是否存在指定参数

```php
// 单个参数
if (Request::has('username')) {
    // username 参数存在
}

// 多个参数
if (Request::has(['username', 'password'])) {
    // username 和 password 都存在
}
```

#### filled()
判断请求参数是否存在且不为空

```php
// 单个参数
if (Request::filled('email')) {
    // email 参数存在且不为空
}

// 多个参数
if (Request::filled(['username', 'password'])) {
    // username 和 password 都存在且不为空
}
```

#### only()
获取指定的参数

```php
$data = Request::only(['username', 'email', 'phone']);
// 返回：['username' => '...', 'email' => '...', 'phone' => '...']
```

#### except()
获取除指定参数外的所有参数

```php
$data = Request::except(['password', 'password_confirmation']);
// 返回除 password 和 password_confirmation 外的所有参数
```

#### server()
获取 Server 信息

```php
// 获取所有 Server 信息
$serverInfo = Request::server();

// 获取特定信息
$protocol = Request::server('server_protocol');
$remoteAddr = Request::server('remote_addr');
```

#### rawContent()
获取原始请求体

```php
$rawBody = Request::rawContent();
```

#### fd()
获取文件描述符（连接 ID）

```php
$fd = Request::fd();
```

**用途**：WebSocket 连接标识、日志记录等

#### getData()
获取完整的原始 HTTP 请求报文

```php
$rawRequest = Request::getData();
```

---

### 七、Token 相关

#### getTokenPayload()
获取 Token Payload（从协程上下文）

```php
// 获取完整 Payload
$payload = Request::getTokenPayload();

// 获取特定字段
$userId = Request::getTokenPayload('user_id');
$role = Request::getTokenPayload('role');
```

**注意**：需要在中间件中解析并存储到协程上下文

---

### 八、验证

#### validate()
参数验证（快捷方式）

```php
try {
    Request::validate([
        'username' => ['text' => '用户名', 'rules' => ['required', ['min_length' => 3]]],
        'email' => ['text' => '邮箱', 'rules' => ['required', 'email']],
    ]);
    
    // 验证通过
} catch (\Library\Core\CupException $e) {
    Response::fail($e->getMessage(), $e->getCode());
}
```

详见：[验证器使用说明.md](./验证器使用说明.md)

---

### 九、其他

#### isCompleted()
判断请求是否已完成

```php
if (Request::isCompleted()) {
    // 请求已完成
}
```

---

## 完整示例

### 示例 1：用户登录

```php
use Library\Facades\Request;
use Library\Facades\Response;

public function login() {
    // 验证参数
    try {
        Request::validate([
            'username' => ['text' => '用户名', 'rules' => ['required']],
            'password' => ['text' => '密码', 'rules' => ['required', ['min_length' => 6]]],
        ]);
    } catch (\Library\Core\CupException $e) {
        Response::fail($e->getMessage(), $e->getCode());
        return;
    }
    
    // 获取参数
    $username = Request::post('username');
    $password = Request::post('password');
    
    // 业务逻辑
    // ...
    
    Response::success(['token' => 'xxx'], '登录成功');
}
```

### 示例 2：文件上传

```php
public function upload() {
    // 验证文件
    try {
        Request::validate([
            'avatar' => ['text' => '头像', 'rules' => [
                'file_required',
                'image',
                ['file_size' => 5 * 1024 * 1024],
                ['file_extension' => ['jpg', 'png', 'gif']]
            ]]
        ]);
    } catch (\Library\Core\CupException $e) {
        Response::fail($e->getMessage(), $e->getCode());
        return;
    }
    
    // 获取文件
    $file = Request::file('avatar');
    
    // 处理上传
    $uploadDir = '/uploads/' . date('Ymd');
    if (!is_dir($uploadDir)) {
        mkdir($uploadDir, 0755, true);
    }
    
    $filename = uniqid() . '_' . $file['name'];
    $uploadPath = $uploadDir . '/' . $filename;
    
    if (move_uploaded_file($file['tmp_name'], $uploadPath)) {
        Response::success(['url' => $uploadPath], '上传成功');
    } else {
        Response::fail('上传失败');
    }
}
```

### 示例 3：API 接口

```php
public function getUserList() {
    // 获取分页参数
    $page = Request::get('page', 1);
    $pageSize = Request::get('page_size', 20);
    $keyword = Request::get('keyword', '');
    
    // 获取客户端信息
    $clientIp = Request::ip();
    $userAgent = Request::userAgent();
    
    // 记录日志
    Log::info("用户列表查询 - IP: {$clientIp}, UA: {$userAgent}");
    
    // 查询数据
    $list = DB::table('users')
        ->where('username', 'like', "%{$keyword}%")
        ->limit($pageSize)
        ->offset(($page - 1) * $pageSize)
        ->get();
    
    $total = DB::table('users')
        ->where('username', 'like', "%{$keyword}%")
        ->count();
    
    Response::paginate($list, $total);
}
```

### 示例 4：RESTful API

```php
public function update($id) {
    // 判断请求方法
    if (!Request::isMethod('PUT') && !Request::isMethod('PATCH')) {
        Response::fail('Method Not Allowed', 405);
        return;
    }
    
    // 获取 JSON 数据
    $data = Request::only(['name', 'email', 'phone']);
    
    // 验证数据
    if (!Request::filled(['name', 'email'])) {
        Response::fail('姓名和邮箱不能为空');
        return;
    }
    
    // 更新数据
    DB::table('users')
        ->where('id', $id)
        ->update($data);
    
    Response::success(null, '更新成功');
}
```

### 示例 5：获取 Token 信息

```php
public function getUserInfo() {
    // 获取 Token
    $token = Request::bearerToken();
    if (!$token) {
        Response::unauthorized('未提供访问令牌');
        return;
    }
    
    // 获取 Token Payload（假设中间件已解析）
    $userId = Request::getTokenPayload('user_id');
    if (!$userId) {
        Response::unauthorized('无效的访问令牌');
        return;
    }
    
    // 查询用户信息
    $user = DB::table('users')->where('id', $userId)->first();
    
    Response::success($user);
}
```

---

## 最佳实践

### 1. 参数验证优先

```php
// ✅ 推荐：先验证再获取
Request::validate([
    'email' => ['text' => '邮箱', 'rules' => ['required', 'email']],
]);
$email = Request::post('email');

// ❌ 不推荐：先获取再验证
$email = Request::post('email');
if (!filter_var($email, FILTER_VALIDATE_EMAIL)) {
    // ...
}
```

### 2. 使用默认值

```php
// ✅ 推荐：提供默认值
$page = Request::get('page', 1);
$pageSize = Request::get('page_size', 20);

// ❌ 不推荐：不提供默认值
$page = Request::get('page') ?: 1;
```

### 3. 使用 only/except 过滤参数

```php
// ✅ 推荐：只获取需要的字段
$data = Request::only(['username', 'email', 'phone']);
DB::table('users')->insert($data);

// ❌ 不推荐：获取所有参数
$data = Request::all();
DB::table('users')->insert($data); // 可能插入不需要的字段
```

### 4. 安全获取客户端 IP

```php
// ✅ 推荐：使用 ip() 方法（自动处理代理）
$clientIp = Request::ip();

// ❌ 不推荐：直接获取
$clientIp = Request::server('remote_addr'); // 可能是代理 IP
```

### 5. 判断请求类型

```php
// ✅ 推荐：使用快捷方法
if (Request::isAjax()) {
    Response::json(['data' => 'result']);
}

// ❌ 不推荐：手动判断
if (Request::header('x-requested-with') === 'XMLHttpRequest') {
    Response::json(['data' => 'result']);
}
```

---

## 注意事项

1. **协程安全**：Request 门面基于 Swoole 协程上下文，自动隔离不同请求的数据
2. **大小写敏感**：请求头会自动转为小写，但参数名保持原样
3. **JSON 解析**：只有 Content-Type 包含 application/json 时才会解析 JSON 请求体
4. **文件上传**：需要在前端设置 `enctype="multipart/form-data"`
5. **默认值**：未提供默认值时，不存在的参数返回 `null`

---

## 技术支持

- **文件路径**：`d:\SVN_OTHER\basic\api_swoole\library\Facades\Request.php`
- **依赖**：Swoole 扩展、协程上下文
- **版本**：适配 Swoole 4.x+

---

© 2026 Swoole Framework Request Facade
