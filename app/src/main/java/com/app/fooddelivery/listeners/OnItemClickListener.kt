package com.app.fooddelivery.listeners

import com.app.fooddelivery.model.DeliveryResponse

/*******
 * File Description
 * Author: Lajesh Dineshkumar
 * Created on: 2019-07-10
 * Modified on: 2019-07-10
 ********/
interface OnItemClickListener {
    fun onItemClick(item: DeliveryResponse)
}