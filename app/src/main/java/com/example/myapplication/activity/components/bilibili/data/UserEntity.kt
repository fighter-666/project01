package com.example.myapplication.activity.components.bilibili.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "user")
data class UserEntity (
    @PrimaryKey(autoGenerate = true)
    var id: Int? = null,
    @ColumnInfo(name = "account")
    var account: String,
    @ColumnInfo(name = "nickname")
     var nickname: String

        )
