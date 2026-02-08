<?php

namespace App\Modules\Dic\Controller\Http;

use App\Base\BaseController;
use Library\Facades\DB;
use Library\Facades\Request;
use Library\Facades\Response;

/**
 * 字典组列表（不分页）
 */
class Query extends BaseController
{
    private $code;
    private $name;
    private $status;

    /**
     * 参数检查
     */
    protected function check()
    {
        $this->code = trim(Request::post('code', ''));
        $this->name = trim(Request::post('name', ''));
        $this->status = Request::post('status');
    }

    /**
     * 业务处理
     */
    protected function service()
    {
        $query = DB::table('tb_basic_sys_dic')
            ->where('is_delete', 0);
        
        // 条件过滤
        if (!empty($this->code)) {
            $query->where('code', 'like', "%{$this->code}%");
        }
        if (!empty($this->name)) {
            $query->where('name', 'like', "%{$this->name}%");
        }
        if ($this->status !== null && $this->status !== '') {
            $query->where('status', $this->status);
        }
        
        $list = $query->orderBy('id', 'asc')->get();
        
        // 格式化返回数据
        $result = [];
        foreach ($list as $item) {
            $result[] = [
                'id' => $item['id'],
                'code' => $item['code'],
                'name' => $item['name'],
                'status' => $item['status'],
                'remark' => $item['remark']
            ];
        }
        
        return Response::success([
            'list' => $result
        ]);
    }
}
