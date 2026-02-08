# DB 门面使用说明

## 简介

DB 门面类提供了便捷的数据库操作方法，基于 PDO 连接池封装，支持原生 SQL 查询和查询构建器，自动管理数据库连接的获取和回收。

## 基本用法

```php
use Library\Facades\DB;

// 查询数据
$users = DB::table('users')->where('status', 1)->get();

// 原生 SQL 查询
$users = DB::select('SELECT * FROM users WHERE status = ?', [1]);

// 插入数据
$userId = DB::table('users')->insertGetId([
    'username' => 'john',
    'email' => 'john@example.com'
]);
```

## 方法列表

### 一、初始化

#### initialize()
初始化数据库连接池

```php
// 在 Server 启动时调用（框架自动调用）
DB::initialize();
```

---

### 二、原生 SQL 查询

#### select()
执行原生 SELECT 查询

```php
// 基础查询
$users = DB::select('SELECT * FROM users');

// 带参数绑定
$users = DB::select('SELECT * FROM users WHERE status = ?', [1]);

// 多个参数
$users = DB::select(
    'SELECT * FROM users WHERE status = ? AND age > ?',
    [1, 18]
);

// 命名参数
$users = DB::select(
    'SELECT * FROM users WHERE username = :username',
    [':username' => 'john']
);

// 返回：数组（关联数组格式）
```

#### statement()
执行原生 UPDATE/DELETE 等语句

```php
// 更新数据
$affected = DB::statement(
    'UPDATE users SET status = ? WHERE id = ?',
    [1, 123]
);

// 删除数据
$affected = DB::statement(
    'DELETE FROM users WHERE id = ?',
    [123]
);

// 返回：影响的行数
```

#### insert()
执行原生 INSERT 语句

```php
// 插入单条数据
$insertId = DB::insert(
    'INSERT INTO users (username, email) VALUES (?, ?)',
    ['john', 'john@example.com']
);

// 插入多条数据（使用批量插入语法）
$insertId = DB::insert(
    'INSERT INTO users (username, email) VALUES (?, ?), (?, ?)',
    ['john', 'john@example.com', 'jane', 'jane@example.com']
);

// 返回：插入的 ID（最后插入的 ID）
```

---

### 三、查询构建器

#### table()
设置表名，返回 Builder 对象

```php
// 基础用法
$builder = DB::table('users');

// 链式调用
$users = DB::table('users')
    ->where('status', 1)
    ->orderBy('id', 'DESC')
    ->limit(10)
    ->get();
```

#### builder()
获取空的 Builder 对象

```php
$builder = DB::builder();
$builder->from('users')->where('id', 1)->first();
```

---

### 四、查询构建器方法（通过 table() 返回的 Builder 对象调用）

#### 查询数据

##### get()
获取所有结果

```php
// 获取所有用户
$users = DB::table('users')->get();

// 带条件
$users = DB::table('users')
    ->where('status', 1)
    ->get();

// 指定字段
$users = DB::table('users')
    ->select(['id', 'username', 'email'])
    ->get();
```

##### first()
获取第一条结果

```php
$user = DB::table('users')->where('id', 1)->first();

// 如果不存在返回 null
if ($user) {
    echo $user['username'];
}
```

##### count()
获取记录总数

```php
$total = DB::table('users')->count();

// 带条件
$activeUsers = DB::table('users')->where('status', 1)->count();
```

##### value()
获取单个字段值

```php
$username = DB::table('users')->where('id', 1)->value('username');
$email = DB::table('users')->where('id', 1)->value('email');
```

##### exists()
判断记录是否存在

```php
$exists = DB::table('users')->where('username', 'john')->exists();
if ($exists) {
    // 用户名已存在
}
```

#### 条件查询

##### where()
添加 WHERE 条件

```php
// 等于
DB::table('users')->where('id', 1)->get();

// 自定义操作符
DB::table('users')->where('age', '>', 18)->get();
DB::table('users')->where('age', '>=', 18)->get();
DB::table('users')->where('age', '<', 60)->get();
DB::table('users')->where('age', '<=', 60)->get();
DB::table('users')->where('username', 'like', '%john%')->get();
DB::table('users')->where('email', 'IS NOT', null)->get();

// 多个条件（AND）
DB::table('users')
    ->where('status', 1)
    ->where('age', '>', 18)
    ->get();
```

