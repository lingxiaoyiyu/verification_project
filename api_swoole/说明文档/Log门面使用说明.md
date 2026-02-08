# Log 门面使用说明

## 简介

Log 门面类提供了强大的日志记录功能，支持多种日志级别、异步日志处理、多目标输出和 MDC（Mapped Diagnostic Context）上下文管理。自动为每个请求生成唯一的 requestId，便于请求追踪和日志关联。

### 主要特性

- **多种日志级别**：支持 debug、info、notice、warning、error、critical、alert、emergency 八个级别
- **异步日志处理**：使用 Swoole 协程 Channel 实现日志队列，不阻塞主请求
- **多目标输出**：可同时输出到文件、控制台和数据库
- **MDC 上下文管理**：支持协程安全的上下文信息存储和传递
- **RequestId 追踪**：自动为每个请求生成唯一标识符，便于请求链路追踪
- **灵活的配置**：支持日志级别、输出目标、队列大小等配置
- **自动日期分类**：日志文件按日期自动分类存储
- **异常详细记录**：完整记录异常信息和堆栈跟踪
- **性能优化**：异步处理，队列满时自动降级，确保日志不丢失

## 基本用法

```php
use Library\Facades\Log;
use Library\Core\Mdc;

// 记录不同级别的日志
Log::debug('调试信息，仅开发环境可见');
Log::info('用户登录成功');
Log::notice('用户密码即将过期');
Log::warning('接口调用频率过高');
Log::error('数据库连接失败');
Log::critical('服务器内存不足');
Log::alert('系统遭受攻击');
Log::emergency('系统崩溃');

// 记录异常日志
try {
    // 业务逻辑
} catch (\Exception $e) {
    Log::exception($e);
}

// 使用 MDC 上下文
Mdc::put('user_id', 123);
Mdc::put('action', 'login');
Log::info('用户操作日志'); // 日志中会包含 user_id 和 action 信息
```

## 方法列表

### debug()
记录调试信息，通常仅在开发环境使用。

```php
// 记录调试日志
Log::debug('SQL查询: SELECT * FROM users WHERE id = ?', [$userId]);
Log::debug('函数执行时间: ' . $executionTime . 'ms');
```

**日志格式**：
```
2026-01-01 12:00:00 debug [requestId:xxx] [user_id:123] 调试信息
```

### info()
记录普通信息日志，用于记录正常的业务流程。

```php
// 记录字符串
Log::info('用户登录成功');

// 自动处理数组（无需手动json_encode）
Log::info(['action' => 'login', 'user_id' => 123, 'ip' => '192.168.1.100']);

// 记录变量
$data = ['key' => 'value'];
Log::info($data);
```

**日志格式**：
```
2026-01-01 12:00:00 info [requestId:xxx] [user_id:123] 用户登录成功
```

### notice()
记录通知级别的日志，表示需要注意的信息，但不影响系统正常运行。

```php
// 记录通知日志
Log::notice('用户密码即将过期 - 用户ID: ' . $userId);
Log::notice('系统配置已更新 - 配置项: ' . $configKey);
```

**日志格式**：
```
2026-01-01 12:00:00 notice [requestId:xxx] 用户密码即将过期 - 用户ID: 123
```

### warning()
记录警告级别的日志，表示可能存在的问题，但系统仍能正常运行。

```php
// 记录警告日志
Log::warning('接口调用频率过高 - IP: ' . $ip);
Log::warning('缓存命中率过低: ' . $hitRate . '%');
```

**日志格式**：
```
2026-01-01 12:00:00 warning [requestId:xxx] 接口调用频率过高 - IP: 192.168.1.100
```

### error()
记录错误级别的日志，表示系统发生了错误，需要关注。

```php
// 记录错误日志
Log::error('数据库连接失败 - 错误: ' . $errorMsg);
Log::error('API调用失败 - URL: ' . $apiUrl);
```

**日志格式**：
```
2026-01-01 12:00:00 error [requestId:xxx] 数据库连接失败 - 错误: Connection refused
```

### critical()
记录严重级别的日志，表示系统发生了严重错误，可能影响部分功能。

```php
// 记录严重错误日志
Log::critical('服务器内存不足 - 使用率: ' . $memoryUsage . '%');
Log::critical('Redis连接丢失 - 影响缓存功能');
```

**日志格式**：
```
2026-01-01 12:00:00 critical [requestId:xxx] 服务器内存不足 - 使用率: 95%
```

### alert()
记录警报级别的日志，表示系统发生了紧急情况，需要立即处理。

