# Server 门面使用说明

## 简介

Server 门面类提供了便捷的 Swoole Server 操作方法，支持 WebSocket 推送、TCP/UDP 发送、连接管理、广播等功能，统一了消息发送格式。

## 基本用法

```php
use Library\Facades\Server;

// WebSocket 推送消息
Server::push($fd, 'Hello WebSocket');

// 推送成功消息（统一格式）
Server::pushSuccess($fd, $data, '操作成功');

// 广播消息
Server::broadcast('广播内容');
```

## 方法列表

### 一、WebSocket 推送（push）

#### push()
向 WebSocket 客户端推送消息

```php
// 基础用法
Server::push($fd, 'Hello');

// 推送二进制数据
Server::push($fd, $binaryData, WEBSOCKET_OPCODE_BINARY);

// 分片传输
Server::push($fd, $data, WEBSOCKET_OPCODE_TEXT, false);
```

**参数说明**：
- `$fd`：连接的文件描述符
- `$data`：要发送的数据
- `$opcode`：数据类型（1=文本，2=二进制，默认1）
- `$finish`：是否完成发送（默认true）

#### pushCurrent()
向当前 WebSocket 客户端推送消息

```php
// 自动获取当前连接的 fd
Server::pushCurrent('Hello Current User');
```

**适用场景**：在控制器中直接向当前请求的用户推送消息

#### pushSuccess()
向指定 WebSocket 客户端推送成功消息（统一格式）

```php
// 基础用法
Server::pushSuccess($fd, $data, '操作成功');

// 只推送数据
Server::pushSuccess($fd, ['user_id' => 123]);

// 自定义状态码
Server::pushSuccess($fd, $data, '创建成功', 201);

// 推送格式：
// {
//     "code": 0,
//     "message": "操作成功",
//     "data": { ... }
// }
```

#### pushSuccessCurrent()
向当前 WebSocket 客户端推送成功消息

```php
Server::pushSuccessCurrent(['result' => 'ok'], '处理成功');
```

#### pushFail()
向指定 WebSocket 客户端推送失败消息（统一格式）

```php
// 基础用法
Server::pushFail($fd, '操作失败');

// 自定义错误码
Server::pushFail($fd, '权限不足', 403);

// 带额外数据
Server::pushFail($fd, '验证失败', 400, ['errors' => $errors]);

// 推送格式：
// {
//     "code": 500,
//     "message": "操作失败",
//     "data": null
// }
```

#### pushFailCurrent()
向当前 WebSocket 客户端推送失败消息

```php
Server::pushFailCurrent('操作失败', 500);
```

---

### 二、TCP/UDP 发送（send）

#### send()
向客户端发送数据（TCP/UDP）

```php
// TCP 发送
Server::send($fd, 'Hello TCP');

// 指定服务器套接字
Server::send($fd, $data, $serverSocket);
```

**注意**：send() 用于 TCP/UDP 服务器，push() 用于 WebSocket 服务器

#### sendCurrent()
向当前客户端发送数据（TCP/UDP）

```php
Server::sendCurrent('Hello Current Client');
```

#### sendSuccess()
向指定客户端发送成功消息（统一格式）

```php
Server::sendSuccess($fd, $data, '操作成功');
```

#### sendSuccessCurrent()
向当前客户端发送成功消息

```php
Server::sendSuccessCurrent(['result' => 'ok'], '处理成功');
```

#### sendFail()
向指定客户端发送失败消息（统一格式）

```php
Server::sendFail($fd, '操作失败', 500);
```

#### sendFailCurrent()
向当前客户端发送失败消息

```php
Server::sendFailCurrent('操作失败', 500);
```

---

### 三、连接管理

#### exists()
判断连接是否存在

```php
if (Server::exists($fd)) {
    // 连接存在
}
```

#### isEstablished()
判断 WebSocket 连接是否已建立

```php
if (Server::isEstablished($fd)) {
    // WebSocket 握手已完成
    Server::push($fd, 'message');
}
```

**区别**：
- `exists()`：检查 TCP 连接是否存在
- `isEstablished()`：检查 WebSocket 握手是否完成

#### close()
关闭客户端连接