##### orWhere()
添加 OR 条件

```php
DB::table('users')
    ->where('status', 1)
    ->orWhere('is_admin', 1)
    ->get();
```

##### whereIn()
WHERE IN 条件

```php
DB::table('users')
    ->whereIn('id', [1, 2, 3, 4, 5])
    ->get();
```

##### whereNotIn()
WHERE NOT IN 条件

```php
DB::table('users')
    ->whereNotIn('status', [0, 2])
    ->get();
```

##### whereBetween()
BETWEEN 条件

```php
DB::table('users')
    ->whereBetween('age', [18, 60])
    ->get();
```

##### whereNull()
IS NULL 条件

```php
DB::table('users')
    ->whereNull('deleted_at')
    ->get();
```

##### whereNotNull()
IS NOT NULL 条件

```php
DB::table('users')
    ->whereNotNull('email')
    ->get();
```

#### 排序和限制

##### orderBy()
排序

```php
// 升序
DB::table('users')->orderBy('id', 'ASC')->get();

// 降序
DB::table('users')->orderBy('created_at', 'DESC')->get();

// 多个排序
DB::table('users')
    ->orderBy('status', 'DESC')
    ->orderBy('id', 'ASC')
    ->get();
```

##### limit()
限制数量

```php
DB::table('users')->limit(10)->get();
```

##### offset()
跳过记录

```php
// 跳过前 10 条，获取接下来的 10 条
DB::table('users')
    ->offset(10)
    ->limit(10)
    ->get();
```

#### 聚合函数

##### sum()
求和

```php
$totalAmount = DB::table('orders')->sum('amount');
$totalAmount = DB::table('orders')->where('status', 1)->sum('amount');
```

##### avg()
平均值

```php
$avgAge = DB::table('users')->avg('age');
```

##### max()
最大值

```php
$maxAge = DB::table('users')->max('age');
```

##### min()
最小值

```php
$minAge = DB::table('users')->min('age');
```

##### countDistinct()
去重计数

```php
// 统计不同的邮箱数量
$distinctEmails = DB::table('users')->countDistinct('email');
```

#### UNION/UNION ALL查询

##### union()
合并查询结果，去除重复行

```php
$activeUsers = DB::table('users')->where('status', 1);
$adminUsers = DB::table('users')->where('is_admin', 1);

// 合并查询结果
$users = $activeUsers->union($adminUsers)->get();
```

##### unionAll()
合并查询结果，保留重复行

```php
$users1 = DB::table('users')->where('age', '<', 18);
$users2 = DB::table('users')->where('age', '>=', 18);

// 合并查询结果，保留重复行
$allUsers = $users1->unionAll($users2)->get();
```

#### 批量操作

##### batchInsert()
批量插入数据

```php
// 批量插入多条用户数据
DB::table('users')->batchInsert([
    ['username' => 'user1', 'email' => 'user1@example.com'],
    ['username' => 'user2', 'email' => 'user2@example.com'],
    ['username' => 'user3', 'email' => 'user3@example.com']
]);
```

##### batchUpdate()
批量更新数据

```php
// 批量更新多个用户的状态
DB::table('users')->batchUpdate([
    ['id' => 1, 'status' => 1, 'updated_at' => date('Y-m-d H:i:s')],
    ['id' => 2, 'status' => 1, 'updated_at' => date('Y-m-d H:i:s')],
    ['id' => 3, 'status' => 0, 'updated_at' => date('Y-m-d H:i:s')]
]);
```

##### batchDelete()
批量删除数据

```php
// 批量删除多个用户
DB::table('users')->batchDelete([1, 2, 3]);

// 指定主键字段
DB::table('users')->batchDelete([1001, 1002, 1003], 'user_id');
```

#### 子查询

##### whereSub()
子查询WHERE条件

```php
// 查找订单数量大于10的用户
$subQuery = DB::table('orders')
    ->select(['user_id', 'COUNT(*) as order_count'])
    ->groupBy('user_id')
    ->having('order_count', '>', 10);

$users = DB::table('users')
    ->whereSub('id', 'IN', $subQuery)
    ->get();
```

##### inSub()
子查询IN条件

```php
// 查找有订单的用户
$subQuery = DB::table('orders')->select(['user_id']);
$users = DB::table('users')->inSub('id', $subQuery)->get();
```

