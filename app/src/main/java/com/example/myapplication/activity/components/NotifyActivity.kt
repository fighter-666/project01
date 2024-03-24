package com.example.myapplication.activity.components

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.graphics.BitmapFactory
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.app.NotificationCompat
import com.example.myapplication.R
import com.example.myapplication.databinding.ActivityNotifyBinding

class NotifyActivity : AppCompatActivity() {
    private lateinit var binding: ActivityNotifyBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityNotifyBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val manager = getSystemService(Context.NOTIFICATION_SERVICE) as
                NotificationManager
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            val  channel = NotificationChannel("normal","Normal",NotificationManager.IMPORTANCE_DEFAULT)
            manager.createNotificationChannel(channel)
        }

        binding.sendNotice.setOnClickListener {
            val notification = NotificationCompat.Builder(this,"normal")
                .setContentTitle("This is content title")
                .setContentText("This is content text")
                .setSmallIcon(R.drawable.beans)
                .setLargeIcon(BitmapFactory.decodeResource(resources,R.drawable.batman))
                .build()
            manager.notify(1,notification)
        }
    }
}