```php
// 正常关闭
Server::close($fd);

// 强制关闭（发送 RST 包）
Server::close($fd, true);
```

#### closeCurrent()
关闭当前客户端连接

```php
Server::closeCurrent();
Server::closeCurrent(true); // 强制关闭
```

#### disconnect()
断开客户端连接（WebSocket）

```php
// 正常断开
Server::disconnect($fd);

// 自定义关闭码和原因
Server::disconnect($fd, 1000, 'Normal Closure');
Server::disconnect($fd, 1001, 'Going Away');
```

**WebSocket 关闭码**：
- `1000`：正常关闭
- `1001`：离开
- `1002`：协议错误
- `1003`：不接受的数据类型

#### disconnectCurrent()
断开当前客户端连接（WebSocket）

```php
Server::disconnectCurrent(1000, 'Logout');
```

---

### 四、连接信息

#### getClientInfo()
获取客户端连接信息

```php
$info = Server::getClientInfo($fd);

// 返回信息包含：
// [
//     'server_port' => 9501,
//     'server_fd' => 4,
//     'socket_fd' => 8,
//     'socket_type' => 1,
//     'remote_port' => 12345,
//     'remote_ip' => '192.168.1.100',
//     'reactor_id' => 0,
//     'connect_time' => 1640995200,
//     'last_time' => 1640995300,
//     'close_errno' => 0,
//     'websocket_status' => 3,  // WebSocket 状态
// ]
```

#### getClientList()
获取所有客户端连接列表

```php
// 获取前10个连接
$connections = Server::getClientList(0, 10);

// 分页获取
$connections = Server::getClientList(100, 50); // 从第100个开始，获取50个
```

#### getConnectionCount()
获取连接总数

```php
$count = Server::getConnectionCount();
```

---

### 五、广播

#### broadcast()
向所有客户端连接群发消息

```php
// 广播给所有人
$count = Server::broadcast('系统通知：服务器将于10分钟后维护');

// 排除特定连接
$count = Server::broadcast('群发消息', [$fd1, $fd2]);

// 返回成功发送的数量
echo "成功发送给 {$count} 个客户端";
```

**注意**：
- 只向已完成 WebSocket 握手的连接推送
- 自动跳过已断开的连接
- 返回成功发送的数量

---

### 六、进程通信

#### sendMessage()
向任意 worker 进程或 task 进程发送消息

```php
// 向 worker 进程发送消息
Server::sendMessage(['type' => 'reload_config'], 0);

// 向 task 进程发送消息
Server::sendMessage(['task' => 'data'], 2);
```

**用途**：
- 进程间通信
- 配置热更新
- 数据同步

---

### 七、异步任务

#### task()
投递异步任务到 task_worker 池中

```php
// 基础用法
$taskId = Server::task(['action' => 'send_email', 'to' => 'user@example.com']);

// 指定 Task 进程
$taskId = Server::task($data, 0);

// 带完成回调
$taskId = Server::task($data, -1, function($server, $taskId, $data) {
    echo "任务 {$taskId} 完成，结果：" . $data;
});
```

---

### 八、服务器信息

#### stats()
获取 Server 统计信息

```php
$stats = Server::stats();

// 返回信息：
// [
//     'start_time' => 1640995200,      // 启动时间
//     'connection_num' => 100,          // 当前连接数
//     'accept_count' => 1000,           // 接受的连接总数
//     'close_count' => 900,             // 关闭的连接总数
//     'tasking_num' => 5,               // 正在执行的任务数
//     'request_count' => 10000,         // 收到的请求总数
//     'worker_request_count' => 1000,   // 当前 Worker 处理的请求数
//     'coroutine_num' => 10,            // 当前协程数量
// ]
```

#### getServer()
获取原始 Swoole Server 对象

```php
$server = Server::getServer();

// 直接调用 Swoole Server 方法
$server->reload();
```

---

## 完整示例

### 示例 1：WebSocket 聊天室

