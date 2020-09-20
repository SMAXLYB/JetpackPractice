package com.example.pagingdemo.pagingboundraycallback.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "user")
data class User(
    @PrimaryKey()
    @ColumnInfo(name = "id", typeAffinity = ColumnInfo.INTEGER)
    val id: Int,

    @ColumnInfo(name = "name", typeAffinity = ColumnInfo.TEXT)
    @SerializedName("login")
    val name: String,

    @ColumnInfo(name = "avatar", typeAffinity = ColumnInfo.TEXT)
    @SerializedName("avatar_url")
    val avatar: String
)