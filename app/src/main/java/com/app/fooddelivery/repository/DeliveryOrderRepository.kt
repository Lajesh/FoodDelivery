package com.app.fooddelivery.repository

import com.app.fooddelivery.data.remote.Api
import com.app.fooddelivery.data.remote.response.ResponseListener
import com.app.fooddelivery.model.DeliveryResponse
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
     * The method for getting the list of deliveries
     * @param offset : Page Offset
     * @param limit: Page Limit
     * @param responseListener: Response Listener Callback
     */
    fun getDeliveries(offset: Int, limit: Int,  responseListener: ResponseListener<List<DeliveryResponse>>) {
        performRequest(api.getDeliveries(offset, limit), responseListener)
    }

}