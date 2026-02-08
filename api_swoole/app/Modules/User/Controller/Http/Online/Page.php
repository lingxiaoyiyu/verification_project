<?php

namespace App\Modules\User\Controller\Http\Online;

use App\Base\BaseController;
use Library\Facades\DB;
use Library\Facades\Redis;
use Library\Facades\Request;
use Library\Facades\Response;

/**
 * 在线用户分页列表
 */
class Page extends BaseController
{
    private $page;
    private $pageSize;
    private $username;
    private $realName;
    private $phoneNumber;
    private $nickName;
    private $email;
    private $status;
    private $roleIds;

    /**
     * 参数检查
     */
    protected function check()
    {
        $this->page = (int)Request::post('page', 1);
        $this->pageSize = (int)Request::post('pageSize', 10);
        $this->username = trim(Request::post('username', ''));
        $this->realName = trim(Request::post('realName', ''));
        $this->phoneNumber = trim(Request::post('phoneNumber', ''));
        $this->nickName = trim(Request::post('nickName', ''));
        $this->email = trim(Request::post('email', ''));
        $this->status = Request::post('status');
        $this->roleIds = Request::post('roleIds', []);
        
        if ($this->page < 1) $this->page = 1;
        if ($this->pageSize < 1) $this->pageSize = 10;
        if ($this->pageSize > 100) $this->pageSize = 100;
    }

    /**
     * 业务处理
     */
    protected function service()
    {
        // 获取所有在线Token
        $onlineTokens = Redis::keys('token:*');
        $onlineUserIds = [];
        $userTokenInfo = [];
        
        foreach ($onlineTokens as $tokenKey) {
            $tokenData = Redis::get($tokenKey);
            if ($tokenData) {
                $data = json_decode($tokenData, true);
                if (isset($data['uid'])) {
                    $userId = $data['uid'];
                    $onlineUserIds[] = $userId;
                    
                    if (!isset($userTokenInfo[$userId])) {
                        $userTokenInfo[$userId] = [
                            'lastActiveAt' => null,
                            'clientForms' => []
                        ];
                    }
                    
                    // 记录客户端类型
                    if (isset($data['clientFrom']) && !in_array($data['clientFrom'], $userTokenInfo[$userId]['clientForms'])) {
                        $userTokenInfo[$userId]['clientForms'][] = $data['clientFrom'];
                    }
                }
            }
        }
        
        $onlineUserIds = array_unique($onlineUserIds);
        
        if (empty($onlineUserIds)) {
            return Response::success([
                'list' => [],
                'total' => 0
            ]);
        }
        
        // 构建查询
        $query = DB::table('tb_basic_sys_user as u')
            ->whereIn('u.id', $onlineUserIds)
            ->select([
                'u.id', 'u.username', 'u.nick_name', 'u.real_name',
                'u.phone', 'u.email', 'u.status', 'u.avatar',
                'u.department_id', 'u.updated_at'
            ]);
        
        // 条件过滤
        if (!empty($this->username)) {
            $query->where('u.username', 'like', "%{$this->username}%");
        }
        if (!empty($this->realName)) {
            $query->where('u.real_name', 'like', "%{$this->realName}%");
        }
        if (!empty($this->phoneNumber)) {
            $query->where('u.phone', 'like', "%{$this->phoneNumber}%");
        }
        if (!empty($this->nickName)) {
            $query->where('u.nick_name', 'like', "%{$this->nickName}%");
        }
        if (!empty($this->email)) {
            $query->where('u.email', 'like', "%{$this->email}%");
        }
        if ($this->status !== null && $this->status !== '') {
            $query->where('u.status', $this->status);
        }
        
        // 按角色ID过滤
        if (!empty($this->roleIds)) {
            $query->whereIn('u.id', function($subQuery) {
                $subQuery->select('user_id')
                    ->from('tb_basic_sys_user_role_relation')
                    ->whereIn('role_id', $this->roleIds);
            });
        }
        
        // 获取总数
        $total = $query->count();
        
        // 分页查询
        $offset = ($this->page - 1) * $this->pageSize;
        $list = $query->orderBy('u.id', 'desc')
            ->offset($offset)
            ->limit($this->pageSize)
            ->get();
        
        // 获取部门信息
        $departmentIds = array_filter(array_column($list, 'department_id'));
        $departments = [];
        if (!empty($departmentIds)) {
            $deptList = DB::table('tb_basic_sys_department')
                ->whereIn('id', $departmentIds)
                ->get(['id', 'name']);
            foreach ($deptList as $dept) {
                $departments[$dept['id']] = $dept['name'];
            }
        }
        
        // 获取用户角色
        $userIds = array_column($list, 'id');
        $roleNames = [];
        if (!empty($userIds)) {
            $userRoles = DB::table('tb_basic_sys_user_role_relation as ur')
                ->join('tb_basic_sys_role as r', 'ur.role_id', '=', 'r.id')
                ->whereIn('ur.user_id', $userIds)
                ->get(['ur.user_id', 'r.name']);
            
            foreach ($userRoles as $ur) {
                if (!isset($roleNames[$ur['user_id']])) {
                    $roleNames[$ur['user_id']] = [];
                }
                $roleNames[$ur['user_id']][] = $ur['name'];
            }
        }
        
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
                'status' => $item['status'],
                'avatar' => $item['avatar'],
                'departmentName' => $departments[$item['department_id']] ?? '',
                'updatedAt' => $item['updated_at'],
                'roleNames' => $roleNames[$item['id']] ?? [],
                'lastActiveAt' => $userTokenInfo[$item['id']]['lastActiveAt'] ?? date('Y-m-d H:i:s'),
                'clientForms' => $userTokenInfo[$item['id']]['clientForms'] ?? []
            ];
        }
        
        return Response::success([
            'list' => $result,
            'total' => $total
        ]);
    }
}
