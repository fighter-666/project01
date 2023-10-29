package com.example.myapplication.broadcast

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.util.Log

//第一步，定义广播接收者（Receiver）
open class UpdateIpSelectCity : BroadcastReceiver() {
    private  val TAG = UpdateIpSelectCity::class.java.simpleName

    override fun onReceive(context: Context?, intent: Intent?) {
        Log.d(TAG, "onReceive")
    }
}