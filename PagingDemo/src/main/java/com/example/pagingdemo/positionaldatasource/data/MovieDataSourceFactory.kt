package com.example.pagingdemo.positionaldatasource.data

import androidx.paging.DataSource
import com.example.pagingdemo.positionaldatasource.model.Movie

class MovieDataSourceFactory : DataSource.Factory<Int, Movie>() {

    override fun create(): DataSource<Int, Movie> {
        return MovieDataSource()
    }
}