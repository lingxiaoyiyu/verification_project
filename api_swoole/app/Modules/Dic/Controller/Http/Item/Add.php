<?php

namespace App\Modules\Dic\Controller\Http\Item;

use App\Base\BaseController;
use Library\Facades\DB;
use Library\Facades\Request;
use Library\Facades\Response;
use Library\Facades\Validate;

/**
 * 添加字典项
 */
class Add extends BaseController
{
    private $dicId;
    private $label;
    private $value;
    private $status;
    private $order;
    private $remark;

    /**
     * 参数检查
     */
    protected function check()
    {
        Validate::validate([
            'dicId' => ['text' => '字典ID', 'rules' => ['required', 'integer', 'min:1']],
            'label' => ['text' => '显示标签', 'rules' => ['required', 'max:64']],
            'value' => ['text' => '字典值', 'rules' => ['required', 'max:64']],
            'status' => ['text' => '状态', 'rules' => ['required', 'integer']],
        ]);
        
        $this->dicId = (int)Request::post('dicId');
        $this->label = trim(Request::post('label'));
        $this->value = trim(Request::post('value'));
        $this->status = (int)Request::post('status');
        $this->order = (int)Request::post('order', 0);
        $this->remark = trim(Request::post('remark', ''));
        
        // 检查字典组是否存在
        $dic = DB::table('tb_basic_sys_dic')
            ->where('id', $this->dicId)
            ->where('is_delete', 0)
            ->first();
        if (!$dic) {
            throw new \Exception("字典组不存在");
        }
        
        // 检查字典值唯一性（同字典组下）
        $existValue = DB::table('tb_basic_sys_dic_item')
            ->where('dic_id', $this->dicId)
            ->where('value', $this->value)
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
        // 如果没有指定排序，计算最大排序值
        if ($this->order == 0) {
            $maxOrder = DB::table('tb_basic_sys_dic_item')
                ->where('dic_id', $this->dicId)
                ->where('is_delete', 0)
                ->max('order') ?? 0;
            $this->order = $maxOrder + 1;
        }
        
        $now = date('Y-m-d H:i:s');
        
        $itemId = DB::table('tb_basic_sys_dic_item')->insertGetId([
            'dic_id' => $this->dicId,
            'label' => $this->label,
            'value' => $this->value,
            'status' => $this->status,
            'order' => $this->order,
            'remark' => $this->remark ?: null,
            'is_delete' => 0,
            'created_user_id' => $this->userId,
            'updated_user_id' => $this->userId,
            'created_at' => $now,
            'updated_at' => $now
        ]);
        
        return Response::success([
            'id' => $itemId
        ], '字典项添加成功');
    }
}
