package com.app.fooddelivery.view.fragment.delivery

import androidx.lifecycle.MutableLiveData
import com.app.fooddelivery.viewmodel.BaseViewModel
import javax.inject.Inject

/*******
 * Viewmodel of delivery details fragment
 * Author: Lajesh Dineshkumar
 * Created on: 2019-07-10
 * Modified on: 2019-07-10
 ********/
class DeliveryDetailsViewModel @Inject constructor() : BaseViewModel(){

    val imageUrl : MutableLiveData<String> = MutableLiveData()
    val desc: MutableLiveData<String> = MutableLiveData()
    val address: MutableLiveData<String> = MutableLiveData()
}