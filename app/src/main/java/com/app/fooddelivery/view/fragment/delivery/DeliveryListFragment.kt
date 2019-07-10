package com.app.fooddelivery.view.fragment.base

import com.app.fooddelivery.BR
import com.app.fooddelivery.R
import com.app.fooddelivery.databinding.FragmentDeliveryListBinding
import com.app.fooddelivery.view.fragment.delivery.DeliveryListViewModel

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
       return ""
    }

}