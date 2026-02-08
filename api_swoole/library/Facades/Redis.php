<?php

namespace Library\Facades;

use Library\Redis\RedisPoolConn;

require_once __DIR__ . '/../../vendor/autoload.php';

class Redis {
    private static bool $initialized = false;

    public static function initialize(): void {
        if (self::$initialized) {
            return;
        }

        RedisPoolConn::getInstance();
        
        self::$initialized = true;
    }

    /**
     * 获取Redis连接
     *
     * @return redis连接
     */
    public static function getConn(){
        return RedisPoolConn::getInstance()->getConn();
    }

    /**
     * 回收连接
     */
    public static function recycle($conn){
        RedisPoolConn::getInstance()->recycle($conn);
    }

    /****************************************字符串操作******************************************************* */
    /**
     * 获取key
     *
     * @param string $key
     * @return string value值
     */
    public static function get(string $key){
        $redis = self::getConn();
        $value = $redis->get($key);
        self::recycle($redis);
        return $value;
    }

    /**
     * 查询键的值
     *
     * @param string|array $arr
     * @return string|array 返回所查询键的值
     */
    public static function mget(string|array $arr){
        $redis = self::getConn();
        $rst = $redis->mget($arr);
        self::recycle($redis);
        return $rst;
    }

    /**
     * 设置key=aa
     *
     * @param string $key
     * @param string $value
     * @return boolean true：设置成功，false：设置失败
     */
    public static function set(string $key, string $value){
        $redis = self::getConn();
        $rst = $redis->set($key, $value);
        self::recycle($redis);
        return $rst;
    }

    /**
     * key的值不存在时，才为其设置值。
     *
     * @param string $key
     * @param string $value
     * @return boolean key不存在且设置成功返回true，否则返回false。
     */
    public static function setnx(string $key, string $value){
        $redis = self::getConn();
        $rst = $redis->setnx($key, $value);
        self::recycle($redis);
        return $rst;
    }

    /**
     * key的值不存在时，才为其设置值。
     *
     * @param string $key
     * @param int $expire 有效期
     * @param string $value
     * @return boolean key不存在且设置成功返回true，否则返回false。
     */
    public static function setex(string $key, int $expire, string $value){
        $redis = self::getConn();
        $rst = $redis->setex($key, $expire, $value);
        self::recycle($redis);
        return $rst;
    }

    /**
     * 设置一个或多个键值
     *
     * @param array $arr
     * @return boolean true：设置成功，false：设置失败
     */
    public static function mset(array $arr){
        $redis = self::getConn();
        $rst = $redis->mset($arr);
        self::recycle($redis);
        return $rst;
    }

    /**
     * 设置新值，返回旧值
     *
     * @param string $key
     * @param string $value
     * @return boolean|string 若key不存在则设置值，返回false
     */
    public static function getset(string $key, string $value){
        $redis = self::getConn();
        $rst = $redis->getset($key, $value);
        self::recycle($redis);
        return $rst;
    }

    /**
     * 删除key，支持数组批量删除
     *
     * @param string|array $key_arr
     * @return int 删除个数
     */
    public static function del(string|array $key_arr){
        $redis = self::getConn();
        $rst = $redis->del($key_arr);
        self::recycle($redis);
        return $rst;
    }

    /**
     * 将指定key存储的数字值增加1。若key不存在会先初始化为0再增加1，若key存储的不是整数值则返回false。成功返回key新值。
     *
     * @param string $key
     * @return boolean|string
     */
    public static function incr(string $key) {
        $redis = self::getConn();
        $rst = $redis->incr($key);
        self::recycle($redis);
        return $rst;
    }

    /**
     * 给指定key存储的数字值增加指定增量值。
     *
     * @param string $key
     * @param int $num
     * @return boolean|string
     */
    public static function incrBy(string $key, int $num) {
        $redis = self::getConn();
        $rst = $redis->incrBy($key, $num);
        self::recycle($redis);
        return $rst;
    }

    /**
     * 给指定key存储的数字值增加指定浮点数增量。
     *
     * @param string $key
     * @param float $num
     * @return boolean|string
     */
    public static function incrByFloat(string $key, float $num) {
        $redis = self::getConn();
        $rst = $redis->incrByFloat($key, $num);
        self::recycle($redis);
        return $rst;
    }

