package com.example.myapplication.recharge.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter


class MyPagerAdapter(fm: FragmentManager, fragmentList: List<Fragment>) :
    FragmentPagerAdapter(fm) {
    var fragmentList: List<Fragment>

    //构造方法，方便之后赋值调用
    init {
        this.fragmentList = fragmentList
    }

    //根据Item的位置返回对应位置的Fragment，绑定item和Fragment
    override fun getItem(position: Int): Fragment {
        return fragmentList[position]
    }

    //设置item的数量
    override fun getCount(): Int {
        return fragmentList.size
    }
}
