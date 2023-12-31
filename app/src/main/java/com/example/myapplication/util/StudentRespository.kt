package com.example.myapplication.util

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData

class StudentRespository {
    /*
    * 根据id获取分数，模拟网络请求
    * */
    fun getStudentScore(id:String ) :LiveData<Int>{
        val studentMutableLiveData = MutableLiveData<Int>()
        if (id == "1"){
            studentMutableLiveData.value =90
        }else{
            studentMutableLiveData.value = 60
        }
        return studentMutableLiveData
    }
}