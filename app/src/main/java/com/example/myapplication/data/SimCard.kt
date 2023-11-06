package com.example.myapplication.data

import android.util.Log
import javax.inject.Inject

class SimCard @Inject constructor(){
    private val TAG = "SimCard"
    fun dialNumber(){
        Log.d(TAG, "拨打电话 ")
    }
}