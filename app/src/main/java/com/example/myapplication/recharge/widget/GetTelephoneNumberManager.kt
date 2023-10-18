package com.example.myapplication.recharge.widget

// LoadMoreManager.kt

object GetTelephoneNumberManager {
    // 定义一个回调接口
    interface OnGetTelephoneNumberManager {
        fun onGetTelephoneNumber(number: String)
    }

    private var onGetTelephoneNumberManager: OnGetTelephoneNumberManager? = null

    // 设置回调监听器
    fun setGetTelephoneNumberListener(listener: OnGetTelephoneNumberManager) {
        onGetTelephoneNumberManager = listener
    }

    // 触发加载更多事件
    fun triggerGetTelephoneNumber(number: String) {
        onGetTelephoneNumberManager?.onGetTelephoneNumber(number)
    }
}
