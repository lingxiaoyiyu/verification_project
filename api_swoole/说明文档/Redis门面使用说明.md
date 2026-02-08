# Redis 门面使用说明

## 简介

Redis 门面类提供了便捷的 Redis 操作方法，基于 Redis 连接池封装，支持字符串、列表等常用数据类型操作，自动管理连接的获取和回收。

## 基本用法

```php
use Library\Facades\Redis;

// 字符串操作
Redis::set('key', 'value');
$value = Redis::get('key');
Redis::setex('key', 3600, 'value');
Redis::del('key');

// 哈希表操作
Redis::hSet('user:1001', 'name', '张三');
Redis::hSet('user:1001', 'age', '25');
$name = Redis::hGet('user:1001', 'name');
$user = Redis::hGetAll('user:1001');

// 集合操作
Redis::sAdd('tags', 'php');
Redis::sAdd('tags', 'mysql');
$tags = Redis::sMembers('tags');

// 有序集合操作
Redis::zAdd('ranking', 100, 'user:1');
Redis::zAdd('ranking', 90, 'user:2');
$top10 = Redis::zRevRange('ranking', 0, 9);

// 键操作
Redis::expire('key', 3600);
$exists = Redis::exists('key');
$ttl = Redis::ttl('key');
```

## 方法列表

### 一、初始化

#### initialize()
初始化 Redis 连接池

```php
// 在 Server 启动时调用（框架自动调用）
Redis::initialize();
```

#### getConn()
获取 Redis 连接

```php
$redis = Redis::getConn();
// 执行自定义操作
$redis->hSet('hash', 'field', 'value');
// 回收连接
Redis::recycle($redis);
```

#### recycle()
回收 Redis 连接

```php
$redis = Redis::getConn();
// ... 执行操作
Redis::recycle($redis);
```

**注意**：使用门面方法无需手动获取和回收连接

---

### 二、字符串操作

#### get()
获取键的值

```php
$value = Redis::get('username');

// 键不存在返回 false
if ($value === false) {
    echo "键不存在";
}
```

#### mget()
批量获取多个键的值

```php
// 方式1：数组
$values = Redis::mget(['key1', 'key2', 'key3']);

// 方式2：字符串（逗号分隔）
$values = Redis::mget('key1,key2,key3');

// 返回：['value1', 'value2', 'value3']
```

#### set()
设置键值

```php
$success = Redis::set('username', 'john');

// 返回：true 成功，false 失败
```

#### setnx()
键不存在时才设置值

```php
$success = Redis::setnx('lock:user:123', '1');

if ($success) {
    // 获取锁成功
} else {
    // 锁已被占用
}
```

**适用场景**：分布式锁

#### setex()
设置键值并指定过期时间（秒）

```php
// 设置 1 小时过期
Redis::setex('session:abc123', 3600, 'user_data');

// 设置 5 分钟过期
Redis::setex('verify_code:13800138000', 300, '123456');
```

#### mset()
批量设置多个键值

```php
$success = Redis::mset([
    'key1' => 'value1',
    'key2' => 'value2',
    'key3' => 'value3'
]);
```

#### getset()
设置新值并返回旧值

```php
$oldValue = Redis::getset('counter', '0');

// 键不存在时返回 false
if ($oldValue === false) {
    echo "键不存在，已设置新值";
}
```

#### del()
删除键（支持批量删除）

```php
// 删除单个键
$count = Redis::del('username');

// 删除多个键
$count = Redis::del(['key1', 'key2', 'key3']);

// 返回：删除的键数量
```

---

### 三、数值操作

#### incr()
将键的值增加 1

```php
$newValue = Redis::incr('page_views');

// 键不存在时会先初始化为 0 再增加
// 返回：增加后的值
```

#### incrBy()
将键的值增加指定整数

```php
// 增加 10
$newValue = Redis::incrBy('score', 10);

// 增加 -5（相当于减 5）
$newValue = Redis::incrBy('balance', -5);
```

#### incrByFloat()
将键的值增加指定浮点数

```php
// 增加 0.5
$newValue = Redis::incrByFloat('price', 0.5);

// 增加 -1.5（相当于减 1.5）
$newValue = Redis::incrByFloat('discount', -1.5);
```

#### decr()
将键的值减少 1

