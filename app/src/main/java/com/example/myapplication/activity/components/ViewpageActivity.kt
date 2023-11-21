package com.example.myapplication.activity.components

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.example.myapplication.R
import com.example.myapplication.databinding.ActivityViewpageBinding
import com.example.myapplication.fragment.TestFragment
import com.example.myapplication.recharge.adapter.MyPagerAdapter
import com.example.myapplication.recharge.data.GetFeedTabData
import com.example.myapplication.recharge.fragment.WaterfallFragment
import com.google.gson.Gson

class ViewpageActivity : AppCompatActivity() {
    private lateinit var binding: ActivityViewpageBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityViewpageBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val jsonTab: String = // 从文件中读取 JSON 数据，这里使用 assets 文件夹中的示例
            application.assets.open("getFeedTabData.json").bufferedReader().use { it.readText() }
        //使用了Gson库来将JSON数据转换为GetFeedTabData对象
        val gsonTab = Gson()
        val tabList = gsonTab.fromJson(jsonTab, GetFeedTabData::class.java)

        //viewpage
        val fragments: MutableList<Fragment> = ArrayList()
        fragments.add(TestFragment())
        fragments.add(WaterfallFragment())

        val myPagerAdapter = MyPagerAdapter(supportFragmentManager, fragments)
        binding.viewPager.adapter = myPagerAdapter              // 绑定adapter
        binding.tabLayout.setupWithViewPager(binding.viewPager)    // 绑定viewPager

        for (i in tabList.tabList.indices) {
            binding.tabLayout.getTabAt(i)?.text = tabList.tabList[i].tabName   // 设置标题
        }
    }
}