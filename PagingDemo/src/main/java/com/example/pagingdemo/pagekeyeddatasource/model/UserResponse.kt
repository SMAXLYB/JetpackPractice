package com.example.pagingdemo.pagekeyeddatasource.model

import com.google.gson.annotations.SerializedName

data class UserResponse(
    @SerializedName("items") val users: MutableList<User>,
    @SerializedName("has_more") val hasMore: Boolean
)