```php
// 记录警报日志
Log::alert('系统遭受攻击 - IP: ' . $attackIp);
Log::alert('数据库主从同步失败');
```

**日志格式**：
```
2026-01-01 12:00:00 alert [requestId:xxx] 系统遭受攻击 - IP: 10.0.0.1
```

### emergency()
记录紧急级别的日志，表示系统已经崩溃，无法正常运行。

```php
// 记录紧急日志
Log::emergency('系统崩溃 - 原因: ' . $crashReason);
Log::emergency('磁盘空间耗尽 - 影响所有功能');
```

**日志格式**：
```
2026-01-01 12:00:00 emergency [requestId:xxx] 系统崩溃 - 原因: Out of memory
```

### exception()
记录异常日志，自动包含异常消息、错误码和完整的堆栈跟踪。

```php
try {
    // 可能抛出异常的代码
    $result = someFunction();
} catch (\Exception $e) {
    // 记录异常
    Log::exception($e);
    
    // 返回错误响应
    Response::serverError('操作失败');
}
```

**日志格式**：
```
2026-01-01 12:00:00 error [requestId:xxx] 错误消息_错误码
异常堆栈信息...
```

**日志内容包括**：
- 错误消息（`$e->getMessage()`）
- 错误码（`$e->getCode()`，如果有）
- 完整的异常堆栈信息（`$e->getTraceAsString()`）
- MDC 上下文信息
- RequestId

---

## 完整示例

### 示例 1：用户操作日志

```php
use Library\Facades\Log;
use Library\Facades\Request;
use Library\Facades\Response;

public function login() {
    $username = Request::post('username');
    $password = Request::post('password');
    $ip = Request::ip();
    
    try {
        // 验证用户
        $user = DB::table('users')
            ->where('username', $username)
            ->first();
        
        if (!$user || !password_verify($password, $user['password'])) {
            // 记录失败日志
            Log::info("登录失败 - 用户名: {$username}, IP: {$ip}");
            Response::fail('用户名或密码错误', 401);
            return;
        }
        
        // 记录成功日志
        Log::info("登录成功 - 用户ID: {$user['id']}, 用户名: {$username}, IP: {$ip}");
        
        // 生成 Token
        $token = bin2hex(random_bytes(32));
        Redis::setex("session:{$token}", 3600, json_encode([
            'user_id' => $user['id'],
            'username' => $user['username']
        ]));
        
        Response::success(['token' => $token], '登录成功');
        
    } catch (\Exception $e) {
        // 记录异常
        Log::exception($e);
        Response::serverError('登录失败');
    }
}
```

### 示例 2：API 请求日志

```php
public function handleRequest() {
    $uri = Request::uri();
    $method = Request::method();
    $params = Request::all();
    $ip = Request::ip();
    $startTime = microtime(true);
    
    try {
        // 记录请求开始
        Log::info("API请求开始 - {$method} {$uri} - IP: {$ip} - 参数: " . json_encode($params));
        
        // 业务处理
        $result = $this->processRequest();
        
        // 计算耗时
        $duration = round((microtime(true) - $startTime) * 1000, 2);
        
        // 记录请求结束
        Log::info("API请求完成 - {$method} {$uri} - 耗时: {$duration}ms - 结果: 成功");
        
        Response::success($result);
        
    } catch (\Exception $e) {
        $duration = round((microtime(true) - $startTime) * 1000, 2);
        
        // 记录异常
        Log::exception($e);
        Log::info("API请求失败 - {$method} {$uri} - 耗时: {$duration}ms - 错误: {$e->getMessage()}");
        
        Response::serverError('请求失败');
    }
}
```

### 示例 3：数据库操作日志

```php
public function updateUserStatus() {
    $userId = Request::post('user_id');
    $newStatus = Request::post('status');
    $adminId = Request::getTokenPayload('user_id');
    
    try {
        // 查询旧状态
        $user = DB::table('users')->where('id', $userId)->first();
        if (!$user) {
            Response::notFound('用户不存在');
            return;
        }
        
        $oldStatus = $user['status'];
        
        // 更新状态
        DB::beginTransaction();
        
        DB::table('users')
            ->where('id', $userId)
            ->update(['status' => $newStatus]);
        
        // 记录操作日志
        DB::table('user_logs')->insert([
            'user_id' => $userId,
            'admin_id' => $adminId,
            'action' => 'update_status',
            'old_value' => $oldStatus,
            'new_value' => $newStatus,
            'created_at' => date('Y-m-d H:i:s')
        ]);
        
        DB::commit();
        
        // 记录日志
        Log::info("用户状态更新 - 用户ID: {$userId}, 管理员ID: {$adminId}, 旧状态: {$oldStatus}, 新状态: {$newStatus}");
        
        Response::success(null, '更新成功');
        
    } catch (\Exception $e) {
        DB::rollBack();
        Log::exception($e);
        Log::info("用户状态更新失败 - 用户ID: {$userId}, 管理员ID: {$adminId}, 错误: {$e->getMessage()}");
        Response::serverError('更新失败');
    }
}
```