    /**
     * 将指定key存储的数字值减一。
     *
     * @param string $key
     * @return boolean|string
     */
    public static function decr(string $key) {
        $redis = self::getConn();
        $rst = $redis->decr($key);
        self::recycle($redis);
        return $rst;
    }

    /**
     * 将指定key存储的数字值减去指定减量值。
     *
     * @param string $key
     * @param int $num
     * @return boolean|string
     */
    public static function decrBy(string $key, int $num) {
        $redis = self::getConn();
        $rst = $redis->decrBy($key, $num);
        self::recycle($redis);
        return $rst;
    }

    /**
     * 获取指定key存储的字符串的长度，key不存在返回0，不为字符串返回false。
     *
     * @param string $key
     * @return boolean|int
     */
    public static function strlen(string $key) {
        $redis = self::getConn();
        $length = $redis->strlen($key);
        self::recycle($redis);
        return $length;
    }

    /**
     * 为指定key追加值到原值末尾，若key不存在则相对于set()函数。
     *
     * @param string $key
     * @param string $value
     * @return boolean|int
     */
    public static function append(string $key, string $value) { 
        $redis = self::getConn();
        $rst = $redis->append($key, $value);
        self::recycle($redis);
        return $rst;
    }

    /****************************************list操作************************************ */
    /**
     * 将一个值value插入到列表key的表头，不存在就创建
     *
     * @param string $key
     * @param string $value
     * @return boolean|string 列表的长度 |false
     */
    public static function lPush(string $key, string $value) { 
        $redis = self::getConn();
        $rst = $redis->lPush($key, $value);
        self::recycle($redis);
        return $rst;
    }

    /**
     * 将一个值value插入到列表key的表头，不存在不创建
     *
     * @param string $key
     * @param string $value
     * @return boolean|string 列表的长度 |false
     */
    public static function lPushx(string $key, string $value) { 
        $redis = self::getConn();
        $rst = $redis->lPushx($key, $value);
        self::recycle($redis);
        return $rst;
    }

    /**
     * 将一个值value插入到列表key的表尾，不存在就创建
     *
     * @param string $key
     * @param string $value
     * @return boolean|string 列表的长度 |false
     */
    public static function rPush(string $key, string $value) { 
        $redis = self::getConn();
        $rst = $redis->rPush($key, $value);
        self::recycle($redis);
        return $rst;
    }

    /**
     * 将一个值value插入到列表key的表尾，不存在不创建
     *
     * @param string $key
     * @param string $value
     * @return boolean|string 列表的长度 |false
     */
    public static function rpushx(string $key, string $value) { 
        $redis = self::getConn();
        $rst = $redis->rpushx($key, $value);
        self::recycle($redis);
        return $rst;
    }

    /**
     * 移除并返回列表的第一个元素，若key不存在或不是列表则返回false。
     *
     * @param string $key
     * @return boolean|string 列表的长度 |false
     */
    public static function lPop(string $key) { 
        $redis = self::getConn();
        $rst = $redis->lPop($key);
        self::recycle($redis);
        return $rst;
    }

    /**
     * 移除并返回列表的最后一个元素，若key不存在或不是列表则返回false。
     *
     * @param string $key
     * @return boolean|string 列表的长度 |false
     */
    public static function rPop(string $key) { 
        $redis = self::getConn();
        $rst = $redis->rPop($key);
        self::recycle($redis);
        return $rst;
    }

    /**
     * 移除并获取列表的第一个元素。如果列表没有元素则会阻塞列表直到等待超时或发现可弹出元素为止。
     *
     * @param string $key
     * @param int $expire 超时时间
     * @return array 返回值：[0=>key,1=>value]，超时返回[]
     */
    public static function blPop(string $key, int $expire) { 
        $redis = self::getConn();
        $rst = $redis->blPop($key, $expire);
        self::recycle($redis);
        return $rst;
    }

