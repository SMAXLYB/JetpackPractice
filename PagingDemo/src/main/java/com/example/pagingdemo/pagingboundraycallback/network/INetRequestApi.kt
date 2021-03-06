package com.example.pagingdemo.pagingboundraycallback.network

import com.example.pagingdemo.pagingboundraycallback.model.User
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface INetRequestApi {
    @GET("users")
    fun getUsers(
        @Query("since") since: Int,
        @Query("per_page") perPage: Int,
    ): Call<List<User>>
}