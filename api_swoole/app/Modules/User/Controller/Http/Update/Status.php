<?php

namespace App\Modules\User\Controller\Http\Update;

use App\Base\BaseController;
use Library\Facades\DB;
use Library\Facades\Request;
use Library\Facades\Response;
use Library\Facades\Validate;

/**
 * 修改用户状态
 */
class Status extends BaseController
{
    private $id;
    private $status;
    private $updatedAt;

    /**
     * 参数检查
     */
    protected function check()
    {
        Validate::validate([
            'id' => ['text' => '用户ID', 'rules' => ['required', 'integer', 'min:1']],
            'status' => ['text' => '状态', 'rules' => ['required', 'integer']],
            'updatedAt' => ['text' => '更新时间', 'rules' => ['required']],
        ]);
        
        $this->id = (int)Request::post('id');
        $this->status = (int)Request::post('status');
        $this->updatedAt = Request::post('updatedAt');
        
        // 检查用户是否存在
        $user = DB::table('tb_basic_sys_user')->where('id', $this->id)->first();
        if (!$user) {
            throw new \Exception("用户不存在");
        }
        
        // 乐观锁检查
        if ($user['updated_at'] !== $this->updatedAt) {
            throw new \Exception("数据已被修改，请刷新后重试");
        }
        
        // 验证状态值
        if (!in_array($this->status, [0, 1, 2])) {
            throw new \Exception("无效的状态值");
        }
    }

    /**
     * 业务处理
     */
    protected function service()
    {
        $now = date('Y-m-d H:i:s');
        
        DB::table('tb_basic_sys_user')
            ->where('id', $this->id)
            ->update([
                'status' => $this->status,
                'updated_user_id' => $this->userId,
                'updated_at' => $now
            ]);
        
        return Response::success([
            'updatedAt' => $now
        ]);
    }
}
