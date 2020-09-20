package com.example.pagingdemo.pagingboundraycallback

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.example.pagingdemo.R
import com.example.pagingdemo.pagingboundraycallback.adapter.UserAdapter
import com.example.pagingdemo.pagingboundraycallback.data.UserViewModel

// 网络+缓存
class BoundaryCallbackActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_boundary_callback)

        val recyclerView = findViewById<RecyclerView>(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.setHasFixedSize(true)

        val userAdapter = UserAdapter(this)
        val userViewModel = ViewModelProvider(this).get(UserViewModel::class.java)
        userViewModel.userPagedList.observe(this) {
            userAdapter.submitList(it)
        }

        recyclerView.adapter = userAdapter

        val swipeRefresh = findViewById<SwipeRefreshLayout>(R.id.swipeRefresh)
        swipeRefresh.setOnRefreshListener {
            userViewModel.refresh()
            swipeRefresh.isRefreshing = false
        }
    }
}