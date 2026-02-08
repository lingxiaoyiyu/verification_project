<?php
namespace App\Modules\Develop\Controller\Http\Generate;

use App\Base\BaseController;
use Library\Facades\Request;
use Library\Facades\Response;
use Library\Facades\Validate;

/**
 * 基础代码生成器
 * POST /basic/develop/generate/baseCode
 */
class BaseCode extends BaseController
{
    protected function check(): void
    {
        $validator = Validate::make(Request::all(), [
            'tableName' => 'required|string|max:100',
            'moduleName' => 'required|string|max:100',
            'codeType' => 'required|string|in:entity,dto,service',
        ], [
            'tableName.required' => '表名不能为空',
            'moduleName.required' => '模块名不能为空',
            'codeType.required' => '代码类型不能为空',
            'codeType.in' => '代码类型无效',
        ]);

        if ($validator->fails()) {
            Response::fail($validator->errors()->first())->send();
        }
    }

    protected function service(): void
    {
        $tableName = Request::input('tableName');
        $moduleName = Request::input('moduleName');
        $codeType = Request::input('codeType');

        // 生成基础代码
        $code = '';
        switch ($codeType) {
            case 'entity':
                $code = $this->generateEntity($tableName, $moduleName);
                break;
            case 'dto':
                $code = $this->generateDto($tableName, $moduleName);
                break;
            case 'service':
                $code = $this->generateService($tableName, $moduleName);
                break;
        }

        Response::success([
            'tableName' => $tableName,
            'moduleName' => $moduleName,
            'codeType' => $codeType,
            'code' => $code,
        ])->send();
    }

    private function generateEntity(string $tableName, string $moduleName): string
    {
        $className = $this->tableNameToClassName($tableName);
        return "<?php\nnamespace App\\Modules\\{$moduleName}\\Entity;\n\n/**\n * {$className} Entity\n */\nclass {$className}\n{\n    // Entity properties\n}\n";
    }

    private function generateDto(string $tableName, string $moduleName): string
    {
        $className = $this->tableNameToClassName($tableName);
        return "<?php\nnamespace App\\Modules\\{$moduleName}\\Dto;\n\n/**\n * {$className} DTO\n */\nclass {$className}Dto\n{\n    // DTO properties\n}\n";
    }

    private function generateService(string $tableName, string $moduleName): string
    {
        $className = $this->tableNameToClassName($tableName);
        return "<?php\nnamespace App\\Modules\\{$moduleName}\\Service;\n\n/**\n * {$className} Service\n */\nclass {$className}Service\n{\n    // Service methods\n}\n";
    }

    private function tableNameToClassName(string $tableName): string
    {
        $tableName = preg_replace('/^sys_/', '', $tableName);
        return str_replace(' ', '', ucwords(str_replace('_', ' ', $tableName)));
    }
}
