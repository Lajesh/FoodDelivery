package com.app.fooddelivery.view.fragment.base

import android.os.Bundle
import android.widget.AbsListView
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.RecyclerView
import com.app.fooddelivery.BR
import com.app.fooddelivery.R
import com.app.fooddelivery.databinding.FragmentDeliveryListBinding
import com.app.fooddelivery.utils.FragmentUtils
import com.app.fooddelivery.view.fragment.delivery.DeliveryDetailsFragment
import com.app.fooddelivery.view.fragment.delivery.DeliveryListViewModel
import kotlinx.android.synthetic.main.fragment_delivery_list.*

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

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        rvOrderList.addOnScrollListener(object: RecyclerView.OnScrollListener(){
            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)
            }

        })

        viewModel.itemClickEvent.observe(this, Observer{
            activity?.supportFragmentManager?.let {
                FragmentUtils.replaceFragment(it, DeliveryDetailsFragment(), R.id.fragmentContainer,
                    true, FragmentUtils.FragmentAnimation.TRANSITION_SLIDE_LEFT_RIGHT)
            }

        })
    }

}