```php
$newValue = Redis::decr('stock');

// 返回：减少后的值
```

#### decrBy()
将键的值减少指定整数

```php
// 减少 10
$newValue = Redis::decrBy('stock', 10);
```

---

### 四、字符串辅助

#### strlen()
获取字符串长度

```php
$length = Redis::strlen('username');

// 键不存在返回 0
// 不是字符串返回 false
```

#### append()
追加值到字符串末尾

```php
$newLength = Redis::append('log', "\nNew log entry");

// 键不存在时相当于 set()
// 返回：追加后的字符串长度
```

---

### 五、列表操作

#### lPush()
将值插入到列表头部（不存在则创建）

```php
$length = Redis::lPush('queue', 'task1');

// 返回：列表长度
```

#### lPushx()
将值插入到列表头部（不存在则不创建）

```php
$length = Redis::lPushx('queue', 'task1');

// 列表不存在时返回 0
// 返回：列表长度
```

#### rPush()
将值插入到列表尾部（不存在则创建）

```php
$length = Redis::rPush('queue', 'task1');

// 返回：列表长度
```

#### rpushx()
将值插入到列表尾部（不存在则不创建）

```php
$length = Redis::rpushx('queue', 'task1');

// 列表不存在时返回 0
// 返回：列表长度
```

#### lPop()
移除并返回列表的第一个元素

```php
$value = Redis::lPop('queue');

// 列表为空或不存在返回 false
if ($value !== false) {
    // 处理任务
}
```

#### rPop()
移除并返回列表的最后一个元素

```php
$value = Redis::rPop('queue');

// 列表为空或不存在返回 false
```

#### blPop()
移除并获取列表的第一个元素（阻塞版本）

```php
// 最多等待 5 秒
$result = Redis::blPop('queue', 5);

// 返回：[0 => 'queue', 1 => 'value'] 或 [] (超时)
if (!empty($result)) {
    $key = $result[0];
    $value = $result[1];
}
```

**适用场景**：消息队列、任务队列

#### brPop()
移除并获取列表的最后一个元素（阻塞版本）

```php
// 最多等待 5 秒
$result = Redis::brPop('queue', 5);

// 返回：[0 => 'queue', 1 => 'value'] 或 [] (超时)
```

#### lLen()
获取列表长度

```php
$length = Redis::lLen('queue');

// 键不存在返回 0
```

---

### 六、哈希表操作

#### hSet()
设置哈希表字段值

```php
// 设置单个字段
Redis::hSet('user:1001', 'name', '张三');
Redis::hSet('user:1001', 'age', '25');
Redis::hSet('user:1001', 'email', 'zhangsan@example.com');
```

#### hGet()
获取哈希表字段值

```php
// 获取单个字段
$name = Redis::hGet('user:1001', 'name');
$email = Redis::hGet('user:1001', 'email');
```

#### hMset()
批量设置哈希表字段值

```php
// 批量设置字段
Redis::hMset('user:1002', [
    'name' => '李四',
    'age' => '30',
    'email' => 'lisi@example.com'
]);
```

#### hMget()
批量获取哈希表字段值

```php
// 批量获取字段
$values = Redis::hMget('user:1001', ['name', 'age', 'email']);

// 返回：['name' => '张三', 'age' => '25', 'email' => 'zhangsan@example.com']
```

#### hDel()
删除哈希表字段

```php
// 删除单个字段
Redis::hDel('user:1001', 'age');

// 删除多个字段
Redis::hDel('user:1001', ['email', 'phone']);
```

#### hExists()
检查哈希表字段是否存在

```php
// 检查字段是否存在
$exists = Redis::hExists('user:1001', 'email');

if ($exists) {
    echo "邮箱存在";
} else {
    echo "邮箱不存在";
}
```

#### hLen()
获取哈希表字段数量

```php
// 获取字段数量
$count = Redis::hLen('user:1001');
```

#### hKeys()
获取哈希表所有字段名

```php
// 获取所有字段名
$keys = Redis::hKeys('user:1001');

// 返回：['name', 'email']
```

#### hVals()
获取哈希表所有字段值

```php
// 获取所有字段值
$values = Redis::hVals('user:1001');

// 返回：['张三', 'zhangsan@example.com']
```

#### hGetAll()
获取哈希表所有字段和值