```php
use Library\Facades\Server;
use Library\Facades\Request;

// WebSocket 消息处理
public function onMessage() {
    $message = Request::all('message');
    $userId = Request::getTokenPayload('user_id');
    $currentFd = Request::fd();
    
    // 构造广播消息
    $data = [
        'user_id' => $userId,
        'message' => $message,
        'time' => date('H:i:s'),
    ];
    
    // 广播给所有人（排除自己）
    $count = Server::broadcast(
        json_encode([
            'code' => 0,
            'message' => 'new_message',
            'data' => $data
        ]),
        [$currentFd]
    );
    
    // 向自己推送发送成功消息
    Server::pushSuccessCurrent(['sent_count' => $count], '发送成功');
}
```

### 示例 2：在线用户管理

```php
// 用户上线
public function onOpen() {
    $fd = Request::fd();
    $userId = Request::getTokenPayload('user_id');
    
    // 存储 fd 和 userId 的映射关系
    Redis::hSet('online_users', $fd, $userId);
    
    // 获取在线人数
    $onlineCount = Server::getConnectionCount();
    
    // 广播用户上线
    Server::broadcast(json_encode([
        'code' => 0,
        'message' => 'user_online',
        'data' => [
            'user_id' => $userId,
            'online_count' => $onlineCount
        ]
    ]));
    
    // 向新用户推送欢迎消息
    Server::pushSuccessCurrent(['online_count' => $onlineCount], '欢迎加入');
}

// 用户下线
public function onClose() {
    $fd = Request::fd();
    $userId = Redis::hGet('online_users', $fd);
    
    // 删除映射关系
    Redis::hDel('online_users', $fd);
    
    // 获取在线人数
    $onlineCount = Server::getConnectionCount();
    
    // 广播用户下线
    Server::broadcast(json_encode([
        'code' => 0,
        'message' => 'user_offline',
        'data' => [
            'user_id' => $userId,
            'online_count' => $onlineCount
        ]
    ]));
}
```

### 示例 3：私聊功能

```php
public function sendPrivateMessage() {
    $toUserId = Request::all('to_user_id');
    $message = Request::all('message');
    $fromUserId = Request::getTokenPayload('user_id');
    
    // 查找目标用户的 fd
    $allOnlineUsers = Redis::hGetAll('online_users');
    $targetFd = null;
    
    foreach ($allOnlineUsers as $fd => $userId) {
        if ($userId == $toUserId) {
            $targetFd = $fd;
            break;
        }
    }
    
    if (!$targetFd) {
        Server::pushFailCurrent('用户不在线', 404);
        return;
    }
    
    // 检查连接是否存在
    if (!Server::isEstablished($targetFd)) {
        Server::pushFailCurrent('用户连接已断开', 404);
        return;
    }
    
    // 发送私聊消息
    $data = [
        'from_user_id' => $fromUserId,
        'message' => $message,
        'time' => date('H:i:s'),
    ];
    
    $success = Server::pushSuccess($targetFd, $data, '私聊消息');
    
    if ($success) {
        Server::pushSuccessCurrent(null, '发送成功');
    } else {
        Server::pushFailCurrent('发送失败', 500);
    }
}
```

### 示例 4：踢人功能

```php
public function kickUser() {
    $targetUserId = Request::all('user_id');
    $adminId = Request::getTokenPayload('user_id');
    
    // 检查权限
    $isAdmin = DB::table('users')->where('id', $adminId)->value('is_admin');
    if (!$isAdmin) {
        Server::pushFailCurrent('无权限', 403);
        return;
    }
    
    // 查找目标用户的 fd
    $allOnlineUsers = Redis::hGetAll('online_users');
    $targetFd = null;
    
    foreach ($allOnlineUsers as $fd => $userId) {
        if ($userId == $targetUserId) {
            $targetFd = $fd;
            break;
        }
    }
    
    if (!$targetFd) {
        Server::pushFailCurrent('用户不在线', 404);
        return;
    }
    
    // 发送踢出通知
    Server::pushFail($targetFd, '您已被管理员踢出', 403);
    
    // 延迟关闭连接
    \Swoole\Coroutine::sleep(1);
    Server::disconnect($targetFd, 1008, 'Kicked by admin');
    
    // 通知管理员
    Server::pushSuccessCurrent(null, '已踢出用户');
}
```

### 示例 5：异步任务处理

