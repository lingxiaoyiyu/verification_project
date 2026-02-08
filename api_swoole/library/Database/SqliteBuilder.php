<?php

namespace Library\Database;

use Library\Core\Config;
use Library\Core\ServerException;
use Library\Database\DbPoolConn;
use Library\Facades\Log;
use PDO;

/**
 * SQLite数据库查询构建器
 * 实现了SQLite兼容的查询构建逻辑
 */
class SqliteBuilder implements BuilderInterface
{
    /**
     * 构造函数
     */
    function __construct() {}

    // 数据库连接
    private $connection;
    // 事务状态标志
    private $inTransaction = false;
    // 查询字段
    private $columns = ['*'];
    // 表名
    private $tableName = "";
    // 别名
    private $alias = "";
    // 连接查询
    private $joins = [];
    // 查询条件
    private $wheres = [];
    // 排序条件
    private $orderByVar = [];
    private $offsetVar = '';
    private $limitVar = '';

    private $sqlData = []; // 数据
    private $updateData = [];
    private $groupByVar = "";
    // UNION查询
    private $unions = [];
    // 窗口函数定义
    private $windows = [];
    // 数据库连接池实例
    private static $pool;

    /**
     * 获取数据库连接
     *
     * @return void
     */
    private function getConn()
    {
        if (is_null(self::$pool)) {
            self::$pool = DbPoolConn::getInstance();
        }
        $this->connection = self::$pool->getConn();
        if (!$this->connection) {
            throw new ServerException('获取数据库连接失败 SqliteBuilder', Config::get("result.code.error"));
        }
    }

    /**
     * 设置表名
     *
     * @param string $tableName
     * @return self
     */
    public function from($tableName): self
    {
        $this->tableName = $tableName;
        $this->columns = ['*'];
        $this->wheres = [];
        $this->sqlData = [];
        $this->orderByVar = [];
        $this->offsetVar = '';
        $this->limitVar = '';
        $this->groupByVar = '';
        return $this;
    }

    /**
     * 别名
     *
     * @param string $alias 别名
     * @return self
     */
    public function alias($alias): self
    {
        $this->alias = $alias;
        return $this;
    }

    /**
     * 设置查询字段
     *
     * @param array $columns
     * @return self
     */
    public function columns(array $columns): self
    {
        $this->columns = $columns;
        return $this;
    }

    /**
     * 查询条件
     *
     * @param string $key 字段名
     * @param string $value 值
     * @param string $operator 运算符
     * @param string $type 条件类型
     * @return self
     */
    public function where($column, $operator = null, $value = null, $boolean = 'and'): self
    {
        if (!is_string($column)) {
            throw new ServerException('where方法第一个参数必须是字符串', Config::get("result.code.error"));
        }
        
        // 兼容旧版本：如果第二个参数不是操作符，则使用默认值
        if ($operator !== null && !in_array($operator, ['=', '>', '<', '>=', '<=', '<>', 'like'])) {
            $value = $operator;
            $operator = '=';
        }
        
        if (is_null($value)) {
            $this->wheres[] = ["$column IS NULL", $boolean];
            return $this;
        }
        if (is_array($value)) {
            $inSql = "";
            $whereInStr = "";
            foreach ($value as $key2 => $val) {
                $whereInStr .= "?";
                if ($key2 < count($value) - 1) {
                    $whereInStr .= ",";
                }
                $this->sqlData[] = $val;
            }
            $this->wheres[] = ["$column IN($whereInStr)", $boolean];
            return $this;
        }
        $this->sqlData[] = $value;
        $this->wheres[] = ["$column $operator ?", $boolean];
        return $this;
    }

    /**
     * 设置排序条件
     *
     * @param string $field 排序字段
     * @param string $direction 排序方向
     * @return self
     */
    public function orderBy($field, $direction = 'asc'): self
    {
        $this->orderByVar[] = [$field, $direction];
        return $this;
    }

    /**
     * 设置偏移量
     *
     * @param int $offset 偏移量
     * @return self
     */
    public function offset($offset): self
    {
        $this->offsetVar = $offset;
        return $this;
    }

    /**
     * 设置限制数量
     *
     * @param int $limit 限制数量
     * @return self
     */
    public function limit($limit): self
    {
        $this->limitVar = $limit;
        return $this;
    }

    /**
     * 执行查询
     *
     * @return array 查询结果
     */
    public function get(): array
    {
        $sql = $this->buildSelectSql();
        $this->getConn();
        $stmt = $this->connection->prepare($sql);
        if (!$stmt) {
            throw new ServerException('查询失败: ' . $sql, Config::get("result.code.error"));
        }
        $stmt->execute($this->sqlData);
        $result = $stmt->fetchAll(PDO::FETCH_ASSOC);
        DbPoolConn::getInstance()->releaseConn($this->connection);
        return $result;
    }

    /**
     * 获取单条记录
     *
     * @return array|null 单条记录
     */
    public function first(): ?array
    {
        $this->limit(1);
        $result = $this->get();
        return $result ? $result[0] : null;
    }

    /**
     * 插入数据
     *
     * @param array $data 插入数据
     * @return int 插入ID
     */
    public function insert(array $data): int
    {
        $sql = $this->buildInsertSql($data);
        $this->getConn();
        $stmt = $this->connection->prepare($sql);
        if (!$stmt) {
            throw new ServerException('插入失败: ' . $sql, Config::get("result.code.error"));
        }
        $stmt->execute($this->sqlData);
        $id = $this->connection->lastInsertId();
        DbPoolConn::getInstance()->releaseConn($this->connection);
        return $id;
    }

