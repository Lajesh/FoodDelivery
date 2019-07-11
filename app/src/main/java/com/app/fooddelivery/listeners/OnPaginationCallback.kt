package com.app.fooddelivery.listeners

/****
 * Interface represents the pagination callbacks
 * Author: Lajesh Dineshkumar
 * Created on: 2019-07-11
 * Modified on: 2019-07-11
 *****/
interface OnPaginationCallback {
    fun onNextPage(itemCount: Int, visibleItem: Int)
}