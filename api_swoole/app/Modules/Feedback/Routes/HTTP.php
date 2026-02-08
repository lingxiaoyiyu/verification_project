<?php
use \Library\Facades\Route;

Route::setRequestType('http');

/**
 * 问题反馈管理相关路由
 * 路由前缀: /basic/feedback
 */
Route::prefix('/basic/feedback')->group(function () {
    // 添加反馈
    Route::post('/add', \App\Modules\Feedback\Controller\Http\Add::class);
    // 删除反馈
    Route::post('/remove', \App\Modules\Feedback\Controller\Http\Remove::class);
    // 获取反馈详情
    Route::post('/get', \App\Modules\Feedback\Controller\Http\Get::class);
    // 反馈列表-分页
    Route::post('/page', \App\Modules\Feedback\Controller\Http\Page::class);
    
    // 更新操作
    Route::prefix('/update')->group(function () {
        // 更新状态
        Route::post('/status', \App\Modules\Feedback\Controller\Http\Update\Status::class);
        // 回复反馈
        Route::post('/reply', \App\Modules\Feedback\Controller\Http\Update\Reply::class);
    });
});