```php
// 获取所有字段和值
$user = Redis::hGetAll('user:1001');

// 返回：['name' => '张三', 'email' => 'zhangsan@example.com']
```

---

### 七、集合操作

#### sAdd()
向集合添加成员

```php
// 添加单个成员
Redis::sAdd('tags', 'php');

// 添加多个成员
Redis::sAdd('tags', ['mysql', 'redis', 'swoole']);
```

#### sRem()
从集合移除成员

```php
// 移除单个成员
Redis::sRem('tags', 'mysql');

// 移除多个成员
Redis::sRem('tags', ['redis', 'swoole']);
```

#### sMembers()
获取集合所有成员

```php
// 获取所有成员
$tags = Redis::sMembers('tags');

// 返回：['php', 'mysql', 'redis', 'swoole']
```

#### sIsMember()
检查成员是否在集合中

```php
// 检查成员是否存在
$isMember = Redis::sIsMember('tags', 'php');

if ($isMember) {
    echo "php 是标签之一";
}
```

#### sCard()
获取集合成员数量

```php
// 获取成员数量
$count = Redis::sCard('tags');
```

#### sPop()
随机移除并返回集合成员

```php
// 随机移除1个成员
$member = Redis::sPop('tags');

// 随机移除3个成员
$members = Redis::sPop('tags', 3);
```

#### sRandMember()
随机返回集合成员

```php
// 随机返回1个成员
$member = Redis::sRandMember('tags');

// 随机返回3个成员
$members = Redis::sRandMember('tags', 3);
```

---

### 八、有序集合操作

#### zAdd()
向有序集合添加成员

```php
// 添加单个成员
Redis::zAdd('ranking', 100, 'user:1');
Redis::zAdd('ranking', 90, 'user:2');
Redis::zAdd('ranking', 85, 'user:3');
```

#### zRem()
从有序集合移除成员

```php
// 移除单个成员
Redis::zRem('ranking', 'user:3');

// 移除多个成员
Redis::zRem('ranking', ['user:1', 'user:2']);
```

#### zScore()
获取有序集合成员分数

```php
// 获取成员分数
$score = Redis::zScore('ranking', 'user:1');

// 返回：100
```

#### zRank()
获取有序集合成员排名（从小到大）

```php
// 获取排名（排名从0开始）
$rank = Redis::zRank('ranking', 'user:1');

// 返回：0（表示第一名）
```

#### zRevRank()
获取有序集合成员倒序排名（从大到小）

```php
// 获取倒序排名（排名从0开始）
$rank = Redis::zRevRank('ranking', 'user:3');

// 返回：2（表示倒数第三名）
```

#### zRange()
按排名范围获取有序集合成员

```php
// 获取前10名（从小到大）
$top10 = Redis::zRange('ranking', 0, 9);

// 获取前10名，包含分数
$top10WithScores = Redis::zRange('ranking', 0, 9, true);
```

#### zRevRange()
按倒序排名范围获取有序集合成员

```php
// 获取前10名（从大到小）
$top10 = Redis::zRevRange('ranking', 0, 9);

// 获取前10名，包含分数
$top10WithScores = Redis::zRevRange('ranking', 0, 9, true);
```

#### zCard()
获取有序集合成员数量

```php
// 获取成员数量
$count = Redis::zCard('ranking');
```

---

### 九、键操作

#### exists()
检查键是否存在

```php
// 检查键是否存在
$exists = Redis::exists('user:1001');

if ($exists) {
    echo "键存在";
} else {
    echo "键不存在";
}
```

#### expire()
设置键过期时间

```php
// 设置键1小时后过期
Redis::expire('session:abc123', 3600);

// 设置键5分钟后过期
Redis::expire('verify_code:13800138000', 300);
```

#### ttl()
获取键剩余过期时间

```php
// 获取剩余过期时间（秒）
$ttl = Redis::ttl('session:abc123');

// 返回：
// -1：永不过期
// -2：键不存在
// 正数：剩余秒数
```

#### persist()
移除键过期时间

```php
// 移除过期时间，使其永不过期
Redis::persist('session:abc123');
```

#### keys()
模糊匹配键

```php
// 匹配所有以user:开头的键
$keys = Redis::keys('user:*');

// 匹配所有包含:100的键
$keys = Redis::keys('*:100*');

// 匹配所有键
$keys = Redis::keys('*');
```

