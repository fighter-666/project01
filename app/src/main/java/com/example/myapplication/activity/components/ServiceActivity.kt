package com.example.myapplication.activity.components

import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.ServiceConnection
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.IBinder
import android.view.View
import com.example.myapplication.databinding.ActivityServiceBinding
import com.example.myapplication.service.MyService

class ServiceActivity : AppCompatActivity() {
    private lateinit var binding: ActivityServiceBinding
    lateinit var downloadBinder: MyService.DownloadBinder


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityServiceBinding.inflate(layoutInflater)
        setContentView(binding.root)


     /*   //沉浸式处理
        ImmersionBar.with(this)
            .transparentStatusBar()  //透明状态栏，不写默认透明色
            .init()*/


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
            downloadBinder = service as MyService.DownloadBinder
            downloadBinder.startDownload()
            downloadBinder.getProgress()
        }

        override fun onServiceDisconnected(name: ComponentName?) {
        }

    }


/*    private val connect = object : ServiceConnection {
        override fun onServiceConnected(name: ComponentName?, service: IBinder?) {
        }

        override fun onServiceDisconnected(name: ComponentName?) {
        }
    }*/
}