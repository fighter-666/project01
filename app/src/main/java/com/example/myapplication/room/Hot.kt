package com.example.myapplication.room

import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey

@Entity(tableName = "cards")
data class Hot(
    // 使用@PrimaryKey注解将它设为了主键，再把autoGenerate参数指定成true，使得主键的值是自动生成的
    @PrimaryKey(autoGenerate = true)
    var id: Long,
    var type: String? = null, // 显示类型 0.本地显示 1.本地不显示
    var cardOrder: Int = 0,
){
    @Ignore
    constructor():this(0)
}

