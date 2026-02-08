<?php

namespace Library\Database;

/**
 * 数据库查询构建器接口
 * 定义了查询构建器必须实现的方法
 */
interface BuilderInterface
{
    /**
     * 设置表名
     *
     * @param string $tableName 表名
     * @return self
     */
    public function from($tableName);

    /**
     * 设置别名
     *
     * @param string $alias 别名
     * @return self
     */
    public function alias($alias);

    /**
     * 设置查询字段
     *
     * @param array $columns 查询字段数组
     * @return self
     */
    public function columns(array $columns);

    /**
     * 设置查询条件
     *
     * @param string $key 字段名
     * @param mixed $value 字段值
     * @param string $operator 操作符
     * @param string $type 条件类型
     * @return self
     */
    public function where($key, $value = null, $operator = '=', $type = 'and');

    /**
     * 设置排序条件
     *
     * @param string $field 排序字段
     * @param string $direction 排序方向
     * @return self
     */
    public function orderBy($field, $direction = 'asc');

    /**
     * 设置偏移量
     *
     * @param int $offset 偏移量
     * @return self
     */
    public function offset($offset);

    /**
     * 设置限制数量
     *
     * @param int $limit 限制数量
     * @return self
     */
    public function limit($limit);

    /**
     * 执行查询
     *
     * @return array 查询结果
     */
    public function get();

    /**
     * 获取单条记录
     *
     * @return array|null 单条记录
     */
    public function first();

    /**
     * 插入数据
     *
     * @param array $data 插入数据
     * @return int 插入ID
     */
    public function insert(array $data);

    /**
     * 更新数据
     *
     * @param array $data 更新数据
     * @return int 受影响行数
     */
    public function update(array $data);

    /**
     * 删除数据
     *
     * @return int 受影响行数
     */
    public function delete();
}