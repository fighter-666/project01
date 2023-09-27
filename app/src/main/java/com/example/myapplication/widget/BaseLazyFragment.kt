package com.example.myapplication.widget

import androidx.fragment.app.Fragment


open class BaseLazyFragment : Fragment() {
    override fun onResume() {
        super.onResume()
        loadData()
    }

     open fun loadData() {
        // 你的加载逻辑
    }
}


