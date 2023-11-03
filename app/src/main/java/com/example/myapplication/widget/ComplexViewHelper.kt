package com.example.myapplication.widget

import android.widget.LinearLayout
import com.example.myapplication.data.ComplexItemEntity
import com.gongwen.marqueen.MarqueeView

class ComplexViewHelper(private val marqueeView: MarqueeView<LinearLayout, ComplexItemEntity>) {

    fun setComplexData(complexDatas: List<ComplexItemEntity>) {
        val marqueeFactory = ComplexViewMF(marqueeView.context)
        marqueeFactory.data = complexDatas
        marqueeView.setMarqueeFactory(marqueeFactory)
        marqueeView.startFlipping()
    }
}