package com.example.myapplication

import android.app.Service
import android.content.Intent
import android.os.IBinder
import android.util.Log
import com.example.myapplication.activity.components.OkhttpActivity
import okhttp3.Call
import okhttp3.Request
import java.io.IOException

class MainAppService : Service() {

    private val TAG = "MainAppService"
    private lateinit var mStub: IAppAidlInterface.Stub
    private lateinit var mStrData: String
    private var mSetServiceRunning:Boolean = true


    override fun onCreate() {
        super.onCreate()
    }

    override fun onStart(intent: Intent?, startId: Int) {
        super.onStart(intent, startId)
        /*mStub =  object : IAppAidlInterface.Stub() {
            override fun pay() {
                Log.d("TAG666", "pay")
            }


        }*/
    }


    override fun onBind(intent: Intent): IBinder {
        Log.d(TAG, "onBind")
        mSetServiceRunning = true
        object : Thread() {
            override fun run() {
                while (mSetServiceRunning){
                    try {
                        Thread.sleep(1000)
                        Log.d(TAG, "mStrData = $mStrData")
                    }catch (e: InterruptedException){
                        e.printStackTrace()
                    }
                }
            }
        }.start()
        //Thread{}
        return mStub
    }

    override fun onRebind(intent: Intent?) {
        Log.d(TAG, "onRebind")
    }

    /*override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        Log.d(TAG, "onStartCommand")
        // 返回START_STICKY 表示当service进程被kill后，系统将会自动重启该service
        return
    }*/

    override fun onUnbind(intent: Intent?): Boolean {
        Log.d(TAG, "onUnbind")
        return true
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d(TAG, "onDestroy")
    }


}