##### fromSub()
子查询作为数据源

```php
// 子查询作为表
$subQuery = DB::table('orders')
    ->select(['user_id', 'SUM(amount) as total_amount'])
    ->groupBy('user_id');

$userStats = DB::table($subQuery, 'user_stats')
    ->where('total_amount', '>', 1000)
    ->get();
```

#### 窗口函数

##### rank()
RANK()窗口函数，排名可能有间隔

```php
// 按金额排名订单
$orders = DB::table('orders')
    ->select(['id', 'user_id', 'amount', DB::raw('RANK() over (order by amount DESC) as rank')])
    ->get();

// 使用rank()方法
$orders = DB::table('orders')
    ->select(['id', 'user_id', 'amount'])
    ->rank('order_rank', '', 'amount DESC')
    ->get();
```

##### denseRank()
DENSE_RANK()窗口函数，排名连续

```php
// 按金额排名订单，连续排名
$orders = DB::table('orders')
    ->select(['id', 'user_id', 'amount'])
    ->denseRank('dense_rank', '', 'amount DESC')
    ->get();
```

##### rowNumber()
ROW_NUMBER()窗口函数，行号

```php
// 为每个用户的订单分配行号
$orders = DB::table('orders')
    ->select(['id', 'user_id', 'amount'])
    ->rowNumber('row_num', 'user_id', 'created_at DESC')
    ->get();
```

##### window()
定义窗口

```php
// 定义窗口并使用
$orders = DB::table('orders')
    ->select(['id', 'user_id', 'amount', DB::raw('SUM(amount) over (partition by user_id) as total')])
    ->window('w', 'user_id', 'created_at DESC')
    ->get();
```

#### JSON操作

##### whereJsonContains()
JSON包含查询

```php
// 查询包含指定标签的文章
$articles = DB::table('articles')
    ->whereJsonContains('tags', 'php')
    ->get();

// 多级JSON查询
$users = DB::table('users')
    ->whereJsonContains('profile->skills', 'javascript')
    ->get();
```

##### whereJsonLength()
JSON长度查询

```php
// 查询至少有3个标签的文章
$articles = DB::table('articles')
    ->whereJsonLength('tags', '>=', 3)
    ->get();
```

##### whereJsonExtract()
JSON提取查询

```php
// 查询profile->age大于18的用户
$users = DB::table('users')
    ->whereJsonExtract('profile->age', '>', 18)
    ->get();
```

##### whereJsonSearch()
JSON搜索查询

```php
// 查询skills中包含"php"的用户
$users = DB::table('users')
    ->whereJsonSearch('profile->skills', 'php')
    ->get();
```

#### 分组

##### groupBy()
分组

```php
$stats = DB::table('orders')
    ->select(['user_id', 'COUNT(*) as total'])
    ->groupBy('user_id')
    ->get();
```

##### having()
HAVING 条件

```php
$users = DB::table('orders')
    ->select(['user_id', 'COUNT(*) as order_count'])
    ->groupBy('user_id')
    ->having('order_count', '>', 5)
    ->get();
```

#### 连接查询

##### join()
INNER JOIN

```php
$data = DB::table('users')
    ->join('orders', 'users.id', '=', 'orders.user_id')
    ->select(['users.*', 'orders.amount'])
    ->get();
```

##### leftJoin()
LEFT JOIN

```php
$data = DB::table('users')
    ->leftJoin('orders', 'users.id', '=', 'orders.user_id')
    ->select(['users.*', 'orders.amount'])
    ->get();
```

##### rightJoin()
RIGHT JOIN

```php
$data = DB::table('users')
    ->rightJoin('orders', 'users.id', '=', 'orders.user_id')
    ->select(['users.*', 'orders.amount'])
    ->get();
```

##### crossJoin()
CROSS JOIN

```php
// 笛卡尔积查询
$data = DB::table('users')
    ->crossJoin('roles')
    ->select(['users.username', 'roles.name'])
    ->get();
```

#### 插入数据

##### insert()
插入单条或多条数据

```php
// 插入单条
DB::table('users')->insert([
    'username' => 'john',
    'email' => 'john@example.com',
    'created_at' => date('Y-m-d H:i:s')
]);

// 插入多条
DB::table('users')->insert([
    ['username' => 'john', 'email' => 'john@example.com'],
    ['username' => 'jane', 'email' => 'jane@example.com']
]);
```

