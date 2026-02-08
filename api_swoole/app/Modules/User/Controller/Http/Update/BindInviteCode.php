<?php

namespace App\Modules\User\Controller\Http\Update;

use App\Base\BaseController;
use Library\Facades\DB;
use Library\Facades\Request;
use Library\Facades\Response;
use Library\Facades\Validate;

/**
 * 绑定邀请码
 */
class BindInviteCode extends BaseController
{
    private $inviteCode;

    /**
     * 参数检查
     */
    protected function check()
    {
        if (!$this->userId) {
            throw new \Exception("未登录");
        }
        
        Validate::validate([
            'inviteCode' => ['text' => '邀请码', 'rules' => ['required']],
        ]);
        
        $this->inviteCode = trim(Request::post('inviteCode'));
        
        // 检查当前用户是否已绑定邀请人
        $user = DB::table('tb_basic_sys_user')->where('id', $this->userId)->first();
        if ($user['inviter_id']) {
            throw new \Exception("已绑定邀请人，不能重复绑定");
        }
        
        // 检查邀请码是否存在
        $inviter = DB::table('tb_basic_sys_user')
            ->where('invite_code', $this->inviteCode)
            ->first();
        if (!$inviter) {
            throw new \Exception("邀请码不存在");
        }
        
        // 不能绑定自己
        if ($inviter['id'] == $this->userId) {
            throw new \Exception("不能绑定自己的邀请码");
        }
    }

    /**
     * 业务处理
     */
    protected function service()
    {
        // 获取邀请人
        $inviter = DB::table('tb_basic_sys_user')
            ->where('invite_code', $this->inviteCode)
            ->first();
        
        $now = date('Y-m-d H:i:s');
        
        DB::table('tb_basic_sys_user')
            ->where('id', $this->userId)
            ->update([
                'inviter_id' => $inviter['id'],
                'updated_user_id' => $this->userId,
                'updated_at' => $now
            ]);
        
        return Response::success(null, '绑定成功');
    }
}
