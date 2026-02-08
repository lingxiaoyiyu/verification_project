<?php

namespace App\Modules\Dic\Controller\Http\Update;

use App\Base\BaseController;
use Library\Facades\DB;
use Library\Facades\Request;
use Library\Facades\Response;
use Library\Facades\Validate;

/**
 * 更新字典组信息
 */
class Info extends BaseController
{
    private $id;
    private $code;
    private $name;
    private $status;
    private $remark;
    private $updatedAt;

    /**
     * 参数检查
     */
    protected function check()
    {
        Validate::validate([
            'id' => ['text' => '字典ID', 'rules' => ['required', 'integer', 'min:1']],
            'code' => ['text' => '字典编码', 'rules' => ['required', 'max:64']],
            'name' => ['text' => '字典名称', 'rules' => ['required', 'max:64']],
            'status' => ['text' => '状态', 'rules' => ['required', 'integer']],
            'updatedAt' => ['text' => '更新时间', 'rules' => ['required']],
        ]);
        
        $this->id = (int)Request::post('id');
        $this->code = trim(Request::post('code'));
        $this->name = trim(Request::post('name'));
        $this->status = (int)Request::post('status');
        $this->remark = trim(Request::post('remark', ''));
        $this->updatedAt = Request::post('updatedAt');
        
        // 检查字典是否存在
        $dic = DB::table('tb_basic_sys_dic')
            ->where('id', $this->id)
            ->where('is_delete', 0)
            ->first();
        if (!$dic) {
            throw new \Exception("字典不存在");
        }
        
        // 乐观锁检查
        if ($dic['updated_at'] !== $this->updatedAt) {
            throw new \Exception("数据已被修改，请刷新后重试");
        }
        
        // 检查编码唯一性（排除自身）
        $existCode = DB::table('tb_basic_sys_dic')
            ->where('code', $this->code)
            ->where('id', '!=', $this->id)
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
        
        DB::table('tb_basic_sys_dic')
            ->where('id', $this->id)
            ->update([
                'code' => $this->code,
                'name' => $this->name,
                'status' => $this->status,
                'remark' => $this->remark ?: null,
                'updated_user_id' => $this->userId,
                'updated_at' => $now
            ]);
        
        return Response::success([
            'updatedAt' => $now
        ], '字典更新成功');
    }
}
