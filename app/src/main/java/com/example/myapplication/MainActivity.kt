package com.example.myapplication

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.os.Process
import android.text.TextUtils
import android.util.Log
import android.view.LayoutInflater
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.myapplication.adapter.DynamicFragmentAdapter
import com.example.myapplication.databinding.ActivityMainBinding
import com.google.android.material.tabs.TabLayoutMediator
import com.gyf.immersionbar.ImmersionBar
import java.io.BufferedReader
import java.io.File
import java.io.FileReader


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    fun getProcessName(): String? {
        return try {
            val file = File("/proc/" + Process.myPid() + "/" + "cmdline")
            val mBufferedReader = BufferedReader(FileReader(file))
            val processName = mBufferedReader.readLine().trim { it <= ' ' }
            mBufferedReader.close()
            processName
        } catch (e: Exception) {
            e.printStackTrace()
            null
        }
    }


    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        startService(Intent(this, MyService::class.java))

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        //沉浸式处理
        ImmersionBar.with(this)
            .transparentStatusBar()  //透明状态栏，不写默认透明色
            .init()

        Log.d("MainActivity", "onCreate: ")

        val processName = getProcessName()

//判断进程名，保证只有主进程运行

//判断进程名，保证只有主进程运行
        if (!TextUtils.isEmpty(processName) && processName == this.packageName) {
            //在这里进行主进程初始化逻辑操作
            Log.i(">>>>>>", "oncreate")
        }


        val tabs = arrayOf("Components", "Helper", "Lab", "Waterfall")
        val pics = arrayOf(
            R.mipmap.icon_tabbar_component_selected,
            R.mipmap.icon_tabbar_util_selected,
            R.mipmap.icon_tabbar_lab_selected,
            R.mipmap.icon_tabbar_lab_selected
        )

        // offscreenPageLimit 离屏页面限制决定了在 ViewPager 的适配器中，当前页面两侧应该保留的页面数量
        // tabs.size 被用来动态地根据标签数量设置离屏页面限制
        binding.viewPager.offscreenPageLimit = tabs.size
        val adapter =
            DynamicFragmentAdapter(supportFragmentManager, lifecycle)
        binding.viewPager.adapter = adapter

        val mediator = TabLayoutMediator(binding.tabLayout, binding.viewPager) { tab, position ->
            val tabView =
                LayoutInflater.from(this@MainActivity).inflate(R.layout.view_custom_tab, null)
            val tabIcon = tabView.findViewById<ImageView>(R.id.tabIcon)
            val tabTitle = tabView.findViewById<TextView>(R.id.tabTitle)
            tabTitle.text = tabs[position]
            tabIcon.setImageResource(pics[position])
            tab.customView = tabView
        }
        mediator.attach()
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d("MainActivity", "onDestroy")
    }

    companion object {
        fun actionStart(context: Context) {
            val intent = Intent(context, MainActivity::class.java)
            context.startActivity(intent)
        }
    }
}