package com.example.myapplication.util

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.myapplication.data.AccountBean

@Dao
interface AccountDao {
    /*
    * 查询账号数据
    * @param accountBean 数据实体
    * */
    @Insert
    fun insert(accountBean: AccountBean)

    /*
    * 查询账号列表
    * @return 账号列表
    * */
    @Query("select * from account")
    fun loadAccount(): List<AccountBean>?

}