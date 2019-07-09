package com.app.fooddelivery.repository

import com.app.fooddelivery.data.remote.Api
import com.app.fooddelivery.data.remote.response.ResponseListener
import com.app.fooddelivery.schedulers.SchedulerContract
import javax.inject.Inject

/*******
 * Food Repository which keeps all the service calls related to Food entity
 * Author: Lajesh Dineshkumar
 * Created on: 2019-07-07
 * Modified on: 2019-07-07
 ********/
class DeliveryOrderRepository@Inject constructor(private val api: Api, scheduler: SchedulerContract)
    : BaseRepository(scheduler){

    /**
     * The method for performing the user login
     * @param authorizationHeader : Access Token
     * @param bodyParams: Json Body
     * @param responseListener: Response Listener Callback
     */
    fun getDeliveries(offset: Int, limit: Int,  responseListener: ResponseListener<User>) {
        performRequest(api.getDeliveries(offset, limit), responseListener)
    }

}