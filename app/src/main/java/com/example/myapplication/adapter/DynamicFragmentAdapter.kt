package com.example.myapplication.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.myapplication.fragment.ComponentsFragment
import com.example.myapplication.fragment.HelperFragment
import com.example.myapplication.fragment.LabFragment
import com.example.myapplication.fragment.WaterfallFragment

class DynamicFragmentAdapter(fragmentManager: FragmentManager, lifecycle: Lifecycle) :
    FragmentStateAdapter(fragmentManager, lifecycle) {
    private val fragments = listOf(
        ComponentsFragment(),
        HelperFragment(),
        LabFragment(),
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