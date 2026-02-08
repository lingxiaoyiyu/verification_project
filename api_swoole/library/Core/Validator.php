<?php
namespace Library\Core;

use Library\Facades\Request;

/**
 * 验证器类 - 支持Laravel风格的验证
 */
class Validator
{
    private $data;
    private $rules;
    private $customMessages;
    private $errors = [];

    public function __construct($data, $rules, $customMessages = [])
    {
        $this->data = $data;
        $this->rules = $rules;
        $this->customMessages = $customMessages;
    }

    /**
     * 执行验证
     */
    public function passes()
    {
        $this->errors = [];
        
        foreach ($this->rules as $field => $ruleString) {
            $rules = explode('|', $ruleString);
            
            foreach ($rules as $rule) {
                $ruleParts = explode(':', $rule, 2);
                $ruleName = trim($ruleParts[0]);
                $ruleParams = isset($ruleParts[1]) ? explode(',', $ruleParts[1]) : [];

                $value = $this->getValue($field);
                
                if (!$this->applyRule($field, $value, $ruleName, $ruleParams)) {
                    $this->addError($field, $ruleName, $ruleParams);
                    break; // 如果一个规则失败，则不再检查该字段的后续规则
                }
            }
        }
        
        return empty($this->errors);
    }

    /**
     * 检查验证是否失败
     */
    public function fails()
    {
        return !$this->passes();
    }

    /**
     * 获取错误信息
     */
    public function errors()
    {
        return new MessageBag($this->errors);
    }

    /**
     * 获取字段值
     */
    private function getValue($field)
    {
        if (is_array($this->data)) {
            return $this->data[$field] ?? null;
        }
        
        // 如果是 Request 对象，尝试从 Request 获取
        if (class_exists('\Library\Facades\Request')) {
            // 尝试使用 Request::all() 方法获取字段值
            $allData = \Library\Facades\Request::all();
            return $allData[$field] ?? null;
        }
        
        return null;
    }

    /**
     * 应用单个验证规则
     */
    private function applyRule($field, $value, $ruleName, $params)
    {
        switch ($ruleName) {
            case 'required':
                return $this->validateRequired($value);
                
            case 'string':
                return $this->validateString($value);
                
            case 'integer':
            case 'int':
                return $this->validateInteger($value);
                
            case 'nullable':
                return $value === null || $value === '' || $this->validateRequired($value);
                
            case 'max':
                return $this->validateMax($value, $params[0]);
                
            case 'min':
                return $this->validateMin($value, $params[0]);
                
            default:
                // 可以扩展更多验证规则
                return true;
        }
    }

    /**
     * 验证必需字段
     */
    private function validateRequired($value)
    {
        if ($value === null) {
            return false;
        }
        
        if (is_string($value) && trim($value) === '') {
            return false;
        }
        
        if (is_array($value) && empty($value)) {
            return false;
        }
        
        return true;
    }

    /**
     * 验证字符串
     */
    private function validateString($value)
    {
        return is_string($value) || is_numeric($value);
    }

    /**
     * 验证整数
     */
    private function validateInteger($value)
    {
        if ($value === null) {
            return true; // 如果值为空，不进行整数验证
        }
        
        return filter_var($value, FILTER_VALIDATE_INT) !== false || ctype_digit((string)$value);
    }

    /**
     * 验证最大值
     */
    private function validateMax($value, $max)
    {
        if ($value === null) {
            return true; // 如果值为空，跳过验证
        }
        
        if (is_numeric($value)) {
            return $value <= $max;
        }
        
        if (is_string($value)) {
            return strlen($value) <= $max;
        }
        
        return true;
    }

    /**
     * 验证最小值
     */
    private function validateMin($value, $min)
    {
        if ($value === null) {
            return true; // 如果值为空，跳过验证
        }
        
        if (is_numeric($value)) {
            return $value >= $min;
        }
        
        if (is_string($value)) {
            return strlen($value) >= $min;
        }
        
        return true;
    }

    /**
     * 添加错误信息
     */
    private function addError($field, $rule, $params)
    {
        $message = $this->getMessage($field, $rule, $params);
        $this->errors[$field][] = $message;
    }

    /**
     * 获取错误消息
     */
    private function getMessage($field, $rule, $params)
    {
        // 检查自定义错误消息
        $customKey = "{$field}.{$rule}";
        if (isset($this->customMessages[$customKey])) {
            return $this->customMessages[$customKey];
        }

        // 默认错误消息
        switch ($rule) {
            case 'required':
                return "{$field}不能为空";
            case 'string':
                return "{$field}必须是字符串";
            case 'integer':
            case 'int':
                return "{$field}必须是整数";
            case 'max':
                return "{$field}不能大于{$params[0]}";
            case 'min':
                return "{$field}不能小于{$params[0]}";
            default:
                return "{$field}验证失败";
        }
    }
}