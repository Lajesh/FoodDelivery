package com.app.fooddelivery.view.fragment.delivery

import android.os.Bundle
import androidx.lifecycle.Observer
import com.app.fooddelivery.BR
import com.app.fooddelivery.FoodDeliveryApp
import com.app.fooddelivery.R
import com.app.fooddelivery.databinding.FragmentDeliveryListBinding
import com.app.fooddelivery.utils.FragmentUtils
import com.app.fooddelivery.view.fragment.base.BaseFragment

/****
 * Delivery list fragment
 * Author: Lajesh Dineshkumar
 * Created on: 2019-07-10
 * Modified on: 2019-07-10
 *****/
class DeliveryListFragment: BaseFragment<DeliveryListViewModel, FragmentDeliveryListBinding>(){
    override val layoutRes: Int
        get() = R.layout.fragment_delivery_list
    override val bindingVariable: Int
        get() = BR.viewModel

    override fun getViewModel(): Class<DeliveryListViewModel> {
        return DeliveryListViewModel::class.java
    }

    override fun getTitle(): String {
       return FoodDeliveryApp.applicationContext().getString(R.string.thingstodeliver)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel.itemClickEvent.observe(this, Observer{
            activity?.supportFragmentManager?.let {
                FragmentUtils.replaceFragment(it, DeliveryDetailsFragment(), R.id.fragmentContainer,
                    true, FragmentUtils.FragmentAnimation.TRANSITION_SLIDE_LEFT_RIGHT)
            }
        })

    }

}