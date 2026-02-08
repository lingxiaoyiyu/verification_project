<?php

namespace App\Modules\Role\Controller\Http;

use App\Base\BaseController;
use Library\Facades\DB;
use Library\Facades\Request;
use Library\Facades\Response;

/**
 * 角色分页列表
 */
class Page extends BaseController
{
    private $page;
    private $pageSize;
    private $name;
    private $status;
    private $identifying;

    /**
     * 参数检查
     */
    protected function check()
    {
        $this->page = (int)Request::post('page', 1);
        $this->pageSize = (int)Request::post('pageSize', 10);
        $this->name = trim(Request::post('name', ''));
        $this->status = Request::post('status');
        $this->identifying = trim(Request::post('identifying', ''));
        
        if ($this->page < 1) $this->page = 1;
        if ($this->pageSize < 1) $this->pageSize = 10;
        if ($this->pageSize > 100) $this->pageSize = 100;
    }

    /**
     * 业务处理
     */
    protected function service()
    {
        $query = DB::table('tb_basic_sys_role')
            ->where('is_delete', 0)
            ->select(['id', 'name', 'code', 'remark', 'status', 'updated_at']);
        
        // 条件过滤
        if (!empty($this->name)) {
            $query->where('name', 'like', "%{$this->name}%");
        }
        if ($this->status !== null && $this->status !== '') {
            $query->where('status', $this->status);
        }
        if (!empty($this->identifying)) {
            $query->where('code', 'like', "%{$this->identifying}%");
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
                'name' => $item['name'],
                'identifying' => $item['code'],
                'remark' => $item['remark'],
                'status' => $item['status'],
                'updatedAt' => $item['updated_at']
            ];
        }
        
        return Response::success([
            'list' => $result,
            'total' => $total
        ]);
    }
}
