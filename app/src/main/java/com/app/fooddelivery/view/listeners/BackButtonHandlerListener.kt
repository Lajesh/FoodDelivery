package com.app.fooddelivery.view.listeners

import com.app.fooddelivery.view.listeners.BackPressListener

/****
 * Back button handler interface. Add/remove listener functionality
 * Author: Lajesh Dineshkumar
 * Created on: 2019-07-05
 * Modified on: 2019-07-05
 *****/
interface BackButtonHandlerListener {
    fun addBackpressListener(listner: BackPressListener)
    fun removeBackpressListener(listner: BackPressListener)
}