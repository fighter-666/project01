package com.example.myapplication.util

import android.util.Log
import android.view.View
import com.example.myapplication.data.User

class ClickHandlers {
    var TAG = "ClickHandlers"
    fun confirm(view: View){
        Log.d(TAG, "触发点击事件了")
    }

    fun confirm2(view: View, user: User){
        Log.d(TAG, "触发点击事件了2")
    }
}