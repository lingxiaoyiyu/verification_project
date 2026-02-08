<?php
use \Library\Facades\Route;

Route::setRequestType('http');

/**
 * 仪表盘相关路由
 * 路由前缀: /basic/dashboard
 */
Route::prefix('/basic/dashboard')->group(function () {
    // 工作台
    Route::post('/workbench', \App\Modules\Dashboard\Controller\Http\Workbench::class);
    
    // 工作台子模块
    Route::prefix('/workbench')->group(function () {
        Route::post('/header', \App\Modules\Dashboard\Controller\Http\Workbench\Header::class);
        Route::post('/quickNav', \App\Modules\Dashboard\Controller\Http\Workbench\QuickNav::class);
        Route::post('/trend', \App\Modules\Dashboard\Controller\Http\Workbench\Trend::class);
        Route::post('/todo', \App\Modules\Dashboard\Controller\Http\Workbench\Todo::class);
    });
});