### 示例 4：第三方 API 调用日志

```php
public function callThirdPartyApi() {
    $apiUrl = 'https://api.example.com/data';
    $params = ['key' => 'value'];
    
    try {
        // 记录调用开始
        Log::info("调用第三方API - URL: {$apiUrl} - 参数: " . json_encode($params));
        
        $startTime = microtime(true);
        
        // 调用 API
        $response = $this->httpClient->post($apiUrl, $params);
        
        $duration = round((microtime(true) - $startTime) * 1000, 2);
        
        // 记录调用结果
        Log::info("第三方API响应 - URL: {$apiUrl} - 耗时: {$duration}ms - 响应: " . json_encode($response));
        
        return $response;
        
    } catch (\Exception $e) {
        // 记录异常
        Log::exception($e);
        Log::info("第三方API调用失败 - URL: {$apiUrl} - 错误: {$e->getMessage()}");
        throw $e;
    }
}
```

### 示例 5：WebSocket 连接日志

```php
use App\Base\OpenController;
use Library\Facades\Log;
use Library\Facades\Request;
use Library\Facades\Server;

class ChatOpenController extends OpenController {
    
    public function open() {
        $fd = Request::fd();
        $ip = Request::ip();
        $userId = Request::getTokenPayload('user_id');
        
        try {
            // 验证用户
            if (!$userId) {
                Log::info("WebSocket连接被拒绝 - FD: {$fd}, IP: {$ip}, 原因: 未认证");
                Server::disconnectCurrent(4001, 'Unauthorized');
                return;
            }
            
            // 保存连接映射
            Redis::hSet('online_users', $fd, $userId);
            
            // 获取在线人数
            $onlineCount = Server::getConnectionCount();
            
            // 记录连接日志
            Log::info("WebSocket连接建立 - FD: {$fd}, IP: {$ip}, 用户ID: {$userId}, 在线人数: {$onlineCount}");
            
            // 推送欢迎消息
            Server::pushSuccessCurrent([
                'type' => 'welcome',
                'online_count' => $onlineCount
            ], '连接成功');
            
        } catch (\Exception $e) {
            Log::exception($e);
            Log::info("WebSocket连接建立失败 - FD: {$fd}, IP: {$ip}, 错误: {$e->getMessage()}");
            Server::disconnectCurrent(4000, 'Internal Error');
        }
    }
}

class ChatCloseController extends CloseController {
    
    public function close() {
        $fd = Request::fd();
        $userId = Redis::hGet('online_users', $fd);
        
        // 删除连接映射
        Redis::hDel('online_users', $fd);
        
        // 获取在线人数
        $onlineCount = Server::getConnectionCount();
        
        // 记录断开日志
        Log::info("WebSocket连接断开 - FD: {$fd}, 用户ID: {$userId}, 在线人数: {$onlineCount}");
    }
}
```

### 示例 6：定时任务日志

```php
public function cronJob() {
    $taskName = '清理过期数据';
    $startTime = microtime(true);
    
    Log::info("定时任务开始 - {$taskName}");
    
    try {
        // 清理过期会话
        $deletedSessions = $this->cleanExpiredSessions();
        
        // 清理过期验证码
        $deletedCodes = $this->cleanExpiredCodes();
        
        // 清理过期日志
        $deletedLogs = $this->cleanExpiredLogs();
        
        $duration = round((microtime(true) - $startTime), 2);
        
        Log::info("定时任务完成 - {$taskName} - 耗时: {$duration}s - 清理会话: {$deletedSessions}, 清理验证码: {$deletedCodes}, 清理日志: {$deletedLogs}");
        
    } catch (\Exception $e) {
        $duration = round((microtime(true) - $startTime), 2);
        
        Log::exception($e);
        Log::info("定时任务失败 - {$taskName} - 耗时: {$duration}s - 错误: {$e->getMessage()}");
    }
}
```

---

## 日志文件

### 存储位置

日志文件默认存储在配置的日志目录中，按日期分类：

```
storage/logs/server/
├── 2026-01-01.log
├── 2026-01-02.log
├── 2026-01-03.log
└── ...
```

