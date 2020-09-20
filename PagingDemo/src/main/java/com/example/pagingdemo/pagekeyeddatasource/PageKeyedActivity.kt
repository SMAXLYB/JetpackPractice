package com.example.pagingdemo.pagekeyeddatasource

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.pagingdemo.R
import com.example.pagingdemo.pagekeyeddatasource.adapter.UserAdapter
import com.example.pagingdemo.pagekeyeddatasource.data.UserViewModel

class PageKeyedActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_page_keyed)

        val recyclerView = findViewById<RecyclerView>(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.setHasFixedSize(true)

        val adapter = UserAdapter(this)
        val userViewModel = ViewModelProvider(this).get(UserViewModel::class.java)
        userViewModel.userPagedList.observe(this) {
            adapter.submitList(it)
        }
        recyclerView.adapter = adapter
    }
}