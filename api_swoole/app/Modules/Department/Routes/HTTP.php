<?php

use Library\Facades\Route;

Route::setRequestType('http');

Route::prefix('basic/sys/department')->group(function(){
    Route::post('add', App\Modules\Department\Controller\Http\Add::class);
    Route::post('remove', App\Modules\Department\Controller\Http\Remove::class);
    Route::post('update', App\Modules\Department\Controller\Http\Update::class);
    Route::post('update/sort', App\Modules\Department\Controller\Http\Update\Sort::class);
    Route::post('query', App\Modules\Department\Controller\Http\Query::class);
    Route::post('get', App\Modules\Department\Controller\Http\Get::class);
});
