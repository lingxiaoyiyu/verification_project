<?php
namespace App\Modules\Index\Controller\Http;

use App\Base\BaseController;
use Library\Facades\Request;
use Library\Facades\Response;
use Library\Facades\Validate;

/**
 * IP地址查询
 * GET /basic/ip2region
 */
class Ip2Region extends BaseController
{
    protected bool $requireAuth = false;

    protected function check(): void
    {
        $validator = Validate::make(Request::all(), [
            'ip' => 'nullable|ip',
        ], [
            'ip.ip' => 'IP地址格式不正确',
        ]);

        if ($validator->fails()) {
            throw new \Exception($validator->errors()->first());
        }
    }

    protected function service(): string
    {
        $ip = Request::input('ip');
        
        // 如果没有传IP，获取客户端IP
        if (empty($ip)) {
            $ip = Request::ip();
        }

        // IP地址解析
        $region = $this->getRegionByIp($ip);

        return Response::success([
            'ip' => $ip,
            'region' => $region,
        ]);
    }

    /**
     * 根据IP获取地区信息
     */
    private function getRegionByIp(string $ip): array
    {
        // 这里可以集成ip2region库进行IP解析
        // 简单实现返回基础信息
        
        // 判断是否为内网IP
        if ($this->isPrivateIp($ip)) {
            return [
                'country' => '内网',
                'province' => '',
                'city' => '',
                'isp' => '内网IP',
            ];
        }

        // 实际项目中应使用ip2region等库解析
        return [
            'country' => '中国',
            'province' => '',
            'city' => '',
            'isp' => '',
        ];
    }

    /**
     * 判断是否为内网IP
     */
    private function isPrivateIp(string $ip): bool
    {
        $longIp = ip2long($ip);
        if ($longIp === false) {
            return false;
        }

        // 10.0.0.0 - 10.255.255.255
        if ($longIp >= ip2long('10.0.0.0') && $longIp <= ip2long('10.255.255.255')) {
            return true;
        }

        // 172.16.0.0 - 172.31.255.255
        if ($longIp >= ip2long('172.16.0.0') && $longIp <= ip2long('172.31.255.255')) {
            return true;
        }

        // 192.168.0.0 - 192.168.255.255
        if ($longIp >= ip2long('192.168.0.0') && $longIp <= ip2long('192.168.255.255')) {
            return true;
        }

        // 127.0.0.0 - 127.255.255.255
        if ($longIp >= ip2long('127.0.0.0') && $longIp <= ip2long('127.255.255.255')) {
            return true;
        }

        return false;
    }
}
