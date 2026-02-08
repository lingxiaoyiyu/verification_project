# PSR-4 自动加载使用说明

## 一、概述

PSR-4（PHP Standard Recommendation 4）是 PHP-FIG 制定的自动加载标准，定义了从文件路径自动加载类文件的规范。Cups 框架采用 PSR-4 自动加载机制，简化了类的引入和使用。

### 1.1 PSR-4 核心规范

1. **命名空间与目录映射**：命名空间前缀对应基础目录
2. **类名与文件名**：类名与文件名保持一致
3. **下划线处理**：类名中的下划线无特殊含义
4. **大小写敏感**：命名空间、类名、文件名大小写敏感

### 1.2 命名空间映射规则

```
命名空间前缀 → 基础目录
App\         → app/
Library\     → library/
```

---

## 二、框架配置

### 2.1 Composer 配置

在 `composer.json` 中配置 PSR-4 自动加载：

```json
{
    "name": "cups/framework",
    "autoload": {
        "psr-4": {
            "App\\": "app/",
            "Library\\": "library/"
        }
    },
    "autoload-dev": {
        "psr-4": {
            "App\\Tests\\": "tests/"
        }
    }
}
```

### 2.2 生成自动加载文件

安装依赖后执行：

```bash
# 生成自动加载文件
composer dump-autoload

# 优化自动加载（生产环境）
composer dump-autoload --optimize

# 开发环境（自动更新）
composer dump-autoload --dev
```

---

## 三、目录结构规范

### 3.1 标准目录结构

```
api_swoole/
├── app/                          # App 命名空间根目录
│   ├── Base/                     # App\Base
│   │   └── BaseController.php    # App\Base\BaseController
│   ├── Controller/               # App\Controller
│   │   ├── UserController.php    # App\Controller\UserController
│   │   └── OrderController.php   # App\Controller\OrderController
│   ├── Service/                  # App\Service
│   │   ├── UserService.php       # App\Service\UserService
│   │   └── OrderService.php      # App\Service\OrderService
│   ├── Model/                    # App\Model
│   │   ├── User.php              # App\Model\User
│   │   └── Order.php             # App\Model\Order
│   └── Middleware/               # App\Middleware
│       └── AuthMiddleware.php    # App\Middleware\AuthMiddleware
├── library/                      # Library 命名空间根目录
│   ├── Cups.php                  # Library\Cups
│   ├── Core/                     # Library\Core
│   │   ├── Config.php            # Library\Core\Config
│   │   ├── Jwt.php               # Library\Core\Jwt
│   │   └── ServerException.php   # Library\Core\ServerException
│   ├── Database/                 # Library\Database
│   │   ├── MysqlBuilder.php      # Library\Database\MysqlBuilder
│   │   └── DbPoolConn.php        # Library\Database\DbPoolConn
│   ├── Facades/                  # Library\Facades
│   │   ├── DB.php                # Library\Facades\DB
│   │   ├── Request.php           # Library\Facades\Request
│   │   └── Response.php          # Library\Facades\Response
│   └── Log/                      # Library\Log
│       ├── Log.php               # Library\Log\Log
│       └── PsrLogger.php         # Library\Log\PsrLogger
├── vendor/                       # Composer 依赖
│   └── autoload.php              # 自动加载入口
└── composer.json                 # Composer 配置
```

### 3.2 命名空间与文件路径映射示例

| 完整类名 | 文件路径 |
|----------|----------|
| `App\Controller\UserController` | `app/Controller/UserController.php` |
| `App\Service\UserService` | `app/Service/UserService.php` |
| `App\Model\User` | `app/Model/User.php` |
| `Library\Core\Config` | `library/Core/Config.php` |
| `Library\Facades\DB` | `library/Facades/DB.php` |
| `Library\Database\MysqlBuilder` | `library/Database/MysqlBuilder.php` |

---

## 四、使用方法

### 4.1 基础使用

```php
<?php

// 引入自动加载文件
require_once __DIR__ . '/vendor/autoload.php';

// 直接使用类，自动加载会自动引入文件
use App\Controller\UserController;
use Library\Facades\DB;

// 实例化类
$controller = new UserController();

// 使用门面
$users = DB::table('users')->get();
```

### 4.2 在控制器中使用

