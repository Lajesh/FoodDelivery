package com.app.fooddelivery.data.remote.response

import com.google.gson.annotations.SerializedName

/****
 * Data Model class which represents the error response
 * Author: Lajesh Dineshkumar
 * Created on: 2019-07-05
 * Modified on: 2019-07-05
 *****/
data class ErrorResponse (@SerializedName("errorCode") var errorCode:  String,
                          @SerializedName("errorDescription") var errorDescription: String){
    init {
        errorCode = ""
        errorDescription = ""
    }
}