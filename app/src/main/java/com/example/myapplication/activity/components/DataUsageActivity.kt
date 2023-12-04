package com.example.myapplication.activity.components

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.chad.library.adapter.base.entity.MultiItemEntity
import com.example.myapplication.R
import com.example.myapplication.adapter.LocationAdapter
import com.example.myapplication.data.City
import com.example.myapplication.data.Province
import com.example.myapplication.data.Town
import com.example.myapplication.databinding.ActivityDataUsageBinding
import com.example.myapplication.fragment.DataUsageFragment
import com.example.myapplication.recharge.adapter.MyPagerAdapter
import com.example.myapplication.recharge.data.GetFeedTabData
import com.example.myapplication.recharge.fragment.WaterfallFragment
import com.google.gson.Gson
import com.gyf.immersionbar.ImmersionBar
import java.util.Random

class DataUsageActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDataUsageBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityDataUsageBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //沉浸式
        ImmersionBar.with(this).transparentStatusBar()  //透明状态栏，不写默认透明色
            .titleBar(binding.tvTitle)    //解决状态栏和布局重叠问题，任选其一
            .init()

        val json: String = // 从文件中读取 JSON 数据，这里使用 assets 文件夹中的示例
            application.assets.open("getFeedTabData.json").bufferedReader().use { it.readText() }
        //使用了Gson库来将JSON数据转换为GetFeedTabData对象
        val gson = Gson()
        val tabList = gson.fromJson(json, GetFeedTabData::class.java)

        //viewpage
        val fragments: MutableList<Fragment> = ArrayList()
        fragments.add(DataUsageFragment())
        fragments.add(WaterfallFragment())
        fragments.add(WaterfallFragment())

        val adapter = MyPagerAdapter(supportFragmentManager, fragments)
        binding.viewPager.offscreenPageLimit = fragments.size
        binding.viewPager.adapter = adapter              // 绑定adapter
        binding.tabLayout.setupWithViewPager(binding.viewPager)    // 绑定viewPager

        for (i in tabList.tabList.indices) {
            binding.tabLayout.getTabAt(i)?.text = tabList.tabList[i].tabName   // 设置标题
        }





    }

    fun num1AndNum2(num1: Int, num2: Int , operation:(Int, Int) -> Int) {

    }




}