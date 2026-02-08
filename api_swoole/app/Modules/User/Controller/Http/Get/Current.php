<?php

namespace App\Modules\User\Controller\Http\Get;

use App\Base\BaseController;
use Library\Facades\DB;
use Library\Facades\Response;

/**
 * 获取当前登录用户详情
 */
class Current extends BaseController
{
    /**
     * 参数检查
     */
    protected function check()
    {
        if (!$this->userId) {
            throw new \Exception("未登录");
        }
    }

    /**
     * 业务处理
     */
    protected function service()
    {
        $user = DB::table('tb_basic_sys_user as u')
            ->where('u.id', $this->userId)
            ->first();
        
        if (!$user) {
            throw new \Exception("用户不存在");
        }
        
        // 获取部门信息
        $departmentName = '';
        if ($user['department_id']) {
            $dept = DB::table('tb_basic_sys_department')
                ->where('id', $user['department_id'])
                ->first(['name']);
            $departmentName = $dept ? $dept['name'] : '';
        }
        
        // 获取用户角色
        $roleIds = [];
        $roleNames = [];
        $roles = [];
        $userRoles = DB::table('tb_basic_sys_user_role_relation as ur')
            ->join('tb_basic_sys_role as r', 'ur.role_id', '=', 'r.id')
            ->where('ur.user_id', $this->userId)
            ->get(['r.id', 'r.name', 'r.code']);
        
        foreach ($userRoles as $role) {
            $roleIds[] = $role['id'];
            $roleNames[] = $role['name'];
            $roles[] = $role['code'];
        }
        
        // 来源名称映射
        $sourceNames = [
            0 => '未知',
            1 => '管理员添加',
            2 => '手机注册',
            3 => '邮箱注册',
            4 => '无界unionid注册',
            5 => '微信公众号注册',
            6 => '微信小程序注册'
        ];
        
        return Response::success([
            'id' => $user['id'],
            'username' => $user['username'],
            'nickName' => $user['nick_name'],
            'realName' => $user['real_name'],
            'phoneNumber' => $user['phone'],
            'email' => $user['email'],
            'avatar' => $user['avatar'],
            'status' => $user['status'],
            'gender' => $user['gender'],
            'inviteCode' => $user['invite_code'],
            'introduction' => $user['introduction'],
            'sourceName' => $sourceNames[$user['source']] ?? '未知',
            'remark' => $user['remark'],
            'departmentId' => $user['department_id'],
            'departmentName' => $departmentName,
            'createdAt' => $user['created_at'],
            'updatedAt' => $user['updated_at'],
            'roleIds' => $roleIds,
            'roleNames' => $roleNames,
            'roles' => $roles
        ]);
    }
}
