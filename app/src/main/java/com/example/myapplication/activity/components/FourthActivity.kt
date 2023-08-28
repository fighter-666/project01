package com.example.myapplication.components

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import com.example.myapplication.databinding.ActivityFourthBinding
import com.gyf.immersionbar.ImmersionBar

class FourthActivity : AppCompatActivity() {
    private lateinit var binding: ActivityFourthBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFourthBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ImmersionBar.with(this)
            .statusBarDarkFont(true)   //状态栏字体是深色，不写默认为亮色
            .navigationBarDarkIcon(true) //导航栏图标是深色，不写默认为亮色
            .init();
    }
}