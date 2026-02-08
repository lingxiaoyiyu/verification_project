<?php
namespace Library\Core;

use Library\Facades\Log;
use Library\Facades\Request;
use Library\Core\ServerException;

/**
 * 表单数据验证类
 */
class Validate {

    /**
     * 验证数据
     * 
     * @param array $rules 验证规则
     * @throws ServerException
     */
    public function validate(array $rules) {
        Log::debug('Validate::validate', $rules);
        foreach ($rules as $fieldName => $ruleArr) {
            $fieldText = $fieldName;
            if (is_array($ruleArr)) {
                if (isset($ruleArr['text'])) {
                    $fieldText = $ruleArr['text'];
                    if(is_array($ruleArr['rules'])) {
                        $rulesToTest = $ruleArr['rules'];
                    } else if (is_string($ruleArr['rules'])) {
                        $rulesToTest = [$ruleArr['rules']];
                    }
                } else {
                    $rulesToTest = $ruleArr;
                }
            } else {
                $rulesToTest = [$ruleArr];
            }

            foreach ($rulesToTest as $rule) {
                if (is_string($rule)) {
                    $this->test($fieldName, $fieldText, $rule);
                } else if (is_array($rule)) {
                    foreach ($rule as $ruleKey => $ruleValue) {
                        if (empty($ruleKey) || empty($ruleValue)) {
                            throw new ServerException($fieldName . '-校验规则配置错误');
                        } else {
                            $this->test($fieldName, $fieldText, $ruleKey, $ruleValue);
                        }
                    }
                }
            }
        }
    }

