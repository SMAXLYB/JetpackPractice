package com.example.pagingdemo.pagekeyeddatasource.network

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object NetRequestCreator {
    private const val BASE_URL = "https://api.stackexchange.com/2.2/"

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

    object NetRequestServiceHolder {
        internal val NET_REQUEST_SERVICE = RetrofitHolder.RETROFIT_CLIENT
            .create(INetRequestApi::class.java)
    }

    fun create(): INetRequestApi = NetRequestServiceHolder.NET_REQUEST_SERVICE
}