#### type()
获取键类型

```php
// 获取键类型
$type = Redis::type('user:1001');

// 返回：string, list, set, zset, hash, none
```

---

### 十、事务操作

#### multi()
开启事务

```php
// 开启事务
$redis = Redis::multi();
```

#### exec()
执行事务

```php
// 执行事务
$result = Redis::exec($redis);

// 返回：事务中所有命令的执行结果数组
```

#### discard()
取消事务

```php
// 取消事务
Redis::discard($redis);
```

#### 事务使用示例

```php
try {
    // 开启事务
    $redis = Redis::multi();
    
    // 执行多个命令
    $redis->hSet('user:1001', 'name', '张三');
    $redis->hSet('user:1001', 'age', '25');
    $redis->sAdd('users', 'user:1001');
    
    // 执行事务
    $result = Redis::exec($redis);
    
    // 处理结果
    echo "事务执行成功";
    
} catch (\Exception $e) {
    // 取消事务
    Redis::discard($redis);
    echo "事务执行失败: " . $e->getMessage();
}
```

---

## 完整示例

### 示例 1：用户会话管理

```php
use Library\Facades\Redis;

// 登录成功后保存会话
public function login() {
    $username = Request::post('username');
    $password = Request::post('password');
    
    // 验证用户名密码
    $user = DB::table('users')
        ->where('username', $username)
        ->first();
    
    if (!$user || !password_verify($password, $user['password'])) {
        Response::fail('用户名或密码错误', 401);
        return;
    }
    
    // 生成会话 Token
    $token = bin2hex(random_bytes(32));
    
    // 保存会话信息（1小时过期）
    Redis::setex(
        "session:{$token}",
        3600,
        json_encode([
            'user_id' => $user['id'],
            'username' => $user['username'],
            'login_time' => time()
        ])
    );
    
    Response::success(['token' => $token], '登录成功');
}

// 获取当前用户信息
public function profile() {
    $token = Request::bearerToken();
    
    // 获取会话信息
    $session = Redis::get("session:{$token}");
    
    if (!$session) {
        Response::unauthorized('会话已过期，请重新登录');
        return;
    }
    
    $sessionData = json_decode($session, true);
    
    // 查询用户详细信息
    $user = DB::table('users')
        ->where('id', $sessionData['user_id'])
        ->first();
    
    Response::success($user);
}

// 退出登录
public function logout() {
    $token = Request::bearerToken();
    
    // 删除会话
    Redis::del("session:{$token}");
    
    Response::success(null, '退出成功');
}
```

### 示例 2：验证码

```php
// 发送验证码
public function sendCode() {
    $phone = Request::post('phone');
    
    // 检查发送频率（60秒内只能发送一次）
    $key = "verify_code_limit:{$phone}";
    $exists = Redis::get($key);
    
    if ($exists) {
        Response::fail('发送过于频繁，请稍后再试', 429);
        return;
    }
    
    // 生成验证码
    $code = rand(100000, 999999);
    
    // 保存验证码（5分钟有效）
    Redis::setex("verify_code:{$phone}", 300, $code);
    
    // 设置发送限制（60秒）
    Redis::setex($key, 60, '1');
    
    // 发送短信（省略）
    // SmsService::send($phone, $code);
    
    Response::success(null, '验证码已发送');
}

// 验证验证码
public function verifyCode() {
    $phone = Request::post('phone');
    $code = Request::post('code');
    
    // 获取验证码
    $savedCode = Redis::get("verify_code:{$phone}");
    
    if (!$savedCode) {
        Response::fail('验证码已过期', 400);
        return;
    }
    
    if ($savedCode != $code) {
        Response::fail('验证码错误', 400);
        return;
    }
    
    // 验证成功后删除验证码
    Redis::del("verify_code:{$phone}");
    
    Response::success(null, '验证成功');
}
```

### 示例 3：分布式锁

