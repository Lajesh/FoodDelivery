package com.app.fooddelivery.model

/*******
 * Data model representing user location
 * Author: Lajesh Dineshkumar
 * Created on: 2019-07-07
 * Modified on: 2019-07-07
 ********/
data class Location(
    val address: String? = "",
    val lat: Double,
    val lng: Double
)