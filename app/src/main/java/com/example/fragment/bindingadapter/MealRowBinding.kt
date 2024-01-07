package com.example.fragment.bindingadapter

import android.graphics.drawable.Drawable
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.databinding.BindingAdapter
import coil.load
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.bumptech.glide.request.transition.DrawableCrossFadeFactory
import com.example.fragment.R


class MealRowBinding {

    companion object{

        @BindingAdapter("setNumberOfLikes")
        @JvmStatic
        fun setNumberOfLikes(textView: TextView, likes: Int){
            textView.text = likes.toString()
            val drawable: Drawable? = textView.compoundDrawablesRelative[1]
            when(likes){
               in 0..20-> {
                   textView.setTextColor(ContextCompat.getColor(textView.context,R.color.gray))
                   drawable?.setTint(ContextCompat.getColor(textView.context, R.color.gray))
               }
               in 21..50 ->{
                   textView.setTextColor(ContextCompat.getColor(textView.context,R.color.orange))
                   drawable?.setTint(ContextCompat.getColor(textView.context, R.color.orange))
               }
                in 51..100 ->{
                    textView.setTextColor(ContextCompat.getColor(textView.context,R.color.red))
                    drawable?.setTint(ContextCompat.getColor(textView.context, R.color.red))
                }
            }

        }

        var factory: DrawableCrossFadeFactory = DrawableCrossFadeFactory.Builder()
            .setCrossFadeEnabled(true)
            .build()

        @BindingAdapter("loadImageFromUrl")
        @JvmStatic
        fun loadImageFromUrl(imageView: ImageView, imageUrl: String) {
            imageView.load(imageUrl) {
                crossfade(600)
                error(R.drawable.ic_launcher_background)
            }
            //glide example
//            Glide.with(imageView)
//                .load(imageUrl)
//                .transition(DrawableTransitionOptions.withCrossFade(factory))
//                .into(imageView)
        }
    }
}