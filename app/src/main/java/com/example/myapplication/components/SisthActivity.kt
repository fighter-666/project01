package com.example.myapplication.components

import com.example.myapplication.widget.CustomView
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.myapplication.databinding.ActivitySixthBinding
import com.gyf.immersionbar.ImmersionBar

class SisthActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySixthBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySixthBinding.inflate(layoutInflater)
        setContentView(binding.root)
        //沉浸式
        ImmersionBar.with(this)
            .statusBarDarkFont(true)   //状态栏字体是深色，不写默认为亮色
            .navigationBarDarkIcon(true) //导航栏图标是深色，不写默认为亮色
            .init();

        //创建了一个自定义视图对象 customView 并将其设置为当前活动的内容视图。
        val customView = CustomView(this)
        setContentView(customView)
    }
}