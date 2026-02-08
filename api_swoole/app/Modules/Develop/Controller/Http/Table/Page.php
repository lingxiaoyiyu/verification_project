<?php
namespace App\Modules\Develop\Controller\Http\Table;

use App\Base\BaseController;
use Library\Facades\DB;
use Library\Facades\Request;
use Library\Facades\Response;
use Library\Facades\Validate;

/**
 * 数据库表列表-分页
 * POST /basic/develop/table/page
 */
class Page extends BaseController
{
    protected function check(): void
    {
        $validator = Validate::make(Request::all(), [
            'pageNo' => 'nullable|integer|min:1',
            'pageSize' => 'nullable|integer|min:1|max:100',
            'tableName' => 'nullable|string|max:100',
        ]);

        if ($validator->fails()) {
            Response::fail($validator->errors()->first())->send();
        }
    }

    protected function service(): void
    {
        $pageNo = Request::input('pageNo', 1);
        $pageSize = Request::input('pageSize', 20);
        $tableName = Request::input('tableName');

        // 获取数据库名称
        $database = env('DB_DATABASE', 'test');

        // 查询表信息
        $sql = "SELECT 
                    TABLE_NAME as tableName,
                    TABLE_COMMENT as tableComment,
                    ENGINE as engine,
                    TABLE_ROWS as tableRows,
                    DATA_LENGTH as dataLength,
                    CREATE_TIME as createTime,
                    UPDATE_TIME as updateTime
                FROM information_schema.TABLES 
                WHERE TABLE_SCHEMA = ?";
        
        $params = [$database];

        if ($tableName) {
            $sql .= " AND TABLE_NAME LIKE ?";
            $params[] = "%{$tableName}%";
        }

        $sql .= " ORDER BY CREATE_TIME DESC";

        // 获取总数
        $countSql = "SELECT COUNT(*) as total FROM information_schema.TABLES WHERE TABLE_SCHEMA = ?";
        $countParams = [$database];
        if ($tableName) {
            $countSql .= " AND TABLE_NAME LIKE ?";
            $countParams[] = "%{$tableName}%";
        }
        
        $countResult = DB::selectOne($countSql, $countParams);
        $total = $countResult ? $countResult->total : 0;

        // 分页查询
        $sql .= " LIMIT ? OFFSET ?";
        $params[] = $pageSize;
        $params[] = ($pageNo - 1) * $pageSize;

        $tables = DB::select($sql, $params);

        // 格式化返回数据
        $records = [];
        foreach ($tables as $table) {
            $records[] = [
                'tableName' => $table->tableName,
                'tableComment' => $table->tableComment,
                'engine' => $table->engine,
                'tableRows' => $table->tableRows,
                'dataLength' => $table->dataLength,
                'dataSizeFormatted' => $this->formatBytes($table->dataLength),
                'createTime' => $table->createTime,
                'updateTime' => $table->updateTime,
            ];
        }

        Response::success([
            'records' => $records,
            'total' => $total,
            'pageNo' => $pageNo,
            'pageSize' => $pageSize,
        ])->send();
    }

    private function formatBytes($bytes): string
    {
        if ($bytes >= 1073741824) {
            return number_format($bytes / 1073741824, 2) . ' GB';
        } elseif ($bytes >= 1048576) {
            return number_format($bytes / 1048576, 2) . ' MB';
        } elseif ($bytes >= 1024) {
            return number_format($bytes / 1024, 2) . ' KB';
        } else {
            return $bytes . ' B';
        }
    }
}
