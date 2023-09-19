package com.example.myapplication.activity.components

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.myapplication.databinding.ActivityCustomBinding
import com.example.myapplication.widget.CustomView
import com.gyf.immersionbar.ImmersionBar

class CustomActivity : AppCompatActivity() {
    private lateinit var binding: ActivityCustomBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCustomBinding.inflate(layoutInflater)
        setContentView(binding.root)
        //沉浸式
        ImmersionBar.with(this)
            .statusBarDarkFont(true)   //状态栏字体是深色，不写默认为亮色
            .navigationBarDarkIcon(true) //导航栏图标是深色，不写默认为亮色
            .init()

        //创建了一个自定义视图对象 customView 并将其设置为当前活动的内容视图。
        val customView = CustomView(this)
        setContentView(customView)
    }
}