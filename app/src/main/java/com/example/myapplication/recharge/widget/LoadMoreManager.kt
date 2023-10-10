package com.example.myapplication.recharge.widget

// LoadMoreManager.kt

object LoadMoreManager {
    // 定义一个回调接口
    interface OnLoadMoreListener {
        fun onLoadMore()
    }

    private var onLoadMoreListener: OnLoadMoreListener? = null

    // 设置回调监听器
    fun setOnLoadMoreListener(listener: OnLoadMoreListener) {
        onLoadMoreListener = listener
    }

    // 触发加载更多事件
    fun triggerLoadMore() {
        onLoadMoreListener?.onLoadMore()
    }
}
