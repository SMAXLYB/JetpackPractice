package com.example.pagingdemo.positionaldatasource

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.pagingdemo.R
import com.example.pagingdemo.positionaldatasource.adapter.MoviePagedListAdapter
import com.example.pagingdemo.positionaldatasource.data.MovieViewModel

/**
 * PageKeyedDataSource：按页加载
 * ItemKeyedDataSource：按条目加载
 * PositionalDataSource：按位置加载(从n到n+x)
 */

class PositionalSourceActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_positional)

        val recyclerView = findViewById<RecyclerView>(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.setHasFixedSize(true)

        val adapter = MoviePagedListAdapter(this)

        val viewModel = ViewModelProvider(this).get(MovieViewModel::class.java)
        viewModel.moviePageList.observe(this) {
            Log.e("TAG", "onCreate: 数据发生改变" )
            adapter.submitList(it)
        }

        recyclerView.adapter = adapter
    }
}
