package com.example.myapplication.recharge.adapter

import android.os.CountDownTimer
import android.util.SparseArray
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.chad.library.adapter.base.BaseMultiItemQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.example.myapplication.R
import com.example.myapplication.recharge.data.GetFeedListData
import com.example.myapplication.recharge.util.UtilOther
import com.example.myapplication.recharge.widget.FeedCommentDataMF
import com.example.myapplication.util.DensityUtils
import com.example.myapplication.util.GetScreenUtils
import com.gongwen.marqueen.MarqueeFactory
import com.gongwen.marqueen.MarqueeView
import com.youth.banner.Banner


/**
 * 瀑布流适配器
 */
class FeedAdapter(mIsCare: Boolean) : BaseMultiItemQuickAdapter<GetFeedListData.FeedListBean, FeedAdapter.TimeViewHolder>(null) {
    var playPosition = -1
    var phoneNum = ""
    var mIsCare = false // 是否关爱版
    private var countDownMap = SparseArray<CountDownTimer>()
    var mFeedScene = ""
    private val bannerRatio = "157:212" // 轮播图比例

    init {
        this.mIsCare = mIsCare

        if (this.mIsCare) {
            addItemType(
                    GetFeedListData.FEED_ADAPTER_ITEM_TYPE.ONE_IMAGE,
                    R.layout.item_feed_one_image
                )
                addItemType(
                    GetFeedListData.FEED_ADAPTER_ITEM_TYPE.MANY_IMAGE,
                    R.layout.item_feed_one_image
                )
                addItemType(
                    GetFeedListData.FEED_ADAPTER_ITEM_TYPE.LIVE,
                    R.layout.item_feed_one_image
                )
                addItemType(
                    GetFeedListData.FEED_ADAPTER_ITEM_TYPE.VIDEO,
                    R.layout.item_feed_one_image
                )
                addItemType(
                    GetFeedListData.FEED_ADAPTER_ITEM_TYPE.ADVERTISE,
                    R.layout.widget_multiple_item_common
                )
                addItemType(
                    GetFeedListData.FEED_ADAPTER_ITEM_TYPE.RECHARGE,
                    R.layout.item_feed_one_image
                )
                addItemType(
                    GetFeedListData.FEED_ADAPTER_ITEM_TYPE.NULL,
                    R.layout.item_feed_one_image
                )
        } else {
                addItemType(
                    GetFeedListData.FEED_ADAPTER_ITEM_TYPE.ONE_IMAGE,
                    R.layout.item_feed_one_image
                )
                addItemType(
                    GetFeedListData.FEED_ADAPTER_ITEM_TYPE.MANY_IMAGE,
                    R.layout.item_feed_many_image
                )
                addItemType(
                    GetFeedListData.FEED_ADAPTER_ITEM_TYPE.LIVE,
                    R.layout.item_feed_one_image
                )
                addItemType(
                    GetFeedListData.FEED_ADAPTER_ITEM_TYPE.VIDEO,
                    R.layout.item_feed_one_image
                )
                addItemType(
                    GetFeedListData.FEED_ADAPTER_ITEM_TYPE.ADVERTISE,
                    R.layout.activity_banner
                )
                addItemType(
                    GetFeedListData.FEED_ADAPTER_ITEM_TYPE.RECHARGE,
                    R.layout.widget_multiple_item_recharge
                )
                addItemType(
                    GetFeedListData.FEED_ADAPTER_ITEM_TYPE.NULL,
                    R.layout.widget_multiple_item_null
                )
        }
    }

