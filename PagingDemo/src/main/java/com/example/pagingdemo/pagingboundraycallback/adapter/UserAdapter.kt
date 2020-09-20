package com.example.pagingdemo.pagingboundraycallback.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.pagingdemo.R
import com.example.pagingdemo.pagingboundraycallback.model.User
import com.squareup.picasso.Picasso

class UserAdapter(private val context: Context) :
    PagedListAdapter<User, UserAdapter.UserViewHolder>(DIFF_CALLBACK) {

    companion object {
        val DIFF_CALLBACK = object : DiffUtil.ItemCallback<User>() {
            override fun areItemsTheSame(oldItem: User, newItem: User): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: User, newItem: User): Boolean {
                return oldItem == newItem
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        val view = LayoutInflater.from(context)
            .inflate(R.layout.item_user, parent, false)
        return UserViewHolder(view)
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        val user = getItem(position)
        if (user != null) {
            Picasso.get()
                .load(user.avatar)
                .placeholder(R.drawable.ic_launcher_background)
                .error(R.drawable.error)
                .into(holder.ivAvatar)

            holder.tvName.text = user.name
        } else {
            holder.ivAvatar.setImageResource(R.drawable.ic_launcher_background)
            holder.tvName.text = ""
        }
    }

    inner class UserViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val ivAvatar: ImageView = itemView.findViewById(R.id.ivAvatar)
        val tvName: TextView = itemView.findViewById(R.id.tvName)
    }
}