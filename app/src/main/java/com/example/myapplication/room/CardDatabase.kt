/*
package com.example.myapplication.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

*/
/*
@Database(version = 1, entities = [User::class], exportSchema = false)
abstract class CardDatabase :RoomDatabase(){
    abstract fun cardDao(): CardDao

    companion object{
        private var instance: CardDatabase?= null
        fun getDatabase(context: Context): CardDatabase{
            //在非空的情况下执行一段代码块
            instance?.let {
                return it
            }
            return Room.databaseBuilder(context.applicationContext,
                CardDatabase::class.java, "card_database")
                .allowMainThreadQueries()
                .build().apply {
                    instance = this
                }
        }
    }
}*//*


@Database(entities = [Hot::class], version = 1)
abstract class CardDatabase : RoomDatabase() {
    abstract fun hotDao(): HotDao

    companion object {
        @Volatile
        private var INSTANCE: CardDatabase? = null

        fun getDatabase(context: Context): CardDatabase {
            val tempInstance = INSTANCE
            if (tempInstance != null) {
                return tempInstance
            }
            synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    CardDatabase::class.java,
                    "hot_database"
                ).allowMainThreadQueries()
                    .build()
                INSTANCE = instance
                return instance
            }
        }
    }
}
*/
