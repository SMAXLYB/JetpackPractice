package com.example.pagingdemo.positionaldatasource.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil.ItemCallback
import androidx.recyclerview.widget.RecyclerView
import com.example.pagingdemo.R
import com.example.pagingdemo.positionaldatasource.model.Movie
import com.squareup.picasso.Picasso

class MoviePagedListAdapter(private val context: Context) :
    PagedListAdapter<Movie, MoviePagedListAdapter.MovieViewHolder>(DIFF_CALLBACK) {

    // 比较两个数据列表之间的差异
    companion object {
        private val DIFF_CALLBACK = object : ItemCallback<Movie>() {
            // 检测两个对象是否属于同一个item
            override fun areItemsTheSame(oldItem: Movie, newItem: Movie): Boolean {
                return oldItem.id == newItem.id
            }

            // 检测两个item是否存在不一样的数据
            override fun areContentsTheSame(oldItem: Movie, newItem: Movie): Boolean {
                return oldItem == newItem
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val view = LayoutInflater.from(context)
            .inflate(R.layout.item_movie, parent, false)
        return MovieViewHolder(view)
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        // 自动获取下一个数据
        val movie = getItem(position)
        if (movie != null) {
            Picasso.get()
                .load(movie.images.large)
                .placeholder(R.drawable.loading)
                .error(R.drawable.error)
                .into(holder.ivImage)
            holder.tvTitle.text = movie.title
            holder.tvYear.text = "上映年份:${movie.year}"
        } else {
            holder.ivImage.setImageResource(R.drawable.loading)
            holder.tvTitle.text = ""
            holder.tvYear.text = ""
        }
    }

    inner class MovieViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var ivImage: ImageView = itemView.findViewById(R.id.ivImage)
        var tvTitle: TextView = itemView.findViewById(R.id.tvTitle)
        var tvYear: TextView = itemView.findViewById(R.id.tvYear)
    }

}

