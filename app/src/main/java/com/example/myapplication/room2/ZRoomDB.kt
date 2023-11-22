package com.example.myapplication.room2

import androidx.room.Database
import androidx.room.RoomDatabase

/**
 * 数据库创建
 * entities: 实体类
 * version: 数据库初始版本号
 * exportSchema: 是否允许数据库架构导出到给定的文件夹中【 默认true 】
 *
 */

@Database(entities = [User::class], version = 1, exportSchema = false)
abstract class ZRoomDB: RoomDatabase() {
    abstract fun userDao(): UserDao
}