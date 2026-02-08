<?php

namespace Library\Core;

class Env {
    const ENV_PREFIX = 'PHP_';

    /**
     * 加载配置文件
     * @access public
     * @param string $filePath 配置文件路径
     * @return void
     */
    public static function loadFile($filePath)
    {
        // 详细的错误日志
        if (!file_exists($filePath)) {
            throw new \Exception('配置文件不存在：' . $filePath);
        }
        
        // 检查文件权限
        if (!is_readable($filePath)) {
            throw new \Exception('配置文件不可读：' . $filePath . '，权限：' . substr(sprintf('%o', fileperms($filePath)), -4));
        }
        
        // 使用@抑制parse_ini_file可能的警告，并检查返回值
        $env = @parse_ini_file($filePath, true);
        if ($env === false) {
            throw new \Exception('解析配置文件失败：' . $filePath);
        }
        
        // 确保是数组
        if (!is_array($env)) {
            throw new \Exception('配置文件解析结果不是数组：' . $filePath);
        }
        
        foreach ($env as $key => $val) {
            $prefix = static::ENV_PREFIX . strtoupper($key);
            if (is_array($val)) {
                foreach ($val as $k => $v) {
                    $item = $prefix . '_' . strtoupper($k);
                    // 安全处理注释和空白
                    $v = trim(preg_replace('/#.*$/', '', $v));
                    // 安全设置环境变量
                    if (!@putenv("$item=$v")) {
                        // 如果putenv失败，尝试使用$_ENV数组
                        $_ENV[$item] = $v;
                    }
                }
            } else {
                // 安全处理注释和空白
                $val = trim(preg_replace('/#.*$/', '', $val));
                // 安全设置环境变量
                if (!@putenv("$prefix=$val")) {
                    // 如果putenv失败，尝试使用$_ENV数组
                    $_ENV[$prefix] = $val;
                }
            }
        }
    }

    /**
     * 获取环境变量值
     * @access public
     * @param string $name 环境变量名（支持二级 . 号分割）
     * @param string $default 默认值
     * @return mixed
     */
    public static function get($name, $default = null) {
        $result = \getenv(static::ENV_PREFIX . strtoupper(str_replace('.', '_', $name)));

        if (false !== $result) {
            if ('false' === $result) {
                $result = false;
            } elseif ('true' === $result) {
                $result = true;
            }
            return $result;
        }
        return $default;
    }
}
