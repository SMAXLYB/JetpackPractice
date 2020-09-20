package com.example.pagingdemo.itemkeyeddatasource.data

import android.util.Log
import androidx.paging.ItemKeyedDataSource
import com.example.pagingdemo.itemkeyeddatasource.model.User
import com.example.pagingdemo.itemkeyeddatasource.network.NetRequestCreator
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class UserDataSource : ItemKeyedDataSource<Int, User>() {

    companion object {
        const val PER_PAGE = 12
    }

    // 首次加载时调用
    override fun loadInitial(params: LoadInitialParams<Int>, callback: LoadInitialCallback<User>) {
        val since = 0
        NetRequestCreator.create()
            .getUsers(since, PER_PAGE)
            .enqueue(object : Callback<List<User>> {
                override fun onResponse(call: Call<List<User>>, response: Response<List<User>>) {
                    response.body()?.let {
                        Log.d("TAG", "onResponse: $response")
                        callback.onResult(it)
                    }
                }

                override fun onFailure(call: Call<List<User>>, t: Throwable) {
                }
            })
    }

    // 加载下一页时调用
    override fun loadAfter(params: LoadParams<Int>, callback: LoadCallback<User>) {
        NetRequestCreator.create()
            .getUsers(params.key, PER_PAGE)
            .enqueue(object : Callback<List<User>> {
                override fun onResponse(call: Call<List<User>>, response: Response<List<User>>) {
                    response.body()?.let {
                        Log.d("TAG", "onResponse: $response")

                        callback.onResult(it)
                    }
                }

                override fun onFailure(call: Call<List<User>>, t: Throwable) {
                }
            })
    }

    //作为下一页请求的key
    override fun getKey(item: User): Int = item.id

    override fun loadBefore(params: LoadParams<Int>, callback: LoadCallback<User>) {
    }
}

