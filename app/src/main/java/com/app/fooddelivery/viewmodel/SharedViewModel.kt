package com.app.fooddelivery.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.app.fooddelivery.model.DeliveryResponse

/*******
 * The viewmodel which is used to share data between activites and fragments
 * Author: Lajesh Dineshkumar
 * Created on: 2019-07-10
 * Modified on: 2019-07-10
 ********/
class SharedViewModel: ViewModel() {

    val deliveryDetails: MutableLiveData<DeliveryResponse> = MutableLiveData()
}