package com.example.myapplication.activity.components.bilibili.activity

import android.content.Context
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.content.edit
import androidx.lifecycle.ViewModelProvider
import com.example.myapplication.activity.components.bilibili.widget.MainViewModelFactory
import com.example.myapplication.activity.components.bilibili.widget.MyViewModel
import com.example.myapplication.databinding.ActivityViewModelTestBinding

class ViewModelTestActivity : AppCompatActivity() {
    private lateinit var binding: ActivityViewModelTestBinding
    private lateinit var myViewModel: MyViewModel
    private lateinit var sp: SharedPreferences
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityViewModelTestBinding.inflate(layoutInflater)
        setContentView(binding.root)
        sp = getPreferences(Context.MODE_PRIVATE)
        val countReserved = sp.getInt("count_reserved", 0)

        //使用ViewModelProvider 来保存数据
        myViewModel = ViewModelProvider(this,MainViewModelFactory(countReserved)).get(MyViewModel::class.java)

        //加1 的点击事件
        binding.button.setOnClickListener {
            myViewModel.number++
            refreshCounter()
        }
        // //加2 的点击事件
        binding.button2.setOnClickListener {
            myViewModel.number += 2
            refreshCounter()
        }

        binding.btClear.setOnClickListener {
            myViewModel.number = 0
            refreshCounter()
        }
        refreshCounter()
    }

    override fun onPause() {
        super.onPause()
        sp.edit{
            putInt("count_reserved", myViewModel.number)
        }
    }

    private fun refreshCounter(){
        binding.textView.text = myViewModel.number.toString()
    }
}