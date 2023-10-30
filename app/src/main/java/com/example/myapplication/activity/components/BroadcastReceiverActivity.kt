package com.example.myapplication.activity.components

import android.content.Intent
import android.content.IntentFilter
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.example.myapplication.R
import com.example.myapplication.broadcast.UpdateIpSelectCity
import com.gyf.immersionbar.ImmersionBar

class BroadcastReceiverActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_broadcast_receiver)
        //沉浸式处理
        ImmersionBar.with(this)
            .transparentStatusBar()  //透明状态栏，不写默认透明色
            .init()

        //第二步，在onCreate()方法中注册广播接收器
        //动态注册一个广播接收者
        val updateIpSelectCity = UpdateIpSelectCity()
        val filter = IntentFilter()
        filter
            .addAction("com.example.myapplication.broadcast.UpdateIpSelectCity")
        registerReceiver(updateIpSelectCity, filter)

    }

    //第三步
    //发送给 动态注册的接收者
    fun sendAction(view: View) {
        val intent = Intent()
        //与注册时保持一致
        intent.action =
            "com.example.myapplication.broadcast.UpdateIpSelectCity"
        sendBroadcast(intent)

    }
}

