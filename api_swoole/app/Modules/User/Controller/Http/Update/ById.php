<?php

namespace App\Modules\User\Controller\Http\Update;

use App\Base\BaseController;
use Library\Facades\DB;
use Library\Facades\Request;
use Library\Facades\Response;
use Library\Facades\Validate;

/**
 * 更新指定用户信息
 */
class ById extends BaseController
{
    private $id;
    private $username;
    private $phoneNumber;
    private $email;
    private $realName;
    private $nickName;
    private $roleIds;
    private $status;
    private $remark;
    private $departmentId;
    private $updatedAt;

    /**
     * 参数检查
     */
    protected function check()
    {
        Validate::validate([
            'id' => ['text' => '用户ID', 'rules' => ['required', 'integer', 'min:1']],
            'username' => ['text' => '用户名', 'rules' => ['required', 'min:3', 'max:32']],
            'roleIds' => ['text' => '角色', 'rules' => ['required', 'array']],
        ]);
        
        $this->id = (int)Request::post('id');
        $this->username = trim(Request::post('username'));
        $this->phoneNumber = trim(Request::post('phoneNumber', ''));
        $this->email = trim(Request::post('email', ''));
        $this->realName = trim(Request::post('realName', ''));
        $this->nickName = trim(Request::post('nickName', ''));
        $this->roleIds = Request::post('roleIds', []);
        $this->status = Request::post('status');
        $this->remark = trim(Request::post('remark', ''));
        $this->departmentId = Request::post('departmentId');
        $this->updatedAt = Request::post('updatedAt');
        
        // 检查用户是否存在
        $user = DB::table('tb_basic_sys_user')->where('id', $this->id)->first();
        if (!$user) {
            throw new \Exception("用户不存在");
        }
        
        // 乐观锁检查
        if ($this->updatedAt && $user['updated_at'] !== $this->updatedAt) {
            throw new \Exception("数据已被修改，请刷新后重试");
        }
        
        // 检查用户名唯一性（排除当前用户）
        $existUser = DB::table('tb_basic_sys_user')
            ->where('username', $this->username)
            ->where('id', '!=', $this->id)
            ->first();
        if ($existUser) {
            throw new \Exception("用户名已存在");
        }
        
        // 检查手机号唯一性
        if (!empty($this->phoneNumber)) {
            $existPhone = DB::table('tb_basic_sys_user')
                ->where('phone', $this->phoneNumber)
                ->where('id', '!=', $this->id)
                ->first();
            if ($existPhone) {
                throw new \Exception("手机号已存在");
            }
        }
        
        // 检查邮箱唯一性
        if (!empty($this->email)) {
            $existEmail = DB::table('tb_basic_sys_user')
                ->where('email', $this->email)
                ->where('id', '!=', $this->id)
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
            $now = date('Y-m-d H:i:s');
            
            // 更新用户信息
            $updateData = [
                'username' => $this->username,
                'phone' => $this->phoneNumber ?: null,
                'email' => $this->email ?: null,
                'real_name' => $this->realName ?: null,
                'nick_name' => $this->nickName ?: null,
                'remark' => $this->remark ?: null,
                'department_id' => $this->departmentId ?: null,
                'updated_user_id' => $this->userId,
                'updated_at' => $now
            ];
            
            if ($this->status !== null && $this->status !== '') {
                $updateData['status'] = (int)$this->status;
            }
            
            DB::table('tb_basic_sys_user')
                ->where('id', $this->id)
                ->update($updateData);
            
            // 获取现有角色
            $existingRoles = DB::table('tb_basic_sys_user_role_relation')
                ->where('user_id', $this->id)
                ->pluck('role_id');
            
            // 计算需要删除和添加的角色
            $toDelete = array_diff($existingRoles, $this->roleIds);
            $toAdd = array_diff($this->roleIds, $existingRoles);
            
            // 删除不在新列表中的角色
            if (!empty($toDelete)) {
                DB::table('tb_basic_sys_user_role_relation')
                    ->where('user_id', $this->id)
                    ->whereIn('role_id', $toDelete)
                    ->delete();
            }
            
            // 添加新角色
            foreach ($toAdd as $roleId) {
                DB::table('tb_basic_sys_user_role_relation')->insert([
                    'user_id' => $this->id,
                    'role_id' => $roleId
                ]);
            }
            
            DB::commit();
            
            return Response::success([
                'updatedAt' => $now
            ]);
        } catch (\Exception $e) {
            DB::rollBack();
            throw $e;
        }
    }
}
