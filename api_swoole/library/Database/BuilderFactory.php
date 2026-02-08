<?php

namespace Library\Database;

use Library\Core\Config;
use Library\Core\ServerException;

/**
 * 查询构建器工厂类
 * 根据配置的数据库类型返回相应的查询构建器实例
 */
class BuilderFactory
{
    /**
     * 获取查询构建器实例
     *
     * @return BuilderInterface 查询构建器实例
     * @throws ServerException 如果数据库类型不支持
     */
    public static function getBuilder(): BuilderInterface
    {
        // 获取数据库配置
        $dbConfig = Config::get('database', []);
        $dbType = $dbConfig['type'] ?? 'mysql';
        
        // 根据数据库类型返回相应的查询构建器实例
        switch ($dbType) {
            case 'mysql':
                return new MysqlBuilder();
            case 'sqlite':
                return new SqliteBuilder();
            default:
                throw new ServerException('不支持的数据库类型: ' . $dbType, Config::get("result.code.error"));
        }
    }
}