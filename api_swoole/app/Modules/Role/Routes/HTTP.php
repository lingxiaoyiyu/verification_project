<?php

use Library\Facades\Route;

Route::setRequestType('http');

Route::prefix('basic/sys/role')->group(function(){
    Route::post('add', App\Modules\Role\Controller\Http\Add::class);
    Route::post('remove', App\Modules\Role\Controller\Http\Remove::class);
    Route::post('update', App\Modules\Role\Controller\Http\Update::class);
    Route::post('update/status', App\Modules\Role\Controller\Http\Update\Status::class);
    Route::post('page', App\Modules\Role\Controller\Http\Page::class);
    Route::post('query', App\Modules\Role\Controller\Http\Query::class);
    Route::post('get', App\Modules\Role\Controller\Http\Get::class);
});
