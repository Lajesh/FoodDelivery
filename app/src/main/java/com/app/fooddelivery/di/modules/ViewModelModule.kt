package com.app.fooddelivery.di.modules

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import com.app.fooddelivery.aac.ViewModelFactory
import com.app.fooddelivery.di.key.ViewModelKey
import com.app.fooddelivery.view.fragment.login.LoginViewModel
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
    @ViewModelKey(LoginViewModel::class)
    fun bindLoginViewModel(loginViewModel: LoginViewModel): ViewModel

    @Binds
    fun bindViewModelFactory(viewModelFactory: ViewModelFactory): ViewModelProvider.Factory
}