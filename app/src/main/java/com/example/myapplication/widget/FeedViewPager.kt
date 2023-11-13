package com.example.myapplication.widget

import android.content.Context
import android.util.AttributeSet
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.viewpager.widget.ViewPager
import com.example.myapplication.R
import com.example.myapplication.adapter.FragAdapter
import com.example.myapplication.recharge.data.GetFeedTabData
import com.example.myapplication.recharge.fragment.CommWebViewFragment
import com.example.myapplication.recharge.fragment.WaterfallFragment
import com.example.myapplication.recharge.util.ExtraParams
import com.scwang.smartrefresh.layout.SmartRefreshLayout

/**
 * 新版吸顶方案CoordinatorLayout使用的ViewPager
 */
class FeedViewPager @JvmOverloads constructor(context: Context, attrs: AttributeSet) :
    ViewPager(context, attrs) {

    // 底部feed流
    private val fragmentsList = ArrayList<Fragment>()
    private lateinit var mFragAdapter: FragAdapter
    private val CONTENT2 = ArrayList<String>()
    lateinit var smartRefreshLayout: SmartRefreshLayout
    lateinit var fragmentManager: FragmentManager
    lateinit var feedTabView: FeedTabView
    private var mIsCare = false
    private var mIsNeedRefresh = false
    private var mFeedPaddingBottom = 0
    var feedScene = ""

    init {
        initView(context, attrs)
    }

    private fun initView(context: Context, attrs: AttributeSet) {

        val mTypedArray = context.obtainStyledAttributes(attrs, R.styleable.FeedViewPager)
        mIsCare = mTypedArray.getBoolean(R.styleable.FeedViewPager_isCare, false)
        mIsNeedRefresh = mTypedArray.getBoolean(R.styleable.FeedViewPager_isNeedRefresh, false)
        mFeedPaddingBottom =
            mTypedArray.getLayoutDimension(R.styleable.FeedViewPager_feedPaddingBottom, 0)
        feedScene = mTypedArray.getString(R.styleable.FeedViewPager_feedScene).toString()
        mTypedArray.recycle()
    }

    fun setData(getFeedTabData: GetFeedTabData) {
        if (getFeedTabData.tabList.isNotEmpty()) {
            fragmentsList.clear()
            getFeedTabData.tabList.run {
                for (i in indices) {
                    val tabListBean: GetFeedTabData.TabListBean = get(i)
                    when (tabListBean.type) {
                        // 网页
                       /* "1" -> {
                            val cWebKitFragment = CommWebViewFragment()
                            val extraParams = ExtraParams()
                            extraParams.data = tabListBean.link
                            fragmentsList.add(cWebKitFragment)
                        }*/
                        /*// 关注页
                        GetFeedTabTask.TabContentType.FOLLOW -> {
                            val feedFollowFragment = FeedFollowFragment()
                            feedFollowFragment.mIsConse = false
                            fragmentsList.add(feedFollowFragment)
                        }*/
                        // 瀑布流
                        else -> {
                            val feedFragment = WaterfallFragment()
                            fragmentsList.add(feedFragment)
                        }
                    }
                }
                mFragAdapter = FragAdapter(fragmentManager, fragmentsList, CONTENT2.toTypedArray())
                adapter = mFragAdapter
                offscreenPageLimit = size
            }
        }
    }

/*    private fun getDefaultTab(tabList: List<GetFeedTabData.TabListBean>): Int {
        var index = 0
        for (i in tabList.indices) {
            if (tabList[i].isDefault == GetFeedTabData.IS_DEFAULT.YES){
                index = i
                break
            }
        }
        return index
    }*/


  /*  fun onRefresh() {
        // 把加载的动作传给当初的fragment，网页已禁止上拉所以不用判断类型
        if (::mFragAdapter.isInitialized) {
            val fragment = mFragAdapter.getItem(currentItem)
            if (fragment is FeedFragment) {
                fragment.onRefresh()
            }
        }
    }

    fun onLoadMore() {
        // 把加载的动作传给当初的fragment，网页已禁止上拉所以不用判断类型
        if (::mFragAdapter.isInitialized) {
            val fragment = mFragAdapter.getItem(currentItem)
            if (fragment is FeedFragment) {
                fragment.onLoadMore()
            }
        }
    }

    fun onPullUp() {
        if (::mFragAdapter.isInitialized) {
            val fragment = mFragAdapter.getItem(currentItem)
            if (fragment is FeedFragment) {
                fragment.onPullUp()
            }
        }
    }

    fun setEnable(pos: Int) {
        if (fragmentsList[pos] is FeedFragment) {
            val fragment = fragmentsList[pos] as FeedFragment
            fragment.setEnable()
        } else {
            smartRefreshLayout.setEnableLoadMore(false)
            smartRefreshLayout.setEnableRefresh(false)
        }
    }

    *//**
     * 判断是否加载更多，网页禁用加载更多
     *//*
    fun setEnableLoadMore(pos: Int) {
        if (fragmentsList[pos] is FeedFragment) {
            val fragment = fragmentsList[pos] as FeedFragment
            fragment.setEnable()
        } else {
            smartRefreshLayout.setEnableLoadMore(false)
        }
    }

    fun onScrollChange() {
        if (::mFragAdapter.isInitialized) {
            val fragment = mFragAdapter.getItem(currentItem)
            if (fragment is FeedFragment){
                if (this.isInScreen) {
                    UtilLog.saveFeedLogcat("滚动回到feed瀑布流")
                    fragment.onScrollChange()
                } else {
                    UtilLog.saveFeedLogcat("滚动停止feed视频播放")
                    fragment.pause()
                }
            }else if(fragment is FeedFollowFragment){
                if (this.isInScreen) {
                    UtilLog.saveFeedLogcat("滚动回到关注流")
                    fragment.onScrollChange()
                } else {
                    UtilLog.saveFeedLogcat("滚动停止关注视频播放")
                    fragment.pause()
                }
            }
        }
    }*/
}