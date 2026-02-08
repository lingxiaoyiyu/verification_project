<?php

namespace App\Modules\User\Controller\Http\Get;

use App\Base\BaseController;
use Library\Facades\DB;
use Library\Facades\Request;
use Library\Facades\Response;
use Library\Facades\Validate;

/**
 * 获取指定用户详情
 */
class ById extends BaseController
{
    private $id;

    /**
     * 参数检查
     */
    protected function check()
    {
        Validate::validate([
            'id' => ['text' => '用户ID', 'rules' => ['required', 'integer', 'min:1']],
        ]);
        
        $this->id = (int)Request::post('id');
        
        // 检查用户是否存在
        $user = DB::table('tb_basic_sys_user')->where('id', $this->id)->first();
        if (!$user) {
            throw new \Exception("用户不存在");
        }
    }

    /**
     * 业务处理
     */
    protected function service()
    {
        return $this->getUserDetail($this->id);
    }

    /**
     * 获取用户详情
     */
    protected function getUserDetail($userId)
    {
        $user = DB::table('tb_basic_sys_user as u')
            ->where('u.id', $userId)
            ->first();
        
        // 获取部门信息
        $departmentName = '';
        if ($user['department_id']) {
            $dept = DB::table('tb_basic_sys_department')
                ->where('id', $user['department_id'])
                ->first(['name']);
            $departmentName = $dept ? $dept['name'] : '';
        }
        
        // 获取邀请人信息
        $inviterUsername = '';
        if ($user['inviter_id']) {
            $inviter = DB::table('tb_basic_sys_user')
                ->where('id', $user['inviter_id'])
                ->first(['username']);
            $inviterUsername = $inviter ? $inviter['username'] : '';
        }
        
        // 获取创建人信息
        $createdUserName = '';
        if ($user['created_user_id']) {
            $creator = DB::table('tb_basic_sys_user')
                ->where('id', $user['created_user_id'])
                ->first(['username']);
            $createdUserName = $creator ? $creator['username'] : '';
        }
        
        // 获取更新人信息
        $updatedUserName = '';
        if ($user['updated_user_id']) {
            $updater = DB::table('tb_basic_sys_user')
                ->where('id', $user['updated_user_id'])
                ->first(['username']);
            $updatedUserName = $updater ? $updater['username'] : '';
        }
        
        // 获取用户角色
        $roleIds = [];
        $roleNames = [];
        $roles = [];
        $userRoles = DB::table('tb_basic_sys_user_role_relation as ur')
            ->join('tb_basic_sys_role as r', 'ur.role_id', '=', 'r.id')
            ->where('ur.user_id', $userId)
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
            'inviterUsername' => $inviterUsername,
            'introduction' => $user['introduction'],
            'sourceName' => $sourceNames[$user['source']] ?? '未知',
            'remark' => $user['remark'],
            'departmentId' => $user['department_id'],
            'departmentName' => $departmentName,
            'createdAt' => $user['created_at'],
            'createdUserName' => $createdUserName,
            'updatedAt' => $user['updated_at'],
            'updatedUserName' => $updatedUserName,
            'roleIds' => $roleIds,
            'roleNames' => $roleNames,
            'roles' => $roles,
            'wechatUnionid' => $user['wechat_unionid'] ?? '',
            'wechatOfficialAccountOpenid' => $user['wechat_official_account_openid'] ?? '',
            'wujieUnionid' => $user['wujie_unionid'] ?? ''
        ]);
    }
}
