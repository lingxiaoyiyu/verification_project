<?php
use \Library\Facades\Route;

Route::setRequestType('http');

/**
 * 开发工具相关路由
 * 路由前缀: /basic/develop
 */
Route::prefix('/basic/develop')->group(function () {
    // 代码生成器
    Route::prefix('/generate')->group(function () {
        Route::post('/apiCode', \App\Modules\Develop\Controller\Http\Generate\ApiCode::class);
        Route::post('/baseCode', \App\Modules\Develop\Controller\Http\Generate\BaseCode::class);
        Route::post('/queryModule', \App\Modules\Develop\Controller\Http\Generate\QueryModule::class);
        Route::post('/querySubModule', \App\Modules\Develop\Controller\Http\Generate\QuerySubModule::class);
    });
    
    // 数据库表管理
    Route::prefix('/table')->group(function () {
        Route::post('/page', \App\Modules\Develop\Controller\Http\Table\Page::class);
        Route::post('/select', \App\Modules\Develop\Controller\Http\Table\Select::class);
        Route::post('/detail', \App\Modules\Develop\Controller\Http\Table\Detail::class);
    });
});
