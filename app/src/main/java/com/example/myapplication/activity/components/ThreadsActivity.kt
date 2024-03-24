package com.example.myapplication.activity.components

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Message
import com.example.myapplication.R
import com.example.myapplication.databinding.ActivityThreadsBinding
import kotlin.concurrent.thread

class ThreadsActivity : AppCompatActivity() {
    private lateinit var binding:ActivityThreadsBinding
    private val updateText = 1

    private val handle = @SuppressLint("HandlerLeak")
    object : Handler() {
        override fun handleMessage(msg: Message) {
            when(msg.what){
                updateText -> binding.tvTitle.text = "msg.obj as String"
            }
        }
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityThreadsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.btChange.setOnClickListener {
            thread {
                val msg = Message()
                msg.what = updateText
                handle.sendMessage(msg)
            }
        }
    }
}