package com.app.fooddelivery.di.components

import android.app.Application
import com.app.fooddelivery.FoodDeliveryApp
import com.app.fooddelivery.di.modules.ActivityBuilderModule
import com.app.fooddelivery.di.modules.AppModule
import com.app.fooddelivery.di.modules.FragmentBuilderModule
import com.app.fooddelivery.di.modules.NetworkModule
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import javax.inject.Singleton

/****
 * Application Component
 * Author: Lajesh Dineshkumar
 * Created on: 2019-07-05
 * Modified on: 2019-07-05
 *****/
@Singleton
@Component(
    modules = [
        AndroidInjectionModule::class,
        AppModule::class,
        NetworkModule::class,
        ActivityBuilderModule::class,
        FragmentBuilderModule::class]
)
interface AppComponent {
    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: Application): Builder

        fun build(): AppComponent
    }

    fun inject(foodDeliveryApp: FoodDeliveryApp)
}