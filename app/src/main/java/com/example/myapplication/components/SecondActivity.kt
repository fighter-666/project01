package com.example.myapplication.components

import android.animation.ObjectAnimator
import android.animation.ValueAnimator
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
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

        binding.llAddAccount.setOnClickListener {
            val objectAnimation = ObjectAnimator.ofFloat(binding.llAddAccount, "translationX", 0f, -270f)
            objectAnimation.start()
        }
        binding.tvText.setOnClickListener {
            val objectAnimation =ObjectAnimator.ofFloat(binding.tvText, "scaleX", 1f,2f)
            objectAnimation.duration=3000
            objectAnimation.repeatCount=2
            objectAnimation.repeatMode= ValueAnimator.RESTART
            objectAnimation.start()
        }
        binding.tvText.setOnClickListener {
            val objectAnimation =ObjectAnimator.ofFloat(binding.tvText, "alpha", 1f,0f,1f)
            objectAnimation.duration=3000
            objectAnimation.start()
        }
        binding.tvText.setOnClickListener {
            val objectAnimation =
                ObjectAnimator.ofFloat(binding.tvText, "rotation", 0f,360f,0f)
            objectAnimation.duration=5000
            objectAnimation.repeatCount=ValueAnimator.INFINITE
            objectAnimation.repeatMode= ValueAnimator.RESTART
            objectAnimation.start()
        }





    }
}