### 日志格式

每条日志记录包含时间戳、日志级别、MDC 上下文和日志内容：

```
[时间戳] [日志级别] [MDC上下文] [日志内容]
```

**MDC 上下文**：包含 requestId、user_id 等上下文信息，格式为 `[key:value]`
**RequestId**：自动生成的唯一标识符，用于追踪请求链路

### 日志示例

```
2026-01-01 12:00:00 info [requestId:20260101120000-abc123-1234] [user_id:123] [action:login] 用户登录成功 - IP: 192.168.1.100
2026-01-01 12:01:00 debug [requestId:20260101120100-def456-5678] SQL查询: SELECT * FROM users WHERE id = 123
2026-01-01 12:02:00 warning [requestId:20260101120200-ghi789-9012] 接口调用频率过高 - IP: 192.168.1.100
2026-01-01 12:03:00 error [requestId:20260101120300-jkl012-3456] 数据库连接失败_500
Connection refused
#0 /path/to/file.php(10): Database->connect()
#1 /path/to/controller.php(20): Model->query()
#2 {main}
2026-01-01 12:04:00 critical [requestId:20260101120400-mno345-6789] 服务器内存不足 - 使用率: 95%
```

### 控制台日志颜色

控制台输出的日志会根据级别显示不同颜色：
- `debug`：青色
- `info`：绿色
- `notice`：黄色
- `warning`：黄色
- `error`：红色
- `critical`：紫色
- `alert`：紫色
- `emergency`：紫色

---

## MDC 功能使用说明

MDC（Mapped Diagnostic Context）提供了协程安全的上下文管理，允许在日志中添加额外的上下文信息，如用户ID、请求ID等。

### MDC 方法列表

#### put()
添加或更新 MDC 上下文信息

```php
use Library\Core\Mdc;

// 添加单个上下文
Mdc::put('user_id', 123);

// 添加多个上下文
Mdc::put('action', 'login');
Mdc::put('ip', '192.168.1.100');
```

#### get()
获取 MDC 上下文信息

```php
// 获取单个上下文
$userId = Mdc::get('user_id'); // 返回 123

// 获取不存在的上下文，返回默认值
$unknown = Mdc::get('unknown_key', 'default'); // 返回 'default'
```

#### remove()
移除指定的 MDC 上下文信息

```php
// 移除单个上下文
Mdc::remove('user_id');

// 移除后获取，返回 null
$userId = Mdc::get('user_id'); // 返回 null
```

#### clear()
清空所有 MDC 上下文信息

```php
// 清空所有上下文
Mdc::clear();

// 清空后获取，返回 null
$userId = Mdc::get('user_id'); // 返回 null
```

#### all()
获取所有 MDC 上下文信息

```php
// 获取所有上下文
$context = Mdc::all();
/*
返回：
[
    'user_id' => 123,
    'action' => 'login',
    'ip' => '192.168.1.100'
]
*/
```

#### has()
检查指定的 MDC 上下文是否存在

```php
// 检查上下文是否存在
$hasUserId = Mdc::has('user_id'); // 返回 true
$hasUnknown = Mdc::has('unknown_key'); // 返回 false
```

### MDC 使用示例

#### 1. 用户操作日志

```php
use Library\Core\Mdc;
use Library\Facades\Log;

public function updateUser($userId, $data) {
    // 设置 MDC 上下文
    Mdc::put('user_id', $userId);
    Mdc::put('action', 'update_user');
    Mdc::put('operator_id', $this->getCurrentUserId());
    
    try {
        // 业务逻辑
        $this->userService->update($userId, $data);
        
        // 记录成功日志
        Log::info('用户信息更新成功');
        // 日志格式：2026-01-01 12:00:00 info [requestId:xxx] [user_id:123] [action:update_user] [operator_id:456] 用户信息更新成功
        
    } catch (\Exception $e) {
        // 记录错误日志
        Log::error('用户信息更新失败');
        Log::exception($e);
        // 日志格式：2026-01-01 12:00:00 error [requestId:xxx] [user_id:123] [action:update_user] [operator_id:456] 用户信息更新失败
    }
}
```

#### 2. API 请求上下文

