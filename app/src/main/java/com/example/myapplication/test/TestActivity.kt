package com.example.myapplication.test

import android.os.Bundle
import com.example.myapplication.databinding.ActivityTestBinding
import com.example.myapplication.test.BaseBindingActivity


class TestActivity :
    BaseBindingActivity<ActivityTestBinding>(ActivityTestBinding::inflate) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding.tvHelloWorld.text = "Hello Android!"
    }
}
