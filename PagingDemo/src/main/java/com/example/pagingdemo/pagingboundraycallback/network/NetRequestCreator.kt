package com.example.pagingdemo.pagingboundraycallback.network

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object NetRequestCreator {
    private const val BASE_URL = "https://api.github.com/"

    object OkHttpHolder {
        internal val OK_HTTP_CLIENT = OkHttpClient.Builder()
            .connectTimeout(6, TimeUnit.SECONDS)
            .build()
    }

    object RetrofitHolder {
        internal val RETROFIT_CLIENT = Retrofit.Builder()
            .client(OkHttpHolder.OK_HTTP_CLIENT)
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(BASE_URL)
            .build()
    }

    object RetrofitServiceHolder {
        internal val RETROFIT_SERVICE = RetrofitHolder.RETROFIT_CLIENT
            .create(INetRequestApi::class.java)
    }

    fun create(): INetRequestApi = RetrofitServiceHolder.RETROFIT_SERVICE
}