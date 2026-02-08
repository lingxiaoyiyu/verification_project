<?php
namespace App\Modules\Develop\Controller\Http\Table;

use App\Base\BaseController;
use Library\Facades\DB;
use Library\Facades\Response;

/**
 * 数据库表选择列表
 * POST /basic/develop/table/select
 */
class Select extends BaseController
{
    protected function check(): void
    {
        // 无需参数校验
    }

    protected function service(): void
    {
        // 获取数据库名称
        $database = env('DB_DATABASE', 'test');

        // 查询所有表
        $sql = "SELECT 
                    TABLE_NAME as tableName,
                    TABLE_COMMENT as tableComment
                FROM information_schema.TABLES 
                WHERE TABLE_SCHEMA = ?
                ORDER BY TABLE_NAME ASC";

        $tables = DB::select($sql, [$database]);

        // 格式化返回数据
        $result = [];
        foreach ($tables as $table) {
            $result[] = [
                'value' => $table->tableName,
                'label' => $table->tableName . ($table->tableComment ? " ({$table->tableComment})" : ''),
                'tableName' => $table->tableName,
                'tableComment' => $table->tableComment,
            ];
        }

        Response::success($result)->send();
    }
}
