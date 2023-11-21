package com.example.myapplication.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy

@Dao
interface HotDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(vararg hot: Hot): List<Long>
   /* @Query("SELECT * FROM cards order by id asc")
    fun getAllCards(): List<HotCard>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCard(card: HotCard)*/

   /* @Update
    suspend fun updateCard(card: MutableList<HotCard>)

    @Delete
    suspend fun deleteCard(card: HotCard)*/
}