package com.example.pagingdemo.positionaldatasource.network

import com.example.pagingdemo.positionaldatasource.model.Movies
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface INetRequestApi {

    @GET("movie/top250")
    fun getMovies(
        @Query("start") since: Int,
        @Query("count") perPage: Int)
            : Call<Movies>
}