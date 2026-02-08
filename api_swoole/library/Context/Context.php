<?php

namespace Library\Context;

/**
 * 上下文处理类
 * 修复：使用 Swoole 协程上下文替代单例模式，实现协程间隔离
 */
class Context {

    /**
     * 上下文数据存储键名
     */
    private const CONTEXT_DATA_KEY = 'APP_CONTEXT_DATA';

    /**
     * @param $key
     * @param $val
     */
    public static function set($key, $val) {
        $context = \Swoole\Coroutine::getContext();
        if (!isset($context[self::CONTEXT_DATA_KEY])) {
            $context[self::CONTEXT_DATA_KEY] = [];
        }
        $context[self::CONTEXT_DATA_KEY][$key] = $val;
    }

    /**
     * @param $key
     * @return mixed|null
     */
    public static function get($key = null) {
        $context = \Swoole\Coroutine::getContext();
        if (!isset($context[self::CONTEXT_DATA_KEY])) {
            return $key ? null : [];
        }
        
        if ($key) {
            return $context[self::CONTEXT_DATA_KEY][$key] ?? null;
        } else {
            return $context[self::CONTEXT_DATA_KEY];
        }
    }

    public static function delete($key) {
        $context = \Swoole\Coroutine::getContext();
        if (isset($context[self::CONTEXT_DATA_KEY][$key])) {
            unset($context[self::CONTEXT_DATA_KEY][$key]);
        }
    }
    
    /**
     * 清空所有上下文数据
     */
    public static function clear() {
        $context = \Swoole\Coroutine::getContext();
        if (isset($context[self::CONTEXT_DATA_KEY])) {
            unset($context[self::CONTEXT_DATA_KEY]);
        }
    }
    
    /**
     * 获取实例（保持向后兼容）
     * @deprecated 建议直接使用静态方法
     */
    public static function getInstance() {
        return new self();
    }
}
