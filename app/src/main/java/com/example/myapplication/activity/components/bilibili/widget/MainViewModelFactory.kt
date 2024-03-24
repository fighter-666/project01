package com.example.myapplication.activity.components.bilibili.widget

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class MainViewModelFactory(private val countReserved:Int):ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return MyViewModel(countReserved) as T // 这里需要强制转换，因为泛型擦除的问题
    }
}