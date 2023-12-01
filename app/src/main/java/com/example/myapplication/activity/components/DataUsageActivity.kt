package com.example.myapplication.activity.components

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.myapplication.R
import com.example.myapplication.databinding.ActivityDataUsageBinding
import com.gyf.immersionbar.ImmersionBar

class DataUsageActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDataUsageBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityDataUsageBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //沉浸式
        ImmersionBar.with(this).transparentStatusBar()  //透明状态栏，不写默认透明色
            .titleBar(binding.tvTitle)    //解决状态栏和布局重叠问题，任选其一
            .statusBarDarkFont(true)   //状态栏字体是深色，不写默认为亮色
            .init()

    }
}