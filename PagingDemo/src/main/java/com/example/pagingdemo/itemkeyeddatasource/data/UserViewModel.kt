package com.example.pagingdemo.itemkeyeddatasource.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.example.pagingdemo.itemkeyeddatasource.model.User

class UserViewModel : ViewModel() {
    lateinit var userPagedList: LiveData<PagedList<User>>

    init {
        val config = PagedList.Config
            .Builder()
            .setPageSize(UserDataSource.PER_PAGE)
            .setPrefetchDistance(2)
            .setEnablePlaceholders(true)
            .setInitialLoadSizeHint(UserDataSource.PER_PAGE * 3)
            .build()

        userPagedList = (LivePagedListBuilder(UserDataSourceFactory(), config)).build()
    }
}