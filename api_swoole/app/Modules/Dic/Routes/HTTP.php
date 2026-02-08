<?php

use Library\Facades\Route;

Route::setRequestType('http');

Route::prefix('basic/sys/dic')->group(function(){
    // 字典组接口
    Route::post('add', App\Modules\Dic\Controller\Http\Add::class);
    Route::post('delete', App\Modules\Dic\Controller\Http\Delete::class);
    Route::post('remove', App\Modules\Dic\Controller\Http\Remove::class);
    Route::post('page', App\Modules\Dic\Controller\Http\Page::class);
    Route::post('query', App\Modules\Dic\Controller\Http\Query::class);
    Route::post('get', App\Modules\Dic\Controller\Http\Get::class);
    Route::post('update/info', App\Modules\Dic\Controller\Http\Update\Info::class);
    Route::post('update/status', App\Modules\Dic\Controller\Http\Update\Status::class);
    
    // 字典项接口
    Route::post('item/add', App\Modules\Dic\Controller\Http\Item\Add::class);
    Route::post('item/delete', App\Modules\Dic\Controller\Http\Item\Delete::class);
    Route::post('item/query', App\Modules\Dic\Controller\Http\Item\Query::class);
    Route::post('item/get', App\Modules\Dic\Controller\Http\Item\Get::class);
    Route::post('item/update/status', App\Modules\Dic\Controller\Http\Item\Update\Status::class);
    Route::post('item/update/info', App\Modules\Dic\Controller\Http\Item\Update\Info::class);
});
