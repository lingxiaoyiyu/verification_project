<?php

namespace Library\Facades;

use Swoole\Coroutine;
use Library\Core\Config;
use Library\Facades\Request;

/**
 * I18n 门面类
 * 提供国际化翻译功能
 */
class I18n
{
    /**
     * 语言缓存
     * @var array
     */
    private static $langCache = [];

    /**
     * 上下文键名
     */
    const CONTEXT_LOCALE = 'i18n_locale';

    /**
     * 翻译方法
     *
     * @param string $key 翻译键名，支持点语法，如 common.hello
     * @param array $replace 占位符替换数组，如 ['name' => '张三']
     * @param string|null $locale 语言，默认使用当前语言
     * @return string 翻译后的文本
     */
    public static function trans(string $key, array $replace = [], ?string $locale = null): string
    {
        $locale = $locale ?? self::getLocale();
        
        // 获取翻译文本
        $translation = self::getTranslation($key, $locale);
        
        // 替换占位符
        if (!empty($replace)) {
            $translation = self::replacePlaceholders($translation, $replace);
        }
        
        return $translation;
    }

    /**
     * 设置当前语言
     *
     * @param string $locale 语言，如 zh-CN, en-US
     * @return bool 是否设置成功
     */
    public static function setLocale(string $locale): bool
    {
        $config = Config::get('i18n');
        
        // 检查语言是否在支持列表中
        if (!in_array($locale, $config['supported'])) {
            return false;
        }
        
        // 存储到协程上下文
        Coroutine::getContext()[self::CONTEXT_LOCALE] = $locale;
        
        return true;
    }

    /**
     * 获取当前语言
     *
     * @return string 当前语言
     */
    public static function getLocale(): string
    {
        // 从协程上下文获取
        $locale = Coroutine::getContext()[self::CONTEXT_LOCALE] ?? null;
        
        if ($locale) {
            return $locale;
        }
        
        // 自动检测语言
        $locale = self::detectLocale();
        
        // 存储到协程上下文
        Coroutine::getContext()[self::CONTEXT_LOCALE] = $locale;
        
        return $locale;
    }

    /**
     * 自动检测语言
     *
     * @return string 检测到的语言
     */
    private static function detectLocale(): string
    {
        $config = Config::get('i18n');
        $detect = $config['detect'];
        
        // 从URL参数检测
        if ($detect['query']) {
            $langParam = Request::get($detect['param']);
            if ($langParam && in_array($langParam, $config['supported'])) {
                return $langParam;
            }
        }
        
        // 从请求头检测
        if ($detect['header']) {
            $acceptLanguage = Request::header('accept-language');
            if ($acceptLanguage) {
                $languages = explode(',', $acceptLanguage);
                foreach ($languages as $lang) {
                    // 处理语言标签，如 en-US;q=0.9
                    $lang = explode(';', $lang)[0];
                    $lang = trim($lang);
                    
                    // 检查是否支持该语言
                    if (in_array($lang, $config['supported'])) {
                        return $lang;
                    }
                    
                    // 检查语言前缀，如 en-US -> en
                    $langPrefix = explode('-', $lang)[0];
                    foreach ($config['supported'] as $supportedLang) {
                        if (strpos($supportedLang, $langPrefix) === 0) {
                            return $supportedLang;
                        }
                    }
                }
            }
        }
        
        // 返回默认语言
        return $config['default'];
    }

    /**
     * 获取翻译文本
     *
     * @param string $key 翻译键名
     * @param string $locale 语言
     * @return string 翻译文本
     */
    private static function getTranslation(string $key, string $locale): string
    {
        // 解析键名，如 common.hello -> ['common', 'hello']
        $parts = explode('.', $key);
        $filename = array_shift($parts);
        
        // 加载语言文件
        $langData = self::loadLangFile($filename, $locale);
        
        // 查找翻译文本
        $translation = $langData;
        foreach ($parts as $part) {
            if (!isset($translation[$part])) {
                // 如果找不到翻译，返回原始键名
                return $key;
            }
            $translation = $translation[$part];
        }
        
        // 如果是数组，返回键名
        if (is_array($translation)) {
            return $key;
        }
        
        return $translation;
    }

