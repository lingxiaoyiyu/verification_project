<?php

namespace App\Modules\User\Controller\Http;

use App\Base\BaseController;
use Library\Facades\DB;
use Library\Facades\Request;
use Library\Facades\Response;

/**
 * 判断用户属性是否存在
 */
class IsExist extends BaseController
{
    private $username;
    private $nickname;
    private $phoneNumber;

    /**
     * 参数检查
     */
    protected function check()
    {
        $this->username = trim(Request::post('username', ''));
        $this->nickname = trim(Request::post('nickname', ''));
        $this->phoneNumber = trim(Request::post('phoneNumber', ''));
    }

    /**
     * 业务处理
     */
    protected function service()
    {
        // 检查用户名
        if (!empty($this->username)) {
            $query = DB::table('tb_basic_sys_user')
                ->where('username', $this->username);
            
            // 排除当前用户
            if ($this->userId) {
                $query->where('id', '!=', $this->userId);
            }
            
            if ($query->first()) {
                throw new \Exception("用户名已存在");
            }
        }
        
        // 检查昵称
        if (!empty($this->nickname)) {
            $query = DB::table('tb_basic_sys_user')
                ->where('nick_name', $this->nickname);
            
            if ($this->userId) {
                $query->where('id', '!=', $this->userId);
            }
            
            if ($query->first()) {
                throw new \Exception("昵称已存在");
            }
        }
        
        // 检查手机号
        if (!empty($this->phoneNumber)) {
            $query = DB::table('tb_basic_sys_user')
                ->where('phone', $this->phoneNumber);
            
            if ($this->userId) {
                $query->where('id', '!=', $this->userId);
            }
            
            if ($query->first()) {
                throw new \Exception("手机号已存在");
            }
        }
        
        return Response::success(null);
    }
}