```php
use Library\Core\Mdc;
use Library\Facades\Log;

public function handleRequest($request) {
    // 从请求中获取信息
    $ip = $request->header['x-real-ip'] ?? $request->server['remote_addr'];
    $userId = $this->getUserIdFromToken($request);
    
    // 设置 MDC 上下文
    Mdc::put('ip', $ip);
    Mdc::put('user_id', $userId);
    Mdc::put('uri', $request->server['request_uri']);
    Mdc::put('method', $request->server['request_method']);
    
    $startTime = microtime(true);
    
    try {
        // 业务处理
        $result = $this->processRequest($request);
        
        $duration = round((microtime(true) - $startTime) * 1000, 2);
        Mdc::put('duration', $duration);
        
        Log::info('API请求处理成功');
        // 日志格式：2026-01-01 12:00:00 info [requestId:xxx] [ip:192.168.1.100] [user_id:123] [uri:/api/user] [method:GET] [duration:50] API请求处理成功
        
        return $result;
        
    } catch (\Exception $e) {
        $duration = round((microtime(true) - $startTime) * 1000, 2);
        Mdc::put('duration', $duration);
        Mdc::put('error', $e->getMessage());
        
        Log::error('API请求处理失败');
        Log::exception($e);
        // 日志格式：2026-01-01 12:00:00 error [requestId:xxx] [ip:192.168.1.100] [user_id:123] [uri:/api/user] [method:GET] [duration:100] [error:Internal Error] API请求处理失败
        
        throw $e;
    }
}
```

#### 3. 数据库操作上下文

```php
use Library\Core\Mdc;
use Library\Facades\Log;

public function executeTransaction($userId, $operations) {
    // 设置 MDC 上下文
    Mdc::put('user_id', $userId);
    Mdc::put('action', 'execute_transaction');
    Mdc::put('transaction_id', uniqid());
    
    try {
        DB::beginTransaction();
        
        foreach ($operations as $operation) {
            Mdc::put('current_operation', $operation['type']);
            Log::debug('执行操作: ' . $operation['type']);
            // 执行具体操作
        }
        
        DB::commit();
        Log::info('事务执行成功');
        // 日志格式：2026-01-01 12:00:00 info [requestId:xxx] [user_id:123] [action:execute_transaction] [transaction_id:abc123] 事务执行成功
        
    } catch (\Exception $e) {
        DB::rollBack();
        Log::error('事务执行失败');
        Log::exception($e);
        // 日志格式：2026-01-01 12:00:00 error [requestId:xxx] [user_id:123] [action:execute_transaction] [transaction_id:abc123] 事务执行失败
    }
}
```

---

## 最佳实践

### 1. 记录关键操作

```php
// ✅ 推荐：记录重要操作，包含完整上下文
Log::info("用户删除 - 用户ID: {$userId}, 操作人: {$adminId}");
Log::info("配置更新 - 配置项: {$key}, 旧值: {$oldValue}, 新值: {$newValue}");

// ❌ 不推荐：不记录关键操作
DB::table('users')->where('id', $userId)->delete();
```

### 2. 记录异常详情

```php
// ✅ 推荐：记录完整异常，包含上下文
try {
    // 业务逻辑
} catch (\Exception $e) {
    Log::exception($e); // 包含完整堆栈
    Log::info("操作失败 - 上下文: {$context}");
}

// ❌ 不推荐：只记录错误消息
catch (\Exception $e) {
    Log::info($e->getMessage()); // 缺少堆栈信息
}
```

### 3. 充分利用 MDC 上下文

```php
// ✅ 推荐：使用 MDC 记录上下文
use Library\Core\Mdc;

Mdc::put('user_id', $userId);
Mdc::put('action', 'login');
Mdc::put('ip', $ip);
Log::info('用户登录成功');

// ❌ 不推荐：手动拼接上下文
Log::info("用户登录成功 - 用户ID: {$userId}, IP: {$ip}, 操作: login");
```

### 4. 利用 RequestId 追踪请求链路

```php
// ✅ 推荐：使用 RequestId 关联日志
// RequestId 会自动添加到 MDC 中，无需手动设置
Log::info('请求开始');
// 业务逻辑
Log::info('请求中间步骤');
// 更多逻辑
Log::info('请求结束');

// 所有日志会自动包含相同的 RequestId，便于追踪完整请求链路
```

### 5. 记录性能信息

```php
// ✅ 推荐：记录耗时，使用 MDC 上下文
$startTime = microtime(true);
// ... 业务逻辑
$duration = round((microtime(true) - $startTime) * 1000, 2);
Mdc::put('duration', $duration);
Log::info("操作完成");

// ❌ 不推荐：不记录性能
Log::info("操作完成");
```

### 6. 选择合适的日志级别

```php
// ✅ 推荐：根据日志重要性选择合适级别
Log::debug('调试信息，仅开发环境可见');
Log::info('正常业务流程');
Log::warning('需要关注的警告');
Log::error('错误信息，需要处理');
Log::critical('严重错误，影响系统功能');

// ❌ 不推荐：所有日志都使用同一级别
Log::info('调试信息');
Log::info('错误信息');
```