    /**
     * 加载语言文件
     *
     * @param string $filename 文件名（不含扩展名）
     * @param string $locale 语言
     * @return array 语言数据
     */
    private static function loadLangFile(string $filename, string $locale): array
    {
        $cacheKey = $locale . ':' . $filename;
        
        // 检查缓存
        if (isset(self::$langCache[$cacheKey])) {
            return self::$langCache[$cacheKey];
        }
        
        $config = Config::get('i18n');
        $langData = [];
        
        // 1. 加载全局语言文件（resources/lang/{locale}/{filename}.php）
        $globalFilePath = $config['directory'] . '/' . $locale . '/' . $filename . '.php';
        if (file_exists($globalFilePath)) {
            $globalLangData = include $globalFilePath;
            if (is_array($globalLangData)) {
                $langData = $globalLangData;
            }
        }
        
        // 2. 遍历所有模块，加载模块语言文件（app/Modules/{ModuleName}/lang/{locale}/{filename}.php）
        $modulesPath = dirname(dirname(dirname(__DIR__))) . '/app/Modules';
        if (is_dir($modulesPath)) {
            $modules = scandir($modulesPath);
            foreach ($modules as $module) {
                // 跳过.和..目录
                if ($module === '.' || $module === '..') {
                    continue;
                }
                
                $moduleLangFilePath = $modulesPath . '/' . $module . '/lang/' . $locale . '/' . $filename . '.php';
                if (file_exists($moduleLangFilePath)) {
                    $moduleLangData = include $moduleLangFilePath;
                    if (is_array($moduleLangData)) {
                        // 合并语言数据，模块语言文件优先级高于全局语言文件
                        $langData = self::arrayMergeRecursiveDistinct($langData, $moduleLangData);
                    }
                }
            }
        }
        
        // 缓存数据
        self::$langCache[$cacheKey] = $langData;
        
        return $langData;
    }

    /**
     * 递归合并数组，保留键值对，后面的数组会覆盖前面的数组
     *
     * @param array $array1 第一个数组
     * @param array $array2 第二个数组
     * @return array 合并后的数组
     */
    private static function arrayMergeRecursiveDistinct(array &$array1, array &$array2): array
    {
        $merged = $array1;
        
        foreach ($array2 as $key => &$value) {
            if (is_array($value) && isset($merged[$key]) && is_array($merged[$key])) {
                $merged[$key] = self::arrayMergeRecursiveDistinct($merged[$key], $value);
            } else {
                $merged[$key] = $value;
            }
        }
        
        return $merged;
    }

    /**
     * 替换占位符
     *
     * @param string $text 原始文本
     * @param array $replace 替换数组
     * @return string 替换后的文本
     */
    private static function replacePlaceholders(string $text, array $replace): string
    {
        foreach ($replace as $key => $value) {
            $text = str_replace('{' . $key . '}', $value, $text);
        }
        
        return $text;
    }

    /**
     * 清除语言缓存
     *
     * @param string|null $locale 语言，默认清除所有缓存
     * @param string|null $filename 文件名，默认清除指定语言的所有缓存
     * @return void
     */
    public static function clearCache(?string $locale = null, ?string $filename = null): void
    {
        if ($locale === null) {
            // 清除所有缓存
            self::$langCache = [];
        } elseif ($filename === null) {
            // 清除指定语言的所有缓存
            foreach (array_keys(self::$langCache) as $cacheKey) {
                if (strpos($cacheKey, $locale . ':') === 0) {
                    unset(self::$langCache[$cacheKey]);
                }
            }
        } else {
            // 清除指定语言和文件名的缓存
            $cacheKey = $locale . ':' . $filename;
            unset(self::$langCache[$cacheKey]);
        }
    }

    /**
     * 获取支持的语言列表
     *
     * @return array 支持的语言列表
     */
    public static function getSupportedLanguages(): array
    {
        return Config::get('i18n.supported', []);
    }

    /**
     * 获取默认语言
     *
     * @return string 默认语言
     */
    public static function getDefaultLanguage(): string
    {
        return Config::get('i18n.default', 'zh-CN');
    }
}