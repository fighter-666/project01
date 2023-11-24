package com.example.myapplication.room3

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.myapplication.room2.User

@Dao
interface HotDao {
    //使用"REPLACE"策略，表示如果有冲突，将替换已存在的数据
    // 函数的参数vararg hot: Hot表示可以接受多个Hot对象作为参数。vararg关键字表示这是一个可变参数，
    // 您可以一次传递多个Hot对象给这个函数。
    //
    //函数的返回类型为List<Long>，表示在插入数据后，返回插入的每个数据的行ID（或主键值）的列表。
    //添加批量数据

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(vararg hot: Hot): List<Long>

    @Update
     fun updateCard(hot: Hot)

    //更新某一条数据
    @Update
    fun updateUser(vararg hot: Hot)

    @Update
     fun updateItems(items:  MutableList<Hot>)

    @Query("SELECT * FROM Hot")
    fun getAllUsers():  MutableList<Hot>

    @Query("SELECT COUNT(*) FROM Hot")
    fun getRowCount(): Int

    @Query("SELECT * FROM Hot WHERE type='2' ORDER BY cardOrder")
    fun getShowCard(): List<Hot>

    @Query("SELECT * FROM Hot ORDER BY type ASC")
    fun update(): List<Hot>

    //删除表里所有数据
    @Query("delete from Hot")
    fun deleteAllUser()
}