    /**
     * 移除并获取列表的最后一个元素。如果列表没有元素则会阻塞列表直到等待超时或发现可弹出元素为止。
     *
     * @param string $key
     * @param int $expire 超时时间
     * @return array 返回值：[0=>key,1=>value]，超时返回[]
     */
    public static function brPop(string $key, int $expire) { 
        $redis = self::getConn();
        $rst = $redis->brPop($key, $expire);
        self::recycle($redis);
        return $rst;
    }

    /**
     * 获取列表长度
     *
     * @param string $key
     * @return int
     */
    public static function lLen(string $key) { 
        $redis = self::getConn();
        $length = $redis->lLen($key);
        self::recycle($redis);
        return $length;
    }

    /****************************************哈希表操作************************************ */
    /**
     * 设置哈希表字段值
     *
     * @param string $key 键名
     * @param string $field 字段名
     * @param string $value 字段值
     * @return boolean true：设置成功，false：设置失败
     */
    public static function hSet(string $key, string $field, string $value) { 
        $redis = self::getConn();
        $rst = $redis->hSet($key, $field, $value);
        self::recycle($redis);
        return $rst;
    }

    /**
     * 获取哈希表字段值
     *
     * @param string $key 键名
     * @param string $field 字段名
     * @return string|null 字段值，字段不存在返回null
     */
    public static function hGet(string $key, string $field) { 
        $redis = self::getConn();
        $value = $redis->hGet($key, $field);
        self::recycle($redis);
        return $value;
    }

    /**
     * 批量设置哈希表字段值
     *
     * @param string $key 键名
     * @param array $arr 字段值数组，格式：['field1' => 'value1', 'field2' => 'value2']
     * @return boolean true：设置成功，false：设置失败
     */
    public static function hMset(string $key, array $arr) { 
        $redis = self::getConn();
        $rst = $redis->hMset($key, $arr);
        self::recycle($redis);
        return $rst;
    }

    /**
     * 批量获取哈希表字段值
     *
     * @param string $key 键名
     * @param array $fields 字段名数组
     * @return array 字段值数组，格式：['field1' => 'value1', 'field2' => 'value2']
     */
    public static function hMget(string $key, array $fields) { 
        $redis = self::getConn();
        $rst = $redis->hMget($key, $fields);
        self::recycle($redis);
        return $rst;
    }

    /**
     * 删除哈希表字段
     *
     * @param string $key 键名
     * @param string|array $fields 字段名或字段名数组
     * @return int 删除的字段数量
     */
    public static function hDel(string $key, $fields) { 
        $redis = self::getConn();
        $rst = $redis->hDel($key, $fields);
        self::recycle($redis);
        return $rst;
    }

    /**
     * 检查哈希表字段是否存在
     *
     * @param string $key 键名
     * @param string $field 字段名
     * @return boolean true：存在，false：不存在
     */
    public static function hExists(string $key, string $field) { 
        $redis = self::getConn();
        $rst = $redis->hExists($key, $field);
        self::recycle($redis);
        return $rst;
    }

    /**
     * 获取哈希表字段数量
     *
     * @param string $key 键名
     * @return int 字段数量
     */
    public static function hLen(string $key) { 
        $redis = self::getConn();
        $length = $redis->hLen($key);
        self::recycle($redis);
        return $length;
    }

    /**
     * 获取哈希表所有字段名
     *
     * @param string $key 键名
     * @return array 字段名数组
     */
    public static function hKeys(string $key) { 
        $redis = self::getConn();
        $keys = $redis->hKeys($key);
        self::recycle($redis);
        return $keys;
    }

    /**
     * 获取哈希表所有字段值
     *
     * @param string $key 键名
     * @return array 字段值数组
     */
    public static function hVals(string $key) { 
        $redis = self::getConn();
        $values = $redis->hVals($key);
        self::recycle($redis);
        return $values;
    }

    /**
     * 获取哈希表所有字段和值
     *
     * @param string $key 键名
     * @return array 字段和值数组，格式：['field1' => 'value1', 'field2' => 'value2']
     */
    public static function hGetAll(string $key) { 
        $redis = self::getConn();
        $data = $redis->hGetAll($key);
        self::recycle($redis);
        return $data;
    }

