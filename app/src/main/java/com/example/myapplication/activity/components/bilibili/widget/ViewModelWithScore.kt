package com.example.myapplication.activity.components.bilibili.widget

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class ViewModelWithScore : ViewModel() {
    //使用 by lazy 委托属性来延迟初始化的 MutableLiveData 对象。
    // 这样可以确保在第一次访问 likedNumber 时进行初始化
    val aTeamScore: MutableLiveData<Int> by lazy {
        //使用 apply 函数对 MutableLiveData 进行初始化，并设置初始值为 0。
        MutableLiveData<Int>().apply { value = 0 }
    }
    val bTeamScore: MutableLiveData<Int> by lazy {
        //使用 apply 函数对 MutableLiveData 进行初始化，并设置初始值为 0。
        MutableLiveData<Int>().apply { value = 0 }
    }
    var aBack: Int = 0
    var bBack: Int = 0


    fun aTeamAdd(p: Int) {
        aBack = aTeamScore.value!!
        bBack = bTeamScore.value!!
        aTeamScore.value = aTeamScore.value?.plus(p)
    }

    fun bTeamAdd(p: Int) {
        aBack = aTeamScore.value!!
        bBack = bTeamScore.value!!
        bTeamScore.value = bTeamScore.value?.plus(p)
    }

    fun reset() {
        aBack = aTeamScore.value!!
        bBack = bTeamScore.value!!
        aTeamScore.value = 0
        bTeamScore.value = 0
    }

    fun undo() {
        aTeamScore.value = aBack
        bTeamScore.value = bBack
    }
}