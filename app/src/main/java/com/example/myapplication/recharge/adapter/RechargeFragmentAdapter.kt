package com.example.myapplication.recharge.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.myapplication.recharge.fragment.RechargeWaterfallBaiduFragment
import com.example.myapplication.recharge.fragment.RechargeWaterfallFragment

class RechargeFragmentAdapter(fragmentManager: FragmentManager, lifecycle: Lifecycle) :
    FragmentStateAdapter(fragmentManager, lifecycle) {
    private val fragments = listOf(
        RechargeWaterfallFragment(),
        RechargeWaterfallBaiduFragment(),
        RechargeWaterfallFragment(),
        RechargeWaterfallFragment(),
        RechargeWaterfallFragment(),
        RechargeWaterfallFragment(),
        RechargeWaterfallFragment(),
        //加载更多的 Fragment 实例
    )

    override fun getItemCount(): Int {
        return fragments.size
    }

    override fun createFragment(position: Int): Fragment {
        return fragments[position]
    }
}