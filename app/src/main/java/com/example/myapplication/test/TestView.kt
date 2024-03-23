package com.example.myapplication.test

import android.content.Context
import android.util.AttributeSet
import androidx.constraintlayout.widget.ConstraintLayout
import com.dylanc.viewbinding.nonreflection.binding
import com.dylanc.viewbinding.nonreflection.inflate
import com.example.myapplication.databinding.ViewTestBinding

class TestView(context: Context, attrs: AttributeSet? = null) : ConstraintLayout(context, attrs) {

    private val binding = inflate(ViewTestBinding::inflate)
    private val childBinding by binding(ViewTestBinding::inflate, attachToParent = false)

    init {
        binding.container.addView(childBinding.root)
    }
}
