package com.example.myapplication.recharge.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.myapplication.recharge.fragment.WapFragment
import com.example.myapplication.recharge.fragment.WaterfallFragment

class FragmentAdapter(fragmentManager: FragmentManager, lifecycle: Lifecycle) :
    FragmentStateAdapter(fragmentManager, lifecycle) {
    private val fragments = listOf(
        WaterfallFragment(),
        WapFragment(),
        WaterfallFragment(),
        WaterfallFragment(),
        WaterfallFragment(),
        WaterfallFragment(),
        WaterfallFragment(),
        //加载更多的 Fragment 实例
    )

    override fun getItemCount(): Int {
        return fragments.size
    }

    override fun createFragment(position: Int): Fragment {
        return fragments[position]
    }
}