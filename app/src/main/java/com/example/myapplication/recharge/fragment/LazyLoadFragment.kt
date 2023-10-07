package com.example.myapplication.recharge.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.myapplication.R

open class LazyLoadFragment : Fragment() {
    private val isViewCreated = false
    @Deprecated("Deprecated in Java")
    override fun setUserVisibleHint(isVisibleToUser: Boolean) {
        super.setUserVisibleHint(isVisibleToUser)


        if (isViewCreated) {
            dispatchUserVisibleHint(isVisibleToUser)
        }
    }

/*
    override fun onResume() {
        super.onResume()
        // userVisibleHint 是为了判断 Fragment 是正在显示的，
        // !lastVisibleState 表示之前页面生命周期不可见，但是现在
        // 回到 onResume() 处于可见状态了，就要修改该状态
        if (userVisibleHint && !lastVisibleState) {
            dispatchUserVisibleHint(true)
        }
    }

    override fun onPause() {
        super.onPause()
        if (userVisibleHint && lastVisibleState) {
            dispatchUserVisibleHint(false)
        }
    }
*/

    /**
     * 页面是否可见，onResume 之前为 true，onPause 开始之后为 false
     */
    private var lastVisibleState = false

    fun dispatchUserVisibleHint(visibleToUser: Boolean) {
        lastVisibleState = visibleToUser

        if (visibleToUser) {
            onFragmentLoadStart()
        } else {
            onFragmentLoadStop()
        }
    }


    // 由于子类使用懒加载并不是强制的，因此这两个方法没有设置为 abstract
    open fun onFragmentLoadStart() {
    }

    open fun onFragmentLoadStop() {
    }

}