```php
<?php

namespace App\Controller;

use App\Base\BaseController;
use App\Service\UserService;
use Library\Facades\Request;
use Library\Facades\Response;
use Library\Facades\Log;

class UserController extends BaseController {
    
    protected function check() {
        // 参数验证
    }
    
    protected function service() {
        // 使用 Service 类
        $userService = new UserService();
        $result = $userService->getUserList();
        
        Log::info('获取用户列表');
        
        return Response::success($result);
    }
}
```

### 4.3 在 Service 中使用

```php
<?php

namespace App\Service;

use App\Model\User;
use Library\Facades\DB;
use Library\Facades\Redis;

class UserService {
    
    public function getUserById($id) {
        // 使用 Model 类
        $user = User::find($id);
        
        // 或使用查询构建器
        $user = DB::table('users')->where('id', $id)->first();
        
        return $user;
    }
    
    public function getUserFromCache($id) {
        // 使用 Redis
        $cacheKey = "user:{$id}";
        $user = Redis::get($cacheKey);
        
        if (!$user) {
            $user = $this->getUserById($id);
            Redis::setex($cacheKey, 3600, json_encode($user));
        } else {
            $user = json_decode($user, true);
        }
        
        return $user;
    }
}
```

---

## 五、创建新类

### 5.1 创建控制器

1. 在 `app/Controller/` 目录创建文件
2. 文件名与类名一致
3. 添加正确的命名空间

```php
<?php
// 文件：app/Controller/ProductController.php

namespace App\Controller;

use App\Base\BaseController;
use Library\Facades\Response;

class ProductController extends BaseController {
    
    protected function check() {
        // 验证逻辑
    }
    
    protected function service() {
        return Response::success(['message' => 'Product Controller']);
    }
}
```

### 5.2 创建 Service

```php
<?php
// 文件：app/Service/ProductService.php

namespace App\Service;

use Library\Facades\DB;

class ProductService {
    
    public function getProductList($page, $pageSize) {
        return DB::table('products')
            ->where('status', 1)
            ->paginate($page, $pageSize);
    }
    
    public function getProductDetail($id) {
        return DB::table('products')->where('id', $id)->first();
    }
}
```

### 5.3 创建 Model

```php
<?php
// 文件：app/Model/Product.php

namespace App\Model;

use Library\Facades\DB;

class Product {
    
    public static function find($id) {
        return DB::table('products')->where('id', $id)->first();
    }
    
    public static function create($data) {
        return DB::table('products')->insertGetId($data);
    }
}
```

### 5.4 创建中间件

```php
<?php
// 文件：app/Middleware/RateLimitMiddleware.php

namespace App\Middleware;

use Library\Facades\Request;
use Library\Facades\Response;
use Library\Facades\Redis;

class RateLimitMiddleware {
    
    public function handle($request, $next) {
        $ip = Request::ip();
        $key = "rate_limit:{$ip}";
        
        $count = Redis::incr($key);
        if ($count === 1) {
            Redis::expire($key, 60); // 1分钟窗口
        }
        
        if ($count > 100) {
            return Response::fail('请求过于频繁', 429);
        }
        
        return $next($request);
    }
}
```

---

## 六、自动加载流程

### 6.1 加载流程图

```
代码中使用类
    ↓
PHP 检查类是否已加载
    ↓
未加载 → 调用自动加载器
    ↓
PSR-4 自动加载器解析命名空间
    ↓
根据映射规则确定文件路径
    ↓
加载文件
    ↓
类可用
```

### 6.2 加载示例

```php
use App\Controller\UserController;

$userController = new UserController();
```

加载过程：
1. PHP 发现 `UserController` 类未定义
2. 调用 Composer 自动加载器
3. 解析命名空间 `App\Controller\UserController`
4. 根据 PSR-4 映射：`App\` → `app/`
5. 确定文件路径：`app/Controller/UserController.php`
6. 加载文件
7. 类可用

---

## 七、最佳实践

### 7.1 命名规范

```php
<?php

namespace App\Controller\Admin;  // 命名空间使用大驼峰

