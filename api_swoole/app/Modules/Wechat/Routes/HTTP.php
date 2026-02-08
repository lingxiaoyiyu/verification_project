<?php

use Library\Facades\Route;

/*
|--------------------------------------------------------------------------
| API Routes
|--------------------------------------------------------------------------
|
| Here is where you can register API routes for your application. These
| routes are loaded by the RouteServiceProvider within a group which
| is assigned the "api" middleware group. Enjoy building your API!
|
*/

Route::setRequestType('http');
Route::prefix('wechat')->group(function(){
    Route::match(['get', 'post'], 'accessToken', App\Modules\Wechat\Controller\Http\AccessToken::class); // 获取微信accessToken
    Route::prefix('officialAccount')->group(function(){
        Route::match(['get', 'post'], 'ticket', App\Modules\Wechat\Controller\Http\OfficialAccount\Ticket::class); // 获取微信公众号Ticket
    });
});