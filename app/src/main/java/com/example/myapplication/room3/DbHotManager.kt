package com.example.myapplication.room3

import androidx.room.Room
import com.example.myapplication.activity.MyApplication

object DbHotManager {
    //数据库名
    private const val dbName: String = "hotRoom"

    //懒加载创建数据库
    val db: HotDatabase by lazy {
        Room.databaseBuilder(
            MyApplication.getContext().applicationContext, HotDatabase::class.java, dbName
        ).allowMainThreadQueries()//允许在主线程操作
            .addMigrations()//增加数据库迁移
            .build()
    }
}