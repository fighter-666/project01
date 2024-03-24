package com.example.myapplication.activity.components

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.graphics.BitmapFactory
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.app.NotificationCompat
import com.example.myapplication.R
import com.example.myapplication.databinding.ActivityNotificationBinding

class NotificationActivity : AppCompatActivity() {
    private lateinit var binding: ActivityNotificationBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityNotificationBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val manager = getSystemService(Context.NOTIFICATION_SERVICE) as
                NotificationManager
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            val  channel = NotificationChannel("normal","Normal", NotificationManager.IMPORTANCE_DEFAULT)
            manager.createNotificationChannel(channel)
        }
        binding.sendNotification.setOnClickListener {
            val intent = Intent(this, NotificationActivity::class.java)
            val pi = PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_MUTABLE)
            val notification = NotificationCompat.Builder(this,"normal")
                .setContentTitle("This is content title")
                .setContentText("This is content text")
                .setSmallIcon(R.drawable.card2)
                .setLargeIcon(BitmapFactory.decodeResource(resources,R.drawable.card3))
                .setContentIntent(pi)
                .build()
            manager.notify(1,notification)
        }

    }
}