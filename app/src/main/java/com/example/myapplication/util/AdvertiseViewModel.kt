package com.example.myapplication.util

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

/*
* 若开发者想在viewmodel类中使用资源文件，则要使用到Context上下文
* 一定不能将Activity的上下文传给 Viewmodel,否则会存在内存泄露的风险
* 只需要将父类Viewmodel修改为AndroidViewModel即可*/
class AdvertiseViewModel : ViewModel() {
    /*
    * 计时开始时间，默认5秒
    * */
    var millisInFuture: Long = 5000

    /*
    * 计时结果
    * */
    private var timingResult = MutableLiveData<Long>()

    val _timingResult: LiveData<Long>
        get() = timingResult

    fun setTimingResult(millisInFuture: Long) {
        timingResult.value = millisInFuture
    }

}