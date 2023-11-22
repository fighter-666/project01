package com.example.myapplication.room2

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey

@Entity(tableName = "loginUser")
data class User (
    @PrimaryKey(autoGenerate = true)
    var id:Int = 0,
    @ColumnInfo(name = "name")
    var aliasName :String = "",
    var age :Int = 0,
    var ads: String ="",
    @Ignore
    var avatar: String = ""
){
    //次构造函数，使用以下方式创建User对象：val user = User("John", 25, "123 Main St", "avatar.jpg")
    constructor(aliasName: String,age: Int,ads: String,avatar: String): this(){
        this.aliasName = aliasName
        this.age = age
        this.ads = ads
        this.avatar = avatar
    }

    //这是一个重写的toString()函数，用于以字符串形式返回User对象的信息。
    override fun toString(): String {
        return "User(id $id, aliasName='$aliasName', age=$age, ads='$ads', avatar='$avatar')"
    }
}