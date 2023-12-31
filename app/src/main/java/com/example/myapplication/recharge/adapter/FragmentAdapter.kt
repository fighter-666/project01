package com.example.myapplication.recharge.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.myapplication.recharge.fragment.CommWebViewFragment
import com.example.myapplication.recharge.fragment.WaterfallFragment

class FragmentAdapter(fragmentManager: FragmentManager, lifecycle: Lifecycle) :
    FragmentStateAdapter(fragmentManager, lifecycle) {

    private val fragments = listOf (
        WaterfallFragment(),
        CommWebViewFragment(),
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

/*
class FragmentAdapter(
    fragmentManager: FragmentManager,
    lifecycle: Lifecycle,
    private val fragmentTypes: List<String>
) : FragmentStateAdapter(fragmentManager, lifecycle) {

    override fun getItemCount(): Int {
        return fragmentTypes.size
    }

    override fun createFragment(position: Int): Fragment {
        return when (fragmentTypes[position]) {
            "Waterfall" -> WaterfallFragment()
            "Wap" -> WapFragment()
            // ... 其他类型的 Fragments ...
            else -> WaterfallFragment()  // 一个默认的 Fragment，或者可以抛出异常
        }
    }
}*/
