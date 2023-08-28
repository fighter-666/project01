package com.example.myapplication.activity.components.bilibili.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.myapplication.R
import com.example.myapplication.activity.components.bilibili.widget.MyData

class SharedPreferencesActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_shared_preferences)

        //不能传this 当横竖屏切换，activity的创建和销毁，会导致内存泄露
        val myData = MyData(applicationContext)
        myData.number = 1000
        myData.save()
        val load = myData.load()
        val TAG = "myLog"
        Log.d(TAG, "onCreate: " + load)
    }
}