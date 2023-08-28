package com.example.myapplication.activity.components.bilibili.widget

import android.content.Context
import android.content.SharedPreferences
import com.example.myapplication.R

class MyData(private  var context: Context) {
    var number: Int = 0

    fun save() {
        val name = context.resources.getString(R.string.MY_DATA)
        val shp = context.getSharedPreferences(name, Context.MODE_PRIVATE)
        val editor: SharedPreferences.Editor = shp.edit()
        val key = context.resources.getString(R.string.MY_KEY)
        editor.putInt(key, number)
        editor.apply()
    }

    fun load() {
        val name = context.resources.getString((R.string.MY_DATA))
        val shp = context.getSharedPreferences(name, Context.MODE_PRIVATE)
        val key = context.resources.getString(R.string.MY_KEY)
        //shp.getInt(key, context.resources.getInteger(R.))
    }
}