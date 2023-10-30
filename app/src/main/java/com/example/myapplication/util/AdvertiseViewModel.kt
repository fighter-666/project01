package com.example.myapplication.util

import android.app.Application
import androidx.lifecycle.AndroidViewModel

class AdvertiseViewModel(application: Application): AndroidViewModel(application) {
    /*
    * 计时开始时间，默认5秒
    * */
    var millisInFuture: Long = 5000
}