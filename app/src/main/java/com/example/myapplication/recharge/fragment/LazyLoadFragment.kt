package com.example.myapplication.recharge.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.myapplication.R

class LazyLoadFragment : Fragment() {

    private var isDataLoaded = false

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_recharge_waterfall, container, false)
    }

    fun onFragmentVisible() {
        lazyLoadData()
    }

    private fun lazyLoadData() {
        if (!isDataLoaded) {
            // 加载数据的代码
            isDataLoaded = true
        }
    }
}
