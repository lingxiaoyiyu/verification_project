<?php

use Library\Facades\Route;

Route::setRequestType('http');

Route::prefix('basic/sys/menu')->group(function(){
    Route::post('add', App\Modules\Menu\Controller\Http\Add::class);
    Route::post('remove', App\Modules\Menu\Controller\Http\Remove::class);
    Route::post('update', App\Modules\Menu\Controller\Http\Update\Info::class);
    Route::post('update/sort', App\Modules\Menu\Controller\Http\Update\Sort::class);
    Route::post('query', App\Modules\Menu\Controller\Http\Query::class);
    Route::post('query/selectTree', App\Modules\Menu\Controller\Http\Query\SelectTree::class);
    Route::post('query/children', App\Modules\Menu\Controller\Http\Query\Children::class);
    Route::post('get', App\Modules\Menu\Controller\Http\Get::class);
});
