package com.example.myapplication.util

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.myapplication.data.AccountBean

@Database(entities = [AccountBean::class], version = 1, exportSchema = false)
abstract class AccountDataBase: RoomDatabase() {
    abstract val accountDao: AccountDao

    companion object {
      /*  val accountDb: AccountDataBase by lazy(mode = LazyThreadSafetyMode.SYNCHRONIZED){
            Room.databaseBuilder(
                    BaseApplicationActivity().context,
                    AccountDataBase::class.java,
                    "account_db"
            ).allowMainThreadQueries().build()
        }*/
    }
}