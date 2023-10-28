package com.example.myapplication.activity.components

import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.ServiceConnection
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.IBinder
import android.view.View
import com.example.myapplication.R
import com.example.myapplication.service.MyService

class ServiceActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_service)

    }

    //启动服务
    fun startService(view: View) {
        startService(Intent(this, MyService::class.java))
    }

    //停止服务
    fun stopService(view: View) {
        stopService(Intent(this, MyService::class.java))
    }

    fun bindService(view: View) {
        bindService(Intent(this, MyService::class.java), connect, Context.BIND_AUTO_CREATE)
    }

    fun unBindService(view: View) {
        unbindService(connect)
    }

    private val connect = object : ServiceConnection {
        override fun onServiceConnected(name: ComponentName?, service: IBinder?) {
            TODO("Not yet implemented")
        }

        override fun onServiceDisconnected(name: ComponentName?) {
            TODO("Not yet implemented")
        }
    }
}