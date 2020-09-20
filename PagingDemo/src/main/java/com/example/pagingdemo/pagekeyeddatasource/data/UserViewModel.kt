package com.example.pagingdemo.pagekeyeddatasource.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.example.pagingdemo.pagekeyeddatasource.model.User

class UserViewModel : ViewModel() {

    lateinit var userPagedList: LiveData<PagedList<User>>

    init {
        val config = PagedList.Config.Builder()
            .setEnablePlaceholders(false)
            .setPageSize(UserDataSource.PER_PAGE)
            .setPrefetchDistance(3)
            .setInitialLoadSizeHint(UserDataSource.PER_PAGE)
            .build()

        userPagedList = (LivePagedListBuilder(UserDataSourceFactory(), config)).build()
    }
}