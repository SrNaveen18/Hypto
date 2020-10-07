package com.example.hypto.helper

import androidx.appcompat.widget.AppCompatImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide

object BindingAdapters {

    @BindingAdapter("loadImage")
    @JvmStatic
    fun loadImage(imageView: AppCompatImageView, url: String) {
        /*Testing*/
        Glide.with(imageView.context)
            .load("https://i.picsum.photos/id/999/200/200.jpg?hmac=iwXALEStJtHL4Thxk_YbLNHNmjq9ZrIQYFUvtxndOaU")
            .into(imageView)
    }
}