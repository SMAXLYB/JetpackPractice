package com.example.pagingdemo.positionaldatasource.network

import okhttp3.HttpUrl
import okhttp3.OkHttpClient
import okhttp3.Request
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object NetRequestCreator {

    private const val BASE_URL = "http://t.yushu.im/v2/"
    private const val API_KEY = "0df993c66c0c636e29ecbb5344252a4a"

    private object OKHttpHolder {
        internal val OK_HTTP_CLIENT = OkHttpClient.Builder()
            .addInterceptor {
                // 获取原始请求
                val original: Request = it.request()
                // 获取原始请求BASE_URL
                val originalUrl: HttpUrl = original.url()
                // 拼接参数成新的URL
                val url: HttpUrl = originalUrl.newBuilder()
                    .addQueryParameter("apikey", API_KEY)
                    .build()
                // 构造新的请求
                val request = original.newBuilder()
                    .url(url)
                    .build()
                // 继续请求
                it.proceed(request)
            }
            .connectTimeout(60, TimeUnit.SECONDS)
            .build()
    }

    private object RetrofitHolder {
        internal val RETROFIT_CLIENT = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(OKHttpHolder.OK_HTTP_CLIENT)
            .build()
    }

    private object NetRequestServiceHolder {
        internal val NET_REQUEST_SERVICE = RetrofitHolder.RETROFIT_CLIENT
            .create(INetRequestApi::class.java)
    }

    fun create(): INetRequestApi = NetRequestServiceHolder.NET_REQUEST_SERVICE
}