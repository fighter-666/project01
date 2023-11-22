package com.example.myapplication.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy

@Dao
interface HotDao {
    /*onConflict = OnConflictStrategy.REPLACE是一个参数，用于指定在插入冲突时采取的策略。
    在这种情况下，使用"REPLACE"策略，表示如果有冲突，将替换已存在的数据。
    这意味着如果插入的数据已经存在于数据库中（根据主键或唯一约束），则会用新的数据替换旧的数据。
函数的参数vararg hot: Hot表示可以接受多个Hot对象作为参数。vararg关键字表示这是一个可变参数，
您可以一次传递多个Hot对象给这个函数。
函数的返回类型为List<Long>，表示在插入数据后，返回插入的每个数据的行ID（或主键值）的列表。
总结起来，这段代码表示一个将一组Hot对象插入到数据库中的方法，并在插入冲突时使用替换策略。
函数返回插入数据的每个行ID的列表。*/
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(vararg hot: Hot)

}