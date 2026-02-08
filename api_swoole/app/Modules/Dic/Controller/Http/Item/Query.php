<?php

namespace App\Modules\Dic\Controller\Http\Item;

use App\Base\BaseController;
use Library\Facades\DB;
use Library\Facades\Request;
use Library\Facades\Response;
use Library\Facades\Validate;

/**
 * 查询字典项列表
 */
class Query extends BaseController
{
    private $dicId;
    private $dicCode;
    private $status;

    /**
     * 参数检查
     */
    protected function check()
    {
        $this->dicId = Request::post('dicId');
        $this->dicCode = trim(Request::post('dicCode', ''));
        $this->status = Request::post('status');
        
        // dicId和dicCode至少提供一个
        if (empty($this->dicId) && empty($this->dicCode)) {
            throw new \Exception("请提供字典ID或字典编码");
        }
    }

    /**
     * 业务处理
     */
    protected function service()
    {
        // 如果提供的是dicCode，先查询dicId
        $dicId = $this->dicId;
        if (empty($dicId) && !empty($this->dicCode)) {
            $dic = DB::table('tb_basic_sys_dic')
                ->where('code', $this->dicCode)
                ->where('is_delete', 0)
                ->first();
            if (!$dic) {
                return Response::success(['list' => []]);
            }
            $dicId = $dic['id'];
        }
        
        $query = DB::table('tb_basic_sys_dic_item')
            ->where('dic_id', $dicId)
            ->where('is_delete', 0);
        
        // 状态过滤
        if ($this->status !== null && $this->status !== '') {
            $query->where('status', $this->status);
        }
        
        $list = $query->orderBy('order', 'asc')->get();
        
        // 格式化返回数据
        $result = [];
        foreach ($list as $item) {
            $result[] = [
                'id' => $item['id'],
                'dicId' => $item['dic_id'],
                'label' => $item['label'],
                'value' => $item['value'],
                'status' => $item['status'],
                'order' => $item['order'],
                'remark' => $item['remark'],
                'updatedAt' => $item['updated_at']
            ];
        }
        
        return Response::success([
            'list' => $result
        ]);
    }
}
