package com.example.myapplication.widget

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import org.jetbrains.annotations.Nullable


open class BaseLazyFragment : Fragment() {
    private var isViewPrepared = false // 标识fragment视图已经初始化完毕
    private var hasFetchData = false // 标识已经触发过懒加载数据
    override fun setUserVisibleHint(isVisibleToUser: Boolean) {
        super.setUserVisibleHint(isVisibleToUser)
        lazyFetchDataIfPrepared() //经过了预加载页面, 然后展示
    }

    override fun onViewCreated(view: View, @Nullable savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        isViewPrepared = true
        lazyFetchDataIfPrepared() //首次进入, 没有预加载直接加载数据
    }

    /**
     * 懒加载方法，获取数据什么的放到这边来使用，在切换到这个界面时才进行网络请求
     */
    private fun lazyFetchDataIfPrepared() {
        // 用户可见fragment && 没有加载过数据 && 视图已经准备完毕
        if (getUserVisibleHint() && !hasFetchData && isViewPrepared) {
            hasFetchData = true //已加载过数据
            lazyInit()
        }
    }

    /**
     * 执行需要懒加载的方法
     */
    open fun lazyInit() {
        Log.i("zmin........." + getArguments()?.getInt("key"), ".............加载完成数据")
    }

    override fun onDestroyView() {
        super.onDestroyView()
        hasFetchData = false
        isViewPrepared = false
        Log.i("zmin........." + getArguments()?.getInt("key"), ".............onDestroyView")
    }
}
