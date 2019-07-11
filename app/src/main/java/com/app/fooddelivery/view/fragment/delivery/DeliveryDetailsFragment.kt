package com.app.fooddelivery.view.fragment.delivery

import android.os.Bundle
import com.app.fooddelivery.BR
import com.app.fooddelivery.FoodDeliveryApp
import com.app.fooddelivery.R
import com.app.fooddelivery.view.fragment.base.BaseFragment
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions


/*******
 * Delivery details fragment
 * Author: Lajesh Dineshkumar
 * Created on: 2019-07-10
 * Modified on: 2019-07-10
 ********/
class DeliveryDetailsFragment : BaseFragment<DeliveryDetailsViewModel, com.app.fooddelivery.databinding.FragmentDeliveryDetailsBinding>(),
OnMapReadyCallback{

    var map:GoogleMap? = null

    override fun onMapReady(p0: GoogleMap?) {
        map = p0

        sharedViewModel.deliveryDetails.value?.let {
            map?.addMarker(MarkerOptions().position(LatLng(it.location.lat, it.location.lng)))
            viewModel.desc.value = it.description
            viewModel.address.value = it.location.address
            viewModel.imageUrl.value = it.imageUrl
        }


    }

    override val layoutRes: Int
        get() = R.layout.fragment_delivery_details

    override val bindingVariable: Int
        get() = BR.viewModel

    override fun getViewModel(): Class<DeliveryDetailsViewModel> {
        return DeliveryDetailsViewModel::class.java
    }

    override fun getTitle(): String {
        return FoodDeliveryApp.applicationContext().getString(R.string.delivery_details)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        isUseCustomeViewModelFactory = false
        super.onCreate(savedInstanceState)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        dataBinding.mapview.onCreate(savedInstanceState)
        dataBinding.mapview.getMapAsync(this)
    }

}
