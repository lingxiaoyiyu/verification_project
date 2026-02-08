<?php
namespace App\Modules\Develop\Controller\Http\Generate;

use App\Base\BaseController;
use Library\Facades\Request;
use Library\Facades\Response;
use Library\Facades\Validate;

/**
 * 获取子模块列表
 * POST /basic/develop/generate/querySubModule
 */
class QuerySubModule extends BaseController
{
    protected function check(): void
    {
        $validator = Validate::make(Request::all(), [
            'moduleName' => 'required|string|max:100',
        ], [
            'moduleName.required' => '模块名不能为空',
        ]);

        if ($validator->fails()) {
            Response::fail($validator->errors()->first())->send();
        }
    }

    protected function service(): void
    {
        $moduleName = Request::input('moduleName');
        $basePath = base_path("app/Modules/{$moduleName}/Controller/Http");

        $subModules = [];
        
        if (is_dir($basePath)) {
            $dirs = scandir($basePath);
            foreach ($dirs as $dir) {
                if ($dir === '.' || $dir === '..') {
                    continue;
                }
                $fullPath = $basePath . '/' . $dir;
                if (is_dir($fullPath)) {
                    $subModules[] = [
                        'name' => $dir,
                        'path' => $fullPath,
                    ];
                }
            }
        }

        Response::success($subModules)->send();
    }
}
