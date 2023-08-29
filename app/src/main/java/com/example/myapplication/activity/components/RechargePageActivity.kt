package com.example.myapplication.activity.components

import android.os.Bundle
import android.view.LayoutInflater
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.viewpager2.adapter.FragmentStateAdapter
import androidx.viewpager2.widget.ViewPager2
import com.example.myapplication.R
import com.example.myapplication.databinding.ActivityRechargePageBinding
import com.example.myapplication.recharge.fragment.RechargeWaterfallFragment
import com.example.myapplication.recharge.adapter.CrossExchengeAdapter
import com.example.myapplication.recharge.adapter.RecommendationServiceAdapteer
import com.example.myapplication.recharge.data.GetFeedTabData
import com.example.myapplication.recharge.property.Cards
import com.example.myapplication.recharge.property.Piggy
import com.example.myapplication.recharge.property.Second
import com.example.myapplication.recharge.widget.ScrollImageView
import com.example.myapplication.recharge.widget.ScrollTextView
import com.example.myapplication.recharge.widget.ScrrollTextViewBackground
import com.google.android.material.tabs.TabLayoutMediator
import com.google.gson.Gson
import com.gyf.immersionbar.ImmersionBar


class RechargePageActivity : AppCompatActivity() {

    private lateinit var binding: ActivityRechargePageBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRechargePageBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //沉浸式
        ImmersionBar.with(this).transparentStatusBar()  //透明状态栏，不写默认透明色
            .titleBar(binding.tvTitle)    //解决状态栏和布局重叠问题，任选其一
            .statusBarDarkFont(true)   //状态栏字体是深色，不写默认为亮色
            .init();

        val tabs = arrayOf("推荐", "年货节", "直播", "本地")
        val json = """
        {
        "tabList": [
            {
                "tabName": "推荐",
                "tabIcon": "",
                "redFlag": "",
                "timestamp": "20230427152925",
                "tabType": "1",
                "order": "1",
                "link": "",
                "linkType": "",
                "type": "1",
                "isDefault": "1",
                "subTitle": "大家在看"
            },
            {
                "tabName": "百度",
                "tabIcon": "",
                "redFlag": "",
                "timestamp": "20230427152943",
                "tabType": "1",
                "order": "2",
                "link": "https://www.baidu.com/",
                "linkType": "",
                "type": "2",
                "isDefault": "",
                "subTitle": "最新动态"
            },
            {
                "tabName": "商城",
                "tabIcon": "",
                "redFlag": "",
                "timestamp": "20230427153051",
                "tabType": "1",
                "order": "4",
                "link": "",
                "linkType": "",
                "type": "1",
                "isDefault": "0",
                "subTitle": "3C数码"
            },
            {
                "tabName": "视频",
                "tabIcon": "",
                "redFlag": "0",
                "timestamp": "20230427153129",
                "tabType": "1",
                "order": "5",
                "link": "",
                "linkType": "",
                "type": "1",
                "isDefault": "0",
                "subTitle": "高清影视"
            },
            {
                "tabName": "彩铃",
                "tabIcon": "",
                "redFlag": "0",
                "timestamp": "20230510142539",
                "tabType": "1",
                "order": "6",
                "link": "",
                "linkType": "",
                "type": "1",
                "isDefault": "0",
                "subTitle": "视频彩铃"
            },
            {
                "tabName": "直播",
                "tabIcon": "",
                "redFlag": "0",
                "timestamp": "20230711142241",
                "tabType": "1",
                "order": "8",
                "link": "",
                "linkType": "",
                "type": "1",
                "isDefault": "0",
                "subTitle": "精彩直播"
            },
            {
                "tabName": "二手",
                "tabIcon": "",
                "redFlag": "0",
                "timestamp": "20230619170238",
                "tabType": "1",
                "order": "9",
                "link": "",
                "linkType": "",
                "type": "1",
                "isDefault": "0",
                "subTitle": "手机换新"
            }
        ],
        "jumpGuideBar": {
            "title": "更多精彩等你发现",
            "iconUrl": "https://w.189.cn/bigdata/2023/5/8/111683532549514962.png",
            "provinceCode": "1000000037",
            "recommender": "cc-010001002000.rmc-hg_cxbl_fc.fd-2.od-1.eoc-0517500057",
            "sceneId": "hg_cxbl_fc##2786##default##0517500057##010001002000##3##N##16347400001##1##N##N##05175"
        },
        "isShowSubTitle": "2"
    }""".trimIndent()

        val gson = Gson()
        val tabList= gson.fromJson(json, GetFeedTabData::class.java)
        //设置默认的丽萍页面限制
        binding.viewPager2.offscreenPageLimit = ViewPager2.OFFSCREEN_PAGE_LIMIT_DEFAULT

        val adapter = RechargeFragmentAdapter(supportFragmentManager, lifecycle)
        binding.viewPager2.adapter = adapter

