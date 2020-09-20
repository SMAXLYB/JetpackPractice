package com.example.pagingdemo.pagekeyeddatasource.data

import androidx.paging.DataSource
import com.example.pagingdemo.pagekeyeddatasource.model.User

class UserDataSourceFactory : DataSource.Factory<Int, User>() {
    override fun create(): DataSource<Int, User> = UserDataSource()
}