### 7. 异步日志性能优化

```php
// ✅ 推荐：使用异步日志，不阻塞主请求
Log::info('异步日志记录');

// ✅ 推荐：合理配置队列大小
// 在 config/default.php 中配置
'queue_size' => 10000, // 根据系统负载调整

// ❌ 不推荐：禁用异步日志（除非必要）
'async' => false, // 会阻塞主请求
```

### 8. 多目标输出配置

```php
// ✅ 推荐：根据环境配置输出目标
// 开发环境
'channels' => [
    'file' => true,
    'console' => true,
    'database' => false,
]

// 生产环境
'channels' => [
    'file' => true,
    'console' => false,
    'database' => true,
]
```

### 9. 保护敏感信息

```php
// ✅ 推荐：过滤敏感信息
Mdc::put('user_id', $userId);
// 不要记录密码、Token 等敏感信息
// Log::info("用户登录 - 密码: {$password}"); // ❌ 错误

// ✅ 推荐：记录脱敏信息
Log::info("用户登录 - 邮箱: " . maskEmail($email));
```

### 10. 及时清理 MDC 上下文

```php
// ✅ 推荐：在请求结束时清理 MDC
register_shutdown_function(function() {
    Mdc::clear();
});

// 或在中间件中清理
public function terminate() {
    Mdc::clear();
}
```

---

## 配置说明

日志配置在 `config/default.php` 中，支持丰富的配置选项：

```php
return [
    'log' => [
        'level' => 'info', // 日志级别，低于此级别的日志不记录
        'path' =>  dirname(__DIR__) . '/storage/logs/server', // 日志存储路径
        
        // 异步日志配置
        'async' => true, // 是否启用异步日志
        'queue_size' => 10000, // 日志队列大小
        'process_num' => 1, // 日志处理进程数
        
        // 日志输出目标配置，可以同时输出到多个目标
        'channels' => [
            'file' => true, // 输出到文件
            'console' => true, // 输出到控制台
            'database' => false, // 输出到数据库
        ],
        
        // 数据库日志配置
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
    ]
];
```

---

## 日志分析

### 查看今天的日志

```bash
tail -f storage/logs/server/2026-01-01.log
```

### 搜索错误日志

```bash
grep "error" storage/logs/server/2026-01-01.log
grep "Exception" storage/logs/server/2026-01-01.log
grep "critical" storage/logs/server/2026-01-01.log
```

### 统计 API 调用次数

```bash
grep "API请求完成" storage/logs/server/2026-01-01.log | wc -l
```

### 分析慢请求

```bash
grep "duration" storage/logs/server/2026-01-01.log | grep -v "debug" | awk -F"duration:" '{print $2}' | awk '$1 > 1000' # 超过1秒的请求
```

### 根据 RequestId 追踪请求链路

```bash
# 获取特定 RequestId 的所有日志
REQUEST_ID="20260101120000-abc123-1234"
grep "requestId:${REQUEST_ID}" storage/logs/server/2026-01-01.log

# 按时间排序查看完整请求链路
grep "requestId:${REQUEST_ID}" storage/logs/server/2026-01-01.log | sort
```

### 根据 MDC 上下文过滤日志

```bash
# 获取特定用户的所有日志
USER_ID="123"
grep "user_id:${USER_ID}" storage/logs/server/2026-01-01.log

# 获取特定操作的所有日志
ACTION="login"
grep "action:${ACTION}" storage/logs/server/2026-01-01.log

# 组合过滤条件
USER_ID="123"
ACTION="login"
grep "user_id:${USER_ID}" storage/logs/server/2026-01-01.log | grep "action:${ACTION}"
```

### 按日志级别过滤

```bash
# 只查看错误级别及以上的日志
grep -E "(error|critical|alert|emergency)" storage/logs/server/2026-01-01.log

# 只查看调试日志
grep "debug" storage/logs/server/2026-01-01.log
```

### 实时监控日志

```bash
# 实时监控日志，只显示包含特定 RequestId 的日志
REQUEST_ID="20260101120000-abc123-1234"
tail -f storage/logs/server/2026-01-01.log | grep "requestId:${REQUEST_ID}"

# 实时监控错误日志
tail -f storage/logs/server/2026-01-01.log | grep "error"```

---

## 注意事项

