<?php

namespace Library\Database;

use Library\Core\Config;
use Library\Core\ServerException;
use Library\Database\DbPoolConn;
use Library\Facades\Log;

/**
 * 数据库连接处理类
 *
 */
class MysqlBuilder implements BuilderInterface
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
            throw new ServerException('获取数据库连接失败 Builder', Config::get("result.code.error"));
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
     * 重置查询条件
     * 修复：确保清理连接和事务状态
     * 
     * @return Builder
     */
    public function reset()
    {
        // 强制回收连接（即使在事务中也要清理）
        if ($this->connection) {
            // 如果还在事务中，先回滚
            if ($this->inTransaction) {
                try {
                    $this->connection->rollBack();
                } catch (\Exception $e) {
                    // 忽略回滚错误
                }
            }
            // 回收连接
            if (self::$pool) {
                self::$pool->recycle($this->connection);
            }
            $this->connection = null;
        }
        
        // 重置事务状态
        $this->inTransaction = false;
        
        // 修复：强制清空所有查询条件，使用unset再重新初始化
        unset($this->columns);
        unset($this->wheres);
        unset($this->sqlData);
        unset($this->orderByVar);
        unset($this->joins);
        unset($this->unions);
        unset($this->windows);
        unset($this->updateData);
        
        // 重新初始化所有属性
        $this->columns = ['*'];
        $this->wheres = [];
        $this->sqlData = [];
        $this->orderByVar = [];
        $this->offsetVar = '';
        $this->limitVar = '';
        $this->groupByVar = '';
        $this->joins = [];
        $this->alias = '';
        $this->unions = [];
        $this->windows = [];
        $this->updateData = [];
        
        return $this;
    }

    /**
     * 通用连接方法
     * 
     * @param string $type 连接类型
     * @param string $table 表名
     * @param string $first 第一个字段
     * @param string $operator 操作符
     * @param string $second 第二个字段
     * @return Builder
     */
    public function join($type, $table, $first, $operator, $second): self
    {
        $this->joins[] = [$type, $table, $first, $operator, $second];
        return $this;
    }

    /**
     * 左连接
     * 
     * @param string $table 表名
     * @param string $first 第一个字段
     * @param string $operator 操作符
     * @param string $second 第二个字段
     * @return Builder
     */
    public function leftJoin($table, $first, $operator, $second): self
    {
        return $this->join('left', $table, $first, $operator, $second);
    }

    /**
     * 右连接
     * 
     * @param string $table 表名
     * @param string $first 第一个字段
     * @param string $operator 操作符
     * @param string $second 第二个字段
     * @return Builder
     */
    public function rightJoin($table, $first, $operator, $second): self
    {
        return $this->join('right', $table, $first, $operator, $second);
    }

    /**
     * 内连接
     * 
     * @param string $table 表名
     * @param string $first 第一个字段
     * @param string $operator 操作符
     * @param string $second 第二个字段
     * @return Builder
     */
    public function innerJoin($table, $first, $operator, $second): self
    {
        return $this->join('inner', $table, $first, $operator, $second);
    }

    /**
     * 交叉连接
     * 
     * @param string $table 表名
     * @return Builder
     */
    public function crossJoin($table): self
    {
        $this->joins[] = ['cross', $table, '', '', ''];
        return $this;
    }

    /**
     * Undocumented function
     *
     * @param string $column 分组字段
     * @param string $sort 
     * @return builder对象
     */
    public function groupBy($column): self
    {
        $this->groupByVar = $column;
        return $this;
    }

    /**
     * 开始事务管理
     */
    public function beginTransaction()
    {
        $this->getConn();
        if ($this->connection) {
            $this->connection->beginTransaction();
            $this->inTransaction = true;
        }
    }

    /**
     * 提交事务
     */
    public function commit()
    {
        if ($this->connection) {
            $this->connection->commit();
            $this->inTransaction = false;
            $this->recycle();
        }
    }

    /**
     * 事务回滚
     */
    public function rollBack()
    {
        if ($this->connection) {
            $this->connection->rollBack();
            $this->inTransaction = false;
            $this->recycle();
        }
    }

    /**
     * 数据库连接返还到连接池
     *
     * @return void
     */
    public function recycle()
    {
        // 事务内不回收连接
        if ($this->connection && !$this->inTransaction) {
            self::$pool->recycle($this->connection);
            $this->connection = null;
        }
    }

    /**
     * 获取多条数据
     *
     * @return array|void
     */
    private function getMultiple($columns)
    {
        if ($columns) {
            $this->setColums($columns);
        } else {
            if (count($this->columns) == 1 && $this->columns[0] == 'count(*) as cnt') {
                $this->setColums(['*']);
            }
        }

        $this->getConn();
        $sth = '';
        try {
            $sql = $this->createSql('select');
            
            // 记录SQL日志
            try {
                $finalSql = $this->replacePlaceholders($sql, $this->sqlData);
                Log::debug("SQL执行: {$finalSql}");
            } catch (\Exception $e) {
                // 日志记录失败不影响业务流程
            }

            $this->connection->setAttribute(\PDO::ATTR_EMULATE_PREPARES, false);
            $sth = $this->connection->prepare($sql);
            
            // 修复：使用临时变量存储参数，避免引用传递导致的问题
            $params = $this->sqlData;
            if ($params) {
                foreach ($params as $key => $value) {
                    // 使用bindValue代替bindParam，避免引用问题
                    $sth->bindValue($key + 1, $value);
                }
            }
            
            $sth->execute();
            $result = $sth->fetchAll(\PDO::FETCH_ASSOC);
            $sth->closeCursor();
            $sth = null;
            $this->recycle();

            if ($result) {
                foreach ($result as $key => $value) {
                    $result[$key] = $this->camelCase($value);
                }
            } else {
                $result = [];
            }
            $this->reset();
            return $result;
        } catch (\PDOException $e) {
            $this->recycle();
            $this->reset();
            throw $e;
        } finally {
            if ($sth) {
                $sth->closeCursor();
                $sth = null;
            }
        }
    }

    /**
     * 获取一条数据
     *
     * @return array|void
     */
    private function getOne($columns = null)
    {
        if ($columns) {
            $this->setColums($columns);
        }

        $sth = '';
        try {
            $this->getConn();
            $sql = $this->createSql('select');
            
            // 记录SQL日志
            try {
                $finalSql = $this->replacePlaceholders($sql, $this->sqlData);
                Log::debug("SQL执行: {$finalSql}");
            } catch (\Exception $e) {
                // 日志记录失败不影响业务流程
            }
            
            $sth = $this->connection->prepare($sql);
            
            // 修复：使用临时变量存储参数，避免引用传递导致的问题
            $params = $this->sqlData;
            if ($params) {
                foreach ($params as $key => $value) {
                    // 使用bindValue代替bindParam，避免引用问题
                    $sth->bindValue($key + 1, $value);
                }
            }
            
            $sth->execute();
            $result = $sth->fetch(\PDO::FETCH_ASSOC);
            $sth->closeCursor();
            $sth = null;
            $this->recycle();
            if ($result) {
                $result = $this->camelCase($result);
            } else {
                $result = [];
            }
            $this->reset();
            return $result;
        } catch (\PDOException $e) {
            $this->recycle();
            $this->reset();
            throw $e;
        } finally {
            if ($sth) {
                $sth->closeCursor();
                $sth = null;
            }
        }
    }

    /**
     * 获取多条数据
     * 
     * @return array|void
     */
    public function get($columns = null)
    {
        return $this->getMultiple($columns) ?? [];
    }

    /**
     * 获取第一条数据
     * 
     * @return array|void
     */
    public function first()
    {
        return $this->getOne();
    }

    /**
     * 查询单个字段单条数据
     *
     * @param [type] $column
     * @return string|int|float|void
     */
    public function value($column)
    {
        $result = $this->getOne($column);
        if ($result) {
            $column = $this->convertUnderline($column);
            return $result[$column];
        } else {
            return '';
        }
    }

    /**
     * 查询单个字段多条数据
     *
     * @param [type] $column
     * @return array:void
     */
    public function pluck($column)
    {
        $result = $this->getMultiple($column);
        if ($result) {
            return array_column($result, $this->convertUnderline($column));
        } else {
            return [];
        }
    }

    /**
     * 是否存在
     *
     * @return boolean true：存在，false：不存在
     */
    public function exists(): bool
    {
        $result = $this->getMultiple(['*']);
        return $result ? true : false;
    }

    /**
     * 是否不存在
     *
     * @return boolean true：不存在，false：存在
     */
    public function doesntExist(): bool
    {
        $result = $this->getMultiple(['*']);
        return $result ? false : true;
    }

    /**
     * 插入数据
     *
     * @param array $data
     * @return boolean
     */
    public function insert($data): bool
    {
        $this->sqlData = $data;
        $sql = $this->createSql('insert');
        $this->execSql($sql);
        return true;
    }

    /**
     * 插入输入，返回新插入数据的ID
     * 
     * @param array $data
     * @return int
     */
    public function insertGetId($data): int
    {
        $this->sqlData = $data;
        $sql = $this->createSql('insert');
        $this->getConn();
        $sth = '';
        $id = 0;
        try {
            // 记录SQL日志
            try {
                $finalSql = $this->replacePlaceholders($sql, $this->sqlData);
                Log::debug("SQL执行: {$finalSql}");
            } catch (\Exception $e) {
                // 日志记录失败不影响业务流程
            }
            
            $sth = $this->connection->prepare($sql);
            
            // 修复：使用临时变量存储参数，避免引用传递导致的问题
            $params = $this->sqlData;
            if ($params) {
                foreach ($params as $key => $value) {
                    // 使用bindValue代替bindParam，避免引用问题
                    $sth->bindValue($key + 1, $value);
                }
            }
            
            $sth->execute();
            $id = $this->connection->lastInsertId();
            $sth->closeCursor();
            $sth = null;
            $this->recycle();
        } catch (\PDOException $e) {
            $this->recycle();
            Log::debug("SQL执行失败: " . $e->getMessage());
            // throw $e;
        } finally {
            if ($sth) {
                $sth->closeCursor();
                $sth = null;
            }
        }
        return $id;
    }
    /**
     * 更新数据
     *
     * @param array $data
     * @return boolean
     */
    public function update($data): string
    {
        $this->updateData = $data;
        $sql = $this->createSql('update');
        $this->execSql($sql);
        return true;
    }

    /**
     * 删除数据
     *
     * @return boolean
     */
    public function delete(): bool
    {
        $sql = $this->createSql('delete');
        $this->execSql($sql);
        return true;
    }

    /**
     * 批量插入数据
     *
     * @param array $data 要插入的数据，格式：[['field1' => value1, 'field2' => value2], ...]
     * @return boolean
     */
    public function batchInsert(array $data): bool
    {
        if (empty($data)) {
            return true;
        }
        
        $this->sqlData = [];
        $columns = array_keys(reset($data));
        $values = [];
        
        foreach ($data as $row) {
            $rowValues = [];
            foreach ($columns as $column) {
                $rowValues[] = '?';
                $this->sqlData[] = $row[$column] ?? null;
            }
            $values[] = '(' . implode(', ', $rowValues) . ')';
        }
        
        $columnsStr = implode(', ', array_map(function ($col) {
            return '`' . $col . '`';
        }, $columns));
        
        $sql = "INSERT INTO `{$this->tableName}` ($columnsStr) VALUES " . implode(', ', $values);
        $this->execSql($sql);
        return true;
    }

    /**
     * 批量更新数据
     *
     * @param array $data 要更新的数据，格式：[['id' => 1, 'field1' => value1], ['id' => 2, 'field2' => value2]]
     * @param string $primaryKey 主键字段名，默认为'id'
     * @return boolean
     */
    public function batchUpdate(array $data, string $primaryKey = 'id'): bool
    {
        if (empty($data)) {
            return true;
        }
        
        $sqls = [];
        $allSqlData = [];
        
        foreach ($data as $row) {
            if (!isset($row[$primaryKey])) {
                continue;
            }
            
            $id = $row[$primaryKey];
            unset($row[$primaryKey]);
            
            if (empty($row)) {
                continue;
            }
            
            $updateFields = [];
            $rowData = [];
            
            foreach ($row as $field => $value) {
                $updateFields[] = "`$field` = ?";
                $rowData[] = $value;
            }
            
            $rowData[] = $id;
            $allSqlData = array_merge($allSqlData, $rowData);
            
            $sqls[] = "UPDATE `{$this->tableName}` SET " . implode(', ', $updateFields) . " WHERE `$primaryKey` = ?";
        }
        
        if (empty($sqls)) {
            return true;
        }
        
        $this->sqlData = $allSqlData;
        $sql = implode('; ', $sqls);
        $this->execSql($sql);
        return true;
    }

    /**
     * 批量删除数据
     *
     * @param array $ids 要删除的主键值数组
     * @param string $primaryKey 主键字段名，默认为'id'
     * @return boolean
     */
    public function batchDelete(array $ids, string $primaryKey = 'id'): bool
    {
        if (empty($ids)) {
            return true;
        }
        
        $placeholders = implode(',', array_fill(0, count($ids), '?'));
        $this->sqlData = $ids;
        
        $sql = "DELETE FROM `{$this->tableName}` WHERE `$primaryKey` IN ($placeholders)";
        $this->execSql($sql);
        return true;
    }

    /**
     * 执行insert、update语句
     *
     * @param string $sql
     * @return void
     */
    private function execSql($sql)
    {
        $this->getConn();
        $sth = '';
        try {
            // 记录SQL日志
            try {
                $finalSql = $this->replacePlaceholders($sql, $this->sqlData);
                Log::debug("SQL执行: {$finalSql}");
            } catch (\Exception $e) {
                // 日志记录失败不影响业务流程
            }
            
            $sth = $this->connection->prepare($sql);

            // 修复：使用临时变量存储参数，避免引用传递导致的问题
            $params = $this->sqlData;
            if ($params) {
                foreach ($params as $key => $value) {
                    // 使用bindValue代替bindParam，避免引用问题
                    $sth->bindValue($key + 1, $value);
                }
            }
            
            $sth->execute();
            $sth->closeCursor();
            $sth = null;
            $this->recycle();
        } catch (\PDOException $e) {
            $this->recycle();
            throw $e;
        } finally {
            if ($sth) {
                $sth->closeCursor();
                $sth = null;
            }
        }
    }

    /**
     * 设置需要查询的字段
     *
     * @return Builder
     */
    public function select(): self
    {
        $numArgs = func_num_args(); // 获取参数数量
        $columns = array();
        for ($i = 0; $i < $numArgs; $i++) {
            $columns[] = func_get_arg($i); // 获取第 $i 个参数的值
        }
        if ($columns) {
            $this->setColums($columns);
        }
        return $this;
    }
    
    /**
     * 设置需要查询的字段（select的别名）
     *
     * @return Builder
     */
    public function field(): self
    {
        $numArgs = func_num_args(); // 获取参数数量
        $columns = array();
        for ($i = 0; $i < $numArgs; $i++) {
            $columns[] = func_get_arg($i); // 获取第 $i 个参数的值
        }
        if ($columns) {
            $this->setColums($columns);
        }
        return $this;
    }


    /**
     * 查询指定字段最大值
     * 
     * @param string $field 查询字段
     * @return int|float|null
     */
    public function max($field): int|float|null
    {
        $data = $this->getOne("max(" . $field . ")");
        if ($data && is_array($data)) {
            return array_values($data)[0];
        }
    }

    /**
     * 查询指定字段最小值
     * 
     * @param string $field 查询字段
     * @return int|float|null
     */
    public function min($field): int|float|null
    {
        $data = $this->getOne("min(" . $field . ")");
        if ($data && is_array($data)) {
            return array_values($data)[0];
        }
    }

    /**
     * 查询指定字段平均值
     * 
     * @param string $field 查询字段
     * @return int|float|null
     */
    public function avg($field): int|float|null
    {
        $data = $this->getOne("avg(" . $field . ")");
        if ($data && is_array($data)) {
            return array_values($data)[0];
        }
    }

    /**
     * 查询指定字段总和
     * 
     * @param string $field 查询字段
     * @return int|float|null
     */
    public function sum($field): int|float|null
    {
        $data = $this->getOne("sum(" . $field . ")");
        if ($data && is_array($data)) {
            return array_values($data)[0];
        }
    }

    /**
     * 查询指定字段去重计数
     * 
     * @param string $field 查询字段
     * @return int|null
     */
    public function countDistinct($field): int|null
    {
        $data = $this->getOne("count(distinct " . $field . ")");
        if ($data && is_array($data)) {
            return array_values($data)[0];
        }
    }

    /**
     * 查询条件
     *
     * @param  \Closure|string|array  $column
     * @param  mixed  $operator
     * @param  mixed  $value
     * @param  string  $boolean
     * @return Builder
     */
    public function where($column, $operator = null, $value = null, $boolean = 'and'): self
    {
        $conditions = [];
        if (\is_array($column)) {
            if (\is_array($column[0])) {
                foreach ($column as $key => $value) {
                    if (count($value) == 2) {
                        $conditions[] = [$value[0], '=', $value[1], $boolean];
                    } else if (count($value) == 3) {
                        $conditions[] = [$value[0], $value[1], $value[2], $boolean];
                    }
                }
            } else {
                if (count($column) == 2) {
                    $conditions[] = [$column[0], '=', $column[1], $boolean];
                } else if (\count($column) == 3) {
                    $conditions[] = [$column[0], $column[1], $column[2], $boolean];
                }
            }
        } else {
            if ($operator !== null) {
                if (\in_array($operator, ['=', '>', '<', '=', '>=', '<=', '<>', 'like'])) {
                    $conditions[] = [$column, $operator, $value, $boolean];
                } else {
                    $conditions[] = [$column, '=', $operator, $boolean];
                }
            }
        }
        if ($conditions) {
            foreach ($conditions as $key => $value) {
                if (\is_numeric($value[2])) {
                    $this->wheres[] = [$this->nameFormat($value[0]) . $value[1] . ' ?', $value[3]];
                } else {
                    $this->wheres[] = [$this->nameFormat($value[0]) . $value[1] . " ?", $value[3]];
                }
                $this->sqlData[] = $value[2];
            }
        }
        return $this;
    }

    /**
     * 查询，不等于
     *
     * @param [type] $column
     * @param [type] $value
     * @param string $boolean
     * @return Builder
     */
    public function whereNot($column, $value = null, $boolean = 'and'): self
    {
        $this->where($column, '<>', $value, $boolean);
        return $this;
    }

    /**
     * in 查询
     *
     * @param [type] $column
     * @param [type] $arrData
     * @return Builder
     */
    public function whereIn($column, $arrData): self
    {
        if (is_array($arrData)) {
            $condition = [];
            foreach ($arrData as $key => $value) {
                $condition[] = '?';
                $this->sqlData[] = $value;
            }
            $this->wheres[] = [$column . ' in (' . implode(',', $condition) . ')', 'and'];
        } else {
            $this->wheres[] = [$column . ' in (?)', 'and'];
            $this->sqlData[] = $arrData;
        }
        return $this;
    }

    /**
     * 子查询IN条件
     *
     * @param string $column 字段名
     * @param Builder $query 子查询Builder实例
     * @return Builder
     */
    public function inSub($column, Builder $query): self
    {
        $subSql = $query->createSql('select');
        $this->wheres[] = [$column . ' in (' . $subSql . ')', 'and'];
        $this->sqlData = array_merge($this->sqlData, $query->sqlData);
        return $this;
    }

    /**
     * 子查询WHERE条件
     *
     * @param string $column 字段名或比较表达式
     * @param string $operator 操作符
     * @param Builder $query 子查询Builder实例
     * @param string $boolean 逻辑运算符
     * @return Builder
     */
    public function whereSub($column, $operator, Builder $query, $boolean = 'and'): self
    {
        $subSql = $query->createSql('select');
        $condition = $column . ' ' . $operator . ' (' . $subSql . ')';
        $this->wheres[] = [$condition, $boolean];
        $this->sqlData = array_merge($this->sqlData, $query->sqlData);
        return $this;
    }

    /**
     * 子查询作为数据源
     *
     * @param Builder $query 子查询Builder实例
     * @param string $alias 别名
     * @return Builder
     */
    public function fromSub(Builder $query, $alias): self
    {
        $this->tableName = '(' . $query->createSql('select') . ') as ' . $alias;
        $this->sqlData = array_merge($this->sqlData, $query->sqlData);
        return $this;
    }

    /**
     * json查询条件
     * 修复：使用参数绑定防止SQL注入
     *
     * @return Builder
     */
    public function whereJsonContains($key, $value): self
    {
        $condition = "";

        // 添加参数到sqlData数组中，使用参数绑定
        if (is_array($value)) {
            $params = $value;
        } else {
            $params = [$value];
        }
        $this->sqlData[] = json_encode($params);

        // 判断$key是否包含"->"，以确定是多级查询还是一级查询
        if (strpos($key, "->") !== false) {
            // 多级查询，使用JSON_EXTRACT函数
            $keys = explode("->", $key);
            $condition = "JSON_CONTAINS(JSON_EXTRACT(`$keys[0]`, '$keys[1]'), ?)";
        } else {
            // 一级查询，使用JSON_CONTAINS函数
            $condition = "JSON_CONTAINS(`$key`, ?)";
        }

        $this->wheres[] = [$condition, 'and'];
        return $this;
    }

    /**
     * JSON长度查询条件
     *
     * @param string $key JSON字段
     * @param mixed $operator 操作符
     * @param int $length 长度值
     * @return Builder
     */
    public function whereJsonLength($key, $operator, $length): self
    {
        if (strpos($key, "->") !== false) {
            $keys = explode("->", $key);
            $condition = "JSON_LENGTH(JSON_EXTRACT(`$keys[0]`, '$keys[1]')) $operator ?";
        } else {
            $condition = "JSON_LENGTH(`$key`) $operator ?";
        }
        
        $this->wheres[] = [$condition, 'and'];
        $this->sqlData[] = $length;
        return $this;
    }

    /**
     * JSON提取查询条件
     *
     * @param string $key JSON字段路径
     * @param mixed $operator 操作符
     * @param mixed $value 比较值
     * @return Builder
     */
    public function whereJsonExtract($key, $operator, $value): self
    {
        if (strpos($key, "->") !== false) {
            $keys = explode("->", $key);
            $condition = "JSON_EXTRACT(`$keys[0]`, '$keys[1]') $operator ?";
        } else {
            $condition = "JSON_EXTRACT(`$key`, '$') $operator ?";
        }
        
        $this->wheres[] = [$condition, 'and'];
        $this->sqlData[] = $value;
        return $this;
    }

    /**
     * JSON搜索查询条件
     *
     * @param string $key JSON字段
     * @param mixed $value 搜索值
     * @param string $path 路径（可选）
     * @return Builder
     */
    public function whereJsonSearch($key, $value, $path = null): self
    {
        if (strpos($key, "->") !== false) {
            $keys = explode("->", $key);
            $jsonField = "JSON_EXTRACT(`$keys[0]`, '$keys[1]')";
        } else {
            $jsonField = "`$key`";
        }
        
        $condition = "JSON_SEARCH($jsonField, 'one', ?";
        if ($path) {
            $condition .= ", '$path'";
        }
        $condition .= ") IS NOT NULL";
        
        $this->wheres[] = [$condition, 'and'];
        $this->sqlData[] = $value;
        return $this;
    }

    /**
     * 统计数量
     *
     * @return int
     */
    public function count($column = null): int
    {
        if ($column) {
            $result = $this->getOne('count(' . $column . ') as cnt');
        } else {
            $result = $this->getOne('count(*) as cnt');
        }

        if ($result) {
            return $result['cnt'];
        } else {
            return 0;
        }
    }

    public function offset($offset): self
    {
        $this->offsetVar = $offset;
        return $this;
    }

    public function limit($limit): self
    {
        $this->limitVar = $limit;
        return $this;
    }

    public function inRandomOrder(): self
    {
        $this->orderByVar[] = ' RAND()';

        return $this;
    }

    /**
     * 排序
     *
     * @param [type] $column
     * @param string $order asc、desc
     * @return object
     */
    public function orderBy($column, $order = 'asc'): self
    {
        $this->orderByVar[] = $this->nameFormat($column) . ' ' . $order;
        return $this;
    }

    /**
     * 倒序排列
     *
     * @param [type] $column
     * @return object
     */
    public function orderByDesc($column): self
    {
        $this->orderByVar[] = $this->nameFormat($column) . ' desc';
        return $this;
    }

    /**
     * 排序（orderBy别名）
     *
     * @param [type] $column
     * @param string $order asc、desc
     * @return object
     */
    public function order($column, $order = 'asc'): self
    {
        return $this->orderBy($column, $order);
    }

    /**
     * 分页查询
     *
     * @param int $page 当前页码
     * @param int $limit 每页数量
     * @return array 查询结果数组
     */
    public function paginate($page = 1, $limit = 10): array
    {
        // 确保页码和每页数量为正数
        $page = max(1, (int)$page);
        $limit = max(1, (int)$limit);
        
        // 计算偏移量
        $offset = ($page - 1) * $limit;
        
        // 设置偏移量和限制
        $this->offset($offset)->limit($limit);
        
        // 直接返回查询结果数组
        return $this->get();
    }

    /**
     * UNION查询
     *
     * @param Builder $query 要合并的查询
     * @return Builder
     */
    public function union(Builder $query): self
    {
        $this->unions[] = ['union', $query];
        return $this;
    }

    /**
     * UNION ALL查询
     *
     * @param Builder $query 要合并的查询
     * @return Builder
     */
    public function unionAll(Builder $query): self
    {
        $this->unions[] = ['union all', $query];
        return $this;
    }

    /**
     * 定义窗口函数
     *
     * @param string $name 窗口名
     * @param string $partitionBy 分区字段
     * @param string $orderBy 排序字段
     * @return Builder
     */
    public function window($name, $partitionBy = '', $orderBy = ''): self
    {
        $window = 'window ' . $name . ' as (';
        if ($partitionBy) {
            $window .= ' partition by ' . $partitionBy;
        }
        if ($orderBy) {
            $window .= ' order by ' . $orderBy;
        }
        $window .= ')';
        $this->windows[] = $window;
        return $this;
    }

    /**
     * RANK()窗口函数
     *
     * @param string $column 别名
     * @param string $partitionBy 分区字段
     * @param string $orderBy 排序字段
     * @return Builder
     */
    public function rank($column = 'rank', $partitionBy = '', $orderBy = ''): self
    {
        $windowExpr = 'rank()';
        if ($partitionBy || $orderBy) {
            $windowExpr .= ' over (';
            if ($partitionBy) {
                $windowExpr .= ' partition by ' . $partitionBy;
            }
            if ($orderBy) {
                $windowExpr .= ' order by ' . $orderBy;
            }
            $windowExpr .= ')';
        }
        $this->columns[] = $windowExpr . ' as ' . $column;
        return $this;
    }

    /**
     * DENSE_RANK()窗口函数
     *
     * @param string $column 别名
     * @param string $partitionBy 分区字段
     * @param string $orderBy 排序字段
     * @return Builder
     */
    public function denseRank($column = 'dense_rank', $partitionBy = '', $orderBy = ''): self
    {
        $windowExpr = 'dense_rank()';
        if ($partitionBy || $orderBy) {
            $windowExpr .= ' over (';
            if ($partitionBy) {
                $windowExpr .= ' partition by ' . $partitionBy;
            }
            if ($orderBy) {
                $windowExpr .= ' order by ' . $orderBy;
            }
            $windowExpr .= ')';
        }
        $this->columns[] = $windowExpr . ' as ' . $column;
        return $this;
    }

    /**
     * ROW_NUMBER()窗口函数
     *
     * @param string $column 别名
     * @param string $partitionBy 分区字段
     * @param string $orderBy 排序字段
     * @return Builder
     */
    public function rowNumber($column = 'row_number', $partitionBy = '', $orderBy = ''): self
    {
        $windowExpr = 'row_number()';
        if ($partitionBy || $orderBy) {
            $windowExpr .= ' over (';
            if ($partitionBy) {
                $windowExpr .= ' partition by ' . $partitionBy;
            }
            if ($orderBy) {
                $windowExpr .= ' order by ' . $orderBy;
            }
            $windowExpr .= ')';
        }
        $this->columns[] = $windowExpr . ' as ' . $column;
        return $this;
    }

    /**
     * NTILE()窗口函数
     *
     * @param int $num 分桶数量
     * @param string $column 别名
     * @param string $partitionBy 分区字段
     * @param string $orderBy 排序字段
     * @return Builder
     */
    public function ntile($num, $column = 'ntile', $partitionBy = '', $orderBy = ''): self
    {
        $windowExpr = 'ntile(' . $num . ')';
        if ($partitionBy || $orderBy) {
            $windowExpr .= ' over (';
            if ($partitionBy) {
                $windowExpr .= ' partition by ' . $partitionBy;
            }
            if ($orderBy) {
                $windowExpr .= ' order by ' . $orderBy;
            }
            $windowExpr .= ')';
        }
        $this->columns[] = $windowExpr . ' as ' . $column;
        return $this;
    }

    /**
     * LAG()窗口函数
     *
     * @param string $field 字段名
     * @param int $offset 偏移量
     * @param mixed $default 默认值
     * @param string $column 别名
     * @param string $partitionBy 分区字段
     * @param string $orderBy 排序字段
     * @return Builder
     */
    public function lag($field, $offset = 1, $default = null, $column = 'lag', $partitionBy = '', $orderBy = ''): self
    {
        $windowExpr = 'lag(' . $field . ', ' . $offset;
        if ($default !== null) {
            $windowExpr .= ', ' . (is_string($default) ? "'$default'" : $default);
        }
        $windowExpr .= ')';
        
        if ($partitionBy || $orderBy) {
            $windowExpr .= ' over (';
            if ($partitionBy) {
                $windowExpr .= ' partition by ' . $partitionBy;
            }
            if ($orderBy) {
                $windowExpr .= ' order by ' . $orderBy;
            }
            $windowExpr .= ')';
        }
        $this->columns[] = $windowExpr . ' as ' . $column;
        return $this;
    }

    /**
     * LEAD()窗口函数
     *
     * @param string $field 字段名
     * @param int $offset 偏移量
     * @param mixed $default 默认值
     * @param string $column 别名
     * @param string $partitionBy 分区字段
     * @param string $orderBy 排序字段
     * @return Builder
     */
    public function lead($field, $offset = 1, $default = null, $column = 'lead', $partitionBy = '', $orderBy = ''): self
    {
        $windowExpr = 'lead(' . $field . ', ' . $offset;
        if ($default !== null) {
            $windowExpr .= ', ' . (is_string($default) ? "'$default'" : $default);
        }
        $windowExpr .= ')';
        
        if ($partitionBy || $orderBy) {
            $windowExpr .= ' over (';
            if ($partitionBy) {
                $windowExpr .= ' partition by ' . $partitionBy;
            }
            if ($orderBy) {
                $windowExpr .= ' order by ' . $orderBy;
            }
            $windowExpr .= ')';
        }
        $this->columns[] = $windowExpr . ' as ' . $column;
        return $this;
    }

    /**
     * 设置字段名
     *
     * @param $columns 字段名
     */
    private function setColums($columns)
    {
        if ($columns) {
            if (\is_array($columns)) {
                $this->columns = $columns;
            } else {
                $this->columns = [$columns];
            }
        } else {
            if ($this->alias) {
                $this->columns = [$this->alias . '.*'];
            } else {
                $this->columns = ['*'];
            }
        }
    }

    /**
     * 格式化字段
     * 
     * @param string $column 字段名
     * @return string
     */
    private function nameFormat($column): string
    {
        if (stripos($column, 'any_value(') === FALSE && stripos($column, 'count(') === FALSE && stripos($column, 'max(') === FALSE && stripos($column, '*') === FALSE) {
            if (stripos($column, '.') !== FALSE) {
                $column = str_replace('.', '.`', $column);
                if (stripos($column, ' as') !== FALSE) {
                    $column = str_replace(' as', '` as', $column);
                } else {
                    $column = $column . '`';
                }
            } else {
                if (stripos($column, ' as') !== FALSE) {
                    $column = '`' . str_replace(' as', '` as', $column);
                } else if ($column != '*') {
                    $column = '`' . $column . '`';
                }
            }
        }

        return $column;
    }
    /**
     * 创建sql语句
     * 
     * @param string $type select、insert、update、delete
     * @return string
     */
    private function createSql($type)
    {
        $sql = '';
        if ($type == 'select') {
            $sql = "select ";
            if (\count($this->columns) == 1 && $this->columns[0] == "*") {
                $sql .= $this->nameFormat($this->columns[0]);
            } else {
                foreach ($this->columns as $key => $value) {
                    $sql .= $this->nameFormat($value);
                    if ($key < (\count($this->columns) - 1)) {
                        $sql .= ', ';
                    }
                }
            }

            $sql .= " from `" . $this->tableName . '`';
            if ($this->alias) {
                $sql .= ' as ' . $this->alias;
            }

            if ($this->joins) {
                foreach ($this->joins as $key => $value) {
                    $joinType = $value[0];
                    $table = $this->nameFormat($value[1]);
                    
                    if ($joinType === 'cross') {
                        $sql .= ' cross join ' . $table;
                    } else {
                        $first = $this->nameFormat($value[2]);
                        $operator = $value[3];
                        $second = $this->nameFormat($value[4]);
                        $sql .= ' ' . $joinType . ' join ' . $table . ' on ' . $first . ' ' . $operator . ' ' . $second;
                    }
                }
            }

            if ($this->wheres) {
                $sql .= " where";
                foreach ($this->wheres as $key => $value) {
                    if ($key < (\count($this->wheres) - 1)) {
                        $sql .= ' ' . $value[0] . ' ' . $value[1];
                    } else {
                        $sql .= ' ' . $value[0];
                    }
                }
            }

            if ($this->groupByVar) {
                $sql .= ' group by ' . $this->groupByVar;
            }

            if ($this->orderByVar) {
                $sql .= ' order by ' . implode(' ,', $this->orderByVar);
            }

            if ($this->limitVar !== '') {
                if ($this->offsetVar == '') {
                    $this->offsetVar = 0;
                }
                $sql .= ' limit ' . $this->offsetVar . ' ,' . $this->limitVar;
            }

            // 处理窗口函数
            if (!empty($this->windows)) {
                $sql .= ' ' . implode(' ', $this->windows);
            }

            // 处理UNION查询
            if (!empty($this->unions)) {
                foreach ($this->unions as $union) {
                    $unionType = $union[0];
                    $unionQuery = $union[1];
                    $unionSql = $unionQuery->createSql('select');
                    $sql .= ' ' . $unionType . ' ' . $unionSql;
                }
            }
        } else if ($type == 'insert') {
            $tmpColumns = [];
            $tmpParams = [];
            $tmpValues = [];
            if ($this->sqlData) {
                foreach ($this->sqlData as $key => $value) {
                    $tmpColumns[] = "`" . $key . "`";
                    $tmpParams[] = $value;
                    $tmpValues[] = '?';
                }

                $this->sqlData = $tmpParams;
            }
            $columns = implode(", ", $tmpColumns);
            $values = implode(", ", $tmpValues);

            $sql = "INSERT INTO $this->tableName ($columns) VALUES ($values)";
        } else if ($type == 'update') {
            $tmpParams = [];
            $setColumns = [];
            if ($this->updateData) {
                foreach ($this->updateData as $key => $value) {
                    $setColumns[] = "`" . $key . "` = ?";
                    $tmpParams[] = $value;
                }
                $this->sqlData = array_merge($tmpParams, $this->sqlData);
            }
            $setData = implode(', ', $setColumns);

            $where = ''; // 根据实际情况设置更新条件
            if ($this->wheres) {
                foreach ($this->wheres as $key => $value) {
                    if ($key < (\count($this->wheres) - 1)) {
                        $where .= ' ' . $value[0] . ' ' . $value[1];
                    } else {
                        $where .= ' ' . $value[0];
                    }
                }
            }

            if ($where) {
                $sql = "UPDATE $this->tableName SET $setData WHERE $where";
            } else {
                $sql = "UPDATE $this->tableName SET $setData";
            }
        } else if ($type == 'delete') {
            $where = ''; // 根据实际情况设置更新条件
            if ($this->wheres) {
                foreach ($this->wheres as $key => $value) {
                    if ($key < (\count($this->wheres) - 1)) {
                        $where .= ' ' . $value[0] . ' ' . $value[1];
                    } else {
                        $where .= ' ' . $value[0];
                    }
                }
            }
            $sql = "delete from $this->tableName WHERE $where";
        }
        return $sql;
    }

    /**
     * 获取最后插入数据的ID
     *
     * @return int 最后插入数据的ID
     */
    public function getLastInsID(): int
    {
        return $this->connection->lastInsertId();
    }

    /**
     * 将下划线命名转换为驼峰式命名
     *
     * @param string $str
     * @param bool $ucfirst
     *
     * @return string
     */
    private function convertUnderline($str, $ucfirst = false): string
    {
        $str = ucwords(str_replace('_', ' ', $str));
        $str = str_replace(' ', '', lcfirst($str));
        return $ucfirst ? ucfirst($str) : $str;
    }

    /**
     * 将下划线命名数组转换为驼峰式命名数组
     * 
     * @param array $data 原数组
     * @param boolean $ucfirst 首字母大小写，false 小写，TRUE 大写
     *
     * @return array
     */
    private function camelCase($data, $ucfirst = false): array
    {
        $result = [];
        foreach ($data as $key => $value) {
            $key1 = $this->convertUnderline($key, $ucfirst);
            $value1 = \gettype($value) == 'array' ? $this->camelCase($value) : $value;
            $result[$key1] = $value1;
        }
        return $result;
    }

    /**
     * 将SQL中的占位符替换为实际值
     *
     * @param string $sql SQL语句
     * @param array $params 参数数组
     * @return string 替换后的SQL语句
     */
    private function replacePlaceholders($sql, $params) {
        if (empty($params)) {
            return $sql;
        }

        $result = $sql;
        $index = 0;
        $length = strlen($result);
        $newSql = '';

        for ($i = 0; $i < $length; $i++) {
            if ($result[$i] == '?') {
                $param = $params[$index] ?? null;
                $newSql .= $this->formatParamValue($param);
                $index++;
            } else {
                $newSql .= $result[$i];
            }
        }

        return $newSql;
    }
    
    /**
     * 设置需要查询的字段
     *
     * @param array $columns 查询字段数组
     * @return self
     */
    public function columns(array $columns): self
    {
        $this->setColums($columns);
        return $this;
    }

    /**
     * 格式化参数值
     *
     * @param mixed $value 参数值
     * @return string 格式化后的参数值
     */
    private function formatParamValue($value) {
        if (is_null($value)) {
            return 'NULL';
        } elseif (is_bool($value)) {
            return $value ? '1' : '0';
        } elseif (is_numeric($value)) {
            return $value;
        } elseif (is_string($value)) {
            // 转义引号和特殊字符
            $value = addslashes($value);
            return "'$value'";
        } elseif (is_array($value)) {
            // 处理数组类型
            $values = [];
            foreach ($value as $item) {
                $values[] = $this->formatParamValue($item);
            }
            return implode(',', $values);
        } else {
            return "'" . addslashes(strval($value)) . "'";
        }
    }
}
