package com.example.myapplication.components

import android.content.ContentValues.TAG
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.animation.Animation
import android.view.animation.RotateAnimation
import android.widget.ImageView
import android.widget.LinearLayout
import com.example.myapplication.R
import com.example.myapplication.databinding.ActivityFourthBinding
import com.example.myapplication.recharge.ScratchCard
import com.gyf.immersionbar.ImmersionBar

class FourthActivity : AppCompatActivity() {
    private lateinit var binding: ActivityFourthBinding
    private lateinit var imageView: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFourthBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ImmersionBar.with(this)
            .statusBarDarkFont(true)   //状态栏字体是深色，不写默认为亮色
            .navigationBarDarkIcon(true) //导航栏图标是深色，不写默认为亮色
            .init();

        imageView = findViewById(R.id.image)
        val container = findViewById<LinearLayout>(R.id.container)
        val customView = ScratchCard(this)
        container.addView(customView)

        imageView.setOnClickListener {
            imageView.visibility = View.GONE
        }
    }


}