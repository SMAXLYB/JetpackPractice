package com.example.pagingdemo.pagekeyeddatasource.model

import com.google.gson.annotations.SerializedName

data class User(
    @SerializedName("account_id") val id: Int,
    @SerializedName("display_name") val name: String,
    @SerializedName("profile_image") val avatar: String,
)
