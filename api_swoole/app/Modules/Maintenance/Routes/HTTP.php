<?php
use \Library\Facades\Route;

Route::setRequestType('http');

/**
 * 运维管理相关路由
 */

// 日志管理 - 路由前缀: /basic/maintenance
Route::prefix('/basic/maintenance')->group(function () {
    // 运行时日志
    Route::prefix('/log/runtime')->group(function () {
        Route::post('/pageRequest', \App\Modules\Maintenance\Controller\Http\Log\Runtime\PageRequest::class);
        Route::post('/pageDetail', \App\Modules\Maintenance\Controller\Http\Log\Runtime\PageDetail::class);
    });
    
    // 操作日志
    Route::post('/log/operation/page', \App\Modules\Maintenance\Controller\Http\Log\Operation\Page::class);
});

// 统计管理 - 路由前缀: /maintenance
Route::prefix('/maintenance')->group(function () {
    // 用户注册统计
    Route::prefix('/statistics/user/regeister')->group(function () {
        Route::post('/day', \App\Modules\Maintenance\Controller\Http\Statistics\User\RegeisterDay::class);
        Route::post('/dayHour', \App\Modules\Maintenance\Controller\Http\Statistics\User\RegeisterDayHour::class);
    });
});
