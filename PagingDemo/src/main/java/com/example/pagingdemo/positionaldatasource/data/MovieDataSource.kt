package com.example.pagingdemo.positionaldatasource.data

import android.util.Log
import androidx.paging.PositionalDataSource
import com.example.pagingdemo.positionaldatasource.model.Movie
import com.example.pagingdemo.positionaldatasource.model.Movies
import com.example.pagingdemo.positionaldatasource.network.NetRequestCreator
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MovieDataSource : PositionalDataSource<Movie>() {

    // 每页加载8个条目
    companion object {
        const val PER_PAGE = 8
    }

    // 页面首次加载数据时调用,将加载出来的数据返回给callback,从而返回给pageList
    override fun loadInitial(params: LoadInitialParams, callback: LoadInitialCallback<Movie>) {
        val startPosition = 0;
        NetRequestCreator.create()
            // 先获取首页,8条数据
            .getMovies(startPosition, PER_PAGE)
            .enqueue(object : Callback<Movies> {
                override fun onResponse(call: Call<Movies>, response: Response<Movies>) {
                    response.body()?.let {
                        Log.d("loadInitial()", "startPosition:"+params.requestedStartPosition+" response:"+response.body());
                        callback.onResult(it.movieList, it.start,it.count)
                    }
                }

                override fun onFailure(call: Call<Movies>, t: Throwable) {
                }
            })
    }

    // 首页数据加载完毕后,接着加载下一页的工作会在此方法中进行
    override fun loadRange(params: LoadRangeParams, callback: LoadRangeCallback<Movie>) {
        NetRequestCreator.create()
            .getMovies(params.startPosition, PER_PAGE)
            .enqueue(object : Callback<Movies> {
                override fun onResponse(call: Call<Movies>, response: Response<Movies>) {
                    response.body()?.let {
                        Log.d("loadRange()", "startPosition:" + params.startPosition + " response:" + response.body());
                        callback.onResult(it.movieList)
                    }
                }

                override fun onFailure(call: Call<Movies>, t: Throwable) {
                }
            })
    }
}