    /****************************************集合操作************************************ */
    /**
     * 向集合添加成员
     *
     * @param string $key 键名
     * @param string|array $members 成员或成员数组
     * @return int 添加的成员数量
     */
    public static function sAdd(string $key, $members) { 
        $redis = self::getConn();
        $rst = $redis->sAdd($key, $members);
        self::recycle($redis);
        return $rst;
    }

    /**
     * 从集合移除成员
     *
     * @param string $key 键名
     * @param string|array $members 成员或成员数组
     * @return int 移除的成员数量
     */
    public static function sRem(string $key, $members) { 
        $redis = self::getConn();
        $rst = $redis->sRem($key, $members);
        self::recycle($redis);
        return $rst;
    }

    /**
     * 获取集合所有成员
     *
     * @param string $key 键名
     * @return array 成员数组
     */
    public static function sMembers(string $key) { 
        $redis = self::getConn();
        $members = $redis->sMembers($key);
        self::recycle($redis);
        return $members;
    }

    /**
     * 检查成员是否在集合中
     *
     * @param string $key 键名
     * @param string $member 成员
     * @return boolean true：在集合中，false：不在集合中
     */
    public static function sIsMember(string $key, string $member) { 
        $redis = self::getConn();
        $rst = $redis->sIsMember($key, $member);
        self::recycle($redis);
        return $rst;
    }

    /**
     * 获取集合成员数量
     *
     * @param string $key 键名
     * @return int 成员数量
     */
    public static function sCard(string $key) { 
        $redis = self::getConn();
        $count = $redis->sCard($key);
        self::recycle($redis);
        return $count;
    }

    /**
     * 随机移除并返回集合成员
     *
     * @param string $key 键名
     * @param int $count 移除的数量，默认1
     * @return string|array 移除的成员或成员数组
     */
    public static function sPop(string $key, int $count = 1) { 
        $redis = self::getConn();
        if ($count == 1) {
            $rst = $redis->sPop($key);
        } else {
            $rst = $redis->sPop($key, $count);
        }
        self::recycle($redis);
        return $rst;
    }

    /**
     * 随机返回集合成员
     *
     * @param string $key 键名
     * @param int $count 返回的数量，默认1
     * @return string|array 返回的成员或成员数组
     */
    public static function sRandMember(string $key, int $count = 1) { 
        $redis = self::getConn();
        if ($count == 1) {
            $rst = $redis->sRandMember($key);
        } else {
            $rst = $redis->sRandMember($key, $count);
        }
        self::recycle($redis);
        return $rst;
    }

    /****************************************有序集合操作************************************ */
    /**
     * 向有序集合添加成员
     *
     * @param string $key 键名
     * @param float $score 分数
     * @param string $member 成员
     * @return int 添加的成员数量
     */
    public static function zAdd(string $key, float $score, string $member) { 
        $redis = self::getConn();
        $rst = $redis->zAdd($key, $score, $member);
        self::recycle($redis);
        return $rst;
    }

    /**
     * 从有序集合移除成员
     *
     * @param string $key 键名
     * @param string|array $members 成员或成员数组
     * @return int 移除的成员数量
     */
    public static function zRem(string $key, $members) { 
        $redis = self::getConn();
        $rst = $redis->zRem($key, $members);
        self::recycle($redis);
        return $rst;
    }

    /**
     * 获取有序集合成员分数
     *
     * @param string $key 键名
     * @param string $member 成员
     * @return float|null 分数，成员不存在返回null
     */
    public static function zScore(string $key, string $member) { 
        $redis = self::getConn();
        $score = $redis->zScore($key, $member);
        self::recycle($redis);
        return $score;
    }

    /**
     * 获取有序集合成员排名（从小到大）
     *
     * @param string $key 键名
     * @param string $member 成员
     * @return int|null 排名，成员不存在返回null
     */
    public static function zRank(string $key, string $member) { 
        $redis = self::getConn();
        $rank = $redis->zRank($key, $member);
        self::recycle($redis);
        return $rank;
    }

