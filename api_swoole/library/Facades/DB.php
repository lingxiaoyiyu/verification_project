<?php

namespace Library\Facades;

use Library\Database\BuilderInterface;
use Library\Database\BuilderFactory;
use Library\Context\Context;

require_once __DIR__ . '/../../vendor/autoload.php';

class DB {
    private static bool $initialized = false;
    // 事务上下文键名
    private const TRANSACTION_BUILDER_KEY = 'db_transaction_builder';

    public static function initialize(): void {
        if (self::$initialized) {
            return;
        }

        // 初始化数据库连接池
        \Library\Database\DbPoolConn::getInstance();
        
        self::$initialized = true;
    }

    /**
     * 执行原生SQL查询
     * 
     * @param string $sql SQL语句
     * @param array $bindings 参数绑定
     * @return array 查询结果
     */
    public static function select(string $sql, array $bindings = []) {
        $dbPool = \Library\Database\DbPoolConn::getInstance();
        $conn = $dbPool->getConn();
        
        $sth = $conn->prepare($sql);
        $sth->execute($bindings);
        $result = $sth->fetchAll(\PDO::FETCH_ASSOC);
        $sth->closeCursor();
        $sth = null;
        $dbPool->recycle($conn);
        
        return $result;
    }

    /**
     * 执行原生SQL语句（更新、删除等）
     * 
     * @param string $sql SQL语句
     * @param array $bindings 参数绑定
     * @return int 影响的行数
     */
    public static function statement(string $sql, array $bindings = []) {
        $dbPool = \Library\Database\DbPoolConn::getInstance();
        $conn = $dbPool->getConn();
        
        $sth = $conn->prepare($sql);
        $sth->execute($bindings);
        $rowCount = $sth->rowCount();
        $sth->closeCursor();
        $sth = null;
        $dbPool->recycle($conn);
        
        return $rowCount;
    }

    /**
     * 执行原生INSERT语句
     * 
     * @param string $sql SQL语句
     * @param array $bindings 参数绑定
     * @return int 插入的ID
     */
    public static function insert(string $sql, array $bindings = []) {
        $dbPool = \Library\Database\DbPoolConn::getInstance();
        $conn = $dbPool->getConn();
        
        $sth = $conn->prepare($sql);
        $sth->execute($bindings);
        $insertId = $conn->lastInsertId();
        $sth->closeCursor();
        $sth = null;
        $dbPool->recycle($conn);
        
        return $insertId;
    }

    /**
     * 设置表名
     *
     * @param String $tableName 表名
     * @return BuilderInterface 查询构建器实例
     */
    public static function table(String $tableName) : BuilderInterface {
        // 修复：每次都创建新实例，避免复用导致的状态污染
        $builder = BuilderFactory::getBuilder();
        $builder->from($tableName);
        return $builder;
    }

    /**
     * 获取builder对象
     *
     * @return BuilderInterface 查询构建器实例
     */
    public static function bulider() : BuilderInterface {
        // 修复：每次都创建新实例，避免复用导致的状态污染
        return BuilderFactory::getBuilder();
    }

    /**
     * 获取当前事务的Builder实例
     * 
     * @return BuilderInterface|null 查询构建器实例或null
     */
    private static function getTransactionBuilder(): ?BuilderInterface {
        return Context::get(self::TRANSACTION_BUILDER_KEY);
    }

    /**
     * 设置当前事务的Builder实例
     * 
     * @param BuilderInterface $builder 查询构建器实例
     * @return void
     */
    private static function setTransactionBuilder(BuilderInterface $builder): void {
        Context::set(self::TRANSACTION_BUILDER_KEY, $builder);
    }

    /**
     * 清除当前事务的Builder实例
     * 
     * @return void
     */
    private static function clearTransactionBuilder(): void {
        Context::delete(self::TRANSACTION_BUILDER_KEY);
    }

    /**
     * 开始事务管理
     */
    public static function beginTransaction():void{
        // 如果已经存在事务Builder，直接返回
        if (self::getTransactionBuilder() !== null) {
            return;
        }
        
        $builder = BuilderFactory::getBuilder();
        $builder->beginTransaction();
        self::setTransactionBuilder($builder);
    }

    /**
     * 提交事务
     */
    public static function commit():void{
        $builder = self::getTransactionBuilder();
        if ($builder !== null) {
            $builder->commit();
            self::clearTransactionBuilder();
        }
    }

    /**
     * 事务回滚
     */
    public static function rollBack(): void {
        $builder = self::getTransactionBuilder();
        if ($builder !== null) {
            $builder->rollBack();
            self::clearTransactionBuilder();
        }
    }

    /**
     * 回收数据库连接
     */
    public static function recycle(): void {
        $builder = self::getTransactionBuilder();
        if ($builder !== null) {
            $builder->rollBack();
            self::clearTransactionBuilder();
        }
    }
}