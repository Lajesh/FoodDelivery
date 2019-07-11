package com.app.fooddelivery.repository

import com.app.fooddelivery.data.remote.response.ApiResponse
import com.app.fooddelivery.data.remote.response.ResponseListener
import com.app.fooddelivery.data.remote.response.ResponseStatus
import com.app.fooddelivery.schedulers.SchedulerContract
import io.reactivex.Observable
import io.reactivex.disposables.Disposable

/****
 * Base repository which is responsible for executing all the REST service calls
 * Author: Lajesh Dineshkumar
 * Company: Lavaira
 * Created on: 4/4/19
 * Modified on: 4/4/19
 *****/
open class BaseRepository (val scheduler: SchedulerContract){


    /**
     * This method perfroms the asynchronous network request using the scheduler
     * @param observable : Observable network request
     * @param responseListener: ResponseListener Callback
     */
     fun <T : Any> performRequest(observable: Observable<T>, responseListener: ResponseListener<T>) : Disposable {
        return observable.subscribeOn(scheduler.io())
            .observeOn(scheduler.ui())
            .doOnSubscribe { responseListener.onStart() }
            .doAfterTerminate { responseListener.onFinish() }
            .subscribe({ result: T -> responseListener.onResponse(ApiResponse(ResponseStatus.SUCCESS, result, null)) },
                { error: Throwable? -> responseListener.onResponse(ApiResponse(ResponseStatus.FAILURE, null, error)) })

    }

}