    /**
     * 测试单个字段规则
     * 
     * @param string $fieldName 字段名
     * @param string $fieldText 字段显示文本
     * @param string $rule 规则名称
     * @param mixed $params 规则参数
     * @throws ServerException
     */
    private function test($fieldName, $fieldText, $rule, $params = null) {
        $value = Request::all($fieldName);
        
        switch ($rule) {
            case 'required':
                if (empty($value) && $value !== '0' && $value !== 0) {
                    throw new ServerException($fieldText . '不能为空', Config::get("result.code.badRequest"));
                }
                break;

            case 'file_required':
                $file = Request::file($fieldName);
                if (!$file) {
                    throw new ServerException($fieldText . '文件不存在', Config::get("result.code.badRequest"));
                }
                break;

            case 'min_length':
                if (is_string($value) && mb_strlen($value) < $params) {
                    throw new ServerException($fieldText . '长度不能小于' . $params, Config::get("result.code.badRequest"));
                }
                break;

            case 'max_length':
                if (is_string($value) && mb_strlen($value) > $params) {
                    throw new ServerException($fieldText . '长度不能大于' . $params, Config::get("result.code.badRequest"));
                }
                break;

            case 'length':
                if (is_string($value) && mb_strlen($value) != $params) {
                    throw new ServerException($fieldText . '长度必须为' . $params, Config::get("result.code.badRequest"));
                }
                break;

            case 'in_array':
                if (is_array($value)) {
                    foreach ($value as $item) {
                        if (!in_array($item, $params)) {
                            throw new ServerException($fieldText . '中的元素不在指定数组中', Config::get("result.code.badRequest"));
                        }
                    }
                } else {
                    if (!in_array($value, $params)) {
                        throw new ServerException($fieldText . '不在指定数组中', Config::get("result.code.badRequest"));
                    }
                }
                break;

            case 'not_in':
                if (is_array($value)) {
                    foreach ($value as $item) {
                        if (in_array($item, $params)) {
                            throw new ServerException($fieldText . '中的元素不能在指定数组中', Config::get("result.code.badRequest"));
                        }
                    }
                } else {
                    if (in_array($value, $params)) {
                        throw new ServerException($fieldText . '不能在指定数组中', Config::get("result.code.badRequest"));
                    }
                }
                break;

            case 'min':
                if (is_numeric($value) && $value < $params) {
                    throw new ServerException($fieldText . '不能小于' . $params, Config::get("result.code.badRequest"));
                }
                break;

            case 'max':
                if (is_numeric($value) && $value > $params) {
                    throw new ServerException($fieldText . '不能大于' . $params, Config::get("result.code.badRequest"));
                }
                break;

            case 'between':
                if (is_array($params) && count($params) == 2) {
                    if (is_numeric($value) && ($value < $params[0] || $value > $params[1])) {
                        throw new ServerException($fieldText . '必须在' . $params[0] . '和' . $params[1] . '之间', Config::get("result.code.badRequest"));
                    }
                }
                break;

            case 'is_array':
                if (!is_array($value)) {
                    throw new ServerException($fieldText . '必须是数组', Config::get("result.code.badRequest"));
                }
                break;

            case 'is_numeric':
                if (!is_numeric($value)) {
                    throw new ServerException($fieldText . '必须是数字', Config::get("result.code.badRequest"));
                }
                break;

            case 'is_int':
            case 'is_integer':
                if (!is_int($value) && !ctype_digit(strval($value))) {
                    throw new ServerException($fieldText . '必须是整数', Config::get("result.code.badRequest"));
                }
                break;

            case 'is_float':
                if (!is_float($value) && !is_numeric($value)) {
                    throw new ServerException($fieldText . '必须是浮点数', Config::get("result.code.badRequest"));
                }
                break;

            case 'is_string':
                if (!is_string($value)) {
                    throw new ServerException($fieldText . '必须是字符串', Config::get("result.code.badRequest"));
                }
                break;

            case 'is_bool':
            case 'is_boolean':
                if (!is_bool($value)) {
                    throw new ServerException($fieldText . '必须是布尔值', Config::get("result.code.badRequest"));
                }
                break;

            case 'regex':
                if (!preg_match($params, $value)) {
                    throw new ServerException($fieldText . '格式不正确', Config::get("result.code.badRequest"));
                }
                break;

            case 'alpha':
                if (!ctype_alpha($value)) {
                    throw new ServerException($fieldText . '只能包含字母', Config::get("result.code.badRequest"));
                }
                break;

            case 'alpha_num':
                if (!ctype_alnum($value)) {
                    throw new ServerException($fieldText . '只能包含字母和数字', Config::get("result.code.badRequest"));
                }
                break;

            case 'alpha_dash':
                if (!preg_match('/^[a-zA-Z0-9_-]+$/', $value)) {
                    throw new ServerException($fieldText . '只能包含字母、数字、下划线和破折号', Config::get("result.code.badRequest"));
                }
                break;

            case 'file_extension':
                $file = Request::file($fieldName);
                if ($file) {
                    $fileInfo = pathinfo($file['name']);
                    if (!in_array($fileInfo['extension'] ?? '', $params)) {
                        throw new ServerException($fieldText . '文件后缀不正确', Config::get("result.code.badRequest"));
                    }
                }
                break;

            case 'file_size':
                $file = Request::file($fieldName);
                if ($file && $file['size'] > $params) {
                    throw new ServerException($fieldText . '文件大小超过限制', Config::get("result.code.badRequest"));
                }
                break;

            case 'file_mime':
                $file = Request::file($fieldName);
                if ($file && !in_array($file['type'] ?? '', $params)) {
                    throw new ServerException($fieldText . '文件类型不正确', Config::get("result.code.badRequest"));
                }
                break;

            case 'image':
                $file = Request::file($fieldName);
                if ($file) {
                    $imageTypes = ['image/jpeg', 'image/png', 'image/gif', 'image/bmp', 'image/webp'];
                    if (!in_array($file['type'] ?? '', $imageTypes)) {
                        throw new ServerException($fieldText . '必须是图片文件', Config::get("result.code.badRequest"));
                    }
                }
                break;

            case 'phone':
            case 'mobile':
                if (!preg_match('/^1[3-9]\d{9}$/', $value)) {
                    throw new ServerException($fieldText . '不是有效的手机号', Config::get("result.code.badRequest"));
                }
                break;

            case 'email':
                if (!filter_var($value, FILTER_VALIDATE_EMAIL)) {
                    throw new ServerException($fieldText . '不是有效的邮箱地址', Config::get("result.code.badRequest"));
                }
                break;

            case 'url':
                if (!filter_var($value, FILTER_VALIDATE_URL)) {
                    throw new ServerException($fieldText . '不是有效的URL地址', Config::get("result.code.badRequest"));
                }
                break;

            case 'ip':
                if (!filter_var($value, FILTER_VALIDATE_IP)) {
                    throw new ServerException($fieldText . '不是有效的IP地址', Config::get("result.code.badRequest"));
                }
                break;

            case 'ipv4':
                if (!filter_var($value, FILTER_VALIDATE_IP, FILTER_FLAG_IPV4)) {
                    throw new ServerException($fieldText . '不是有效的IPv4地址', Config::get("result.code.badRequest"));
                }
                break;

            case 'ipv6':
                if (!filter_var($value, FILTER_VALIDATE_IP, FILTER_FLAG_IPV6)) {
                    throw new ServerException($fieldText . '不是有效的IPv6地址', Config::get("result.code.badRequest"));
                }
                break;

            case 'json':
                if (!is_string($value)) {
                    throw new ServerException($fieldText . '必须是JSON字符串', Config::get("result.code.badRequest"));
                }
                json_decode($value);
                if (json_last_error() !== JSON_ERROR_NONE) {
                    throw new ServerException($fieldText . '不是有效的JSON格式', Config::get("result.code.badRequest"));
                }
                break;

            case 'date':
                if (!strtotime($value)) {
                    throw new ServerException($fieldText . '不是有效的日期格式', Config::get("result.code.badRequest"));
                }
                break;

            case 'date_format':
                $date = \DateTime::createFromFormat($params, $value);
                if (!$date || $date->format($params) !== $value) {
                    throw new ServerException($fieldText . '日期格式必须为' . $params, Config::get("result.code.badRequest"));
                }
                break;

            case 'before':
                $timestamp = strtotime($value);
                $compareTimestamp = strtotime($params);
                if (!$timestamp || !$compareTimestamp || $timestamp >= $compareTimestamp) {
                    throw new ServerException($fieldText . '必须在' . $params . '之前', Config::get("result.code.badRequest"));
                }
                break;

            case 'after':
                $timestamp = strtotime($value);
                $compareTimestamp = strtotime($params);
                if (!$timestamp || !$compareTimestamp || $timestamp <= $compareTimestamp) {
                    throw new ServerException($fieldText . '必须在' . $params . '之后', Config::get("result.code.badRequest"));
                }
                break;

            case 'confirmed':
                $confirmField = $fieldName . '_confirmation';
                $confirmValue = Request::all($confirmField);
                if ($value !== $confirmValue) {
                    throw new ServerException($fieldText . '两次输入不一致', Config::get("result.code.badRequest"));
                }
                break;

            case 'same':
                $compareValue = Request::all($params);
                if ($value !== $compareValue) {
                    throw new ServerException($fieldText . '必须与' . $params . '相同', Config::get("result.code.badRequest"));
                }
                break;

            case 'different':
                $compareValue = Request::all($params);
                if ($value === $compareValue) {
                    throw new ServerException($fieldText . '不能与' . $params . '相同', Config::get("result.code.badRequest"));
                }
                break;

            case 'unique':
                // 需要配合数据库使用，参数格式：['table', 'column', 'except_id']
                // 这里留空，由具体业务实现
                break;

            case 'exists':
                // 需要配合数据库使用，参数格式：['table', 'column']
                // 这里留空，由具体业务实现
                break;

            default:
                // 自定义规则或未知规则
                break;
        }
    }
}