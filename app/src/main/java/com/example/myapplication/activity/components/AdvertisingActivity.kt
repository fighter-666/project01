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

    private var advertisingManage: AdvertisingManage? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_advertising)
        btnIngore = findViewById(R.id.btnIgnore)
        tvAdvertisingTime = findViewById(R.id.tvAdvertisingTime)
        advertisingManage =
            AdvertisingManage()
        advertisingManage?.advertisingManageListener =
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
        //开始广告
        advertisingManage?.start()
    }

    override fun onDestroy() {
        super.onDestroy()
        advertisingManage?.onCancle()
    }
}



