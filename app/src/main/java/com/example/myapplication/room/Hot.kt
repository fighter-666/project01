package com.example.myapplication.room

import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey

@Entity(tableName = "cards")
data class Hot(
    @PrimaryKey(autoGenerate = true)
    var id: Long,
    var cardId: String? = null,
    var type: String? = null,
    var cardOrder: Int = 0,
){
    @Ignore
    constructor():this(0)
}

