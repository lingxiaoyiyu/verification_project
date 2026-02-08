<?php

namespace Library\Facades;

/**
 * 验证器门面类
 */
class Validate
{
    /**
     * 直接定义validate方法，避免使用魔术方法
     * 
     * @param array $rules 验证规则
     * @return void
     */
    public static function validate(array $rules)
    {
        // 直接创建核心Validate类的实例并调用validate方法
        $vali = new \Library\Core\Validate();
        $vali->validate($rules);
    }
    
    /**
     * 创建验证器实例 - Laravel风格
     * 
     * @param array $data 需要验证的数据
     * @param array $rules 验证规则
     * @param array $messages 自定义错误消息
     * @return \Library\Core\Validator
     */
    public static function make(array $data, array $rules, array $messages = [])
    {
        return new \Library\Core\Validator($data, $rules, $messages);
    }
}