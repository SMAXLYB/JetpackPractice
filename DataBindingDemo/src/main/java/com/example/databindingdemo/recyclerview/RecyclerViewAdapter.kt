package com.example.databindingdemo.recyclerview

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.databindingdemo.R
import com.example.databindingdemo.databinding.LayoutItemBinding

class RecyclerViewAdapter(private val books: List<Book>) :
    RecyclerView.Adapter<RecyclerViewAdapter.MyViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val layoutItemBinding =
            DataBindingUtil.inflate<LayoutItemBinding>(LayoutInflater.from(parent.context),
                R.layout.layout_item,
                parent,
                false)
        return MyViewHolder(layoutItemBinding)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val book = books[position]
        holder.layoutItemBinding.book = book
    }

    override fun getItemCount(): Int = books.size

    inner class MyViewHolder(var layoutItemBinding: LayoutItemBinding) :
        RecyclerView.ViewHolder(layoutItemBinding.root)
}