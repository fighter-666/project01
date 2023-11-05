package com.example.myapplication.activity.components.bilibili.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(
    entities = [UserEntity::class],
    version = 1,
    exportSchema = false
)
abstract class MyDataBase : RoomDatabase() {
    companion object {
        private var db: MyDataBase? = null
        private val name = "app"
        fun getDB(context: Context) = if (db == null) {
            Room.databaseBuilder(context, MyDataBase::class.java, name)
                .enableMultiInstanceInvalidation().build().apply {
                db = this
            }
        } else {
            db!!

        }
    }

    abstract fun getUserDao(): UserDao
}