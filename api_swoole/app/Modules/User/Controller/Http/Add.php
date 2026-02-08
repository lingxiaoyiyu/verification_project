<?php

namespace App\Modules\User\Controller\Http;

use App\Base\BaseController;
use Library\Facades\DB;
use Library\Facades\Request;
use Library\Facades\Response;
use Library\Facades\Validate;

/**
 * 添加用户
 */
class Add extends BaseController
{
    private $username;
    private $password;
    private $phoneNumber;
    private $email;
    private $realName;
    private $nickName;
    private $roleIds;
    private $status;
    private $remark;
    private $departmentId;

    /**
     * 参数检查
     */
    protected function check()
    {
        Validate::validate([
            'username' => ['text' => '用户名', 'rules' => ['required', 'min:3', 'max:32']],
            'password' => ['text' => '密码', 'rules' => ['required', 'min:6', 'max:64']],
            'roleIds' => ['text' => '角色', 'rules' => ['required', 'array']],
        ]);
        
        $this->username = trim(Request::post('username'));
        $this->password = Request::post('password');
        $this->phoneNumber = trim(Request::post('phoneNumber', ''));
        $this->email = trim(Request::post('email', ''));
        $this->realName = trim(Request::post('realName', ''));
        $this->nickName = trim(Request::post('nickName', ''));
        $this->roleIds = Request::post('roleIds', []);
        $this->status = (int)Request::post('status', 0);
        $this->remark = trim(Request::post('remark', ''));
        $this->departmentId = Request::post('departmentId');
        
        // 检查用户名是否已存在
        $existUser = DB::table('tb_basic_sys_user')
            ->where('username', $this->username)
            ->first();
        if ($existUser) {
            throw new \Exception("用户名已存在");
        }
        
        // 检查手机号是否已存在
        if (!empty($this->phoneNumber)) {
            $existPhone = DB::table('tb_basic_sys_user')
                ->where('phone', $this->phoneNumber)
                ->first();
            if ($existPhone) {
                throw new \Exception("手机号已存在");
            }
        }
        
        // 检查邮箱是否已存在
        if (!empty($this->email)) {
            $existEmail = DB::table('tb_basic_sys_user')
                ->where('email', $this->email)
                ->first();
            if ($existEmail) {
                throw new \Exception("邮箱已存在");
            }
        }
        
        // 验证角色ID有效性
        if (!empty($this->roleIds)) {
            $roleCount = DB::table('tb_basic_sys_role')
                ->whereIn('id', $this->roleIds)
                ->count();
            if ($roleCount !== count($this->roleIds)) {
                throw new \Exception("角色ID无效");
            }
        }
    }

    /**
     * 业务处理
     */
    protected function service()
    {
        DB::beginTransaction();
        
        try {
            // 生成邀请码
            $inviteCode = $this->generateRandomString(6);
            
            // 创建用户
            $userId = DB::table('tb_basic_sys_user')->insertGetId([
                'username' => $this->username,
                'password' => md5($this->password),
                'phone' => $this->phoneNumber ?: null,
                'email' => $this->email ?: null,
                'real_name' => $this->realName ?: null,
                'nick_name' => $this->nickName ?: null,
                'invite_code' => $inviteCode,
                'status' => $this->status,
                'remark' => $this->remark ?: null,
                'department_id' => $this->departmentId ?: null,
                'source' => 1, // 管理员添加
                'created_user_id' => $this->userId,
                'updated_user_id' => $this->userId,
                'created_at' => date('Y-m-d H:i:s'),
                'updated_at' => date('Y-m-d H:i:s')
            ]);
            
            // 添加用户角色关联
            foreach ($this->roleIds as $roleId) {
                DB::table('tb_basic_sys_user_role_relation')->insert([
                    'user_id' => $userId,
                    'role_id' => $roleId
                ]);
            }
            
            DB::commit();
            
            return Response::success([
                'id' => $userId
            ], '用户添加成功');
        } catch (\Exception $e) {
            DB::rollBack();
            throw $e;
        }
    }

    /**
     * 生成随机字符串
     */
    private function generateRandomString($length)
    {
        $chars = 'ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789';
        $str = '';
        for ($i = 0; $i < $length; $i++) {
            $str .= $chars[mt_rand(0, strlen($chars) - 1)];
        }
        return $str;
    }
}
