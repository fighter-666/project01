package com.example.myapplication.activity.components

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.example.myapplication.R
import com.example.myapplication.databinding.ActivityFifthBinding

class FifthActivity : AppCompatActivity() {
    private lateinit var binding: ActivityFifthBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFifthBinding.inflate(layoutInflater)
        setContentView(binding.root)


            // 带数字小红点
            binding.tabLayout.getTabAt(0)?.let {
                it.orCreateBadge.apply {
                    backgroundColor = ContextCompat.getColor(applicationContext, R.color.red)
                    badgeTextColor = ContextCompat.getColor(applicationContext, R.color.white)
                    number = 6
                }
            }

            // 不带数字小红点
            // 红点
            binding.tabLayout.getTabAt(1)?.let { tab ->
                tab.orCreateBadge.backgroundColor = ContextCompat.getColor(this, R.color.red)
            }


    }
}