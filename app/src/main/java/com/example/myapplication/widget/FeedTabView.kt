package com.example.myapplication.widget

import android.content.Context
import android.graphics.Typeface
import android.util.AttributeSet
import android.util.TypedValue
import android.view.MotionEvent
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.viewpager.widget.ViewPager
import com.bumptech.glide.Glide
import com.example.myapplication.R
import com.example.myapplication.activity.components.RechargePageActivity
import com.example.myapplication.recharge.data.GetFeedTabData
import com.example.myapplication.util.DensityUtils
import com.google.android.material.tabs.TabLayout

class FeedTabView constructor(context: Context, attrs: AttributeSet) :
    TabLayout(context, attrs) {

    var feedScene = ""
    private lateinit var onTabListener: OnTabSelectedListener
    private var mGetFeedTabData: GetFeedTabData ?= null
    private var mIsSticky = false

    init {
        initView(context, attrs)
    }

    private fun initView(context: Context, attrs: AttributeSet) {
        val mTypedArray = context.obtainStyledAttributes(attrs, R.styleable.FeedTabView)
        mTypedArray.recycle()
    }

    fun setData(getFeedTabData: GetFeedTabData, mPager: ViewPager) {
        mGetFeedTabData = getFeedTabData
        if (getFeedTabData.tabList.isNotEmpty()) {
            getFeedTabData.tabList.run {
                removeAllTabs()
                if (::onTabListener.isInitialized) {
                    removeOnTabSelectedListener(onTabListener)
                }
                for (i in indices) {
                    addTab(newTab())
                }
                setupWithViewPager(mPager)
                for (i in indices) {
                    val tabListBean: GetFeedTabData.TabListBean = get(i)
                    //获取每一个tab对象
                    val tabAt = getTabAt(i)
                    //将每一个条目设置我们自定义的视图
                    tabAt?.run {
                        setCustomView(R.layout.tablayout_feed_item)
                        customView?.run {
                            //通过tab对象找到自定义视图的ID
                            val textView = findViewById<TextView>(R.id.tvTab)
                            val ivIcon = findViewById<ImageView>(R.id.ivIcon)
                            val ivTabDot = findViewById<ImageView>(R.id.ivTabDot)
                            val ivIconDot = findViewById<ImageView>(R.id.ivIconDot)

                            if ("2" == tabListBean.tabType) {
                                Glide.with(RechargePageActivity.mContext)
                                    .load(tabListBean.tabIcon)//使用 load() 方法传入 URL 字符串 imageUrl 来指定要加载的图片资源
                                    //使用 transition() 方法可以设置过渡效果，例如交叉淡入淡出效果
                                    .error(R.drawable.baseline_thumb_up_24)
                                    .into(ivIcon)
                                textView.visibility = GONE
                              /*  if (UtilRefresh.isShowRedDot(
                                        tabListBean.redFlag,
                                        tabListBean.timestamp,
                                        tabListBean.tabName,
                                        feedScene
                                    )
                                ) {
                                    ivIconDot.visibility = VISIBLE
                                } else {
                                    ivIconDot.visibility = GONE
                                }*/
                                val lp1 = ivIcon.layoutParams
                                lp1.height = DensityUtils.dpToPx(RechargePageActivity.mContext,16f)
                                ivIcon.layoutParams = lp1
                            } else {
                                textView.text = tabListBean.tabName //设置tab上的文字
                                textView.visibility = VISIBLE
                               /* if (UtilRefresh.isShowRedDot(
                                        tabListBean.redFlag,
                                        tabListBean.timestamp,
                                        tabListBean.tabName,
                                        feedScene
                                    )
                                ) {
                                    ivTabDot.visibility = VISIBLE
                                } else {
                                    ivTabDot.visibility = GONE
                                }*/
                            }
                        }
                    }
                }

                var isNoDefault = true
                for (i in 0 until this.size) {
                    val tabAt = getTabAt(i)
                    val bean = this[i]
                    // 默认选中
                    if (bean.isDefault == GetFeedTabData.IS_DEFAULT.YES) {
                        // 设置第一个tab的TextView是被选择的样式
                        tabAt?.run {
                            isNoDefault = false
                            updateTabView(this, true)
                        }
                        break
                    }
                }

                // 都没配默认选中第一个
                if (isNoDefault) {
                    val tabAt = getTabAt(0)
                    tabAt?.run {
                        updateTabView(this, true)
                    }
                }

                onTabListener = object : OnTabSelectedListener {
                    override fun onTabSelected(tab: Tab) {
                        updateTabView(tab, true)
                    }

                    override fun onTabUnselected(tab: Tab) {
                        updateTabView(tab, false)
                    }

                    override fun onTabReselected(tab: Tab) {}
                }
                addOnTabSelectedListener(onTabListener)
                mPager.currentItem = getDefaultTab(this)
            }
        }
    }

    private fun getDefaultTab(tabList: List<GetFeedTabData.TabListBean>): Int {
        var index = 0
        for (i in tabList.indices) {
            if (tabList[i].isDefault == GetFeedTabData.IS_DEFAULT.YES) {
                index = i
                break
            }
        }
        return index
    }

    /**
     * 设置有副标题的时候笑脸的显示逻辑
     */
    fun setSubTypeIndicatorVisible(visibility: Boolean){
        mGetFeedTabData?.run {
            if (tabList.size > 0 && selectedTabPosition >= 0){
//                if (!tabList[selectedTabPosition].tagList.isNullOrEmpty()){
                    val tabAt = getTabAt(selectedTabPosition)
                    tabAt?.customView?.run {
                        val ivIndicator = findViewById<ImageView>(R.id.ivIndicator)
                        if (visibility){
                            mIsSticky = true
                            ivIndicator.visibility = View.VISIBLE
                        }else{
                            mIsSticky = false
                            ivIndicator.visibility = View.INVISIBLE
                        }
                    }
//                }
            }
        }
    }

    /**
     * 用来改变tabLayout选中后的字体大小及颜色
     * @param tab
     * @param isSelect
     */
    private fun updateTabView(tab: Tab, isSelect: Boolean) {
        //找到自定义视图的控件ID
        tab.customView?.run {
            val tvTab = findViewById<TextView>(R.id.tvTab)
            val ivTabDot = findViewById<ImageView>(R.id.ivTabDot)
            val ivIcon = findViewById<ImageView>(R.id.ivIcon)
            val ivIconDot = findViewById<ImageView>(R.id.ivIconDot)
            val ivIndicator = findViewById<ImageView>(R.id.ivIndicator)
            ivTabDot.visibility = View.GONE
            ivIconDot.visibility = View.GONE
            if (isSelect) {
                val lp1 = ivIcon.layoutParams
                //设置标签选中
                tvTab.isSelected = true
                tvTab.typeface = Typeface.defaultFromStyle(Typeface.BOLD)
                //选中后主标题字体变大, 副标题变小
                tvTab.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 20f)
                lp1.height = DensityUtils.dpToPx(context,18f)
                ivIcon.layoutParams = lp1
                if (mIsSticky){
                    ivIndicator.visibility = View.VISIBLE
                }
            } else {
                //设置标签取消选中
                tvTab.isSelected = false
                tvTab.typeface = Typeface.defaultFromStyle(Typeface.NORMAL)
                val lp1 = ivIcon.layoutParams
                //恢复为默认字体大小
                tvTab.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 15f)
                lp1.height = DensityUtils.dpToPx(context,16f)
                ivIcon.layoutParams = lp1
                ivIndicator.visibility = View.INVISIBLE
            }
        }
    }

    private var mLastX = 0
    private var mLastY = 0
    override fun dispatchTouchEvent(ev: MotionEvent): Boolean {
        val x = ev.x.toInt()
        val y = ev.y.toInt()
        when (ev.action) {
            MotionEvent.ACTION_DOWN -> {
                // 避免滑动冲突，本范围内告诉父控件不拦截
                parent.requestDisallowInterceptTouchEvent(true)
            }

            MotionEvent.ACTION_MOVE -> {
                val deltaX: Int = x - mLastX
                val deltaY: Int = y - mLastY
                // 纵向交给父控件，横向防止滑太快导致纵向移动
                if (Math.abs(deltaX) < Math.abs(deltaY)) {
                    parent.requestDisallowInterceptTouchEvent(false)
                    return false
                }
            }

            else -> {}
        }
        mLastX = x
        mLastY = y
        return super.dispatchTouchEvent(ev)
    }

}