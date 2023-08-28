package com.example.myapplication.activity.components.bilibili.util

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class ViewModelWithDataBinding : ViewModel() {
    //使用 by lazy 委托属性来延迟初始化的 MutableLiveData 对象。
    // 这样可以确保在第一次访问 likedNumber 时进行初始化
    val number: MutableLiveData<Int> by lazy {
        //使用 apply 函数对 MutableLiveData 进行初始化，并设置初始值为 0。
        MutableLiveData<Int>().apply { value = 0 }
    }
    fun add() {
        number.value = number.value?.plus(1)
    }
}