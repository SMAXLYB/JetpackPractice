package com.example.pagingdemo.positionaldatasource.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.example.pagingdemo.positionaldatasource.model.Movie

class MovieViewModel : ViewModel() {
    lateinit var moviePageList: LiveData<PagedList<Movie>>

    init{
        val config = PagedList.Config.Builder()
            .setEnablePlaceholders(false)
            .setPageSize(MovieDataSource.PER_PAGE)
            .setInitialLoadSizeHint(MovieDataSource.PER_PAGE)
            .setPrefetchDistance(2)
            .build()
        moviePageList = (LivePagedListBuilder(MovieDataSourceFactory(),config)).build()
    }
}