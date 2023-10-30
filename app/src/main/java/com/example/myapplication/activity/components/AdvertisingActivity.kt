package com.example.myapplication.activity.components

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.myapplication.R
import android.widget.Button
import android.widget.TextView
import com.example.myapplication.MainActivity
import com.example.myapplication.util.AdvertisingManage

class AdvertisingActivity : AppCompatActivity() {
    //跳过广告按钮
    lateinit var btnIngore: Button
    //广告时间
    lateinit var tvAdvertisingTime: TextView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_advertising)
        val advertisingManage =
            AdvertisingManage
        //在activity中注册addObserver方法注册AdvertisingManage
        lifecycle.addObserver(advertisingManage)

        btnIngore = findViewById(R.id.btnIgnore)
        tvAdvertisingTime = findViewById(R.id.tvAdvertisingTime)

        advertisingManage.advertisingManageListener =
            object : AdvertisingManage.AdvertisingManageListener {
                override fun timing(second: Int) {
                    tvAdvertisingTime.text = "广告剩余时间：$second"
                }

                override fun enterMainActivity() {
                    MainActivity.actionStart(this@AdvertisingActivity)
                    finish()
                }
            }
        //跳过广告
        btnIngore.setOnClickListener {
            MainActivity.actionStart(this)
            finish()
        }
    }


}



