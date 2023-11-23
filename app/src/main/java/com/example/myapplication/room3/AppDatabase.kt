package com.example.myapplication.room3

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(version = 1, entities = [User::class], exportSchema = false)
abstract class AppDatabase :RoomDatabase(){
    abstract fun userDao(): UserDao

    companion object{
        private var instance: AppDatabase?= null
        fun getDatabase(context: Context): AppDatabase{
            //在非空的情况下执行一段代码块
            instance?.let {
                return it
            }
            return Room.databaseBuilder(context.applicationContext,
                AppDatabase::class.java, "app_database")
                .allowMainThreadQueries()
                .build().apply {
                    instance = this
                }
        }
    }
}