package com.clevmania.tellerium.utils

import android.widget.ImageView
import com.bumptech.glide.Glide
import com.clevmania.tellerium.R
import com.google.android.material.imageview.ShapeableImageView

/**
 * @author by Lawrence on 10/24/20.
 * for Tellerium
 */
fun ShapeableImageView.loadImage(imageUrl: String){
    Glide.with(context)
        .load(imageUrl)
        .placeholder(R.drawable.ic_planting)
        .error(R.drawable.ic_planting)
        .into(this)
}

fun ImageView.loadImage(imageUrl: String){
    Glide.with(context)
        .load(imageUrl)
        .placeholder(R.drawable.ic_planting)
        .error(R.drawable.ic_planting)
        .into(this)
}