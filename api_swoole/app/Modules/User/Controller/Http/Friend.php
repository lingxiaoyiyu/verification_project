<?php

namespace App\Modules\User\Controller\Http;

use App\Base\BaseController;
use Library\Facades\DB;
use Library\Facades\Request;
use Library\Facades\Response;

/**
 * 获取好友列表（通过邀请码关系）
 */
class Friend extends BaseController
{
    private $page;
    private $pageSize;

    /**
     * 参数检查
     */
    protected function check()
    {
        if (!$this->userId) {
            throw new \Exception("未登录");
        }
        
        $this->page = (int)Request::post('page', 1);
        $this->pageSize = (int)Request::post('pageSize', 10);
        
        if ($this->page < 1) $this->page = 1;
        if ($this->pageSize < 1) $this->pageSize = 10;
        if ($this->pageSize > 100) $this->pageSize = 100;
    }

    /**
     * 业务处理
     */
    protected function service()
    {
        // 查询被当前用户邀请的用户（好友）
        $query = DB::table('tb_basic_sys_user')
            ->where('inviter_id', $this->userId)
            ->select([
                'id', 'username', 'nick_name', 'real_name',
                'phone', 'email', 'avatar', 'status', 'created_at'
            ]);
        
        // 获取总数
        $total = $query->count();
        
        // 分页查询
        $offset = ($this->page - 1) * $this->pageSize;
        $list = $query->orderBy('id', 'desc')
            ->offset($offset)
            ->limit($this->pageSize)
            ->get();
        
        // 格式化返回数据
        $result = [];
        foreach ($list as $item) {
            $result[] = [
                'id' => $item['id'],
                'username' => $item['username'],
                'nickName' => $item['nick_name'],
                'realName' => $item['real_name'],
                'phoneNumber' => $item['phone'],
                'email' => $item['email'],
                'avatar' => $item['avatar'],
                'status' => $item['status'],
                'createdAt' => $item['created_at']
            ];
        }
        
        return Response::success([
            'list' => $result,
            'total' => $total
        ]);
    }
}
