<?php
use \Library\Facades\Route;

Route::setRequestType('http');

/**
 * 系统配置管理相关路由
 * 路由前缀: /basic/config
 */
Route::prefix('/basic/config')->group(function () {
    // 配置项管理
    Route::post('/add', \App\Modules\Config\Controller\Http\Add::class);
    Route::post('/delete', \App\Modules\Config\Controller\Http\Delete::class);
    Route::post('/query', \App\Modules\Config\Controller\Http\Query::class);
    Route::post('/get', \App\Modules\Config\Controller\Http\Get::class);
    Route::post('/page', \App\Modules\Config\Controller\Http\Page::class);
    
    // 配置项更新
    Route::post('/update', \App\Modules\Config\Controller\Http\Update\Info::class);
    Route::post('/update/sort', \App\Modules\Config\Controller\Http\Update\Sort::class);
    Route::post('/update/multiple', \App\Modules\Config\Controller\Http\Update\Multiple::class);
    Route::post('/update/status', \App\Modules\Config\Controller\Http\Update\Status::class);
    
    // 配置组管理
    Route::prefix('/group')->group(function () {
        Route::post('/add', \App\Modules\Config\Controller\Http\Group\Add::class);
        Route::post('/delete', \App\Modules\Config\Controller\Http\Group\Delete::class);
        Route::post('/update', \App\Modules\Config\Controller\Http\Group\Update::class);
        Route::post('/query', \App\Modules\Config\Controller\Http\Group\Query::class);
        Route::post('/get', \App\Modules\Config\Controller\Http\Group\Get::class);
    });
});