    /**
     * 更新数据
     *
     * @param array $data 更新数据
     * @return int 受影响行数
     */
    public function update(array $data): int
    {
        $sql = $this->buildUpdateSql($data);
        $this->getConn();
        $stmt = $this->connection->prepare($sql);
        if (!$stmt) {
            throw new ServerException('更新失败: ' . $sql, Config::get("result.code.error"));
        }
        $stmt->execute($this->sqlData);
        $rowCount = $stmt->rowCount();
        DbPoolConn::getInstance()->releaseConn($this->connection);
        return $rowCount;
    }

    /**
     * 删除数据
     *
     * @return int 受影响行数
     */
    public function delete(): int
    {
        $sql = $this->buildDeleteSql();
        $this->getConn();
        $stmt = $this->connection->prepare($sql);
        if (!$stmt) {
            throw new ServerException('删除失败: ' . $sql, Config::get("result.code.error"));
        }
        $stmt->execute($this->sqlData);
        $rowCount = $stmt->rowCount();
        DbPoolConn::getInstance()->releaseConn($this->connection);
        return $rowCount;
    }

    /**
     * 构建SELECT语句
     *
     * @return string
     */
    private function buildSelectSql(): string
    {
        $sql = "SELECT " . $this->buildColumnsSql() . " FROM " . $this->tableName;
        if (!empty($this->alias)) {
            $sql .= " AS " . $this->alias;
        }
        if (!empty($this->joins)) {
            $sql .= " " . implode(" ", $this->joins);
        }
        if (!empty($this->wheres)) {
            $sql .= " WHERE " . $this->buildWheresSql();
        }
        if (!empty($this->groupByVar)) {
            $sql .= " GROUP BY " . $this->groupByVar;
        }
        if (!empty($this->orderByVar)) {
            $sql .= " ORDER BY " . $this->buildOrderBySql();
        }
        if (!empty($this->limitVar)) {
            $sql .= " LIMIT " . $this->limitVar;
        }
        if (!empty($this->offsetVar)) {
            $sql .= " OFFSET " . $this->offsetVar;
        }
        return $sql;
    }

    /**
     * 构建列SQL
     *
     * @return string
     */
    private function buildColumnsSql(): string
    {
        return implode(", ", $this->columns);
    }

    /**
     * 构建WHERE条件SQL
     *
     * @return string
     */
    private function buildWheresSql(): string
    {
        $whereSql = "";
        foreach ($this->wheres as $key => $where) {
            if ($key > 0) {
                $whereSql .= " " . $where[1] . " ";
            }
            $whereSql .= $where[0];
        }
        return $whereSql;
    }

    /**
     * 构建ORDER BY SQL
     *
     * @return string
     */
    private function buildOrderBySql(): string
    {
        $orderBySql = "";
        foreach ($this->orderByVar as $key => $orderBy) {
            if ($key > 0) {
                $orderBySql .= ", ";
            }
            $orderBySql .= $orderBy[0] . " " . $orderBy[1];
        }
        return $orderBySql;
    }

    /**
     * 构建INSERT语句
     *
     * @param array $data 插入数据
     * @return string
     */
    private function buildInsertSql(array $data): string
    {
        $keys = implode(", ", array_keys($data));
        $values = str_repeat("?, ", count($data));
        $values = rtrim($values, ", ");
        $sql = "INSERT INTO " . $this->tableName . " (" . $keys . ") VALUES (" . $values . ")";
        $this->sqlData = array_values($data);
        return $sql;
    }

    /**
     * 构建UPDATE语句
     *
     * @param array $data 更新数据
     * @return string
     */
    private function buildUpdateSql(array $data): string
    {
        $updateSql = "";
        foreach ($data as $key => $value) {
            $updateSql .= $key . " = ?, ";
            $this->sqlData[] = $value;
        }
        $updateSql = rtrim($updateSql, ", ");
        $sql = "UPDATE " . $this->tableName . " SET " . $updateSql;
        if (!empty($this->wheres)) {
            $sql .= " WHERE " . $this->buildWheresSql();
        }
        return $sql;
    }

    /**
     * 构建DELETE语句
     *
     * @return string
     */
    private function buildDeleteSql(): string
    {
        $sql = "DELETE FROM " . $this->tableName;
        if (!empty($this->wheres)) {
            $sql .= " WHERE " . $this->buildWheresSql();
        }
        return $sql;
    }

    /**
     * 开始事务
     *
     * @return bool
     */
    public function beginTransaction(): bool
    {
        if ($this->inTransaction) {
            return false;
        }
        $this->inTransaction = true;
        $this->getConn();
        return $this->connection->beginTransaction();
    }

    /**
     * 提交事务
     *
     * @return bool
     */
    public function commit(): bool
    {
        if (!$this->inTransaction) {
            return false;
        }
        $this->inTransaction = false;
        $result = $this->connection->commit();
        DbPoolConn::getInstance()->releaseConn($this->connection);
        return $result;
    }

    /**
     * 回滚事务
     *
     * @return bool
     */
    public function rollBack(): bool
    {
        if (!$this->inTransaction) {
            return false;
        }
        $this->inTransaction = false;
        $result = $this->connection->rollBack();
        DbPoolConn::getInstance()->releaseConn($this->connection);
        return $result;
    }
}