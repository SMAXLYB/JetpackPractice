package com.example.databindingdemo

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.example.databindingdemo.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var book: Book

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // 取消了原来的setContentView
        binding = DataBindingUtil.setContentView<ActivityMainBinding>(this, R.layout.activity_main)

        // 将数据传递给布局
        book = Book("Android高级编程","叶坤")
        binding.book = book

        // 事件监听
        binding.clickHandler = ClickHandler()

        // 自定义BindingAdapter
        binding.networkImage = "https://www.smax.top/smax/img/smax.jpg"
        binding.localImage = R.mipmap.ic_launcher
        binding.imagePadding = 40
    }

    inner class ClickHandler{
        // 方法参数要一致
        fun onClick(view: View){
            book.rating.set(3)
            binding.imagePadding = 180
        }
    }
}
