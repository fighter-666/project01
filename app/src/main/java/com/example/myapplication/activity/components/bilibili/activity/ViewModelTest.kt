package com.example.myapplication.activity.components.bilibili.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.example.myapplication.activity.components.bilibili.util.MyViewModel
import com.example.myapplication.databinding.ActivityViewModelTestBinding

class ViewModelTest : AppCompatActivity() {
    private lateinit var binding: ActivityViewModelTestBinding
    private lateinit var myViewModel: MyViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityViewModelTestBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //使用ViewModelProvider 来保存数据
        myViewModel = ViewModelProvider(this).get(MyViewModel::class.java)
        binding.textView.text = myViewModel.number.toString()

        //加1 的点击事件
        binding.button.setOnClickListener {
            myViewModel.number++
            binding.textView.text = myViewModel.number.toString()
        }
        // //加2 的点击事件
        binding.button2.setOnClickListener {
            myViewModel.number += 2
            binding.textView.text = myViewModel.number.toString()
        }
    }
}