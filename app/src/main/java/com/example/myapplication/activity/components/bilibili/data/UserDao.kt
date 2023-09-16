package com.example.myapplication.activity.components.bilibili.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface UserDao {
    // 查询所有用户信息
    @Query("SELECT * FROM user")
    fun getAll(): List<UserEntity>

    // 插入用户信息
    @Insert
    fun insertUser(userEntity: UserEntity)

    @Query("SELECT * FROM user WHERE account = :account")
    fun getInfoByAct(account: String): List<UserEntity>

}