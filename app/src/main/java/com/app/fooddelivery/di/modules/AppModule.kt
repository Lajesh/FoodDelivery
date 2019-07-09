package com.app.fooddelivery.di.modules

import com.app.fooddelivery.data.remote.Api
import com.app.fooddelivery.repository.DeliveryOrderRepository
import com.app.fooddelivery.schedulers.SchedulerContract
import com.app.fooddeliveryapp.schedulers.SchedulerProvider
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import javax.inject.Singleton

/****
 * Application Module which is responsible for providing the app wide instances.
 * Author: Lajesh Dineshkumar
 * Created on: 2019-07-05
 * Modified on: 2019-07-05
 *****/
@Module(includes = [(ViewModelModule::class)])
class AppModule {
    @Provides
    @Singleton
    fun provideApi(retrofit: Retrofit) : Api {
        return retrofit.create(Api::class.java)
    }


    @Provides
    @Singleton
    fun provideDeliveryOrderRepository(api: Api, scheduler: SchedulerContract): DeliveryOrderRepository {
        return DeliveryOrderRepository(api, scheduler)
    }

    @Provides
    @Singleton
    fun provideScheduler(): SchedulerContract {
        return SchedulerProvider()
    }
}