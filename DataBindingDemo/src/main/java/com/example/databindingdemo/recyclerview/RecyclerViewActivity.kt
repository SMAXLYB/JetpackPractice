package com.example.databindingdemo.recyclerview

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.databindingdemo.R
import com.example.databindingdemo.databinding.ActivityRecyclerviewBinding

class RecyclerViewActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding = DataBindingUtil.setContentView<ActivityRecyclerviewBinding>(this,
            R.layout.activity_recyclerview)
        binding.rv.layoutManager = LinearLayoutManager(this)
        binding.rv.setHasFixedSize(true)


        // 模拟数据
        val books = mutableListOf<Book>()
        for (i in 0..100) {
            books.add(Book("Android高性能编程",
                "叶坤$i",
                "https://img1.doubanio.com/view/subject/l/public/s29670267.jp"))
        }

        // 适配器
        val adapter = RecyclerViewAdapter(books)
        binding.rv.adapter = adapter
    }
}