##### insertGetId()
插入数据并返回 ID

```php
$userId = DB::table('users')->insertGetId([
    'username' => 'john',
    'email' => 'john@example.com'
]);

echo "新用户ID: {$userId}";
```

#### 更新数据

##### update()
更新数据

```php
// 基础更新
$affected = DB::table('users')
    ->where('id', 1)
    ->update([
        'username' => 'john_new',
        'updated_at' => date('Y-m-d H:i:s')
    ]);

// 批量更新
$affected = DB::table('users')
    ->where('status', 0)
    ->update(['status' => 1]);
```

##### increment()
字段自增

```php
// 自增 1
DB::table('users')->where('id', 1)->increment('login_count');

// 自增指定值
DB::table('users')->where('id', 1)->increment('points', 10);

// 自增同时更新其他字段
DB::table('users')
    ->where('id', 1)
    ->increment('login_count', 1, ['last_login' => date('Y-m-d H:i:s')]);
```

##### decrement()
字段自减

```php
// 自减 1
DB::table('users')->where('id', 1)->decrement('balance');

// 自减指定值
DB::table('users')->where('id', 1)->decrement('balance', 100);
```

#### 删除数据

##### delete()
删除数据

```php
// 删除指定记录
$affected = DB::table('users')->where('id', 1)->delete();

// 批量删除
$affected = DB::table('users')->where('status', 0)->delete();
```

---

### 五、事务管理

#### beginTransaction()
开始事务

```php
DB::beginTransaction();
```

#### commit()
提交事务

```php
DB::commit();
```

#### rollBack()
回滚事务

```php
DB::rollBack();
```

#### 事务使用示例

```php
try {
    DB::beginTransaction();
    
    // 扣减余额
    DB::table('users')
        ->where('id', 1)
        ->decrement('balance', 100);
    
    // 创建订单
    $orderId = DB::table('orders')->insertGetId([
        'user_id' => 1,
        'amount' => 100,
        'created_at' => date('Y-m-d H:i:s')
    ]);
    
    DB::commit();
    
    Response::success(['order_id' => $orderId], '下单成功');
    
} catch (\Exception $e) {
    DB::rollBack();
    Log::exception($e);
    Response::fail('下单失败');
}
```

---

## Domain层使用说明

### 一、Domain层简介

Domain层是业务逻辑层，负责封装数据库操作和业务规则。通过继承`Library\Domain\BaseDomain`类，可以快速创建针对特定表的Domain类，简化数据库操作。

### 二、创建Domain类

#### 1. 基础结构

```php
<?php

namespace App\Modules\Sys\Domain;

use Library\Domain\BaseDomain;

class UserDomain extends BaseDomain
{
    // 可选：如果表名与类名转换结果不一致，可以手动指定
    protected $table = 'users';
}
```

#### 2. 自动表名

Domain类会自动根据类名生成表名：
- `User` → `user`
- `UserRole` → `user_role`
- `OrderItem` → `order_item`

### 三、使用Domain类

#### 1. 静态调用

```php
use App\Modules\Sys\Domain\UserDomain;

// 查询单个用户
$user = UserDomain::find(1);

// 查询多个用户
$users = UserDomain::where('status', 1)->get();

// 创建用户
$userId = UserDomain::create([
    'username' => 'test',
    'email' => 'test@example.com'
]);

// 更新用户
UserDomain::updateById(1, ['status' => 0]);

// 删除用户
UserDomain::deleteById(1);
```

#### 2. 实例化调用

```php
use App\Modules\Sys\Domain\UserDomain;

$userDomain = UserDomain::getInstance();

// 查询用户
$user = $userDomain->where('id', 1)->first();

// 链式调用
$users = $userDomain->where('status', 1)
    ->orderBy('created_at', 'desc')
    ->limit(10)
    ->get();
```

#### 3. 自定义方法

```php
<?php

namespace App\Modules\Sys\Domain;

use Library\Domain\BaseDomain;

class UserDomain extends BaseDomain
{
    /**
     * 获取活跃用户列表
     * 
     * @param int $limit 数量限制
     * @return array
     */
    public static function getActiveUsers($limit = 10)
    {
        return static::where('status', 1)
            ->orderBy('last_login', 'desc')
            ->limit($limit)
            ->get();
    }
}

// 使用自定义方法
$activeUsers = UserDomain::getActiveUsers(20);
```

