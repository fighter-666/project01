package com.example.myapplication.recharge.adapter

import android.util.Log
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.chad.library.adapter.base.BaseMultiItemQuickAdapter
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import com.example.myapplication.R
import com.example.myapplication.databinding.AdapterRechargeTabWaterfallBinding
import com.example.myapplication.databinding.WidgetMultipleItemCommonBinding
import com.example.myapplication.databinding.WidgetMultipleItemManyImageBinding
import com.example.myapplication.recharge.data.GetFeedListData

class RechargeWaterfallMultipleItemQuickAdapter(data: MutableList<GetFeedListData.FeedListBean>) :
    BaseMultiItemQuickAdapter<GetFeedListData.FeedListBean, BaseViewHolder>(data) {

    init {
        addItemType(GetFeedListData.FEED_ADAPTER_ITEM_TYPE.MANY_IMAGE, R.layout.widget_multiple_item_many_image)
        addItemType(GetFeedListData.FEED_ADAPTER_ITEM_TYPE.ONE_IMAGE, R.layout.widget_multiple_item_common)
        addItemType(GetFeedListData.FEED_ADAPTER_ITEM_TYPE.NULL, R.layout.widget_multiple_item_common)
        addItemType(GetFeedListData.FEED_LIST_ITEM_TYPE.LIVE.toInt(), R.layout.widget_multiple_item_common)
        addItemType(GetFeedListData.FEED_LIST_ITEM_TYPE.VIDEO.toInt(), R.layout.widget_multiple_item_common)
        addItemType(GetFeedListData.FEED_LIST_ITEM_TYPE.ADVERTISE.toInt(), R.layout.widget_multiple_item_common)
        addItemType(GetFeedListData.FEED_LIST_ITEM_TYPE.RECHARGE.toInt(), R.layout.widget_multiple_item_common)
    }

    override fun convert(holder: BaseViewHolder, item: GetFeedListData.FeedListBean) {
        when (holder.itemViewType) {
            GetFeedListData.FEED_ADAPTER_ITEM_TYPE.MANY_IMAGE -> {
                // 处理多图布局
                val binding = WidgetMultipleItemManyImageBinding.bind(holder.itemView)
                Glide.with(context)
                    .load(item.picArea.imageUrl)//使用 load() 方法传入 URL 字符串 imageUrl 来指定要加载的图片资源
                    //使用 transition() 方法可以设置过渡效果，例如交叉淡入淡出效果
                    .transition(DrawableTransitionOptions.withCrossFade())
                    .into(binding.ivPicAreaImageUrl)
            }
            GetFeedListData.FEED_ADAPTER_ITEM_TYPE.ONE_IMAGE -> {
                // 处理单图布局
                val binding = WidgetMultipleItemCommonBinding.bind(holder.itemView)
                Glide.with(context)
                    .load(item.picArea.imageUrl)//使用 load() 方法传入 URL 字符串 imageUrl 来指定要加载的图片资源
                    //使用 transition() 方法可以设置过渡效果，例如交叉淡入淡出效果
                    .transition(DrawableTransitionOptions.withCrossFade())
                    .into(binding.ivPicAreaImageUrl)
                if (item.contentAreaList != null){
                    for (tab in item.contentAreaList) {
                        if (tab.mainTitle != null){
                            binding.tvMainTitleTitle.text = tab.mainTitle.title
                        }
                        //随销条
                        if (tab.saleTipList != null){
                                binding.tvSaleTipList.text = tab.saleTipList[0].title
                                binding.tvSaleTipListCopy.text = tab.saleTipList[1].title
                        }
                        //价格
                        if (tab.price != null){
                            binding.tvPriceInteger.text = tab.price.priceInteger
                            binding.tvPriceDecimal.text = tab.price.priceDecimal
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
            }
        }
    }
}