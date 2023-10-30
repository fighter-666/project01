package com.example.myapplication.activity.components

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.myapplication.R
import android.widget.Button
import android.widget.TextView
import androidx.lifecycle.ViewModelProvider
import com.example.myapplication.MainActivity
import com.example.myapplication.util.AdvertiseViewModel
import com.example.myapplication.util.AdvertisingManage
import com.gyf.immersionbar.ImmersionBar

class AdvertisingActivity : AppCompatActivity() {
    //跳过广告按钮
    lateinit var btnIngore: Button
    //广告时间
    lateinit var tvAdvertisingTime: TextView

    private lateinit var advertiseViewModel: AdvertiseViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_advertising)
        //沉浸式处理
        ImmersionBar.with(this)
            .transparentStatusBar()  //透明状态栏，不写默认透明色
            .init()

        advertiseViewModel = ViewModelProvider(this).get(AdvertiseViewModel::class.java)
        val advertisingManage =
            AdvertisingManage(advertiseViewModel.millisInFuture)
        //在activity中注册addObserver方法注册AdvertisingManage
        lifecycle.addObserver(advertisingManage)

        btnIngore = findViewById(R.id.btnIgnore)
        tvAdvertisingTime = findViewById(R.id.tvAdvertisingTime)

        //回调
        advertisingManage.advertisingManageListener =
            object : AdvertisingManage.AdvertisingManageListener {
                override fun timing(second: Int) {
                    tvAdvertisingTime.text = "广告剩余时间：$second"
                    advertiseViewModel.millisInFuture = second.toLong() * 1000
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



