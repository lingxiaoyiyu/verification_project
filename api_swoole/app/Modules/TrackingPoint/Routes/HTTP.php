<?php
use \Library\Facades\Route;

Route::setRequestType('http');

/**
 * 埋点相关路由
 * 路由前缀: /basic/trackingPoint
 */
Route::prefix('/basic/trackingPoint')->group(function () {
    // 添加埋点记录
    Route::post('/add', \App\Modules\TrackingPoint\Controller\Http\Add::class);
});
