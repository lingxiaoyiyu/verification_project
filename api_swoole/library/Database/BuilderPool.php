<?php

namespace Library\Database;

/**
 * Builder对象池，用于复用Builder实例，减少对象创建开销
 */
class BuilderPool
{
    // 单例实例
    private static $instance;
    
    // Builder实例池
    private $pool = [];
    
    // 最大实例数量
    private $maxSize = 50;
    
    // 当前实例数量
    private $currentSize = 0;
    
    /**
     * 获取单例实例
     *
     * @return BuilderPool
     */
    public static function getInstance()
    {
        if (is_null(self::$instance)) {
            self::$instance = new self();
        }
        return self::$instance;
    }
    
    /**
     * 从对象池获取Builder实例
     *
     * @return BuilderInterface
     */
    public function get()
    {
        // 修复：每次都创建新实例，避免复用导致的状态污染
        return BuilderFactory::getBuilder();
    }
    
    /**
     * 将Builder实例放回对象池
     *
     * @param BuilderInterface $builder
     * @return void
     */
    public function put(BuilderInterface $builder)
    {
        // 修复：重置实例状态但不放回池中，避免状态污染
        if (method_exists($builder, 'reset')) {
            $builder->reset();
        }
        // 不再放回池中，让PHP垃圾回收器处理
    }
    
    /**
     * 设置对象池最大大小
     *
     * @param int $maxSize
     * @return void
     */
    public function setMaxSize($maxSize)
    {
        $this->maxSize = $maxSize;
    }
    
    /**
     * 获取当前对象池大小
     *
     * @return int
     */
    public function getCurrentSize()
    {
        return count($this->pool);
    }
}
