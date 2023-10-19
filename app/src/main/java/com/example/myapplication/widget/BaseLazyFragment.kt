package com.example.myapplication.widget

import androidx.fragment.app.Fragment


open class BaseLazyFragment : Fragment() {
    private var isDataLoaded = false

    override fun onResume() {
        super.onResume()
        if (!isDataLoaded) {
            loadData()
            isDataLoaded = true
        }


    }

     open fun loadData() {
        // 你的加载逻辑
    }
}


