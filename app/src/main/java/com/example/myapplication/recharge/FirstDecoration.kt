package com.example.myapplication.recharge

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.recharge.DensityUtils

class FirstDecoration : RecyclerView.ItemDecoration() {

    override fun getItemOffsets(outRect: Rect, view: View, parent: RecyclerView, state: RecyclerView.State) {
        super.getItemOffsets(outRect, view, parent, state)

        val itemCount = parent.adapter?.itemCount ?: 0
        if (itemCount <= 3) {
            val layoutParams = view.layoutParams   //获取列表项视图（item view）的布局参数。
            val itemWidth = (parent.measuredWidth - DensityUtils.dpToPx(view.context, 30f)) / 3
            layoutParams.width = itemWidth.toInt()
            layoutParams.height = layoutParams.width
            view.layoutParams = layoutParams
        } else{
            val layoutParams = view.layoutParams   //获取列表项视图（item view）的布局参数。
            val itemWidth = (parent.measuredWidth - DensityUtils.dpToPx(view.context, 30f)) / 3.5
            layoutParams.width = itemWidth.toInt()
            layoutParams.height = layoutParams.width
            view.layoutParams = layoutParams
        }
    }
}