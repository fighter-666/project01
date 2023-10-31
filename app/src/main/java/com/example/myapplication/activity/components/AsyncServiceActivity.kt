package com.example.myapplication.activity.components

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.os.Message
import com.example.myapplication.R
import com.example.myapplication.databinding.ActivityAsyncServiceBinding
import kotlin.concurrent.thread

class AsyncServiceActivity : AppCompatActivity() {
    private lateinit var binding: ActivityAsyncServiceBinding

    private val updateText = 1
    private val handler = object: Handler(Looper.myLooper()!!){
        override fun handleMessage(msg: Message) {
            // 更新UI
            when(msg.what){
                updateText -> {
                    binding.tvText.text = "Hello World"
                }

            }
        }
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAsyncServiceBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.btnText.setOnClickListener {
            thread {
                val msg = Message()
                msg.what = updateText
                handler.sendMessage(msg)
            }
        }
    }
}