package com.example.myapplication.activity.components.bilibili.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.get
import com.example.myapplication.R
import com.example.myapplication.activity.components.bilibili.util.ViewModelWithData
import com.example.myapplication.databinding.ActivityLiveDataBinding

class LiveData : AppCompatActivity() {
    private lateinit var binding: ActivityLiveDataBinding
    private lateinit var viewModelWithData: ViewModelWithData

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLiveDataBinding.inflate(layoutInflater)
        setContentView(binding.root)
        viewModelWithData = ViewModelProvider(this).get( ViewModelWithData::class.java)
        viewModelWithData.likedNumber.observe(this) { likedNumber ->
            // 在这里处理观察到的 likedNumber 值的变化
            binding.textView2.text = likedNumber.toString()
        }

        binding.imageView.setOnClickListener {
            viewModelWithData.addLikedNumber(1)
        }

        binding.imageView2.setOnClickListener {
            viewModelWithData.addLikedNumber(-1)
        }

    }
}