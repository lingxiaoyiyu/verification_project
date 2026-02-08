<?php

use \Library\Facades\Route;

Route::setRequestType('http');

// 登录路由（无需认证）
Route::prefix('basic/auth/login')->group(function(){
    Route::post('username', App\Modules\Auth\Controller\Http\Login\Username::class);
    Route::post('email', App\Modules\Auth\Controller\Http\Login\Email::class);
    Route::post('usernameVerCode', App\Modules\Auth\Controller\Http\Login\UsernameVerCode::class);
    Route::post('phone', App\Modules\Auth\Controller\Http\Login\Phone::class);
    Route::post('wujieUnionId', App\Modules\Auth\Controller\Http\Login\WujieUnionId::class);
    Route::get('wechatoffice', App\Modules\Auth\Controller\Http\Login\WechatOffice::class);
    Route::get('wechatmp', App\Modules\Auth\Controller\Http\Login\WechatMp::class);
});

// 注册路由（无需认证）
Route::prefix('basic/auth/reg')->group(function(){
    Route::post('username', App\Modules\Auth\Controller\Http\Reg\Username::class);
    Route::post('email', App\Modules\Auth\Controller\Http\Reg\Email::class);
});

// 密码重置路由（无需认证）
Route::prefix('basic/auth/resetPwd')->group(function(){
    Route::post('email', App\Modules\Auth\Controller\Http\ResetPwd\Email::class);
});

// 其他认证路由
Route::prefix('basic/auth')->group(function(){
    Route::post('logout', App\Modules\Auth\Controller\Http\Logout::class);
    Route::post('refresh', App\Modules\Auth\Controller\Http\Refresh::class);
    Route::post('menus', App\Modules\Auth\Controller\Http\Menus::class);
    Route::post('codes', App\Modules\Auth\Controller\Http\Codes::class);
});
