<?php
use \Library\Facades\Route;

Route::setRequestType('http');

/**
 * 入口相关路由
 */
// 默认入口
Route::any('/', \App\Modules\Index\Controller\Http\Index::class);

// 数据初始化
Route::any('/index/init', \App\Modules\Index\Controller\Http\Init::class);

// IP地址查询
Route::get('/basic/ip2region', \App\Modules\Index\Controller\Http\Ip2Region::class);
