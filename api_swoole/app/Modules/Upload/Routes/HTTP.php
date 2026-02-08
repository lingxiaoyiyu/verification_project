<?php

use Library\Facades\Route;

Route::setRequestType('http');
Route::prefix('basic/common/upload')->group(function(){
    Route::post('img', App\Modules\Upload\Controller\Http\Img::class);
    Route::post('file', App\Modules\Upload\Controller\Http\File::class);
});
