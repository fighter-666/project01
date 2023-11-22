package com.example.myapplication.room2

import android.util.Log
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.myapplication.activity.MyApplication

object DbManager {
    //数据库名
    private const val dbName: String = "zroom"

    //懒加载创建数据库
    val db: ZRoomDB by lazy {
        Room.databaseBuilder(
            MyApplication.getContext().applicationContext,ZRoomDB::class.java,dbName
        ).allowMainThreadQueries()//允许在主线程操作
            .addCallback(DbCreateCallBack)//增加回调监听
            .addMigrations()//增加数据库迁移
            .build()
    }

    private object DbCreateCallBack: RoomDatabase.Callback(){
        //第一次创建数据库时调用
        override fun onCreate(db: SupportSQLiteDatabase) {
            super.onCreate(db)
            Log.e("TAG","first onCreate db version:" + db.version)
        }
    }
}