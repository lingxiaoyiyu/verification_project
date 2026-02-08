<?php

namespace Library\Redis;

use Library\Core\Config;
use Library\Facades\Log;
use Swoole\Database\RedisConfig;
use Swoole\Database\RedisPool;

/**
 * Redis连接池处理类
 */
class RedisPoolConn {
    private static $instance;
    private $pool;

    /**
     * RedisPoolConn constructor.
     */
    public function __construct() {
        $this->init();
    }

    /**
     * 获取单例实例
     *
     * @return RedisPoolConn
     */
    public static function getInstance() {
        if (is_null(self::$instance)) {
            self::$instance = new self();
        }

        return self::$instance;
    }

    /**
     * 初始化Redis连接池
     *
     * @return $this
     */
    public function init() {
        try {
            // 检查必要的Redis配置
            $redisConfig = [
                'host' => Config::get('redis.host', '127.0.0.1'),
                'port' => Config::get('redis.port', 6379),
                'auth' => Config::get('redis.auth', ''),
                'dbIndex' => Config::get('redis.dbIndex', 0),
                'timeout' => Config::get('redis.timeout', 30),
                'pool_conn_num' => Config::get('redis.pool_conn_num', swoole_cpu_num() * 2)
            ];
            
            // 验证配置项是否存在
            $requiredConfig = ['host', 'port'];
            foreach ($requiredConfig as $key) {
                if (empty($redisConfig[$key])) {
                    throw new \Exception(sprintf('Redis配置缺失：%s', $key));
                }
            }
            
            // 验证端口是否为整数
            if (!is_numeric($redisConfig['port'])) {
                throw new \Exception('Redis端口必须为数字');
            }
            $redisConfig['port'] = (int)$redisConfig['port'];
            
            // 验证超时时间是否为数字
            if (!is_numeric($redisConfig['timeout'])) {
                throw new \Exception('Redis超时时间必须为数字');
            }
            $redisConfig['timeout'] = (float)$redisConfig['timeout'];
            
            // 验证数据库索引是否为数字
            if (!is_numeric($redisConfig['dbIndex'])) {
                throw new \Exception('Redis数据库索引必须为数字');
            }
            $redisConfig['dbIndex'] = (int)$redisConfig['dbIndex'];
            
            // 验证连接池连接数是否为数字
            if (!is_numeric($redisConfig['pool_conn_num'])) {
                throw new \Exception('Redis连接池连接数必须为数字111');
            }
            $redisConfig['pool_conn_num'] = (int)$redisConfig['pool_conn_num'];
            
            Log::info(sprintf("正在初始化Redis连接池... 主机: %s, 端口: %d, 数据库索引: %d, 连接数: %d", 
                $redisConfig['host'], $redisConfig['port'], $redisConfig['dbIndex'], $redisConfig['pool_conn_num']));
            
            // 创建RedisPool实例
            $this->pool = new RedisPool((new RedisConfig)
                ->withHost($redisConfig['host'])
                ->withPort($redisConfig['port'])
                ->withAuth((string)$redisConfig['auth'])
                ->withDbIndex((int)$redisConfig['dbIndex'])
                ->withTimeout((float)$redisConfig['timeout']),
                $redisConfig['pool_conn_num']
            );
            
            // 填充连接池
            Log::info("正在填充Redis连接池...");
            $this->pool->fill();
            
            Log::info("Redis连接池初始化成功！");
            
            return $this;
        } catch (\Throwable $e) {
            $errorMsg = sprintf('Redis连接池初始化失败：%s', $e->getMessage());
            Log::error($errorMsg);
            
            // 确保始终返回$this
            return $this;
        }
    }

    /**
     * 获取Redis连接
     *
     * @return mixed
     */
    public function getConn() {
        return $this->pool->get();
    }

    /**
     * 回收Redis连接
     *
     * @param $conn
     */
    public function recycle($conn) {
        if ($conn) {
            $this->pool->put($conn);
        }
    }

    /**
     * 获取RedisPool实例
     *
     * @return RedisPool
     */
    public function getPool() {
        return $this->pool;
    }
}
