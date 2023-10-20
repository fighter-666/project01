package com.example.myapplication.recharge.adapter

import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter


// viewPager2的adapter 本质上是RecyclerView 的Adapter

// viewPager2的adapter 本质上是RecyclerView 的Adapter
// viewPager2 的 适配器
class Viewpager2Adapter(activity: AppCompatActivity) : FragmentStateAdapter(activity) {
    private var fragments: MutableList<Fragment>? = null

    init {
        if (fragments == null) {
            fragments = ArrayList()
        }
    }

    fun addFragment(fragment: Fragment) {
        fragments?.add(fragment)
    }

/*    fun deleteFragment(fragment: Fragment) {
        fragments?.remove(fragment)
    }

    fun clearAllFragment() {
        if (fragments != null) {
            fragments!!.clear()
            notifyDataSetChanged()
        }
    }*/

    override fun createFragment(position: Int): Fragment {
        return fragments!![position]
    }

    override fun getItemCount(): Int {
        return fragments!!.size
    }
}