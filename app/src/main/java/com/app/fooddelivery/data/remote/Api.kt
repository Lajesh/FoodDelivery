package com.app.fooddelivery.data.remote

import com.app.fooddelivery.model.DeliveryResponse
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query

/****
 * Keep all the REST Apis here
 * Author: Lajesh Dineshkumar
 * Company: Lavaira
 * Created on: 4/3/19
 * Modified on: 4/3/19
 *****/
interface Api {
    @GET("/deliveries")
    fun getDeliveries(@Query("offset") offset: Int, @Query("limit") limit: Int): Observable<List<DeliveryResponse>>

}