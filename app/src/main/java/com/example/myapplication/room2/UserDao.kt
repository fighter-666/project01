package com.example.myapplication.room2

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update


@Dao
interface UserDao {
    //查询
    @Query("select * from loginUser")
    fun queryAllUser(): MutableList<User>

    //根据姓名参数查询
    @Query("select * from loginUser where name = :name")
    fun queryFindUser(name: String): User?

    //添加单条数据
    @Insert
    fun addUser(vararg user: User)

    //添加批量数据
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addBatchUser(list: MutableList<User>)

    //更新某一条数据
    @Update
    fun updateUser(vararg user: User)

    //更新所有数据
    @Query("update loginUser set age = '50'")
    fun updateAll()

    //删除某一个数据
    @Delete
    fun deleteSingle(vararg user: User)

    //删除表里所有数据
    @Query("delete from loginUser")
    fun deleteAllUser()

}
