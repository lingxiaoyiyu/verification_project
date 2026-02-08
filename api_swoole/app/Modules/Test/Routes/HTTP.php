<?php

use Library\Facades\Route;

Route::setRequestType('http');
Route::prefix('test')->group(function(){
    Route::match(['get', 'post'], 'test', App\Modules\Test\Controller\Http\Test::class);
});