package com.example.movieapppaging3mvmm.moviedetails.ui.utils

import android.view.View
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide

@BindingAdapter("load")
fun loadImage(view:ImageView,url:String?){
    url?.let {
        Glide.with(view).load(url).into(view)
    }
}