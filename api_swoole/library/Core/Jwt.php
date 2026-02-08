<?php
namespace Library\Core;

use Library\Core\Config;
use Library\Core\ServerException;

class Jwt {

    // 头部
    private static $header = [ // 生成signature的算法
        'alg' => 'HS512',  // 算法类型
        'typ' => 'JWT'
    ];

    /**
     * 获取JWT密钥
     * 从环境变量或配置读取，避免硬编码
     *
     * @return string
     * @throws ServerException
     */
    private static function getKey(): string
    {
        $key = Config::get('jwt_key');
        if (empty($key) || $key === 'default_jwt_key_for_development_only') {
            throw new ServerException('JWT密钥未配置或使用了默认密钥，请检查配置文件', Config::get("result.code.error"));
        }
        return $key;
    }

    /**
     * 获取jwt token
     *
     * @param array $payload jwt载荷 格式如下非必须
     * [
     * 'iss'=>'jwt_admin', //该JWT的签发者
     * 'iat'=>time(), //签发时间
     * 'exp'=>time()+7200, //过期时间
     * 'nbf'=>time(), // 该时间之前不接收处理该Token
     * 'sub'=>'www.admin.com', //面向的用户
     * 'jti'=>md5(uniqid('JWT').time()) //该Token唯一标识
     * ]
     * @return bool|string
     */
    public static function getToken(array $payload) {
        if (!is_array($payload))
            return false;
        !isset($payload['iss']) && $payload['iss'] = 'jwt_admin'; // 该JWT的签发者
        !isset($payload['iat']) && $payload['iat'] = time(); // 签发时间
        !isset($payload['exp']) && $payload['exp'] = time()+Config::get('token_expire'); // 过期时间
        !isset($payload['nbf']) && $payload['nbf'] = time(); // 该时间之前不接收处理该Token
        !isset($payload['sub']) && $payload['sub'] = 'jwt_admin'; // 面向的用户 TODO 改成面向当前用户
        !isset($payload['jti']) && $payload['jti'] = md5(uniqid('JWT').time()); //// 该Token唯一标识

        $base64header = self::base64UrlEncode(json_encode(self::$header, JSON_UNESCAPED_UNICODE));
        $base64payload = self::base64UrlEncode(json_encode($payload, JSON_UNESCAPED_UNICODE));
        return $base64header . '.' . $base64payload . '.' . self::signature($base64header . '.' . $base64payload, self::getKey(), self::$header['alg']);
    }

    /**
     * 验证token是否有效,默认验证exp,nbf,iat时间
     *
     * @param string $Token 需要验证的token
     * @return bool|string
     */
    public static function verifyToken(string $token) {
        if (empty($token)) {
            throw new ServerException('token为空！', Config::get("result.code.forbidden"));
        }

        $tokens = explode('.', $token);
        if (count($tokens) != 3) {
            throw new ServerException('token格式错误！', Config::get("result.code.forbidden"));
        }

        list($base64header, $base64payload, $sign) = $tokens;

        // 获取jwt算法
        $base64decodeheader = json_decode(self::base64UrlDecode($base64header), JSON_OBJECT_AS_ARRAY);
        if (empty($base64decodeheader['alg'])) {
            throw new ServerException('token算法为空！', Config::get("result.code.forbidden"));
        }

        // 签名验证
        if (self::signature($base64header . '.' . $base64payload, self::getKey(), $base64decodeheader['alg']) !== $sign) {
            throw new ServerException('签名验证失败！', Config::get("result.code.forbidden"));
        }

        $payload = json_decode(self::base64UrlDecode($base64payload), JSON_OBJECT_AS_ARRAY);
        // 签发时间大于当前服务器时间，验证失败
        if (isset($payload['iat']) && $payload['iat'] > time()) {
            throw new ServerException('签发时间错误！', Config::get("result.code.forbidden"));
        }

        // 过期时间小于当前服务器时间，验证失败
        if (isset($payload['exp']) && $payload['exp'] < time()) {
            throw new ServerException('过期时间错误！', Config::get("result.code.forbidden"));
        }
        // 该nbf时间之前不接收处理该token
        if (isset($payload['nbf']) && $payload['nbf'] > time()) {
            throw new ServerException('nbf时间错误！', Config::get("result.code.forbidden"));
        }

        // TODO 验证面向的用户是否正确。
    }

    public static function getPayLoad($token) {
        if ($token) {
            $tokens = explode('.', $token);
            if (count($tokens) >= 3) {
                list($base64header, $base64payload, $sign) = $tokens;
                if ($base64payload) {
                    $payload = json_decode(self::base64UrlDecode($base64payload), JSON_OBJECT_AS_ARRAY);
                    return $payload;
                }
            }
        }
    }

    /**
     * base64UrlEncode https://jwt.io/ 中base64UrlEncode编码实现
     *
     * @param string $input 需要编码的字符串
     * @return string
     */
    public static function base64UrlEncode(string $input) {
        return str_replace('=', '', strtr(base64_encode($input), '+/', '-_'));
    }

    /**
     * base64UrlEncode https://jwt.io/ 中base64UrlEncode解码实现
     *
     * @param string $input 需要解码的字符串
     * @return bool|string
     */
    public static function base64UrlDecode(string $input) {
        $remainder = strlen($input) % 4;
        if ($remainder) {
            $addlen = 4 - $remainder;
            $input .= str_repeat('=', $addlen);
        }
        return base64_decode(strtr($input, '-_', '+/'));
    }

    /**
     * HMACSHA256签名 https://jwt.io/ 中HMACSHA256签名实现
     *
     * @param string $input 为base64UrlEncode(header).'.'.base64UrlEncode(payload)
     * @param string $key
     * @param string $alg 算法方式
     * @return mixed
     */
    private static function signature(string $input, string $key, string $alg = 'HS512') {
        $alg_config = ['HS256' => 'sha256', 'HS512'=>'sha512'];
        return self::base64UrlEncode(hash_hmac($alg_config[$alg], $input, $key, true));
    }
}