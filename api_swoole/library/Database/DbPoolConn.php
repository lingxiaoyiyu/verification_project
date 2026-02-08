<?php

namespace Library\Database;

use Library\Core\Config;
use Library\Core\ServerException;
use Library\Facades\Log;
use Swoole\Database\PDOConfig;
use Swoole\Database\PDOPool;

/**
 * 数据库连接池处理类
 */
class DbPoolConn {
    private static $instance;
    private $pool;
    private $dbType;
    private $isSQLite = false;

    /**
     * DbPoolConn constructor.
     */
    public function __construct() {
        $this->init();
    }

    /**
     *
     * @return DbPoolConn
     */
    public static function getInstance() {
        if (is_null(self::$instance)) {
            self::$instance = new self();
        }

        return self::$instance;
    }


    /**
     * 初始化连接
     *
     * @return $this
     * @throws \Exception
     */
    public function init()
    {
        try {
            // 获取数据库类型
            $dbType = Config::get('database.type', 'mysql');
            $this->dbType = $dbType;
            
            if ($dbType === 'sqlite') {
                // SQLite连接方式
                $dbPath = Config::get('database.dbname', './storage/database/database.sqlite');
                
                // 验证数据库文件路径
                if (!file_exists($dbPath)) {
                    throw new \Exception(sprintf('数据库文件不存在：%s', $dbPath));
                }
                
                $poolConnNum = Config::get('database.pool_conn_num', swoole_cpu_num() * 2);
                $this->isSQLite = true;
                
                Log::info(sprintf("正在初始化SQLite数据库连接池... 数据库: %s, 连接数: %d", $dbPath, $poolConnNum));
                
                // SQLite使用简单的连接池，使用PDO
                $this->pool = new \SplQueue();
                for ($i = 0; $i < $poolConnNum; $i++) {
                    $pdo = new \PDO("sqlite:" . $dbPath);
                    $pdo->setAttribute(\PDO::ATTR_ERRMODE, \PDO::ERRMODE_EXCEPTION);
                    $this->pool->enqueue($pdo);
                }
                
            } else {
                // MySQL连接方式
                $dbConfig = [
                    'host' => Config::get('database.dbhost', '127.0.0.1'),
                    'port' => Config::get('database.dbport', 3306),
                    'dbname' => Config::get('database.dbname', ''),
                    'charset' => Config::get('database.charset', 'utf8mb4'),
                    'username' => Config::get('database.dbuser', 'root'),
                    'password' => Config::get('database.dbpasswd', ''),
                    'pool_conn_num' => Config::get('database.pool_conn_num', swoole_cpu_num() * 2)
                ];
                
                // 验证必要的配置项
                $requiredConfig = ['host', 'port', 'dbname', 'username'];
                foreach ($requiredConfig as $key) {
                    if (empty($dbConfig[$key])) {
                        throw new \Exception(sprintf('MySQL数据库配置缺失：%s', $key));
                    }
                }
                
                // 验证端口是否为整数
                if (!is_numeric($dbConfig['port'])) {
                    throw new \Exception('数据库端口必须为数字');
                }
                $dbConfig['port'] = (int)$dbConfig['port'];
                
                Log::info(sprintf("正在初始化MySQL数据库连接池... 主机: %s, 端口: %d, 数据库: %s, 用户: %s, 连接数: %d", 
                    $dbConfig['host'], $dbConfig['port'], $dbConfig['dbname'], $dbConfig['username'], $dbConfig['pool_conn_num']));
                
                // 创建PDOPool实例
                $this->pool = new PDOPool(
                    (new PDOConfig())
                        ->withHost($dbConfig['host'])
                        ->withPort($dbConfig['port'])
                        ->withDbName($dbConfig['dbname'])
                        ->withCharset($dbConfig['charset'])
                        ->withUsername($dbConfig['username'])
                        ->withPassword($dbConfig['password']),
                    $dbConfig['pool_conn_num']
                );
                
                // 填充连接池
                Log::info("正在填充数据库连接池...");
                $this->pool->fill();
            }
            
            Log::info("数据库连接池初始化成功！");
            
            return $this;
        } catch (\Throwable $e) {
            Log::error("数据库连接池初始化失败：" . $e->getMessage());
            return $this;
        }
    }

    /**
     * 获取连接
     *
     * @param int $timeout
     * @return mixed
     */
    public function getConn()
    {
        try {
            if ($this->isSQLite) {
                $conn = $this->pool->dequeue();
                if (!$conn) {
                    throw new ServerException('获取数据库连接失败 DbPoolConn');
                }
                return $conn;
            } else {
                $conn = $this->pool->get(1);
                if (!$conn) {
                    throw new ServerException('获取数据库连接失败 DbPoolConn');
                }
                return $conn;
            }
        } catch (\Throwable $th) {
            throw $th;
        }
    }

    /**
     * 回收连接
     *
     * @param $conn
     */
    public function recycle($conn = null)
    {
        if ($conn) {
            if ($this->isSQLite) {
                // SQLite不需要回收,直接放回队列
                $this->pool->enqueue($conn);
            } else {
                // MySQL需要回收连接到连接池
                $this->pool->put($conn);
            }
        }
    }

    /**
     * 释放连接（别名方法）
     *
     * @param $conn
     */
    public function releaseConn($conn = null)
    {
        $this->recycle($conn);
    }

    /**
     * 获取PDOPool实例
     *
     * @return PDOPool
     */
    public function getPool() {
        return $this->pool;
    }
}
