package com.app.fooddelivery.view.activity

import android.os.Bundle
import com.app.fooddelivery.BR
import com.app.fooddelivery.R
import com.app.fooddelivery.databinding.ActivityMainBinding
import com.app.fooddelivery.utils.FragmentUtils
import com.app.fooddelivery.view.activity.base.BaseActivity
import com.app.fooddelivery.view.fragment.base.DeliveryListFragment

class MainActivity : BaseActivity<MainActivityViewModel, ActivityMainBinding>() {
    override val layoutRes: Int
        get() = R.layout.activity_main
    override val bindingVariable: Int
        get() = BR.viewModel

    override fun getViewModel(): Class<MainActivityViewModel> {
       return MainActivityViewModel::class.java
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        isUseCustomeViewModelFactory = false
        super.onCreate(savedInstanceState)

        FragmentUtils.replaceFragment(this, DeliveryListFragment(), R.id.fragmentContainer, false, FragmentUtils.FragmentAnimation.TRANSITION_NONE)
    }

}
