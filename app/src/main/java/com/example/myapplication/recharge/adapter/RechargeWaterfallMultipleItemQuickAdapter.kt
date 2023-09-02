package com.example.myapplication.recharge.adapter

import android.view.View
import androidx.core.view.isGone
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.bumptech.glide.request.RequestOptions
import com.chad.library.adapter.base.BaseMultiItemQuickAdapter
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import com.example.myapplication.R
import com.example.myapplication.databinding.WidgetMultipleItemCommonBinding
import com.example.myapplication.databinding.WidgetMultipleItemManyImageBinding
import com.example.myapplication.databinding.WidgetMultipleItemRechargeBinding
import com.example.myapplication.recharge.data.GetFeedListData
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class RechargeWaterfallMultipleItemQuickAdapter(data: MutableList<GetFeedListData.FeedListBean>) :
    BaseMultiItemQuickAdapter<GetFeedListData.FeedListBean, BaseViewHolder>(data) {

    init {
        addItemType(
            GetFeedListData.FEED_ADAPTER_ITEM_TYPE.MANY_IMAGE,
            R.layout.widget_multiple_item_many_image
        )
        addItemType(
            GetFeedListData.FEED_ADAPTER_ITEM_TYPE.ONE_IMAGE,
            R.layout.widget_multiple_item_common
        )
        addItemType(
            GetFeedListData.FEED_ADAPTER_ITEM_TYPE.NULL,
            R.layout.widget_multiple_item_common
        )
        addItemType(
            GetFeedListData.FEED_LIST_ITEM_TYPE.LIVE.toInt(),
            R.layout.widget_multiple_item_common
        )
        addItemType(
            GetFeedListData.FEED_LIST_ITEM_TYPE.VIDEO.toInt(),
            R.layout.widget_multiple_item_common
        )
        addItemType(
            GetFeedListData.FEED_LIST_ITEM_TYPE.ADVERTISE.toInt(),
            R.layout.widget_multiple_item_common
        )
        addItemType(
            GetFeedListData.FEED_LIST_ITEM_TYPE.RECHARGE.toInt(),
            R.layout.widget_multiple_item_recharge
        )
    }

    override fun convert(holder: BaseViewHolder, item: GetFeedListData.FeedListBean) {
        when (holder.itemViewType) {
            GetFeedListData.FEED_ADAPTER_ITEM_TYPE.MANY_IMAGE -> {
                // 处理多图布局
                val binding = WidgetMultipleItemManyImageBinding.bind(holder.itemView)
                //在协程中加载网络图片或在后台线程中加载大量图片。
                // 确保在使用 Glide 加载图片时选择正确的 Dispatchers，以避免阻塞主线程
                CoroutineScope(Dispatchers.Main).launch {
                    // 设置圆角半径
                    val requestOptions = RequestOptions().transform(RoundedCorners(20))
                    Glide.with(context)
                        .load(item.picArea.imageUrl)//使用 load() 方法传入 URL 字符串 imageUrl 来指定要加载的图片资源
                        //使用 transition() 方法可以设置过渡效果，例如交叉淡入淡出效果
                        .transition(DrawableTransitionOptions.withCrossFade())
                        .apply(requestOptions)
                        .into(binding.ivPicAreaImageUrl)
                    Glide.with(context)
                        .load(item.picArea.imageUrl)//使用 load() 方法传入 URL 字符串 imageUrl 来指定要加载的图片资源
                        //使用 transition() 方法可以设置过渡效果，例如交叉淡入淡出效果
                        .transition(DrawableTransitionOptions.withCrossFade())
                        .apply(requestOptions)
                        .into(binding.ivPicAreaImageUrl2)
                    Glide.with(context)
                        .load(item.picArea.imageUrl)//使用 load() 方法传入 URL 字符串 imageUrl 来指定要加载的图片资源
                        //使用 transition() 方法可以设置过渡效果，例如交叉淡入淡出效果
                        .transition(DrawableTransitionOptions.withCrossFade())
                        .apply(requestOptions)
                        .into(binding.ivPicAreaImageUrl3)
                    Glide.with(context)
                        .load(item.picArea.imageUrl)//使用 load() 方法传入 URL 字符串 imageUrl 来指定要加载的图片资源
                        //使用 transition() 方法可以设置过渡效果，例如交叉淡入淡出效果
                        .transition(DrawableTransitionOptions.withCrossFade())
                        .apply(requestOptions)
                        .into(binding.ivPicAreaImageUrl4)
                }

            }

            GetFeedListData.FEED_ADAPTER_ITEM_TYPE.ONE_IMAGE -> {
                // 处理单图布局
                val binding = WidgetMultipleItemCommonBinding.bind(holder.itemView)
                //在协程中加载网络图片或在后台线程中加载大量图片。
                // 确保在使用 Glide 加载图片时选择正确的 Dispatchers，以避免阻塞主线程
                CoroutineScope(Dispatchers.Main).launch {
                    // 设置圆角半径
                    val requestOptions = RequestOptions().transform(RoundedCorners(20))
                    Glide.with(context)
                        .load(item.picArea.imageUrl)//使用 load() 方法传入 URL 字符串 imageUrl 来指定要加载的图片资源
                        //使用 transition() 方法可以设置过渡效果，例如交叉淡入淡出效果
                        .transition(DrawableTransitionOptions.withCrossFade())
                        .apply(requestOptions)
                        .into(binding.ivPicAreaImageUrl)
                }

                //commentList : 评论列表
                if (item.picArea.commentList != null) {
                    /*//右边textview跑马灯
                    val marqueeText2: ScrrollTextViewBackground = binding.tvScrollBackground
                    val demographicsList2: MutableList<String> = ArrayList()
                    demographicsList2.add("股票")
                    demographicsList2.add("药业")
                    demographicsList2.add("上市")
                    marqueeText2.setList(demographicsList2)
                    marqueeText2.startScroll()*/

                    binding.tvCommentList.text = item.picArea.commentList[0].title
                    if (item.picArea.commentList.size > 2) {
                        binding.tvCommentListSecond.text =
                            item.picArea.commentList[1].title
                    } else {
                        binding.tvCommentListSecond.isGone = true
                    }
                } else {
                    binding.tvCommentList.isGone = true
                    binding.tvCommentListSecond.isGone = true
                }

                //库存显示
                if (item.picArea.stock != null) {
                    binding.tvStockout.isGone = true
                }
                //contentAreaList : 内容区域
                if (item.contentAreaList != null) {
                    //遍历item.contentAreaList中的元素
                    for (tab in item.contentAreaList) {
                        //type : 类型：1：主文案、2：随销条、3：价格、4：位置、5：倒计时、6：人数、7：配图：一行一个、8：配图2：一行两个、9：末尾卡片按钮列表 string
                        when (tab.type) {
                            "1" -> {
                                //mainTitle : 主标题
                                if (tab.mainTitle != null) {
                                    if (tab.mainTitle.title != "") {
                                        binding.tvMainTitleTitle.text = tab.mainTitle.title
                                        binding.tvMainTitleTitle.visibility = View.VISIBLE
                                    }
                                }
                            }

                            //2：随销条、
                            "2" -> {
                                //saleTipList : 随销条
                                if (tab.saleTipList != null) {
                                    binding.tvSaleTipList.text = tab.saleTipList[0].title
                                    binding.tvSaleTipListSecond.text = tab.saleTipList[1].title
                                    binding.horizontalScrollView.visibility = View.VISIBLE
                                    binding.tvSaleTipList.visibility = View.VISIBLE
                                    binding.tvSaleTipListSecond.visibility = View.VISIBLE
                                    //判断列表中的元素数量
                                    if (tab.saleTipList.size > 2) {
                                        binding.tvSaleTipListThird.text =
                                            tab.saleTipList[2].title
                                        binding.tvSaleTipListThird.visibility = View.VISIBLE
                                    }

                                }

                            }

                            //3：价格
                            "3" -> {
                                //price : 价格
                                if (tab.price != null) {
                                    binding.tvPriceInteger.text = tab.price.priceInteger
                                    binding.tvPriceDecimal.text = tab.price.priceDecimal
                                    binding.tvOriginalPrice.text = tab.price.originalPrice

                                    binding.tvPriceInteger.visibility = View.VISIBLE
                                    binding.tvPriceDecimal.visibility = View.VISIBLE
                                    binding.tvOriginalPrice.visibility = View.VISIBLE
                                    binding.tvDollar.visibility = View.VISIBLE
                                }
                            }

                            "4" -> {

                            }

                            "5" -> {

                            }

                            "6" -> {

                            }

                            //7：配图：一行一个
                            "7" -> {
                                //7：配图：一行一个
                                if (tab.picList != null) {
                                    binding.ivContentAreaListPicListFirst.visibility = View.VISIBLE
                                    CoroutineScope(Dispatchers.Main).launch {
                                        // 设置圆角半径
                                        val requestOptions =
                                            RequestOptions().transform(RoundedCorners(20))
                                        Glide.with(context)
                                            .load(item.contentAreaList[0].picList[0].imageUrl)//使用 load() 方法传入 URL 字符串 imageUrl 来指定要加载的图片资源
                                            //使用 transition() 方法可以设置过渡效果，例如交叉淡入淡出效果
                                            .transition(DrawableTransitionOptions.withCrossFade())
                                            .apply(requestOptions)
                                            .into(binding.ivContentAreaListPicListFirst)
                                    }
                                    binding.ivContentAreaListPicListSecond.visibility = View.VISIBLE
                                    CoroutineScope(Dispatchers.Main).launch {
                                        // 设置圆角半径
                                        val requestOptions =
                                            RequestOptions().transform(RoundedCorners(20))
                                        Glide.with(context)
                                            .load(item.contentAreaList[1].picList[0].imageUrl)//使用 load() 方法传入 URL 字符串 imageUrl 来指定要加载的图片资源
                                            //使用 transition() 方法可以设置过渡效果，例如交叉淡入淡出效果
                                            .transition(DrawableTransitionOptions.withCrossFade())
                                            .apply(requestOptions)
                                            .into(binding.ivContentAreaListPicListSecond)
                                    }
                                    binding.ivContentAreaListPicListThird.visibility = View.VISIBLE
                                    CoroutineScope(Dispatchers.Main).launch {
                                        // 设置圆角半径
                                        val requestOptions =
                                            RequestOptions().transform(RoundedCorners(20))
                                        Glide.with(context)
                                            .load(item.contentAreaList[2].picList[0].imageUrl)//使用 load() 方法传入 URL 字符串 imageUrl 来指定要加载的图片资源
                                            //使用 transition() 方法可以设置过渡效果，例如交叉淡入淡出效果
                                            .transition(DrawableTransitionOptions.withCrossFade())
                                            .apply(requestOptions)
                                            .into(binding.ivContentAreaListPicListThird)
                                    }
                                    binding.ivContentAreaListPicListFourth.visibility = View.VISIBLE
                                    CoroutineScope(Dispatchers.Main).launch {
                                        // 设置圆角半径
                                        val requestOptions =
                                            RequestOptions().transform(RoundedCorners(20))
                                        Glide.with(context)
                                            .load(item.contentAreaList[3].picList[0].imageUrl)//使用 load() 方法传入 URL 字符串 imageUrl 来指定要加载的图片资源
                                            //使用 transition() 方法可以设置过渡效果，例如交叉淡入淡出效果
                                            .transition(DrawableTransitionOptions.withCrossFade())
                                            .apply(requestOptions)
                                            .into(binding.ivContentAreaListPicListFourth)
                                    }
                                    /*if (item.contentAreaList.size > 4) {
                                        binding.ivContentAreaListPicListFifth.visibility = View.VISIBLE
                                        CoroutineScope(Dispatchers.Main).launch {
                                            // 设置圆角半径
                                            val requestOptions = RequestOptions().transform(RoundedCorners(20))
                                            Glide.with(context)
                                                .load(item.contentAreaList[4].picList[0].imageUrl)//使用 load() 方法传入 URL 字符串 imageUrl 来指定要加载的图片资源
                                                //使用 transition() 方法可以设置过渡效果，例如交叉淡入淡出效果
                                                .transition(DrawableTransitionOptions.withCrossFade())
                                                .apply(requestOptions)
                                                .into(binding.ivContentAreaListPicListFifth)
                                        }
                                    }*/
                                }

                            }

                            //8：配图2：一行两个
                            "8" -> {
                                if (tab.picList != null) {
                                    binding.ivContentAreaListPicListDoubleIssueFee.visibility =
                                        View.VISIBLE
                                    CoroutineScope(Dispatchers.Main).launch {
                                        // 设置圆角半径
                                        val requestOptions =
                                            RequestOptions().transform(RoundedCorners(20))
                                        Glide.with(context)
                                            .load(tab.picList[0].imageUrl)//使用 load() 方法传入 URL 字符串 imageUrl 来指定要加载的图片资源
                                            //使用 transition() 方法可以设置过渡效果，例如交叉淡入淡出效果
                                            .transition(DrawableTransitionOptions.withCrossFade())
                                            .apply(requestOptions)
                                            .into(binding.ivContentAreaListPicListDoubleIssueFee)
                                    }
                                    binding.ivContentAreaListPicListDoubleDrawALottery.visibility =
                                        View.VISIBLE
                                    CoroutineScope(Dispatchers.Main).launch {
                                        // 设置圆角半径
                                        val requestOptions =
                                            RequestOptions().transform(RoundedCorners(20))
                                        Glide.with(context)
                                            .load(tab.picList[1].imageUrl)//使用 load() 方法传入 URL 字符串 imageUrl 来指定要加载的图片资源
                                            //使用 transition() 方法可以设置过渡效果，例如交叉淡入淡出效果
                                            .transition(DrawableTransitionOptions.withCrossFade())
                                            .apply(requestOptions)
                                            .into(binding.ivContentAreaListPicListDoubleDrawALottery)
                                    }
                                    /*binding.tvContentAreaListPicListDoubleIssueFee.visibility = View.VISIBLE
                                    binding.tvContentAreaListPicListDoubleDrawALottery.visibility = View.VISIBLE*/

                                    binding.tvContentAreaListPicListDoubleIssueFee.text =
                                        tab.picList[0].title
                                    binding.tvContentAreaListPicListDoubleDrawALottery.text =
                                        tab.picList[0].title
                                }

                            }

                            "9" -> {

                            }

                            else -> {

                            }
                        }
                    }
                }
            }

            GetFeedListData.FEED_ADAPTER_ITEM_TYPE.NULL -> {
                // 处理空布局
            }

            GetFeedListData.FEED_LIST_ITEM_TYPE.LIVE.toInt() -> {
                // 处理直播布局
            }

            GetFeedListData.FEED_LIST_ITEM_TYPE.VIDEO.toInt() -> {
                // 处理视频布局
            }

            GetFeedListData.FEED_LIST_ITEM_TYPE.ADVERTISE.toInt() -> {
                // 处理广告布局

            }

            GetFeedListData.FEED_LIST_ITEM_TYPE.RECHARGE.toInt() -> {
                // 处理充值布局
                val binding = WidgetMultipleItemRechargeBinding.bind(holder.itemView)
                binding.tvTitle.text = item.title
                //10元
                binding.tvMainTitleTenDollar.text = item.quickRecharge.denominations[0].mainTitle
                binding.tvSubtitleTenDollar.text = item.quickRecharge.denominations[0].subtitle
                //50元
                binding.tvMainTitleFiftyDollar.text = item.quickRecharge.denominations[1].mainTitle
                binding.tvSubtitleFiftyDollar.text = item.quickRecharge.denominations[1].subtitle

                //100元
                binding.tvMainTitleHundredDollar.text =
                    item.quickRecharge.denominations[2].mainTitle
                binding.tvSubtitleHundredDollar.text = item.quickRecharge.denominations[2].subtitle

                //200元
                binding.tvMainTitleTwoHundredDollar.text =
                    item.quickRecharge.denominations[3].mainTitle
                binding.tvSubtitleTwoHundredDollar.text =
                    item.quickRecharge.denominations[3].subtitle
            }
        }
    }
}