```php
// 获取锁
private function acquireLock($key, $timeout = 10) {
    $lockKey = "lock:{$key}";
    $identifier = uniqid();
    
    // 尝试获取锁
    $acquired = Redis::setnx($lockKey, $identifier);
    
    if ($acquired) {
        // 设置过期时间
        Redis::setex($lockKey, $timeout, $identifier);
        return $identifier;
    }
    
    return false;
}

// 释放锁
private function releaseLock($key, $identifier) {
    $lockKey = "lock:{$key}";
    $value = Redis::get($lockKey);
    
    // 只有锁的持有者才能释放
    if ($value === $identifier) {
        Redis::del($lockKey);
        return true;
    }
    
    return false;
}

// 秒杀场景
public function seckill() {
    $productId = Request::post('product_id');
    $userId = Request::getTokenPayload('user_id');
    
    $lockKey = "seckill:{$productId}";
    $identifier = $this->acquireLock($lockKey, 10);
    
    if (!$identifier) {
        Response::fail('系统繁忙，请稍后再试', 429);
        return;
    }
    
    try {
        // 检查库存
        $stock = Redis::get("stock:{$productId}");
        
        if ($stock <= 0) {
            Response::fail('商品已售罄', 400);
            return;
        }
        
        // 减库存
        Redis::decr("stock:{$productId}");
        
        // 创建订单
        $orderId = DB::table('orders')->insertGetId([
            'user_id' => $userId,
            'product_id' => $productId,
            'status' => 0,
            'created_at' => date('Y-m-d H:i:s')
        ]);
        
        Response::success(['order_id' => $orderId], '抢购成功');
        
    } finally {
        // 释放锁
        $this->releaseLock($lockKey, $identifier);
    }
}
```

### 示例 4：计数器和排行榜

```php
// 页面访问统计
public function recordPageView() {
    $page = Request::get('page', 'home');
    $date = date('Y-m-d');
    
    // 总访问量
    Redis::incr('page_views:total');
    
    // 今日访问量
    Redis::incr("page_views:daily:{$date}");
    
    // 页面访问量
    Redis::incr("page_views:page:{$page}");
    
    Response::success(null, '记录成功');
}

// 点赞
public function like() {
    $postId = Request::post('post_id');
    $userId = Request::getTokenPayload('user_id');
    
    // 检查是否已点赞
    $key = "like:{$postId}:{$userId}";
    $exists = Redis::get($key);
    
    if ($exists) {
        Response::fail('已经点赞过了', 400);
        return;
    }
    
    // 记录点赞
    Redis::setex($key, 86400 * 30, '1'); // 30天有效
    
    // 增加点赞数
    $count = Redis::incr("like_count:{$postId}");
    
    Response::success(['count' => $count], '点赞成功');
}

// 取消点赞
public function unlike() {
    $postId = Request::post('post_id');
    $userId = Request::getTokenPayload('user_id');
    
    // 删除点赞记录
    Redis::del("like:{$postId}:{$userId}");
    
    // 减少点赞数
    $count = Redis::decr("like_count:{$postId}");
    
    Response::success(['count' => $count], '取消点赞');
}
```

### 示例 5：消息队列

```php
// 生产者：添加任务到队列
public function addTask() {
    $taskData = Request::post('task');
    
    // 推入队列
    $length = Redis::rPush('task_queue', json_encode([
        'id' => uniqid(),
        'data' => $taskData,
        'created_at' => time()
    ]));
    
    Response::success(['queue_length' => $length], '任务已添加');
}

// 消费者：处理任务（在 Worker 进程中执行）
public function processTask() {
    while (true) {
        // 阻塞获取任务（最多等待 1 秒）
        $result = Redis::blPop('task_queue', 1);
        
        if (empty($result)) {
            // 没有任务，继续等待
            continue;
        }
        
        $taskJson = $result[1];
        $task = json_decode($taskJson, true);
        
        try {
            // 处理任务
            $this->handleTask($task);
            
            // 记录成功
            Redis::incr('task_success_count');
            
        } catch (\Exception $e) {
            // 记录失败
            Redis::incr('task_fail_count');
            Log::exception($e);
            
            // 重试队列（可选）
            Redis::rPush('task_retry_queue', $taskJson);
        }
        
        // 让出 CPU
        \Swoole\Coroutine::sleep(0.1);
    }
}
```

### 示例 6：缓存应用

