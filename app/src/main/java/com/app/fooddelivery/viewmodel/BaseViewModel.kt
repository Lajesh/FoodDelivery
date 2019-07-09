package com.app.fooddelivery.viewmodel

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import com.app.fooddelivery.aac.SingleLiveEvent
import com.app.fooddelivery.data.remote.response.ApiResponse

/****
 * Base view model from which all other viewmodels are inherited
 * Author: Lajesh Dineshkumar
 * Created on: 2019-07-05
 * Modified on: 2019-07-05
 *****/
abstract class BaseViewModel<T> : ViewModel() {

    val response: SingleLiveEvent<ApiResponse<T>> = SingleLiveEvent()

    val loadingStatus: SingleLiveEvent<Boolean> = SingleLiveEvent()

    val logoutStatus: SingleLiveEvent<Boolean> = SingleLiveEvent()

    val backPressAction: SingleLiveEvent<Boolean> = SingleLiveEvent()

    val logoutAction: SingleLiveEvent<Boolean> = SingleLiveEvent()

    val headerStatus: MutableLiveData<Boolean> = MutableLiveData()

    val headerStepperStatus: MutableLiveData<Boolean> = MutableLiveData()

    val headerTitle: MutableLiveData<String> = MutableLiveData()

    //lateinit var sharedViewModel: SharedViewModel

    fun showStepper() {
        headerStepperStatus.value = true
    }

    fun showHeader(show: Boolean) {
        headerStatus.value = show
    }

    open fun onBackpress() {
        backPressAction.value = true
    }

    open fun onLogoutAction() {
        logoutAction.value = true
    }

    fun setTitle(title: String) {
        headerTitle.value = title
    }

}