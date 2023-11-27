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
    //函数的返回类型为List<Long>，表示在插入数据后，返回插入的每个数据的行ID（或主键值）的列表。
    //添加批量数据
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(vararg hot: Hot): List<Long>

    //下架卡片
    @Query("UPDATE Hot SET isTakeDown='1' WHERE cardId=:cardId")
    fun updateTakeDown(cardId: String): Int

    //上架卡片
    @Query("UPDATE Hot SET isTakeDown='0' WHERE cardId=:cardId ")
    fun updateUpLoad(cardId: String): Int


    // 更新卡片位置
    @Query("UPDATE Hot SET cardOrder=:cardOrder WHERE cardId=:cardId")
    fun updatePosition(cardOrder: Int, cardId: String): Int

    //获取所有卡片
    @Query("SELECT * FROM Hot")
    fun getAllUsers():  MutableList<Hot>

    //获取所有卡片
    @Query("SELECT * FROM Hot WHERE isTakeDown='0'" )
    fun getAllLoadUsers():  MutableList<Hot>

    @Query("SELECT * FROM Hot WHERE isTakeDown='0' and type='0'  ORDER BY cardOrder")
    fun getShowCard(): MutableList<Hot>

    @Query("SELECT * FROM Hot WHERE isTakeDown='1' and type='1'  ORDER BY cardOrder")
    fun getNoShowCard(): List<Hot>

    @Query("SELECT * FROM Hot WHERE isTakeDown='1' ORDER BY cardOrder ")
    fun getTakeDownCard(): List<Hot>

    @Query("SELECT * FROM Hot WHERE isTakeDown='0' ORDER BY cardOrder ")
    fun getUpLoadCard(): List<Hot>

    //获取所有卡片数量
    @Query("SELECT COUNT(*) FROM Hot")
    fun getRowCount(): Int


    // 获取所有上架卡片的最大位置
    @Query("SELECT MAX(cardOrder) FROM Hot WHERE isTakeDown='0' ")
    fun getMaxUpLoadCard(): Int

    // 获取所有下架卡片的最大位置
    @Query("SELECT MAX(cardOrder) FROM Hot WHERE isTakeDown='1' ")
    fun getMaxTakeDownCard(): Int

}