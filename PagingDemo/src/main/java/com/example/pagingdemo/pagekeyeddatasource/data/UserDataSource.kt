package com.example.pagingdemo.pagekeyeddatasource.data

import androidx.paging.PageKeyedDataSource
import com.example.pagingdemo.pagekeyeddatasource.model.User
import com.example.pagingdemo.pagekeyeddatasource.model.UserResponse
import com.example.pagingdemo.pagekeyeddatasource.network.NetRequestCreator
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class UserDataSource : PageKeyedDataSource<Int, User>() {

    companion object {
        const val FIRST_PAGE = 1;
        const val PER_PAGE = 8
        const val SITE = "stackoverflow"
    }

    // 首次加载数据时调用
    override fun loadInitial(
        params: LoadInitialParams<Int>,
        callback: LoadInitialCallback<Int, User>,
    ) {
        NetRequestCreator.create()
            .getUsers(FIRST_PAGE, PER_PAGE, SITE)
            .enqueue(object : Callback<UserResponse> {
                override fun onResponse(
                    call: Call<UserResponse>,
                    response: Response<UserResponse>,
                ) {
                    response.body()?.let {
                        callback.onResult(it.users, null, FIRST_PAGE + 1)
                    }
                }

                override fun onFailure(call: Call<UserResponse>, t: Throwable) {
                    TODO("Not yet implemented")
                }
            })
    }

    override fun loadBefore(params: LoadParams<Int>, callback: LoadCallback<Int, User>) {
    }


    // 加载下一页的数据时调用
    override fun loadAfter(params: LoadParams<Int>, callback: LoadCallback<Int, User>) {
        NetRequestCreator.create()
            .getUsers(params.key, PER_PAGE, SITE)
            .enqueue(object : Callback<UserResponse> {
                override fun onResponse(
                    call: Call<UserResponse>,
                    response: Response<UserResponse>,
                ) {
                    response.body()?.let {
                        val nextKey = if (it.hasMore) {
                            params.key + 1
                        } else {
                            null
                        }
                        callback.onResult(it.users, nextKey)
                    }
                }

                override fun onFailure(call: Call<UserResponse>, t: Throwable) {
                }
            })
    }
}