1. **日志目录权限**：确保日志目录有写入权限
2. **日志文件大小**：定期清理或归档旧日志文件，建议保留最近 7-30 天的日志
3. **敏感信息保护**：不要记录密码、Token、信用卡号等敏感信息，如需记录可使用脱敏处理
4. **日志级别选择**：根据日志重要性选择合适的级别，避免所有日志都使用同一级别
5. **异步日志配置**：
   - 合理设置队列大小，避免队列溢出
   - 监控队列状态，确保日志正常处理
   - 队列满时会自动降级为同步写入，可能影响性能
6. **多目标输出性能**：
   - 生产环境建议只输出到文件和数据库，关闭控制台输出
   - 数据库日志会增加数据库压力，建议评估性能影响
7. **MDC 上下文管理**：
   - 及时清理 MDC 上下文，避免内存泄漏
   - 不要在 MDC 中存储过大的数据
   - 确保 MDC 操作的协程安全性
8. **性能影响**：
   - 异步日志大大降低了对主请求的性能影响
   - 但仍需合理记录日志，避免过度日志记录
   - 调试日志建议只在开发环境启用
9. **RequestId 追踪**：
   - RequestId 自动添加到所有日志中，无需手动设置
   - 可用于追踪完整的请求链路
   - 建议在 API 响应中返回 RequestId，便于客户端调试
10. **日志格式一致性**：
    - 保持日志格式一致，便于日志分析工具处理
    - 新功能开发时建议使用 MDC 而非手动拼接上下文

---

## 扩展日志功能

当前日志系统已经实现了丰富的功能，包括多种日志级别、异步日志处理和多目标输出。如需进一步扩展，可以考虑以下方向：

### 1. 添加新的日志输出目标

```php
// 示例：添加 ELK 输出目标
namespace Library\Core\Log;

class ElkLogHandler implements LogHandlerInterface {
    public function handle($logData) {
        // 将日志发送到 ELK 集群
        $this->sendToElk($logData);
    }
}
```

### 2. 实现日志轮转策略

```php
// 示例：按文件大小轮转
namespace Library\Core\Log;

class SizeBasedLogRotator implements LogRotatorInterface {
    public function shouldRotate($logFile) {
        // 检查文件大小是否超过阈值
        return filesize($logFile) > 100 * 1024 * 1024; // 100MB
    }
    
    public function rotate($logFile) {
        // 执行日志轮转
        $this->compressAndArchive($logFile);
    }
}
```

### 3. 添加日志过滤功能

```php
// 示例：过滤敏感信息
namespace Library\Core\Log;

class SensitiveDataFilter implements LogFilterInterface {
    public function filter($logData) {
        // 过滤密码、Token 等敏感信息
        $logData['content'] = preg_replace('/password":".*?"/', 'password":"***"', $logData['content']);
        return $logData;
    }
}
```

### 4. 实现分布式日志追踪

```php
// 示例：添加链路追踪支持
namespace Library\Core\Log;

class TraceIdMiddleware {
    public function run() {
        // 从请求头获取或生成 TraceId
        $traceId = Request::header('X-Trace-Id') ?? uniqid();
        Mdc::put('trace_id', $traceId);
    }
}
```

### 5. 添加日志告警功能

```php
// 示例：实现日志告警
namespace Library\Core\Log;

class LogAlertHandler {
    public function handle($logData) {
        if ($this->shouldAlert($logData)) {
            // 发送告警通知
            $this->sendAlert($logData);
        }
    }
    
    private function shouldAlert($logData) {
        // 检查是否需要告警
        return in_array($logData['level'], ['error', 'critical', 'alert', 'emergency']);
    }
}
```

---

## 技术支持

- **Log 门面文件路径**：`d:\SVN_OTHER\basic\api_swoole\library\Facades\Log.php`
- **异步日志处理器路径**：`d:\SVN_OTHER\basic\api_swoole\library\Core\Log\AsyncLogProcessor.php`
- **MDC 上下文管理路径**：`d:\SVN_OTHER\basic\api_swoole\library\Core\Mdc.php`
- **依赖**：
  - Config 配置类
  - Swoole Coroutine Channel
  - Swoole Coroutine API
- **版本**：适配 Swoole 4.x+，支持 PHP 7.4+

### 核心组件说明

| 组件 | 功能 | 文件路径 |
|------|------|----------|
| Log 门面 | 提供简洁的日志记录接口 | `library/Facades/Log.php` |
| AsyncLogProcessor | 异步日志处理，支持多目标输出 | `library/Core/Log/AsyncLogProcessor.php` |
| Mdc | 协程安全的上下文管理 | `library/Core/Mdc.php` |

### 配置文件

