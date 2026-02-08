<?php
namespace App\Modules\Develop\Controller\Http\Generate;

use App\Base\BaseController;
use Library\Facades\Request;
use Library\Facades\Response;
use Library\Facades\Validate;

/**
 * API接口代码生成器
 * POST /basic/develop/generate/apiCode
 */
class ApiCode extends BaseController
{
    protected function check(): void
    {
        $validator = Validate::make(Request::all(), [
            'tableName' => 'required|string|max:100',
            'moduleName' => 'required|string|max:100',
            'subModuleName' => 'nullable|string|max:100',
            'description' => 'nullable|string|max:200',
        ], [
            'tableName.required' => '表名不能为空',
            'moduleName.required' => '模块名不能为空',
        ]);

        if ($validator->fails()) {
            Response::fail($validator->errors()->first())->send();
        }
    }

    protected function service(): void
    {
        $tableName = Request::input('tableName');
        $moduleName = Request::input('moduleName');
        $subModuleName = Request::input('subModuleName', '');
        $description = Request::input('description', '');

        // 生成代码逻辑
        // 这里简化实现，实际应根据表结构生成CRUD代码
        
        $code = $this->generateApiCode($tableName, $moduleName, $subModuleName, $description);

        Response::success([
            'tableName' => $tableName,
            'moduleName' => $moduleName,
            'subModuleName' => $subModuleName,
            'code' => $code,
        ])->send();
    }

    /**
     * 生成API代码
     */
    private function generateApiCode(string $tableName, string $moduleName, string $subModuleName, string $description): array
    {
        $className = $this->tableNameToClassName($tableName);
        $namespace = "App\\Modules\\{$moduleName}\\Controller\\Http";
        if ($subModuleName) {
            $namespace .= "\\{$subModuleName}";
        }

        return [
            'controller' => [
                'add' => $this->generateAddController($namespace, $className, $description),
                'delete' => $this->generateDeleteController($namespace, $className),
                'update' => $this->generateUpdateController($namespace, $className),
                'get' => $this->generateGetController($namespace, $className),
                'page' => $this->generatePageController($namespace, $className),
            ],
            'route' => $this->generateRoute($moduleName, $subModuleName, $className),
        ];
    }

    private function tableNameToClassName(string $tableName): string
    {
        // 移除表前缀并转换为驼峰命名
        $tableName = preg_replace('/^sys_/', '', $tableName);
        return str_replace(' ', '', ucwords(str_replace('_', ' ', $tableName)));
    }

    private function generateAddController(string $namespace, string $className, string $description): string
    {
        return "<?php\nnamespace {$namespace};\n\nuse App\\Modules\\Common\\Controller\\BaseController;\n// Add controller for {$className}\n// {$description}\n";
    }

    private function generateDeleteController(string $namespace, string $className): string
    {
        return "<?php\nnamespace {$namespace};\n\nuse App\\Modules\\Common\\Controller\\BaseController;\n// Delete controller for {$className}\n";
    }

    private function generateUpdateController(string $namespace, string $className): string
    {
        return "<?php\nnamespace {$namespace};\n\nuse App\\Modules\\Common\\Controller\\BaseController;\n// Update controller for {$className}\n";
    }

    private function generateGetController(string $namespace, string $className): string
    {
        return "<?php\nnamespace {$namespace};\n\nuse App\\Modules\\Common\\Controller\\BaseController;\n// Get controller for {$className}\n";
    }

    private function generatePageController(string $namespace, string $className): string
    {
        return "<?php\nnamespace {$namespace};\n\nuse App\\Modules\\Common\\Controller\\BaseController;\n// Page controller for {$className}\n";
    }

    private function generateRoute(string $moduleName, string $subModuleName, string $className): string
    {
        $prefix = strtolower($moduleName);
        if ($subModuleName) {
            $prefix .= '/' . strtolower($subModuleName);
        }
        return "Route::prefix('/{$prefix}')->group(function () {\n    // Routes for {$className}\n});";
    }
}
