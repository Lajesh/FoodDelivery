package com.app.fooddelivery.di.modules

import com.app.fooddelivery.view.activity.MainActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

/****
 * The module which provides the android injection service to activities
 * Author: Lajesh Dineshkumar
 * Created on: 2019-07-05
 * Modified on: 2019-07-05
 *****/
@Suppress("unused")
@Module
abstract class ActivityBuilderModule {
    @ContributesAndroidInjector(modules = [FragmentBuilderModule::class])
    abstract fun contributeMainActivity(): MainActivity
}