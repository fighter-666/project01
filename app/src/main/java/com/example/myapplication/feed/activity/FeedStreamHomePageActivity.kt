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

    }
}