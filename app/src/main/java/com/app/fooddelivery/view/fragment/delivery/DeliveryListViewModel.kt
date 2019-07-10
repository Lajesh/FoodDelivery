package com.app.fooddelivery.view.fragment.delivery

import com.app.fooddelivery.data.remote.response.ApiResponse
import com.app.fooddelivery.data.remote.response.ResponseListener
import com.app.fooddelivery.model.DeliveryResponse
import com.app.fooddelivery.repository.DeliveryOrderRepository
import com.app.fooddelivery.viewmodel.BaseViewModel
import javax.inject.Inject

/****
 * Viewmodel of delivery list fragment
 * Author: Lajesh Dineshkumar
 * Created on: 2019-07-10
 * Modified on: 2019-07-10
 *****/
class DeliveryListViewModel @Inject constructor(private val deliveryOrderRepository: DeliveryOrderRepository) : BaseViewModel() {

    init {
        getDeliveryList()
    }


    /**
     * Service call which returns the list of deliveries
     */
     fun getDeliveryList() {
        deliveryOrderRepository.getDeliveries(0, 10, object : ResponseListener<List<DeliveryResponse>>{
            override fun onStart() {
                loadingStatus.value = true
            }

            override fun onFinish() {
                loadingStatus.value = false
            }

            override fun onResponse(result: ApiResponse<List<DeliveryResponse>>) {
                result.let {
                    val response = result.data
                }
            }

        })
    }
}