<?php

namespace App\Modules\User\Controller\Http\Update;

use App\Base\BaseController;
use Library\Facades\DB;
use Library\Facades\Request;
use Library\Facades\Response;

/**
 * 更新当前用户信息
 */
class Current extends BaseController
{
    private $nickName;
    private $realName;
    private $phoneNumber;
    private $email;
    private $avatar;
    private $gender;
    private $introduction;

    /**
     * 参数检查
     */
    protected function check()
    {
        if (!$this->userId) {
            throw new \Exception("未登录");
        }
        
        $this->nickName = trim(Request::post('nickName', ''));
        $this->realName = trim(Request::post('realName', ''));
        $this->phoneNumber = trim(Request::post('phoneNumber', ''));
        $this->email = trim(Request::post('email', ''));
        $this->avatar = trim(Request::post('avatar', ''));
        $this->gender = Request::post('gender');
        $this->introduction = trim(Request::post('introduction', ''));
        
        // 检查手机号唯一性
        if (!empty($this->phoneNumber)) {
            $existPhone = DB::table('tb_basic_sys_user')
                ->where('phone', $this->phoneNumber)
                ->where('id', '!=', $this->userId)
                ->first();
            if ($existPhone) {
                throw new \Exception("手机号已被使用");
            }
        }
        
        // 检查邮箱唯一性
        if (!empty($this->email)) {
            $existEmail = DB::table('tb_basic_sys_user')
                ->where('email', $this->email)
                ->where('id', '!=', $this->userId)
                ->first();
            if ($existEmail) {
                throw new \Exception("邮箱已被使用");
            }
        }
    }

    /**
     * 业务处理
     */
    protected function service()
    {
        $now = date('Y-m-d H:i:s');
        
        $updateData = [
            'updated_user_id' => $this->userId,
            'updated_at' => $now
        ];
        
        if (!empty($this->nickName)) {
            $updateData['nick_name'] = $this->nickName;
        }
        if (!empty($this->realName)) {
            $updateData['real_name'] = $this->realName;
        }
        if (!empty($this->phoneNumber)) {
            $updateData['phone'] = $this->phoneNumber;
        }
        if (!empty($this->email)) {
            $updateData['email'] = $this->email;
        }
        if (!empty($this->avatar)) {
            $updateData['avatar'] = $this->avatar;
        }
        if ($this->gender !== null && $this->gender !== '') {
            $updateData['gender'] = (int)$this->gender;
        }
        if (!empty($this->introduction)) {
            $updateData['introduction'] = $this->introduction;
        }
        
        DB::table('tb_basic_sys_user')
            ->where('id', $this->userId)
            ->update($updateData);
        
        return Response::success([
            'updatedAt' => $now
        ]);
    }
}
