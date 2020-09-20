package com.example.pagingdemo.pagingboundraycallback.data

import android.app.Application
import android.os.AsyncTask
import androidx.paging.PagedList
import com.example.pagingdemo.pagingboundraycallback.db.UserDatabase
import com.example.pagingdemo.pagingboundraycallback.model.User
import com.example.pagingdemo.pagingboundraycallback.network.NetRequestCreator
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class UserBoundaryCallback(private val application: Application) :
    PagedList.BoundaryCallback<User>() {

    // 当数据库为空时,加载第一页数据
    override fun onZeroItemsLoaded() {
        super.onZeroItemsLoaded()
        getTopData()
    }

    // 当数据库的数据已经加载完毕时,请求下一页的数据
    override fun onItemAtEndLoaded(itemAtEnd: User) {
        super.onItemAtEndLoaded(itemAtEnd)
        getTopAfterData(itemAtEnd)
    }

    override fun onItemAtFrontLoaded(itemAtFront: User) {
        super.onItemAtFrontLoaded(itemAtFront)
    }

    // 加载第一页数据
    private fun getTopData() {
        val since = 0
        NetRequestCreator.create()
            .getUsers(since, UserViewModel.PER_PAGE)
            .enqueue(object : Callback<List<User>> {
                override fun onResponse(call: Call<List<User>>, response: Response<List<User>>) {
                    response.body()?.let {
                        insertUsers(it)
                    }
                }

                override fun onFailure(call: Call<List<User>>, t: Throwable) {
                }
            })
    }

    // 加载下一页数据
    private fun getTopAfterData(user: User) {
        NetRequestCreator.create()
            .getUsers(user.id, UserViewModel.PER_PAGE)
            .enqueue(object : Callback<List<User>> {
                override fun onResponse(call: Call<List<User>>, response: Response<List<User>>) {
                    response.body()?.let {
                        insertUsers(it)
                    }
                }

                override fun onFailure(call: Call<List<User>>, t: Throwable) {
                }
            })
    }

    // 插入数据库
    private fun insertUsers(users: List<User>) {
        AsyncTask.execute {
            UserDatabase.getDatabase(application)
                .userDao()
                .insertUser(users)
        }
    }
}