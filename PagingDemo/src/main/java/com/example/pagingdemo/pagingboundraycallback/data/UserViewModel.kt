package com.example.pagingdemo.pagingboundraycallback.data

import android.app.Application
import android.os.AsyncTask
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.example.pagingdemo.pagingboundraycallback.db.UserDatabase
import com.example.pagingdemo.pagingboundraycallback.model.User

class UserViewModel(application: Application) : AndroidViewModel(application) {
    lateinit var userPagedList: LiveData<PagedList<User>>

    companion object {
        const val PER_PAGE = 8
    }

    init {
        val database = UserDatabase.getDatabase(application)

        // 直接使用room数据库返回的数据工厂
        userPagedList = (LivePagedListBuilder(database.userDao().getUserList(), PER_PAGE))
            .setBoundaryCallback(UserBoundaryCallback(application))
            .build()
    }


    // 刷新数据
    fun refresh() {
        AsyncTask.execute {
            UserDatabase.getDatabase(getApplication())
                .userDao()
                .clear()
        }
    }
}