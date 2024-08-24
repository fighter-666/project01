package com.example.myapplication.service

import android.app.IntentService
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.app.Service
import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Binder
import android.os.Build
import android.os.IBinder
import android.util.Log
import androidx.core.app.NotificationCompat
import com.example.myapplication.R
import com.example.myapplication.activity.components.ServiceActivity

class FrontDeskService : IntentService("MyService") {
    private val TAG = "MyService"

    private val mBinder = DownloadBinder()

    override fun onCreate() {
        super.onCreate()
        Log.d(TAG, "onCreate")
        val manager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            val channel = NotificationChannel("my_service","前台Service通知",NotificationManager.IMPORTANCE_DEFAULT)
            manager.createNotificationChannel(channel)
        }
        val intent = Intent(this, ServiceActivity::class.java)
        val pi = PendingIntent.getActivities(this, 0, arrayOf(intent), PendingIntent.FLAG_IMMUTABLE)
        val notification = NotificationCompat.Builder(this, "my_service")
            .setContentTitle("前台Service通知")
            .setContentText("正在下载")
            .setSmallIcon(R.drawable.ic_launcher_foreground)
            .setLargeIcon(BitmapFactory.decodeResource(resources, R.drawable.ic_launcher_foreground))
            .setContentIntent(pi)
            .build()
        startForeground(1, notification)
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        Log.d(TAG, "onStartCommand")
        return super.onStartCommand(intent, flags, startId)
    }

    class DownloadBinder: Binder() {
        fun startDownload() {
            Log.d("MyService", "startDownload")
        }

        fun getProgress():Int {
            Log.d("MyService", "getProgress")
            return 0
        }
    }

    override fun onBind(intent: Intent): IBinder {
        Log.d(TAG, "onBind")
        return mBinder
    }

    override fun onUnbind(intent: Intent?): Boolean {
        Log.d(TAG, "onUnbind")
        return super.onUnbind(intent)
    }

    override fun onHandleIntent(intent: Intent?) {
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d(TAG, "onDestroy")
    }
}