package com.example.myapplication.recharge.adapter

import android.view.View
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
import com.example.myapplication.databinding.ActivityBannerBinding
import com.example.myapplication.databinding.WidgetMultipleItemCommonBinding
import com.example.myapplication.databinding.WidgetMultipleItemManyImageBinding
import com.example.myapplication.databinding.WidgetMultipleItemNullBinding
import com.example.myapplication.databinding.WidgetMultipleItemRechargeBinding
import com.example.myapplication.recharge.data.GetFeedListData
import com.example.myapplication.recharge.widget.ScrollTextViewCommentListBackground
import com.youth.banner.adapter.BannerImageAdapter
import com.youth.banner.holder.BannerImageHolder
import com.youth.banner.indicator.CircleIndicator
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class WaterfallAdapter(data: MutableList<GetFeedListData.FeedListBean>) :
    BaseMultiItemQuickAdapter<GetFeedListData.FeedListBean, BaseViewHolder>(data) {

    //一个可为空的函数类型变量，用于保存点击事件的监听器
    private var onItemClickListener: ((GetFeedListData.FeedListBean) -> Unit)? = null
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
            R.layout.activity_banner
        )
        addItemType(
            GetFeedListData.FEED_LIST_ITEM_TYPE.RECHARGE.toInt(),
            R.layout.widget_multiple_item_recharge
        )
        /*addItemType(
            GetFeedListData.FEED_LIST_ITEM_TYPE.BANNER.toInt(),
            R.layout.activity_banner
        )*/
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

                val myAdapter = ManyImageGridAdapter(
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
                    val stars: MutableList<String> = mutableListOf() // 创建空的可变列表

                    for (tab in item.picArea.commentList) {
                        stars.add(tab.title) // 将每个标题添加到列表中
                    }

                    binding.tvCommentList.visibility = View.VISIBLE
                    val marqueeText2: ScrollTextViewCommentListBackground = binding.tvCommentList
                    marqueeText2.setList(stars) // 将列表传递给跑马灯控件的setList方法
                    marqueeText2.startScroll()
                }

                //库存显示
                if (item.picArea.stock != null) {
                    binding.tvStockout.visibility = View.VISIBLE
                }
                // imageWeight = recyclerView.measuredWidth
                if (item.picArea.imageRatio == null) {
                    item.picArea.imageRatio = 1.0f.toString()
                }
                //val imageRatio = item.picArea.imageRatio.toFloat()
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
                        //val requestOptions = RequestOptions().transform(RoundedCorners(20))
                        Glide.with(context)
                            .load(item.picArea.imageUrl)//使用 load() 方法传入 URL 字符串 imageUrl 来指定要加载的图片资源
                            //使用 transition() 方法可以设置过渡效果，例如交叉淡入淡出效果
                            .transition(DrawableTransitionOptions.withCrossFade())
                            .transform(GranularRoundedCorners(20f, 20f, 0f, 0f))//四个角单独指定角度
                            //.apply(requestOptions)
                            .into(binding.ivPicAreaImageUrl)

                    }
                } else {
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
                val binding = ActivityBannerBinding.bind(holder.itemView)

                binding.banner.setAdapter(object :
                    BannerImageAdapter<GetFeedListData.FeedListBean.AdListBean>(item.adLists) {
                    override fun onBindView(
                        holder: BannerImageHolder,
                        data: GetFeedListData.FeedListBean.AdListBean,
                        position: Int,
                        size: Int,
                    ) {
                        CoroutineScope(Dispatchers.Main).launch {
                            Glide.with(holder.imageView)
                                .load(data.imageUrl)
                                .error(R.drawable.ic_launcher_foreground)
                                .into(holder.imageView)
                        }
                    }
                })
                //设置圆角
                binding.banner.setBannerRound2(20f)
                //设置轮播时间间隔
                binding.banner.setLoopTime(5000)
                binding.banner.indicator = CircleIndicator(context) // 设置指示器为圆圈样式
                binding.banner.setIndicatorWidth(15, 15)

            }

            GetFeedListData.FEED_LIST_ITEM_TYPE.RECHARGE.toInt() -> {
                // 处理充值布局
                val binding = WidgetMultipleItemRechargeBinding.bind(holder.itemView)
                binding.btnSelect.setOnClickListener {
                    //设置点击事件监听器
                        onItemClickListener?.invoke(item)
                   /* if (ContextCompat.checkSelfPermission(
                            context, Manifest.permission.READ_CONTACTS
                        ) != PackageManager.PERMISSION_GRANTED
                    ) {
                        // 如果没有权限，则向用户请求权限
                        ActivityCompat.requestPermissions(
                            context as Activity, arrayOf(Manifest.permission.READ_CONTACTS), 2
                        )

                    } else {
                        // 如果已经拥有权限，则执行读取联系人数据的操作
                        //getContactNumberByUri(contactUri)
                    }
                    val intent = Intent(Intent.ACTION_PICK, ContactsContract.Contacts.CONTENT_URI)
                    (context as ComponentActivity).startActivityForResult(intent, 1)*/
                    //(context as ComponentActivity).pickContactLauncher.launch(intent)
                }

                //消除空格，并且第四位到第七位用*代替
                binding.etPhone.text =
                    hideCharactersFromIndex(item.quickRecharge.title.replace(" ", ""), 3)

                val rechargeAdapter =
                    RechargeAdapter(R.layout.adapter_recharge, item.quickRecharge.denominations)

                //设置布局管理器和给recyclerView 设置设配器
                binding.rlRecharge.apply {
                    layoutManager = LinearLayoutManager(context)
                    adapter = rechargeAdapter
                }
            }

            /*GetFeedListData.FEED_LIST_ITEM_TYPE.BANNER.toInt() -> {
                *//*val binding = WidgetMultipleItemAdvertiseBinding.bind(holder.itemView)
                val rechargeAdapter = AdvertiseAdapter(R.layout.adapter_advertise, item.adLists)

                //设置布局管理器和给recyclerView 设置设配器
                binding.rvAdList.apply {
                    layoutManager = LinearLayoutManager(context)
                    adapter = rechargeAdapter
                }*//*
            }*/

        }
    }

    //它接受一个函数类型的参数 listener，该函数类型接受一个 Piggy 对象作为参数，并不返回任何结果
    fun setOnItemClickListener(listener: (GetFeedListData.FeedListBean) -> Unit) {
        //onItemClickListener 被赋值为传入的 listener，
        // 从而将外部传入的点击事件监听器与适配器关联起来
        onItemClickListener = listener
    }

    //第四位到第七位用*代替
    private fun hideCharactersFromIndex(text: String, startIndex: Int): String {
        val length = text.length
        if (startIndex >= length) {
            return text
        }

        val hiddenText = StringBuilder(text)
        for (i in startIndex until startIndex + 4) {
            hiddenText.setCharAt(i, '*')
        }

        return hiddenText.toString()
    }
}