    override fun convert(helper: TimeViewHolder, item: GetFeedListData.FeedListBean) {
        helper.run {
            when (itemViewType) {
                // 轮播图
                GetFeedListData.FEED_ADAPTER_ITEM_TYPE.ADVERTISE -> {
                    //val rvContent = this.getView<>(R.id.banner)
                    //isNestedScrollingEnabled = false
                   /* val banner = getView<AdvertisingBanner>(R.id.advertisingBanner).setOnItemListenerCallBack {
                        HgSy.hitFeedFocus(item.adLists[it], it)
                    }
                    banner.setData(item.adLists).setFocusPercent(bannerRatio)
                    banner.setOnPageChangeListener {
                        HgSyExpose.exposeFeedFocus(item.adLists[it], it)
                    }*/
                }
                // 充值
                GetFeedListData.FEED_ADAPTER_ITEM_TYPE.RECHARGE -> {
                   /* val feedChargeView = getView<FeedChargeView>(R.id.feedChargeView)
                    feedChargeView.setData(item.quickRecharge)
                    if (!UtilText.isEmptyOrNull(phoneNum)){
                        feedChargeView.setNbr(phoneNum)
                    }
                    addOnClickListener(R.id.ivContact)

                    feedChargeView.setOnVisibilityChange { view, isVisible ->
                        HgSyExpose.exposeQuickCharge()

                        for (i in item.quickRecharge.denominations.indices){
                            item.quickRecharge.denominations[i].run {
                                HgSyExpose.exposeQuickChargeItem("$mainTitle$subtitle", i)
                            }
                        }
                    }*/
                }
                else -> {
                    // 主图区域
                    when (itemViewType) {
                        // 多图
                        GetFeedListData.FEED_ADAPTER_ITEM_TYPE.MANY_IMAGE -> {
                            val rvImg = getView<RecyclerView>(R.id.rvImg)
                            val ivTopImage = getView<ImageView>(R.id.ivTopImage)
                            item.picArea?.run {
                                if (topImage != null) {
                                    Glide.with(mContext)
                                        .load(topImage)//使用 load() 方法传入 URL 字符串 imageUrl 来指定要加载的图片资源
                                        //使用 transition() 方法可以设置过渡效果，例如交叉淡入淡出效果
                                        .transition(DrawableTransitionOptions.withCrossFade())
                                        //.transform(GranularRoundedCorners(20f, 20f, 0f, 0f))//四个角单独指定角度
                                        //.apply(requestOptions
                                        .error(R.drawable.ic_launcher_foreground)
                                        .into(ivTopImage)
                                    ivTopImage.visibility = View.VISIBLE
                                    ivTopImage.contentDescription = title
                                } else {
                                    ivTopImage.visibility = View.GONE
                                }
                                rvImg.run {
                                    layoutManager = GridLayoutManager(mContext, 2)
                                    isNestedScrollingEnabled = false
                                    // 只展示2或4个
                                    if (!picList.isNullOrEmpty()) {
                                        if (picList.size >= 4) {
                                            picList = picList.subList(0, 4)
                                        } else if (picList.size >= 2) {
                                            picList = picList.subList(0, 2)
                                        }
                                        val mFeedManyImgAdapter =
                                            FeedManyImgAdapter(
                                                R.layout.item_feed_many_img_son,
                                                picList
                                            )
                                        mFeedManyImgAdapter.pItem = item
                                        mFeedManyImgAdapter.pPos = layoutPosition
                                        adapter = mFeedManyImgAdapter
                                    }
                                }
                            }
                        }
                        // 视频
                        GetFeedListData.FEED_ADAPTER_ITEM_TYPE.VIDEO ->{}
                        // 直播
                        GetFeedListData.FEED_ADAPTER_ITEM_TYPE.LIVE -> {}
                        // 单图
                        else -> {
                            val ivImg = getView<ImageView>(R.id.ivImg)
                            val ivTopImage = getView<ImageView>(R.id.ivTopImage)
                            item.picArea?.run {
                                val lp1 = ivImg.layoutParams
                                var mImageRatio = UtilOther.parseFloat(imageRatio)
                                if (mImageRatio == 0f) {
                                    mImageRatio = 1f
                                }
                                lp1.width = (GetScreenUtils.getScreenWidth(mContext) - DensityUtils.dpToPx(
                                    mContext,
                                    13 + 13 + 10f
                                ))
                                lp1.height = ((lp1.width / 2) / mImageRatio).toInt()
                                ivImg.layoutParams = lp1
                                ivImg.contentDescription = title
                                Glide.with(mContext)
                                    .load(imageUrl)//使用 load() 方法传入 URL 字符串 imageUrl 来指定要加载的图片资源
                                    //使用 transition() 方法可以设置过渡效果，例如交叉淡入淡出效果
                                    .transition(DrawableTransitionOptions.withCrossFade())
                                    //.transform(GranularRoundedCorners(20f, 20f, 0f, 0f))//四个角单独指定角度
                                    //.apply(requestOptions
                                    .error(R.drawable.ic_launcher_foreground)
                                    .into(ivImg)
                                if (topImage!=null) {
                                    Glide.with(mContext)
                                        .load(topImage)//使用 load() 方法传入 URL 字符串 imageUrl 来指定要加载的图片资源
                                        //使用 transition() 方法可以设置过渡效果，例如交叉淡入淡出效果
                                        .transition(DrawableTransitionOptions.withCrossFade())
                                        //.transform(GranularRoundedCorners(20f, 20f, 0f, 0f))//四个角单独指定角度
                                        //.apply(requestOptions
                                        .error(R.drawable.ic_launcher_foreground)
                                        .into(ivTopImage)
                                    ivTopImage.visibility = View.VISIBLE
                                    ivTopImage.contentDescription = title
                                } else {
                                    ivTopImage.visibility = View.GONE
                                }

                                addOnClickListener(R.id.ivImg)
                            }
                        }
                    }

                    item.picArea?.run {
                        val tvStock = getView<TextView>(R.id.tvStock)
                        val marqueeView = getView<MarqueeView<View, Any>>(R.id.marqueeView)
                        // 库存
                        if (stock!=null) {
                            tvStock.visibility = View.VISIBLE
                            tvStock.text = stock
                        } else {
                            tvStock.visibility = View.GONE
                        }

                        // 评论区
                        val mf = FeedCommentDataMF(mContext, mIsCare)
                        mf.data = commentList
                        marqueeView.setMarqueeFactory(mf as MarqueeFactory<View, Any>)
                        if (!commentList.isNullOrEmpty()) {
                            marqueeView.visibility = View.VISIBLE
                            if (commentList.size > 1) {
                                marqueeView.startFlipping()
                            } else {
                                marqueeView.stopFlipping()
                            }
                        } else {
                            marqueeView.stopFlipping()
                            marqueeView.visibility = View.GONE
                        }
                        addOnClickListener(R.id.llBottom)
                    }
                    // 内容区域
                    if (item.contentAreaList != null) {
                        val rvContent = getView<RecyclerView>(R.id.rvContent)
                        rvContent.run {
                            layoutManager = LinearLayoutManager(mContext)
                            isNestedScrollingEnabled = false
                            adapter=ContentAreaListAdapter(
                                R.layout.adapter_recharge_content_area_list, item.contentAreaList
                            )

                            /*  val mFeedContentAdapter = FeedContentAdapter(mIsCare)
                              mFeedContentAdapter.setNewData(item.contentAreaList)
                              mFeedContentAdapter.pItem = item
                              mFeedContentAdapter.pData = data
                              mFeedContentAdapter.pPos = layoutPosition
                              mFeedContentAdapter.pAdapter = this@FeedAdapter
                              adapter = mFeedContentAdapter*/

                        }
                    }

                }
            }
        }
    }

    /**
     * 清空资源
     */
    fun cancelAllTimers() {
        var i = 0
        val length = countDownMap.size()
        while (i < length) {
            val cdt = countDownMap[countDownMap.keyAt(i)]
            cdt.cancel()
            i++
        }
    }

    class TimeViewHolder(view: View?) : BaseViewHolder(view) {
        var countDownTimer: CountDownTimer? = null
    }


}






