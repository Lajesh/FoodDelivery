package com.app.fooddelivery.view.fragment.delivery

import androidx.databinding.ObservableArrayList
import androidx.databinding.ObservableList
import com.app.fooddelivery.BR
import com.app.fooddelivery.R
import com.app.fooddelivery.aac.SingleLiveEvent
import com.app.fooddelivery.data.remote.response.ApiResponse
import com.app.fooddelivery.data.remote.response.ResponseListener
import com.app.fooddelivery.data.remote.response.ResponseStatus
import com.app.fooddelivery.listeners.OnItemClickListener
import com.app.fooddelivery.model.DeliveryResponse
import com.app.fooddelivery.repository.DeliveryOrderRepository
import com.app.fooddelivery.viewmodel.BaseViewModel
import me.tatarka.bindingcollectionadapter2.*
import javax.inject.Inject

/****
 * Viewmodel of delivery list fragment
 * Author: Lajesh Dineshkumar
 * Created on: 2019-07-10
 * Modified on: 2019-07-10
 *****/
class DeliveryListViewModel @Inject constructor(private val deliveryOrderRepository: DeliveryOrderRepository) : BaseViewModel() {

    val deliveryItems: ObservableList<DeliveryResponse> = ObservableArrayList()
    val itemClickEvent: SingleLiveEvent<Void> = SingleLiveEvent()

    val itemBinding: ItemBinding<DeliveryResponse> = ItemBinding.of<DeliveryResponse>(com.app.fooddelivery.BR.data, R.layout.item_delivery)
        .bindExtra(BR.listener, object : OnItemClickListener{
            override fun onItemClick(item: DeliveryResponse) {
                sharedViewModel.deliveryDetails.value = item
                itemClickEvent.call()
            }
        })

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
                if(result.status == ResponseStatus.SUCCESS && null != result.data){
                    deliveryItems.addAll(result.data)
                }else{

                }
            }

        })
    }
}