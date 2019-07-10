package com.app.fooddelivery.viewmodel

import androidx.databinding.Observable
import androidx.databinding.PropertyChangeRegistry
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.app.fooddelivery.aac.SingleLiveEvent

/****
 * Base view model from which all other viewmodels are inherited
 * Author: Lajesh Dineshkumar
 * Created on: 2019-07-05
 * Modified on: 2019-07-05
 *****/
abstract class BaseViewModel : ViewModel(), Observable {


    val loadingStatus: SingleLiveEvent<Boolean> = SingleLiveEvent()

    val logoutStatus: SingleLiveEvent<Boolean> = SingleLiveEvent()

    val backPressAction: SingleLiveEvent<Boolean> = SingleLiveEvent()

    val logoutAction: SingleLiveEvent<Boolean> = SingleLiveEvent()

    val headerStatus: MutableLiveData<Boolean> = MutableLiveData()

    val headerStepperStatus: MutableLiveData<Boolean> = MutableLiveData()

    val headerTitle: MutableLiveData<String> = MutableLiveData()

    private val callbacks = PropertyChangeRegistry()


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

    override fun addOnPropertyChangedCallback(
        callback: Observable.OnPropertyChangedCallback
    ) {
        callbacks.add(callback)
    }

    override fun removeOnPropertyChangedCallback(
        callback: Observable.OnPropertyChangedCallback
    ) {
        callbacks.remove(callback)
    }

    /**
     * Notifies observers that all properties of this instance have changed.
     */
    internal fun notifyChange() {
        callbacks.notifyCallbacks(this, 0, null)
    }

    /**
     * Notifies observers that a specific property has changed. The getter for the
     * property that changes should be marked with the @Bindable annotation to
     * generate a field in the BR class to be used as the fieldId parameter.
     *
     * @param fieldId The generated BR id for the Bindable field.
     */
    internal fun notifyPropertyChanged(fieldId: Int) {
        callbacks.notifyCallbacks(this, fieldId, null)
    }

}