<?php

use Library\Facades\Route;

Route::setRequestType('http');
Route::prefix('basic/common/verifyCode')->group(function(){
    Route::post('img', App\Modules\VerifyCode\Controller\Http\Img::class);
    Route::post('phone', App\Modules\VerifyCode\Controller\Http\Phone::class);
    Route::post('email', App\Modules\VerifyCode\Controller\Http\Email::class);
});
