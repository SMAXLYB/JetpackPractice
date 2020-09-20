package com.example.pagingdemo.itemkeyeddatasource.model

import com.google.gson.annotations.SerializedName

data class User(
    val id: Int,
    @SerializedName("login") val name: String,
    @SerializedName("avatar_url") val avatar: String,
)