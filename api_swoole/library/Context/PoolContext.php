<?php

namespace Library\Context;

use Library\Context\Coroutine;
use Library\Context\Context;

/**
 * 上下文连接池处理类
 *
 * @desc context pool，请求之间隔离，请求之内任何地方可以存取
 */
class PoolContext {
    /**
     * @var array context pool
     */
    public static $pool = [];

    /**
     * @return \Library\Context\Context
     * @desc 可以任意协程获取到context
     */
    public static function getContext() {
        $id = Coroutine::getPid();
        if (!isset(self::$pool[$id])) {
            self::$pool[$id] = Context::getInstance();

        }
        return self::$pool[$id];
    }

    /**
     * @desc 清除context
     */
    public static function clear() {
        $id = Coroutine::getPid();
        if (isset(self::$pool[$id])) {
            unset(self::$pool[$id]);
        }
    }

    /**
     * @param $context
     * @desc 设置context
     */
    public static function set($context) {
        $id = Coroutine::getPid();
        self::$pool[$id] = $context;
    }
}
