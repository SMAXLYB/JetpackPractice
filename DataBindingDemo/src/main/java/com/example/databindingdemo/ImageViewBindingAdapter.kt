package com.example.databindingdemo

import android.graphics.Color
import android.text.TextUtils
import android.util.Log
import android.view.View
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.squareup.picasso.Picasso

object ImageViewBindingAdapter {

    private const val TAG = "ImageViewBindingAdapter"

    @JvmStatic
    @BindingAdapter("image")
    fun setImage(imageView: ImageView, imageUrl: String) {
        if (!TextUtils.isEmpty(imageUrl)) {
            Picasso.get()
                .load(imageUrl)
                .placeholder(R.drawable.loading)
                .error(R.drawable.error)
                .into(imageView)
        } else {
            imageView.setBackgroundColor(Color.DKGRAY)
        }
    }

    // 方法重载
    @JvmStatic
    @BindingAdapter("image")
    fun setImage(imageView: ImageView, imageResource: Int) {
        imageView.setImageResource(imageResource)
    }

    // 多参数重载
    @JvmStatic
    @BindingAdapter(value = ["image", "DefaultImageResource"], requireAll = false)
    fun setImage(imageView: ImageView, imageUrl: String, imageResource: Int) {
        if (!TextUtils.isEmpty(imageUrl)) {
            Picasso.get()
                .load(imageUrl)
                .placeholder(R.drawable.loading)
                .error(R.drawable.error)
                .into(imageView)
        } else {
            imageView.setImageResource(imageResource)
        }
    }

    // 可选旧值,旧值必须在前,新值在后
    @JvmStatic
    @BindingAdapter("padding")
    fun setPadding(view: View, oldPadding: Int, newPadding: Int) {
        Log.d(TAG, "oldPadding: $oldPadding, newPadding: $newPadding")
        if (oldPadding != newPadding) {
            view.setPadding(newPadding, newPadding, newPadding, newPadding)
        }
    }
}