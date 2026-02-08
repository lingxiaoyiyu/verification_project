<?php

namespace App\Modules\Dic\Controller\Http\Item\Update;

use App\Base\BaseController;
use Library\Facades\DB;
use Library\Facades\Request;
use Library\Facades\Response;
use Library\Facades\Validate;

/**
 * 更新字典项信息
 */
class Info extends BaseController
{
    private $id;
    private $label;
    private $value;
    private $status;
    private $order;
    private $remark;
    private $updatedAt;

    /**
     * 参数检查
     */
    protected function check()
    {
        Validate::validate([
            'id' => ['text' => '字典项ID', 'rules' => ['required', 'integer', 'min:1']],
            'label' => ['text' => '显示标签', 'rules' => ['required', 'max:64']],
            'value' => ['text' => '字典值', 'rules' => ['required', 'max:64']],
            'status' => ['text' => '状态', 'rules' => ['required', 'integer']],
            'updatedAt' => ['text' => '更新时间', 'rules' => ['required']],
        ]);
        
        $this->id = (int)Request::post('id');
        $this->label = trim(Request::post('label'));
        $this->value = trim(Request::post('value'));
        $this->status = (int)Request::post('status');
        $this->order = (int)Request::post('order', 0);
        $this->remark = trim(Request::post('remark', ''));
        $this->updatedAt = Request::post('updatedAt');
        
        // 检查字典项是否存在
        $item = DB::table('tb_basic_sys_dic_item')
            ->where('id', $this->id)
            ->where('is_delete', 0)
            ->first();
        if (!$item) {
            throw new \Exception("字典项不存在");
        }
        
        // 乐观锁检查
        if ($item['updated_at'] !== $this->updatedAt) {
            throw new \Exception("数据已被修改，请刷新后重试");
        }
        
        // 检查字典值唯一性（排除自身，同字典组下）
        $existValue = DB::table('tb_basic_sys_dic_item')
            ->where('dic_id', $item['dic_id'])
            ->where('value', $this->value)
            ->where('id', '!=', $this->id)
            ->where('is_delete', 0)
            ->first();
        if ($existValue) {
            throw new \Exception("字典值已存在");
        }
    }

    /**
     * 业务处理
     */
    protected function service()
    {
        $now = date('Y-m-d H:i:s');
        
        DB::table('tb_basic_sys_dic_item')
            ->where('id', $this->id)
            ->update([
                'label' => $this->label,
                'value' => $this->value,
                'status' => $this->status,
                'order' => $this->order,
                'remark' => $this->remark ?: null,
                'updated_user_id' => $this->userId,
                'updated_at' => $now
            ]);
        
        return Response::success([
            'updatedAt' => $now
        ], '字典项更新成功');
    }
}
