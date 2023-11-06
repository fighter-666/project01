package com.example.myapplication.data

import android.util.Log
import javax.inject.Inject

class UserManager @Inject constructor(){
    val TAG = "UserManager"
    fun getUserToken() {
        Log.d(TAG, "getUserToken: ")
    }
}