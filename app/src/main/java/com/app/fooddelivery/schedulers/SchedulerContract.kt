package com.app.fooddelivery.schedulers

import androidx.annotation.NonNull
import io.reactivex.Scheduler

/****
 * Scheduler contract interface
 * Author: Lajesh Dineshkumar
 * Created on: 2019-07-05
 * Modified on: 2019-07-05
 *****/
interface SchedulerContract {

    @NonNull
    fun io(): Scheduler

    @NonNull
    fun ui(): Scheduler
}