<?php

use Library\Facades\Route;

Route::setRequestType('http');

Route::prefix('basic/sys/region')->group(function(){
    Route::post('add', App\Modules\Region\Controller\Http\Add::class);
    Route::post('remove', App\Modules\Region\Controller\Http\Remove::class);
    Route::post('update', App\Modules\Region\Controller\Http\Update::class);
    Route::post('update/sort', App\Modules\Region\Controller\Http\Update\Sort::class);
    Route::post('query', App\Modules\Region\Controller\Http\Query::class);
    Route::post('get', App\Modules\Region\Controller\Http\Get::class);
});
