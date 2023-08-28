package com.example.myapplication.activity.components.bilibili.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.example.myapplication.R
import com.example.myapplication.activity.components.bilibili.widget.ViewModelWithDataBinding
import com.example.myapplication.databinding.ActivityDataBindingBinding

class DataBindingActivity : AppCompatActivity() {
    private lateinit var viewModelWithDataBinding: ViewModelWithDataBinding
    private lateinit var binding: ActivityDataBindingBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_data_binding)
        binding = DataBindingUtil.setContentView(this,R.layout.activity_data_binding)
        viewModelWithDataBinding = ViewModelProvider(this).get(ViewModelWithDataBinding::class.java)
        binding.setData(viewModelWithDataBinding)
        binding.setLifecycleOwner(this)
    }
}