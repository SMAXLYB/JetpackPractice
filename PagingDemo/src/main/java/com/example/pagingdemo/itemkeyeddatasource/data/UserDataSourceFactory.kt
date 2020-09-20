package com.example.pagingdemo.itemkeyeddatasource.data

import androidx.paging.DataSource
import com.example.pagingdemo.itemkeyeddatasource.model.User

class UserDataSourceFactory : DataSource.Factory<Int, User>() {
    override fun create(): DataSource<Int, User> = UserDataSource()
}