### 四、Domain层优势

1. **代码复用**：封装常用数据库操作，避免重复代码
2. **业务集中**：将业务逻辑集中在Domain层，便于维护
3. **类型安全**：通过类方法调用，提供更好的代码提示
4. **易于扩展**：可以在Domain层添加自定义业务方法
5. **简化调用**：支持静态调用，减少代码量

## 完整示例

### 示例 1：用户列表（分页）

```php
public function getUserList() {
    $page = Request::get('page', 1);
    $pageSize = Request::get('page_size', 20);
    $keyword = Request::get('keyword', '');
    $status = Request::get('status');
    
    // 构建查询
    $query = DB::table('users');
    
    // 关键词搜索
    if ($keyword) {
        $query->where('username', 'like', "%{$keyword}%");
    }
    
    // 状态筛选
    if ($status !== null) {
        $query->where('status', $status);
    }
    
    // 查询总数
    $total = $query->count();
    
    // 查询列表
    $list = $query
        ->select(['id', 'username', 'email', 'status', 'created_at'])
        ->orderBy('id', 'DESC')
        ->offset(($page - 1) * $pageSize)
        ->limit($pageSize)
        ->get();
    
    Response::paginate($list, $total);
}
```

### 示例 2：用户注册

```php
public function register() {
    try {
        // 验证参数
        Request::validate([
            'username' => ['text' => '用户名', 'rules' => ['required', ['min_length' => 3]]],
            'email' => ['text' => '邮箱', 'rules' => ['required', 'email']],
            'password' => ['text' => '密码', 'rules' => ['required', ['min_length' => 6]]],
        ]);
        
        $username = Request::post('username');
        $email = Request::post('email');
        $password = Request::post('password');
        
        // 检查用户名是否存在
        $exists = DB::table('users')
            ->where('username', $username)
            ->exists();
        
        if ($exists) {
            Response::fail('用户名已存在', 400);
            return;
        }
        
        // 开始事务
        DB::beginTransaction();
        
        // 创建用户
        $userId = DB::table('users')->insertGetId([
            'username' => $username,
            'email' => $email,
            'password' => password_hash($password, PASSWORD_DEFAULT),
            'status' => 1,
            'created_at' => date('Y-m-d H:i:s')
        ]);
        
        // 创建用户资料
        DB::table('user_profiles')->insert([
            'user_id' => $userId,
            'nickname' => $username,
            'created_at' => date('Y-m-d H:i:s')
        ]);
        
        // 提交事务
        DB::commit();
        
        Response::success(['user_id' => $userId], '注册成功');
        
    } catch (\Library\Core\ServerException $e) {
        DB::rollBack();
        Response::fail($e->getMessage(), $e->getCode());
    } catch (\Exception $e) {
        DB::rollBack();
        Log::exception($e);
        Response::serverError('注册失败');
    }
}
```

### 示例 3：订单统计

```php
public function getOrderStats() {
    $startDate = Request::get('start_date');
    $endDate = Request::get('end_date');
    
    // 日销售统计
    $dailyStats = DB::table('orders')
        ->select([
            'DATE(created_at) as date',
            'COUNT(*) as order_count',
            'SUM(amount) as total_amount'
        ])
        ->whereBetween('created_at', [$startDate, $endDate])
        ->where('status', 1)
        ->groupBy('DATE(created_at)')
        ->orderBy('date', 'ASC')
        ->get();
    
    // 总统计
    $totalStats = DB::table('orders')
        ->whereBetween('created_at', [$startDate, $endDate])
        ->where('status', 1)
        ->first([
            'COUNT(*) as total_orders',
            'SUM(amount) as total_amount',
            'AVG(amount) as avg_amount',
            'MAX(amount) as max_amount'
        ]);
    
    Response::success([
        'daily' => $dailyStats,
        'total' => $totalStats
    ]);
}
```

### 示例 4：复杂查询（关联）

