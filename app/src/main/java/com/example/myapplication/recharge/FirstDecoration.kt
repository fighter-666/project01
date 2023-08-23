package com.example.myapplication.recharge

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.recharge.DensityUtils

class FirstDecoration : RecyclerView.ItemDecoration() {

    //outRect：用于指定目标项的四个方向的偏移量（上、下、左、右）。
    //view：当前的目标项 View。
    //parent：RecyclerView 的父容器。
    //state：RecyclerView 的状态。
    override fun getItemOffsets(outRect: Rect, view: View, parent: RecyclerView, state: RecyclerView.State) {
        super.getItemOffsets(outRect, view, parent, state)

        //获取item的个数
        val itemCount = parent.adapter?.itemCount ?: 0

        if (itemCount <= 3) {
            val layoutParams = view.layoutParams   //获取列表项视图（item view）的布局参数。

            //获取item 的宽度
            val itemWidth = (parent.measuredWidth - DensityUtils.dpToPx(view.context, 30f)) / 3
            layoutParams.width = itemWidth
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