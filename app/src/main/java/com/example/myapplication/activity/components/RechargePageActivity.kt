package com.example.myapplication.activity.components

import android.graphics.Typeface
import android.os.Bundle
import android.util.TypedValue
import android.view.LayoutInflater
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.example.myapplication.R
import com.example.myapplication.databinding.ActivityRechargePageBinding
import com.example.myapplication.recharge.adapter.CrossExchangeAdapter
import com.example.myapplication.recharge.adapter.RecommendationServiceAdapteer
import com.example.myapplication.recharge.adapter.Viewpager2Adapter
import com.example.myapplication.recharge.data.GetFeedTabData
import com.example.myapplication.recharge.fragment.CommWebViewFragment
import com.example.myapplication.recharge.fragment.WaterfallFragment
import com.example.myapplication.recharge.property.Piggy
import com.example.myapplication.recharge.property.Second
import com.example.myapplication.recharge.widget.LoadMoreManager
import com.example.myapplication.recharge.widget.ScrollImageView
import com.example.myapplication.recharge.widget.ScrollTextView
import com.example.myapplication.recharge.widget.ScrollTextViewBackground
import com.example.myapplication.util.DensityUtils
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import com.google.gson.Gson
import com.gyf.immersionbar.ImmersionBar
import com.scwang.smart.refresh.footer.BallPulseFooter
import com.scwang.smart.refresh.header.BezierRadarHeader
import com.scwang.smart.refresh.layout.constant.SpinnerStyle
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class RechargePageActivity : AppCompatActivity() {

    private lateinit var binding: ActivityRechargePageBinding
    private lateinit var fragmentTypes: List<String>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRechargePageBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //沉浸式
        ImmersionBar.with(this).transparentStatusBar()  //透明状态栏，不写默认透明色
            .titleBar(binding.tvTitle)    //解决状态栏和布局重叠问题，任选其一
            .statusBarDarkFont(true)   //状态栏字体是深色，不写默认为亮色
            .init()

        //加载更多
        binding.refreshLayout.setOnLoadMoreListener {
            binding.refreshLayout.finishLoadMore(true)
            //设置回调
            LoadMoreManager.triggerLoadMore()
        }

        //监听吸顶
        binding.scrollerLayout.setOnPermanentStickyChangeListener {
            /* if (it.contains( binding.toolbar)){
                 binding.toolbar.setBackgroundColor(resources.getColor(R.color.white))
                 binding.tvTitle.setBackgroundColor(resources.getColor(R.color.white))
             }*/

            if (it.contains(binding.tabLayout)) {
                binding.tabLayout.setBackgroundColor(resources.getColor(R.color.white))
            } else {
                binding.tabLayout.setBackgroundColor(resources.getColor(R.color.gray_200))
            }

        }


// 设置 Header 为贝塞尔雷达样式
        binding.refreshLayout.setRefreshHeader(BezierRadarHeader(this).setEnableHorizontalDrag(true))
// 设置 Footer 为球脉冲样式
        binding.refreshLayout.setRefreshFooter(BallPulseFooter(this).setSpinnerStyle(SpinnerStyle.Translate))

        val json: String = // 从文件中读取 JSON 数据，这里使用 assets 文件夹中的示例
            application.assets.open("getFeedTabData.json").bufferedReader().use { it.readText() }
        //使用了Gson库来将JSON数据转换为GetFeedTabData对象
        val gson = Gson()
        val tabList = gson.fromJson(json, GetFeedTabData::class.java)

        //将 offscreenPageLimit 属性设置为 tab的数量，表示 ViewPager 会在当前页面的左右各保留 tab数量 个页面的缓存。
        // 这样可以提高用户体验，因为用户在滑动 ViewPager 时，相邻的页面已经被缓存，可以更快地进行加载和显示
        // 延迟设置offscreenPageLimit属性，防止进入activity时的等待
        binding.viewPager2.offscreenPageLimit = tabList.tabList.size - 1




        // val fragmentTypes = listOf("Waterfall", "Wap", "Waterfall", /* ... 其他类型 ... */)
        // val adapter = FragmentAdapter(supportFragmentManager, lifecycle, fragmentTypes)
        //val adapter = FragmentAdapter(supportFragmentManager, lifecycle)
        val adapter = Viewpager2Adapter(this)

        binding.viewPager2.adapter = adapter

        binding.viewPager2.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                Toast.makeText(applicationContext, "position:$position", Toast.LENGTH_SHORT).show()
                // Here, position indicates the currently visible fragment's index.
            }
        })




    /*    binding.viewPager2.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                val view = (binding.viewPager2.getChildAt(0) as RecyclerView).layoutManager?.findViewByPosition(position)

                view?.post {
                    val wMeasureSpec = View.MeasureSpec.makeMeasureSpec(view.width, View.MeasureSpec.EXACTLY)
                    val hMeasureSpec = View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED)
                    view.measure(wMeasureSpec, hMeasureSpec)

                    if (binding.viewPager2.layoutParams.height != view.measuredHeight) {
                        binding.viewPager2.layoutParams = (binding.viewPager2.layoutParams).also { lp -> lp.height = view.measuredHeight }
                    }
                }
            }
        })*/



        /* for (tab in tabList.tabList) {
             adapter.addFragment(WapFragment.newInstance(tab))
         }*/

        // 添加  frament
        for (i in tabList.tabList.indices) {
            when (i) {
                0 -> adapter.addFragment(WaterfallFragment.newInstance(i))
                1 -> adapter.addFragment(CommWebViewFragment.newInstance(i))
                else -> adapter.addFragment(WaterfallFragment.newInstance(i))
                // ...为其他indexes添加对应的Fragment
            }
        }
      /*  binding.viewPager2.viewTreeObserver.addOnGlobalLayoutListener {

            updatePagerHeightForChild(tabList.tabList[binding.viewPager2.currentItem].view, binding.viewPager2)
        }*/


        /*   binding.viewPager2.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
               override fun onPageSelected(position: Int) {
                   val fragment = adapter.getFragment(position) as? LazyLoadFragment
                   fragment?.onFragmentVisible()
               }
           })*/


        //创建了一个TabLayoutMediator对象，并将其与TabLayout和ViewPager2进行关联。
        val mediator = TabLayoutMediator(binding.tabLayout, binding.viewPager2) { tab, position ->
            val tabView =
                LayoutInflater.from(this).inflate(R.layout.view_custom_recharge_waterfall_tab, null)
            val tabIcon = tabView.findViewById<ImageView>(R.id.tabIcon)
            val tabName = tabView.findViewById<TextView>(R.id.tabName)
            val subTitle = tabView.findViewById<TextView>(R.id.subTitle)
            val redFlag = tabView.findViewById<ImageView>(R.id.redFlag)

            // 排序tabList
            tabList.tabList.sortBy { it.order }

            // 重新按照排序后的tabList获取tabItem
            val tabItem = tabList.tabList[position]

            tabName.text = tabItem.tabName
            subTitle.text = tabItem.subTitle

            // 设置tab的自定义视图
            tab.customView = tabView

            //对选中状态的监听
            binding.tabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
                override fun onTabSelected(tab: TabLayout.Tab) {
                    // 进行相应的处理，例如更新UI、加载内容等
                    updateTabFont(tab, true) // 设置选中标签字体加粗
                }

                override fun onTabUnselected(tab: TabLayout.Tab) {
                    // 当前选中的标签取消选中时的处理逻辑
                    updateTabFont(tab, false) // 取消选中标签字体加粗
                }

                override fun onTabReselected(tab: TabLayout.Tab) {
                    // 当前选中的标签再次被选中时的处理逻辑
                }

                // 辅助方法：更新标签字体样式
                private fun updateTabFont(tab: TabLayout.Tab, isSelected: Boolean) {
                    val customView: View? = tab.customView
                    if (customView != null) {
                        val tabName: TextView =
                            customView.findViewById(R.id.tabName) as TextView // 自定义布局中的 TextView
                        val tabIcon = customView.findViewById<ImageView>(R.id.tabIcon)
                        CoroutineScope(Dispatchers.Main).launch {

                            if (isSelected) {
                                tabName.setTypeface(null, Typeface.BOLD) // 设置字体加粗
                                tabName.setTextSize(TypedValue.COMPLEX_UNIT_SP, 17f) // 设置字体大小，20sp
                                val layoutParams = tabName.layoutParams
                                layoutParams.height = DensityUtils.dpToPx(applicationContext, 25f)
                                tabName.layoutParams = layoutParams
                                val layoutParams1 = tabIcon.layoutParams
                                layoutParams1.height = DensityUtils.dpToPx(applicationContext, 25f)
                                tabIcon.layoutParams = layoutParams1


                            } else {
                                tabName.setTypeface(null, Typeface.NORMAL) // 取消字体加粗
                                tabName.setTextSize(TypedValue.COMPLEX_UNIT_SP, 16f) //  取消字体加粗
                                val layoutParams = tabName.layoutParams
                                layoutParams.height = DensityUtils.dpToPx(applicationContext, 20f)
                                tabName.layoutParams = layoutParams
                                val layoutParams1 = tabIcon.layoutParams
                                layoutParams1.height = DensityUtils.dpToPx(applicationContext, 20f)
                                tabIcon.layoutParams = layoutParams1
                            }
                        }
                    }
                }
            })


            // 添加红点
            if (tabItem.redFlag == "1") {
                val badgeDrawable = tab.orCreateBadge
                badgeDrawable.backgroundColor = ContextCompat.getColor(this, R.color.red)
                badgeDrawable.isVisible = true
                badgeDrawable.number = 0 // 设置红点上的数字，0 表示只显示红点，不显示数字
            }

            //redFlag : 是否显示红点：0否 1是 string
            if (tabItem.redFlag == "1") {
                //通过 tabList.tabList.indexOf(tabItem) 获取 tabItem 在 tabList.tabList 中的索引
                redFlag.visibility = View.VISIBLE

            }
            binding.tabLayout.getTabAt(2)?.let {
                it.orCreateBadge.apply {
                    backgroundColor = ContextCompat.getColor(application, R.color.red)
                }
            }

            //tabType : tab栏显示类型：1：显示标题 2：显示图标 string
            if (tabItem.tabType == "1") {
                tabIcon.visibility = View.GONE
            } else {
                //subTitle.visibility = View.INVISIBLE
                tabName.visibility = View.GONE
                //tabIcon : tab栏图标 string

                if (tabItem.tabIcon != "") {
                    //使用 Glide 的 with() 方法传入一个上下文对象 context 来初始化 Glide
                    //在协程中加载网络图片或在后台线程中加载大量图片。
                    // 确保在使用 Glide 加载图片时选择正确的 Dispatchers，以避免阻塞主线程
                    CoroutineScope(Dispatchers.Main).launch {
                        // 设置圆角半径
                        Glide.with(application)
                            .load(tabItem.tabIcon)//使用 load() 方法传入 URL 字符串 imageUrl 来指定要加载的图片资源
                            //使用 transition() 方法可以设置过渡效果，例如交叉淡入淡出效果
                            .transition(DrawableTransitionOptions.withCrossFade())
                            .error(R.drawable.baseline_thumb_up_24)
                            .into(tabIcon)
                    }
                    /* val tabIconResourceName = tabItem.tabIcon.substringAfter("R.drawable.")
                     //使用 resources.getIdentifier(tabIconResourceName, "drawable", packageName)，
                     // 我们通过资源名称、资源类型（这里是 "drawable"）和包名来获取资源的标识符
                     val resourceId =
                         resources.getIdentifier(tabIconResourceName, "drawable", packageName)
                     tabIcon.setImageResource(resourceId)*/
                }
            }

            //type : tab栏显示内容类型：1.显示feed流原生列表 2：显示wap页面 3：10.0新增显示原生关注页 string
            // link : wap页面跳转链接 string
            if (tabItem.type == "2") {
                link = tabItem.link
            } else {
                // 处理链接为空的情况
                // 可以显示一个提示或执行其他逻辑
            }
            //fragmentTypes =tabItem

            //isDefault : 10.0新增是否默认选中（0：否，1：是） string
            if (tabItem.isDefault == "1") {
                //通过 tabList.tabList.indexOf(tabItem) 获取 tabItem 在 tabList.tabList 中的索引
                binding.viewPager2.setCurrentItem(
                    tabList.tabList.indexOf(tabItem),
                    false
                ) // 设置默认选中项
            }
        }
        mediator.attach()

        //消息条
        //右边textview跑马灯
        val marqueeText2: ScrollTextViewBackground = binding.tvScrollBackground
        val demographicsList2: MutableList<String> = ArrayList()
        demographicsList2.add("股票")
        demographicsList2.add("药业")
        demographicsList2.add("上市")
        marqueeText2.setList(demographicsList2)
        marqueeText2.startScroll()

        //imageview跑马灯
        val marqueeText3: ScrollImageView = binding.imScroll
        val demographicsList3: MutableList<Int> = ArrayList()
        demographicsList3.add(R.drawable.card3)
        demographicsList3.add(R.drawable.beans)
        demographicsList3.add(R.drawable.card2)
        marqueeText3.setList(demographicsList3)
        marqueeText3.startScroll()

        //中间textview跑马灯
        val marqueeText: ScrollTextView = binding.tvScroll
        val demographicsList: MutableList<String> = ArrayList()
        demographicsList.add("今日测试股票 上市")
        demographicsList.add("今日科伦药业 中国人保 可申购今日科伦药业 中国人保 可申购今日科伦药业 中国人保 可申购今日科伦药业 中国人保 可申购今日科伦药业 中国人保 可申购今日科伦药业 中国人保 可申购")
        demographicsList.add("今日中国平安 上市")
        marqueeText.setList(demographicsList)
        marqueeText.startScroll()

        //第一个 recyclerView 3个item 的时候
        val piggies = mutableListOf<Piggy>()
        piggies.add(Piggy(R.drawable.image1, "充流量", "流量告急速订购"))
        piggies.add(Piggy(R.drawable.image2, "开通自动充", "专治忘充值"))
        piggies.add(Piggy(R.drawable.image3, "电子发票", "批量开票不排队"))

        //创建适配器
        val myAdapter =
            RecommendationServiceAdapteer(R.layout.adapter_recommendation_service, piggies)

        //设置布局管理器
        binding.rvRecommendationService.layoutManager = LinearLayoutManager(
            this, LinearLayoutManager.HORIZONTAL, false
        )

        //给RecycleView设置适配器
        binding.rvRecommendationService.adapter = myAdapter

        //添加装饰器
        //binding.rvRecommendationService.addItemDecoration(FirstDecoration())

        //第一个 recyclerViewCopy 页面超过3个，显示3.5个item 的时候
        val piggiesCopy = mutableListOf<Piggy>()
        piggiesCopy.add(Piggy(R.drawable.image1, "充流量", "流量告急速订购"))
        piggiesCopy.add(Piggy(R.drawable.image2, "开通自动充", "专治忘充值"))
        piggiesCopy.add(Piggy(R.drawable.image3, "电子发票", "批量开票不排队"))
        piggiesCopy.add(Piggy(R.drawable.image4, "充值记录", "可查全网记录"))
        piggiesCopy.add(Piggy(R.drawable.image4, "充值记录", "可查全网记录"))
        piggiesCopy.add(Piggy(R.drawable.image4, "充值记录", "可查全网记录"))
        piggiesCopy.add(Piggy(R.drawable.image4, "充值记录", "可查全网记录"))

        //创建适配器
        val myAdapterCopy =
            RecommendationServiceAdapteer(R.layout.adapter_recommendation_service, piggiesCopy)

        //设置布局管理器
        binding.rvRecommendationServiceCopy.layoutManager = LinearLayoutManager(
            this, LinearLayoutManager.HORIZONTAL, false
        )

        //给RecycleView设置适配器
        binding.rvRecommendationServiceCopy.adapter = myAdapterCopy

        //添加装饰器
        //binding.rvRecommendationServiceCopy.addItemDecoration(FirstDecoration())


        //第二个
        val piggies2 = mutableListOf<Second>()
        piggies2.add(
            Second(
                R.drawable.tengxun, "腾讯视频会员\n" + "周卡", "1000金豆", 0
            )
        )
        piggies2.add(
            Second(
                R.drawable.youku,
                "优酷视频会员\n" + "周卡",
                "1500金豆",
                R.drawable.shape_rectangle16
            )
        )
        piggies2.add(
            Second(
                R.drawable.youku, "腾讯视频会员\n" + "周卡", "1000金豆", 0
            )
        )
        piggies2.add(
            Second(
                R.drawable.youku, "腾讯视频会员\n" + "周卡", "1000金豆", 0
            )
        )
        piggies2.add(
            Second(
                R.drawable.tengxun, "腾讯视频会员\n" + "周卡", "1000金豆", 0
            )
        )
        piggies2.add(
            Second(
                R.drawable.tengxun, "腾讯视频会员\n" + "周卡", "1000金豆", 0
            )
        )

        //创建适配器
        val secondAdapter = CrossExchangeAdapter(R.layout.adapter_cross_exchenge, piggies2)

        //设置布局管理器
        binding.rvCrossExchange.layoutManager = LinearLayoutManager(
            this, LinearLayoutManager.HORIZONTAL, false
        )

        //给RecycleView设置适配器
        binding.rvCrossExchange.adapter = secondAdapter
    }

    private fun initDatas() {
        val piggies2 = mutableListOf<Second>()
        piggies2.add(
            Second(
                R.drawable.tengxun, "腾讯视频会员\n" + "周卡", "1000金豆", 0
            )
        )
    }

    companion object {
        lateinit var link: String
    }
}