```php
public function sendEmail() {
    $email = Request::all('email');
    $content = Request::all('content');
    
    // 投递异步任务
    $taskId = Server::task([
        'action' => 'send_email',
        'email' => $email,
        'content' => $content
    ], -1, function($server, $taskId, $result) {
        // 任务完成回调
        $fd = Request::fd();
        if ($result['success']) {
            Server::pushSuccess($fd, null, '邮件发送成功');
        } else {
            Server::pushFail($fd, '邮件发送失败', 500);
        }
    });
    
    // 立即返回
    Server::pushSuccessCurrent(['task_id' => $taskId], '任务已提交');
}
```

### 示例 6：系统通知广播

```php
public function broadcastNotice() {
    $notice = Request::all('notice');
    $adminId = Request::getTokenPayload('user_id');
    
    // 检查权限
    $isAdmin = DB::table('users')->where('id', $adminId)->value('is_admin');
    if (!$isAdmin) {
        Server::pushFailCurrent('无权限', 403);
        return;
    }
    
    // 广播系统通知
    $data = [
        'type' => 'system',
        'notice' => $notice,
        'time' => date('Y-m-d H:i:s'),
    ];
    
    $count = Server::broadcast(json_encode([
        'code' => 0,
        'message' => 'system_notice',
        'data' => $data
    ]));
    
    // 记录日志
    Log::info("管理员 {$adminId} 发送系统通知，到达 {$count} 个用户");
    
    Server::pushSuccessCurrent(['count' => $count], "已发送给 {$count} 个用户");
}
```

---

## push 和 send 的区别

| 特性 | push() | send() |
|------|--------|--------|
| **适用协议** | WebSocket | TCP/UDP |
| **数据格式** | 自动添加 WebSocket 帧头 | 原始数据包 |
| **opcode 支持** | ✅ 支持（文本/二进制） | ❌ 不支持 |
| **分片传输** | ✅ 支持 | ❌ 不支持 |
| **自动握手检查** | ✅ 是 | ❌ 否 |
| **使用场景** | WebSocket 服务器 | TCP/UDP 服务器 |

---

## 最佳实践

### 1. 推送前检查连接状态

```php
// ✅ 推荐：先检查再推送
if (Server::isEstablished($fd)) {
    Server::push($fd, $message);
}

// ❌ 不推荐：直接推送（可能失败）
Server::push($fd, $message);
```

### 2. 使用统一格式消息

```php
// ✅ 推荐：使用 pushSuccess/pushFail
Server::pushSuccess($fd, $data, '操作成功');
Server::pushFail($fd, '操作失败', 500);

// ❌ 不推荐：自定义格式
Server::push($fd, json_encode(['status' => 'ok', 'data' => $data]));
```

### 3. 广播时排除发送者

```php
// ✅ 推荐：排除自己
$currentFd = Request::fd();
Server::broadcast($message, [$currentFd]);

// ❌ 不推荐：发送给所有人（包括自己）
Server::broadcast($message);
```

### 4. 使用 *Current 方法简化代码

```php
// ✅ 推荐：使用 Current 方法
Server::pushSuccessCurrent($data, '成功');

// ❌ 不推荐：手动获取 fd
$fd = Request::fd();
Server::pushSuccess($fd, $data, '成功');
```

### 5. 异常处理

```php
// ✅ 推荐：检查返回值
$success = Server::push($fd, $message);
if (!$success) {
    Log::info("推送失败，fd: {$fd}");
}

// ❌ 不推荐：不检查返回值
Server::push($fd, $message);
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
        ]
    ]
];
```

---

## 注意事项

1. **协程安全**：Server 门面基于 Swoole 协程上下文，自动获取当前 Server 对象
2. **连接检查**：推送前务必检查连接是否存在（exists/isEstablished）
3. **广播性能**：大量连接时注意广播性能，可考虑分批发送
4. **异步任务**：task() 需要配置 task_worker_num 参数
5. **进程通信**：sendMessage() 只能在 worker 和 task 进程间使用

---

## 技术支持

- **文件路径**：`d:\SVN_OTHER\basic\api_swoole\library\Facades\Server.php`
- **依赖**：Swoole 扩展、Request 门面类、Config 配置类
- **版本**：适配 Swoole 4.x+

---

© 2026 Swoole Framework Server Facade
