package com.example.myapplication.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Account")
data class AccountBean (
    @PrimaryKey (autoGenerate = true)
    var accountId: Int,
     @ColumnInfo(name = "_loginAccount")
     var loginAccount: String,
     @ColumnInfo(name = "_loginPassword")
     var loginPassword: String
)