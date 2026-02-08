<?php

namespace App\Modules\Role\Controller\Http;

use App\Base\BaseController;
use Library\Facades\DB;
use Library\Facades\Request;
use Library\Facades\Response;
use Library\Facades\Validate;

/**
 * 添加角色
 */
class Add extends BaseController
{
    private $name;
    private $identifying;
    private $status;
    private $remark;
    private $menuIdList;

    /**
     * 参数检查
     */
    protected function check()
    {
        Validate::validate([
            'name' => ['text' => '角色名称', 'rules' => ['required', 'max:64']],
            'identifying' => ['text' => '角色标识', 'rules' => ['required', 'max:32']],
            'status' => ['text' => '状态', 'rules' => ['required', 'integer']],
        ]);
        
        $this->name = trim(Request::post('name'));
        $this->identifying = trim(Request::post('identifying'));
        $this->status = (int)Request::post('status');
        $this->remark = trim(Request::post('remark', ''));
        $this->menuIdList = Request::post('menuIdList', []);
        
        // 检查角色名称是否已存在
        $existName = DB::table('tb_basic_sys_role')
            ->where('name', $this->name)
            ->where('is_delete', 0)
            ->first();
        if ($existName) {
            throw new \Exception("角色名称已存在");
        }
        
        // 检查角色标识是否已存在
        $existCode = DB::table('tb_basic_sys_role')
            ->where('code', $this->identifying)
            ->where('is_delete', 0)
            ->first();
        if ($existCode) {
            throw new \Exception("角色标识已存在");
        }
        
        // 验证菜单ID有效性
        if (!empty($this->menuIdList)) {
            $menuCount = DB::table('tb_basic_sys_menu')
                ->whereIn('id', $this->menuIdList)
                ->where('is_delete', 0)
                ->count();
            if ($menuCount !== count($this->menuIdList)) {
                throw new \Exception("菜单ID无效");
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
            // 创建角色
            $roleId = DB::table('tb_basic_sys_role')->insertGetId([
                'name' => $this->name,
                'code' => $this->identifying,
                'status' => $this->status,
                'remark' => $this->remark ?: null,
                'is_delete' => 0,
                'created_user_id' => $this->userId,
                'updated_user_id' => $this->userId,
                'created_at' => date('Y-m-d H:i:s'),
                'updated_at' => date('Y-m-d H:i:s')
            ]);
            
            // 自动添加基础菜单权限(ID 1和2)
            $menuIds = array_unique(array_merge([1, 2], $this->menuIdList ?: []));
            
            // 添加角色菜单关联
            foreach ($menuIds as $menuId) {
                DB::table('tb_basic_sys_role_menu_relation')->insert([
                    'role_id' => $roleId,
                    'menu_id' => $menuId,
                    'is_delete' => 0
                ]);
            }
            
            DB::commit();
            
            return Response::success([
                'id' => $roleId
            ], '角色添加成功');
        } catch (\Exception $e) {
            DB::rollBack();
            throw $e;
        }
    }
}
