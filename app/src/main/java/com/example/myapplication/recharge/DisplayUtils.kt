package com.example.recharge

import android.content.Context
import android.util.DisplayMetrics
import android.view.WindowManager

object DisplayUtils {
    fun getScreenWidth(context: Context): Int {
        val windowManager = context.getSystemService(Context.WINDOW_SERVICE) as WindowManager
        val displayMetrics = DisplayMetrics()
        windowManager.defaultDisplay.getMetrics(displayMetrics)
        return displayMetrics.widthPixels
    }
    //getScreenWidth(context: Context): Int：这个函数用于获取屏幕的宽度。
    //context: Context：上下文对象，用于获取窗口管理器和显示度量相关的信息。
    //首先通过 context.getSystemService(Context.WINDOW_SERVICE) 来获取窗口管理器对象。
    //创建一个空的 DisplayMetrics 对象，用于存储屏幕显示的度量信息。
    //通过 windowManager.defaultDisplay.getMetrics(displayMetrics) 方法，
    // 将屏幕的度量信息赋值给 displayMetrics 对象。
    //最后返回 displayMetrics.widthPixels，即屏幕的宽度（以像素为单位）。

    fun getScreenHeight(context: Context): Int {
        val windowManager = context.getSystemService(Context.WINDOW_SERVICE) as WindowManager
        val displayMetrics = DisplayMetrics()
        windowManager.defaultDisplay.getMetrics(displayMetrics)
        return displayMetrics.heightPixels
    }
}