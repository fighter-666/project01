package com.example.myapplication

import android.os.Bundle
import android.view.LayoutInflater
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import androidx.viewpager2.widget.ViewPager2
import com.example.myapplication.databinding.ActivityMainBinding
import com.example.myapplication.fragment.ComponentsFragment
import com.example.myapplication.fragment.HelperFragment
import com.example.myapplication.fragment.LabFragment
import com.example.myapplication.fragment.WaterfallFragment
import com.google.android.material.tabs.TabLayoutMediator
import com.gyf.immersionbar.ImmersionBar

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        //沉浸式处理
        ImmersionBar.with(this)
            .transparentStatusBar()  //透明状态栏，不写默认透明色
            .init();



        val tabs = arrayOf("Components", "Helper", "Lab", "Waterfall")
        val pics = arrayOf(
            R.mipmap.icon_tabbar_component_selected,
            R.mipmap.icon_tabbar_util_selected,
            R.mipmap.icon_tabbar_lab_selected,
            R.mipmap.icon_tabbar_lab_selected
        )

        //设置默认的丽萍页面限制
        binding.viewPager.offscreenPageLimit = ViewPager2.OFFSCREEN_PAGE_LIMIT_DEFAULT
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

        val adapter = DynamicFragmentAdapter(supportFragmentManager,lifecycle)
        binding.viewPager.adapter = adapter
        
        val mediator = TabLayoutMediator(binding.tabLayout,binding.viewPager) { tab,position ->
            val tabView = LayoutInflater.from(this@MainActivity).inflate(R.layout.custom_tab_view, null)
            val tabIcon = tabView.findViewById<ImageView>(R.id.tab_icon)
            val tabTitle = tabView.findViewById<TextView>(R.id.tab_title)
            tabTitle.text = tabs[position]
            tabIcon.setImageResource(pics[position])
            tab.customView = tabView
            //tab.setIcon(pics[position])
        }
        mediator.attach()
    }

    class DynamicFragmentAdapter(fragmentManager: FragmentManager, lifecycle: Lifecycle): FragmentStateAdapter(fragmentManager, lifecycle) {
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