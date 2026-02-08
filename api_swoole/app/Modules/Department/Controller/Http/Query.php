<?php

namespace App\Modules\Department\Controller\Http;

use App\Base\BaseController;
use Library\Facades\DB;
use Library\Facades\Request;
use Library\Facades\Response;

/**
 * 查询部门列表
 */
class Query extends BaseController
{
    private $name;
    private $status;

    /**
     * 参数检查
     */
    protected function check()
    {
        $this->name = trim(Request::post('name', ''));
        $this->status = Request::post('status');
    }

    /**
     * 业务处理
     */
    protected function service()
    {
        $query = DB::table('tb_basic_sys_department')
            ->where('is_delete', 0);
        
        // 条件过滤
        if (!empty($this->name)) {
            $query->where('name', 'like', "%{$this->name}%");
        }
        if ($this->status !== null && $this->status !== '') {
            $query->where('status', $this->status);
        }
        
        // 查询列表
        $list = $query->orderBy('order', 'asc')->get();
        
        // 获取创建人和更新人信息
        $userIds = array_unique(array_merge(
            array_filter(array_column($list, 'created_user_id')),
            array_filter(array_column($list, 'updated_user_id'))
        ));
        
        $users = [];
        if (!empty($userIds)) {
            $userList = DB::table('tb_basic_sys_user')
                ->whereIn('id', $userIds)
                ->get(['id', 'username']);
            foreach ($userList as $user) {
                $users[$user['id']] = $user['username'];
            }
        }
        
        // 格式化返回数据
        $result = [];
        foreach ($list as $item) {
            $result[] = [
                'id' => $item['id'],
                'parentId' => $item['parent_id'],
                'name' => $item['name'],
                'status' => $item['status'],
                'remark' => $item['remark'],
                'order' => $item['order'],
                'createdAt' => $item['created_at'],
                'createdUserName' => $users[$item['created_user_id']] ?? '',
                'updatedAt' => $item['updated_at'],
                'updatedUserName' => $users[$item['updated_user_id']] ?? ''
            ];
        }
        
        return Response::success([
            'list' => $result
        ]);
    }
}
