package com.example.myapplication

import android.R
import android.content.res.ColorStateList
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import androidx.viewpager2.widget.ViewPager2
import com.example.myapplication.databinding.ActivityMainBinding
import com.google.android.material.tabs.TabLayoutMediator

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    val activeColor: Int = Color.parseColor("#ff678f")
    val normalColor: Int = Color.parseColor("#666666")

    var activeSize: Int = 20
    var normalSize: Int = 14

    lateinit var fragments: ArrayList<Fragment>
    lateinit var mediator: TabLayoutMediator

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //        binding.tvTextview.setText("成功啦")
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
//        val tabLayout = binding.tabLayout
//        val viewPager2 = binding.viewPager
        val tabs = arrayOf("Components", "    Helper", "       Lab")
        val image1 = com.example.myapplication.R.drawable.ic_canyin
        val image2 = com.example.myapplication.R.drawable.bgs
        val image3 = com.example.myapplication.R.drawable.images
        val pics = arrayOf(image1,image2,image3)
        binding.viewPager.offscreenPageLimit = ViewPager2.OFFSCREEN_PAGE_LIMIT_DEFAULT
        binding.viewPager.adapter = object : FragmentStateAdapter(supportFragmentManager,lifecycle) {
            override fun getItemCount(): Int {
                return tabs.size
            }

            override fun createFragment(position: Int): Fragment {
                return ComponentsFragment.newInstance(tabs[position])
            }

        }

        val adapter = DynamicFragmentAdapter(supportFragmentManager,lifecycle)
        binding.viewPager.adapter = adapter
        
        val mediator = TabLayoutMediator(binding.tabLayout,binding.viewPager) { tab,position ->
            val tabView = TextView(this@MainActivity)
            val states = arrayOf(
                intArrayOf(android.R.attr.state_selected),
                intArrayOf()
            )
            binding.tabLayout.getTabAt(0)?.setIcon(pics[0])
            binding.tabLayout.getTabAt(1)?.setIcon(pics[1]);
            binding.tabLayout.getTabAt(2)?.setIcon(pics[2]);
            val colors = intArrayOf(activeColor, normalColor)
            val colorStateList = ColorStateList(states, colors)
            tabView.text = tabs[position]
            tabView.setTextSize(normalSize.toFloat())
            tabView.setTextColor(colorStateList)
            tab.customView = tabView
        }
        mediator.attach()
    }

    class DynamicFragmentAdapter(fragmentManager: FragmentManager, lifecycle: Lifecycle): FragmentStateAdapter(fragmentManager, lifecycle) {
        private val fragments = listOf(
            ComponentsFragment(),
            HelperFragment(),
            LabFragment(),
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