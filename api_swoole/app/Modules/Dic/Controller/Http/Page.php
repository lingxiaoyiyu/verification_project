<?php

namespace App\Modules\Dic\Controller\Http;

use App\Base\BaseController;
use Library\Facades\DB;
use Library\Facades\Request;
use Library\Facades\Response;

/**
 * 字典组分页列表
 */
class Page extends BaseController
{
    private $page;
    private $pageSize;
    private $code;
    private $name;
    private $status;

    /**
     * 参数检查
     */
    protected function check()
    {
        $this->page = (int)Request::post('page', 1);
        $this->pageSize = (int)Request::post('pageSize', 10);
        $this->code = trim(Request::post('code', ''));
        $this->name = trim(Request::post('name', ''));
        $this->status = Request::post('status');
        
        if ($this->page < 1) $this->page = 1;
        if ($this->pageSize < 1) $this->pageSize = 10;
        if ($this->pageSize > 100) $this->pageSize = 100;
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
                'code' => $item['code'],
                'name' => $item['name'],
                'status' => $item['status'],
                'remark' => $item['remark'],
                'updatedAt' => $item['updated_at']
            ];
        }
        
        return Response::success([
            'list' => $result,
            'total' => $total
        ]);
    }
}
