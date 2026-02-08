<?php

use Library\Facades\Route;

Route::setRequestType('http');

Route::prefix('basic/sys/user')->group(function(){
    // 基础操作
    Route::post('add', App\Modules\User\Controller\Http\Add::class);
    Route::post('page', App\Modules\User\Controller\Http\Page::class);
    Route::post('isExist', App\Modules\User\Controller\Http\IsExist::class);
    Route::post('friend', App\Modules\User\Controller\Http\Friend::class);
    
    // 获取用户
    Route::post('get/byId', App\Modules\User\Controller\Http\Get\ById::class);
    Route::post('get/current', App\Modules\User\Controller\Http\Get\Current::class);
    
    // 更新用户
    Route::post('update/byId', App\Modules\User\Controller\Http\Update\ById::class);
    Route::post('update/current', App\Modules\User\Controller\Http\Update\Current::class);
    Route::post('update/status', App\Modules\User\Controller\Http\Update\Status::class);
    Route::post('update/bindInviteCode', App\Modules\User\Controller\Http\Update\BindInviteCode::class);
    Route::post('update/password/byId', App\Modules\User\Controller\Http\Update\Password\ById::class);
    Route::post('update/password/current', App\Modules\User\Controller\Http\Update\Password\Current::class);
    
    // 在线用户管理
    Route::post('online/page', App\Modules\User\Controller\Http\Online\Page::class);
    Route::post('online/terminalList', App\Modules\User\Controller\Http\Online\TerminalList::class);
    Route::post('online/kickout', App\Modules\User\Controller\Http\Online\Kickout::class);
});
