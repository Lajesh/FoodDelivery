package com.app.fooddelivery.data.remote.response

/****
 * ResponseListener callback which is responsible for giving the API response back to the presentation layer
 * Author: Lajesh Dineshkumar
 * Created on: 2019-07-05
 * Modified on: 2019-07-05
 *****/
interface ResponseListener<T> {
    fun onStart()
    fun onFinish()
    fun onResponse(result: ApiResponse<T>)
}