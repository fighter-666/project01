package com.example.myapplication.recharge.widget

import java.util.Hashtable

// LoadMoreManager.kt

object LoadMoreManager {
    private val callbackMap = mutableMapOf<String, () -> Unit>()

    fun ontLoadMoreManager(key: String) {
        callbackMap[key]?.invoke()
    }

    fun setOnLoadMoreListener(key: String, listener: () -> Unit) {
        callbackMap[key] = listener
    }

    fun removeLoadMoreListener(key: String) {
        callbackMap.remove(key)
    }
}