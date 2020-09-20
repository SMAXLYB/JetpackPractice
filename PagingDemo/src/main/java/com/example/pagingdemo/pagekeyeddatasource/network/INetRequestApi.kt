package com.example.pagingdemo.pagekeyeddatasource.network

import com.example.pagingdemo.pagekeyeddatasource.model.UserResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface INetRequestApi {
    @GET("users")
    fun getUsers(
        @Query("page") page: Int,
        @Query("pagesize") pageSize: Int,
        @Query("site") site: String,
    ): Call<UserResponse>
}