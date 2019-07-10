package com.app.fooddelivery.common

import com.app.fooddelivery.BuildConfig

/****
 *  Keep all the Build/ deployment configurations here
 * Author: Lajesh Dineshkumar
 * Created on: 2019-07-10
 * Modified on: 2019-07-10
 *****/
object Configuration {
    private const val DEV = "development"

    private const val PROD = "production"


    // HOST Urls

    private const val URL_DEV = "https://mock-api-mobile.dev.lalamove.com/" // Put the development url here

    private const val URL_PROD = "https://mock-api-mobile.dev.lalamove.com/" // Put the production url here



    val baseURL: String
        get() {

            return when (BuildConfig.FLAVOR) {

                DEV -> URL_DEV

                PROD -> URL_PROD

                else -> URL_DEV
            }
        }

}