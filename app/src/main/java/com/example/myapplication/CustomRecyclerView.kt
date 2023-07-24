package com.example.myapplication

import android.content.Context
import android.util.AttributeSet
import android.view.MotionEvent
import androidx.recyclerview.widget.RecyclerView


/**
 * @Description isCanSlide 设置为false时，viewpager不能横向滑动，用在横向滑动控件与viewpager冲突时
 * @Version 1.0
 */
class CustomRecyclerView : RecyclerView {
    private var startX = 0f
    private var startY = 0f

    constructor(context: Context?) : super(context!!) {}
    constructor(context: Context?, attrs: AttributeSet?) : super(
        context!!, attrs
    ) {
    }

    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(
        context!!, attrs, defStyleAttr
    ) {
    }

    override fun onInterceptTouchEvent(event: MotionEvent): Boolean {
        when (event.action) {
            MotionEvent.ACTION_DOWN -> {
                startX = event.x
                startY = event.y
            }

            MotionEvent.ACTION_MOVE -> {
                val offsetX = event.x - startX
                val offsetY = event.y - startY
                if (Math.abs(offsetX) > Math.abs(offsetY)) { // 水平滑动优先
                    parent.requestDisallowInterceptTouchEvent(true)
                }
            }
        }
        return super.onInterceptTouchEvent(event)
    }

    override fun onTouchEvent(event: MotionEvent): Boolean {
        val action = event.action
        when (action) {
            MotionEvent.ACTION_UP, MotionEvent.ACTION_CANCEL -> parent.requestDisallowInterceptTouchEvent(
                false
            )
        }
        return super.onTouchEvent(event)
    }
}