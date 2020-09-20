package com.example.pagingdemo.positionaldatasource.model

data class Movie(
    val id: String,
    val title: String,
    val year: String,
    val images:Images,
) {
    inner class Images(val large: String) {
        override fun toString(): String {
            return "Images(large='$large')"
        }
    }
}