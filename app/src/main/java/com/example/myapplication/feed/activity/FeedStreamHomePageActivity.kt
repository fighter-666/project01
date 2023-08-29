package com.example.myapplication.feed.activity

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import androidx.viewpager2.widget.ViewPager2
import com.example.myapplication.R
import com.example.myapplication.databinding.ActivityFeedStreamHomePageBinding
import com.example.myapplication.recharge.data.GetFeedTabData
import com.example.myapplication.fragment.ComponentsFragment
import com.example.myapplication.fragment.HelperFragment
import com.example.myapplication.fragment.LabFragment
import com.example.myapplication.fragment.WaterfallFragment
import com.google.android.material.tabs.TabLayoutMediator
import com.google.gson.Gson
import com.gyf.immersionbar.ImmersionBar

class FeedStreamHomePageActivity : AppCompatActivity() {
    private lateinit var binding: ActivityFeedStreamHomePageBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFeedStreamHomePageBinding.inflate(layoutInflater)
        setContentView(binding.root)
        //沉浸式处理
        ImmersionBar.with(this)
            .transparentStatusBar()  //透明状态栏，不写默认透明色
            .init();

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

        for (tab in tabList.tabList) {
            Log.d("1","标签名称: ${tab.tabName}")
            Log.d("1","标签图标: ${tab.tabIcon}")
            Log.d("1","红旗: ${tab.redFlag}")
            Log.d("1","时间戳: ${tab.timestamp}")
            Log.d("1","标签类型: ${tab.tabType}")
            Log.d("1","顺序: ${tab.order}")
            Log.d("1","链接: ${tab.link}")
            Log.d("1","链接类型: ${tab.linkType}")
            Log.d("1","类型: ${tab.type}")
            Log.d("1","是否默认: ${tab.isDefault}")
            Log.d("1","子标题: ${tab.subTitle}")
            Log.d("1"," ")
        }

        val tabs = arrayOf("Components", "Helper", "Lab", "Waterfall")
        val pics = arrayOf(
            R.mipmap.icon_tabbar_component_selected,
            R.mipmap.icon_tabbar_util_selected,
            R.mipmap.icon_tabbar_lab_selected,
            R.mipmap.icon_tabbar_lab_selected
        )

        //设置默认的丽萍页面限制
        binding.viewPager2.offscreenPageLimit = ViewPager2.OFFSCREEN_PAGE_LIMIT_DEFAULT
        //使用FragmentStateAdapter的匿名子类为ViewPager2设置适配器
        /* binding.viewPager.adapter = object : FragmentStateAdapter(supportFragmentManager,lifecycle) {
             override fun getItemCount(): Int {
                 //返回标签页的数量
                 return tabs.size
             }

             override fun createFragment(position: Int): Fragment {
                 val fragment = ComponentsFragment.newInstance(tabs[position])
                 val tab = binding.tabLayout.getTabAt(position)
                 tab?.setIcon(pics[position])
                 return fragment
             }
         }*/

        val adapter = DynamicFragmentAdapter(supportFragmentManager, lifecycle)
        binding.viewPager2.adapter = adapter

        val mediator = TabLayoutMediator(binding.tabLayout, binding.viewPager2) { tab, position ->
            val tabView =
                LayoutInflater.from(applicationContext).inflate(R.layout.view_custom_tab, null)
            val tabIcon = tabView.findViewById<ImageView>(R.id.tabIcon)
            val tabTitle = tabView.findViewById<TextView>(R.id.tabTitle)
            tabTitle.text = tabs[position]
            tabIcon.setImageResource(pics[position])
            tab.customView = tabView
            //tab.setIcon(pics[position])
        }
        mediator.attach()
    }

    class DynamicFragmentAdapter(fragmentManager: FragmentManager, lifecycle: Lifecycle) :
        FragmentStateAdapter(fragmentManager, lifecycle) {
        private val fragments = listOf(
            ComponentsFragment(),
            HelperFragment(),
            LabFragment(),
            WaterfallFragment(),
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