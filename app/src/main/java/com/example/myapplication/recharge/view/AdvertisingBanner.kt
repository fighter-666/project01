/*
package com.example.myapplication.recharge.view

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import androidx.viewpager.widget.PagerAdapter
import com.example.myapplication.recharge.data.BannerBean

*/
/**
 * @brief 广告位(1个轮播)
 * @author susin
 * @date 2022-10-30
 *//*

class AdvertisingBanner(context: Context, attrs: AttributeSet? = null) : LinearLayout(context, attrs) {
    private val bannerDatas: MutableList<BannerBean> = ArrayList()
    private var advertisingBannerAdapter: AdvertisingBannerAdapter
    private var mFocusPercent = "304:70"
    private var mPadding = 10f

    private val binding = inflate<ViewLoopFocusHomeBannerBinding>()

    init {
        // 加载轮播图默认数据
        bannerDatas.clear()
        advertisingBannerAdapter = AdvertisingBannerAdapter(bannerDatas, context)

        binding.run {
            viewpager.setPeriod(4000)
            viewpager.adapter = advertisingBannerAdapter
            viewpager.isNeedStopping = true
            viewpager.setScroll(false)
            UtilView.setViewpagerDuration(viewpager, 1000)
            // 曝光
            viewpager.addOnPageChangeListener(object :
                com.ct.client.promotion.comm.OnPageChangeListener() {
                override fun onPageSelected(i: Int) {
                    super.onPageSelected(i)
                    val position = viewpager.getCurrentItem()
                    onPageChangeListener?.invoke(position)
                }
            })
        }
        val accessibilityManager =
            MyApplication.mContext.getSystemService(Context.ACCESSIBILITY_SERVICE) as AccessibilityManager
        accessibilityManager.addTouchExplorationStateChangeListener { enabled ->
            if (enabled) {
                //无障碍打开
                clearLoop()
            } else {
                //无障碍关闭
                startLoop()
            }
        }
    }

    */
/**
     * @param 宽高比,egg:"3:1"
     *//*

    fun setFocusPercent(focusPercent: String) {
        binding.run {
            mFocusPercent = focusPercent
            val set = ConstraintSet()
            set.clone(container)
            set.setDimensionRatio(R.id.viewpager, focusPercent)
            set.applyTo(container)
        }
    }

    fun setDot(dotsColor: Int){
        binding.run {
            indicator.setDotsColor(dotsColor)
        }
    }

    */
/**
     * @param 横向padding，单位pd
     *//*

    fun setPadding(paddingHorizontal: Float): AdvertisingBanner {
        mPadding = paddingHorizontal
        binding.container.setPadding(
            SizeUtils.dp2px(context, mPadding), 0,
            SizeUtils.dp2px(context, mPadding), 0
        )
        return this
    }


    private var mListener: ((Int) -> Unit)? = null
    fun setOnItemListenerCallBack(listener: (pos: Int) -> Unit): AdvertisingBanner {
        this.mListener = listener
        return this
    }

    private var onPageChangeListener: ((Int) -> Unit)? = null
    fun setOnPageChangeListener(listener: (Int) -> Unit): AdvertisingBanner {
        this.onPageChangeListener = listener
        return this
    }

    */
/**
     * 绑定数据
     * @param adItems
     *//*

    fun setData(data: List<BannerBean>): AdvertisingBanner {
        bannerDatas.clear()
        bannerDatas.addAll(data)
        updateFocus(bannerDatas)
        return this
    }

    private fun updateFocus(datas: List<BannerBean>) {
        if (datas.isEmpty()) {
            visibility = View.GONE
            return
        }

        binding.run {
            visibility = VISIBLE
            advertisingBannerAdapter = AdvertisingBannerAdapter(datas, context)
            viewpager.adapter = advertisingBannerAdapter
            UtilView.setViewpagerDuration(viewpager, 1000)
            if (datas.size > 1) {
                indicator.setViewPager(viewpager)
                indicator.visibility = VISIBLE
                viewpager.isNeedStopping = false
                viewpager.setScroll(true)
            } else {
                indicator.visibility = GONE
                viewpager.isNeedStopping = true
                viewpager.setScroll(false)
            }
            if (UtilApp.isTouchExplorationEnabled()) {
                viewpager.isNeedStopping = true
                viewpager.setLoopTask(null)
                viewpager.setScroll(true)
            }

            advertisingBannerAdapter.setItemClickListener {
                mListener?.invoke(it)
            }
        }
    }

    private fun clearLoop() {
        binding.run {
            viewpager.isNeedStopping = true
            viewpager.setLoopTask(null)
            viewpager.setScroll(true)
        }
    }

    private fun startLoop() {
        binding.run {
            viewpager.adapter?.run {
                if (count > 1) {
                    viewpager.isNeedStopping = false
                    viewpager.setScroll(true)
                }
            }
        }
    }

    fun setDotColor(color: Int): AdvertisingBanner {
        binding.indicator.setDotsColor(color)
        return this
    }

    fun setDotUnselectColor(color: Int): AdvertisingBanner {
        binding.indicator.setUnSelectColor(color)
        return this
    }

}


private class AdvertisingBannerAdapter(
    private val adItems: List<BannerBean>,
    private val mContext: Context
) :
    PagerAdapter() {
    override fun getCount(): Int {
        return adItems.size
    }

    override fun isViewFromObject(view: View, `object`: Any): Boolean {
        return view === `object`
    }

    override fun destroyItem(view: ViewGroup, position: Int, `object`: Any) {
        view.removeView(`object` as View)
    }

    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        val view = LayoutInflater.from(mContext).inflate(R.layout.vp_focus_adapter_x, null)
        val mImg = view.findViewById<ImageView>(R.id.ivFocus)
        if (adItems.isNotEmpty()) {
            UtilGlide.showImage(
                adItems[position].iconUrl,
                mImg
            )
            mImg.contentDescription = adItems[position].title
        }
        mImg.setOnClickListener {
            if (adItems.isNotEmpty()) {
                val linkItem = CommonLinkItem()
                linkItem.linkType = adItems[position].linkType
                linkItem.link = adItems[position].link
                linkItem.goTarget(mContext)
                if (::onItemClickListener.isInitialized) {
                    onItemClickListener(position)
                }
            }
        }
        container.addView(view)
        return view
    }

    lateinit var onItemClickListener: (Int) -> Unit
    fun setItemClickListener(listener: (Int) -> Unit) {
        this.onItemClickListener = listener
    }
}
*/
