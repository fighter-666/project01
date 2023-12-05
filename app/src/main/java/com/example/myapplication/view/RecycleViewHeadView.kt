package com.example.myapplication.view

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.widget.LinearLayout
import com.example.myapplication.binding.inflate
import com.example.myapplication.databinding.RecyclviewHeadBinding

class RecycleViewHeadView(context: Context, attrs: AttributeSet) : LinearLayout(context, attrs) {

    private val binding = inflate<RecyclviewHeadBinding>()



    init {
        visibility = View.VISIBLE
    }

    fun setData() {
        binding.run {
        }
    }
}