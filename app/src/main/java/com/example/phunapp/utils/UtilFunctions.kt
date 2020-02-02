package com.example.phunapp.utils

import android.content.Context
import androidx.core.graphics.drawable.RoundedBitmapDrawableFactory
import android.graphics.BitmapFactory
import androidx.core.graphics.drawable.RoundedBitmapDrawable


fun converDrawableToCircular(context: Context, id: Int): RoundedBitmapDrawable {
    val placeholder = BitmapFactory.decodeResource(context.resources, id)
    val circularBitmapDrawable = RoundedBitmapDrawableFactory.create(context.resources, placeholder)
    circularBitmapDrawable.isCircular = true
    return circularBitmapDrawable
}