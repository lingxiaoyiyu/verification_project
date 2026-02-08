<?php
use \Library\Facades\Route;

Route::setRequestType('http');

/**
 * S3存储管理相关路由
 * 路由前缀: /basic/s3
 */
Route::prefix('/basic/s3')->group(function () {
    // 文件管理
    Route::prefix('/file')->group(function () {
        // 上传文件
        Route::post('/add', \App\Modules\S3\Controller\Http\File\Add::class);
        // 删除文件
        Route::post('/delete', \App\Modules\S3\Controller\Http\File\Delete::class);
        // 批量删除文件
        Route::post('/batchDelete', \App\Modules\S3\Controller\Http\File\BatchDelete::class);
        // 获取文件信息
        Route::post('/get', \App\Modules\S3\Controller\Http\File\Get::class);
        // 文件列表-分页
        Route::post('/page', \App\Modules\S3\Controller\Http\File\Page::class);
        
        // 文件夹管理
        Route::prefix('/folder')->group(function () {
            // 创建文件夹
            Route::post('/add', \App\Modules\S3\Controller\Http\Folder\Add::class);
            // 删除文件夹
            Route::post('/delete', \App\Modules\S3\Controller\Http\Folder\Delete::class);
        });
    });
});
