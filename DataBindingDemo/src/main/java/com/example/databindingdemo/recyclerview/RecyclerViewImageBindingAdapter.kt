package com.example.databindingdemo.recyclerview

import android.graphics.Color
import android.text.TextUtils
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.example.databindingdemo.R
import com.squareup.picasso.Picasso

object RecyclerViewImageBindingAdapter {
    @JvmStatic
    @BindingAdapter("itemImage")
    fun setImage(imageView: ImageView, imageUrl: String) {
        if (!TextUtils.isEmpty(imageUrl)) {
            Picasso.get()
                .load(imageUrl)
                .placeholder(R.drawable.ic_launcher_background)
                .error(R.drawable.ic_launcher_background)
                .into(imageView)
        } else {
            imageView.setBackgroundColor(Color.DKGRAY)
        }
    }
}