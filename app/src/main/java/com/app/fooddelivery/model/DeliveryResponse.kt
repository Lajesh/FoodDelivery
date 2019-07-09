package com.app.fooddelivery.model

/*******
 * Data model representing delivery response
 * Author: Lajesh Dineshkumar
 * Created on: 2019-07-07
 * Modified on: 2019-07-07
 ********/
data class DeliveryResponse(
    val description: String? = "",
    val id: Int = 0,
    val imageUrl: String? = "",
    val location: Location
)