// 类名使用大驼峰
class UserManagerController {    
    // 方法名使用小驼峰
    public function getUserList() {
        // 变量名使用小驼峰
        $userList = [];
    }
}
```

### 7.2 文件组织

```
app/
├── Controller/           # 控制器层
│   ├── Admin/           # 后台控制器子命名空间
│   │   └── DashboardController.php
│   └── Api/             # API 控制器子命名空间
│       └── UserController.php
├── Service/             # 业务逻辑层
│   ├── UserService.php
│   └── OrderService.php
├── Model/               # 数据模型层
│   ├── User.php
│   └── Order.php
└── Middleware/          # 中间件
    ├── AuthMiddleware.php
    └── CorsMiddleware.php
```

### 7.3 命名空间使用

```php
<?php

namespace App\Controller\Api;

// 使用 use 引入其他命名空间的类
use App\Base\BaseController;
use App\Service\UserService;
use Library\Facades\Request;
use Library\Facades\Response;

class UserController extends BaseController {
    
    public function index() {
        $service = new UserService();
        // ...
    }
}
```

---

## 八、常见问题

### 8.1 类未找到错误

**错误信息：**
```
Class 'App\Controller\UserController' not found
```

**可能原因：**
1. 文件路径与命名空间不匹配
2. 文件名与类名不一致
3. 未执行 `composer dump-autoload`
4. 大小写不匹配（Linux 系统敏感）

**解决方案：**
```bash
# 重新生成自动加载文件
composer dump-autoload

# 检查文件路径
# App\Controller\UserController → app/Controller/UserController.php
```

### 8.2 大小写问题

**错误示例：**
```php
// 文件：app/Controller/usercontroller.php (小写)
namespace App\Controller;

class UserController {} // 类名大写
```

**正确做法：**
```php
// 文件：app/Controller/UserController.php (大驼峰)
namespace App\Controller;

class UserController {} // 类名与文件名一致
```

### 8.3 命名空间声明错误

**错误示例：**
```php
<?php
// 缺少命名空间声明
class UserController {} // 错误：未声明命名空间
```

**正确做法：**
```php
<?php
namespace App\Controller; // 必须声明命名空间

class UserController {}
```

---

## 九、性能优化

### 9.1 生成优化映射

生产环境建议生成优化后的自动加载映射：

```bash
composer dump-autoload --optimize
```

这会生成一个类到文件的完整映射数组，避免文件系统查找，提高加载速度。

### 9.2 APCu 缓存

启用 APCu 缓存自动加载：

```bash
composer dump-autoload --optimize --apcu
```

### 9.3 预加载（PHP 7.4+）

配置 PHP 预加载：

```ini
; php.ini
opcache.preload=/path/to/preload.php
opcache.preload_user=www-data
```

preload.php:
```php
<?php
require_once '/path/to/vendor/autoload.php';
```

---

## 十、完整示例项目

### 10.1 项目结构

```
api_swoole/
├── app/
│   ├── Base/
│   │   └── BaseController.php
│   ├── Controller/
│   │   ├── UserController.php
│   │   └── OrderController.php
│   ├── Service/
│   │   ├── UserService.php
│   │   └── OrderService.php
│   ├── Model/
│   │   ├── User.php
│   │   └── Order.php
│   └── Middleware/
│       └── AuthMiddleware.php
├── library/
│   └── ...
├── vendor/
│   └── autoload.php
└── composer.json
```

### 10.2 代码示例

**BaseController.php:**
```php
<?php
namespace App\Base;

abstract class BaseController {
    abstract protected function check();
    abstract protected function service();
}
```

**UserController.php:**
```php
<?php
namespace App\Controller;

use App\Base\BaseController;
use App\Service\UserService;
use Library\Facades\Response;

class UserController extends BaseController {
    protected function check() {}
    
    protected function service() {
        $service = new UserService();
        return Response::success($service->getList());
    }
}
```

**UserService.php:**
```php
<?php
namespace App\Service;

use App\Model\User;

class UserService {
    public function getList() {
        return User::all();
    }
}
```

**User.php:**
```php
<?php
namespace App\Model;

use Library\Facades\DB;

class User {
    public static function all() {
        return DB::table('users')->get();
    }
}
```

---

## 十一、版本信息

- **PSR-4 版本**：PSR-4: Autoloader (PSR-4 规范)
- **框架版本**：v1.0.0
- **Composer 版本**：2.x
- **PHP 版本**：8.0+

---

© 2026 Cups Swoole Framework - PSR-4 Autoloading Implementation
