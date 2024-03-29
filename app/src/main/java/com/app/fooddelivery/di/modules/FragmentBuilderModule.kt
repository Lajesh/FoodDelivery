package com.app.fooddelivery.di.modules

import com.app.fooddelivery.view.fragment.delivery.DeliveryDetailsFragment
import com.app.fooddelivery.view.fragment.delivery.DeliveryListFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

/****
 * The module which provides the android injection service to fragments.
 * Author: Lajesh Dineshkumar
 * Created on: 2019-07-05
 * Modified on: 2019-07-05
 *****/
@Suppress("unused")
@Module
abstract class FragmentBuilderModule {

    @ContributesAndroidInjector
    abstract fun contributeDeliveryListFragment(): DeliveryListFragment

    @ContributesAndroidInjector
    abstract fun contributeDeliveryDetailsFragment(): DeliveryDetailsFragment
}