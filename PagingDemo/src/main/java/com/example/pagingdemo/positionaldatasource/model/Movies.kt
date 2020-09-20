package com.example.pagingdemo.positionaldatasource.model

import com.google.gson.annotations.SerializedName

data class Movies(
    val count: Int,
    val start: Int,
    val total: Int,
    @SerializedName("subjects") val movieList: List<Movie>,
) {
}