    /**
     * 获取有序集合成员倒序排名（从大到小）
     *
     * @param string $key 键名
     * @param string $member 成员
     * @return int|null 排名，成员不存在返回null
     */
    public static function zRevRank(string $key, string $member) { 
        $redis = self::getConn();
        $rank = $redis->zRevRank($key, $member);
        self::recycle($redis);
        return $rank;
    }

    /**
     * 按排名范围获取有序集合成员
     *
     * @param string $key 键名
     * @param int $start 起始排名
     * @param int $stop 结束排名
     * @param bool $withScores 是否返回分数，默认false
     * @return array 成员或成员和分数数组
     */
    public static function zRange(string $key, int $start, int $stop, bool $withScores = false) { 
        $redis = self::getConn();
        $members = $redis->zRange($key, $start, $stop, $withScores);
        self::recycle($redis);
        return $members;
    }

    /**
     * 按倒序排名范围获取有序集合成员
     *
     * @param string $key 键名
     * @param int $start 起始排名
     * @param int $stop 结束排名
     * @param bool $withScores 是否返回分数，默认false
     * @return array 成员或成员和分数数组
     */
    public static function zRevRange(string $key, int $start, int $stop, bool $withScores = false) { 
        $redis = self::getConn();
        $members = $redis->zRevRange($key, $start, $stop, $withScores);
        self::recycle($redis);
        return $members;
    }

    /**
     * 获取有序集合成员数量
     *
     * @param string $key 键名
     * @return int 成员数量
     */
    public static function zCard(string $key) { 
        $redis = self::getConn();
        $count = $redis->zCard($key);
        self::recycle($redis);
        return $count;
    }

    /****************************************键操作************************************ */
    /**
     * 检查键是否存在
     *
     * @param string $key 键名
     * @return boolean true：存在，false：不存在
     */
    public static function exists(string $key) { 
        $redis = self::getConn();
        $rst = $redis->exists($key);
        self::recycle($redis);
        return $rst;
    }

    /**
     * 设置键过期时间
     *
     * @param string $key 键名
     * @param int $seconds 过期时间（秒）
     * @return boolean true：设置成功，false：设置失败
     */
    public static function expire(string $key, int $seconds) { 
        $redis = self::getConn();
        $rst = $redis->expire($key, $seconds);
        self::recycle($redis);
        return $rst;
    }

    /**
     * 获取键剩余过期时间
     *
     * @param string $key 键名
     * @return int 剩余过期时间（秒），-1：永不过期，-2：键不存在
     */
    public static function ttl(string $key) { 
        $redis = self::getConn();
        $ttl = $redis->ttl($key);
        self::recycle($redis);
        return $ttl;
    }

    /**
     * 移除键过期时间
     *
     * @param string $key 键名
     * @return boolean true：移除成功，false：移除失败
     */
    public static function persist(string $key) { 
        $redis = self::getConn();
        $rst = $redis->persist($key);
        self::recycle($redis);
        return $rst;
    }

    /**
     * 模糊匹配键
     *
     * @param string $pattern 匹配模式
     * @return array 匹配的键数组
     */
    public static function keys(string $pattern) { 
        $redis = self::getConn();
        $keys = $redis->keys($pattern);
        self::recycle($redis);
        return $keys;
    }

    /**
     * 获取键类型
     *
     * @param string $key 键名
     * @return string 键类型：string, list, set, zset, hash, none
     */
    public static function type(string $key) { 
        $redis = self::getConn();
        $type = $redis->type($key);
        self::recycle($redis);
        return $type;
    }

    /****************************************事务操作************************************ */
    /**
     * 开启事务
     *
     * @return Redis 事务对象
     */
    public static function multi() { 
        $redis = self::getConn();
        $redis->multi();
        return $redis;
    }

    /**
     * 执行事务
     *
     * @param Redis $redis 事务对象
     * @return array 事务执行结果
     */
    public static function exec($redis) { 
        $rst = $redis->exec();
        self::recycle($redis);
        return $rst;
    }

    /**
     * 取消事务
     *
     * @param Redis $redis 事务对象
     * @return boolean true：取消成功，false：取消失败
     */
    public static function discard($redis) { 
        $rst = $redis->discard();
        self::recycle($redis);
        return $rst;
    }
}