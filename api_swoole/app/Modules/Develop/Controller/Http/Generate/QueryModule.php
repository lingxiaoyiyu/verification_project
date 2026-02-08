<?php
namespace App\Modules\Develop\Controller\Http\Generate;

use App\Base\BaseController;
use Library\Facades\Request;
use Library\Facades\Response;
use Library\Facades\Validate;

/**
 * 获取模块列表
 * POST /basic/develop/generate/queryModule
 */
class QueryModule extends BaseController
{
    protected function check(): void
    {
        $validator = Validate::make(Request::all(), [
            'basePath' => 'nullable|string|max:500',
        ]);

        if ($validator->fails()) {
            Response::fail($validator->errors()->first())->send();
        }
    }

    protected function service(): void
    {
        $basePath = Request::input('basePath', base_path('app/Modules'));

        $modules = [];
        
        if (is_dir($basePath)) {
            $dirs = scandir($basePath);
            foreach ($dirs as $dir) {
                if ($dir === '.' || $dir === '..') {
                    continue;
                }
                $fullPath = $basePath . '/' . $dir;
                if (is_dir($fullPath)) {
                    $modules[] = [
                        'name' => $dir,
                        'path' => $fullPath,
                    ];
                }
            }
        }

        Response::success($modules)->send();
    }
}
