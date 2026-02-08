<?php
namespace Library\Core;

/**
 * 错误消息包类
 */
class MessageBag
{
    private $messages;

    public function __construct($messages = [])
    {
        $this->messages = $messages ?: [];
    }

    /**
     * 获取所有错误消息
     */
    public function all()
    {
        $all = [];
        foreach ($this->messages as $field => $fieldErrors) {
            foreach ($fieldErrors as $error) {
                $all[] = $error;
            }
        }
        return $all;
    }

    /**
     * 获取第一个错误消息
     */
    public function first()
    {
        foreach ($this->messages as $field => $fieldErrors) {
            if (!empty($fieldErrors)) {
                return $fieldErrors[0];
            }
        }
        return null;
    }

    /**
     * 检查是否有错误
     */
    public function has($key = null)
    {
        if ($key === null) {
            return !empty($this->messages);
        }
        return isset($this->messages[$key]) && !empty($this->messages[$key]);
    }

    /**
     * 获取指定字段的错误消息
     */
    public function get($key)
    {
        return $this->messages[$key] ?? [];
    }

    /**
     * 获取所有错误消息，按字段分组
     */
    public function toArray()
    {
        return $this->messages;
    }
}