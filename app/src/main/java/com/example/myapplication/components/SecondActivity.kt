package com.example.myapplication.components

import CustomView
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.myapplication.MyView
import com.example.myapplication.databinding.ActivitySecondBinding
import com.gyf.immersionbar.ImmersionBar


class SecondActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySecondBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySecondBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ImmersionBar.with(this)
            .statusBarDarkFont(true)   //状态栏字体是深色，不写默认为亮色
            .navigationBarDarkIcon(true) //导航栏图标是深色，不写默认为亮色
            .init();

        val customView = MyView(this)
        setContentView(customView)
    }
}