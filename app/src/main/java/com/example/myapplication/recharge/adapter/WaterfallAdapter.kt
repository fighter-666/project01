package com.example.myapplication.recharge.adapter

import android.view.View
import android.view.ViewGroup
import android.widget.AdapterViewFlipper
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.blankj.utilcode.util.SnackbarUtils.getView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.chad.library.adapter.base.BaseMultiItemQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.example.myapplication.R
import com.example.myapplication.databinding.ActivityBannerBinding
import com.example.myapplication.databinding.WidgetMultipleItemCommonBinding
import com.example.myapplication.databinding.WidgetMultipleItemManyImageBinding
import com.example.myapplication.databinding.WidgetMultipleItemNullBinding
import com.example.myapplication.databinding.WidgetMultipleItemRechargeBinding
import com.example.myapplication.recharge.data.GetFeedListData
import com.example.myapplication.recharge.widget.GetTelephoneNumberManager
import com.example.myapplication.util.DensityUtils
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
            GetFeedListData.FEED_ADAPTER_ITEM_TYPE.ONE_IMAGE, R.layout.widget_multiple_item_common
        )
        addItemType(
            GetFeedListData.FEED_ADAPTER_ITEM_TYPE.NULL, R.layout.widget_multiple_item_null
        )
        addItemType(
            GetFeedListData.FEED_LIST_ITEM_TYPE.LIVE.toInt(), R.layout.widget_multiple_item_common
        )
        addItemType(
            GetFeedListData.FEED_LIST_ITEM_TYPE.VIDEO.toInt(), R.layout.widget_multiple_item_common
        )
        addItemType(
            GetFeedListData.FEED_LIST_ITEM_TYPE.ADVERTISE.toInt(), R.layout.activity_banner
        )
        addItemType(
            GetFeedListData.FEED_LIST_ITEM_TYPE.RECHARGE.toInt(),
            R.layout.widget_multiple_item_recharge
        )/*addItemType(
            GetFeedListData.FEED_LIST_ITEM_TYPE.BANNER.toInt(),
            R.layout.activity_banner
        )*/
    }

    override fun convert(holder: BaseViewHolder, item: GetFeedListData.FeedListBean) {
        //holder.addOnClickListener(R.id.btn_select)
        when (holder.itemViewType) {
            GetFeedListData.FEED_ADAPTER_ITEM_TYPE.MANY_IMAGE -> {
                // 处理多图布局
                val binding = WidgetMultipleItemManyImageBinding.bind(holder.itemView)

                //contentAreaList : 内容区域
                if (item.contentAreaList != null) {
                    val rechargeAdapter = ContentAreaListAdapter(
                        R.layout.adapter_recharge_content_area_list, item.contentAreaList
                    )

                    //设置布局管理器和给recyclerView 设置设配器
                    binding.rvContentAreaList.apply {
                        layoutManager = LinearLayoutManager(context)
                        adapter = rechargeAdapter
                        isNestedScrollingEnabled = false
                    }
                }

                val myAdapter = ManyImageGridAdapter(
                    R.layout.adapter_recharge_many_image_grid, item.picArea.picList
                )

                //设置布局管理器和给recyclerView设置适配器
                binding.rvPicAreaImageUrl.apply {
                    layoutManager = GridLayoutManager(context, 2)
                    adapter = myAdapter
                    isNestedScrollingEnabled = false
                }
            }

            GetFeedListData.FEED_ADAPTER_ITEM_TYPE.ONE_IMAGE -> {
                val ivTopImage = holder.getView<ImageView>(R.id.ivTopImage)
                val ivPicAreaImageUrl = holder.getView<ImageView>(R.id.ivPicAreaImageUrl)
                val tvCommentList = holder.getView<AdapterViewFlipper>(R.id.tvCommentList)
                val clCommentList = holder.getView<ConstraintLayout>(R.id.clCommentList)
                val clContentAreaList = holder.getView<ConstraintLayout>(R.id.clContentAreaList)
                val tvStockout = holder.getView<TextView>(R.id.tvStockout)
                val rvContentAreaList = holder.getView<RecyclerView>(R.id.rvContentAreaList)
                // 处理单图布局
                //val binding = WidgetMultipleItemCommonBinding.bind(holder.itemView)
                /* //卡片锁宽等比缩放（imageRatio用来计算高度）
                 val lp = binding.ivTopImage.layoutParams as ConstraintLayout.LayoutParams
                 lp.dimensionRatio = item.picArea.imageRatio // 例如，设置宽高比为16:9

                 binding.ivTopImage.layoutParams = lp*/
                //顶部标签
                if (item.picArea.topImage != null) {
                    ivTopImage.visibility = View.VISIBLE
                    Glide.with(mContext)
                        .load(item.picArea.topImage)//使用 load() 方法传入 URL 字符串 imageUrl 来指定要加载的图片资源
                        //使用 transition() 方法可以设置过渡效果，例如交叉淡入淡出效果
                        .transition(DrawableTransitionOptions.withCrossFade())
                        //.transform(GranularRoundedCorners(20f, 20f, 0f, 0f))//四个角单独指定角度
                        //.apply(requestOptions
                        .error(R.drawable.ic_launcher_foreground)
                        .into(ivTopImage)
                }

                //commentList : 评论列表
                if (item.picArea.commentList != null) {
                    val stars: MutableList<String> = mutableListOf() // 创建空的可变列表

                    for (tab in item.picArea.commentList) {
                        stars.add(tab.title) // 将每个标题添加到列表中
                        /*   val textView=
                               LayoutInflater.from(context)
                                   .inflate(R.layout.item_view, null) as TextView
                           textView.text = tab.title

                           binding.tvCommentList.addView(textView)*/
                    }
                    val viewFlipperAdapter = ViewFlipperAdapter(mContext, stars)
                    tvCommentList.adapter = viewFlipperAdapter
                    tvCommentList.visibility = View.VISIBLE
                    clCommentList.visibility = View.VISIBLE

                    /*  val marqueeText2: ScrollTextViewCommentListBackground = binding.tvCommentList
                      marqueeText2.setList(stars) // 将列表传递给跑马灯控件的setList方法
                      marqueeText2.startScroll()*/
                }

                //库存显示
                if (item.picArea.stock != null) {
                    tvStockout.text = item.picArea.stock
                    tvStockout.visibility = View.VISIBLE
                }
                // imageWeight = recyclerView.measuredWidth
                //比例
                if (item.picArea.imageRatio == null) {
                    item.picArea.imageRatio = 1.0f.toString()
                }


                //卡片锁宽等比缩放（imageRatio用来计算高度）
                val layoutParams =
                    ivPicAreaImageUrl.layoutParams as ConstraintLayout.LayoutParams
                layoutParams.dimensionRatio = item.picArea.imageRatio // 例如，设置宽高比为16:9
                ivPicAreaImageUrl.layoutParams = layoutParams

                //contentAreaList : 内容区域
                if (item.contentAreaList != null) {
                    val rechargeAdapter = ContentAreaListAdapter(
                        R.layout.adapter_recharge_content_area_list, item.contentAreaList
                    )
                    if (item.contentAreaList[0].type != "7" && item.contentAreaList[0].type != "8"
                        && item.contentAreaList[item.contentAreaList.size - 1].type != "7"
                        && item.contentAreaList[item.contentAreaList.size - 1].type != "8"
                    ) {
                        val lp =
                            clContentAreaList.layoutParams as ViewGroup.MarginLayoutParams
                        lp.topMargin = DensityUtils.dpToPx(mContext, 5f)
                        lp.bottomMargin = DensityUtils.dpToPx(mContext, 5f)
                        clContentAreaList.layoutParams = lp
                    }

                    //设置布局管理器和给recyclerView 设置设配器
                    rvContentAreaList.apply {
                        layoutManager = LinearLayoutManager(context)
                        adapter = rechargeAdapter
                        isNestedScrollingEnabled = false
                    }
                    // 设置圆角半径
                    //val requestOptions = RequestOptions().transform(RoundedCorners(20))
                    Glide.with(mContext)
                        .load(item.picArea.imageUrl)//使用 load() 方法传入 URL 字符串 imageUrl 来指定要加载的图片资源
                        //使用 transition() 方法可以设置过渡效果，例如交叉淡入淡出效果
                        .transition(DrawableTransitionOptions.withCrossFade())
                        //.transform(GranularRoundedCorners(20f, 20f, 0f, 0f))//四个角单独指定角度
                        //.apply(requestOptions
                        .error(R.drawable.ic_launcher_foreground)
                        .into(ivPicAreaImageUrl)
                } else {
                    Glide.with(mContext)
                        .load(item.picArea.imageUrl)//使用 load() 方法传入 URL 字符串 imageUrl 来指定要加载的图片资源
                        .error(R.drawable.ic_launcher_foreground)
                        .into(ivPicAreaImageUrl)
                }
            }

            GetFeedListData.FEED_ADAPTER_ITEM_TYPE.NULL -> {
                // 处理空布局
                val binding = WidgetMultipleItemNullBinding.bind(holder.itemView)

                //contentAreaList : 内容区域
                if (item.contentAreaList != null) {
                    val rechargeAdapter = ContentAreaListAdapter(
                        R.layout.adapter_recharge_content_area_list, item.contentAreaList
                    )
                    if (item.contentAreaList[0].type != "7" && item.contentAreaList[0].type != "8"
                        && item.contentAreaList[item.contentAreaList.size - 1].type != "7"
                        && item.contentAreaList[item.contentAreaList.size - 1].type != "8"
                    ) {
                        val layoutParams =
                            binding.clContentAreaList.layoutParams as ViewGroup.MarginLayoutParams
                        layoutParams.topMargin = DensityUtils.dpToPx(mContext, 5f)
                        layoutParams.bottomMargin = DensityUtils.dpToPx(mContext, 5f)
                        binding.clContentAreaList.layoutParams = layoutParams
                    }

                    //设置布局管理器和给recyclerView 设置设配器
                    binding.rvContentAreaList.apply {
                        layoutManager = LinearLayoutManager(context)
                        adapter = rechargeAdapter
                        isNestedScrollingEnabled = false
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
                            Glide.with(holder.imageView).load(data.imageUrl)
                                .error(R.drawable.ic_launcher_foreground).into(holder.imageView)
                        }
                    }
                })
                //设置圆角
                binding.cvBanner.isNestedScrollingEnabled = false
                binding.banner.isNestedScrollingEnabled = false
                binding.banner.setBannerRound2(20f)
                //设置轮播时间间隔
                binding.banner.setLoopTime(5000)
                binding.banner.indicator = CircleIndicator(mContext) // 设置指示器为圆圈样式
                binding.banner.setIndicatorWidth(15, 15)

            }

            GetFeedListData.FEED_LIST_ITEM_TYPE.RECHARGE.toInt() -> {
                holder.addOnClickListener(R.id.btnSelect)
                // 处理充值布局
                val binding = WidgetMultipleItemRechargeBinding.bind(holder.itemView)
                binding.cvRecharge.isNestedScrollingEnabled = false
                GetTelephoneNumberManager.setGetTelephoneNumberListener(object :
                    GetTelephoneNumberManager.OnGetTelephoneNumberManager {
                    override fun onGetTelephoneNumber(number: String) {

                        if (number.replace(" ", "").length == 11) {
                            //消除空格，并且第四位到第七位用*代替
                            binding.tvGetTelephoneNumber.text =
                                hideCharactersFromIndex(number.replace(" ", ""))
                        } else {
                            //val telephoneNumber = number.replace(" ", "").substring(0, 11)
                            binding.tvGetTelephoneNumber.text = number
                            Toast.makeText(mContext,"请输入有效号码！",Toast.LENGTH_SHORT).show()
                                //hideCharactersFromIndex(telephoneNumber)

                        }
                    }

                })

                //获取title
                //binding.tvTitle.text = item.quickRecharge.title

                /*     binding.btnSelect.setOnClickListener {
                         //设置点击事件监听器
                         onItemClickListener?.invoke(item)*//* if (ContextCompat.checkSelfPermission(
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
                     (context as ComponentActivity).startActivityForResult(intent, 1)*//*
                    //(context as ComponentActivity).pickContactLauncher.launch(intent)
                }*/

                //消除空格，并且第四位到第七位用*代替
                /* binding.tvLoginRechargeText.text =
                     hideCharactersFromIndex(item.quickRecharge.title.replace(" ", ""))*/

                val rechargeAdapter =
                    RechargeAdapter(R.layout.adapter_recharge, item.quickRecharge.denominations)

                //设置布局管理器和给recyclerView 设置设配器
                binding.rlRecharge.apply {
                    layoutManager = LinearLayoutManager(context)
                    adapter = rechargeAdapter
                    isNestedScrollingEnabled = false
                }
            }

            else  -> {
                // 处理单图布局
                val binding = WidgetMultipleItemCommonBinding.bind(holder.itemView)
                /* //卡片锁宽等比缩放（imageRatio用来计算高度）
                 val lp = binding.ivTopImage.layoutParams as ConstraintLayout.LayoutParams
                 lp.dimensionRatio = item.picArea.imageRatio // 例如，设置宽高比为16:9

                 binding.ivTopImage.layoutParams = lp*/
                //顶部标签
                if (item.picArea.topImage != null) {
                    binding.ivTopImage.visibility = View.VISIBLE
                    Glide.with(mContext)
                        .load(item.picArea.topImage)//使用 load() 方法传入 URL 字符串 imageUrl 来指定要加载的图片资源
                        //使用 transition() 方法可以设置过渡效果，例如交叉淡入淡出效果
                        .transition(DrawableTransitionOptions.withCrossFade())
                        //.transform(GranularRoundedCorners(20f, 20f, 0f, 0f))//四个角单独指定角度
                        //.apply(requestOptions
                        .error(R.drawable.ic_launcher_foreground)
                        .into(binding.ivTopImage)
                }

                //commentList : 评论列表
                if (item.picArea.commentList != null) {
                    val stars: MutableList<String> = mutableListOf() // 创建空的可变列表

                    for (tab in item.picArea.commentList) {
                        stars.add(tab.title) // 将每个标题添加到列表中
                        /*   val textView=
                               LayoutInflater.from(context)
                                   .inflate(R.layout.item_view, null) as TextView
                           textView.text = tab.title

                           binding.tvCommentList.addView(textView)*/
                    }
                    val viewFlipperAdapter = ViewFlipperAdapter(mContext, stars)
                    binding.tvCommentList.adapter = viewFlipperAdapter
                    binding.tvCommentList.visibility = View.VISIBLE
                    binding.clCommentList.visibility = View.VISIBLE

                    /*  val marqueeText2: ScrollTextViewCommentListBackground = binding.tvCommentList
                      marqueeText2.setList(stars) // 将列表传递给跑马灯控件的setList方法
                      marqueeText2.startScroll()*/
                }

                //库存显示
                if (item.picArea.stock != null) {
                    binding.tvStockout.text = item.picArea.stock
                    binding.tvStockout.visibility = View.VISIBLE
                }
                // imageWeight = recyclerView.measuredWidth
                //比例
                if (item.picArea.imageRatio == null) {
                    item.picArea.imageRatio = 1.0f.toString()
                }


                //卡片锁宽等比缩放（imageRatio用来计算高度）
                val layoutParams =
                    binding.ivPicAreaImageUrl.layoutParams as ConstraintLayout.LayoutParams
                layoutParams.dimensionRatio = item.picArea.imageRatio // 例如，设置宽高比为16:9
                binding.ivPicAreaImageUrl.layoutParams = layoutParams

                //contentAreaList : 内容区域
                if (item.contentAreaList != null) {
                    val rechargeAdapter = ContentAreaListAdapter(
                        R.layout.adapter_recharge_content_area_list, item.contentAreaList
                    )
                    if (item.contentAreaList[0].type != "7" && item.contentAreaList[0].type != "8"
                        && item.contentAreaList[item.contentAreaList.size - 1].type != "7"
                        && item.contentAreaList[item.contentAreaList.size - 1].type != "8"
                    ) {
                        val lp =
                            binding.clContentAreaList.layoutParams as ViewGroup.MarginLayoutParams
                        lp.topMargin = DensityUtils.dpToPx(mContext, 5f)
                        lp.bottomMargin = DensityUtils.dpToPx(mContext, 5f)
                        binding.clContentAreaList.layoutParams = lp
                    }

                    //设置布局管理器和给recyclerView 设置设配器
                    binding.rvContentAreaList.apply {
                        layoutManager = LinearLayoutManager(context)
                        adapter = rechargeAdapter
                    }
                    // 设置圆角半径
                    //val requestOptions = RequestOptions().transform(RoundedCorners(20))
                    Glide.with(mContext)
                        .load(item.picArea.imageUrl)//使用 load() 方法传入 URL 字符串 imageUrl 来指定要加载的图片资源
                        //使用 transition() 方法可以设置过渡效果，例如交叉淡入淡出效果
                        .transition(DrawableTransitionOptions.withCrossFade())
                        //.transform(GranularRoundedCorners(20f, 20f, 0f, 0f))//四个角单独指定角度
                        //.apply(requestOptions
                        .error(R.drawable.ic_launcher_foreground)
                        .into(binding.ivPicAreaImageUrl)
                } else {
                    Glide.with(mContext)
                        .load(item.picArea.imageUrl)//使用 load() 方法传入 URL 字符串 imageUrl 来指定要加载的图片资源
                        .error(R.drawable.ic_launcher_foreground)
                        .into(binding.ivPicAreaImageUrl)
                }
            }
        }
    }

    //它接受一个函数类型的参数 listener，该函数类型接受一个 Piggy 对象作为参数，并不返回任何结果
    fun setOnItemClickListener(listener: (GetFeedListData.FeedListBean) -> Unit) {
        //onItemClickListener 被赋值为传入的 listener，
        // 从而将外部传入的点击事件监听器与适配器关联起来
        onItemClickListener = listener
    }

    //第四位到第七位用*代替
    private fun hideCharactersFromIndex(text: String): String {
        val length = text.length
        if (3 >= length) {
            return text
        }

        val hiddenText = StringBuilder(text)
        for (i in 3 until 3 + 4) {
            hiddenText.setCharAt(i, '*')
        }

        return hiddenText.toString()
    }

    fun addMoreValue(
        feedList: MutableList<GetFeedListData.FeedListBean>,
        newData: List<GetFeedListData.FeedListBean>,
    ) {
        // 将新数据（newData）添加到现有的 feedList 集合中
        feedList.addAll(newData)
        // 或者，如果你希望新数据添加到开头，可以使用以下方式
        // feedList.addAll(0, newData)
    }

/*    fun refreshValue(
        feedList: MutableList<GetFeedListData.FeedListBean>,
        newData: List<GetFeedListData.FeedListBean>,
    ) {
        // 创建临时变量保存删除前的数据
        val deletedData = ArrayList(feedList)
        // 清空现有的数据
        feedList.clear()
        // 添加新的数据
        feedList.addAll(newData)
        // 在需要恢复删除的数据时，将其添加回 feedList
        feedList.addAll(deletedData)
    }*/


}


