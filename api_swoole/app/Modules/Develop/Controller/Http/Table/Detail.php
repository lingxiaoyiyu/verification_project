<?php
namespace App\Modules\Develop\Controller\Http\Table;

use App\Base\BaseController;
use Library\Facades\DB;
use Library\Facades\Request;
use Library\Facades\Response;
use Library\Facades\Validate;

/**
 * 数据库表结构详情
 * POST /basic/develop/table/detail
 */
class Detail extends BaseController
{
    protected function check(): void
    {
        $validator = Validate::make(Request::all(), [
            'tableName' => 'required|string|max:100',
        ], [
            'tableName.required' => '表名不能为空',
        ]);

        if ($validator->fails()) {
            Response::fail($validator->errors()->first())->send();
        }
    }

    protected function service(): void
    {
        $tableName = Request::input('tableName');
        $database = env('DB_DATABASE', 'test');

        // 获取表信息
        $tableInfoSql = "SELECT 
                            TABLE_NAME as tableName,
                            TABLE_COMMENT as tableComment,
                            ENGINE as engine,
                            TABLE_ROWS as tableRows,
                            DATA_LENGTH as dataLength,
                            CREATE_TIME as createTime,
                            UPDATE_TIME as updateTime
                        FROM information_schema.TABLES 
                        WHERE TABLE_SCHEMA = ? AND TABLE_NAME = ?";
        
        $tableInfo = DB::selectOne($tableInfoSql, [$database, $tableName]);

        if (!$tableInfo) {
            Response::fail('表不存在')->send();
        }

        // 获取列信息
        $columnsSql = "SELECT 
                            COLUMN_NAME as columnName,
                            COLUMN_TYPE as columnType,
                            DATA_TYPE as dataType,
                            CHARACTER_MAXIMUM_LENGTH as maxLength,
                            NUMERIC_PRECISION as numericPrecision,
                            NUMERIC_SCALE as numericScale,
                            IS_NULLABLE as isNullable,
                            COLUMN_DEFAULT as columnDefault,
                            COLUMN_KEY as columnKey,
                            EXTRA as extra,
                            COLUMN_COMMENT as columnComment
                        FROM information_schema.COLUMNS 
                        WHERE TABLE_SCHEMA = ? AND TABLE_NAME = ?
                        ORDER BY ORDINAL_POSITION";
        
        $columns = DB::select($columnsSql, [$database, $tableName]);

        // 获取索引信息
        $indexesSql = "SELECT 
                            INDEX_NAME as indexName,
                            COLUMN_NAME as columnName,
                            NON_UNIQUE as nonUnique,
                            SEQ_IN_INDEX as seqInIndex,
                            INDEX_TYPE as indexType
                        FROM information_schema.STATISTICS 
                        WHERE TABLE_SCHEMA = ? AND TABLE_NAME = ?
                        ORDER BY INDEX_NAME, SEQ_IN_INDEX";
        
        $indexes = DB::select($indexesSql, [$database, $tableName]);

        // 格式化列信息
        $columnList = [];
        foreach ($columns as $column) {
            $columnList[] = [
                'columnName' => $column->columnName,
                'columnType' => $column->columnType,
                'dataType' => $column->dataType,
                'maxLength' => $column->maxLength,
                'numericPrecision' => $column->numericPrecision,
                'numericScale' => $column->numericScale,
                'isNullable' => $column->isNullable === 'YES',
                'columnDefault' => $column->columnDefault,
                'isPrimaryKey' => $column->columnKey === 'PRI',
                'isUnique' => $column->columnKey === 'UNI',
                'isAutoIncrement' => strpos($column->extra, 'auto_increment') !== false,
                'columnComment' => $column->columnComment,
            ];
        }

        // 格式化索引信息
        $indexList = [];
        $currentIndex = null;
        foreach ($indexes as $index) {
            if ($currentIndex !== $index->indexName) {
                if ($currentIndex !== null) {
                    $indexList[] = [
                        'indexName' => $currentIndex,
                        'columns' => $currentColumns,
                        'isUnique' => $currentNonUnique === 0,
                        'indexType' => $currentIndexType,
                    ];
                }
                $currentIndex = $index->indexName;
                $currentColumns = [];
                $currentNonUnique = $index->nonUnique;
                $currentIndexType = $index->indexType;
            }
            $currentColumns[] = $index->columnName;
        }
        if ($currentIndex !== null) {
            $indexList[] = [
                'indexName' => $currentIndex,
                'columns' => $currentColumns,
                'isUnique' => $currentNonUnique === 0,
                'indexType' => $currentIndexType,
            ];
        }

        Response::success([
            'tableInfo' => [
                'tableName' => $tableInfo->tableName,
                'tableComment' => $tableInfo->tableComment,
                'engine' => $tableInfo->engine,
                'tableRows' => $tableInfo->tableRows,
                'dataLength' => $tableInfo->dataLength,
                'createTime' => $tableInfo->createTime,
                'updateTime' => $tableInfo->updateTime,
            ],
            'columns' => $columnList,
            'indexes' => $indexList,
        ])->send();
    }
}