- **主配置文件**：`config/default.php`，包含日志级别、输出目标、异步配置等
- **环境变量**：支持通过 `.env` 文件配置日志相关参数

---

## 日志轮转功能

日志系统内置了完整的日志轮转策略，能够自动管理日志文件，避免磁盘空间被无限占用。

### 轮转策略

#### 1. 按天轮转
- 日志文件默认按天生成，命名格式：`YYYY-MM-DD.log`
- 每天自动创建新的日志文件，无需手动干预
- 便于按日期查找和管理日志

#### 2. 按大小轮转
- 当单个日志文件超过配置的 `max_size` 时，自动生成带序号的新文件
- 命名格式：`YYYY-MM-DD.1.log`、`YYYY-MM-DD.2.log`
- 避免单个日志文件过大，便于查看和处理

#### 3. 过期清理
- 自动清理超过 `max_days` 配置的旧日志文件
- 默认保留 7 天的日志
- 每天凌晨 1 点执行清理任务

#### 4. 日志压缩
- 可选功能，通过 `compress` 配置启用
- 对超过 `compress_age` 配置的旧日志文件进行压缩
- 压缩格式：`.gz`，可减少 70%-90% 的磁盘空间占用
- 压缩后的文件命名格式：`YYYY-MM-DD.log.gz`

### 日志文件命名规则

| 类型 | 命名格式 | 示例 | 说明 |
|------|----------|------|------|
| 主日志文件 | `YYYY-MM-DD.log` | `2026-01-03.log` | 当天的主日志文件 |
| 大小轮转文件 | `YYYY-MM-DD.N.log` | `2026-01-03.1.log` | 当天超过大小限制的第N个日志文件 |
| 压缩日志文件 | `YYYY-MM-DD.log.gz` | `2026-01-03.log.gz` | 已压缩的旧日志文件 |
| 压缩轮转文件 | `YYYY-MM-DD.N.log.gz` | `2026-01-03.1.log.gz` | 已压缩的旧轮转日志文件 |

### 配置示例

#### 1. 默认配置（适合大多数场景）

```php
'rotation' => [
    'max_days' => 7,
    'max_size' => 100 * 1024 * 1024,
    'compress' => false,
    'compress_age' => 1,
]
```

#### 2. 磁盘空间紧张场景

```php
'rotation' => [
    'max_days' => 3, // 只保留3天日志
    'max_size' => 50 * 1024 * 1024, // 单个文件50MB
    'compress' => true, // 启用压缩
    'compress_age' => 0, // 当天日志就压缩
]
```

#### 3. 高并发场景

```php
'rotation' => [
    'max_days' => 14, // 保留14天日志
    'max_size' => 50 * 1024 * 1024, // 单个文件50MB，高并发下生成多个文件
    'compress' => true, // 启用压缩
    'compress_age' => 1, // 超过1天的日志压缩
]
```

---

## 性能优化建议

### 1. 异步日志配置优化

```php
// 生产环境建议配置
'log' => [
    'async' => true,
    'queue_size' => 20000, // 增大队列大小，适应高并发场景
    'process_num' => 2, // 增加处理进程数
    'channels' => [
        'file' => true,
        'console' => false, // 关闭控制台输出
        'database' => true,
    ],
]
```

### 2. 日志级别调整

```php
// 开发环境
'log' => [
    'level' => 'debug'
]

// 生产环境
'log' => [
    'level' => 'info' // 只记录 info 及以上级别的日志
]
```

### 3. 日志文件优化

- 使用 SSD 存储日志文件，提高写入性能
- 利用日志轮转功能自动管理日志文件，无需手动清理
- 根据磁盘空间调整 `max_days` 和 `max_size` 配置
- 启用日志压缩可减少 70%-90% 的磁盘空间占用
- 高并发场景建议减小 `max_size`，避免单个文件过大
- 定期监控日志目录大小，及时调整配置

### 4. 日志轮转最佳实践

```php
// ✅ 推荐：根据场景调整配置
'rotation' => [
    'max_days' => env('LOG_MAX_DAYS', 7),
    'max_size' => env('LOG_MAX_SIZE', 100 * 1024 * 1024),
    'compress' => env('LOG_COMPRESS', false),
    'compress_age' => env('LOG_COMPRESS_AGE', 1),
]

// ❌ 不推荐：使用默认配置不调整
'rotation' => [
    // 不配置，使用默认值
]

// ❌ 不推荐：设置过大的文件大小
'rotation' => [
    'max_size' => 1024 * 1024 * 1024, // 1GB，单个文件过大
]
```

---

© 2026 Swoole Framework Log Facade