```php
// 获取用户信息（带缓存）
public function getUserInfo() {
    $userId = Request::get('user_id');
    
    // 尝试从缓存获取
    $cacheKey = "user:{$userId}";
    $cached = Redis::get($cacheKey);
    
    if ($cached !== false) {
        $user = json_decode($cached, true);
        Response::success($user, '来自缓存');
        return;
    }
    
    // 缓存不存在，从数据库查询
    $user = DB::table('users')
        ->where('id', $userId)
        ->first();
    
    if (!$user) {
        Response::notFound('用户不存在');
        return;
    }
    
    // 保存到缓存（1小时）
    Redis::setex($cacheKey, 3600, json_encode($user));
    
    Response::success($user, '来自数据库');
}

// 更新用户信息（同时更新缓存）
public function updateUserInfo() {
    $userId = Request::getTokenPayload('user_id');
    $data = Request::only(['nickname', 'avatar', 'bio']);
    
    // 更新数据库
    DB::table('users')
        ->where('id', $userId)
        ->update($data);
    
    // 删除缓存（下次访问时重新生成）
    Redis::del("user:{$userId}");
    
    Response::success(null, '更新成功');
}
```

---

## 最佳实践

### 1. 合理设置过期时间

```php
// ✅ 推荐：设置合理的过期时间
Redis::setex('session:xxx', 3600, $data);     // 会话：1小时
Redis::setex('cache:xxx', 300, $data);        // 缓存：5分钟
Redis::setex('verify_code:xxx', 300, $code);  // 验证码：5分钟

// ❌ 不推荐：不设置过期时间（可能造成内存泄漏）
Redis::set('data', $value);
```

### 2. 使用有意义的键名

```php
// ✅ 推荐：使用冒号分隔的命名空间
Redis::set('session:abc123', $data);
Redis::set('user:1001:profile', $data);
Redis::set('cache:post:123', $data);

// ❌ 不推荐：无规则的键名
Redis::set('s1', $data);
Redis::set('user_profile_1001', $data);
```

### 3. 批量操作提高性能

```php
// ✅ 推荐：使用批量操作
Redis::mset(['key1' => 'val1', 'key2' => 'val2', 'key3' => 'val3']);
$values = Redis::mget(['key1', 'key2', 'key3']);

// ❌ 不推荐：多次单独操作
Redis::set('key1', 'val1');
Redis::set('key2', 'val2');
Redis::set('key3', 'val3');
```

### 4. 检查键是否存在

```php
// ✅ 推荐：使用 setnx 防止覆盖
if (Redis::setnx('lock:resource', '1')) {
    // 获取锁成功
}

// ❌ 不推荐：直接 set（可能覆盖重要数据）
Redis::set('lock:resource', '1');
```

### 5. 处理返回值

```php
// ✅ 推荐：检查返回值
$value = Redis::get('key');
if ($value === false) {
    // 键不存在
} else {
    // 使用 $value
}

// ❌ 不推荐：不检查返回值
$value = Redis::get('key');
echo $value; // 可能输出 false
```

---

## 注意事项

1. **连接池**：Redis 门面自动管理连接池，无需手动获取和释放连接
2. **返回值**：键不存在时 get() 返回 `false`，注意与空字符串的区别
3. **过期时间**：过期时间单位为秒
4. **数据序列化**：复杂数据需要 json_encode 后存储
5. **阻塞操作**：blPop/brPop 会阻塞协程，注意超时时间设置

---

## 配置说明

Redis 配置在 `config/redis.php` 或环境变量中：

```php
return [
    'host' => '127.0.0.1',
    'port' => 6379,
    'password' => '',
    'database' => 0,
    'pool' => [
        'min' => 5,    // 最小连接数
        'max' => 20,   // 最大连接数
    ]
];
```

---

## 扩展操作

如需使用门面未封装的 Redis 方法，可通过 getConn() 获取原生连接：

```php
$redis = Redis::getConn();

// 使用原生 Redis 方法
$redis->hSet('hash_key', 'field', 'value');
$redis->sAdd('set_key', 'member1', 'member2');
$redis->zAdd('zset_key', 100, 'member1');

// 回收连接
Redis::recycle($redis);
```

---

## 技术支持

- **文件路径**：`d:\SVN_OTHER\basic\api_swoole\library\Facades\Redis.php`
- **依赖**：Redis 扩展、Redis\RedisPoolConn 连接池
- **版本**：适配 Swoole 4.x+ 和 phpredis 扩展

---

© 2026 Swoole Framework Redis Facade
