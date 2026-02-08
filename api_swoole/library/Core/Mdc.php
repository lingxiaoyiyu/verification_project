<?php

namespace Library\Core;

/**
 * MDC (Mapped Diagnostic Context) 类
 * 用于在同一请求的不同方法和组件间传递上下文信息
 * 基于 Swoole 协程上下文实现，确保协程安全
 */
class Mdc
{
    /**
     * MDC 数据在协程上下文中的键名
     */
    private const CONTEXT_KEY = 'MDC_DATA';

    /**
     * 存储键值对到 MDC
     *
     * @param string $key 键名
     * @param mixed $value 值
     * @return void
     */
    public static function put(string $key, mixed $value): void
    {
        $context = \Swoole\Coroutine::getContext();
        if (!isset($context[self::CONTEXT_KEY])) {
            $context[self::CONTEXT_KEY] = [];
        }
        $context[self::CONTEXT_KEY][$key] = $value;
    }

    /**
     * 从 MDC 获取值
     *
     * @param string $key 键名
     * @param mixed $default 默认值
     * @return mixed
     */
    public static function get(string $key, mixed $default = null): mixed
    {
        $context = \Swoole\Coroutine::getContext();
        if (!isset($context[self::CONTEXT_KEY]) || !isset($context[self::CONTEXT_KEY][$key])) {
            return $default;
        }
        return $context[self::CONTEXT_KEY][$key];
    }

    /**
     * 从 MDC 删除键
     *
     * @param string $key 键名
     * @return void
     */
    public static function remove(string $key): void
    {
        $context = \Swoole\Coroutine::getContext();
        if (isset($context[self::CONTEXT_KEY]) && isset($context[self::CONTEXT_KEY][$key])) {
            unset($context[self::CONTEXT_KEY][$key]);
        }
    }

    /**
     * 清空 MDC
     *
     * @return void
     */
    public static function clear(): void
    {
        $context = \Swoole\Coroutine::getContext();
        if (isset($context[self::CONTEXT_KEY])) {
            unset($context[self::CONTEXT_KEY]);
        }
    }

    /**
     * 获取所有 MDC 数据
     *
     * @return array
     */
    public static function all(): array
    {
        $context = \Swoole\Coroutine::getContext();
        return $context[self::CONTEXT_KEY] ?? [];
    }

    /**
     * 检查键是否存在
     *
     * @param string $key 键名
     * @return bool
     */
    public static function has(string $key): bool
    {
        $context = \Swoole\Coroutine::getContext();
        return isset($context[self::CONTEXT_KEY]) && isset($context[self::CONTEXT_KEY][$key]);
    }
}
