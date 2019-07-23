package com.app.fooddelivery.di.modules

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.app.fooddelivery.aac.ViewModelFactory
import com.app.fooddelivery.di.key.ViewModelKey
import com.app.fooddelivery.view.activity.MainActivityViewModel
import com.app.fooddelivery.view.fragment.delivery.DeliveryDetailsViewModel
import com.app.fooddelivery.view.fragment.delivery.DeliveryListViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

/****
 * View Model module which provides the viewmodelfactory and viewmodel instances
 * Author: Lajesh Dineshkumar
 * Created on: 2019-07-05
 * Modified on: 2019-07-05
 *****/
@Module
interface ViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(MainActivityViewModel::class)
    fun bindMainActivityViewModel(mainActivityViewModel: MainActivityViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(DeliveryListViewModel::class)
    fun bindDeliveryListViewModel(deliveryListViewModel: DeliveryListViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(DeliveryDetailsViewModel::class)
    fun bindDeliveryDetailsViewModel(deliveryDetailsViewModel: DeliveryDetailsViewModel): ViewModel

    @Binds
    fun bindViewModelFactory(viewModelFactory: ViewModelFactory): ViewModelProvider.Factory
}