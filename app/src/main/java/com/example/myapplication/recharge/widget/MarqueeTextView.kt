package com.example.myapplication.recharge.widget

import android.content.Context
import android.util.AttributeSet

class MarqueeTextView : androidx.appcompat.widget.AppCompatTextView {

    constructor(context: Context) : super(context)

    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs)

    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    )

    //获取焦点
    override fun isFocused(): Boolean {
        return true
    }
}