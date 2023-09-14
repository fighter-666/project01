package com.example.myapplication.recharge.adapter

import android.app.Activity
import android.content.Intent
import android.provider.ContactsContract
import android.view.View
import androidx.activity.ComponentActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.GranularRoundedCorners
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.bumptech.glide.request.RequestOptions
import com.chad.library.adapter.base.BaseMultiItemQuickAdapter
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import com.example.myapplication.R
import com.example.myapplication.data.DataBean
import com.example.myapplication.databinding.ActivityBannerBinding
import com.example.myapplication.databinding.WidgetMultipleItemAdvertiseBinding
import com.example.myapplication.databinding.WidgetMultipleItemCommonBinding
import com.example.myapplication.databinding.WidgetMultipleItemManyImageBinding
import com.example.myapplication.databinding.WidgetMultipleItemNullBinding
import com.example.myapplication.databinding.WidgetMultipleItemRechargeBinding
import com.example.myapplication.recharge.data.GetFeedListData
import com.example.myapplication.recharge.widget.ScrrollTextViewCommentListBackground
import com.youth.banner.adapter.BannerImageAdapter
import com.youth.banner.holder.BannerImageHolder
import com.youth.banner.indicator.CircleIndicator
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
            R.layout.widget_multiple_item_null
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
            R.layout.widget_multiple_item_advertise
        )
        addItemType(
            GetFeedListData.FEED_LIST_ITEM_TYPE.RECHARGE.toInt(),
            R.layout.widget_multiple_item_recharge
        )
        addItemType(
            GetFeedListData.FEED_LIST_ITEM_TYPE.BANNER.toInt(),
            R.layout.activity_banner
        )
    }

    override fun convert(holder: BaseViewHolder, item: GetFeedListData.FeedListBean) {
        when (holder.itemViewType) {
            GetFeedListData.FEED_ADAPTER_ITEM_TYPE.MANY_IMAGE -> {
                // 处理多图布局
                val binding = WidgetMultipleItemManyImageBinding.bind(holder.itemView)

                //contentAreaList : 内容区域
                if (item.contentAreaList != null) {
                    val rechargeAdapter = ContentAreaListAdapter(
                        R.layout.adapter_recharge_content_area_list,
                        item.contentAreaList
                    )

                    //设置布局管理器和给recyclerView 设置设配器
                    binding.rvContentAreaList.apply {
                        layoutManager = LinearLayoutManager(context)
                        adapter = rechargeAdapter
                    }
                }

                val myAdapter = RechargeManyImageGridAdapter(
                    R.layout.adapter_recharge_many_image_grid,
                    item.picArea.picList
                )

                //设置布局管理器和给recyclerView设置适配器
                binding.rvPicAreaImageUrl.apply {
                    layoutManager = GridLayoutManager(context, 2)
                    adapter = myAdapter
                }

                binding.tvMainTitleTitle.text = item.picArea.title
                binding.tvMainTitleTitle.visibility = View.VISIBLE


            }

            GetFeedListData.FEED_ADAPTER_ITEM_TYPE.ONE_IMAGE -> {
                // 处理单图布局
                val binding = WidgetMultipleItemCommonBinding.bind(holder.itemView)



                //commentList : 评论列表
                if (item.picArea.commentList != null) {
                    val strs: MutableList<String> = mutableListOf() // 创建空的可变列表

                    for (tab in item.picArea.commentList) {
                        strs.add(tab.title) // 将每个标题添加到列表中
                    }

                    binding.tvCommentList.visibility = View.VISIBLE
                    val marqueeText2: ScrrollTextViewCommentListBackground = binding.tvCommentList
                    marqueeText2.setList(strs) // 将列表传递给跑马灯控件的setList方法
                    marqueeText2.startScroll()
                }

                //库存显示
                if (item.picArea.stock != null) {
                    binding.tvStockout.visibility = View.VISIBLE
                }
                //contentAreaList : 内容区域
                if (item.contentAreaList != null) {
                    val rechargeAdapter = ContentAreaListAdapter(
                        R.layout.adapter_recharge_content_area_list,
                        item.contentAreaList
                    )

                    //设置布局管理器和给recyclerView 设置设配器
                    binding.rvContentAreaList.apply {
                        layoutManager = LinearLayoutManager(context)
                        adapter = rechargeAdapter
                    }

                    //在协程中加载网络图片或在后台线程中加载大量图片。
                    // 确保在使用 Glide 加载图片时选择正确的 Dispatchers，以避免阻塞主线程
                    CoroutineScope(Dispatchers.Main).launch {
                        // 设置圆角半径
                        val requestOptions = RequestOptions().transform(RoundedCorners(20))
                        Glide.with(context)
                            .load(item.picArea.imageUrl)//使用 load() 方法传入 URL 字符串 imageUrl 来指定要加载的图片资源
                            //使用 transition() 方法可以设置过渡效果，例如交叉淡入淡出效果
                            .transition(DrawableTransitionOptions.withCrossFade())
                            .transform( GranularRoundedCorners(20f,20f,0f,0f))//四个角单独指定角度
                            //.apply(requestOptions)
                            .into(binding.ivPicAreaImageUrl)
                    }
                } else{
                    //在协程中加载网络图片或在后台线程中加载大量图片。
                    // 确保在使用 Glide 加载图片时选择正确的 Dispatchers，以避免阻塞主线程
                    CoroutineScope(Dispatchers.Main).launch {
                        // 设置圆角半径
                        val requestOptions = RequestOptions().transform(RoundedCorners(20))
                        Glide.with(context)
                            .load(item.picArea.imageUrl)//使用 load() 方法传入 URL 字符串 imageUrl 来指定要加载的图片资源
                            //使用 transition() 方法可以设置过渡效果，例如交叉淡入淡出效果
                            //.transform( GranularRoundedCorners(20f,20f,0f,0f))//四个角单独指定角度
                            //.transition(DrawableTransitionOptions.withCrossFade())
                            .apply(requestOptions)
                            .into(binding.ivPicAreaImageUrl)
                    }
                }
            }

            GetFeedListData.FEED_ADAPTER_ITEM_TYPE.NULL -> {
                // 处理空布局
                val binding = WidgetMultipleItemNullBinding.bind(holder.itemView)

                //contentAreaList : 内容区域
                if (item.contentAreaList != null) {
                    val rechargeAdapter = ContentAreaListAdapter(
                        R.layout.adapter_recharge_content_area_list,
                        item.contentAreaList
                    )

                    //设置布局管理器和给recyclerView 设置设配器
                    binding.rvContentAreaList.apply {
                        layoutManager = LinearLayoutManager(context)
                        adapter = rechargeAdapter
                    }
                }
            }

            GetFeedListData.FEED_LIST_ITEM_TYPE.LIVE.toInt() -> {
                // 处理直播布局
            }

            GetFeedListData.FEED_LIST_ITEM_TYPE.VIDEO.toInt() -> {
                // 处理视频布局
            }

            GetFeedListData.FEED_LIST_ITEM_TYPE.ADVERTISE.toInt() -> {
                // 处理广告布局
                val binding = WidgetMultipleItemAdvertiseBinding.bind(holder.itemView)
                val rechargeAdapter = AdvertiseAdapter(R.layout.adapter_advertise, item.adLists)

                //设置布局管理器和给recyclerView 设置设配器
                binding.rvAdList.apply {
                    layoutManager = LinearLayoutManager(context)
                    adapter = rechargeAdapter
                }
            }

            GetFeedListData.FEED_LIST_ITEM_TYPE.RECHARGE.toInt() -> {
                // 处理充值布局
                val binding = WidgetMultipleItemRechargeBinding.bind(holder.itemView)
                binding.btnSelect.setOnClickListener {
                    val intent = Intent(Intent.ACTION_PICK, ContactsContract.Contacts.CONTENT_URI)
                    (context as ComponentActivity).startActivityForResult(intent, 1)
                }

                binding.etPhone.text = item.quickRecharge.title

                val rechargeAdapter =
                    RechargeAdapter(R.layout.adapter_recharge, item.quickRecharge.denominations)

                //设置布局管理器和给recyclerView 设置设配器
                binding.rlRecharge.apply {
                    layoutManager = LinearLayoutManager(context)
                    adapter = rechargeAdapter
                }
            }

            GetFeedListData.FEED_LIST_ITEM_TYPE.BANNER.toInt() -> {
                // 处理充值布局
                val binding = ActivityBannerBinding.bind(holder.itemView)
                binding.banner.setAdapter(object :
                    BannerImageAdapter<DataBean>(DataBean.testData3) {
                    override fun onBindView(
                        holder: BannerImageHolder,
                        data: DataBean,
                        position: Int,
                        size: Int,
                    ) {
                        Glide.with(holder.imageView)
                            .load(data.imageUrl)
                            .into(holder.imageView)
                    }
                })
                binding.banner.setBannerRound2(20f)
                binding.banner.setLoopTime(5000)
                binding.banner.setIndicator(CircleIndicator(context)) // 设置指示器为圆圈样式
            }

        }
    }


    // 在片段（Fragment）中重写onActivityResult方法
    fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (requestCode == 1 && resultCode == Activity.RESULT_OK) {
            // 处理通讯录选择结果
            // ...
            // 刷新适配器
            notifyItemChanged(2)
        }
    }
}


