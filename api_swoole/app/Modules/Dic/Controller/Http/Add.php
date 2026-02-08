<?php

namespace App\Modules\Dic\Controller\Http;

use App\Base\BaseController;
use Library\Facades\DB;
use Library\Facades\Request;
use Library\Facades\Response;
use Library\Facades\Validate;

/**
 * 添加字典组
 */
class Add extends BaseController
{
    private $code;
    private $name;
    private $status;
    private $remark;

    /**
     * 参数检查
     */
    protected function check()
    {
        Validate::validate([
            'code' => ['text' => '字典编码', 'rules' => ['required', 'max:64']],
            'name' => ['text' => '字典名称', 'rules' => ['required', 'max:64']],
            'status' => ['text' => '状态', 'rules' => ['required', 'integer']],
        ]);
        
        $this->code = trim(Request::post('code'));
        $this->name = trim(Request::post('name'));
        $this->status = (int)Request::post('status');
        $this->remark = trim(Request::post('remark', ''));
        
        // 检查编码唯一性
        $existCode = DB::table('tb_basic_sys_dic')
            ->where('code', $this->code)
            ->where('is_delete', 0)
            ->first();
        if ($existCode) {
            throw new \Exception("字典编码已存在");
        }
    }

    /**
     * 业务处理
     */
    protected function service()
    {
        $now = date('Y-m-d H:i:s');
        
        $dicId = DB::table('tb_basic_sys_dic')->insertGetId([
            'code' => $this->code,
            'name' => $this->name,
            'status' => $this->status,
            'remark' => $this->remark ?: null,
            'is_delete' => 0,
            'created_user_id' => $this->userId,
            'updated_user_id' => $this->userId,
            'created_at' => $now,
            'updated_at' => $now
        ]);
        
        return Response::success([
            'id' => $dicId
        ], '字典添加成功');
    }
}
