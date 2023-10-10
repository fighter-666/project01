package com.example.myapplication.recharge.widget

object HideOnScrollManager {
    // 定义一个回调接口
    interface OnHideOnScrollListener {
        fun onHide()
    }

    private var onHideOnScrollListener: OnHideOnScrollListener? = null

    // 设置回调监听器
    fun setOnHideOnScrollListener(listener: OnHideOnScrollListener) {
        onHideOnScrollListener = listener
    }

    // 当滑动时触发隐藏事件
    fun notifyScrolling() {
        onHideOnScrollListener?.onHide()
    }


}
