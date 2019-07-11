package com.app.fooddelivery.di.modules

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkInfo
import com.app.fooddelivery.FoodDeliveryApp
import com.app.fooddelivery.common.Configuration
import com.app.fooddelivery.common.Constants
import com.app.fooddelivery.common.Constants.CACHE_SIZE
import com.app.fooddelivery.common.Constants.CONNECTION_TIMEOUT
import com.app.fooddelivery.common.Constants.READ_TIMEOUT
import com.app.fooddelivery.common.Constants.WRITE_TIMEOUT
import dagger.Module
import dagger.Provides
import okhttp3.Cache
import okhttp3.JavaNetCookieJar
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.net.CookieManager
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

/****
 * Network Module which provides the retrofit related instances
 * Author: Lajesh Dineshkumar
 * Created on: 2019-07-05
 * Modified on: 2019-07-05
 *****/

@Module
class NetworkModule {


    @Provides
    @Singleton
    fun provideOkHttpClient(logging: HttpLoggingInterceptor): OkHttpClient {
        val cookieHandler = CookieManager()
        val appCache = Cache(FoodDeliveryApp.applicationContext().cacheDir, CACHE_SIZE)
        return OkHttpClient.Builder()
            .cache(appCache)
            .addInterceptor(logging)
            .addInterceptor { chain ->
                var request = chain.request()

                // If the device is not connected to internet, the response will be taken from the cache
                request = if (hasNetwork(FoodDeliveryApp.applicationContext())!!)
                    request.newBuilder().header(Constants.HEADER_CACHE_CONTROL, "public, max-age=" + 5).build()
                else
                    request.newBuilder().header(
                        Constants.HEADER_CACHE_CONTROL,
                        "public, only-if-cached, max-stale=" + 60 * 60 * 24 * 7).build()
                chain.proceed(request)
            }
            .cookieJar(JavaNetCookieJar(cookieHandler))
            .connectTimeout(CONNECTION_TIMEOUT, TimeUnit.MILLISECONDS)
            .readTimeout(READ_TIMEOUT, TimeUnit.MILLISECONDS)
            .writeTimeout(WRITE_TIMEOUT, TimeUnit.MILLISECONDS)
            .build()
    }


    @Provides
    @Singleton
    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl(Configuration.baseURL)
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient)
            .build()
    }

    /**
     * Retrofit interceptor for logging network requests and response
     */
    @Provides
    @Singleton
    fun provideLogging(): HttpLoggingInterceptor {
        return HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
    }

    /**
     * This method check whether the device has internet connectivity or No
     */
    private fun hasNetwork(context: Context): Boolean? {
        var isConnected: Boolean? = false // Initial Value
        val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val activeNetwork: NetworkInfo? = connectivityManager.activeNetworkInfo
        if (activeNetwork != null && activeNetwork.isConnected)
            isConnected = true
        return isConnected
    }
}