```php
public function getUserOrders() {
    $userId = Request::get('user_id');
    
    // 查询用户及其订单
    $orders = DB::table('orders')
        ->leftJoin('users', 'orders.user_id', '=', 'users.id')
        ->leftJoin('products', 'orders.product_id', '=', 'products.id')
        ->where('orders.user_id', $userId)
        ->select([
            'orders.*',
            'users.username',
            'users.email',
            'products.name as product_name',
            'products.price as product_price'
        ])
        ->orderBy('orders.created_at', 'DESC')
        ->get();
    
    Response::success($orders);
}
```

### 示例 5：批量操作

```php
public function batchUpdateStatus() {
    $ids = Request::post('ids'); // [1, 2, 3, 4, 5]
    $status = Request::post('status');
    
    try {
        DB::beginTransaction();
        
        // 批量更新
        $affected = DB::table('users')
            ->whereIn('id', $ids)
            ->update([
                'status' => $status,
                'updated_at' => date('Y-m-d H:i:s')
            ]);
        
        // 记录日志
        foreach ($ids as $id) {
            DB::table('user_logs')->insert([
                'user_id' => $id,
                'action' => 'status_change',
                'old_value' => '', // 这里可以先查询旧值
                'new_value' => $status,
                'created_at' => date('Y-m-d H:i:s')
            ]);
        }
        
        DB::commit();
        
        Response::success(['affected' => $affected], '批量更新成功');
        
    } catch (\Exception $e) {
        DB::rollBack();
        Log::exception($e);
        Response::fail('批量更新失败');
    }
}
```

---

## 最佳实践

### 1. 使用参数绑定防止 SQL 注入

```php
// ✅ 推荐：使用参数绑定
$users = DB::select('SELECT * FROM users WHERE username = ?', [$username]);

// ❌ 不推荐：直接拼接（SQL 注入风险）
$users = DB::select("SELECT * FROM users WHERE username = '{$username}'");
```

### 2. 使用查询构建器

```php
// ✅ 推荐：使用查询构建器
$users = DB::table('users')
    ->where('status', 1)
    ->orderBy('id', 'DESC')
    ->limit(10)
    ->get();

// ❌ 不推荐：手写复杂 SQL（易出错）
$users = DB::select('SELECT * FROM users WHERE status = 1 ORDER BY id DESC LIMIT 10');
```

### 3. 事务处理异常

```php
// ✅ 推荐：使用 try-catch 处理事务
try {
    DB::beginTransaction();
    // 操作
    DB::commit();
} catch (\Exception $e) {
    DB::rollBack();
    // 错误处理
}

// ❌ 不推荐：不处理异常
DB::beginTransaction();
// 操作（可能抛出异常）
DB::commit();
```

### 4. 选择必要的字段

```php
// ✅ 推荐：只选择需要的字段
$users = DB::table('users')
    ->select(['id', 'username', 'email'])
    ->get();

// ❌ 不推荐：查询所有字段
$users = DB::table('users')->get();
```

### 5. 分页查询

```php
// ✅ 推荐：使用 offset 和 limit
$users = DB::table('users')
    ->offset(($page - 1) * $pageSize)
    ->limit($pageSize)
    ->get();

// ❌ 不推荐：查询所有数据再切片
$users = DB::table('users')->get();
$users = array_slice($users, ($page - 1) * $pageSize, $pageSize);
```

---

## 注意事项

1. **连接池**：DB 门面自动管理连接池，无需手动获取和释放连接
2. **事务嵌套**：不支持事务嵌套，请在业务层面控制
3. **查询结果**：查询返回关联数组，不是对象
4. **参数绑定**：推荐使用 `?` 占位符或命名参数 `:name`
5. **性能优化**：复杂查询建议使用原生 SQL + 参数绑定

---

## 配置说明

数据库配置在 `config/database.php` 或环境变量中：

```php
return [
    'host' => '127.0.0.1',
    'port' => 3306,
    'database' => 'test',
    'username' => 'root',
    'password' => '',
    'charset' => 'utf8mb4',
    'pool' => [
        'min' => 5,    // 最小连接数
        'max' => 20,   // 最大连接数
    ]
];
```

---

## 技术支持

- **文件路径**：`d:\SVN_OTHER\basic\api_swoole\library\Facades\DB.php`
- **依赖**：PDO、Database\Builder 类、Database\DbPoolConn 连接池
- **版本**：适配 Swoole 4.x+

---

© 2026 Swoole Framework DB Facade
