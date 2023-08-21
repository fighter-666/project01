package com.example.myapplication.recharge

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView

class FirstDecoration(private val itemSize: Int) : RecyclerView.ItemDecoration() {

    override fun getItemOffsets(outRect: Rect, view: View, parent: RecyclerView, state: RecyclerView.State) {
        super.getItemOffsets(outRect, view, parent, state)

        outRect.set(itemSize, itemSize, itemSize, itemSize)
    }
}