        val mediator = TabLayoutMediator(binding.tabLayout, binding.viewPager2) { tab, position ->
            val tabView =
                LayoutInflater.from(this).inflate(R.layout.view_custom_recharge_waterfall_tab, null)
            //val tabIcon = tabView.findViewById<ImageView>(R.id.tabIcon)
            val tabTitle = tabView.findViewById<TextView>(R.id.tabTitle)
            tabTitle.text = tabList.tabList[position].tabName
            //tabIcon.setImageResource(pics[position])
            tab.customView = tabView
            //tab.setIcon(pics[position])
        }
        mediator.attach()

        //消息条
        //右边textview跑马灯
        val marqueeText2: ScrrollTextViewBackground = binding.tvScrollBackground
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
        val myAdapter = RecommendationServiceAdapteer(R.layout.adapter_recommendation_service, piggies)

        //设置布局管理器
        binding.rvRecommendationService.setLayoutManager(
            LinearLayoutManager(
                this, LinearLayoutManager.HORIZONTAL, false
            )
        )

        //给RecycleView设置适配器
        binding.rvRecommendationService.setAdapter(myAdapter)

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
        val myAdapterCopy = RecommendationServiceAdapteer(R.layout.adapter_recommendation_service, piggiesCopy)

        //设置布局管理器
        binding.rvRecommendationServiceCopy.setLayoutManager(
            LinearLayoutManager(
                this, LinearLayoutManager.HORIZONTAL, false
            )
        )

        //给RecycleView设置适配器
        binding.rvRecommendationServiceCopy.setAdapter(myAdapterCopy)

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
        val secondAdapter = CrossExchengeAdapter(R.layout.adapter_cross_exchenge, piggies2)

        //设置布局管理器
        binding.rvCrossExchange.setLayoutManager(
            LinearLayoutManager(
                this, LinearLayoutManager.HORIZONTAL, false
            )
        )

        //给RecycleView设置适配器
        binding.rvCrossExchange.setAdapter(secondAdapter)


        /*//第三个
        val piggies3 = mutableListOf<MultipleItem>()
        piggies3.add(MultipleItem(1,))
        piggies3.add(MultipleItem(2,))
        piggies3.add(MultipleItem(3,))

        //创建适配器
        val thirdAdapter = MultipleItemQuickAdapter( piggies3)

        //设置布局管理器
       binding.recyclerView3.setLayoutManager(LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false))

        //给RecycleView设置适配器
       binding.recyclerView3.setAdapter(thirdAdapter)*/


        //第三个
        val piggies4 = mutableListOf<Cards>()
        piggies4.add(
            Cards(
                0, 0, R.drawable.falls1, "电信关爱版-为老年人架桥", "0", "0", "0", "0", "0", 0, 0
            )
        )
        piggies4.add(
            Cards(
                R.drawable.shape_rectangle18,
                R.drawable.shape_rectangle18,
                R.drawable.falls8,
                "加装【副卡】，一份套餐全家用 ",
                "赠新人礼包",
                "赠美团神券",
                "￥",
                "10",
                "/月",
                0,
                0
            )
        )
        piggies4.add(
            Cards(
                R.drawable.shape_rectangle18,
                R.drawable.shape_rectangle18,
                R.drawable.fall,
                "iPhone12 128GB 红色 双卡双待",
                "免运费",
                "送配件",
                "0",
                "0",
                "0",
                0,
                0
            )
        )
        piggies4.add(
            Cards(
                R.drawable.shape_rectangle18,
                0,
                R.drawable.falls4,
                "15GB定向流量+腾讯视频月会员卡",
                "0",
                "0",
                "0",
                "0",
                "0",
                0,
                0
            )
        )
        //piggies4.add(Cards(R.drawable.falls3, "iPhone12 128GB 红色 双卡双待", "免运费","0","0","0","0", 0, 0))
        //piggies4.add(Cards(R.drawable.falls4, "15GB定向流量+腾讯视频月会员卡", "可查全网记录","0","0","0","0", 0, 0))

        /*//创建适配器
        val fourthAdapter = RechangeWaterfallAdapter(R.layout.adapter_recharge_waterfall, piggies4)

        //设置布局管理器
        binding.rvWaterfall.setLayoutManager(
            StaggeredGridLayoutManager(
                2, StaggeredGridLayoutManager.VERTICAL
            )
        )
        //给RecycleView设置适配器
        binding.rvWaterfall.setAdapter(fourthAdapter)*/
    }

    class RechargeFragmentAdapter(fragmentManager: FragmentManager, lifecycle: Lifecycle) :
        FragmentStateAdapter(fragmentManager, lifecycle) {
        private val fragments = listOf(
            RechargeWaterfallFragment(),
            RechargeWaterfallFragment(),
            RechargeWaterfallFragment(),
            RechargeWaterfallFragment(),
            RechargeWaterfallFragment(),
            RechargeWaterfallFragment(),
            RechargeWaterfallFragment(),
            //加载更多的 Fragment 实例
        )

        override fun getItemCount(): Int {
            return fragments.size
        }

        override fun createFragment(position: Int): Fragment {
            return fragments[position]
        }
    }
}

