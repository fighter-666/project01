package com.example.myapplication.util

import android.app.Activity
import android.content.Context


//真实宽高
object GetScreenUtils {
    fun getScreenWidth(context: Context): Int {
        val displayMetrics = context.resources.displayMetrics
        (context as Activity).windowManager.defaultDisplay.getRealMetrics(displayMetrics)
        return displayMetrics.widthPixels
    }

    fun getScreenHeight(context: Context): Int {
        val displayMetrics = context.resources.displayMetrics
        (context as Activity).windowManager.defaultDisplay.getRealMetrics(displayMetrics)
        return displayMetrics.heightPixels
    }
}